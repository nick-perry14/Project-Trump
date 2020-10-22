package com.brogrammers.projecttrump.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Entry implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String developer;
	private byte rating;
	private String category;
	public static ArrayList<Entry> entries = readFromFile();

	public Entry(String name, String developer, byte rating, String category) {
		this.name = name;
		this.developer = developer;
		this.rating = rating;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Deserializes array list of current entries.
	 * 
	 * @return
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
