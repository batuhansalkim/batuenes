package views;

import controllers.UserController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserController userController;

    public LoginPage() {
        // Controller nesnesi
        userController = new UserController();

        // Frame ayarları
        setTitle("Login Page");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Welcome to LMS");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Giriş Paneli
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // İç boşluklar
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        // Username Label ve Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        // Password Label ve Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // Login Butonu
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        panel.add(loginButton, gbc);

        // Register Butonu
        gbc.gridx = 1;
        gbc.gridy = 3;
        registerButton.setBackground(new Color(255, 153, 51));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        panel.add(registerButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Login butonu işlevi
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Register butonu işlevi
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Login sayfasını kapat
                RegisterPage registerPage = new RegisterPage();
                registerPage.setVisible(true); // Register sayfasını aç
            }
        });

        // Frame ortalama
        setLocationRelativeTo(null);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = userController.login(username, password);

        if (user != null) {
            // Kullanıcı doğru, home sayfasına yönlendirme
            HomePage homePage = new HomePage(user.getUserType());
            homePage.setVisible(true);
            dispose();
        } else {
            showErrorDialog("isim veya şifre hatalı!");
        }
    }

    private void showErrorDialog(String message) {
        // Hata penceresi
        JDialog errorDialog = new JDialog(this, "Login Error", true);
        errorDialog.setSize(300, 150);
        errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        errorDialog.setLayout(new BorderLayout());
        errorDialog.setResizable(false);

        // Üst panel (Başlık)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 69, 0));
        JLabel titleLabel = new JLabel("Giriş Hatalı");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        errorDialog.add(headerPanel, BorderLayout.NORTH);

        // Mesaj paneli
        JPanel messagePanel = new JPanel();
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messagePanel.add(messageLabel);
        errorDialog.add(messagePanel, BorderLayout.CENTER);

        // Kapat butonu
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(70, 130, 180));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> errorDialog.dispose());
        buttonPanel.add(closeButton);
        errorDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Ekranın ortasında göster
        errorDialog.setLocationRelativeTo(this);
        errorDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
        });
    }
}
