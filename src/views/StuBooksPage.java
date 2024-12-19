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

        // Buton Paneli
        JPanel buttonPanel = new JPanel();
        
        // Kitap ödünç al butonu
        JButton borrowButton = new JButton("Kitap Ödünç Al");
        borrowButton.addActionListener(e -> borrowBook());
        buttonPanel.add(borrowButton);

        // Ödünç alınan kitaplar butonu
        JButton borrowedBooksButton = new JButton("Ödünç Alınan Kitaplar");
        borrowedBooksButton.addActionListener(e -> openBorrowedBooksPage());
        buttonPanel.add(borrowedBooksButton);

        // Çıkış butonu
        JButton logoutButton = new JButton("Çıkış");
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);

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

    // Kitap ödünç alma fonksiyonu
    private void borrowBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookTitle = (String) booksTable.getValueAt(selectedRow, 0);
            // Kitap ödünç alınacak işlemi burada yapılacak
            JOptionPane.showMessageDialog(this, bookTitle + " adlı kitabı ödünç aldınız.");
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen ödünç almak istediğiniz kitabı seçin.");
        }
    }

    // Ödünç alınan kitaplar sayfasına yönlendirme
    private void openBorrowedBooksPage() {
        BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
        borrowedBooksPage.setVisible(true);
        dispose(); // Şu anki sayfayı kapat
    }

    // Çıkış fonksiyonu
    private void logout() {
        // Log out action
        JOptionPane.showMessageDialog(this, "Çıkış yapıldı!");
        dispose(); // HomePage'e yönlendir
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StuBooksPage stuBooksPage = new StuBooksPage();
            stuBooksPage.setVisible(true);
        });
    }
}
