// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brogrammers.projecttrump.gui.entries.Category;
import com.brogrammers.projecttrump.gui.entries.Entry;
import com.brogrammers.projecttrump.user.User;

/**
 * Page for users to request new entries
 * 
 * @author Luke Brown (Main) Nick Perry (Backend / Tweaking)
 *
 */
public class RequestPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField devField;
	private JTextField urlField;
	private JLabel devLabel;
	private JLabel Rating;
	private JLabel reqLabel;
	private JSlider slider;
	private JLabel catLabel;
	private JLabel urlLabel;
	User user;

	/**
	 * Creates the frame
	 * @param user User creating the request
	 */
	public RequestPage(User user) {
		this.user = user;
		setResizable(false);
		setTitle("Submit Request");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 368, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		reqLabel = new JLabel("Request Name:");
		reqLabel.setBounds(10, 11, 250, 14);
		contentPane.add(reqLabel);

		nameField = new JTextField();
		nameField.setToolTipText("");
		nameField.setBounds(10, 24, 250, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		devLabel = new JLabel("Developer:");
		devLabel.setBounds(10, 55, 250, 14);
		contentPane.add(devLabel);

		devField = new JTextField();
		devField.setBounds(10, 69, 250, 20);
		contentPane.add(devField);
		devField.setColumns(10);

		Rating = new JLabel("Rating:");
		Rating.setBounds(10, 100, 250, 14);
		contentPane.add(Rating);

		slider = new JSlider();
		slider.setSnapToTicks(true);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put(1, new JLabel("E"));
		table.put(2, new JLabel("E 10+"));
		table.put(3, new JLabel("T"));
		table.put(4, new JLabel("M"));
		table.put(5, new JLabel("AO"));
		slider.setLabelTable(table);
		slider.setToolTipText("");
		slider.setMinorTickSpacing(1);
		slider.setValue(3);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setBounds(10, 120, 250, 45);
		contentPane.add(slider);

		catLabel = new JLabel("Category:");
		catLabel.setBounds(10, 169, 250, 14);
		contentPane.add(catLabel);

		JComboBox<Category> comboBox = new JComboBox<>();
		comboBox.setToolTipText("Leave blank for no category");
		comboBox.addItem(null);
		comboBox.addItem(Category.GAME);
		comboBox.addItem(Category.UTILITY);
		comboBox.addItem(Category.SOCIAL);
		comboBox.addItem(Category.BUSINESS);
		comboBox.addItem(Category.NEWS);
		comboBox.setBounds(10, 194, 250, 22);
		contentPane.add(comboBox);

		urlLabel = new JLabel("URL: ");
		urlLabel.setBounds(10, 227, 46, 14);
		contentPane.add(urlLabel);

		urlField = new JTextField();
		urlField.setBounds(10, 252, 250, 20);
		contentPane.add(urlField);
		urlField.setColumns(10);
		JButton sendButton = new JButton("Send Request");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean req = true;
				if (nameField.getText().equals("")) {
					req = false;
					reqLabel.setForeground(Color.RED);
				} else
					reqLabel.setForeground(Color.BLACK);
				if (devField.getText().equals("")) {
					req = false;
					devLabel.setForeground(Color.RED);
				} else
					devLabel.setForeground(Color.BLACK);
				if (urlField.getText().equals("")) {
					req = false;
					urlLabel.setForeground(Color.RED);
				} else
					urlLabel.setForeground(Color.BLACK);
				if (req) {
					Entry.addRequest(user, nameField.getText(), devField.getText(), (byte) slider.getValue(),
							(Category) comboBox.getSelectedItem(), urlField.getText());
					JOptionPane.showMessageDialog(contentPane, "Request Successfully Submitted!", "Request Submitted",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {

				}

			}
		});
		sendButton.setBounds(234, 283, 118, 23);
		contentPane.add(sendButton);

	}
}
