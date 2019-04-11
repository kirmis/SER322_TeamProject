package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JList gamesList;
	private JLabel lblYourGames;
	private JLabel lblUserTitle;
	private JLabel lblTheArmory;
	private JButton btnSearch;
	
	private DefaultListModel<String> gamesListModel;
	
	private DatabaseManager dbMgr;
	
	private String username;
	private JTextPane currentGamePane;

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
		dbMgr = new DatabaseManager(); // initializing database manager
		
		username = "mramirez"; // need login
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBounds(100, 100, 700, 700);
		contentPane.setBackground(new Color(0, 204, 153));
		
		lblTheArmory = new JLabel("The Armory");
		lblTheArmory.setForeground(new Color(0, 102, 255));
		lblTheArmory.setBounds(6, 6, 430, 100);
		lblTheArmory.setFont(new Font("Optima", Font.BOLD, 82));
		contentPane.add(lblTheArmory);
		
		btnSearch = new JButton("Search Games");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(566, 116, 117, 29);
		contentPane.add(btnSearch);
		
		lblUserTitle = new JLabel("Username - rank");
		lblUserTitle.setBounds(16, 115, 106, 16);
		contentPane.add(lblUserTitle);
		
		lblYourGames = new JLabel("Your Games");
		lblYourGames.setFont(new Font("Lucida Grande", Font.PLAIN, 46));
		lblYourGames.setBounds(28, 156, 291, 45);
		contentPane.add(lblYourGames);
		
		currentGamePane = new JTextPane();
		currentGamePane.setBounds(341, 213, 342, 227);
		contentPane.add(currentGamePane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 213, 303, 445);
		contentPane.add(scrollPane);
		
		gamesListModel = new DefaultListModel<String>();
		gamesList = new JList();
		scrollPane.setViewportView(gamesList);
		this.refreshGameList();
		gamesList.addListSelectionListener(new ListSelectionListener () {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = gamesList.getSelectedIndex();
				String gameName = gamesListModel.getElementAt(index).toString();
				currentGamePane.setText(dbMgr.getGameInfoByName(gameName));
			}
		});
		
		frame.getContentPane().add(contentPane);
	}
	
	public void refreshGameList() {
		gamesListModel.clear();
		List<String> userGames = dbMgr.getGameTitles(username);
		
		for (int i = 0; i < userGames.size(); i++) {
			gamesListModel.addElement(userGames.get(i)); 
		}
		
		gamesList.setModel(gamesListModel);
	}
}
