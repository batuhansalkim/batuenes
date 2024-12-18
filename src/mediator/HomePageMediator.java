package mediator;

import javax.swing.*;
import views.BooksPage;
import views.InventoryPage;
import views.LoginPage;
import views.StudentsPage;
import java.awt.Component;

public class HomePageMediator implements Mediator {
    private JButton booksButton;
    private JButton studentsButton;
    private JButton inventoryButton;
    private JButton notificationsButton;
    private JButton logoutButton;

    public void setBooksButton(JButton booksButton) {
        this.booksButton = booksButton;
    }

    public void setStudentsButton(JButton studentsButton) {
        this.studentsButton = studentsButton;
    }

    public void setInventoryButton(JButton inventoryButton) {
        this.inventoryButton = inventoryButton;
    }

    public void setNotificationsButton(JButton notificationsButton) {
        this.notificationsButton = notificationsButton;
    }

    public void setLogoutButton(JButton logoutButton) {
        this.logoutButton = logoutButton;
    }

    @Override
    public void notify(Component sender, String event) {
        if (sender instanceof JButton) {  // sender'ın JButton olup olmadığını kontrol ediyoruz
            JButton button = (JButton) sender;  // sender'ı JButton olarak cast ediyoruz

            // Buton tıklama olaylarını kontrol ediyoruz
            if (button == booksButton && event.equals("click")) {
                new BooksPage().setVisible(true);
            } else if (button == studentsButton && event.equals("click")) {
                new StudentsPage().setVisible(true);
            } else if (button == inventoryButton && event.equals("click")) {
                new InventoryPage().setVisible(true);
            } else if (button == notificationsButton && event.equals("click")) {
                JOptionPane.showMessageDialog(null, "Bildirim gönderme ekranına yönlendiriliyorsunuz.");
            } else if (button == logoutButton && event.equals("click")) {
                int confirm = JOptionPane.showConfirmDialog(null, "Çıkmak istediğinize emin misiniz?", "Çıkış", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new LoginPage().setVisible(true);
                }
            }
        }
    }
}
