package com.brogrammers.projecttrump.gui.entries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.brogrammers.projecttrump.games.TicTacToeGame;
import com.brogrammers.projecttrump.user.User;

public abstract class Entry implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String developer;
	private byte rating;
	private Category category;
	private DefaultMutableTreeNode node;
	protected static ArrayList<Entry> entries = readFromFile();
	private ArrayList<Comment> comments = new ArrayList<>();

	public Entry(String name, String developer, byte rating, Category category) {
		this.name = name;
		this.developer = developer;
		this.rating = rating;
		this.category = category;
		node = new DefaultMutableTreeNode(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public DefaultMutableTreeNode getNode() {
		return node;
	}

	public boolean addComment(User user, String content) {
		if (user != null) {
			comments.add(new Comment(user.getUsername(), content));
			return true;
		}
		return false;
	}

	public int removeComment(User user, int ID) {
		if (User.isModerator(user)) {
			if (comments.remove(ID) != null)
				return 0;
			return 1;
		}
		return -1;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public static ArrayList<Entry> getEntries() {
		return entries;
	}

	public static boolean addEntry(User user, String name, String developer, byte rating, Category category,
			String url) {
		if (User.isAdmin(user)) {
			new WebEntry(name, developer, rating, category, url);
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
	public static int removeEntry(User user, int ID) {
		if (User.isAdmin(user)) {
			Entry e = entries.get(ID);
			if (e != null)
				if (!(e instanceof JavaEntry))
					return 0;
				else
					return 2;
			else
				return 1;
		}
		return -1;
	}

	public static void addDefaultGames() {
		if (entries.isEmpty()) {
			entries.add(new JavaEntry("Tic Tac Toe", "Brogrammers", (byte) 5, Category.GAME, TicTacToeGame.class));
			entries.add(new WebEntry("Google", "Alphabet, Inc.", (byte) 5, Category.UTILITY, "https://google.com"));
			entries.add(new WebEntry("CNN", "CNN", (byte) 5, Category.NEWS, "https://cnn.com"));
			entries.add(new WebEntry("FOX News", "FOX", (byte) 5, Category.NEWS, "https://foxnews.com"));
			entries.add(new WebEntry("Instagram", "Facebook", (byte) 5, Category.SOCIAL, "https://instagram.com"));
			;
			entries.add(new WebEntry("Miami Univ", "MiamiOH", (byte) 5, null, "https://miamioh.edu"));
			entries.add(new WebEntry("Facebook", "Facebook", (byte) 5, Category.SOCIAL, "https://facebook.com"));
			entries.add(
					new WebEntry("Yahoo Finance", "Yahoo", (byte) 5, Category.BUSINESS, "https://finance.yahoo.com"));
		}
	}

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
	}

}
