package com.abc.media.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.media.dto.UserDto;
import com.abc.media.entity.User;
import com.abc.media.exception.InvalidInputException;
import com.abc.media.exception.UserNotFoundException;
import com.abc.media.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public UserDto addUser(UserDto userdto) {
		

		User user = new User();
		
		user.setFirstName(userdto.getFirstName());
		user.setLastName(userdto.getLastName());
		user.setDateOfBirth(userdto.getDateOfBirth());
		user.setGender(userdto.getGender());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setUsername(userdto.getUsername());
		
		User newUser = userRepository.save(user);
		UserDto newDto = new UserDto();
		
		newDto.setFirstName(newUser.getFirstName());
		newDto.setLastName(newUser.getLastName());
		newDto.setDateOfBirth(newUser.getDateOfBirth());
		newDto.setGender(newUser.getGender());
		newDto.setEmail(newUser.getEmail());
		newDto.setUsername(newUser.getUsername());
		newDto.setPassword(newUser.getPassword());
		return newDto;
	
	}
	
	@Override
	public String resetPassword(String email, String password, String newPassword) throws InvalidInputException {
		Optional<User> loginObj= userRepository.findByEmail(email);
		String s="";
		if(loginObj.isPresent()) {
			if( loginObj.get().getPassword().equals(password)) {
				User users = loginObj.get();
				users.setPassword(newPassword);
				userRepository.save(users);
				return "Password reset successfully";
			}
			else {
				s="enter valid password";
				}
			}
		else {
			s="Password reset successfully";
			}
		throw new InvalidInputException(s);
        }


	@Override
	public String resetForgotPassword(String email, String newPassword, String confirmPassword) throws InvalidInputException {
		Optional<User> loginObj= userRepository.findByEmail(email);
		String s="";
		if(loginObj.isPresent()) {
			if(newPassword.equals(confirmPassword)) {
				User users = loginObj.get();
				users.setPassword(newPassword);
				userRepository.save(users);
				return "Password reset successfully";
			}
			else {
				s="password not match";
				}
			}
		else {
			s="enter valid email";
			}
		throw new InvalidInputException(s);
	}
	
	
	@Override
	public User getUserById(long userId) throws UserNotFoundException
	{
		Optional<User> optionalUser =userRepository.findById(userId);
		if(optionalUser.isEmpty())
		{
			throw new UserNotFoundException("User Not Found with id:"+userId);
		}
		
		User user= optionalUser.get();
		return user;
	}

}
