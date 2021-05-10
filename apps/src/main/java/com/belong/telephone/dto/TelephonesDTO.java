package com.belong.telephone.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jyotikattikar
 *
 */
public class TelephonesDTO {
	
	Integer total;
	Set<Long> telephones = new HashSet<>();

	
	public Set<Long> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<Long> telephone) {
		this.telephones = telephone;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
