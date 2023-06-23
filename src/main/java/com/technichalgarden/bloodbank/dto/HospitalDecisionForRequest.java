package com.technichalgarden.bloodbank.dto;

public class HospitalDecisionForRequest {

	private Long requestId;
	private String decision;
	private String remarks;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "HospitalDecisionForRequest [requestId=" + requestId + ", decision=" + decision + ", remarks=" + remarks
				+ "]";
	}

}
