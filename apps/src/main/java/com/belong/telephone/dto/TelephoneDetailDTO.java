package com.belong.telephone.dto;

import java.io.Serializable;

/**
 * DTO to hold telephone details of the customers
 * 
 * @author jyotikattikar
 *
 */
public class TelephoneDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long telephone;
	
	private boolean isActive;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	
	
}
