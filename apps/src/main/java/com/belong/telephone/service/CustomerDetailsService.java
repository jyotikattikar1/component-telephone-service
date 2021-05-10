package com.belong.telephone.service;

import java.util.List;

import com.belong.telephone.dto.CustomerDTO;
import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;
import com.belong.telephone.dto.TelephonesDTO;
import com.belong.telephone.exception.ResourceNotFoundException;

/**
 * 
 * @author jyotikattikar
 *
 */
public interface CustomerDetailsService {

	
	/**
	 * Method to get all Phone numbers from the system.
	 * 
	 * @return Phone numbers with customer details.
	 */
	CustomerDetailsDTO getAllCustomerPhoneDeatils();
	
    /**
     * Get Phone details of a customer 
     * 
     * @param customerId
     * @return
     */
    CustomerData getTelephoneDetails(String customerId) ;

	/**
	 * Method to activate/deactivate a phone number
	 * 
	 * @param customerId
	 * @param phone
	 * @param isActivate
	 * @return
	 * @throws ResourceNotFoundException
	 */
	TelephoneDetailDTO activatePhoneNumber(String customerId, Long phone, boolean isActivate)
			throws ResourceNotFoundException;

	/**
	 * Method to activate/Deactivate phone numbers in bulk.
	 * 
	 * @param customerDetails
	 * @return list of all the phone numbers activated/deactivated
	 */
	TelephonesDTO activatePhoneNumbers(List<CustomerDTO> customerDetails);

}
