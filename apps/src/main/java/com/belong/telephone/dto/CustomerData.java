package com.belong.telephone.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO to hold details of the customers
 * 
 * @author jyotikattikar
 *
 */
public class CustomerData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String customer;
	
	List<TelephoneDetailDTO> telephoneDetails ;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<TelephoneDetailDTO> getTelephoneDetails() {
		return telephoneDetails;
	}
	public void setTelephoneDetails(List<TelephoneDetailDTO> telephoneDetails) {
		this.telephoneDetails = telephoneDetails;
	}
	

}
