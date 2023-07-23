package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;

public class BloodRequsetForSelfDTO {

	private Long recieverId;
	private Long HospitalId;
	private BloodGroup bloodGroup;
	private Long quantity;

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getHospitalId() {
		return HospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		HospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return "BloodRequsetForSelfDTO [recieverId=" + recieverId + ", HospitalId=" + HospitalId + ", bloodGroup="
				+ bloodGroup + ", quantity=" + quantity + "]";
	}

}
