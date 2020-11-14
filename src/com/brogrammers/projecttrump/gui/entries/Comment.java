package com.brogrammers.projecttrump.gui.entries;

import java.io.Serializable;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String content;

	public Comment(String username, String content) {
		this.username = username;
		this.content = content;
	}

	public String getUser() {
		return username;
	}

	public String getContent() {
		return content;
	}
}
