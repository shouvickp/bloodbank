package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;
import com.technichalgarden.bloodbank.enumeration.RequestStatus;

public class RequestDTO {

	private Long id;

	private String requestRefNo;

	private Long recieverId;

	private Long hospitalId;

	private String patientName;

	private String patientEmail;

	private BloodGroup bloodGroup;

	private Long bottles;

	private RequestStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestRefNo() {
		return requestRefNo;
	}

	public void setRequestRefNo(String requestRefNo) {
		this.requestRefNo = requestRefNo;
	}

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

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

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getBottles() {
		return bottles;
	}

	public void setBottles(Long bottles) {
		this.bottles = bottles;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestDTO [id=" + id + ", requestRefNo=" + requestRefNo + ", recieverId=" + recieverId
				+ ", hospitalId=" + hospitalId + ", patientName=" + patientName + ", patientEmail=" + patientEmail
				+ ", bloodGroup=" + bloodGroup + ", bottles=" + bottles + ", status=" + status + "]";
	}

}
