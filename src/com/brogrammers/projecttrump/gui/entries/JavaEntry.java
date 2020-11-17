// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.entries;

import javax.swing.JFrame;

/**
 * Entry class that associated a java gui
 * 
 * @author Nick Perry
 *
 */
public class JavaEntry extends Entry {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference to class type of the entry
	 */
	Class<?> type;

	/**
	 * Constructor for JavaEntry
	 * 
	 * @param name      Name of entry
	 * @param developer Developer of entry
	 * @param rating    Rating of entry
	 * @param category  Category of entry
	 * @param type      Type of class entry is
	 */
	protected JavaEntry(String name, String developer, byte rating, Category category, Class<?> type) {
		super(name, developer, rating, category);
		this.type = type;
		entries.add(this);
	}

	@SuppressWarnings("deprecation")
	public void run() {
		try {
			Object game = type.newInstance();
			if (game instanceof JFrame) {
				((JFrame) game).setVisible(true);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
