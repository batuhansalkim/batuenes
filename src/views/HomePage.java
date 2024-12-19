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
        JButton studentsButton = new JButton("Öğrenciler-Personeller");
        JButton inventoryButton = new JButton("Envanter");
        JButton notificationsButton = new JButton("Bildirim Gönder");
        JButton logoutButton = new JButton("Çıkış");

        // Kullanıcı türüne göre kitaplar butonunun davranışı
        booksButton.addActionListener(e -> {
            if ("staff".equals(userType)) {
                new BooksPage().setVisible(true); // Staff için BooksPage açılır
            } else if ("student".equals(userType)) {
                new StuBooksPage().setVisible(true); // Student için StuBooksPage açılır
            }
        });

        studentsButton.addActionListener(e -> mediator.notify(studentsButton, "click"));
        inventoryButton.addActionListener(e -> mediator.notify(inventoryButton, "click"));
        notificationsButton.addActionListener(e -> mediator.notify(notificationsButton, "click"));
        logoutButton.addActionListener(e -> mediator.notify(logoutButton, "click"));

        ((HomePageMediator) mediator).setBooksButton(booksButton);
        ((HomePageMediator) mediator).setLogoutButton(logoutButton);

        buttonPanel.add(booksButton);
        buttonPanel.add(logoutButton);

        if ("staff".equals(userType)) {
            ((HomePageMediator) mediator).setStudentsButton(studentsButton);
            ((HomePageMediator) mediator).setInventoryButton(inventoryButton);
            ((HomePageMediator) mediator).setNotificationsButton(notificationsButton);

            buttonPanel.add(studentsButton);
            buttonPanel.add(inventoryButton);
            buttonPanel.add(notificationsButton);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage("staff"); // veya "student" olarak değiştirilebilir
            homePage.setVisible(true);
        });
    }
}
