// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.entries;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Helper class that allows DefaultMutableTreeNodes to hold entries
 * 
 * @author Nick Perry
 *
 */
public class EntryMutableTreeNode extends DefaultMutableTreeNode {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Entry that the node corresponds to
	 */
	private Entry entry;

	/**
	 * Constructor for Node
	 * 
	 * @param name  Name of node
	 * @param entry Entry associated
	 */
	public EntryMutableTreeNode(String name, Entry entry) {
		super(name);
		this.entry = entry;
	}

	/**
	 * Gets the associated entry
	 * 
	 * @return Associated entry
	 */
	public Entry getEntry() {
		return entry;
	}
}
