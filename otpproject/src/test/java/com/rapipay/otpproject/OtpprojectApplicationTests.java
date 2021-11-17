package com.rapipay.otpproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rapipay.otpproject.dao.EmailDao;
import com.rapipay.otpproject.entity.Email;
import com.rapipay.otpproject.services.AllServices;
import com.rapipay.otpproject.services.ServicesImpl;

@SpringBootTest
class OtpprojectApplicationTests {

	@Autowired 
	EmailDao email;
	@Test
	public void verifyOTP()
	{
		Email e=new Email();
		
		e.setOtp();
		int a=e.getOtp();
		String s=Integer.toString(a);
		int expected=6;
		int actual=s.length();
		assertEquals(expected, actual);
		
	}
	@Test
	public void checkEmail()
	{
		Email e =new Email();
		e.setEmail("irasheikh21@gmail.com");
		e.setOtp();
		e.setExpiryTime();
		e.setGeneratedTime();
		email.save(e);
		assertNotNull(e, "saves object!!");
		
		
	}
	
	@Test
	public void addEmail()
	{
		Email mail=new Email();
		mail.setEmail("navyaagrawal23@gmail.com");
		mail.setExpiryTime();
		mail.setGeneratedTime();
		mail.setOtp();
		email.save(mail);
		assertNotNull(mail,"saved");
	}
	
	
	
	


}
