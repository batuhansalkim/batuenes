package views;

import javax.swing.*;

import controller.UserController;

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
        setTitle("Kayıt Ol Sayfası");
        setSize(450, 400); // Yüksekliği artırdık, "Login" butonu için
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel("Hesap Oluşturun");
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
        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        // Şifre
        JLabel passwordLabel = new JLabel("Şifre:");
        passwordField = new JPasswordField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // E-posta
        JLabel emailLabel = new JLabel("E-Posta:");
        emailField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailField, gbc);

        // Kayıt Butonu
        JButton registerButton = new JButton("Kayıt Ol");
        registerButton.setBackground(new Color(50, 205, 50));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);

        // Login Butonu
        JButton loginButton = new JButton("Girişe Dön");
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
            showCustomDialog("Hata!", "Lütfen tüm alanları doldurun!", new Color(255, 69, 0));
            return;
        }

        boolean isRegistered = userController.register(username, password, email);

        if (isRegistered) {
            showCustomDialog("Başarılı!", "Kayıt başarılı! Giriş sayfasına yönlendiriliyorsunuz...", new Color(50, 205, 50));
            dispose(); // Bu sayfayı kapat
            LoginPage loginPage = new LoginPage(); // Giriş sayfasına yönlendir
            loginPage.setVisible(true);
        } else {
            showCustomDialog("Hata!", "Kayıt başarısız! Lütfen tekrar deneyin.", new Color(255, 69, 0));
        }
    }

    private void showCustomDialog(String title, String message, Color color) {
        // Özel hata/bilgi penceresi
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(350, 150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        // Başlık paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(color);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        dialog.add(headerPanel, BorderLayout.NORTH);

        // Mesaj paneli
        JPanel messagePanel = new JPanel();
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messagePanel.add(messageLabel);
        dialog.add(messagePanel, BorderLayout.CENTER);

        // Kapat butonu
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Tamam");
        closeButton.setBackground(new Color(70, 130, 180));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Ortada göster
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
        });
    }
}