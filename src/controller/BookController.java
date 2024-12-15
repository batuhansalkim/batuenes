package controller;

import models.Book;
import dao.BookDAO;

import java.sql.*;
import java.util.List;

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
