package com.brogrammers.projecttrump.games;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TicTacToeGame extends JFrame implements Serializable{

	public static JPanel contentPane;
	public static String[] board;
	public static String turn;
	public static JButton btnNewGame;
	public static JButton btnNewButton_1;
	public static Button button;
	public static Button button_1;
	public static Button button_2;
	public static Button button_3;
	public static Button button_4;
	public static Button button_5;
	public static Button button_6;
	public static Button button_7;
	public static Button button_8;
	public static JTextPane txt;
	public static JButton btnNewButton;
	public static boolean b;
	public static int numInput;
	public static String winner = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToeGame frame = new TicTacToeGame();
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
	public TicTacToeGame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 640);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{180, 180, 180, 0};
		gbl_contentPane.rowHeights = new int[]{39, 160, 160, 160, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		btnNewGame = new JButton("Start Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}
		});
		btnNewGame.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 1;
		gbc_btnNewGame.gridy = 0;
		contentPane.add(btnNewGame, gbc_btnNewGame);
		
		btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		button = new Button("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 1;
					if (turn == "X") {
						button.setLabel("X");
						turn = "O";
						board[0] = "X";
						check();
					} else if (turn == "O") {
						button.setLabel("O");
						turn = "X";
						board[0] = "O";
						check();
					}
					button.setEnabled(false);
			}
		});
		button.setBackground(new Color(255, 255, 255));
		button.setForeground(new Color(220, 20, 60));
		button.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		contentPane.add(button, gbc_button);
		
		button_1 = new Button("2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 2;
					if (turn == "X") {
						button_1.setLabel("X");
						turn = "O";
						board[1] = "X";
						check();
					} else if (turn == "O") {
						button_1.setLabel("O");
						turn = "X";
						board[1] = "O";
						check();
					}
					button_1.setEnabled(false);
			}
		});
		button_1.setForeground(new Color(220, 20, 60));
		button_1.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		contentPane.add(button_1, gbc_button_1);
		
		button_2 = new Button("3");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 3;
					if (turn == "X") {
						button_2.setLabel("X");
						turn = "O";
						board[2] = "X";
						check();
					} else if (turn == "O") {
						button_2.setLabel("O");
						turn = "X";
						board[2] = "O";
						check();
					}
					button_2.setEnabled(false);
			}
		});
		button_2.setForeground(new Color(220, 20, 60));
		button_2.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 0);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 1;
		contentPane.add(button_2, gbc_button_2);
		
		button_3 = new Button("4");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 4;
					if (turn == "X") {
						button_3.setLabel("X");
						turn = "O";
						board[3] = "X";
						check();
					} else if (turn == "O") {
						button_3.setLabel("O");
						turn = "X";
						board[3] = "O";
						check();
					}
					button_3.setEnabled(false);
			}
		});
		button_3.setForeground(new Color(220, 20, 60));
		button_3.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 0;
		gbc_button_3.gridy = 2;
		contentPane.add(button_3, gbc_button_3);
		
		button_4 = new Button("5");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 5;
					if (turn == "X") {
						button_4.setLabel("X");
						turn = "O";
						board[4] = "X";
						check();
					} else if (turn == "O") {
						button_4.setLabel("O");
						turn = "X";
						board[4] = "O";
						check();
					}
					button_4.setEnabled(false);
			}
		});
		button_4.setForeground(new Color(220, 20, 60));
		button_4.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 1;
		gbc_button_4.gridy = 2;
		contentPane.add(button_4, gbc_button_4);
		
		button_5 = new Button("6");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 6;
					if (turn == "X") {
						button_5.setLabel("X");
						turn = "O";
						board[5] = "X";
						check();
					} else if (turn == "O") {
						button_5.setLabel("O");
						turn = "X";
						board[5] = "O";
						check();
				}
				button_5.setEnabled(false);
			}
		});
		button_5.setForeground(new Color(220, 20, 60));
		button_5.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 5, 0);
		gbc_button_5.gridx = 2;
		gbc_button_5.gridy = 2;
		contentPane.add(button_5, gbc_button_5);
		
		button_6 = new Button("7");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 7;
					if (turn == "X") {
						button_6.setLabel("X");
						turn = "O";
						board[6] = "X";
						check();
					} else if (turn == "O") {
						button_6.setLabel("O");
						turn = "X";
						board[6] = "O";
						check();
					}
					button_6.setEnabled(false);
			}
		});
		button_6.setForeground(new Color(220, 20, 60));
		button_6.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 0;
		gbc_button_6.gridy = 3;
		contentPane.add(button_6, gbc_button_6);
		
		button_7 = new Button("8");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 8;
					if (turn == "X") {
						button_7.setLabel("X");
						turn = "O";
						board[7] = "X";
						check();
					} else if (turn == "O") {
						button_7.setLabel("O");
						turn = "X";
						board[7] = "O";
						check();
					}
					button_7.setEnabled(false);
				}
		});
		button_7.setForeground(new Color(220, 20, 60));
		button_7.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 5, 5);
		gbc_button_7.gridx = 1;
		gbc_button_7.gridy = 3;
		contentPane.add(button_7, gbc_button_7);
		
		button_8 = new Button("9");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numInput = 9;
					if (turn == "X") {
						button_8.setLabel("X");
						turn = "O";
						board[8] = "X";
						check();
					} else if (turn == "O") {
						button_8.setLabel("O");
						turn = "X";
						board[8] = "O";
						check();
					}
					button_8.setEnabled(false);
			}
		});
		button_8.setForeground(new Color(220, 20, 60));
		button_8.setFont(new Font("Bahnschrift", Font.BOLD, 90));
		button_8.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.insets = new Insets(0, 0, 5, 0);
		gbc_button_8.gridx = 2;
		gbc_button_8.gridy = 3;
		contentPane.add(button_8, gbc_button_8);
		
		txt = new JTextPane();
		txt.setEditable(false);
		txt.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		txt.setText("Welcome to Tic-Tac-Toe");
		GridBagConstraints gbc_txtpnAdfasdf = new GridBagConstraints();
		gbc_txtpnAdfasdf.gridwidth = 3;
		gbc_txtpnAdfasdf.fill = GridBagConstraints.BOTH;
		gbc_txtpnAdfasdf.gridx = 0;
		gbc_txtpnAdfasdf.gridy = 4;
		contentPane.add(txt, gbc_txtpnAdfasdf);
		
		btnNewButton = new JButton("Dark Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contentPane.getBackground() == Color.black) {
					button.setBackground(Color.white);
					button_1.setBackground(Color.white);
					button_2.setBackground(Color.white);
					button_3.setBackground(Color.white);
					button_4.setBackground(Color.white);
					button_5.setBackground(Color.white);
					button_6.setBackground(Color.white);
					button_7.setBackground(Color.white);
					button_8.setBackground(Color.white);
					contentPane.setBackground(Color.white);
					txt.setBackground(Color.white);
					btnNewButton.setLabel("Dark Mode");
				}
				else if (contentPane.getBackground() == Color.white) {
					button.setBackground(Color.black);
					button_1.setBackground(Color.black);
					button_2.setBackground(Color.black);
					button_3.setBackground(Color.black);
					button_4.setBackground(Color.black);
					button_5.setBackground(Color.black);
					button_6.setBackground(Color.black);
					button_7.setBackground(Color.black);
					button_8.setBackground(Color.black);
					txt.setBackground(Color.black);
					contentPane.setBackground(Color.black);
					btnNewButton.setLabel("Light Mode");
				}
			}
		});
		
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		}

	// Implement Back-end

	public static void startGame() {
		board = new String[9];
		turn = "X";
		emptyBoard();
		txt.setText("Welcome to 2 Player Tic Tac Toe.");
		check();
	}

	static void check() {
		for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			if (line.equals("XXX")) {
				txt.setText("X Wins! Thank You For Playing!");
				fillBoard();
				return;
			} else if (line.equals("OOO")) {
				txt.setText("O Wins! Thank You For Playing");
				fillBoard();
				return;
			} else {
				txt.setText(turn + "'s turn; enter a slot number to place " + turn + " in:");
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8)
				txt.setText("Draw!");
		}

	}
	static void fillBoard() {
		button.setEnabled(false);
		button_1.setEnabled(false);
		button_2.setEnabled(false);
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		button_5.setEnabled(false);
		button_6.setEnabled(false);
		button_7.setEnabled(false);
		button_8.setEnabled(false);
	}
	static void emptyBoard() {
		for (int a = 0; a < 9; a++) {
			board[a] = String.valueOf(a + 1);
		}
		button.setLabel("1");
		button_1.setLabel("2");
		button_2.setLabel("3");
		button_3.setLabel("4");
		button_4.setLabel("5");
		button_5.setLabel("6");
		button_6.setLabel("7");
		button_7.setLabel("8");
		button_8.setLabel("9");
		button.setEnabled(true);
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(true);
		button_8.setEnabled(true);
	}

}
