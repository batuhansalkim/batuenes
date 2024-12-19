package views;

import mediator.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private String userType;

    public HomePage(String userType) {
        this.userType = userType; // Store user type
        setTitle("Home Page - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Mediator mediator = new HomePageMediator();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Buttons
        JButton booksButton = new JButton("Kitaplar");
        JButton studentsButton = new JButton("Öğrenciler-Personeller");
        JButton inventoryButton = new JButton("Envanter");
        JButton notificationsButton = new JButton("Bildirim Gönder");
        JButton logoutButton = new JButton("Çıkış");

        // Adding action listeners for buttons
        booksButton.addActionListener(e -> openBooksPage());
        studentsButton.addActionListener(e -> mediator.notify(studentsButton, "click"));
        inventoryButton.addActionListener(e -> mediator.notify(inventoryButton, "click"));
        notificationsButton.addActionListener(e -> {
            // Show message when "Bildirim Gönder" button is clicked
            JOptionPane.showMessageDialog(this, "Eklenen Kitaplar Öğrencilere Bildirim Olarak Gönderildi");
        });
        logoutButton.addActionListener(e -> mediator.notify(logoutButton, "click"));

        // Mediator button assignments
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

    // Open BooksPage or StuBooksPage based on user type
    private void openBooksPage() {
        if ("staff".equals(userType)) {
            // Open BooksPage for staff
            new BooksPage().setVisible(true);
        } else if ("student".equals(userType)) {
            // Open StuBooksPage for student
            new StuBooksPage().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // User type can be "staff" or "student"
            HomePage homePage = new HomePage("student"); // Example: "student"
            homePage.setVisible(true);
        });
    }
}
