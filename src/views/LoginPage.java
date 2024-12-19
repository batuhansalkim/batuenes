package views;

import controller.UserController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends AbstractPage {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserController userController;

    public LoginPage() {
        super(); // Calls the constructor of AbstractPage

        // Initialize userController
        userController = new UserController();

        // Login page layout setup
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        // Setup layout for username, password, and buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        registerButton.setBackground(new Color(255, 153, 51));
        registerButton.setForeground(Color.WHITE);
        panel.add(registerButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close login page
                RegisterPage registerPage = new RegisterPage();
                registerPage.setVisible(true); // Show register page
            }
        });

        // Center the frame
        setLocationRelativeTo(null);
    }

    @Override
    protected String getTitleText() {
        return "Login Page"; // Login page title
    }

    @Override
    public void showPage() {
        setVisible(true); // Show the login page
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = userController.login(username, password);

        if (user != null) {
            // User is valid, show appropriate page
            if ("student".equals(user.getUserType())) {
                // If user is a student, open StuBooksPage
                StuBooksPage stuBooksPage = new StuBooksPage();
                stuBooksPage.setVisible(true);
            } else {
                // If user is staff, open HomePage
                HomePage homePage = new HomePage(user.getUserType());
                homePage.setVisible(true);
            }
            dispose(); // Close login page
        } else {
            showErrorDialog("Username or password is incorrect!");
        }
    }

    private void showErrorDialog(String message) {
        // Show error dialog if login fails
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}