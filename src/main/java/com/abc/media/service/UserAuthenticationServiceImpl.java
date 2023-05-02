package com.abc.media.service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.abc.media.entity.User;
import com.abc.media.entity.Verification;
import com.abc.media.exception.UserEmailNotExistingException;
import com.abc.media.exception.UserAuthenticationFailureException;
import com.abc.media.repository.UserRepository;
import com.abc.media.repository.VerificationRepository;


@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationRepository verificationRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public User login(String email, String password) {
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if(optionalUser.isEmpty())
		{
			throw new UserEmailNotExistingException("User not registered");
		}
		
		User user = optionalUser.get();
		if(!password.equals(user.getPassword()))
		{
			throw new UserAuthenticationFailureException("Login failed");
		}
		// TODO Auto-generated method stub
		return user;
	}
	
	
	@Override
	public String sendEmail(String email) {
		
		Optional<User> user= userRepository.findByEmail(email);
		if(user.isPresent())
		{
			  SimpleMailMessage message = new SimpleMailMessage();		
			    message.setTo(email);
	            message.setSubject("Email Verification for  Application");
			    String code = RandomStringUtils.randomNumeric(4);
	            message.setText(code);
	            javaMailSender.send(message);
		        Verification verification=new Verification();
		      
		        verification.setUserName(email);
		        verification.setOtp(code);
		        verificationRepository.save(verification);
		        return "mail sent successfully";

	    	}
	    	else {
	    		throw new UserAuthenticationFailureException ("enter Valid Email");
	    		}
			
		  }


	@Override
	public String verifyOtp(String email, String otp) {
		
		
		
		Optional<Verification> obj= verificationRepository.findByUserName(email);
				if(obj.isPresent() ) {
					Verification obj2=obj.get();
    	   if(obj2.getOtp().equals(otp)) {
    	   verificationRepository.delete(obj2);
    	   return "otp matched";
    	   }
    	   else {
    		   throw new UserEmailNotExistingException("enter valid otp");
    	   }
    			
        }
    	else {
    		 throw new UserEmailNotExistingException("Otp did not matched");
    	}	
		
		
	
	}

	@Override
	public String verifyEmail(String email) {
		Optional<User> user=userRepository.findByEmail(email);
		if(user.isEmpty())
		{
			return "user not registered";
		}
		else
		{
			return null ; //"user already register"
		}
	}	

}
