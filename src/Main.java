import views.AbstractPage;
import views.LoginPage;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        AbstractPage loginPage = new LoginPage(); // Abstract sınıftan referans
        SwingUtilities.invokeLater(loginPage::showPage); // Abstract metodu çağır
    }
}
