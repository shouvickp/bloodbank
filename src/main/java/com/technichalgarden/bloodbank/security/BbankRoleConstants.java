package com.technichalgarden.bloodbank.security;

public class BbankRoleConstants {

	/** The Constant HOSPITAL **/
	public static final String HOSPITAL = "BLOOD_BANK";

	/** The Constant RECIEVER **/
	public static final String RECIEVER = "RECIEVER";

	public class CommonRoles {
		/** The Constant IS_HOSPITAL **/
		public static final String IS_HOSPITAL = "hasAuthority(\"" + HOSPITAL + "\")";

		/** The Constant IS_RECIEVER **/
		public static final String IS_RECIEVER = "hasAuthority(\"" + RECIEVER + "\")";

		/** The Constant IS_HOSPITAL_RECIEVER **/
		public static final String IS_HOSPITAL_RECIEVER = "hasAnyAuthority(\"" + HOSPITAL + "\", \"" + RECIEVER + "\")";
	}

}
