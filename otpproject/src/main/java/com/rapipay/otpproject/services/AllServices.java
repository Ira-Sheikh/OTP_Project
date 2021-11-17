package com.rapipay.otpproject.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rapipay.otpproject.dao.EmailOtp;
import com.rapipay.otpproject.entity.Email;
import com.rapipay.otpproject.exception.EmailNotFoundException;
import com.rapipay.otpproject.exception.InvalidOtpException;
import com.rapipay.otpproject.exception.TimeOutException;

public interface AllServices {
	
	 List<Email> getAllEmail();
	
	 Email getByEmail(String email) ;
	 

		ResponseEntity<HttpStatus> addEmail(Email email) ;

		String OtpValidate(EmailOtp eo) throws InvalidOtpException, EmailNotFoundException,TimeOutException;
	 
	 
	 
	 
	
	

}

