package com.brogrammers.projecttrump.gui.entries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.brogrammers.projecttrump.games.TicTacToeGame;
import com.brogrammers.projecttrump.user.User;

/**
 * Abstract class that contains information about entries in the repository.
 * 
 * @author Nick Perry
 *
 */
public abstract class Entry implements Serializable {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Name of entry
	 */
	private String name;
	/**
	 * Developer of entry
	 */
	private String developer;
	/**
	 * Rating of entry
	 */
	private byte rating;
	/**
	 * Category of entry
	 */
	private Category category;
	/**
	 * List of all current entries
	 */
	protected static ArrayList<Entry> entries = readFromFile();
	/**
	 * 
	 */
	protected static HashMap<WebEntry, User> requests = readReqFromFile();
	private ArrayList<Comment> comments = new ArrayList<>();

	/**
	 * Protected constructor
	 * 
	 * @param name      Name of entry
	 * @param developer Developer of entry
	 * @param rating    Rating of entry
	 * @param category  Category of entry
	 */
	protected Entry(String name, String developer, byte rating, Category category) {
		this.name = name;
		this.developer = developer;
		this.rating = rating;
		this.category = category;
	}

	/**
	 * Gets the name of the entry
	 * 
	 * @return Name of entry
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of the developer
	 * 
	 * @param New Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the developer of the entry
	 * 
	 * @return Developer of entry
	 */
	public String getDeveloper() {
		return developer;
	}

	/**
	 * Changes the developer of the entry
	 * 
	 * @param New developer
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	/**
	 * Gets the rating of the entry
	 * 
	 * @return Rating of entry
	 */
	public byte getRating() {
		return rating;
	}

	/**
	 * Changes the rating of the entry
	 * 
	 * @param rating New rating
	 */
	public void setRating(byte rating) {
		this.rating = rating;
	}

	/**
	 * Gets the category of the entry
	 * 
	 * @return Category of entry
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Changes the category of the entry
	 * 
	 * @param category New category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Adds a comment to the entry
	 * 
	 * @param user    Commenter
	 * @param content Comment content
	 * @return
	 */
	public boolean addComment(User user, String content) {
		if (user != null) {
			comments.add(new Comment(user.getUsername(), content));
			return true;
		}
		return false;
	}

	/**
	 * Removes the specified comment
	 * 
	 * @param user Moderator executing the delete
	 * @param ID   ID of comment
	 * @return Status code: -1 = no permission, 0 = success, 1 = comment not found
	 */
	public int removeComment(User user, int ID) {
		if (User.isModerator(user)) {
			if (comments.remove(ID) != null)
				return 0;
			return 1;
		}
		return -1;
	}

	/**
	 * Gets the list of comments for the entry
	 * 
	 * @return List of comments
	 */
	public ArrayList<Comment> getComments() {
		return comments;
	}

	/**
	 * Gets the list of current entries
	 * 
	 * @return List of entries
	 */
	public static ArrayList<Entry> getEntries() {
		return entries;
	}

	/**
	 * Gives the list of active requests
	 * 
	 * @param admin Admin requesting to view list
	 * @return List, or null if user is not admin.
	 */
	public static HashMap<WebEntry, User> getRequests(User admin) {
		if (User.isAdmin(admin))
			return requests;
		return null;
	}

	/**
	 * Creates a new entry
	 * 
	 * @param user      Admin user executing command
	 * @param name      Name of new entry
	 * @param developer Developer of new entry
	 * @param rating    Rating of new entry (5= AO, 4 = E M, 3 = T 2 = E 10+, 1 = E)
	 * @param category  Category of new entry
	 * @param url       URL of new entry
	 * @return Whether or not the add was successful
	 */
	public static boolean addEntry(User user, String name, String developer, byte rating, Category category,
			String url) {
		if (User.isAdmin(user)) {
			new WebEntry(name, developer, rating, category, url);
		}
		return false;
	}

