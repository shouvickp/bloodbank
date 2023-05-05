package com.technichalgarden.bloodbank.dto;

public class ResponseDTO<T> {

	private String responseMessage;

	private String responseCode;

	private boolean isError;

	private T responseObject;

	public ResponseDTO() {

	}

	public ResponseDTO(String responseMessage, String responseCode, boolean isError) {
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.isError = isError;
	}

	public ResponseDTO(String responseMessage, String responseCode, boolean isError, T responseObject) {
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.isError = isError;
		this.responseObject = responseObject;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public boolean getIsError() {
		return isError;
	}

	public void setIsError(boolean isError) {
		this.isError = isError;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	@Override
	public String toString() {
		return "ResponseDTO [responseMessage=" + responseMessage + ", responseCode=" + responseCode + ", isError="
				+ isError + ", responseObject=" + responseObject + "]";
	}

}
