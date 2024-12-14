package controller;

import models.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM books";  // Burada tablo adı ve kolon adı "book_id" olacak
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        // Veritabanından gelen her satır için Book nesnesi oluştur
        while (rs.next()) {
            int bookId = rs.getInt("book_id");  // book_id'yi kullanıyoruz
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
        String query = "INSERT INTO books (title, author, category, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getCategory());
        stmt.setString(4, book.getStatus());
        int rowsInserted = stmt.executeUpdate();

        return rowsInserted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



}
