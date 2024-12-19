package views;

import mediator.*;
import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    public HomePage(String userType) {
        setTitle("Home Page - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Mediator mediator = new HomePageMediator();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton booksButton = new JButton("Kitaplar");
        JButton loanedBooksButton = new JButton("Ödünç Alınan Kitaplar");
        JButton logoutButton = new JButton("Çıkış");

        // Kitaplar butonunun davranışı
        booksButton.addActionListener(e -> {
            if ("staff".equals(userType)) {
                new BooksPage().setVisible(true); // Staff için BooksPage açılır
            } else if ("student".equals(userType)) {
                new StuBooksPage().setVisible(true); // Student için StuBooksPage açılır
            }
        });

        // Ödünç Alınan Kitaplar butonunun davranışı
        loanedBooksButton.addActionListener(e -> new LoanedBooksPage().setVisible(true));

        // Çıkış butonunun davranışı
        logoutButton.addActionListener(e -> mediator.notify(logoutButton, "click"));

        buttonPanel.add(booksButton);
        buttonPanel.add(logoutButton);

        // Student girişine özel buton ekleme
        if ("student".equals(userType)) {
            buttonPanel.add(loanedBooksButton);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage("student"); // veya "staff" olarak değiştirilebilir
            homePage.setVisible(true);
        });
    }
}
