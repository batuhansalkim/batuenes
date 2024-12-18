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
        setSize(600, 600); // Sayfa boyutunu artırdık
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Ana layout'u BorderLayout yapıyoruz

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Kütüphane Envanteri");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Bilgiler paneli
        JPanel infoPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        studentCountLabel = createStyledLabel("Toplam Öğrenci Sayısı: ");
        adminCountLabel = createStyledLabel("Toplam Admin Sayısı: ");
        totalBooksLabel = createStyledLabel("Toplam Kitap Sayısı: ");
        lostBooksLabel = createStyledLabel("Kayıp Kitap Sayısı: ");
        borrowedBooksLabel = createStyledLabel("Ödünç Verilmiş Kitap Sayısı: ");
        booksOnShelfLabel = createStyledLabel("Rafta Bulunan Kitap Sayısı: ");
        
        // Kitap Durumu paneli
        borrowedBooksStatusLabel = createStyledLabel("Ödünç Verilen Kitap Durumu: ");
        booksOnShelfStatusLabel = createStyledLabel("Rafta Kitap Durumu: ");
        lostBooksStatusLabel = createStyledLabel("Kayıp Kitap Durumu: ");
        
        // Bilgileri ekliyoruz
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(studentCountLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(adminCountLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(totalBooksLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(lostBooksLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(borrowedBooksLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(booksOnShelfLabel);
        
        // Kitap Durumu Paneli
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(borrowedBooksStatusLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(booksOnShelfStatusLabel);
        infoPanel.add(new JLabel("")); // Boş bir label ekleyerek düzeni sağlıyoruz
        infoPanel.add(lostBooksStatusLabel);

        loadInventoryData(); // Envanter verilerini yükleyen metot

        add(infoPanel, BorderLayout.CENTER); // Bilgiler panelini merkeze ekliyoruz

        // Footer Paneli
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.BLUE); // Footer arka planı mavi yapıyoruz
        footerPanel.setLayout(new BorderLayout());  // BorderLayout kullanıyoruz

        // Footer yazısı
        JLabel footerLabel = new JLabel("<html><center>" +
            "<p><strong style='color:white;'>Kırklareli Üniversitesi Yazılım Mimarisi ve Tasarımı Dersi</strong></p>" +
            "<p style='color:white;'>Bu uygulama, Kırklareli Üniversitesi Yazılım Mimarisi ve Tasarımı dersi için Batuhan Salkım ve Enes Yenigün tarafından kodlanmıştır.</p>" +
            "<p style='color:white;'>Aşağıdaki veriler kütüphane yönetim sisteminin istatistiksel verilerini yansıtmaktadır.</p>" +
            "<p><strong style='color:white;'>Uygulama Özellikleri:</strong></p>" +
            "<ul style='color:white;'>" +
            "<li>Kütüphanedeki toplam kitap sayısını görüntüleyebilirsiniz.</li>" +
            "<li>Ödünç alınmış kitaplar ve kayıp kitaplar hakkında bilgi alabilirsiniz.</li>" +
            "<li>Öğrenci ve admin sayısını görebilirsiniz.</li>" +
            "</ul>" +
            "</center></html>");
        
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Yazı fontu
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerLabel, BorderLayout.CENTER);

        // Footer panelin genişliğini ve yüksekliğini arttırıyoruz
        footerPanel.setPreferredSize(new Dimension(getWidth(), 200)); // Footer yüksekliğini arttırdık

        // Alt kısıma footerPanel'ı ekleyelim
        add(footerPanel, BorderLayout.SOUTH);

        // "Geri" Butonu Ekleme
        JButton backButton = new JButton("Geri");
        backButton.addActionListener(e -> {
            dispose(); // InventoryPage'i kapat
            HomePage homePage = new HomePage("staff"); // "staff" veya "student" kullanabilirsiniz
            homePage.setVisible(true); // HomePage'i görünür hale getir
        });
        
        // Geri butonunu footerPanel'e ekleyelim
        footerPanel.add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Ekranın ortasında açılması için
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(50, 50, 50));  // Gri renk
        label.setOpaque(true);
        label.setBackground(new Color(230, 230, 255)); // Hafif mor arka plan
        label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 1));  // Mavi kenarlık
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(250, 30));
        return label;
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
