package com.brogrammers.projecttrump.gui.entries;

import javax.swing.JFrame;

public class JavaEntry extends Entry {
	private static final long serialVersionUID = 1L;
	Class<?> type;

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
