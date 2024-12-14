package models;

public class User {
    private int userId;
    private String username;
    private String password;
    private String userType;
    private String email;

    public User(int userId, String username, String password, String userType, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.email = email;
    }

    // Getter ve Setter metodları
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
