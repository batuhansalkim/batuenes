package views;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPage extends JFrame {
    protected JPanel headerPanel;
    protected JPanel contentPanel;

    public AbstractPage() {
        // Başlangıç ayarları
        setSize(450, 400); // Orta boyut
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli (Ortak)
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel(getTitleText());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // İçerik Paneli (Her sayfada özelleştirilebilir)
        contentPanel = new JPanel();
        add(contentPanel, BorderLayout.CENTER);
    }

    // Soyut metot: Her sayfa kendi başlık metnini belirler
    protected abstract String getTitleText();

    // Sayfayı gösterme metodunu her sayfa için özelleştirebilirsiniz
    public abstract void showPage();
}
