package com.brogrammers.projecttrump.gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblUsername;
	private JLabel lblProjectTrumpV;
	private Panel panel_1;
	private Panel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 519);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(427, 130, 303, 304);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(25, 11, 253, 35);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setBounds(25, 57, 253, 35);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 103, 253, 37);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(25, 151, 253, 35);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(25, 197, 122, 35);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		
		JButton btnNewButton_1 = new JButton("Guest Mode\r\n");
		btnNewButton_1.setBounds(157, 197, 121, 35);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		
		JButton btnNewButton_2 = new JButton("Create Account");
		btnNewButton_2.setBounds(25, 243, 253, 35);
		panel.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		
		panel_1 = new Panel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(44, 30, 678, 54);
		contentPane.add(panel_1);
		
		lblProjectTrumpV = new JLabel("PROJECT TRUMP V1.1");
		lblProjectTrumpV.setForeground(new Color(220, 20, 60));
		panel_1.add(lblProjectTrumpV);
		lblProjectTrumpV.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectTrumpV.setFont(new Font("Bahnschrift", Font.BOLD, 36));
		
		panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(44, 130, 303, 304);
		contentPane.add(panel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Luket\\Desktop\\blue-and-white-website-icon.png"));
		label.setBounds(10, 11, 283, 282);
		panel_2.add(label);
	}
}
