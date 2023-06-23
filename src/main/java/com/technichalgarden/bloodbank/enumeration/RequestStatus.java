package com.technichalgarden.bloodbank.enumeration;

public enum RequestStatus {

	DRAFT("Draft"), SUBMITTED("Submitted"), REJECTED("Rejected"), ACCEPTED("Accepted");

	public final String value;

	private RequestStatus(String value) {
		this.value = value;
	}

	public static RequestStatus getEnum(String value) {
		for (RequestStatus rs : values()) {
			if (rs.value.equals(value)) {
				return rs;
			}
		}
		return null;
	}
}
