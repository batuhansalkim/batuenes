package views;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.Book;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.BookController;

import java.util.ArrayList;
import java.util.List;

public class BooksPage extends JFrame {
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private BookController bookController;
    private JTextField searchField; // Arama kutusu ekleniyor
    private List<Book> allBooks = new ArrayList<>(); // Kitapları tutacak liste

    public BooksPage() {
        // Frame ayarları
        bookController = new BookController();
        setTitle("Kitaplar Sayfası - Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        
        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(100, 149, 237));
        JLabel headerLabel = new JLabel("Kitaplar");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        
        // Arama kutusu ekleniyor
        searchField = new JTextField(20); // Arama kutusu
    searchField.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            searchBooks(); // Arama fonksiyonunu tetikle
        }
    });
    headerPanel.add(new JLabel("Ara:"));
    headerPanel.add(searchField);

    add(headerPanel, BorderLayout.NORTH);

        // Kitaplar Tablosu
        String[] columnNames = {"Sayı", "Kitap Adı", "Yazar", "Kategori", "Durum"};
        tableModel = new DefaultTableModel(columnNames, 0);
        booksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buton Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Kategori Ekle Butonu
JButton addCategoryButton = new JButton("Kategori Ekle");
addCategoryButton.addActionListener(e -> showAddCategoryDialog());
buttonPanel.add(addCategoryButton);

        // Kitap Ekle Butonu
        JButton addBookButton = new JButton("Kitap Ekle");
        addBookButton.addActionListener(e -> showAddBookDialog());
        buttonPanel.add(addBookButton);
        
        // Sil Butonu
        JButton deleteBookButton = new JButton("Kitap Sil");
        deleteBookButton.addActionListener(e -> deleteSelectedBook());
        buttonPanel.add(deleteBookButton);

        // Düzenle Butonu (Yeni Eklendi)
        JButton editBookButton = new JButton("Kitap Düzenle");
        editBookButton.addActionListener(e -> {
            int selectedRow = booksTable.getSelectedRow();
            if (selectedRow != -1) {
                int bookId = (int) tableModel.getValueAt(selectedRow, 0);
                showEditBookDialog(bookId);
            } else {
                JOptionPane.showMessageDialog(this, "Lütfen düzenlemek istediğiniz kitabı seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });
        buttonPanel.add(editBookButton);

        // Geri Dön Butonu
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(e -> {
            dispose();
            HomePage homePage = new HomePage("staff");
            homePage.setVisible(true);
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Veritabanından kitapları çek ve tabloyu güncelle
        loadBooksFromDatabase();

        // Tabloya MouseListener ekle
        booksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Çift tıklama kontrolü
                    int selectedRow = booksTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int bookId = (int) tableModel.getValueAt(selectedRow, 0);
                        showBookOptionsDialog(bookId);
                    }
                }
            }
        });

        setLocationRelativeTo(null); // Ortada açılmasını sağlar
    }

    // Veritabanından kitapları yükle
    private void loadBooksFromDatabase() {
        // Burada veritabanından kitapları yükleme işlemini yapıyoruz
        List<Book> books = bookController.getBooks();

        // Kitapları allBooks listesine ekle
        allBooks.clear();
        allBooks.addAll(books);

        // Mevcut tabloyu temizle
        tableModel.setRowCount(0);

        // Kitapları tabloya ekle
        for (Book book : books) {
            Object[] row = {book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus()};
            tableModel.addRow(row);
        }
    }

    private void showAddCategoryDialog() {
    JTextField categoryField = new JTextField();

    JPanel panel = new JPanel(new GridLayout(1, 2));
    panel.add(new JLabel("Kategori Adı:"));
    panel.add(categoryField);

    int option = JOptionPane.showConfirmDialog(this, panel, "Yeni Kategori Ekle", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String categoryName = categoryField.getText();
        if (categoryName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kategori adı boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean success = bookController.addCategory(categoryName);
        if (success) {
            JOptionPane.showMessageDialog(this, "Kategori başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            loadBooksFromDatabase(); // Kategorileri yenile
        } else {
            JOptionPane.showMessageDialog(this, "Kategori eklenirken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    private void showAddBookDialog() {
    JPanel addBookPanel = new JPanel(new GridLayout(4, 2));

    JTextField titleField = new JTextField();
    JTextField authorField = new JTextField();
    List<String> categories = bookController.getCategories();
    JComboBox<String> categoryComboBox = new JComboBox<>(categories.toArray(new String[0]));
    
    JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Rafta", "Odunc", "Kayıp"});


    addBookPanel.add(new JLabel("Kitap Adı:"));
    addBookPanel.add(titleField);
    addBookPanel.add(new JLabel("Yazar:"));
    addBookPanel.add(authorField);
    addBookPanel.add(new JLabel("Kategori:"));
    addBookPanel.add(categoryComboBox);
    addBookPanel.add(new JLabel("Durum:"));
    addBookPanel.add(statusComboBox);

    int option = JOptionPane.showConfirmDialog(this, addBookPanel, "Yeni Kitap Ekle", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String title = titleField.getText();
        String author = authorField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String status = (String) statusComboBox.getSelectedItem();

        // Yeni kitap ekleme işlemi
        boolean success = bookController.addBook(new Book(title, author, category, status));
        if (success) {
            loadBooksFromDatabase(); // Kitaplar yüklendikten sonra listeyi güncelle
            JOptionPane.showMessageDialog(this, "Kitap başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Kitap eklenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    // Arama fonksiyonu
    private void searchBooks() {
        String searchText = searchField.getText().toLowerCase(); // Kullanıcıdan gelen metni alıyoruz

        // Mevcut tabloyu temizle
        tableModel.setRowCount(0);

        // Tablodaki kitapları filtrele
        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchText) || 
                book.getAuthor().toLowerCase().contains(searchText) || 
                book.getCategory().toLowerCase().contains(searchText)) {
                
                // Arama kriterine uyan kitapları tabloya ekle
                Object[] row = {book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus()};
                tableModel.addRow(row);
            }
        }
    }


    

    // Kitap düzenleme fonksiyonu
    private void showEditBookDialog(int bookId) {
    Book book = bookController.getBookById(bookId);
    if (book == null) {
        JOptionPane.showMessageDialog(this, "Kitap bilgisi yüklenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
        return;
    }

    JPanel editBookPanel = new JPanel(new GridLayout(4, 2));

    JTextField titleField = new JTextField(book.getTitle());
    JTextField authorField = new JTextField(book.getAuthor());

    // Kategoriler veritabanından alınarak JComboBox'a ekleniyor
    JComboBox<String> categoryComboBox = new JComboBox<>(bookController.getCategories().toArray(new String[0]));
    categoryComboBox.setSelectedItem(book.getCategory()); // Mevcut kategori seçili olsun

    JLabel statusLabel = new JLabel("Durum:");
    String[] statuses = bookController.getStatuses().toArray(new String[0]);
    JComboBox<String> statusComboBox = new JComboBox<>(statuses);
    statusComboBox.setSelectedItem(book.getStatus()); // Mevcut durum seçili olsun

    editBookPanel.add(new JLabel("Kitap Adı:"));
    editBookPanel.add(titleField);
    editBookPanel.add(new JLabel("Yazar:"));
    editBookPanel.add(authorField);
    editBookPanel.add(new JLabel("Kategori:"));
    editBookPanel.add(categoryComboBox);
    editBookPanel.add(statusLabel);
    editBookPanel.add(statusComboBox);

    int option = JOptionPane.showConfirmDialog(this, editBookPanel, "Kitap Düzenle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (option == JOptionPane.OK_OPTION) {
        String newTitle = titleField.getText();
        String newAuthor = authorField.getText();
        String newCategory = (String) categoryComboBox.getSelectedItem(); // Seçilen kategori
        String newStatus = (String) statusComboBox.getSelectedItem(); // Seçilen durum

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setCategory(newCategory); // Yeni kategori atanıyor
        book.setStatus(newStatus); // Yeni durum atanıyor

        boolean success = bookController.updateBook(book);
        if (success) {
            loadBooksFromDatabase(); // Tabloyu güncelle
            JOptionPane.showMessageDialog(this, "Kitap başarıyla güncellendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Kitap güncellenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    // Kitap silme fonksiyonu
    private void deleteSelectedBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz kitabı seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int bookId = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Seçili kitabı silmek istediğinize emin misiniz?",
                "Onay",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = bookController.deleteBook(bookId);
            if (success) {
                loadBooksFromDatabase();
                JOptionPane.showMessageDialog(this, "Kitap başarıyla silindi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Kitap silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Kitap işlemleri dialogu
    private void showBookOptionsDialog(int bookId) {
        int option = JOptionPane.showOptionDialog(this,
                "Seçeneklerden birini seçin:",
                "Kitap İşlemleri",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Sil", "Düzenle", "Vazgeç"},
                "Vazgeç");

        if (option == 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Kitabı silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = bookController.deleteBook(bookId);
                if (success) {
                    loadBooksFromDatabase();
                    JOptionPane.showMessageDialog(this, "Kitap başarıyla silindi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Kitap silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (option == 1) {
            showEditBookDialog(bookId);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BooksPage booksPage = new BooksPage();
            booksPage.setVisible(true);
        });
    }
}