package com.brogrammers.projecttrump.gui.entries;

import javax.swing.tree.DefaultMutableTreeNode;

public class EntryMutableTreeNode extends DefaultMutableTreeNode {
	private Entry entry;

	public EntryMutableTreeNode(String name, Entry entry) {
		super(name);
		this.entry = entry;
	}
	public Entry getEntry() {
		return entry;
	}
}
