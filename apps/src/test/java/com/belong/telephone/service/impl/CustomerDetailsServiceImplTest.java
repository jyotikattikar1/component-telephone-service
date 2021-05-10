package com.belong.telephone.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;
import com.belong.telephone.exception.ResourceNotFoundException;

/**
 * 
 * 
 * @author jyotikattikar
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerDetailsServiceImplTest {
	
	@InjectMocks
	private CustomerDetailsServiceImpl customerDetailsServiceImpl;

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);
	
	@Mock 
	private CustomerDataBuilderService customerDataBuilderService;
	
	@BeforeEach
	void init() {
		Map<String, List<Map<Long, Boolean>>> customerDetails = new HashMap<String, List<Map<Long,Boolean>>>();
		
		Map<Long, Boolean> telephoneDetails = new HashMap<Long, Boolean>();
		telephoneDetails.put(111111L, true);
		telephoneDetails.put(111112L, false);
		telephoneDetails.put(111113L, true);
		List<Map<Long, Boolean>> telephoneList = new ArrayList<Map<Long,Boolean>>();
		telephoneList.add(telephoneDetails);
		customerDetails.put("11111", telephoneList);
		
		telephoneDetails = new HashMap<Long, Boolean>();
		telephoneDetails.put(211111L, true);
		
		telephoneList = new ArrayList<Map<Long,Boolean>>();
		telephoneList.add(telephoneDetails);
		customerDetails.put("21111", telephoneList);
		
		when(customerDataBuilderService.getCustomerDetails()).thenReturn(customerDetails);
	}
	
	@Test
	public void getCustomerDetailsTest() throws Exception {

		CustomerDetailsDTO customerDetailsDTO = customerDetailsServiceImpl.getAllCustomerPhoneDeatils();
		assertNotNull(customerDetailsDTO);
		assertEquals(2, customerDetailsDTO.getTotal());
	}
	
	@Test
	public void getTelephoneDetailsTest() throws Exception {

		CustomerData customerData = customerDetailsServiceImpl.getTelephoneDetails("11111");
		assertNotNull(customerData);
		assertEquals(3, customerData.getTelephoneDetails().size());
	}
	
	@Test
	public void activatePhoneNumberTest() throws Exception {

		TelephoneDetailDTO telephoneDetailDTO = customerDetailsServiceImpl.activatePhoneNumber("11111",111112L, true);
		assertNotNull(telephoneDetailDTO);
		assertEquals(true, telephoneDetailDTO.isActive());
	}
	
	
	@Test
	public void getTelephoneDetailsTest_NoRecord() throws Exception {

		assertThrows(ResourceNotFoundException.class, () -> customerDetailsServiceImpl.getTelephoneDetails("111113"),
				"Customer id does not exists");
	}
	
	@Test
	public void activatePhoneNumber_NoRecord() throws Exception {

		assertThrows(ResourceNotFoundException.class, () -> customerDetailsServiceImpl.activatePhoneNumber("11111",111114L, true),
				"Customer id does not exists");
	}
	
}
