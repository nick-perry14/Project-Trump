// Copyright Brogrammers 2020
package com.brogrammers.projecttrump.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.brogrammers.projecttrump.user.User;
import com.brogrammers.projecttrump.user.UserAlreadyExistsException;

/**
 * Login GUI
 * 
 * @author Luke Brown, Nick Perry (backend)
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblUsername;
	private JLabel lblProjectTrumpV;
	private Panel panel_1;
	private Panel panel_2;
	private JLabel label;
	private static Login frame = new Login();

	/**
	 * Launch the login screen
	 * 
	 * @param args - No runtime args needed.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setResizable(false);
		setTitle("Project Trump");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/project_trump_small_logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 519);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(427, 130, 303, 336);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer pass = new StringBuffer();
				pass.append(passwordField.getPassword());
				User user = User.login(textField.getText(), pass.toString());
				if (user != null) {
					(new GUI(user)).setVisible(true);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Username or password incorrect!",
							"Incorrect Login!", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}

			}
		});
		btnNewButton.setBounds(25, 197, 122, 35);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));

		JButton btnNewButton_1 = new JButton("Guest Mode\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new GUI(null)).setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(157, 197, 121, 35);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));

		JButton btnNewButton_2 = new JButton("Create Account");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (textField.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane(), "Username Required!", "Username Required!",
								JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");

					} else if (passwordField.getPassword().length == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "Password Required!", "Password Required!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						StringBuffer pass = new StringBuffer();
						pass.append(passwordField.getPassword());
						User a = new User(textField.getText(), pass.toString());
						JOptionPane.showMessageDialog(getContentPane(), "Account " + textField.getText() + " Created!",
								"Account Created!", JOptionPane.INFORMATION_MESSAGE);
						User.storeToFile();
						(new GUI(a)).setVisible(true);
						dispose();
					}

				} catch (UserAlreadyExistsException e) {
					JOptionPane.showMessageDialog(getContentPane(), "User already exists, select new username!",
							"Account Creation Failed!", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}
			}
		});
		btnNewButton_2.setBounds(25, 243, 253, 35);
		panel.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 13));

		panel_1 = new Panel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(44, 30, 678, 54);
		contentPane.add(panel_1);

		lblProjectTrumpV = new JLabel("PROJECT TRUMP ");
		lblProjectTrumpV.setForeground(new Color(220, 20, 60));
		panel_1.add(lblProjectTrumpV);
		lblProjectTrumpV.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectTrumpV.setFont(new Font("Bahnschrift", Font.BOLD, 36));

		panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(44, 130, 303, 336);
		contentPane.add(panel_2);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("images/project_trump_small_logo.png")));
		label.setBounds(10, 11, 283, 282);
		panel_2.add(label);
	}
}
