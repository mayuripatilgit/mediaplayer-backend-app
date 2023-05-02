package com.abc.media.exception;

public class UserAuthenticationFailureException extends RuntimeException{

	public UserAuthenticationFailureException(String msg)
	{
		super(msg);
	}
}
