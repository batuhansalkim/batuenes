package views;

import controller.LibraryController;
import models.LibraryStats;

import javax.swing.*;
import java.awt.*;

public class InventoryPage extends JFrame {

    private LibraryController libraryController;

    // Label'lar için değişkenler
    private JLabel studentCountLabel;
    private JLabel adminCountLabel;
    private JLabel totalBooksLabel;
    private JLabel lostBooksLabel;
    private JLabel borrowedBooksLabel;
    private JLabel booksOnShelfLabel;
    
    // Kitap Durumu Label'ları
    private JLabel borrowedBooksStatusLabel;
    private JLabel booksOnShelfStatusLabel;
    private JLabel lostBooksStatusLabel;

    public InventoryPage() {
        libraryController = new LibraryController(); // LibraryController'dan verileri alıyoruz

        setTitle("Envanter Sayfası - Kütüphane Yönetim Sistemi");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 10, 10)); // 10 satır ve 2 sütun olacak şekilde düzenlendi

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Kütüphane Envanteri");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Bilgiler paneli
        studentCountLabel = new JLabel("Toplam Öğrenci Sayısı: ");
        adminCountLabel = new JLabel("Toplam Admin Sayısı: ");
        totalBooksLabel = new JLabel("Toplam Kitap Sayısı: ");
        lostBooksLabel = new JLabel("Kayıp Kitap Sayısı: ");
        borrowedBooksLabel = new JLabel("Ödünç Verilmiş Kitap Sayısı: ");
        booksOnShelfLabel = new JLabel("Rafta Bulunan Kitap Sayısı: ");
        
        // Kitap Durumu paneli
        borrowedBooksStatusLabel = new JLabel("Ödünç Verilen Kitap Durumu: ");
        booksOnShelfStatusLabel = new JLabel("Rafta Kitap Durumu: ");
        lostBooksStatusLabel = new JLabel("Kayıp Kitap Durumu: ");
        
        // Envanter verilerinin paneli
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(studentCountLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(adminCountLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(totalBooksLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(lostBooksLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(borrowedBooksLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(booksOnShelfLabel);
        
        // Kitap Durumu Paneli
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(borrowedBooksStatusLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(booksOnShelfStatusLabel);
        add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        add(lostBooksStatusLabel);

        loadInventoryData(); // Envanter verilerini yükleyen metot

        setLocationRelativeTo(null); // Ekranın ortasında açılması için
    }

    private void loadInventoryData() {
        // LibraryController'dan envanter verilerini alıyoruz
        LibraryStats stats = libraryController.getLibraryStats();

        // İstatistikleri ekrana yazdırıyoruz
        studentCountLabel.setText("Toplam Öğrenci Sayısı: " + stats.getStudentCount());
        adminCountLabel.setText("Toplam Admin Sayısı: " + stats.getAdminCount());
        totalBooksLabel.setText("Toplam Kitap Sayısı: " + stats.getTotalBooks());
        lostBooksLabel.setText("Kayıp Kitap Sayısı: " + stats.getLostBooks());
        borrowedBooksLabel.setText("Ödünç Verilmiş Kitap Sayısı: " + stats.getBorrowedBooks());
        booksOnShelfLabel.setText("Rafta Bulunan Kitap Sayısı: " + stats.getBooksOnShelf());

        // Kitap Durumu
        borrowedBooksStatusLabel.setText("Ödünç Verilen Kitap Durumu: " + stats.getBorrowedBooks() + " Kitap");
        booksOnShelfStatusLabel.setText("Rafta Kitap Durumu: " + stats.getBooksOnShelf() + " Kitap");
        lostBooksStatusLabel.setText("Kayıp Kitap Durumu: " + stats.getLostBooks() + " Kitap");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryPage().setVisible(true); // Sayfayı görünür hale getiriyoruz
        });
    }
}
