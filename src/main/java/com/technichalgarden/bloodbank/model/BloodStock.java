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
@Table(name = "_blood_stock")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodStock extends BbankModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "blood_stock_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@Column(name = "id")
	private Long id;

	@Column(name = "hospital_id", nullable = false)
	private Long hospitalId;

	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group", nullable = false, length = 12)
	private BloodGroup bloodGroup;

	@Column(name = "stock_count", nullable = false)
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
		return "BloodStock [id=" + id + ", hospitalId=" + hospitalId + ", bloodGroup=" + bloodGroup + ", stockCount="
				+ stockCount + "]";
	}

}
