package main.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.LineBorder;

public class LoginDialog extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton createUser;
    private boolean succeeded;
    private Frame parent;
    private String username;

    public LoginDialog(Frame parent, DatabaseManager dbmgr) {
        super(parent, "Login", true);
        System.out.println("debug");
        username = null;
        this.parent = parent;
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (dbmgr.login(tfUsername.getText().trim(), new String(pfPassword.getPassword()))) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Hi " + tfUsername.getText().trim() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    username = tfUsername.getText().trim();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset password
                    pfPassword.setText("");
                    succeeded = false;
                }
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        createUser = new JButton("Create User");
        createUser.addActionListener(new ActionListener() {

            //getting username and passwored after create user button was pressed
            public void actionPerformed(ActionEvent e) {
                if(true) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Please enter a username and password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean teacher = false;
                int selection = JOptionPane.showConfirmDialog(LoginDialog.this,
                        "Is the new user a Teacher?");
                ////////////////////If yes, then create a user that is a teacher////////////////////////////              
                if (selection == 0) {
                    teacher = true;
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Successfully created new teacher " + " please log in again",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                //If cancel, then close dialog box
                else if(selection == 2) {
                    return;
                }

                else if(selection != 1) {
                    return;
                }

                /////////////////////If no, then create user that is a student/////////////////////////////
                if (selection != 0) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Successfully created new user " + " please log in again",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Username is taken, please enter a new username",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel bp = new JPanel();
        GroupLayout layout = new GroupLayout(bp);
        bp.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(btnLogin)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createUser)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel)
                );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btnLogin)
                        .addComponent(btnCancel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(createUser))
                );

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return username;
    }
}

