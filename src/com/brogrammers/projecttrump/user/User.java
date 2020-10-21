// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.user;

import com.brogrammers.projecttrump.gui.Entry;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class designed to hold user login and favorite information
 * @author Nick Perry
 *
 */
public class User {
	/**
	 * Static Hashmap that contains all the users that are registered.
	 */
	private static HashMap<String, User> users = loadUsersInit();
	/**
	 * The password hash of the user
	 */
	private String passwordHash;
	/**
	 * The salt of the user
	 */
	private byte[] salt;
	/**
	 * Username of the user
	 */
	private String username;
	/**
	 * Rank of the user
	 */
	private Ranks rank;
	/**
	 * Favorite apps of the user
	 */
	public ArrayList<Entry> favorites = new ArrayList<>();

	/**
	 * User Creation Constructor
	 * 
	 * @param username Username of the new user
	 * @param password Password of the new user
	 */
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
	 * The initial Loading method that gets the users on program start.
	 * 
	 * @return HashMap of loaded users
	 */
	private static HashMap<String, User> loadUsersInit() {
		HashMap<String, User> users = new HashMap<>();
		return users;
	}

	/**
	 * Checks if the user has moderator privileges
	 * 
	 * @param user User object to check rank
	 * @return Whether or not the user is a moderator
	 */
	public static boolean isModerator(User user) {
		return user.rank == Ranks.Moderator || user.rank == Ranks.Admin;
	}

	/**
	 * Checks if the user has admin privileges
	 * 
	 * @param user User object to check rank
	 * @return Whether or not the user is an admin
	 */
	public static boolean isAdmin(User user) {
		return user.rank == Ranks.Admin;
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
