package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;

public class BloodStockDTO {

	private Long id;
	private Long hospitalId;
	private BloodGroup bloodGroup;
	private Long stockCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

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
		return "BloodStockDTO [id=" + id + ", hospitalId=" + hospitalId + ", bloodGroup=" + bloodGroup + ", stockCount="
				+ stockCount + "]";
	}

}
