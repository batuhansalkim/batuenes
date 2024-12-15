package models;

public class Student {
    private int userId; // Öğrencinin benzersiz ID'si
    private String username; // Öğrencinin kullanıcı adı
    private String email; // Öğrencinin e-posta adresi
    private String userType; // Öğrencinin kullanıcı türü (ör. "student" veya başka bir tür)

    // Parametresiz kurucu (zorunlu olmasa da kullanım kolaylığı sağlar)
    public Student() {
    }

    // Parametreli kurucu
    public Student(int userId, String username, String email, String userType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.userType = userType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // toString metodu (Tablo veya loglama için uygun formatta çıktı sağlar)
    @Override
    public String toString() {
        return "Student{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
