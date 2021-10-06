package project.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class homePage extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	JMenuItem logOut,encrypt,decrypt,about;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePage frame = new homePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public homePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 360);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File Security");
		lblNewLabel.setFont(new Font("Parchment", Font.PLAIN, 32));
		lblNewLabel.setBounds(130, 288, 106, 22);
		contentPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setPreferredSize(new Dimension(0, 0));
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
		
		JTextArea textblock = new JTextArea();
		textblock.setDisabledTextColor(Color.BLACK);
		textblock.setBackground(SystemColor.control);
		textblock.setFont(new Font("Parchment", Font.PLAIN, 30));
		textblock.setEnabled(false);
		textblock.setEditable(false);
		textblock.setLineWrap(true);
		textblock.setText("Presenting you the secure way to store your file easily and access it whenever required in safe and efficient way ");
		textblock.setBounds(23, 89, 305, 107);
		contentPane.add(textblock);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			JMenuItem menuItem = (JMenuItem)e.getSource();
			
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