	/**
	 * Creates a new request
	 * 
	 * @param user      Requesting User
	 * @param name      Name of request
	 * @param developer Developer of request
	 * @param rating    Rating of request
	 * @param category  Category of request
	 * @param url       URL of request
	 */
	public static void addRequest(User user, String name, String developer, byte rating, Category category,
			String url) {
		new WebEntry(user, name, developer, rating, category, url);
	}

	/**
	 * Approves the specified request
	 * 
	 * @param entry entry of request
	 * @param user  Requester
	 * @return Whether or not the approval was successful
	 */
	public static boolean approveRequest(WebEntry entry, User user) {
		if (User.isAdmin(user)) {
			User rem = requests.remove(entry);
			if (rem != null) {
				entries.add(entry);
				return true;
			}
		}
		return false;
	}

	/**
	 * Rejects the specified request
	 * 
	 * @param ID   entry of request
	 * @param user Requester
	 * @return Whether or not the rejection was successful
	 */
	public static boolean rejectRequest(WebEntry entry, User user) {
		if (User.isAdmin(user)) {
			User rem = requests.remove(entry);
			return rem != null;
		}
		return false;
	}

	/**
	 * Removes an entry from the list
	 * 
	 * @param user Admin user removing the entry
	 * @param ID   ID of Entry
	 * @return Status Code: -1 = No permission, 0 = Success, 1 = Entry does not
	 *         exist, 2 = Entry Protected
	 */
	public static int removeEntry(User user, Entry entry) {
		if (User.isAdmin(user)) {
			if (entry instanceof JavaEntry)
				return 2;
			if (entries.remove(entry))
				return 0;
			return 1;
		}
		return -1;
	}

	/**
	 * Adds default game to entry list if the list is empty
	 */
	public static void addDefaultGames() {
		if (entries.isEmpty()) {
			new JavaEntry("Tic Tac Toe", "Brogrammers", (byte) 1, Category.GAME, TicTacToeGame.class);
			new WebEntry("Google", "Alphabet, Inc.", (byte) 1, Category.UTILITY, "https://google.com");
			new WebEntry("CNN", "CNN", (byte) 1, Category.NEWS, "https://cnn.com");
			new WebEntry("FOX News", "FOX", (byte) 1, Category.NEWS, "https://foxnews.com");
			new WebEntry("Instagram", "Facebook", (byte) 2, Category.SOCIAL, "https://instagram.com");
			new WebEntry("Miami Univ", "MiamiOH", (byte) 1, null, "https://miamioh.edu");
			new WebEntry("Facebook", "Facebook", (byte) 2, Category.SOCIAL, "https://facebook.com");
			new WebEntry("Yahoo Finance", "Yahoo", (byte) 1, Category.BUSINESS, "https://finance.yahoo.com");
		}
	}

	/**
	 * Run method that runs the specified entry.
	 */
	public abstract void run();

	/**
	 * Deserializes array list of current entries.
	 * 
	 * @return Array List Containing all entries
	 */
	@SuppressWarnings("unchecked")
	private static ArrayList<Entry> readFromFile() {
		try {
			File file = new File("entries");
			if (!file.exists())
				return new ArrayList<>();
			ArrayList<Entry> entries;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			entries = (ArrayList<Entry>) ois.readObject();
			ois.close();
			fis.close();
			return entries;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new ArrayList<>();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return new ArrayList<>();
		} catch (ClassCastException e) {
			return new ArrayList<>();
		}
	}

	/**
	 * Reads requests using serialization
	 * 
	 * @return Array list containing requests.
	 */
	@SuppressWarnings("unchecked")
	private static HashMap<WebEntry, User> readReqFromFile() {
		try {
			File file = new File("requests");
			if (!file.exists())
				return new HashMap<>();
			HashMap<WebEntry, User> reqs;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			reqs = (HashMap<WebEntry, User>) ois.readObject();
			ois.close();
			fis.close();
			return reqs;
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
	 * Stores entries to file using serialization.
	 */
	public static void storeToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("entries");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(entries);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		storereqsToFile();
	}

	/**
	 * Stores requests using serialization
	 */
	private static void storereqsToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("requests");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(requests);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public String toString() {
		return name;
	}

}
