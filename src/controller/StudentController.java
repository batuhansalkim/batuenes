package controller;

import models.User;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    // Veritabanındaki tüm öğrencileri döndür
    public List<User> getStudents() {
        List<User> students = new ArrayList<>();
        String query = "SELECT * FROM users WHERE user_type = 'student'";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                students.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("user_type"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Veritabanındaki tüm personeli döndür
    public List<User> getStaff() {
        List<User> staff = new ArrayList<>();
        String query = "SELECT * FROM users WHERE user_type = 'staff'";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                staff.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("user_type"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }

    // Belirli bir öğrenci ID'sine göre kullanıcı getir
    public User getStudentById(int userId) {
        String query = "SELECT * FROM users WHERE user_id = ? AND user_type = 'student'";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("user_type"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Belirli bir personel ID'sine göre kullanıcı getir
    public User getStaffById(int userId) {
        String query = "SELECT * FROM users WHERE user_id = ? AND user_type = 'staff'";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("user_type"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Öğrenci sil
    public boolean deleteStudent(int userId) {
        String query = "DELETE FROM users WHERE user_id = ? AND user_type = 'student'";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Personel sil
    public boolean deleteStaff(int userId) {
        String query = "DELETE FROM users WHERE user_id = ? AND user_type = 'staff'";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Öğrenci güncelle
    public boolean updateStudent(User student) {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, user_type = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getUsername());
            stmt.setString(2, student.getPassword());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getUserType());
            stmt.setInt(5, student.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Personel güncelle
    public boolean updateStaff(User staff) {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, user_type = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getUsername());
            stmt.setString(2, staff.getPassword());
            stmt.setString(3, staff.getEmail());
            stmt.setString(4, staff.getUserType());
            stmt.setInt(5, staff.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
