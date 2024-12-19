package dao;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordsDAO {
    public List<Object[]> getLoanedBooksByUserId(int userId) {
        List<Object[]> records = new ArrayList<>();

        try {
            // Singleton üzerinden bağlantıyı al
            Connection conn = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT books.title, books.author, borrow_records.borrow_date, borrow_records.return_date " +
                         "FROM borrow_records " +
                         "JOIN books ON borrow_records.book_id = books.book_id " +
                         "WHERE borrow_records.user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(new Object[]{
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
