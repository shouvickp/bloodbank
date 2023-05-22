package com.technichalgarden.bloodbank.dto;

import java.util.List;

public class BloodStockAvalibiltyInfoDTO {

	private Long hospitalId;
	private String hospitalName;
	private String hospitalAddress;
	private String hospitalContactNo;
	private String hospitalEmail;
	private List<AvaliableBloodStockDTO> bloodStocks;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalContactNo() {
		return hospitalContactNo;
	}

	public void setHospitalContactNo(String hospitalContactNo) {
		this.hospitalContactNo = hospitalContactNo;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public List<AvaliableBloodStockDTO> getBloodStocks() {
		return bloodStocks;
	}

	public void setBloodStocks(List<AvaliableBloodStockDTO> bloodStocks) {
		this.bloodStocks = bloodStocks;
	}

	@Override
	public String toString() {
		return "BloodStockAvalibiltyInfoDTO [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName
				+ ", hospitalAddress=" + hospitalAddress + ", hospitalContactNo=" + hospitalContactNo
				+ ", hospitalEmail=" + hospitalEmail + ", bloodStocks=" + bloodStocks + "]";
	}

}
