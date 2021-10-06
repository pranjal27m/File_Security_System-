package project.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class decrypt extends JFrame implements ActionListener{

	JMenuItem logOut,encrypt,decrypt,about;
	private JPanel contentPane;
	private JTextField textField;
	private JButton decryptButton,choosefile;
	private File f; 
	private String filepath,filename;
	private String path;
	private String Encryptedfilename;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					decrypt frame = new decrypt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public decrypt() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File Security");
		lblNewLabel.setFont(new Font("Parchment", Font.PLAIN, 32));
		lblNewLabel.setBounds(125, 298, 106, 22);
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
		chooseFileLabel.setBounds(79, 132, 75, 22);
		contentPane.add(chooseFileLabel);
		
		choosefile = new JButton("Choose File");
		choosefile.setBounds(164, 132, 106, 23);
		choosefile.addActionListener(this);
		contentPane.add(choosefile);
		
		JLabel file = new JLabel("Encrypted File");
		file.setBounds(70, 94, 82, 22);
		contentPane.add(file);
		
		textField = new JTextField();
		textField.setBounds(164, 94, 106, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		decryptButton = new JButton("Decrypt");
		decryptButton.setBounds(121, 186, 89, 23);
		decryptButton.addActionListener(this);
		contentPane.add(decryptButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == choosefile) {
			
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(this);
			if(i==JFileChooser.APPROVE_OPTION){  
				f=fc.getSelectedFile();  
				filepath=f.getPath();
				path= f.getParent();
				Encryptedfilename = f.getName();
				System.out.println("choosed file :"+ " " + filepath + "\nfilename:"+ Encryptedfilename);
				System.out.println("path:" + path);
		        textField.setText(Encryptedfilename);
			}  
		}
	
		else if(e.getSource() == decryptButton) {
	
			System.out.println("processing to decrypt");	
			String EncryptedFile = textField.getText();
			try {
			
			Connection con = connection.connect();
			PreparedStatement ps =  con.prepareStatement("select key from Fdata where EncryptedFile=?");
			//PreparedStatement ps2 =  con.prepareStatement("delete from Fdata where EncryptedFile=?");
			ps.setString(1, EncryptedFile);
			ResultSet rs = ps.executeQuery();
			System.out.println("Query executed");
			//ResultSet rs2 = ps2.executeQuery();
			if(rs.next()) {
				String key = rs.getString("key");
				System.out.println("key:"+ key);
//				SecretKey originalkey = Secretkey.decodeKeyFromString(varkey);
//				System.out.println("originalkey"+originalkey);
//				String finalkey =  Secretkey.keyToString(originalkey);
//				System.out.println("decoded key:"+finalkey);
				AESFileDecryption.decryptMethod(Encryptedfilename,path,key);
				dispose();
				homePage hp = new homePage();
				hp.setVisible(true);
				JOptionPane.showMessageDialog(decryptButton, "You have successfully decrypted");
				System.out.println("deleted values from database");
		
			}else {
				JOptionPane.showMessageDialog(decryptButton, "Oops! Something went wrong");
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
	
	}}
}
