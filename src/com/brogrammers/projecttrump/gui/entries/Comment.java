// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.entries;

import java.io.Serializable;

/**
 * Class that holds information about comments.
 * 
 * @author Nick Perry
 *
 */
public class Comment implements Serializable {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Username of commenter
	 */
	private String username;
	/**
	 * Content of comment
	 */
	private String content;

	/**
	 * Constructor for comment
	 * 
	 * @param username Username of commenter
	 * @param content  Content of comment
	 */
	public Comment(String username, String content) {
		this.username = username;
		this.content = content;
	}

	/**
	 * Gets commenter
	 * 
	 * @return Username of commenter
	 */
	public String getUser() {
		return username;
	}

	/**
	 * Gets content of comment
	 * 
	 * @return Comment content
	 */
	public String getContent() {
		return content;
	}
}
