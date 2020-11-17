// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.brogrammers.projecttrump.gui.GUI;
import com.brogrammers.projecttrump.gui.entries.Category;
import com.brogrammers.projecttrump.gui.entries.Entry;
import com.brogrammers.projecttrump.gui.entries.EntryMutableTreeNode;
import com.brogrammers.projecttrump.user.User;

/**
 * Admin Panel Remove Entry Window
 * 
 * @author Nick Perry
 *
 */
public class RemEntryWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Create Frame
	 * 
	 * @param admin Admin User
	 * @param gui   Gui of Admin User
	 */
	public RemEntryWindow(User admin, GUI gui) {
		if (!User.isAdmin(admin))
			dispose();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Remove Entry");
		setResizable(false);
		setMinimumSize(new Dimension(260, 559));
		setMaximumSize(new Dimension(260, 559));
		JLabel userListLabel = new JLabel("Entry List");
		userListLabel.setBounds(40, 11, 164, 14);
		getContentPane().add(userListLabel);
		JTree tree = new JTree();
		tree.setScrollsOnExpand(true);
		tree.setSelectionRows(new int[] { 1 });
		tree.setSelectionRow(1);
		tree.setForeground(Color.GRAY);
		tree.setFont(new Font("Bahnschrift", Font.BOLD, 11));
		tree.setBorder(null);
		tree.setBackground((Color.white));
		tree.setBounds(10, 58, 294, 260);
		DefaultMutableTreeNode headNode = new DefaultMutableTreeNode("Applications");
		DefaultTreeModel treeModel = new DefaultTreeModel(headNode);
		DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode("Games");
		DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("Utilities");
		DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode("Social Media");
		DefaultMutableTreeNode node_4 = new DefaultMutableTreeNode("Business");
		DefaultMutableTreeNode node_5 = new DefaultMutableTreeNode("News");
		DefaultMutableTreeNode node_6 = new DefaultMutableTreeNode("Uncategorized");
		for (Entry x : Entry.getEntries()) {
			Category cat = x.getCategory();
			if (cat == null) {
				node_6.add(new EntryMutableTreeNode(x.getName(), x));
				continue;
			}
			switch (cat) {
			case GAME:
				node_1.add(new EntryMutableTreeNode(x.getName(), x));
				break;
			case UTILITY:
				node_2.add(new EntryMutableTreeNode(x.getName(), x));
				break;
			case SOCIAL:
				node_3.add(new EntryMutableTreeNode(x.getName(), x));
				break;
			case BUSINESS:
				node_4.add(new EntryMutableTreeNode(x.getName(), x));
				break;
			case NEWS:
				node_5.add(new EntryMutableTreeNode(x.getName(), x));
				break;
			}

		}
		if (!node_1.isLeaf())
			headNode.add(node_1);
		if (!node_2.isLeaf())
			headNode.add(node_2);
		if (!node_3.isLeaf())
			headNode.add(node_3);
		if (!node_4.isLeaf())
			headNode.add(node_4);
		if (!node_5.isLeaf())
			headNode.add(node_5);
		if (!node_6.isLeaf())
			headNode.add(node_6);
		tree.setModel(treeModel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 25, 164, 408);
		scrollPane.setViewportView(tree);
		getContentPane().add(scrollPane);
		getContentPane().setSize(400, 400);
		getContentPane().setLayout(null);

		JButton submitButton = new JButton("Remove Entry");
		submitButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		submitButton.setBounds(65, 444, 111, 23);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tree.getLastSelectedPathComponent() == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Select an entry!", "No Entry Selected!",
							JOptionPane.ERROR_MESSAGE);
					return;

				}
				if (tree.getLastSelectedPathComponent() instanceof EntryMutableTreeNode) {
					EntryMutableTreeNode n = (EntryMutableTreeNode) tree.getLastSelectedPathComponent();
					int opt = JOptionPane.showConfirmDialog(getContentPane(),
							"Are you sure you want to remove " + n.toString() + "?", "Confirm Removal",
							JOptionPane.YES_NO_OPTION);
					if (opt == 0) {
						int code = Entry.removeEntry(admin, n.getEntry());
						switch (code) {
						case 0:
							JOptionPane.showMessageDialog(getContentPane(),
									"Removed " + n.getEntry().getName() + " successfully!", "Remove Success!",
									JOptionPane.INFORMATION_MESSAGE);
							gui.refreshEntries();
							dispose();
							break;
						case 1:
							JOptionPane.showMessageDialog(getContentPane(), "This entry does not exist! ",
									"Remove Fail!", JOptionPane.ERROR_MESSAGE);
							break;
						case 2:
							JOptionPane.showMessageDialog(getContentPane(),
									"This entry cannot be removed, it is protected! ", "Remove Fail!",
									JOptionPane.ERROR_MESSAGE);
							break;
						}

					}

				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							tree.getLastSelectedPathComponent().toString()
									+ " is a folder!  You cannot remove folders!",
							"Error: Folder Selected!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		getContentPane().add(submitButton);
		getContentPane().setVisible(true);

	}
}
