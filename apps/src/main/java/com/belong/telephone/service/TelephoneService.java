package com.belong.telephone.service;

import org.springframework.lang.NonNull;

import com.belong.common.APIResponse;
import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;

/**
 * 
 * @author jyotikattikar
 *
 */
public interface TelephoneService {
	  
	  public APIResponse<CustomerDetailsDTO> getAllCustomerPhoneDeatils( String orderBy, String query, String identifier,
	            Boolean isActive) ;
	  
	  public APIResponse<CustomerData>  getTelephoneDetails(@NonNull String customerId);
	  
	  public APIResponse<TelephoneDetailDTO> activateTelephonePhoneNumber(String customerId, Long telephoneNumber) ;
		
}
