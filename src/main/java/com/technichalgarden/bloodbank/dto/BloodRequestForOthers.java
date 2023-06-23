package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;

public class BloodRequestForOthers {

	private Long hospitalId;
	private String patientName;
	private String patientEmail;
	private BloodGroup patientBloodGroup;
	private Long quantity;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public BloodGroup getPatientBloodGroup() {
		return patientBloodGroup;
	}

	public void setPatientBloodGroup(BloodGroup patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return "BloodRequestForOthers [hospitalId=" + hospitalId + ", patientName=" + patientName + ", patientEmail="
				+ patientEmail + ", patientBloodGroup=" + patientBloodGroup + ", quantity=" + quantity + "]";
	}

}
