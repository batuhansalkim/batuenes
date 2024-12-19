package views;

import javax.swing.*;
import java.awt.*;

public class LoanedBooksPage extends JFrame {
    public LoanedBooksPage() {
        setTitle("Ödünç Alınan Kitaplar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Ödünç alınan kitaplar için örnek tablo
        String[] columnNames = {"Kitap Adı", "Yazar", "Alış Tarihi", "İade Tarihi"};
        Object[][] data = {
            {"Kitap 1", "Yazar 1", "01-12-2024", "15-12-2024"},
            {"Kitap 2", "Yazar 2", "05-12-2024", "19-12-2024"},
            {"Kitap 3", "Yazar 3", "10-12-2024", "24-12-2024"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }
}
