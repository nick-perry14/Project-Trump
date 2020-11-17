// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.brogrammers.projecttrump.user.User;
import com.brogrammers.projecttrump.gui.GUI;

/**
 * Admin Panel
 * 
 * @author Nick Perry
 *
 */
public class AdminPanel extends JFrame {
	private static final long serialVersionUID = 1L;

	public AdminPanel(User user, GUI gui) {
		setMaximumSize(new Dimension(337, 156));
		setMinimumSize(new Dimension(337, 156));
		setResizable(false);
		if (!User.isAdmin(user))
			dispose();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Admin Mode");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton checkRequests = new JButton("Moderate Requests");
		checkRequests.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		checkRequests.setBounds(10, 11, 146, 23);
		checkRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new ModerateReqsWindow(user, gui)).setVisible(true);
			}
		});
		panel.add(checkRequests);

		JButton editRank = new JButton("Edit User Rank");
		editRank.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		editRank.setBounds(161, 11, 146, 23);
		editRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new EditUserRankWindow(user)).setVisible(true);
			}
		});
		panel.add(editRank);

		JButton addEntry = new JButton("Add Entry");
		addEntry.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		addEntry.setBounds(10, 45, 146, 23);
		addEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new AddEntryWindow(user, gui)).setVisible(true);
			}
		});
		panel.add(addEntry);

		JButton addUser = new JButton("Add User");
		addUser.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		addUser.setBounds(161, 45, 146, 23);
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new AddUserWindow(user)).setVisible(true);
			}
		});
		panel.add(addUser);

		JButton remEntry = new JButton("Remove Entry");
		remEntry.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		remEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new RemEntryWindow(user, gui)).setVisible(true);
			}
		});
		remEntry.setBounds(10, 79, 146, 23);
		panel.add(remEntry);

		JButton remUser = new JButton("Remove User");
		remUser.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		remUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new RemUserWindow(user)).setVisible(true);
			}
		});
		remUser.setBounds(161, 79, 146, 23);
		panel.add(remUser);
	}

}
