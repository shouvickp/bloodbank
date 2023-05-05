package com.technichalgarden.bloodbank.model;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "requests")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Requests extends BbankModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "request_ref_no", nullable = false)
	private String requestRefNo;

	@Column(name = "reciever_id", nullable = false)
	private Long recieverId;

	@Column(name = "hospital_id", nullable = false)
	private Long hospitalId;

	@Column(name = "patient_name", nullable = false, length = 255)
	private String patientName;

	@Column(name = "patient_email", nullable = false, length = 255)
	private String patientEmail;

	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group", nullable = false, length = 12)
	private BloodGroup bloodGroup;

	@Column(name = "bottles", nullable = false)
	private Long bottles;

	@Column(name = "status", nullable = false)
	private Boolean status;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Requests [id=" + id + ", requestRefNo=" + requestRefNo + ", recieverId=" + recieverId + ", hospitalId="
				+ hospitalId + ", patientName=" + patientName + ", patientEmail=" + patientEmail + ", bloodGroup="
				+ bloodGroup + ", bottles=" + bottles + ", status=" + status + "]";
	}

}
