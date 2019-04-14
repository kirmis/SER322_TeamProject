package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

/**
 * Account panel for user to update account information.
 * 
 * @author Alper Mencek, Manolito Ramirez, and Ryan Kirmis
 * @version 1.0.0
 */
public class AccountPanel extends JPanel {
    private DatabaseManager dbMgr;
    private String username;
    private JPanel parentPanel;
    private JLabel lblTheArmory;
    private JButton btnBack;
    private JList platformList;
    private DefaultListModel<String> platformModel;
    
    /**
     * Sets up account panel.
     * 
     * @param parentPanel the main panel of the application
     * @param dbMgr the database manager
     * @param username the current user's username
     */
    public AccountPanel(JPanel parentPanel, DatabaseManager dbMgr, String username) {
        this.parentPanel = parentPanel;
        this.username = username;
        this.dbMgr = dbMgr;
        initialize();
    }
    
    /**
     * Initializing panel UI elements.
     */
    public void initialize() {
        this.setLayout(null);
        this.setBounds(0, 0, 700, 700);
        this.setBackground(Color.DARK_GRAY);
        
        lblTheArmory = new JLabel("The Armory");
        lblTheArmory.setForeground(new Color(0, 102, 255));
        lblTheArmory.setBounds(6, 6, 460, 100);
        lblTheArmory.setFont(new Font("Optima", Font.BOLD, 82));
        this.add(lblTheArmory);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parentPanel.setVisible(true);
            }
        });
        btnBack.setBounds(16, 116, 122, 29);
        btnBack.setForeground(new Color(0, 102, 255));
        this.add(btnBack);
        
        JLabel lblCardInformation = new JLabel("Card information");
        lblCardInformation.setForeground(Color.LIGHT_GRAY);
        lblCardInformation.setFont(new Font("Optima", Font.PLAIN, 18));
        lblCardInformation.setBounds(27, 310, 160, 23);
        add(lblCardInformation);
        
        JLabel lblAccount = new JLabel("Account");
        lblAccount.setForeground(Color.LIGHT_GRAY);
        lblAccount.setFont(new Font("Optima", Font.PLAIN, 18));
        lblAccount.setBounds(27, 185, 160, 23);
        add(lblAccount);
        
        JLabel lblPlatforms = new JLabel("Platforms");
        lblPlatforms.setForeground(Color.LIGHT_GRAY);
        lblPlatforms.setFont(new Font("Optima", Font.PLAIN, 18));
        lblPlatforms.setBounds(27, 480, 160, 23);
        add(lblPlatforms);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 515, 646, 144);
        add(scrollPane);
        
        platformModel = new DefaultListModel<String>();
        platformList = new JList();
        scrollPane.setViewportView(platformList);
        this.refreshPlatformList();
        
        JLabel lblCardType = new JLabel("Card type: ");
        lblCardType.setForeground(Color.LIGHT_GRAY);
        lblCardType.setFont(new Font("Optima", Font.PLAIN, 16));
        lblCardType.setBounds(37, 345, 98, 23);
        add(lblCardType);
        
        JLabel lblCardNumber = new JLabel("Card number: ");
        lblCardNumber.setForeground(Color.LIGHT_GRAY);
        lblCardNumber.setFont(new Font("Optima", Font.PLAIN, 16));
        lblCardNumber.setBounds(37, 372, 98, 23);
        add(lblCardNumber);
        
        JLabel lblSecurityCode = new JLabel("Security code: ");
        lblSecurityCode.setForeground(Color.LIGHT_GRAY);
        lblSecurityCode.setFont(new Font("Optima", Font.PLAIN, 16));
        lblSecurityCode.setBounds(37, 399, 114, 23);
        add(lblSecurityCode);
        
        JLabel lblExpirationDate = new JLabel("Expiration date: ");
        lblExpirationDate.setForeground(Color.LIGHT_GRAY);
        lblExpirationDate.setFont(new Font("Optima", Font.PLAIN, 16));
        lblExpirationDate.setBounds(37, 426, 114, 23);
        add(lblExpirationDate);
        
        JLabel lblAccountType = new JLabel("Account type: ");
        lblAccountType.setForeground(Color.LIGHT_GRAY);
        lblAccountType.setFont(new Font("Optima", Font.PLAIN, 16));
        lblAccountType.setBounds(37, 220, 119, 23);
        add(lblAccountType);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.LIGHT_GRAY);
        lblUsername.setFont(new Font("Optima", Font.PLAIN, 16));
        lblUsername.setBounds(37, 246, 119, 23);
        add(lblUsername);
        
        JButton btnUpgradeAccount = new JButton("Upgrade account");
        btnUpgradeAccount.setBounds(534, 184, 139, 29);
        btnUpgradeAccount.setForeground(new Color(0, 102, 255));
        add(btnUpgradeAccount);
        
        JButton btnUpdateCardInformation = new JButton("Update card information");
        btnUpdateCardInformation.setBounds(492, 309, 181, 29);
        btnUpdateCardInformation.setForeground(new Color(0, 102, 255));
        add(btnUpdateCardInformation);
        
        JButton btnAddPlatform = new JButton("Add platform");
        btnAddPlatform.setBounds(534, 479, 139, 29);
        btnAddPlatform.setForeground(new Color(0, 102, 255));
        add(btnAddPlatform);
    }
    
    /**
     * Refresh the list of the user's platforms.
     */
    public void refreshPlatformList() {
        platformModel.clear();
        List<String> platforms = dbMgr.getUserPlatforms(username);

        // fill list with user games
        for (int i = 0; i < platforms.size(); i++) {
            platformModel.addElement(platforms.get(i)); 
        }

        platformList.setModel(platformModel);
    }
}
