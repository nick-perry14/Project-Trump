package com.brogrammers.projecttrump.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtWords;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JList list;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 519);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 98, 386, 372);
		contentPane.add(panel);

		Button button = new Button("Play Selected Game");
		button.setActionCommand("Play ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("Bahnschrift", Font.BOLD, 22));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(220, 20, 60));
		button.setBounds(429, 10, 316, 71);
		contentPane.add(button);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Applications") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Games");
				node_1.add(new DefaultMutableTreeNode("Tic Tac Toe"));
				node_1.add(new DefaultMutableTreeNode("BlackJack"));
				node_1.add(new DefaultMutableTreeNode("Connect4"));
				node_1.add(new DefaultMutableTreeNode("Sudoku"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Utilities");
				node_1.add(new DefaultMutableTreeNode("Calculator"));
				node_1.add(new DefaultMutableTreeNode("Stopwatch"));
				add(node_1);
			}
		}));

		tree.setForeground(Color.GRAY);
		tree.setFont(new Font("Bahnschrift", Font.BOLD, 11));
		tree.setBorder(new LineBorder(Color.GRAY, 2));
		tree.setBackground(Color.GRAY);
		tree.setBounds(429, 135, 316, 335);

		contentPane.add(tree);

		txtWords = new JTextField();
		txtWords.setEditable(false);
		txtWords.setBackground(Color.GRAY);
		txtWords.setHorizontalAlignment(SwingConstants.CENTER);
		txtWords.setForeground(Color.BLACK);
		txtWords.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		txtWords.setText("Application Directory\r\n");
		txtWords.setBounds(429, 100, 316, 36);
		contentPane.add(txtWords);
		txtWords.setColumns(10);
		// dark mode/light mode button has been turned into a single button
		JButton btnNewButton = new JButton("Dark Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contentPane.getBackground() == Color.black) {
					contentPane.setBackground(Color.white);
					btnNewButton.setLabel("Dark Mode");
				} else if (contentPane.getBackground() == Color.white) {
					contentPane.setBackground(Color.black);
					btnNewButton.setLabel("Light Mode");
				}
			}
		});
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton.setBounds(10, 64, 161, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnNewButton_1.setBounds(235, 64, 161, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Welcome, Guest!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 10, 386, 43);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 134, 316, 335);

		for (int i = 0; i < 5; i++) {
			JRadioButton button2 = new JRadioButton("" + (i + 1));
			GridBagConstraints gbc = new GridBagConstraints(0, i, // cell for top left corner
					1, 1, // cells to span
					1, i == 4 ? 1 : 0, // spacing wieght
					GridBagConstraints.PAGE_START, // where to anchor the component in the cell
					GridBagConstraints.HORIZONTAL, // how to fill extra space
					new Insets(0, 0, 0, 0), // insets for the cell
					0, 0); // additional padding
			scrollPane.add(button2, gbc);
		}
	}
}
