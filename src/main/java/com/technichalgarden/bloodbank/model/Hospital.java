package com.technichalgarden.bloodbank.model;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Hospital extends BbankModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "hospital_name", nullable = false, length = 255)
	private String hospitalName;

	@Column(name = "login_name", nullable = false, length = 8)
	private String loginName;

	@Column(name = "email", nullable = false, length = 255)
	private String email;

	@Column(name = "contact_number", nullable = false, length = 10)
	private String contactNumber;

	@Column(name = "addressLine", nullable = false, length = 4096)
	private String addressLine;

	@Column(name = "city", nullable = false, length = 255)
	private String city;

	@Column(name = "state", nullable = false, length = 255)
	private String state;

	@Column(name = "pincode", nullable = false, length = 6)
	private String pincode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", hospitalName=" + hospitalName + ", loginName=" + loginName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", addressLine=" + addressLine + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + "]";
	}

}
