package com.brogrammers.projecttrump.user;

import com.brogrammers.projecttrump.gui.Entry;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
	private static HashMap<String, User> users = new HashMap<>();
	private String passwordHash;
	private byte[] salt;
	private String username;
	private Ranks rank;
	public ArrayList<Entry> favorites = new ArrayList<>();

	public User(String username, String password) {
		this.username = username;
		this.salt = PasswordHash.getSalt();
		this.passwordHash = PasswordHash.hashPassword(password, this.salt);
		this.rank = Ranks.User;
		users.put(this.username, this);
	}

	/**
	 * This will refresh the user HashMap from the file.
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

	/**
	 * Private getter for the login method, that allows the static method to
	 * retrieve the password hash.
	 * 
	 * @return Password hash
	 */
	private String getPassHash() {
		return passwordHash;
	}

	/**
	 * Private getter for the login method, that allows the static method to retrive
	 * the salt
	 * 
	 * @return Salt
	 */
	private byte[] getSalt() {
		return salt;
	}

	/**
	 * Adds a favorite game or app
	 * 
	 * @param entry Entry object of game / app to add
	 */
	public void addFavorite(Entry entry) {
		favorites.add(entry);
	}

	/**
	 * Removes a favorite game or app
	 * 
	 * @param entry Entry object of game / app to remove
	 */
	public void remvoeFavorite(Entry entry) {
		favorites.remove(entry);
	}
}