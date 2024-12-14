package config;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        
        if (databaseConnection.isConnectionValid()) {
            System.out.println("Veritabanına bağlantı başarılı ve geçerli.");
        } else {
            System.err.println("Veritabanına bağlanılamadı veya bağlantı geçersiz.");
        }

        // Bağlantıyı kapatmayı unutmayın
        databaseConnection.closeConnection();
    }
}
