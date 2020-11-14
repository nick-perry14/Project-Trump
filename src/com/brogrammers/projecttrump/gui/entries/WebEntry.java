package com.brogrammers.projecttrump.gui.entries;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.brogrammers.projecttrump.user.Ranks;
import com.brogrammers.projecttrump.user.User;

public class WebEntry extends Entry {
	private static final long serialVersionUID = 1L;
	String url;

	protected WebEntry(String name, String developer, byte rating, Category category, String url) {
		super(name, developer, rating, category);
		this.url = url;
		entries.add(this);
	}

	protected WebEntry(User user, String name, String developer, byte rating, Category category, String url) {
		super(name, developer, rating, category);
		this.url = url;
		if(User.isAdmin(user)) 
			entries.add(this);
			
		else 
			requests.add(this);
			
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
