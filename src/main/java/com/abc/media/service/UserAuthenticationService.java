package com.abc.media.service;

import com.abc.media.entity.User;

public interface UserAuthenticationService {

	
	public User login(String email,String password);
	public String sendEmail(String email);
	public String verifyOtp(String email,String otp);
	public String verifyEmail(String email);

}
