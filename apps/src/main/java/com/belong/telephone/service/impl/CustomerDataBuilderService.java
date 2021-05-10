package com.belong.telephone.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

/**
 *  Load the customer details from  csv
 * 
 * @author jyotikattikar
 *
 */
@Service
public class CustomerDataBuilderService {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDataBuilderService.class);
	
	private static Map<String, List<Map<Long, Boolean>>> customerDetails ;
	
	@Value("${csv.file}")
    private String csvFile;
	
	@Value("${test}")
    private String test;
	
	public void buildCustomerDetails() throws FileNotFoundException, IOException {
		customerDetails = new HashMap<String, List<Map<Long, Boolean>>>();
		try (CSVReader reader = new CSVReader(new FileReader("test.csv"))) {
			List<String[]> r = reader.readAll();
			r.forEach(item -> {
				addCustomerDetails(item[0], Long.parseLong(item[1]), Boolean.parseBoolean(item[2]));
			});
			logger.debug("Loaded customer details total customers {} ", customerDetails.size());
		}
	}
   
	
	public Map<String, List<Map<Long, Boolean>>> getCustomerDetails() {
		return customerDetails;
	}
	
	private Map<Long, Boolean> addPhoneDetails(Long phone, Boolean isActive){
		var phoneDetails = new HashMap<Long, Boolean>();
		phoneDetails.put(phone, isActive);
		return phoneDetails;
	}
	
	private void addCustomerDetails(String customerId, Long phone, Boolean isActive){
		var phoneDetails = addPhoneDetails( phone, isActive);
		List<Map<Long,Boolean>> phones = customerDetails.get(customerId);
		
		if(phones == null) {
			phones = new ArrayList<Map<Long,Boolean>>();
			customerDetails.put(customerId, phones);
		}
		phones.add(phoneDetails);
	}
	
}
