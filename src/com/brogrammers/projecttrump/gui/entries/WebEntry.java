// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.entries;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.brogrammers.projecttrump.user.User;

/**
 * Entry class for websites
 * 
 * @author Nick Perry
 *
 */
public class WebEntry extends Entry {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * URL of website
	 */
	String url;

	/**
	 * Constructor for new web entry
	 * 
	 * @param name      Name of entry
	 * @param developer Developer of entry
	 * @param rating    Rating of entry
	 * @param category  Category of entry
	 * @param url       URL of entry
	 */
	protected WebEntry(String name, String developer, byte rating, Category category, String url) {
		super(name, developer, rating, category);
		this.url = url;
		entries.add(this);
	}

	/**
	 * Constructor for requests
	 * 
	 * @param user      Requesting user
	 * @param name      Name of requested entry
	 * @param developer Developer of requested entry
	 * @param rating    Rating of requested entry
	 * @param category  Category of requested entry
	 * @param url       URL of requested entry
	 */
	protected WebEntry(User user, String name, String developer, byte rating, Category category, String url) {
		super(name, developer, rating, category);
		this.url = url;
		requests.put(this, user);

	}

	/**
	 * Gets the URL of the web entry
	 * 
	 * @return URL
	 */
	public String getURL() {
		return url;
	}

	public void run() {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
