package controller;

import config.DatabaseConnection;
import models.LibraryStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryController {

    private Connection connection;

    public LibraryController() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    // Veritabanından kütüphane istatistiklerini almak
    public LibraryStats getLibraryStats() {
        int studentCount = 0;
        int adminCount = 0;
        int totalBooks = 0;
        int lostBooks = 0;
        int borrowedBooks = 0;
        int booksOnShelf = 0;

        try {
            // Öğrencilerin sayısını almak
            String studentQuery = "SELECT COUNT(*) FROM users WHERE user_type = 'student'";
            PreparedStatement studentStmt = connection.prepareStatement(studentQuery);
            ResultSet studentResult = studentStmt.executeQuery();
            if (studentResult.next()) {
                studentCount = studentResult.getInt(1);
            }

            // Adminlerin sayısını almak (user_type 'admin' veya 'staff' olanlar)
            String adminQuery = "SELECT COUNT(*) FROM users WHERE user_type IN ('admin', 'staff')";
            PreparedStatement adminStmt = connection.prepareStatement(adminQuery);
            ResultSet adminResult = adminStmt.executeQuery();
            if (adminResult.next()) {
                adminCount = adminResult.getInt(1);
            }

            // Toplam kitap sayısını almak
            String totalBooksQuery = "SELECT COUNT(*) FROM books";
            PreparedStatement totalBooksStmt = connection.prepareStatement(totalBooksQuery);
            ResultSet totalBooksResult = totalBooksStmt.executeQuery();
            if (totalBooksResult.next()) {
                totalBooks = totalBooksResult.getInt(1);
            }

            // Kayıp kitap sayısını almak
            String lostBooksQuery = "SELECT COUNT(*) FROM books WHERE status = 'kayıp'";
            PreparedStatement lostBooksStmt = connection.prepareStatement(lostBooksQuery);
            ResultSet lostBooksResult = lostBooksStmt.executeQuery();
            if (lostBooksResult.next()) {
                lostBooks = lostBooksResult.getInt(1);
            }

            // Ödünç verilmiş kitap sayısını almak
            String borrowedBooksQuery = "SELECT COUNT(*) FROM books WHERE status = 'odunc'";
            PreparedStatement borrowedBooksStmt = connection.prepareStatement(borrowedBooksQuery);
            ResultSet borrowedBooksResult = borrowedBooksStmt.executeQuery();
            if (borrowedBooksResult.next()) {
                borrowedBooks = borrowedBooksResult.getInt(1);
            }

            // Rafta bulunan kitap sayısını almak
            String booksOnShelfQuery = "SELECT COUNT(*) FROM books WHERE status = 'rafta'";
            PreparedStatement booksOnShelfStmt = connection.prepareStatement(booksOnShelfQuery);
            ResultSet booksOnShelfResult = booksOnShelfStmt.executeQuery();
            if (booksOnShelfResult.next()) {
                booksOnShelf = booksOnShelfResult.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LibraryStats(studentCount, adminCount, totalBooks, lostBooks, borrowedBooks, booksOnShelf);
    }
}
