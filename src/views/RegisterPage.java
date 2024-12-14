package views;

import controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private UserController userController;

    public RegisterPage() {
        // Controller nesnesi
        userController = new UserController();

        // Frame ayarları
        setTitle("Register Page");
        setSize(450, 400); // Yüksekliği artırdık, "Login" butonu için
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel("Create Your Account");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Kayıt Paneli
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // İç boşluk
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Kullanıcı Adı
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        // Şifre
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // E-posta
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailField, gbc);

        // Kayıt Butonu
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(50, 205, 50));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);

        // Login Butonu
        JButton loginButton = new JButton("Back to Login");
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(loginButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Register butonu işlevi
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        // Login butonu işlevi
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Bu sayfayı kapat
                LoginPage loginPage = new LoginPage(); // Login sayfasına yönlendir
                loginPage.setVisible(true);
            }
        });

        setLocationRelativeTo(null); // Ortada açılmasını sağlar
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isRegistered = userController.register(username, password, email);

        if (isRegistered) {
            JOptionPane.showMessageDialog(this, "Kayıt Başarılı! Giriş Sayfasına Yönlendiriliyor...");
            dispose(); // Bu sayfayı kapat
            LoginPage loginPage = new LoginPage(); // Giriş sayfasına yönlendir
            loginPage.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
        });
    }
}
