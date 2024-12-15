package views;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.Book;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.BookController;
import java.util.List;

public class BooksPage extends JFrame {
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private BookController bookController;

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

    private void deleteSelectedBook() {
    int selectedRow = booksTable.getSelectedRow(); // Tablo üzerindeki seçili satır

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz kitabı seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int bookId = (int) tableModel.getValueAt(selectedRow, 0); // Kitap ID'si

    int confirm = JOptionPane.showConfirmDialog(this, 
        "Seçili kitabı silmek istediğinize emin misiniz?", 
        "Onay", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        boolean success = bookController.deleteBook(bookId);
        if (success) {
            loadBooksFromDatabase(); // Tabloyu yenile
            JOptionPane.showMessageDialog(this, "Kitap başarıyla silindi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Kitap silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    // Veritabanından kitapları yükle
    private void loadBooksFromDatabase() {
        List<Book> books = bookController.getBooks();

        // Mevcut tabloyu temizle
        tableModel.setRowCount(0);

        // Kitapları tabloya ekle
        for (Book book : books) {
            Object[] row = {book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus()};
            tableModel.addRow(row);
        }

        // Durum sütununu JComboBox ile düzenlenebilir yap
        String[] statuses = {"Rafta", "Odunc", "Kayıp"};
        TableColumn statusColumn = booksTable.getColumnModel().getColumn(4);
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Rafta", "Odunc", "Kayıp"});
        statusColumn.setCellEditor(new DefaultCellEditor(statusComboBox));


        // Tabloya listener ekle
        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            // Durum sütununda değişiklik olmuşsa
            if (column == 4) {
                int bookId = (int) tableModel.getValueAt(row, 0); // Kitap ID'si
                String newStatus = (String) tableModel.getValueAt(row, column);

                // Veritabanında güncellemeyi yap
                boolean success = bookController.updateBookStatus(bookId, newStatus);
if (success) {
            JOptionPane.showMessageDialog(this, "Durum başarıyla güncellendi.");
        } else {
            JOptionPane.showMessageDialog(this, "Durum güncellenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
            }
        });
    }

    

    private void showEditBookDialog(int bookId) {
    // Kitap bilgilerini düzenleme formu
    Book book = bookController.getBookById(bookId);
    if (book == null) {
        JOptionPane.showMessageDialog(this, "Kitap bilgisi yüklenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
        return;
    }

    JPanel editBookPanel = new JPanel(new GridLayout(4, 2));

    JTextField titleField = new JTextField(book.getTitle());
    JTextField authorField = new JTextField(book.getAuthor());
    JTextField categoryField = new JTextField(book.getCategory());

    JLabel statusLabel = new JLabel("Durum:");
    String[] statuses = bookController.getStatuses().toArray(new String[0]);
    JComboBox<String> statusComboBox = new JComboBox<>(statuses);
    statusComboBox.setSelectedItem(book.getStatus());

    editBookPanel.add(new JLabel("Kitap Adı:"));
    editBookPanel.add(titleField);
    editBookPanel.add(new JLabel("Yazar:"));
    editBookPanel.add(authorField);
    editBookPanel.add(new JLabel("Kategori:"));
    editBookPanel.add(categoryField);
    editBookPanel.add(statusLabel);
    editBookPanel.add(statusComboBox);

    int option = JOptionPane.showConfirmDialog(this, editBookPanel, "Kitap Düzenle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (option == JOptionPane.OK_OPTION) {
        String newTitle = titleField.getText();
        String newAuthor = authorField.getText();
        String newCategory = categoryField.getText();
        String newStatus = (String) statusComboBox.getSelectedItem();

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setCategory(newCategory);
        book.setStatus(newStatus);

        boolean success = bookController.updateBook(book);
        if (success) {
            loadBooksFromDatabase();
            JOptionPane.showMessageDialog(this, "Kitap başarıyla güncellendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Kitap güncellenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}

 


    private void showBookOptionsDialog(int bookId) {
        int option = JOptionPane.showOptionDialog(this,
                "Seçeneklerden birini seçin:",
                "Kitap İşlemleri",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Sil", "Düzenle", "Vazgeç"},
                "Vazgeç");

        if (option == 0) { // Sil seçeneği
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
        } else if (option == 1) { // Düzenle seçeneği
            showEditBookDialog(bookId);
        }
    }
   
    private void showAddBookDialog() {
        JPanel addBookPanel = new JPanel(new GridLayout(5, 2));

        JLabel titleLabel = new JLabel("Kitap Adı:");
        JTextField titleField = new JTextField(20);
        addBookPanel.add(titleLabel);
        addBookPanel.add(titleField);

        JLabel authorLabel = new JLabel("Yazar Adı:");
        JTextField authorField = new JTextField(20);
        addBookPanel.add(authorLabel);
        addBookPanel.add(authorField);

        JLabel categoryLabel = new JLabel("Kategori:");
        JTextField categoryField = new JTextField(20);
        addBookPanel.add(categoryLabel);
        addBookPanel.add(categoryField);

        JLabel statusLabel = new JLabel("Durum:");
        JTextField statusField = new JTextField(20);
        addBookPanel.add(statusLabel);
        addBookPanel.add(statusField);

        int option = JOptionPane.showConfirmDialog(this, addBookPanel, "Yeni Kitap Ekle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String bookName = titleField.getText();
            String authorName = authorField.getText();
            String category = categoryField.getText();
            String status = statusField.getText();

            if (bookName.trim().isEmpty() || authorName.trim().isEmpty() || category.trim().isEmpty() || status.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Book newBook = new Book(0, bookName, authorName, category, status);
            boolean success = bookController.addBook(newBook);

            if (success) {
                loadBooksFromDatabase();
                JOptionPane.showMessageDialog(this, "Kitap başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Kitap eklenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }







    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BooksPage booksPage = new BooksPage();
            booksPage.setVisible(true);
        });
    }
}
