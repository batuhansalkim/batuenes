package controller;

import config.DatabaseConnection;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    public User login(String username, String password) {
        User user = null;

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userType = resultSet.getString("user_type");
                String email = resultSet.getString("email");

                // User nesnesi oluşturuluyor
                user = new User(userId, username, password, userType, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean register(String username, String password, String email) {
    boolean isRegistered = false;

    try {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "INSERT INTO users (username, password, user_type, email) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, "student"); // Varsayılan olarak "student"
        preparedStatement.setString(4, email);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            isRegistered = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isRegistered;
}

}

