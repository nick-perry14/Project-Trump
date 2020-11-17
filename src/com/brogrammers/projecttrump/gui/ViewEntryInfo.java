// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.brogrammers.projecttrump.gui.entries.Entry;

/**
 * Entry information window
 * 
 * @author Luke Brown
 *
 */
public class ViewEntryInfo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame
	 * @param entry Entry for info to be viewed
	 */
	public ViewEntryInfo(Entry entry) {
		setResizable(false);
		setTitle("Info Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name: " + entry.getName());
		lblName.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblName.setBounds(10, 0, 263, 14);
		contentPane.add(lblName);

		JLabel lblDeveloper = new JLabel("Developer: " + entry.getDeveloper());
		lblDeveloper.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblDeveloper.setBounds(10, 26, 263, 14);
		contentPane.add(lblDeveloper);
		String rating = "Rating: ";
		switch (entry.getRating()) {
		case 1:
			rating = rating + "E";
			break;
		case 2:
			rating = rating + "E 10+";
			break;
		case 3:
			rating = rating + "T";
			break;
		case 4:
			rating = rating + "M";
			break;
		case 5:
			rating = rating + "AO";
			break;
		default:
			rating = rating + "NR";
		}
		JLabel lblRating = new JLabel(rating);
		lblRating.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblRating.setBounds(10, 51, 263, 14);
		contentPane.add(lblRating);

		JLabel lblCategory = new JLabel(
				"Category: " + ((entry.getCategory() != null) ? entry.getCategory() : "Uncategorized"));
		lblCategory.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblCategory.setBounds(10, 76, 194, 14);
		contentPane.add(lblCategory);

	}
}
