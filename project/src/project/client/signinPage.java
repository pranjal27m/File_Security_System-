package project.client;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class signinPage extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField passwordTextField;
	private JTextField emailTextField;
	private JButton submit,signUp;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signinPage frame = new signinPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public signinPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(173, 119, 126, 22);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel email = new JLabel("Email");
		email.setBounds(92, 86, 33, 22);
		contentPane.add(email);
		
		JLabel password = new JLabel("Password");
		password.setBounds(79, 119, 67, 22);
		contentPane.add(password);
		
		JLabel head = new JLabel("Sign In");
		head.setBounds(157, 11, 45, 22);
		head.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(head);
		
		JLabel lblNewLabel = new JLabel("File Security");
		lblNewLabel.setBounds(130, 288, 106, 22);
		lblNewLabel.setFont(new Font("Parchment", Font.PLAIN, 32));
		contentPane.add(lblNewLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(173, 86, 126, 22);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		signUp = new JButton("Sign Up");
		signUp.setBackground(UIManager.getColor("Button.light"));
		signUp.setBorder(null);
		signUp.setBounds(152, 205, 67, 22);
		signUp.addActionListener(this);
		contentPane.add(signUp);
		
		submit = new JButton("Sign In");
		submit.setBackground(UIManager.getColor("Button.light"));
		submit.addActionListener(this);
		submit.setBounds(142, 171, 93, 23);
		contentPane.add(submit);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			
			String email = emailTextField.getText();
			String password = new String(passwordTextField.getPassword());
		
			try {
				Connection con = connection.connect();
				PreparedStatement ps =  con.prepareStatement("select Email,Password from Fuser where Email=? and Password=?");
				ps.setString(1, email);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					dispose();
					homePage hp = new homePage();
					hp.setVisible(true);
					JOptionPane.showMessageDialog(submit, "You have successfully logged in");
					System.out.println(email + " : " +"logged in to homePage");
				}else {
					JOptionPane.showMessageDialog(submit, "Wrong credentials");
					System.out.println("Wrong credentials");
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}	
		}
		if(e.getSource() == signUp) {
			
			dispose();
			signupPage sip = new signupPage();
			sip.setVisible(true);
			
		}
	}
}
