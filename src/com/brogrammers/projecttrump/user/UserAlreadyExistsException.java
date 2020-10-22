package com.brogrammers.projecttrump.user;

public class UserAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String Username) {
		super("User Already Exists: " + Username);
	}
}
