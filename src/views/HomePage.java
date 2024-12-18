package views;

import mediator.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    public HomePage(String userType) {
        setTitle("Home Page - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Mediator mediator = new HomePageMediator();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // JButton kullanıyoruz
        JButton booksButton = new JButton("Kitaplar");
        JButton studentsButton = new JButton("Öğrenciler-Personeller");
        JButton inventoryButton = new JButton("Envanter");
        JButton notificationsButton = new JButton("Bildirim Gönder");
        JButton logoutButton = new JButton("Çıkış");

        // Butonlara tıklama aksiyonları ekliyoruz
        booksButton.addActionListener(e -> mediator.notify(booksButton, "click"));
        studentsButton.addActionListener(e -> mediator.notify(studentsButton, "click"));
        inventoryButton.addActionListener(e -> mediator.notify(inventoryButton, "click"));
        notificationsButton.addActionListener(e -> mediator.notify(notificationsButton, "click"));
        logoutButton.addActionListener(e -> mediator.notify(logoutButton, "click"));

        // Mediator'a butonları ekliyoruz
        ((HomePageMediator) mediator).setBooksButton(booksButton);
        ((HomePageMediator) mediator).setStudentsButton(studentsButton);
        ((HomePageMediator) mediator).setInventoryButton(inventoryButton);
        ((HomePageMediator) mediator).setNotificationsButton(notificationsButton);
        ((HomePageMediator) mediator).setLogoutButton(logoutButton);

        buttonPanel.add(booksButton);
        buttonPanel.add(studentsButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(notificationsButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage("staff");
            homePage.setVisible(true);
        });
    }
}
