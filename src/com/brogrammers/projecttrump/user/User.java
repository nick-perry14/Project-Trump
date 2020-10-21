package user;

import java.util.HashMap;

public class User {
	private static HashMap<String, User> users = new HashMap<>();
	private String passwordHash;
	private byte[] salt;
	private String username;
	private Ranks rank;

	public User(String username, String password) {
		this.username = username;
		this.salt = PasswordHash.getSalt();
		this.passwordHash = PasswordHash.hashPassword(password, this.salt);
		this.rank = Ranks.User;
		users.put(username, this);
	}

	/**
	 * This will refresh the user ArrayList from the file.
	 */
	public void loadUsers() {

	}

	/**
	 * Login Method
	 * 
	 * @param username Username Given
	 * @param password Password Given
	 * @return User Object (or null if invalid login)
	 */
	public static User login(String username, String password) {
		User user = users.get(username);
		if (user == null)
			return null;
		if (PasswordHash.hashPassword(password, user.getSalt()).equals(user.getPassHash()))
			return user;
		return null;
	}

	private String getPassHash() {
		return passwordHash;
	}

	private byte[] getSalt() {
		return salt;
	}

}
