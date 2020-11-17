// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brogrammers.projecttrump.user.Ranks;
import com.brogrammers.projecttrump.user.User;
import com.brogrammers.projecttrump.user.UserAlreadyExistsException;

/**
 * Window in admin panel that allows an admin to add a user
 * 
 * @author Nick Perry
 *
 */
public class AddUserWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JLabel password;
	private JLabel userName;
	User user;
	private JPasswordField passwordField;
	private JLabel rankLabel;
	private JComboBox<Ranks> rankSelector;

	/**
	 * Creates the frame
	 * 
	 * @param user Admin User
	 */
	public AddUserWindow(User user) {
		this.user = user;
		setResizable(false);
		setTitle("Add User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 368, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userName = new JLabel("Username:");
		userName.setBounds(10, 11, 250, 14);
		contentPane.add(userName);

		nameField = new JTextField();
		nameField.setToolTipText("");
		nameField.setBounds(10, 24, 250, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		password = new JLabel("Password:");
		password.setBounds(10, 55, 250, 14);
		contentPane.add(password);
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 67, 250, 20);
		contentPane.add(passwordField);
		JButton sendButton = new JButton("Create User");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean req = true;
				if (nameField.getText().equals("")) {
					req = false;
					userName.setForeground(Color.RED);
				} else
					userName.setForeground(Color.BLACK);
				if (passwordField.getText().equals("")) {
					req = false;
					password.setForeground(Color.RED);
				} else
					password.setForeground(Color.BLACK);
				if (req) {
					try {
						StringBuffer pass = new StringBuffer();
						pass.append(passwordField.getPassword());
						User u = new User(nameField.getText(), pass.toString());
						User.changeRank(user, u, (Ranks) rankSelector.getSelectedItem());
						JOptionPane.showMessageDialog(contentPane,
								"User " + nameField.getText() + " Successfully Created!", "User Created!",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (UserAlreadyExistsException ex) {
						JOptionPane.showMessageDialog(getContentPane(), "User already exists, select new username!",
								"Account Creation Failed!", JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
					}
				} else {

				}

			}
		});

		rankLabel = new JLabel("Rank:");
		rankLabel.setBounds(10, 93, 46, 14);
		contentPane.add(rankLabel);
		sendButton.setBounds(234, 154, 118, 23);
		contentPane.add(sendButton);

		rankSelector = new JComboBox<>();
		rankSelector.addItem(Ranks.USER);
		rankSelector.addItem(Ranks.MODERATOR);
		rankSelector.addItem(Ranks.ADMIN);
		;
		rankSelector.setBounds(10, 111, 250, 22);
		contentPane.add(rankSelector);
	}
}
