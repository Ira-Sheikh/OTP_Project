package com.rapipay.otpproject.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rapipay.otpproject.entity.Email;
import com.rapipay.otpproject.exception.EmailNotFoundException;
import com.rapipay.otpproject.exception.InvalidOtpException;
import com.rapipay.otpproject.exception.TimeOutException;
import com.rapipay.otpproject.dao.EmailDao;
import com.rapipay.otpproject.dao.EmailOtp;



@Service
public class ServicesImpl implements AllServices{
	
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Autowired
	private EmailDao emaildao;

	@Override
	public List<Email> getAllEmail() {
		
		return emaildao.findAll();
	}

	@Override
	public Email getByEmail(String email) {
		
		return emaildao.findById(email).orElse(null);
	}

	@Override
	public ResponseEntity<HttpStatus> addEmail(Email email) {
		Email x =  emaildao.save(email);
		sendEmail(email.getEmail() , email.getOtp());
		if(x!=null)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
		
		void sendEmail(String email  , int otp) 
		{
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(email);
	        msg.setSubject("Testing from Spring Boot");
	        msg.setText(otp + "expires in 10 minutes ");
	        javaMailSender.send(msg);
	    }
		
		
		@Override
		public String  OtpValidate(EmailOtp eo) throws InvalidOtpException,EmailNotFoundException,TimeOutException {
			
			Email emailData = getByEmail(eo.getEmail());
			if(emailData == null) 
				{
						throw new EmailNotFoundException();	
				}
			else 
			{
				if(emailData.getOtp()==(eo.getOtp()))
				{
					LocalDateTime start = emailData.getGeneratedTime();
					LocalDateTime end = emailData.getExpiryTime();
					LocalDateTime curr = LocalDateTime.now();
					System.out.println(ChronoUnit.MINUTES.between(start , curr));
					System.out.println(ChronoUnit.MINUTES.between(curr , end));
					if(ChronoUnit.MINUTES.between(start , curr) <= 10 && ChronoUnit.MINUTES.between(curr , end) >= 0)
						{
								return "Valid otp";
						}
					else {
					throw new TimeOutException();
					}
				}
				else
				{
				throw new InvalidOtpException();
				}
			}
		}
}



//	void sendEmail() {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("to_1@gmail.com", "to_2@gmail.com", "to_3@yahoo.com");
//
//        msg.setSubject("Testing from Spring Boot");
//        msg.setText("Hello World \n Spring Boot Email");
//
//        javaMailSender.send(msg);
//	}

	
	

