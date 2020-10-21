// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.brogrammers.projecttrump.gui.Entry;

/**
 * Class designed to hold user login and favorite information
 * 
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
	 * The initial Loading method that gets the users on program start.  Uses Deserialization
	 * 
	 * @return HashMap of loaded users
	 */
	@SuppressWarnings("unchecked") // Compiler warning ignored, because if not cast correctly, CastClastException
									// thrown
	private static HashMap<String, User> loadUsersInit() {
		try {
			File file = new File("users.txt");
			if (!file.exists())
				return new HashMap<>();
			HashMap<String, User> users;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			users = (HashMap<String, User>) ois.readObject();
			ois.close();
			fis.close();
			return users;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new HashMap<>();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return new HashMap<>();
		} catch (ClassCastException e) {
			return new HashMap<>();
		}
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

	/**
	 * Serializes and stores the user map into a file.
	 */
	public static void storeToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("users.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
