package views;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    public HomePage(String userType) {
        // Frame ayarları
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Mesaj gösterimi
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        if (userType.equals("staff")) {
            welcomeLabel.setText("Hoş geldiniz, Personel!");
        } else if (userType.equals("student")) {
            welcomeLabel.setText("Hoş geldiniz, Öğrenci!");
        } else {
            welcomeLabel.setText("Hoş geldiniz!");
        }

        add(welcomeLabel, BorderLayout.CENTER);
        setLocationRelativeTo(null); // Ortada açılmasını sağlar
    }
}
