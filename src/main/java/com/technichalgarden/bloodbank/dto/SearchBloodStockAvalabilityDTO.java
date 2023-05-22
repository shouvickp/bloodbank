package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;

public class SearchBloodStockAvalabilityDTO {

	private String state;
	private String city;
	private BloodGroup bloodGroup;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Override
	public String toString() {
		return "SearchBloodStockAvalabilityDTO [State=" + state + ", city=" + city + ", bloodGroup=" + bloodGroup + "]";
	}

}
