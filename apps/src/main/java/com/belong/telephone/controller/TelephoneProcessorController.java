package com.belong.telephone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.belong.common.APIResponse;
import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;
import com.belong.telephone.service.TelephoneService;

/**
 * 
 * @author jyotikattikar
 *
 */
@RestController
@RequestMapping("/v1/telephone")
public class TelephoneProcessorController {

	@Autowired
	private TelephoneService telephoneService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<APIResponse<CustomerDetailsDTO>>  getAllCustomerPhoneDeatils(
            @RequestParam(name = "order-by", required = false) String orderBy,
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "identifier", required = false) String identifier,
            @RequestParam(name = "isActive", required = false, defaultValue = "false") Boolean isActive) {

        APIResponse<CustomerDetailsDTO>  apiResponse = telephoneService.getAllCustomerPhoneDeatils(orderBy, query, identifier, isActive);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<APIResponse<CustomerData>> getTelephoneDetails(@PathVariable(value = "customerId") String customerId) {

        APIResponse<CustomerData> apiResponse = telephoneService.getTelephoneDetails(customerId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
   

    @RequestMapping(method = RequestMethod.PUT, value = "/{customerId}/activate")
    private ResponseEntity<APIResponse<TelephoneDetailDTO>> activateTelephone(@PathVariable(value = "customerId") String customerId,
    		@RequestParam(value = "telephone") Long telephone) {
        APIResponse<TelephoneDetailDTO> apiResponse = telephoneService.activateTelephonePhoneNumber(customerId, telephone);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    

}
