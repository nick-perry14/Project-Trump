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
import javax.swing.ListSelectionModel;

import com.brogrammers.projecttrump.gui.GUI;
import com.brogrammers.projecttrump.gui.entries.Entry;
import com.brogrammers.projecttrump.gui.entries.WebEntry;
import com.brogrammers.projecttrump.user.User;

/**
 * Admin Panel Request Moderation Window
 * @author Nick Perry
 *
 */
public class ModerateReqsWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<WebEntry> list;
	private User admin;

	public ModerateReqsWindow(User admin, GUI gui) {
		if (!User.isAdmin(admin))
			dispose();
		this.admin = admin;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Rank Change");
		setResizable(false);
		setMinimumSize(new Dimension(275, 565));
		setMaximumSize(new Dimension(275, 565));
		DefaultListModel<WebEntry> l1 = new DefaultListModel<>();
		for (WebEntry x : Entry.getRequests(admin).keySet()) {
			l1.addElement(x);
		}

		JLabel userListLabel = new JLabel("Request List");
		userListLabel.setBounds(51, 11, 164, 14);
		getContentPane().add(userListLabel);
		list = new JList<>(l1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 25, 164, 408);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		getContentPane().add(scrollPane);
		getContentPane().setSize(400, 400);
		getContentPane().setLayout(null);

		JButton approveButton = new JButton("Approve Request");
		approveButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		approveButton.setBounds(10, 478, 125, 23);
		approveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebEntry sel = list.getSelectedValue();
				if (sel == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Select A Request!", "No Request Selected!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int opt = JOptionPane.showConfirmDialog(getContentPane(),
						"Are you sure you want to approve " + sel + "?", "Confrim Approval", JOptionPane.YES_NO_OPTION);
				if (opt == 0) {
					if (Entry.approveRequest(sel, admin)) {
						JOptionPane.showMessageDialog(getContentPane(), "Request: " + sel + " has been approved!",
								"Approve Success", JOptionPane.INFORMATION_MESSAGE);
						gui.refreshEntries();
						refreshList();

					} else
						JOptionPane.showMessageDialog(getContentPane(), "Request: " + sel + " has failed!",
								"Approve Fail", JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		JButton viewButton = new JButton("View Info");
		viewButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		viewButton.setBounds(87, 444, 89, 23);
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebEntry sel = list.getSelectedValue();
				if (sel != null)
					(new ReqsInfo(list.getSelectedValue(), Entry.getRequests(admin).get(list.getSelectedValue())))
							.setVisible(true);
				else
					JOptionPane.showMessageDialog(getContentPane(), "Select A Request!", "No Request Selected!",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		getContentPane().add(viewButton);

		JButton rejectButton = new JButton("Reject Request");
		rejectButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		rejectButton.setBounds(145, 478, 114, 23);
		rejectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebEntry sel = list.getSelectedValue();
				if (sel == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Select A Request!", "No Request Selected!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int opt = JOptionPane.showConfirmDialog(getContentPane(),
						"Are you sure you want to reject " + sel + "?", "Confrim Rejection", JOptionPane.YES_NO_OPTION);
				if (opt == 0) {
					if (Entry.rejectRequest(sel, admin)) {
						JOptionPane.showMessageDialog(getContentPane(), "Request: " + sel + " has been rejected!",
								"Rejection Success", JOptionPane.INFORMATION_MESSAGE);
						gui.refreshEntries();
						refreshList();

					} else
						JOptionPane.showMessageDialog(getContentPane(), "Request: " + sel + " has failed!",
								"Rejection Fail", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		getContentPane().add(rejectButton);
		getContentPane().add(approveButton);
		getContentPane().setVisible(true);

	}

	private void refreshList() {
		DefaultListModel<WebEntry> l1 = new DefaultListModel<>();
		for (WebEntry x : Entry.getRequests(admin).keySet()) {
			l1.addElement(x);
		}
		list.setModel(l1);
	}
}
