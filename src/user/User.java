package user;

import java.util.ArrayList;

public class User {
	private static ArrayList<User> users;
	private String passwordHash;
	private String username;
	private Ranks rank;
	
	public User(String username, String password) {
		
	}

	/**
	 * This will refresh the user ArrayList from the file.
	 */
	public void loadUsers() {

	}
	
	
}
