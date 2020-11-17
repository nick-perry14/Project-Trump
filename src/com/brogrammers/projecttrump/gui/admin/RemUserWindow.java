// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.admin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.brogrammers.projecttrump.user.User;

/**
 * Admin Panel Remove User Window
 * 
 * @author Nick Perry
 *
 */
public class RemUserWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField rankField;

	/**
	 * Creates Remove User Window
	 * 
	 * @param admin Admin User
	 */
	public RemUserWindow(User admin) {
		if (!User.isAdmin(admin))
			dispose();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Remove User");
		setResizable(false);
		setMinimumSize(new Dimension(260, 559));
		setMaximumSize(new Dimension(260, 559));
		DefaultListModel<User> l1 = new DefaultListModel<>();
		for (User x : User.getAllUsers(admin).values()) {
			l1.addElement(x);
		}

		JLabel userListLabel = new JLabel("User List");
		userListLabel.setBounds(31, 11, 164, 14);
		getContentPane().add(userListLabel);
		JList<User> list = new JList<>(l1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 25, 164, 408);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				rankField.setText(list.getSelectedValue().getRank().toString());

			}
		});
		getContentPane().add(scrollPane);
		getContentPane().setSize(400, 400);
		getContentPane().setLayout(null);

		JButton submitButton = new JButton("Remove User");
		submitButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		submitButton.setBounds(62, 459, 111, 23);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == admin) {
					JOptionPane.showMessageDialog(getContentPane(), "You cannot remove yourself!", "Error Removing!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (list.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Select a user", "Error Removing!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int opt = JOptionPane.showConfirmDialog(getContentPane(),
						"Are you sure you would like to remove " + list.getSelectedValue().getUsername() + "?",
						"Confirm Remove", JOptionPane.YES_NO_OPTION);
				if (opt == 0) {
					if (User.removeUser(admin, list.getSelectedValue())) {
						JOptionPane.showMessageDialog(getContentPane(),
								"User " + list.getSelectedValue().getUsername() + " has been removed!",
								"Remove Success!", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else
						JOptionPane.showMessageDialog(getContentPane(),
								"Error removing " + list.getSelectedValue().getUsername() + "!", "Error Removing!",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		getContentPane().add(submitButton);

		rankField = new JTextField();
		rankField.setEditable(false);
		rankField.setBounds(72, 433, 101, 20);
		getContentPane().add(rankField);
		rankField.setColumns(10);

		JLabel rankLabel = new JLabel("Rank:");
		rankLabel.setBounds(31, 436, 46, 14);
		getContentPane().add(rankLabel);
		getContentPane().setVisible(true);

	}
}
