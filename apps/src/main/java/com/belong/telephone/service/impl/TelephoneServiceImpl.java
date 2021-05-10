package com.belong.telephone.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.belong.common.APIResponse;
import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;
import com.belong.telephone.service.CustomerDetailsService;
import com.belong.telephone.service.TelephoneService;

/**
 * 
 * @author jyotikattikar
 *
 */
@Service
public class TelephoneServiceImpl <T> implements TelephoneService {

	private static final Logger logger = LoggerFactory.getLogger(TelephoneServiceImpl.class);

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Override
	public APIResponse<CustomerDetailsDTO> getAllCustomerPhoneDeatils(String orderBy, String query, String identifier,
			Boolean isActive) {
		logger.debug(" Get Telephone Numbers : orderby " + orderBy + " query " + query + " identifier " + identifier
				+ " isActive " + isActive );
		APIResponse<CustomerDetailsDTO> responseBody = new APIResponse<CustomerDetailsDTO>();
		CustomerDetailsDTO  data = customerDetailsService.getAllCustomerPhoneDeatils();
		responseBody.setData(data);
		return responseBody;
	}
	
	@Override
	public APIResponse<CustomerData>  getTelephoneDetails(@NonNull String customerId) {
		CustomerData  data = customerDetailsService.getTelephoneDetails(customerId);
		APIResponse<CustomerData> responseBody = new APIResponse<CustomerData>();
		responseBody.setData(data);
		return responseBody;
	}

	@Override
	public APIResponse<TelephoneDetailDTO> activateTelephonePhoneNumber(@NonNull String customerId, @NonNull Long phone) {
		TelephoneDetailDTO data = customerDetailsService.activatePhoneNumber(customerId, phone, true);
		APIResponse<TelephoneDetailDTO> responseBody = new APIResponse<TelephoneDetailDTO>();
		responseBody.setData(data);
		return responseBody;
	}
	
}
