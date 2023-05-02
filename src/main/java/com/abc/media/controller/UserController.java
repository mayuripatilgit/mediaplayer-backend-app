package com.abc.media.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.abc.media.dto.UserDto;
import com.abc.media.entity.User;
import com.abc.media.exception.InvalidInputException;
import com.abc.media.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController 
{
    @Autowired
	UserService userService;
    
   
	@PostMapping("/user/save")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userdto)
	{
		UserDto newUserDto = userService.addUser(userdto);
		ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(newUserDto,HttpStatus.CREATED);
		return responseEntity;
		
	}
	
	@PutMapping("/forgotpassword/resetpassword")
	public String gorgotPassword(@RequestParam String email,@RequestParam String newPassword ,@RequestParam String confirmPassword) throws InvalidInputException {
		return userService.resetForgotPassword(email,newPassword,confirmPassword);
    }
	
	
	@PutMapping("/resetpassword")
	public String resetPassword(@RequestParam String email,@RequestParam String password ,@Valid @RequestParam String newPassword) throws InvalidInputException {
		return userService.resetPassword(email,password,newPassword);
    }
	
	
	@GetMapping("/user/id/{id}")
	public ResponseEntity<User> fetchUserDetails(@PathVariable("id") int userId)
	{
		User user = userService.getUserById(userId);
		ResponseEntity<User> responseEntity =new ResponseEntity<>(user,HttpStatus.OK);
		return responseEntity;
	}
	
}
