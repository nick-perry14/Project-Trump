package com.brogrammers.projecttrump.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JToggleButton;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;
import java.awt.ScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.SwingConstants;

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
		panel.setBounds(10, 59, 386, 411);
		contentPane.add(panel);

		Button button = new Button("Play Selected Game");
		button.setActionCommand("Play ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("Bahnschrift", Font.BOLD, 22));
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(429, 30, 316, 51);
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

		JButton btnNewButton = new JButton("Toggle Dark Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(Color.black);
			}
		});
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton.setBounds(10, 25, 161, 23);
		contentPane.add(btnNewButton);

		JButton btnToggleLightMode = new JButton("Toggle Light Mode");
		btnToggleLightMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(Color.white);
			}
		});
		btnToggleLightMode.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnToggleLightMode.setBounds(235, 25, 161, 23);
		contentPane.add(btnToggleLightMode);

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
