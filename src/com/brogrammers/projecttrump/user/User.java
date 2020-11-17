// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.brogrammers.projecttrump.gui.entries.Entry;

/**
 * Class designed to hold user login and favorite information
 * 
 * @author Nick Perry
 *
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * 
	 * @throws UserAlreadyExistsException Thrown if the user already exists
	 */
	public User(String username, String password) throws UserAlreadyExistsException {
		if (users.containsKey(username))
			throw new UserAlreadyExistsException(username);
		this.username = username;
		this.salt = PasswordHash.getSalt();
		this.passwordHash = PasswordHash.hashPassword(password, this.salt);
		if (users.isEmpty())
			this.rank = Ranks.ADMIN;
		else
			this.rank = Ranks.USER;
		users.put(this.username, this);
	}

	/**
	 * Method to remove a user
	 * 
	 * @param adminUser Admin executing the removal
	 * @param removed   User to be removed
	 * @return If the removal was successful
	 */
	public static boolean removeUser(User adminUser, User removed) {
		if (isAdmin(adminUser)) {
			return users.remove(removed.getUsername()) != null;
		}
		return false;
	}

	/**
	 * The initial Loading method that gets the users on program start. Uses
	 * Deserialization
	 * 
	 * @return HashMap of loaded users
	 */
	@SuppressWarnings("unchecked") // Compiler warning ignored, because if not cast correctly, CastClastException
									// thrown
	private static HashMap<String, User> loadUsersInit() {
		try {
			File file = new File("users");
			if (!file.exists())
				return new HashMap<>();
			HashMap<String, User> users;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			users = (HashMap<String, User>) ois.readObject();
			ois.close();
			fis.close();
			return users;
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
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
		return user != null && (user.rank == Ranks.MODERATOR || user.rank == Ranks.ADMIN);
	}

	/**
	 * Checks if the user has admin privileges
	 * 
	 * @param user User object to check rank
	 * @return Whether or not the user is an admin
	 */
	public static boolean isAdmin(User user) {
		return user != null && user.rank == Ranks.ADMIN;
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
	 * 
	 * @return Whether or not the favorite was added
	 */
	public boolean addFavorite(Entry entry) {
		if (!favorites.contains(entry)) {
			favorites.add(entry);
			return true;
		}
		return false;
	}

	/**
	 * Removes a favorite game or app
	 * 
	 * @param entry Entry object of game / app to remove
	 * 
	 * @return Whether or not the favorite was removed
	 */
	public boolean removeFavorite(Entry entry) {
		return favorites.remove(entry);
	}

	/**
	 * Public getter for username
	 * 
	 * @return Username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Changes the user's password
	 * 
	 * @param username    Username of user to change password
	 * @param oldpassword Current password of user
	 * @param newpassword New Password of user
	 * @return If the password change was successful (a false will indicate invalid
	 *         login)
	 */
	public static boolean changePassword(String username, String oldpassword, String newpassword) {
		User user = login(username, oldpassword);
		if (user == null)
			return false;
		// Generate New Salt, Helps with security.
		user.salt = PasswordHash.getSalt();
		user.passwordHash = PasswordHash.hashPassword(newpassword, user.salt);
		return true;
	}

	/**
	 * Gives the ability to change the rank of a user.
	 * 
	 * @param adminUser        User of the admin that is promoting
	 * @param promotedUser User being promoted
	 * @param rank             New rank of promoted user
	 * @return Status code. 0: success 1: no permission (not an admin user) 2:
	 *         Promoted user does not exist
	 */
	public static int changeRank(User adminUser, User promotedUser, Ranks rank) {
		if (!isAdmin(adminUser))
			return 1;
		if (promotedUser == null)
			return 2;
		promotedUser.rank = rank;
		return 0;
	}

	/**
	 * Returns all the users that are registered
	 * 
	 * @param user Admin user requesting list
	 * @return List of users, or null if user requesting is not admin.
	 */
	public static HashMap<String, User> getAllUsers(User user) {
		if (isAdmin(user)) {
			return users;
		}
		return null;
	}

	public static int getNumUsers(User user) {
		if (isAdmin(user))
			return users.size();
		return -1;
	}

	/**
	 * Serializes and stores the user map into a file.
	 */
	public static void storeToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("users");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public String toString() {
		return username;
	}

	/**
	 * Gets rank of specified user
	 * 
	 * @return Rank
	 */
	public Ranks getRank() {
		// TODO Auto-generated method stub
		return rank;
	}
}
