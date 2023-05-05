package com.technichalgarden.bloodbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blood_bank_series")
public class BloodBankSeries {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "next_series")
	private Long nextSeries;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNexSeries() {
		return nextSeries;
	}

	public void setNexSeries(Long nextSeries) {
		this.nextSeries = nextSeries;
	}

	@Override
	public String toString() {
		return "BloodBankSeries [name=" + name + ", nextSeries=" + nextSeries + "]";
	}

}
