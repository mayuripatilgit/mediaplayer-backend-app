package com.abc.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.media.dto.LoginReq;
import com.abc.media.entity.User;
import com.abc.media.service.UserAuthenticationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationService userAuthenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<User> doLogin(@RequestBody LoginReq loginReq)
	{
		User user = userAuthenticationService.login(loginReq.getEmail(),loginReq.getPassword());
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user,HttpStatus.OK);
		return responseEntity;
		
	}
	

	@PostMapping("/sendEmail/{toEmail}")
	public String sendEmail(@PathVariable("toEmail") String toEmail) {
		return userAuthenticationService.sendEmail(toEmail);
	}
	
	@DeleteMapping("/verify/{email}/{otp}")
	public String Verifyotp(@PathVariable("email")String email,@PathVariable("otp") String otp)
	{
		return userAuthenticationService.verifyOtp(email, otp);
		
	}
	
	@GetMapping("/verifyEmail/{email}")
	public String getVerifyEmailId(@PathVariable("email") String email)
	{
       String msg =userAuthenticationService.verifyEmail(email);
		
		return msg;
	}
	

}
