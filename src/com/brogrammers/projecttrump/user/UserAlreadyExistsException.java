// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.user;

/**
 * Exception that is thrown when a user already exists, to be caught by Login
 * 
 * @author Nick Perry
 *
 */
public class UserAlreadyExistsException extends Exception {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception constructor
	 * 
	 * @param Username Username of user that already exists.
	 */
	public UserAlreadyExistsException(String Username) {
		super("User Already Exists: " + Username);
	}
}
