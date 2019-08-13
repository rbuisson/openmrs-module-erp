package org.openmrs.module.erp.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.module.erp.ErpConstants;

import java.util.List;
import java.util.Map;

public interface ErpPartnerService {
	
	List<String> defaultModelAttributes();
	
	/**
	 * Returns an ERP partner by UUID.
	 * 
	 * @param erpPartnerUuid The ERP partner uuid
	 * @return JSONObject
	 * @throws APIException
	 */
	@Authorized(ErpConstants.MODULE_PRIVILEGE)
	Map<String, Object> getErpPartnerByUuid(String erpPartnerUuid);
	
}
