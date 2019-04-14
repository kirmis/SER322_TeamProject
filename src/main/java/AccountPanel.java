package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

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
    private JLabel lblCardInformation;
    private JLabel lblAccount;
    private JLabel lblPlatforms;
    private JLabel lblCardType;
    private JLabel lblCardNumber;
    private JLabel lblSecurityCode;
    private JLabel lblExpirationDate;
    private JLabel lblAccountType;
    private JLabel lblUsername;
    private JButton btnBack;
    private JButton btnUpgradeAccount;
    private JButton btnUpdateCardInformation;
    private JButton btnAddPlatform;
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
        
        lblCardInformation = new JLabel("Card information");
        lblCardInformation.setForeground(Color.LIGHT_GRAY);
        lblCardInformation.setFont(new Font("Optima", Font.PLAIN, 18));
        lblCardInformation.setBounds(27, 310, 160, 23);
        add(lblCardInformation);
        
        lblAccount = new JLabel("Account");
        lblAccount.setForeground(Color.LIGHT_GRAY);
        lblAccount.setFont(new Font("Optima", Font.PLAIN, 18));
        lblAccount.setBounds(27, 185, 160, 23);
        add(lblAccount);
        
        lblPlatforms = new JLabel("Platforms");
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
        
        lblCardType = new JLabel("Card type: ");
        lblCardType.setForeground(Color.LIGHT_GRAY);
        lblCardType.setFont(new Font("Optima", Font.PLAIN, 16));
        lblCardType.setBounds(37, 345, 98, 23);
        add(lblCardType);
        
        lblCardNumber = new JLabel("Card number: ");
        lblCardNumber.setForeground(Color.LIGHT_GRAY);
        lblCardNumber.setFont(new Font("Optima", Font.PLAIN, 16));
        lblCardNumber.setBounds(37, 372, 98, 23);
        add(lblCardNumber);
        
        lblSecurityCode = new JLabel("Security code: ");
        lblSecurityCode.setForeground(Color.LIGHT_GRAY);
        lblSecurityCode.setFont(new Font("Optima", Font.PLAIN, 16));
        lblSecurityCode.setBounds(37, 399, 114, 23);
        add(lblSecurityCode);
        
        lblExpirationDate = new JLabel("Expiration date: ");
        lblExpirationDate.setForeground(Color.LIGHT_GRAY);
        lblExpirationDate.setFont(new Font("Optima", Font.PLAIN, 16));
        lblExpirationDate.setBounds(37, 426, 114, 23);
        add(lblExpirationDate);
        
        lblAccountType = new JLabel("Account type: ");
        lblAccountType.setForeground(Color.LIGHT_GRAY);
        lblAccountType.setFont(new Font("Optima", Font.PLAIN, 16));
        lblAccountType.setBounds(37, 220, 119, 23);
        add(lblAccountType);
        
        lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.LIGHT_GRAY);
        lblUsername.setFont(new Font("Optima", Font.PLAIN, 16));
        lblUsername.setBounds(37, 246, 119, 23);
        add(lblUsername);
        
        btnUpgradeAccount = new JButton("Upgrade account");
        btnUpgradeAccount.setBounds(534, 184, 139, 29);
        btnUpgradeAccount.setForeground(new Color(0, 102, 255));
        add(btnUpgradeAccount);
        
        btnUpdateCardInformation = new JButton("Update card information");
        btnUpdateCardInformation.setBounds(492, 309, 181, 29);
        btnUpdateCardInformation.setForeground(new Color(0, 102, 255));
        btnUpdateCardInformation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog cardDlg = new JDialog();
                
                JPanel cardPanel = new JPanel(new GridBagLayout());
                cardPanel.setBounds(0, 0, 300, 300);
                cardPanel.setBackground(Color.DARK_GRAY);
                GridBagConstraints cs = new GridBagConstraints();

                JLabel lblCardType = new JLabel("Card type:");
                lblCardType.setFont(new Font("Optima", Font.PLAIN, 16));
                lblCardType.setForeground(new Color(0, 102, 255));
                cs.gridx = 0;
                cs.gridy = 0;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(lblCardType, cs);

                JSpinner cardSpinner = new JSpinner();
                String [] cards = {"Debit", "Credit"};
                SpinnerListModel cardModel = new SpinnerListModel(cards);
                cardSpinner.setModel(cardModel);
                cardSpinner.setMinimumSize(new Dimension(80, 0));
                
                cs = new GridBagConstraints();
                cs.gridx = 1;
                cs.gridy = 0;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(cardSpinner, cs);
                
                JLabel lblCardNum = new JLabel("Card number:");
                lblCardNum.setFont(new Font("Optima", Font.PLAIN, 16));
                lblCardNum.setForeground(new Color(0, 102, 255));
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 1;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(lblCardNum, cs);
                
                JTextField tfCardNum = new JTextField();
                tfCardNum.setMinimumSize(new Dimension(80, 0));
                cs = new GridBagConstraints();
                cs.gridx = 1;
                cs.gridy = 1;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(tfCardNum, cs);
                
                JLabel lblSecurityCode = new JLabel("Security code:");
                lblCardNum.setFont(new Font("Optima", Font.PLAIN, 16));
                lblCardNum.setForeground(new Color(0, 102, 255));
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 2;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(lblSecurityCode, cs);
                
                JTextField tfSecurityCode = new JTextField();
                tfSecurityCode.setMinimumSize(new Dimension(80, 0));
                cs = new GridBagConstraints();
                cs.gridx = 1;
                cs.gridy = 2;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(tfSecurityCode, cs);
                
                JButton btnUpdate = new JButton("Update");
                btnUpdate.setForeground(new Color(0, 102, 255));
                btnUpdate.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                });
                
                cs = new GridBagConstraints();
                cs.gridx = 1;
                cs.gridy = 3;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(btnUpdate, cs);
                
                cardDlg.setBounds(0, 0, 300, 300);
                cardDlg.setLocationRelativeTo(parentPanel);
                
                cardDlg.add(cardPanel);
                cardDlg.setVisible(true);
            }
        });
        add(btnUpdateCardInformation);
        
        btnAddPlatform = new JButton("Add platform");
        btnAddPlatform.setBounds(534, 479, 139, 29);
        btnAddPlatform.setForeground(new Color(0, 102, 255));
        btnAddPlatform.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog platformDlg = new JDialog();
                
                JPanel platformPanel = new JPanel(new GridBagLayout());
                platformPanel.setBounds(0, 0, 300, 300);
                platformPanel.setBackground(Color.DARK_GRAY);
                GridBagConstraints cs = new GridBagConstraints();

                JLabel lblChoosePlatform = new JLabel("Choose a platform:");
                lblChoosePlatform.setFont(new Font("Optima", Font.PLAIN, 16));
                lblChoosePlatform.setForeground(new Color(0, 102, 255));
                cs.gridx = 0;
                cs.gridy = 0;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                platformPanel.add(lblChoosePlatform, cs);

                JScrollPane scrollPane = new JScrollPane();
                String [] platforms = {"Xbox One", "Playstation 4", "Nintendo Switch", "PC"};
                DefaultListModel<String> platformListModel = new DefaultListModel<String>();
                for(int i = 0; i < platforms.length; i++)
                    platformListModel.addElement(platforms[i]);
                JList platformList = new JList(platformListModel);
                platformList.setPreferredSize(new Dimension(200, 100));
                scrollPane.setViewportView(platformList);
                
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 1;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                platformPanel.add(scrollPane, cs);
                
                JButton btnAdd = new JButton("Add");
                btnAdd.setForeground(new Color(0, 102, 255));
                btnAdd.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int index = platformList.getSelectedIndex();
                        String platform = platformListModel.getElementAt(index).toString();
                        
                        if(dbMgr.checkUserPlatform(username, platform) == true) {
                            JOptionPane.showMessageDialog(parentPanel,
                                    "You already own this platform.",
                                    "Platform not added",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            dbMgr.addPlatformToUser(username, platform);
                            platformDlg.dispose();
                            JOptionPane.showMessageDialog(parentPanel,
                                    "Platform added successfully.",
                                    "Platform added",
                                    JOptionPane.INFORMATION_MESSAGE);
                            refreshPlatformList();
                        }
                    }
                });
                
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 2;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                platformPanel.add(btnAdd, cs);
                
                platformDlg.setBounds(0, 0, 300, 300);
                platformDlg.setLocationRelativeTo(parentPanel);
                
                platformDlg.add(platformPanel);
                platformDlg.setVisible(true);
            }
        });
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
