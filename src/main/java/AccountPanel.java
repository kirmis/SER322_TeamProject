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
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
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
    private String rank;
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
    private JLabel lblBalance;
    private JButton btnBack;
    private JButton btnUpgradeAccount;
    private JButton btnUpdateCardInformation;
    private JButton btnAddPlatform;
    private JButton btnAddFunds;
    private JList platformList;
    private DefaultListModel<String> platformModel;
    
    /**
     * Sets up account panel.
     * 
     * @param parentPanel the main panel of the application
     * @param dbMgr the database manager
     * @param username the current user's username
     */
    public AccountPanel(JPanel parentPanel, DatabaseManager dbMgr, String username, String rank) {
        this.parentPanel = parentPanel;
        this.username = username;
        this.rank = rank;
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
        
        lblBalance = new JLabel("Wallet: $" + String.format("%.2f", dbMgr.userBalance(username)));
        lblBalance.setForeground(new Color(0, 102, 255));
        lblBalance.setBounds(500, 6, 200, 80);
        lblBalance.setFont(new Font("Optima", Font.PLAIN, 24));
        this.add(lblBalance);
        
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
        lblCardType.setBounds(37, 345, 514, 23);
        add(lblCardType);
        
        lblCardNumber = new JLabel("Card number: ");
        lblCardNumber.setForeground(Color.LIGHT_GRAY);
        lblCardNumber.setFont(new Font("Optima", Font.PLAIN, 16));
        lblCardNumber.setBounds(37, 372, 514, 23);
        add(lblCardNumber);
        
        lblSecurityCode = new JLabel("Security code: ");
        lblSecurityCode.setForeground(Color.LIGHT_GRAY);
        lblSecurityCode.setFont(new Font("Optima", Font.PLAIN, 16));
        lblSecurityCode.setBounds(37, 399, 514, 23);
        add(lblSecurityCode);
        
        lblExpirationDate = new JLabel("Expiration date: ");
        lblExpirationDate.setForeground(Color.LIGHT_GRAY);
        lblExpirationDate.setFont(new Font("Optima", Font.PLAIN, 16));
        lblExpirationDate.setBounds(37, 426, 514, 23);
        add(lblExpirationDate);
        
        lblAccountType = new JLabel("Account type: " + rank);
        lblAccountType.setForeground(Color.LIGHT_GRAY);
        lblAccountType.setFont(new Font("Optima", Font.PLAIN, 16));
        lblAccountType.setBounds(37, 220, 514, 23);
        add(lblAccountType);
        
        lblUsername = new JLabel("Username: " + username);
        lblUsername.setForeground(Color.LIGHT_GRAY);
        lblUsername.setFont(new Font("Optima", Font.PLAIN, 16));
        lblUsername.setBounds(37, 246, 514, 23);
        add(lblUsername);
        
        btnUpdateCardInformation = new JButton("Update card information");
        btnUpdateCardInformation.setBounds(492, 309, 181, 29);
        btnUpdateCardInformation.setForeground(new Color(0, 102, 255));
        btnUpdateCardInformation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Listener that allows the user to update card information
                
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
                cs.fill = GridBagConstraints.HORIZONTAL;
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
                cs = new GridBagConstraints();
                cs.fill = GridBagConstraints.HORIZONTAL;
                cs.gridx = 1;
                cs.gridy = 1;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 2;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(tfCardNum, cs);
                
                JLabel lblSecurityCode = new JLabel("Security code:");
                lblSecurityCode.setFont(new Font("Optima", Font.PLAIN, 16));
                lblSecurityCode.setForeground(new Color(0, 102, 255));
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 2;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(lblSecurityCode, cs);
                
                JTextField tfSecurityCode = new JTextField();
                cs = new GridBagConstraints();
                cs.fill = GridBagConstraints.HORIZONTAL;
                cs.gridx = 1;
                cs.gridy = 2;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(tfSecurityCode, cs);
                
                JLabel lblExpDate = new JLabel("Expiration date:");
                lblExpDate.setFont(new Font("Optima", Font.PLAIN, 16));
                lblExpDate.setForeground(new Color(0, 102, 255));
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 3;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(lblExpDate, cs);
                
                JSpinner expDate = new JSpinner(new SpinnerDateModel());
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
                expDate.setEditor(new JSpinner.DateEditor(expDate, sdf.toPattern()));
                cs = new GridBagConstraints();
                cs.fill = GridBagConstraints.HORIZONTAL;
                cs.gridx = 1;
                cs.gridy = 3;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(expDate, cs);
                
                JButton btnUpdate = new JButton("Update");
                btnUpdate.setForeground(new Color(0, 102, 255));
                btnUpdate.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String cardType = (String)cardSpinner.getValue();
                        String cardNum = tfCardNum.getText();
                        String securityCode = tfSecurityCode.getText();
                        java.sql.Date expirationDate = new java.sql.Date(((java.util.Date)expDate.getValue()).getTime());
                        dbMgr.updateCardInformation(username, cardType, cardNum, securityCode, expirationDate);
                        JOptionPane.showMessageDialog(parentPanel,
                                "Card information updated successfully.",
                                "Card information updated",
                                JOptionPane.INFORMATION_MESSAGE);
                        cardDlg.dispose();
                        refreshCardInfo();
                    }
                });
                
                cs = new GridBagConstraints();
                cs.gridx = 1;
                cs.gridy = 4;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                cardPanel.add(btnUpdate, cs);
                
                cardDlg.setBounds(0, 0, 300, 300);
                cardDlg.setLocationRelativeTo(parentPanel);
                
                cardDlg.getContentPane().add(cardPanel);
                cardDlg.setVisible(true);
            }
        });
        add(btnUpdateCardInformation);
        
        btnAddPlatform = new JButton("Add platform");
        btnAddPlatform.setBounds(534, 479, 139, 29);
        btnAddPlatform.setForeground(new Color(0, 102, 255));
        btnAddPlatform.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Listener that allows the user to add new game platforms
                
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
                
                platformDlg.getContentPane().add(platformPanel);
                platformDlg.setVisible(true);
            }
        });
        add(btnAddPlatform);
        
        btnAddFunds = new JButton("Add funds");
        btnAddFunds.setForeground(new Color(0, 102, 255));
        btnAddFunds.setBounds(534, 116, 139, 29);
        btnAddFunds.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Listener that allows the user to add funds to their account
                
                JDialog fundsDlg = new JDialog();
                
                JPanel fundsPanel = new JPanel(new GridBagLayout());
                fundsPanel.setBounds(0, 0, 300, 300);
                fundsPanel.setBackground(Color.DARK_GRAY);
                GridBagConstraints cs = new GridBagConstraints();

                JLabel lblChoosePlatform = new JLabel("Enter amount:");
                lblChoosePlatform.setFont(new Font("Optima", Font.PLAIN, 20));
                lblChoosePlatform.setForeground(new Color(0, 102, 255));
                cs.gridx = 0;
                cs.gridy = 0;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                fundsPanel.add(lblChoosePlatform, cs);
                
                JSpinner amountSpinner = new JSpinner();
                String [] amounts = {"$5.00", "$10.00", "$25.00", "$50.00", "$100.00"};
                SpinnerListModel amountModel = new SpinnerListModel(amounts);
                amountSpinner.setModel(amountModel);
                amountSpinner.setMinimumSize(new Dimension(80, 100));
                
                cs = new GridBagConstraints();
                cs.fill = GridBagConstraints.HORIZONTAL;
                cs.gridx = 0;
                cs.gridy = 1;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                fundsPanel.add(amountSpinner, cs);
                
                JButton btnAdd = new JButton("Add funds");
                btnAdd.setForeground(new Color(0, 102, 255));
                btnAdd.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        float balance = dbMgr.userBalance(username);
                        balance += Float.parseFloat(((String)amountSpinner.getValue()).substring(1));
                        dbMgr.updateBalance(username, balance);
                        
                        fundsDlg.dispose();
                        JOptionPane.showMessageDialog(parentPanel,
                                "Funds added successfully.",
                                "Funds added",
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        lblBalance.setText("Wallet: $" + String.format("%.2f", dbMgr.userBalance(username)));
                    }
                });
                
                cs = new GridBagConstraints();
                cs.gridx = 0;
                cs.gridy = 2;
                cs.insets = new Insets(5, 10, 5, 10);
                cs.gridwidth = 1;
                cs.anchor = GridBagConstraints.CENTER;
                fundsPanel.add(btnAdd, cs);
                
                fundsDlg.setBounds(0, 0, 300, 300);
                fundsDlg.setLocationRelativeTo(parentPanel);
                
                fundsDlg.getContentPane().add(fundsPanel);
                fundsDlg.setVisible(true);
            }
        });
        add(btnAddFunds);
        
        refreshCardInfo();
    }
    
    /**
     * Refreshes the user's card information.
     */
    public void refreshCardInfo() {
        List<String> userInfo = dbMgr.getUserInfo(username);
        
        if(userInfo.get(3) != null)
            lblCardType.setText("Card type: " + userInfo.get(3));
        if(userInfo.get(4) != null)
            lblCardNumber.setText("Card number: " + userInfo.get(4));
        if(userInfo.get(5) != null)
            lblSecurityCode.setText("Security code: " + userInfo.get(5));
        if(userInfo.get(6) != null)
            lblExpirationDate.setText("Expiration date: " + userInfo.get(6));
    }
    
    /**
     * Refreshes the list of the user's platforms.
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
