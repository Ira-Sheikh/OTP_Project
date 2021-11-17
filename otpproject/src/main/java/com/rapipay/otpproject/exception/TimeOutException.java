package com.rapipay.otpproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class TimeOutException extends Exception {

	@Override
	public String toString() {
		return "TIME OUT";
	}
	

}
