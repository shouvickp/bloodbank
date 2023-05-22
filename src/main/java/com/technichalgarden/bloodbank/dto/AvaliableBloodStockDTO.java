package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;

public class AvaliableBloodStockDTO {

	private BloodGroup bloodGroup;
	private Long stockCount;

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getStockCount() {
		return stockCount;
	}

	public void setStockCount(Long stockCount) {
		this.stockCount = stockCount;
	}

	@Override
	public String toString() {
		return "AvaliableBloodStockDTO [bloodGroup=" + bloodGroup + ", stockCount=" + stockCount + "]";
	}

}
