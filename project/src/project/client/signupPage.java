package project.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class signupPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JPasswordField confirmPasswordTextField;
	private JButton submit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signupPage frame = new signupPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public signupPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel email = new JLabel("Email");
		email.setBounds(84, 77, 33, 22);
		contentPane.add(email);
		
		JLabel password = new JLabel("Password");
		password.setBounds(77, 110, 67, 22);
		contentPane.add(password);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(165, 77, 126, 22);
		contentPane.add(emailTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(165, 110, 126, 22);
		contentPane.add(passwordTextField);
		
		submit = new JButton("Sign Up");
		submit.setBounds(130, 203, 89, 23);
		submit.addActionListener(this);
		contentPane.add(submit);
		
		JLabel lblNewLabel = new JLabel("File Security");
		lblNewLabel.setFont(new Font("Parchment", Font.PLAIN, 32));
		lblNewLabel.setBounds(130, 288, 106, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblSignIn = new JLabel("Sign Up");
		lblSignIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSignIn.setBounds(139, 11, 67, 22);
		contentPane.add(lblSignIn);
		
		JLabel confirmPassword = new JLabel("Confirm Password");
		confirmPassword.setBounds(45, 143, 110, 22);
		contentPane.add(confirmPassword);
		
		confirmPasswordTextField = new JPasswordField();
		confirmPasswordTextField.setColumns(10);
		confirmPasswordTextField.setBounds(165, 143, 126, 22);
		contentPane.add(confirmPasswordTextField);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			
			String email = emailTextField.getText();
			String password = new String(passwordTextField.getPassword());
			String confirmpassword = new String( confirmPasswordTextField.getPassword());
			if(confirmpassword.equals(password)) {
				
				try {
						Connection con = connection.connect();
						
						PreparedStatement ps =  con.prepareStatement("insert into Fuser values(?,?)");
						ps.setString(1, email);
						ps.setString(2, password);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							dispose();
							homePage hp = new homePage();
							hp.setVisible(true);
							JOptionPane.showMessageDialog(submit, "Welcome! You have successfully signed in. Check your Email id for cofirmation");
							System.out.println(email + " : " +"signed in to homePage");
							
							mailSender.sendBeginMail(email);
							
						}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}	
				
			}
			else {
				JOptionPane.showMessageDialog(submit, "check password");
			}
			
		}
	}
}
