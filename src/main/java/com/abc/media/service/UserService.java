package com.abc.media.service;
import com.abc.media.dto.UserDto;
import com.abc.media.entity.User;
import com.abc.media.exception.InvalidInputException;
import com.abc.media.exception.UserNotFoundException;


public interface UserService {

  

	public UserDto addUser(UserDto userdto);
	
	String resetPassword(String email, String password, String newPassword) throws InvalidInputException;


	String resetForgotPassword(String email, String newPassword, String confirmPassword) throws InvalidInputException;

	

	User getUserById(long userId) throws UserNotFoundException;

}


