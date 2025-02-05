package org.openmrs.module.erp.api;

import com.odoojava.api.FilterCollection;
import com.odoojava.api.ObjectAdapter;
import com.odoojava.api.OdooApiException;
import com.odoojava.api.Session;
import org.apache.xmlrpc.XmlRpcException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.erp.api.impl.odoo.OdooPartnerServiceImpl;
import org.openmrs.module.erp.api.impl.odoo.OdooSession;

import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.openmrs.module.erp.api.utils.TestHelper.getOdooRecord;
import static org.powermock.api.mockito.PowerMockito.when;

public class OdooPartnerServiceImplTest {
	
	private String[] fields = { "id", "name", "uuid", "create_date" };
	
	private ErpPartnerService odooPartnerService;
	
	@Before
	public void setup() throws XmlRpcException, OdooApiException {
		// Setup mocks
		Session session = mock(Session.class);
		ObjectAdapter objectAdapter = mock(ObjectAdapter.class);
		when(objectAdapter.searchAndReadObject(any(FilterCollection.class), any(String[].class)))
		        .thenReturn(getOdooRecord());
		when(objectAdapter.getFieldNames()).thenReturn(fields);
		when(session.getObjectAdapter(any(String.class))).thenReturn(objectAdapter);
		
		OdooSession odooSession = mock(OdooSession.class);
		when(odooSession.getSession()).thenReturn(session);
		
		odooPartnerService = new OdooPartnerServiceImpl(odooSession);
	}
	
	@Test
	public void getPartnerByUuidShouldReturnPartnerTest() {
		Map<String, Object> partners = odooPartnerService.getErpPartnerByUuid("1111-2222-3333");
		
		// Verify
		Assert.assertEquals("REC/001", String.valueOf(partners.get("name")));
		Assert.assertEquals("1", String.valueOf(partners.get("id")));
	}
	
}
