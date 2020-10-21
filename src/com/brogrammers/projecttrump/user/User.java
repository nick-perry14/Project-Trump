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
	 * Checks if the user has moderator privileges
	 * 
	 * @param user User object to check rank
	 * @return Whether or not the user is a moderator
	 */
	public static boolean isModerator(User user) {
		if (user.rank == Ranks.Moderator || user.rank == Ranks.Admin)
			return true;
		return false;
	}
	
	/**
	 * Checks if the user has admin privileges
	 * 
	 * @param user User object to check rank
	 * @return Whether or not the user is an admin
	 */
	public static boolean isAdmin(User user) {
		if (user.rank == Ranks.Admin)
			return true;
		return false;
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
