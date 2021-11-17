package com.rapipay.otpproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rapipay.otpproject.dao.EmailOtp;
import com.rapipay.otpproject.entity.Email;
import com.rapipay.otpproject.exception.EmailNotFoundException;
import com.rapipay.otpproject.exception.InvalidOtpException;
import com.rapipay.otpproject.exception.TimeOutException;
import com.rapipay.otpproject.services.AllServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class OTPController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OTPController.class);
	Logger logger=LoggerFactory.getLogger(OTPController.class);
	@Autowired
	  private JavaMailSender javaMailSender;
	
	
	
	@Autowired
	private AllServices allservices;

	
	@CrossOrigin
	@GetMapping("/email")
	public List<Email> getAllEmail(){
		LOGGER.info("for informational purpose");
		return allservices.getAllEmail();
	}
	
	@CrossOrigin
	@GetMapping("/email/{email}")
	public Email getByEmail(@PathVariable String email ) {
		LOGGER.info("for informational purpose");
		return allservices.getByEmail(email);
		
	}

	@CrossOrigin
	@PostMapping("/email")
	public ResponseEntity<HttpStatus> addEmail(@RequestBody Email email ) {
		LOGGER.info("for informational purpose");
		email.setGeneratedTime();
		email.setExpiryTime();
		email.setOtp();
		return this.allservices.addEmail(email);
	}
	
	@CrossOrigin
	@PostMapping("/otp-validate")
	public String otpValidate(@RequestBody EmailOtp eo) 
	{  LOGGER.info("for informational purpose");
	try {
		return allservices.OtpValidate(eo);
	}
	catch(InvalidOtpException e)
	{
		return e.toString();
	} catch (EmailNotFoundException e) {
		// TODO Auto-generated catch block
	return e.toString();
	} catch (TimeOutException e) {
		// TODO Auto-generated catch block
	e.toString();
	}
	return null;
	
	}
}
