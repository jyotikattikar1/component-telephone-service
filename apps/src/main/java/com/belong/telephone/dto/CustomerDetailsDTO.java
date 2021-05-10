package com.belong.telephone.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO to hold details of the customers
 * 
 * @author jyotikattikar
 *
 */
public class CustomerDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer total;
	Set<CustomerData> customers  ;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Set<CustomerData> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<CustomerData> customers) {
		this.customers = customers;
	}


}
