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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "_reciever")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reciever extends BbankModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "reciever_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "login_name", nullable = false, length = 8)
	private String loginName;

	@Column(name = "email", nullable = false, length = 255)
	private String email;

	@Column(name = "mobile_no", nullable = false, length = 10)
	private String mobileNo;

	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group", nullable = false, length = 12)
	private BloodGroup bloodGroup;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
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
		return "Reciever [id=" + id + ", name=" + name + ", loginName=" + loginName + ", email=" + email + ", mobileNo="
				+ mobileNo + ", bloodGroup=" + bloodGroup + ", addressLine=" + addressLine + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + "]";
	}

}
