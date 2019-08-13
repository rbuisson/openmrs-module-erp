package org.openmrs.module.erp.web.controller;

import org.openmrs.module.erp.ErpConstants;
import org.openmrs.module.erp.ErpContext;
import org.openmrs.module.erp.api.ErpPartnerService;
import org.openmrs.module.erp.web.RecordRepresentation;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller(value = "/rest/" + RestConstants.VERSION_1 + "/" + "erp" + ErpConstants.ERP_PARTNER_URI)
public class ErpPartnerController extends BaseRestController {
	
	@Autowired
	protected ErpContext erpContext;
	
	@Autowired
	private ErpPartnerService erpPartnerService;
	
	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getErpPartnerByUuid(@PathVariable("uuid") String id, @RequestParam(defaultValue = "default") String rep) {
		erpPartnerService = erpContext.getErpPartnerService();
		return new RecordRepresentation(erpPartnerService.defaultModelAttributes()).getRepresentedRecord(
		    erpPartnerService.getErpPartnerByUuid(id), rep);
	}
	
}
