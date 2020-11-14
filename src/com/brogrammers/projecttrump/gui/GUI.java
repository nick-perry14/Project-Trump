package com.brogrammers.projecttrump.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JTextField;

import com.brogrammers.projecttrump.gui.entries.Category;
import com.brogrammers.projecttrump.gui.entries.Entry;
import com.brogrammers.projecttrump.gui.entries.EntryMutableTreeNode;
import com.brogrammers.projecttrump.user.User;

/**
 * Main Gui Class
 * 
 * @author Luke Brown (Main), Nick Perry (Contributer)
 *
 */
class GUI extends JFrame implements WindowListener {
	/**
	 * Content pane of GUI
	 */
	private JPanel contentPane;
	/**
	 * Current logged in user - null for guest
	 */
	User user;

	/**
	 * Launch the application. String username is the name of the user.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(user); // RENAME TO ResizableGUI (IM TALKING TO YOU, LUKE)
												// <---------------------------
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
	public GUI(User user) {
		setTitle("Project Trump");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/project_trump_small_logo.png")));
		setBounds(100, 100, 700, 500);
		Dimension d = new Dimension(700, 500);
		setMinimumSize(d);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 272, 226, 0 };
		gbl_contentPane.rowHeights = new int[] { 123, 328, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Creates default game if there are no entries
		Entry.addDefaultGames();
		// Sets User
		this.user = user;

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
		JLabel lblNewLabel = new JLabel("Welcome, " + ((user == null) ? "Guest" : user.getUsername()));
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
		Button playButton = new Button("Run Selected App");
		playButton.setForeground(Color.WHITE);
		playButton.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		playButton.setBackground(new Color(220, 20, 60));
		playButton.setActionCommand("Play ");
		playButton.setBounds(10, 10, 294, 96);
		panel_1.add(playButton);

		// creates a tree with games as nodes
		JTree tree = new JTree();
		tree.setScrollsOnExpand(true);
		tree.setSelectionRows(new int[] { 1 });
		tree.setSelectionRow(1);
		tree.setForeground(Color.GRAY);
		tree.setFont(new Font("Bahnschrift", Font.BOLD, 11));
		tree.setBorder(null);
		tree.setBackground((Color.white));
		tree.setBounds(10, 58, 294, 260);

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Applications") {
			private static final long serialVersionUID = 1L;

			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Games");
				DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("Utilities");
				DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode("Social Media");
				DefaultMutableTreeNode node_4 = new DefaultMutableTreeNode("Business");
				DefaultMutableTreeNode node_5 = new DefaultMutableTreeNode("News");
				DefaultMutableTreeNode node_6 = new DefaultMutableTreeNode("Uncategorized");
				for (Entry x : Entry.getEntries()) {
					Category cat = x.getCategory();
					if (cat == null) {
						node_6.add(new EntryMutableTreeNode(x.getName(), x));
						continue;
					}
					switch (cat) {
					case GAME:
						node_1.add(new EntryMutableTreeNode(x.getName(), x));
						break;
					case UTILITY:
						node_2.add(new EntryMutableTreeNode(x.getName(), x));
						break;
					case SOCIAL:
						node_3.add(new EntryMutableTreeNode(x.getName(), x));
						break;
					case BUSINESS:
						node_4.add(new EntryMutableTreeNode(x.getName(), x));
						break;
					case NEWS:
						node_5.add(new EntryMutableTreeNode(x.getName(), x));
						break;
					}

				}
				if (!node_1.isLeaf())
					add(node_1);
				if (!node_2.isLeaf())
					add(node_2);
				if (!node_3.isLeaf())
					add(node_3);
				if (!node_4.isLeaf())
					add(node_4);
				if (!node_5.isLeaf())
					add(node_5);
				if (!node_6.isLeaf())
					add(node_6);
			}
		}));

		// potential logo panel or game panel
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.GRAY, 2));
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);

		JLabel lblDirectoryManagement = new JLabel("Control Center");
		lblDirectoryManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirectoryManagement.setForeground(new Color(220, 20, 60));
		lblDirectoryManagement.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		lblDirectoryManagement.setBounds(10, 11, 335, 41);
		panel_2.add(lblDirectoryManagement);

		JTextField textField = new JTextField();
		textField.setBounds(10, 71, 194, 20);
		panel_2.add(textField);
		textField.setColumns(10);

		// Search Button
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// root of the tree
				Object root = tree.getModel().getRoot();

				int parentLimit = tree.getModel().getChildCount(root);
				for (int i = 0; i < parentLimit; i++) {
					if (textField.getText().equals(""))
						break;
					tree.expandRow(0); // will expand all nodes
					tree.expandRow(1);
					Object node = tree.getModel().getChild(root, i); // gets the child of the parent
					int childLimit = tree.getModel().getChildCount(node);
					for (int j = 0; j < childLimit; j++) {
						Object child = tree.getModel().getChild(node, j); // gets the child of the previous child
						String childStr = child.toString().toUpperCase(); // turns node into string
						if (childStr.contains(textField.getText().toUpperCase())) { // if the node contains a portion of
																					// the search
							TreePath tPath = getPath((TreeNode) child); // get treepath
							tree.setSelectionPath(tPath); // select node
						}
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_2.setBounds(214, 69, 131, 23);
		panel_2.add(btnNewButton_2);

		// Sort Button
		JButton btnNewButton_3 = new JButton("Sort To Other Folder");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object root = tree.getModel().getRoot();
				// array that stores selected node(s)
				TreePath[] paths = tree.getSelectionPaths();
				for (TreePath path : paths) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent(); // finds
																												// the
																												// selected
																												// node
					String selectedNodeStr = path.getLastPathComponent().toString(); // converts node to string
					if (selectedNode.getParent().toString().equals("Games")) { // If it is in the Games category
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						model.removeNodeFromParent((MutableTreeNode) selectedNode); // remove node from games
						Object utilNode = (MutableTreeNode) tree.getModel().getChild(root, 1);
						model.insertNodeInto(new DefaultMutableTreeNode(selectedNodeStr), (MutableTreeNode) utilNode,
								0); // insert node into utilities
					} else if (selectedNode.getParent().toString().equals("Utilities")) {
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						model.removeNodeFromParent((MutableTreeNode) selectedNode); // remove node from utilties
						Object gamesNode = (MutableTreeNode) tree.getModel().getChild(root, 0);
						model.insertNodeInto(new DefaultMutableTreeNode(selectedNodeStr), (MutableTreeNode) gamesNode,
								0); // insert node into games
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_3.setBounds(180, 102, 165, 23);
		panel_2.add(btnNewButton_3);

		// Filter Games Button
		JButton btnNewButton_4 = new JButton("Filter Games");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object root = tree.getModel().getRoot();

				int parentLimit = tree.getModel().getChildCount(root);
				for (int i = 0; i < parentLimit; i++) {
					Object node = tree.getModel().getChild(root, i); // gets the child of the parent

					if (node.toString().equals("Games")) {
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						model.removeNodeFromParent((MutableTreeNode) node);
					}
				}
			}
		});
		btnNewButton_4.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_4.setBounds(10, 102, 165, 23);
		panel_2.add(btnNewButton_4);

		// Filter Utilities Button
		JButton btnNewButton_5 = new JButton("Filter Utilities");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object root = tree.getModel().getRoot();

				int parentLimit = tree.getModel().getChildCount(root);
				for (int i = 0; i < parentLimit; i++) {
					Object node = tree.getModel().getChild(root, i); // gets the child of the parent

					if (node.toString().equals("Utilities")) {
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						model.removeNodeFromParent((MutableTreeNode) node);
					}
				}
			}
		});
		btnNewButton_5.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_5.setBounds(10, 136, 165, 23);
		panel_2.add(btnNewButton_5);

		// Undo Filters Button
		JButton btnNewButton_6 = new JButton("Undo Filter(s)");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Applications") {
					private static final long serialVersionUID = 1L;

					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Games");
						DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("Utilities");
						DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode("Social Media");
						DefaultMutableTreeNode node_4 = new DefaultMutableTreeNode("Business");
						DefaultMutableTreeNode node_5 = new DefaultMutableTreeNode("News");
						DefaultMutableTreeNode node_6 = new DefaultMutableTreeNode("Uncategorized");
						for (Entry x : Entry.getEntries()) {
							Category cat = x.getCategory();
							if (cat == null) {
								node_6.add(new EntryMutableTreeNode(x.getName(), x));
								continue;
							}
							switch (cat) {
							case GAME:
								node_1.add(new EntryMutableTreeNode(x.getName(), x));
								break;
							case UTILITY:
								node_2.add(new EntryMutableTreeNode(x.getName(), x));
								break;
							case SOCIAL:
								node_3.add(new EntryMutableTreeNode(x.getName(), x));
								break;
							case BUSINESS:
								node_4.add(new EntryMutableTreeNode(x.getName(), x));
								break;
							case NEWS:
								node_5.add(new EntryMutableTreeNode(x.getName(), x));
								break;
							}

						}
						if (!node_1.isLeaf())
							add(node_1);
						if (!node_2.isLeaf())
							add(node_2);
						if (!node_3.isLeaf())
							add(node_3);
						if (!node_4.isLeaf())
							add(node_4);
						if (!node_5.isLeaf())
							add(node_5);
						if (!node_6.isLeaf())
							add(node_6);
					}
				}));
			}
		});
		btnNewButton_6.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_6.setBounds(180, 136, 165, 23);
		panel_2.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("Select Favorite ");
		btnNewButton_7.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_7.setBounds(10, 170, 165, 23);
		panel_2.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("Reviews");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Review.main(null);
			}
		});
		btnNewButton_8.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		btnNewButton_8.setBounds(180, 170, 165, 23);
		panel_2.add(btnNewButton_8);

		String r = "";
		if (user == null) {
			r = "GUEST";
		} else {
			r = user.getRank().toString();
		}
		JLabel lblRank = new JLabel("Rank : " + r);
		lblRank.setHorizontalAlignment(SwingConstants.LEFT);
		lblRank.setForeground(new Color(220, 20, 60));
		lblRank.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblRank.setBounds(10, 286, 165, 31);
		panel_2.add(lblRank);

		JLabel lblApplicationDirectory = new JLabel("Application Directory");
		lblApplicationDirectory.setForeground(new Color(220, 20, 60));
		lblApplicationDirectory.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplicationDirectory.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		lblApplicationDirectory.setBounds(23, 11, 271, 36);
		// panel to hold our tree
		JScrollPane panel_3 = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_3.setBorder(new LineBorder(new Color(105, 105, 105), 2));
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(230, 230, 250));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		panel_3.add(tree);
		panel_3.add(lblApplicationDirectory);
		contentPane.add(panel_3, gbc_panel_3);
		// Dark mode / Light mode button
		JButton btnNewButton = new JButton("Dark Mode");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (panel_0.getBackground() == Color.black) {
					panel_0.setBackground(Color.white);
					panel_1.setBackground(Color.white);
					contentPane.setBackground(Color.white);
					panel_2.setBackground(Color.white);
					tree.setBackground((Color.white));
					panel_3.setBackground(Color.white);
					btnNewButton.setLabel("Dark Mode");
				} else if (panel_0.getBackground() == Color.white) {
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
			public void actionPerformed(ActionEvent e) { // will evaluate the node highlighted
				// array that stores selected node(s)
				TreePath[] paths = tree.getSelectionPaths();
				for (TreePath path : paths) {
					DefaultMutableTreeNode x = (DefaultMutableTreeNode) path.getLastPathComponent();
					if (x instanceof EntryMutableTreeNode) {
						((EntryMutableTreeNode) x).getEntry().run();
						break;
					}
				}
			}
		});
		addWindowListener(this);
	}

	// returns the treepath for a node
	public static TreePath getPath(TreeNode treeNode) {
		ArrayList<Object> nodes = new ArrayList<Object>();
		if (treeNode != null) {
			nodes.add(treeNode);
			treeNode = treeNode.getParent();
			while (treeNode != null) {
				nodes.add(0, treeNode);
				treeNode = treeNode.getParent();
			}
		}
		if (nodes.isEmpty()) {
			return null;
		}
		return new TreePath(nodes.toArray());
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	/**
	 * Makes sure entries and users are stored before application closes.
	 */
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