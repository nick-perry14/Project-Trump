package com.brogrammers.projecttrump.gui.admin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.brogrammers.projecttrump.user.Ranks;
import com.brogrammers.projecttrump.user.User;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

public class EditUserRankWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	JComboBox<Ranks> rankSelector;

	public EditUserRankWindow(User admin) {
		if(!User.isAdmin(admin))
			dispose();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Rank Change");
		setResizable(false);
		setMinimumSize(new Dimension(275, 565));
		setMaximumSize(new Dimension(275, 565));
		DefaultListModel<User> l1 = new DefaultListModel<>();
		for (User x : User.getAllUsers(admin).values()) {
			l1.addElement(x);
		}

		JLabel userListLabel = new JLabel("User List");
		userListLabel.setBounds(51, 11, 164, 14);
		getContentPane().add(userListLabel);
		JList<User> list = new JList<>(l1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 25, 164, 408);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				rankSelector.setSelectedItem(list.getSelectedValue().getRank());

			}
		});
		getContentPane().add(scrollPane);
		getContentPane().setSize(400, 400);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Selected Rank");
		lblNewLabel.setBounds(88, 444, 101, 14);
		getContentPane().add(lblNewLabel);

		rankSelector = new JComboBox<>();
		rankSelector.addItem(Ranks.USER);
		rankSelector.addItem(Ranks.MODERATOR);
		rankSelector.addItem(Ranks.ADMIN);
		rankSelector.setBounds(76, 457, 113, 29);
		getContentPane().add(rankSelector);

		JButton submitButton = new JButton("Change Rank");
		submitButton.setBounds(76, 497, 113, 23);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == admin) {
					JOptionPane.showMessageDialog(getContentPane(), "You cannot change your own rank!",
							"Error Changing Rank!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (User.changeRank(admin, list.getSelectedValue(), (Ranks) rankSelector.getSelectedItem()) == 0) {
					JOptionPane.showMessageDialog(getContentPane(),
							list.getSelectedValue().getUsername() + " has been changed to "
									+ (Ranks) rankSelector.getSelectedItem(),
							"Rank Change Success!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else
					JOptionPane.showMessageDialog(getContentPane(), "Error Changing Rank!", "Error Changing Rank!",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		getContentPane().add(submitButton);
		getContentPane().setVisible(true);

	}
}
