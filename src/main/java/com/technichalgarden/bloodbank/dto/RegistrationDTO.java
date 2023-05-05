package com.technichalgarden.bloodbank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.technichalgarden.bloodbank.enumeration.BloodGroup;
import com.technichalgarden.bloodbank.enumeration.UserType;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationDTO {
	@NotNull(message = "Name cannot be empty")
	@Size(min = 3, max=255, message="Name must contains minimum 3 characters and should not exceed 255 characters")
	private String name;

	@JsonProperty(value = "userName", access = JsonProperty.Access.READ_ONLY)
	private String loginName;

	@NotNull(message = "Email cannot be empty")
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email Provided")
	private String email;

	@NotNull(message = "Contact number cannot be empty")
	@Size(min=10, max = 10, message="Mobile number must have 10 digits")
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must contains 10 digits only")
	private String contactNumber;

	@NotNull(message = "Address cannot be empty")
	private String addressLine;

	@NotNull(message = "City cannot be empty")
	private String city;

	@NotNull(message = "State cannot be empty")
	private String state;

	@NotNull(message = "Pincode cannot be empty")
	@Size(min=6, max = 6, message="Pincode must have 6 digits")
	@Pattern(regexp = "^\\d{6}$", message = "Pincode must contains 6 digits only")
	private String pincode;

	@JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Password cannot be empty")
	private String password;

	@NotNull(message = "User type cannot be empty")
	private UserType userType;
	
	private BloodGroup bloodGroup;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

}
