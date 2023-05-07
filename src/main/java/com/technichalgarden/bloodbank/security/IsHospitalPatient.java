package com.technichalgarden.bloodbank.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize(BbankRoleConstants.CommonRoles.IS_HOSPITAL_RECIEVER)
public @interface IsHospitalPatient {

}
