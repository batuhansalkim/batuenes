package controller;

import models.Book;
import dao.BookDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BookController {
    private Connection connection;

    // Veritabanı bağlantısını oluştur
    public BookController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kitapları veritabanından çekme
    public List<Book> getBooks() {
    List<Book> books = new ArrayList<>();
    try {
        String query = "SELECT book_id, title, author, category, status FROM books"; // book_id kullanılıyor
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int bookId = rs.getInt("book_id");  // book_id
            String title = rs.getString("title");
            String author = rs.getString("author");
            String category = rs.getString("category");
            String status = rs.getString("status");

            books.add(new Book(bookId, title, author, category, status));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return books;
}


    // Yeni kitap ekleme
    public boolean addBook(Book book) {
        try {
            String query = "INSERT INTO books (book_id,title, author, category, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, book.getBookId()); // 'bookId' yerine 'book_id' doğru bir şekilde kullanılır
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getCategory());
            stmt.setString(5, book.getStatus());
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kitap durumunu güncelleme
   public boolean updateBookStatus(int bookId, String newStatus) {
    try {
        // book_id olarak güncellenmiş sorgu
        String query = "UPDATE books SET status = ? WHERE book_id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, newStatus);  // Durumu ayarla
        stmt.setInt(2, bookId);        // Kitap ID'sini ayarla
        int rowsAffected = stmt.executeUpdate();

        // Eğer güncelleme başarılıysa, 1'den fazla satır etkilenmiş olmalı
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "SQL Hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE); // Hata mesajı
        return false;
    }
}

    // Durumları veritabanından çekme
    public List<String> getStatuses() {
        List<String> statuses = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT status FROM books";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                statuses.add(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}
