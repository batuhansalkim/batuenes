package controller;

import models.Book;
import dao.BookDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;

public class BookController {
    private BookDAO bookDAO;

    // Constructor: DAO ile bağlantıyı kur
    public BookController() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            this.bookDAO = new BookDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kitapları listeleme
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    // Yeni kitap ekleme
    public boolean addBook(Book book) {
        return bookDAO.addBook(book);
    }
// BookController.java
public List<String> getCategories() {
    List<String> categories = new ArrayList<>();
    String sql = "SHOW COLUMNS FROM books LIKE 'category'";

    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            String enumValues = rs.getString("Type"); // ENUM değerlerini içeren sütun
            enumValues = enumValues.replace("enum(", "").replace(")", "").replace("'", "");
            String[] values = enumValues.split(",");

            for (String value : values) {
                categories.add(value);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return categories;
}

public boolean addCategory(String categoryName) {
    return bookDAO.addCategory(categoryName);
}




    // Kitap silme
    public boolean deleteBook(int bookId) {
        return bookDAO.deleteBook(bookId);
    }

    // Kitap güncelleme
    public boolean updateBook(Book book) {
        return bookDAO.updateBook(book);
    }

    // Kitap durumlarını listeleme
    public List<String> getStatuses() {
        return bookDAO.getStatuses();
    }

    // Kitap ID ile sorgulama
    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }
    
    public boolean updateBookStatus(int bookId, String newStatus) {
    // BookDAO'dan çağrı yaparak veritabanını güncelleyin
    return bookDAO.updateBookStatus(bookId, newStatus);
}


}
