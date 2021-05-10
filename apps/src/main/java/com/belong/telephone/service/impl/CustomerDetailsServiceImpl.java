package com.belong.telephone.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belong.telephone.dto.CustomerDTO;
import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;
import com.belong.telephone.dto.TelephonesDTO;
import com.belong.telephone.exception.ResourceNotFoundException;
import com.belong.telephone.service.CustomerDetailsService;

/** 
 * 
 * 
 * @author jyotikattikar
 *
 */
@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);
	
	@Autowired 
	private CustomerDataBuilderService customerDataBuilderService;
	
	
	private Map<String, List<Map<Long, Boolean>>> getCustomerDetails(){
		return customerDataBuilderService.getCustomerDetails(); 
	}

	@Override
	public TelephonesDTO activatePhoneNumbers(List<CustomerDTO> customerDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetailsDTO getAllCustomerPhoneDeatils() {
			
		Map<String, List<Map<Long, Boolean>>> data = getCustomerDetails();
		
		if (data == null) {
			throw new ResourceNotFoundException("Customer id does not exists");
		}
		
		CustomerDetailsDTO customerDetailsDTO =  new CustomerDetailsDTO();
		Set<CustomerData> customerDataSet = new HashSet<CustomerData>();
		Iterator<String> itr = data.keySet().iterator();
		
		while (itr.hasNext())
		{
			String item = itr.next();
			CustomerData customerData = new CustomerData();
			customerData.setCustomer(item);
			List<TelephoneDetailDTO>  telephoneDetailDTOList = new ArrayList<TelephoneDetailDTO>();
			data.get(item).forEach(mapItem -> {
				mapItem.keySet().forEach(key -> {
					TelephoneDetailDTO  telephoneDetailDTO = new TelephoneDetailDTO();
					telephoneDetailDTO.setActive(mapItem.get(key));
					telephoneDetailDTO.setTelephone(key);
					telephoneDetailDTOList.add(telephoneDetailDTO);
				});
			});
			customerData.setTelephoneDetails(telephoneDetailDTOList);
			customerDataSet.add(customerData);
		}
		customerDetailsDTO.setTotal(customerDataSet.size());
		customerDetailsDTO.setCustomers(customerDataSet);
		
		return customerDetailsDTO;
	}
	
	@Override
	public CustomerData getTelephoneDetails(String customerId) {
		List<Map<Long, Boolean>> data = getCustomerDetails().get(customerId);
		
		if (data == null) {
			throw new ResourceNotFoundException("Customer id does not exists");
		}
		
		CustomerData customerData = getCustomerData(customerId, data);
		
		return customerData;
		
	}

	private CustomerData getCustomerData(String customerId, List<Map<Long, Boolean>> data) {
		CustomerData customerData = new CustomerData();
		customerData.setCustomer(customerId);
		List<TelephoneDetailDTO>  telephoneDetailDTOList = new ArrayList<TelephoneDetailDTO>();
		data.forEach(mapItem -> {
			mapItem.keySet().forEach(key -> {
				TelephoneDetailDTO  telephoneDetailDTO = new TelephoneDetailDTO();
				telephoneDetailDTO.setActive(mapItem.get(key));
				telephoneDetailDTO.setTelephone(key);
				telephoneDetailDTOList.add(telephoneDetailDTO);
			});
		});
		customerData.setTelephoneDetails(telephoneDetailDTOList);
		return customerData;
	}
	
	@Override
	public TelephoneDetailDTO activatePhoneNumber(String customerId, Long phone, boolean isActivate)
			throws ResourceNotFoundException {
		return activateNumber( customerId,  phone,  isActivate);
	}
	
	private TelephoneDetailDTO activateNumber(String customerId, Long phone, Boolean isActivate) throws ResourceNotFoundException {
		List<Map<Long, Boolean>> phoneDetails = getCustomerDetails().get(customerId);
		var telephoneDTO = new TelephoneDetailDTO();
		if (null != phoneDetails) {
			phoneDetails.stream().forEach(item -> {
				if (item.containsKey(phone)) {
					item.put(phone, isActivate);
					logger.debug("Activate/Deactivate Customerid : {} , Phone {} , isActivate{} ", customerId, phone,
							isActivate);
					telephoneDTO.setActive(isActivate);
					telephoneDTO.setTelephone(phone);
					return;
				}
			});
		}
		
		if(telephoneDTO.getTelephone() == 0 ) {
			throw new ResourceNotFoundException("PhoneNumber does not exists");
		}
		
		return telephoneDTO;
	}
	
	
}
