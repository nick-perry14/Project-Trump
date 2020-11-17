// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brogrammers.projecttrump.gui.entries.Entry;
import com.brogrammers.projecttrump.user.User;

/**
 * Review Window for entries
 * 
 * @author Luke Brown
 *
 */
public class Review extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private List list;
	private JButton btnDelete;

	/**
	 * Creates Frame
	 * 
	 * @param entry Entry to be reviewed
	 * @param user  User opening the window
	 */
	public Review(Entry entry, User user) {
		setTitle(entry.getName() + " Reviews");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new List();
		list.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		list.setBounds(10, 10, 424, 206);
		contentPane.add(list);

		for (int i = 0; i < entry.getComments().size(); i++) {
			list.add(entry.getComments().get(i).getContent() + "  -" + entry.getComments().get(i).getUser());
		}

		textField = new JTextField();
		textField.setFont(new Font("Bahnschrift", Font.BOLD, 11));
		textField.setBounds(170, 224, 264, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnWriteNewReview = new JButton("Write New Review");
		btnWriteNewReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entry.addComment(user, textField.getText());
				list.add(textField.getText() + "  -" + user.getUsername());
			}
		});
		btnWriteNewReview.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnWriteNewReview.setBounds(10, 224, 150, 26);
		contentPane.add(btnWriteNewReview);

		btnDelete = new JButton("Delete (Moderator Only)");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (User.isModerator(user)) {
					entry.removeComment(user, list.getSelectedIndex());
					list.remove(list.getSelectedIndex());
				} else
					JOptionPane.showMessageDialog(contentPane, "You do not have permission to do this!",
							"No Permission!", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnDelete.setFont(new Font("Bahnschrift", Font.BOLD, 10));
		btnDelete.setBounds(193, 254, 219, 17);
		if (User.isModerator(user))
			contentPane.add(btnDelete);

		JButton btnViewDetails = new JButton("View Entry Info");
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new ViewEntryInfo(entry)).setVisible(true);;
			}
		});
		btnViewDetails.setFont(new Font("Bahnschrift", Font.BOLD, 10));
		btnViewDetails.setBounds(10, 254, 150, 17);
		contentPane.add(btnViewDetails);

	}
}
