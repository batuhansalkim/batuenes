package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBooksPage extends JFrame {
    private JTable borrowedBooksTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private List<String> borrowedBooks = new ArrayList<>(); // Ödünç alınan kitapların listesi

    public BorrowedBooksPage() {
        setTitle("Ödünç Alınan Kitaplar - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel("Ödünç Alınan Kitaplar");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        
        // Arama kutusu
        searchField = new JTextField(20);
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBorrowedBooks();
            }
        });
        headerPanel.add(new JLabel("Ara:"));
        headerPanel.add(searchField);

        add(headerPanel, BorderLayout.NORTH);

        // Ödünç Alınan Kitaplar Tablosu
        String[] columnNames = {"Kitap Adı", "Ödünç Alınan Tarih"};
        tableModel = new DefaultTableModel(columnNames, 0);
        borrowedBooksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(borrowedBooksTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buton Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Geri Dön butonu
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(e -> {
            dispose();
            StuBooksPage stuBooksPage = new StuBooksPage(); // Öğrenci kitaplar sayfasına dönüş
            stuBooksPage.setVisible(true);
        });

        // İade Et butonu
        JButton returnAllButton = new JButton("Tüm Kitapları İade Et");
        returnAllButton.addActionListener(e -> {
            returnAllBooks();
        });

        // Butonları panelin içine ekle
        buttonPanel.add(backButton);
        buttonPanel.add(returnAllButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Ödünç alınan kitapları ekle
        loadBorrowedBooks();

        setLocationRelativeTo(null); 
    }

    // Ödünç alınan kitapları yükle
    private void loadBorrowedBooks() {
        // Ödünç alınan kitaplar
        borrowedBooks.add("1984 - George Orwell - 12/12/2024");
        borrowedBooks.add("Suç ve Ceza - Fyodor Dostoyevski - 15/12/2024");
        borrowedBooks.add("Sefiller - Victor Hugo - 18/12/2024");  // Örnek kitap

        tableModel.setRowCount(0); // Tabloyu temizle

        // Kitapları tabloya ekle
        for (String borrowedBook : borrowedBooks) {
            String[] bookData = borrowedBook.split(" - ");
            tableModel.addRow(bookData);
        }
    }

    // Tüm kitapları iade etme işlemi
    private void returnAllBooks() {
        int confirmed = JOptionPane.showConfirmDialog(this, "Tüm kitapları iade etmek istediğinizden emin misiniz?", "İade Et", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            borrowedBooks.clear();  // Tüm ödünç alınan kitapları temizle
            tableModel.setRowCount(0);  // Tabloyu temizle
            JOptionPane.showMessageDialog(this, "Tüm kitaplar iade edildi!");
        }
    }

    // Ödünç alınan kitapları arama fonksiyonu
    private void searchBorrowedBooks() {
        String searchText = searchField.getText().toLowerCase();

        tableModel.setRowCount(0);

        for (String borrowedBook : borrowedBooks) {
            if (borrowedBook.toLowerCase().contains(searchText)) {
                String[] bookData = borrowedBook.split(" - ");
                tableModel.addRow(bookData);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
            borrowedBooksPage.setVisible(true);
        });
    }
}
