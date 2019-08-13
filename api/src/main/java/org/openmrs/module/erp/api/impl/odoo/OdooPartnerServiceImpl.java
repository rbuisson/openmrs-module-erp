package org.openmrs.module.erp.api.impl.odoo;

import com.odoojava.api.*;
import org.openmrs.api.APIException;
import org.openmrs.module.erp.ErpConstants;
import org.openmrs.module.erp.api.ErpPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(ErpConstants.COMPONENT_ODOO_PARTNER_SERVICE)
public class OdooPartnerServiceImpl implements ErpPartnerService {
	
	private static final String RES_PARTNER = "res_partner";
	
	private List<String> partnerDefaultAttributes = Arrays.asList("id", "name", "uuid", "create_date");
	
	private Session session;
	
	@Autowired
	private OdooSession odooSession;
	
	public OdooPartnerServiceImpl() {
	}
	
	public OdooPartnerServiceImpl(OdooSession odooSession) {
		this.session = odooSession.getSession();
	}
	
	@Override
	public List<String> defaultModelAttributes() {
		return partnerDefaultAttributes;
	}
	
	public Map<String, Object> getErpPartnerByUuid(String erpPartnerUuid) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		if (this.session == null) {
			this.session = odooSession.getSession();
		}
		try {
			session.startSession();
			ObjectAdapter objectAdapter = session.getObjectAdapter(RES_PARTNER);
			FilterCollection filters = new FilterCollection();
			
			String[] fields = objectAdapter.getFieldNames();
			
			filters.clear();
			filters.add("uuid", "=", erpPartnerUuid);
			
			RowCollection records = objectAdapter.searchAndReadObject(filters, fields);
			if ((records != null) && (!records.isEmpty())) {
				Row record = records.get(0);
				for (String field : fields) {
					Object value = record.get(field);
					response.put(field, value);
				}
			}
		}
		catch (Exception e) {
			throw new APIException("Error while reading data from ERP server", e);
		}
		return response;
	}
}
