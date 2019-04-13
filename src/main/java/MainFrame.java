package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author Alper Mencek, Manolito Ramirez, and Ryan Kirmis
 * @version 1.0.0
 */

public class MainFrame {

    private JFrame frame;
    private JPanel contentPane;
    private JPanel searchPane;
    private JList gamesList;
    private JList gamesSearchList;
    private JList reviewList;
    private JLabel lblYourGames;
    private JLabel lblUserTitle;
    private JLabel lblTheArmory;
    private JLabel lblReviews;
    private JButton btnSearch;
    private JButton btnBuy;

    private DefaultListModel<String> gamesListModel;
    private DefaultListModel<String> gamesSearchListModel;
    private DefaultListModel<String> reviewListModel;

    private DatabaseManager dbMgr;

    private String username;
    private String rank;
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
        dbMgr = new DatabaseManager();
        LoginDialog login = new LoginDialog(frame, dbMgr);
        login.setVisible(true);

        username = login.getUsername(); // initializing database manager
        rank = getRank();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 700, 700);
        contentPane.setBackground(Color.DARK_GRAY);

        searchPane = new JPanel();
        searchPane.setLayout(null);
        searchPane.setBounds(0, 0, 700, 700);
        searchPane.setBackground(Color.DARK_GRAY);
        searchPane.setVisible(false);

        lblTheArmory = new JLabel("The Armory");
        lblTheArmory.setForeground(new Color(0, 102, 255));
        lblTheArmory.setBounds(6, 6, 460, 100);
        lblTheArmory.setFont(new Font("Optima", Font.BOLD, 82));
        contentPane.add(lblTheArmory);

        btnSearch = new JButton("Search Games");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPane.setVisible(true);
                contentPane.setVisible(false);
            }
        });
        btnSearch.setBounds(566, 116, 122, 29);
        contentPane.add(btnSearch);

        lblUserTitle = new JLabel(username + " - " + rank);
        lblUserTitle.setForeground(Color.LIGHT_GRAY);
        lblUserTitle.setFont(new Font("Lao MN", Font.PLAIN, 16));
        lblUserTitle.setBounds(16, 115, 200, 16);
        contentPane.add(lblUserTitle);

        lblYourGames = new JLabel("Your Games");
        lblYourGames.setForeground(Color.LIGHT_GRAY);
        lblYourGames.setFont(new Font("Lao MN", Font.PLAIN, 46));
        lblYourGames.setBounds(28, 167, 291, 45);
        contentPane.add(lblYourGames);

        lblReviews = new JLabel("Reviews:");
        lblReviews.setForeground(Color.LIGHT_GRAY);
        lblReviews.setFont(new Font("Lao MN", Font.PLAIN, 30));
        lblReviews.setBounds(351, 465, 291, 45);
        contentPane.add(lblReviews);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(16, 213, 303, 445);
        contentPane.add(scrollPane);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(341, 213, 342, 227);
        contentPane.add(scrollPane2);

        currentGamePane = new JTextPane();
        scrollPane2.setViewportView(currentGamePane);
        currentGamePane.setFont(new Font("Lao MN", Font.PLAIN, 16));
        currentGamePane.setEditable(false);
        currentGamePane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        gamesListModel = new DefaultListModel<String>();
        gamesList = new JList();
        scrollPane.setViewportView(gamesList);
        this.refreshGameList();
        gamesList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        gamesList.addListSelectionListener(new ListSelectionListener () {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = gamesList.getSelectedIndex();
                String gameName = gamesListModel.getElementAt(index).toString();
                List<String> gameInfo = dbMgr.getGameInfoByName(gameName);
                List<String> pubInfo = dbMgr.getPublisherByGame(gameInfo.get(0));
                String gameText = gameInfo.get(1) + "\n\nGame ID: " + gameInfo.get(0)
                + "\nRelease date: " + gameInfo.get(2) + "\nReview release date: "
                + gameInfo.get(3) + "\nPrice: $" + gameInfo.get(4) + "\nPublisher: "
                + pubInfo.get(1) + "\n\f\fLocation: " + pubInfo.get(2);
                currentGamePane.setText(gameText);
                refreshReviewList();
            }
        });

        JScrollPane scrollPane4 = new JScrollPane();
        scrollPane4.setBounds(16, 213, 303, 445);
        searchPane.add(scrollPane4);

        JScrollPane scrollPane5 = new JScrollPane();
        scrollPane5.setBounds(341, 213, 342, 227);
        searchPane.add(scrollPane5);

        scrollPane5.setViewportView(currentGamePane);

        gamesSearchListModel = new DefaultListModel<String>();
        gamesSearchList = new JList();
        scrollPane4.setViewportView(gamesSearchList);
        this.refreshGameSearchList();

        gamesSearchList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        gamesSearchList.addListSelectionListener(new ListSelectionListener () {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = gamesSearchList.getSelectedIndex();
                String gameName = gamesSearchListModel.getElementAt(index).toString();
                List<String> gameInfo = dbMgr.getGameInfoByName(gameName);
                List<String> pubInfo = dbMgr.getPublisherByGame(gameInfo.get(0));
                String gameText = gameInfo.get(1) + "\n\nGame ID: " + gameInfo.get(0)
                + "\nRelease date: " + gameInfo.get(2) + "\nReview release date: "
                + gameInfo.get(3) + "\nPrice: $" + gameInfo.get(4) + "\nPublisher: "
                + pubInfo.get(1) + "\n\f\fLocation: " + pubInfo.get(2);
                currentGamePane.setText(gameText);
                refreshSearchReviewList();
            }
        });

        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setBounds(341, 503, 342, 155);
        contentPane.add(scrollPane3);

        JScrollPane scrollPane6 = new JScrollPane();
        scrollPane6.setBounds(341, 503, 342, 155);
        searchPane.add(scrollPane6);

        btnBuy = new JButton("Buy");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float balance = dbMgr.userBalance(username);
                float price = dbMgr.gamePrice(getGame(gamesSearchList, gamesSearchListModel));
                if(balance > price) {
                    int selection = JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to buy this game\n resulting price is " + (balance - price) + "?");
                    if(selection == 0) {
                        dbMgr.updateBalance(username, balance-price);
                        dbMgr.addGameToUser(username, dbMgr.getGameID(getGame(gamesSearchList, gamesSearchListModel)));
                    }
                }

                else {
                    JOptionPane.showMessageDialog(frame,
                            "Your funds are not sufficient, please add more funds to your wallet",
                            "Insufficient Funds",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        btnBuy.setBounds(566, 116, 122, 29);
        searchPane.add(btnBuy);

        reviewListModel = new DefaultListModel<String>();
        reviewList = new JList();
        scrollPane3.setViewportView(reviewList);
        scrollPane6.setViewportView(reviewList);
        reviewList.setFont(new Font("Lao MN", Font.PLAIN, 12));
        reviewList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        frame.getContentPane().add(contentPane);
        frame.getContentPane().add(searchPane);
    }

    public String getGame(JList games, DefaultListModel<String> gamesModel) {
        int index = games.getSelectedIndex();
        String gameName = gamesModel.getElementAt(index).toString();
        return gameName;
    }

    /**
     * Refresh the list of the user's games.
     */
    public void refreshGameList() {
        gamesListModel.clear();
        List<String> userGames = dbMgr.getGameTitles(username);

        // fill list with user games
        for (int i = 0; i < userGames.size(); i++) {
            gamesListModel.addElement(userGames.get(i)); 
        }

        gamesList.setModel(gamesListModel);
    }

    /**
     * Refresh the list of all games.
     */
    public void refreshGameSearchList() {
        gamesSearchListModel.clear();
        List<String> games = dbMgr.getAllGameTitles(username);
        List<String> gamesOwned = dbMgr.getGameTitles(username);

        // fill list with user games
        for (int i = 0; i < games.size(); i++) {
            if(!gamesOwned.contains(games.get(i)))
                gamesSearchListModel.addElement(games.get(i)); 
        }

        gamesSearchList.setModel(gamesSearchListModel);
    }

    /**
     * Refresh the list of the user's reviews.
     */
    public void refreshReviewList() {
        reviewListModel.clear();
        int index = gamesList.getSelectedIndex();
        String gameName = gamesListModel.getElementAt(index).toString();
        String gameID = dbMgr.getGameID(gameName);
        List<List<String>> userReviews = dbMgr.getReviewsByGame(gameID);

        // fill list with review for currently selected game
        for (int i = 0; i < userReviews.size(); i++) {
            reviewListModel.addElement(userReviews.get(i).get(0) + ", " + userReviews.get(i).get(1) + "/10"); 
        }

        reviewList.setModel(reviewListModel);
    }

    /**
     * Refresh the list of the user's reviews.
     */
    public void refreshSearchReviewList() {
        reviewListModel.clear();
        int index = gamesSearchList.getSelectedIndex();
        String gameName = gamesSearchListModel.getElementAt(index).toString();
        String gameID = dbMgr.getGameID(gameName);
        List<List<String>> userReviews = dbMgr.getReviewsByGame(gameID);

        // fill list with review for currently selected game
        for (int i = 0; i < userReviews.size(); i++) {
            reviewListModel.addElement(userReviews.get(i).get(0) + ", " + userReviews.get(i).get(1) + "/10"); 
        }

        reviewList.setModel(reviewListModel);
    }

    public String getRank() {
        if(dbMgr.checkAdmin(username))
            return "Admin";

        else if(dbMgr.checkBannedUser(username))
            return "Banned";

        else if(dbMgr.checkReviewUser(username))
            return "Review User";

        else if(dbMgr.checkPremiumUser(username))
            return "Premium User";

        else 
            return "user";
    }
}
