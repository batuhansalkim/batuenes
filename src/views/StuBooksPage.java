package views;

import javax.swing.table.DefaultTableModel;
import models.Book;
import controller.BookController;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StuBooksPage extends JFrame {
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private BookController bookController;
    private JTextField searchField; 
    private List<Book> allBooks = new ArrayList<>(); 

    public StuBooksPage() {
        bookController = new BookController();
        setTitle("Öğrenci Kitaplar Sayfası - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel("Kitaplar");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        
        // Arama kutusu
        searchField = new JTextField(20);
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBooks();
            }
        });
        headerPanel.add(new JLabel("Ara:"));
        headerPanel.add(searchField);

        add(headerPanel, BorderLayout.NORTH);

        // Kitaplar Tablosu
        String[] columnNames = {"Kitap Adı", "Yazar", "Kategori", "Durum"};
        tableModel = new DefaultTableModel(columnNames, 0);
        booksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);

        // Geri Dön Butonu
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(e -> {
            dispose();
            HomePage homePage = new HomePage("student");
            homePage.setVisible(true);
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Veritabanından kitapları çek ve tabloyu güncelle
        loadBooksFromDatabase();

        setLocationRelativeTo(null); 
    }

    // Veritabanından kitapları yükle
    private void loadBooksFromDatabase() {
        List<Book> books = bookController.getBooks();

        allBooks.clear();
        allBooks.addAll(books);

        tableModel.setRowCount(0);

        for (Book book : books) {
            Object[] row = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus()};
            tableModel.addRow(row);
        }
    }

    // Arama fonksiyonu
    private void searchBooks() {
        String searchText = searchField.getText().toLowerCase();

        tableModel.setRowCount(0);

        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchText) || 
                book.getAuthor().toLowerCase().contains(searchText) || 
                book.getCategory().toLowerCase().contains(searchText)) {
                
                Object[] row = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus()};
                tableModel.addRow(row);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StuBooksPage stuBooksPage = new StuBooksPage();
            stuBooksPage.setVisible(true);
        });
    }
}

