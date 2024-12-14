package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection databaseConnection;

    // Constructor: Veritabanı bağlantısını alır
    public BookDAO(Connection connection) {
        this.databaseConnection = connection;
    }

    // Kitap durumlarını veritabanından çeker
    public List<String> getStatuses() {
        List<String> statuses = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT status FROM books"; // Kitap durumlarını çekmek için sorgu
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Sonuçları listeye ekle
            while (resultSet.next()) {
                statuses.add(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}
