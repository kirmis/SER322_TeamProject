package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.Box;

public class MainFrame {

	private JFrame frame;
	private DatabaseManager dbMgr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		dbMgr = new DatabaseManager();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 204, 153));
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTheArmory = new JLabel("The Armory");
		lblTheArmory.setForeground(new Color(0, 102, 255));
		lblTheArmory.setBounds(6, 6, 430, 100);
		lblTheArmory.setFont(new Font("Optima", Font.BOLD, 82));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblTheArmory);
		
		JButton btnSearch = new JButton("Search Games");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(566, 116, 117, 29);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblUserTitle = new JLabel("Username - rank");
		lblUserTitle.setBounds(16, 115, 106, 16);
		frame.getContentPane().add(lblUserTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 213, 667, 445);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel games = new DefaultListModel();
		List<String> userGames = dbMgr.getGameTitles("mramirez");
		
		for (int i = 0; i < userGames.size(); i++) {
			games.addElement(userGames.get(i)); 
		}
		
		games.addElement(dbMgr.getGameInfo("Anthem"));
		
		JList listGames = new JList(games);
		scrollPane.setViewportView(listGames);
		
		JLabel lblYourGames = new JLabel("Your Games");
		lblYourGames.setFont(new Font("Lucida Grande", Font.PLAIN, 46));
		lblYourGames.setBounds(28, 156, 291, 45);
		frame.getContentPane().add(lblYourGames);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setForeground(Color.BLACK);
		horizontalStrut.setBounds(6, 102, 687, 12);
		frame.getContentPane().add(horizontalStrut);
	}
}
