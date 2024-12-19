package dao;

import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection databaseConnection;

    // Constructor: Veritabanı bağlantısını alır
    public BookDAO(Connection connection) {
        this.databaseConnection = connection;
    }

    // Kitapları veritabanından çekme
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String query = "SELECT book_id, title, author, category, status FROM books";
            PreparedStatement stmt = databaseConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Yeni kitap ekleme
    public boolean addBook(Book book) {
        try {
            String query = "INSERT INTO books (title, author, category, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = databaseConnection.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getStatus());

            return stmt.executeUpdate() > 0; // Başarılıysa true döner
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kitap silme
    public boolean deleteBook(int bookId) {
        try {
            String query = "DELETE FROM books WHERE book_id = ?";
            PreparedStatement stmt = databaseConnection.prepareStatement(query);
            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0; // Başarılıysa true döner
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kitap güncelleme
    public boolean updateBook(Book book) {
        try {
            String query = "UPDATE books SET title = ?, author = ?, category = ?, status = ? WHERE book_id = ?";
            PreparedStatement stmt = databaseConnection.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getStatus());
            stmt.setInt(5, book.getBookId());

            return stmt.executeUpdate() > 0; // Başarılıysa true döner
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addCategory(String categoryName) {
    String sql = "ALTER TABLE books MODIFY category ENUM('Aşk', 'Polisiye', 'Bilim', ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, categoryName);
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    // Kitap durumlarını veritabanından çekme
    public List<String> getStatuses() {
        List<String> statuses = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT status FROM books";
            Statement stmt = databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                statuses.add(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    // Kitap ID'ye göre çekme
    public Book getBookById(int bookId) {
        try {
            String query = "SELECT * FROM books WHERE book_id = ?";
            PreparedStatement stmt = databaseConnection.prepareStatement(query);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Eğer kitap bulunamazsa null döner
    }

    public boolean updateBookStatus(int bookId, String newStatus) {
    try {
        String query = "UPDATE books SET status = ? WHERE book_id = ?";
        PreparedStatement stmt = databaseConnection.prepareStatement(query);
        stmt.setString(1, newStatus);
        stmt.setInt(2, bookId);
        return stmt.executeUpdate() > 0; // Başarılıysa true döner
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}