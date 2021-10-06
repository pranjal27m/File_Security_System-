package project.client;

import project.client.AESFileEncryption;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class encrypt extends JFrame implements ActionListener{

	JMenuItem logOut,encrypt,decrypt,about;
	private JPanel contentPane;
	private JButton browseFile,encryptButton;
	private JTextField textField;
	private File f; 
	private String filepath,filename;
	private String path;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					encrypt frame = new encrypt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public encrypt() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File Security");
		lblNewLabel.setFont(new Font("Parchment", Font.PLAIN, 32));
		lblNewLabel.setBounds(130, 288, 106, 22);
		contentPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 354, 23);
		contentPane.add(menuBar);
		
		about = new JMenuItem("About");
		about.setHorizontalTextPosition(SwingConstants.CENTER);
		about.addActionListener(this);
		menuBar.add(about);
		
		encrypt = new JMenuItem("Encrypt");
		encrypt.setHorizontalTextPosition(SwingConstants.CENTER);
		encrypt.addActionListener(this);
		menuBar.add(encrypt);
		
		decrypt = new JMenuItem("Decrypt");
		decrypt.setHorizontalTextPosition(SwingConstants.CENTER);
		decrypt.addActionListener(this);
		menuBar.add(decrypt);
		
		logOut = new JMenuItem("LogOut");
		logOut.setHorizontalTextPosition(SwingConstants.CENTER);
		logOut.addActionListener(this);
		menuBar.add(logOut);
		
		JLabel chooseFileLabel = new JLabel("Choose File");
		chooseFileLabel.setBounds(96, 113, 75, 22);
		contentPane.add(chooseFileLabel);
		
		encryptButton = new JButton("Encrypt");
		encryptButton.setBounds(135, 193, 89, 23);
		encryptButton.addActionListener(this);
		contentPane.add(encryptButton);
		
		browseFile = new JButton("Browse File");
		browseFile.setBounds(183, 113, 106, 23);
		browseFile.addActionListener(this);
		contentPane.add(browseFile);
		
		textField = new JTextField();
		textField.setBounds(183, 82, 106, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel fileName = new JLabel("File Name");
		fileName.setBounds(96, 82, 75, 20);
		contentPane.add(fileName);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == browseFile) {
			
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(this);
			if(i==JFileChooser.APPROVE_OPTION){  
				f=fc.getSelectedFile();  
				filepath=f.getPath();
				path = f.getParent();
				filename = f.getName();
				System.out.println("choosed file :"+ " " + filepath + "\nfilename:" + " " + filename);
				System.out.println("path:" + path );
		        textField.setText(filename);
			}  
		}
		
		else if(e.getSource() == encryptButton) {
			
			System.out.println("processing to encrypt");
			
			String Filename = textField.getText();
			String FileLocation = filepath;
			String EncryptedFilename = filename+".encrypted";
			String key = "SecureFile";
//			SecretKey skey = Secretkey.generateSecretKey();
//			System.out.println("Key:" + skey);
//			String key = Secretkey.keyToString(skey);
//			System.out.println("key into string:" + key);
			try {
			
			AESFileEncryption.encryptMethod(path,key,filename);
			
			Connection con = connection.connect();
			PreparedStatement ps =  con.prepareStatement("insert into Fdata values(?,?,?,?)");
			ps.setString(1, Filename);
			ps.setString(2, FileLocation);
			ps.setString(3, key);
			ps.setString(4, EncryptedFilename);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dispose();
				homePage hp = new homePage();
				hp.setVisible(true);
				JOptionPane.showMessageDialog(encryptButton, "You have successfully encrypted");
				System.out.println("Inserted values in database");
		
			}else {
				JOptionPane.showMessageDialog(encryptButton, "Oops! Something went wrong");
			}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}

			
		}
	
		else {
			JMenuItem menuItem = (JMenuItem)e.getSource();
			
			try {	
				
				if(menuItem == logOut) {
					dispose();
					signinPage lp = new signinPage();
					lp.setVisible(true);
				}
				else if(menuItem == encrypt) {
					dispose();
					encrypt ep = new encrypt();
					ep.setVisible(true);
				}
				else if(menuItem == decrypt) {
					dispose();
					decrypt dp = new decrypt();
					dp.setVisible(true);
				}
				else if(menuItem == about) {
					dispose();
					homePage ab = new homePage();
					ab.setVisible(true);
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
		}
		
	}
}
