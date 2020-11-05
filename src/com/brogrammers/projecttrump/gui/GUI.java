package com.brogrammers.projecttrump.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.brogrammers.projecttrump.user.User;

class GUI extends JFrame implements WindowListener{

	private JPanel contentPane;


	/**
	 * Launch the application.
	 * String username is the name of the user.
	 */
	public static void main(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(username); // RENAME TO ResizableGUI (IM TALKING TO YOU, LUKE) <---------------------------
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
	public GUI(String username) {
		setTitle("Project Trump");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		Dimension d = new Dimension(700,500);
		setMinimumSize(d);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{272, 226, 0};
		gbl_contentPane.rowHeights = new int[]{123, 328, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		// "Welcome" Panel
		JPanel panel_0 = new JPanel();
		panel_0.setBorder(new LineBorder(Color.GRAY, 2));
		panel_0.setLayout(null);
		panel_0.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_0 = new GridBagConstraints();
		gbc_panel_0.insets = new Insets(0, 0, 5, 5);
		gbc_panel_0.fill = GridBagConstraints.BOTH;
		gbc_panel_0.gridx = 0;
		gbc_panel_0.gridy = 0;
		contentPane.add(panel_0, gbc_panel_0);

		// Creating the "Welcome Guest Label
		JLabel lblNewLabel = new JLabel("Welcome, " + username);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		lblNewLabel.setBounds(0, 11, 350, 60);
		panel_0.add(lblNewLabel);

		// "Logout" button
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User.storeToFile();
				Entry.storeToFile();
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnNewButton_1.setBounds(195, 82, 134, 23);
		panel_0.add(btnNewButton_1);

		// "Play Selected Game" Panel
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY, 2));
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);

		// creates the "Play Selected Game" button
		Button playButton = new Button("Play Selected Game");
		playButton.setForeground(Color.WHITE);
		playButton.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		playButton.setBackground(new Color(220, 20, 60));
		playButton.setActionCommand("Play ");
		playButton.setBounds(10, 10, 294, 96);
		panel_1.add(playButton);



		// potential logo panel or game panel
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.GRAY, 2));
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// panel to hold our tree
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(105, 105, 105), 2));
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(230, 230, 250));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		contentPane.add(panel_3, gbc_panel_3);

        // creates a tree with games as nodes
		JTree tree = new JTree();
		tree.setSelectionRows(new int[] {1});
		tree.setSelectionRow(1);
		tree.setForeground(Color.GRAY);
		tree.setFont(new Font("Bahnschrift", Font.BOLD, 11));
		tree.setBorder(null);
		tree.setBackground((Color.white));
		tree.setBounds(10, 58, 294, 260);

		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Applications") {
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
				}
			));

		panel_3.add(tree);

		JLabel lblApplicationDirectory = new JLabel("Application Directory");
		lblApplicationDirectory.setForeground(new Color(220, 20, 60));
		lblApplicationDirectory.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplicationDirectory.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		lblApplicationDirectory.setBounds(23, 11, 271, 36);
		panel_3.add(lblApplicationDirectory);

		// Dark mode / Light mode button
		JButton btnNewButton = new JButton("Dark Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_0.getBackground() == Color.black) {
					panel_0.setBackground(Color.white);
					panel_1.setBackground(Color.white);
					contentPane.setBackground(Color.white);
					panel_2.setBackground(Color.white);
					tree.setBackground((Color.white));
					panel_3.setBackground(Color.white);
					btnNewButton.setLabel("Dark Mode");
				}
				else if (panel_0.getBackground() == Color.white) {
					panel_0.setBackground(Color.black);
					panel_1.setBackground(Color.black);
					contentPane.setBackground(Color.black);
					panel_2.setBackground(Color.black);
					tree.setBackground((Color.black));
					panel_3.setBackground(Color.black);
					btnNewButton.setLabel("Light Mode");
				}
			}
		});
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton.setBounds(10, 82, 134, 23);
		panel_0.add(btnNewButton);


		// associates an action with the "play game" button and the selected node
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  // will evaluate the node highlighted
				// array that stores selected node(s)
				TreePath[] paths = tree.getSelectionPaths();
				for(TreePath path : paths) {
					String temp = path.getLastPathComponent().toString(); // converts node to string
					if (temp.equals("Tic Tac Toe")) {
						//TicTacToeGame.main(null);
					}
				} 
			}
		});
		addWindowListener(this);
	}



	@Override
	public void windowOpened(WindowEvent e) {
	}



	@Override
	public void windowClosing(WindowEvent e) {
		User.storeToFile();
		Entry.storeToFile();
	}



	@Override
	public void windowClosed(WindowEvent e) {
	}



	@Override
	public void windowIconified(WindowEvent e) {
	}



	@Override
	public void windowDeiconified(WindowEvent e) {
	}



	@Override
	public void windowActivated(WindowEvent e) {
	}



	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	
}


