package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {
    public HomePage(String userType) {
        // Frame ayarları
        setTitle("Home Page - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel("Kütüphane Yönetim Sistemi");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Hoş Geldiniz Mesajı
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        if (userType.equals("staff")) {
            welcomeLabel.setText("Hoş geldiniz, Personel!");
        } else if (userType.equals("student")) {
            welcomeLabel.setText("Hoş geldiniz, Öğrenci!");
        } else {
            welcomeLabel.setText("Hoş geldiniz!");
        }
        add(welcomeLabel, BorderLayout.CENTER);

        // Tanıtım Metni
        JTextArea descriptionArea = new JTextArea(
            "Kütüphane Yönetim Sistemi, kütüphane işlemlerini kolaylaştırmak için geliştirilmiştir. " +
            "Bu sistem, kitapların yönetimi, öğrenci ve personel bilgileri, envanter takibi ve bildirim gönderme gibi " +
            "birçok özelliği içerir. Kullanıcılar, ihtiyaçlarına göre bu özelliklerden yararlanabilir."
        );
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(descriptionArea, BorderLayout.CENTER);

        // Buton Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Butonlar
        JButton booksButton = new JButton("Kitaplar");
        JButton studentsButton = new JButton("Öğrenciler-Personeller");
        JButton inventoryButton = new JButton("Envanter");
        JButton notificationsButton = new JButton("Bildirim Gönder");
        
        // Uzatılmış çıkış butonu
        JButton logoutButton = new JButton("Çıkış");
        logoutButton.setPreferredSize(new Dimension(600, 50)); // Buton boyutunu ayarla
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER); // Yazıyı ortala

        // Buton işlevleri
        booksButton.addActionListener(e -> {
            dispose(); // Mevcut HomePage ekranını kapat
            BooksPage booksPage = new BooksPage(); // BooksPage sayfasını oluştur
            booksPage.setVisible(true); // BooksPage'i görünür yap
        });
        studentsButton.addActionListener(e -> {
            dispose(); // HomePage ekranını kapat
            StudentsPage studentsPage = new StudentsPage(); // StudentsPage ekranını başlat
            studentsPage.setVisible(true); // StudentsPage'i görünür yap
        });
        inventoryButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Envanter ekranına yönlendiriliyorsunuz."));
        notificationsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Bildirim gönderme ekranına yönlendiriliyorsunuz."));
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Çıkmak istediğinize emin misiniz?", "Çıkış", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Ana sayfayı kapat
                LoginPage loginPage = new LoginPage(); // Login sayfasına geri dön
                loginPage.setVisible(true);
            }
        });

        // Kullanıcı tipine göre butonları gösterme
        if (userType.equals("staff")) {
            buttonPanel.add(booksButton);
            buttonPanel.add(studentsButton);
            buttonPanel.add(inventoryButton);
            buttonPanel.add(notificationsButton);
        } else if (userType.equals("student")) {
            buttonPanel.add(booksButton);
        }

        buttonPanel.add(logoutButton); // Uzatılmış çıkış butonu her iki tür için de görünür

        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null); // Ortada açılmasını sağlar
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage("staff"); // Test için "staff" veya "student" girin
            homePage.setVisible(true);
        });
    }
}
