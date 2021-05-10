package com.belong.telephone.dto;

import java.io.Serializable;

/**
 * DTO to hold details of the customers
 * 
 * @author jyotikattikar
 *
 */
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String customerid;
	Long phone;
	Boolean isActive;
	
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
