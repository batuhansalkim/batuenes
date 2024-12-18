package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.StudentController;
import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class StudentsPage extends JFrame {
    private JTable studentsTable;
    private JTable staffTable;
    private DefaultTableModel studentTableModel;
    private DefaultTableModel staffTableModel;
    private StudentController studentController;

    public StudentsPage() {
        studentController = new StudentController();

        setTitle("Kullanıcı Listesi - Library Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Kullanıcı Yönetimi");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Sol ve Sağ Tablo Paneli
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);

        // Öğrenci Tablosu
        studentTableModel = new DefaultTableModel(new String[]{"ID", "Kullanıcı Adı", "Email"}, 0);
        studentsTable = new JTable(studentTableModel);
        JScrollPane studentScrollPane = new JScrollPane(studentsTable);

        // Personel Tablosu
        staffTableModel = new DefaultTableModel(new String[]{"ID", "Kullanıcı Adı", "Email"}, 0);
        staffTable = new JTable(staffTableModel);
        JScrollPane staffScrollPane = new JScrollPane(staffTable);

        // Sol (Personel) ve Sağ (Öğrenci) Paneline Tabloları Ekle
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Personel Listesi", JLabel.CENTER), BorderLayout.NORTH);
        leftPanel.add(staffScrollPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Öğrenci Listesi", JLabel.CENTER), BorderLayout.NORTH);
        rightPanel.add(studentScrollPane, BorderLayout.CENTER);

        // SplitPane'e panelleri ekle
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        add(splitPane, BorderLayout.CENTER);

        // Alt Kısım (Butonlar için Panel)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Ekle");
        JButton editButton = new JButton("Düzenle");
        JButton deleteButton = new JButton("Sil");
        JButton backButton = new JButton("Geri"); // Geri butonunu ekliyoruz

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton); // Geri butonunu panelimize ekliyoruz

        add(buttonPanel, BorderLayout.SOUTH);

        // Buton Aksiyonları
        addButton.addActionListener(e -> showAddUserDialog());
        editButton.addActionListener(e -> handleEditButtonClick());
        deleteButton.addActionListener(e -> handleDeleteButtonClick());
        backButton.addActionListener(e -> goBackToHomePage()); // Geri butonuna aksiyon ekliyoruz

        loadUsersFromDatabase();

        setLocationRelativeTo(null);
    }

    private void goBackToHomePage() {
        // Geri butonuna basıldığında ana sayfaya yönlendirme yapılır
        HomePage homePage = new HomePage("staff");
        homePage.setVisible(true);
        this.dispose(); // Current StudentsPage penceresini kapatıyoruz
    }

    // Diğer metotlar aynı kalacak
    private void loadUsersFromDatabase() {
        // Load students
        List<User> students = studentController.getStudents();
        studentTableModel.setRowCount(0);
        for (User student : students) {
            studentTableModel.addRow(new Object[]{
                student.getUserId(),
                student.getUsername(),
                student.getEmail()
            });
        }

        // Load staff
        List<User> staff = studentController.getStaff();
        staffTableModel.setRowCount(0);
        for (User person : staff) {
            staffTableModel.addRow(new Object[]{
                person.getUserId(),
                person.getUsername(),
                person.getEmail()
            });
        }
    }

    private void handleDeleteButtonClick() {
        int selectedStudentRow = studentsTable.getSelectedRow();
        int selectedStaffRow = staffTable.getSelectedRow();

        if (selectedStudentRow != -1) {
            int userId = (int) studentTableModel.getValueAt(selectedStudentRow, 0);
            deleteUser(userId, "student");
        } else if (selectedStaffRow != -1) {
            int userId = (int) staffTableModel.getValueAt(selectedStaffRow, 0);
            deleteUser(userId, "staff");
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz bir kullanıcı seçin.");
        }
    }

    private void handleEditButtonClick() {
        int selectedStudentRow = studentsTable.getSelectedRow();
        int selectedStaffRow = staffTable.getSelectedRow();

        if (selectedStudentRow != -1) {
            int userId = (int) studentTableModel.getValueAt(selectedStudentRow, 0);
            showEditUserDialog(userId, "student");
        } else if (selectedStaffRow != -1) {
            int userId = (int) staffTableModel.getValueAt(selectedStaffRow, 0);
            showEditUserDialog(userId, "staff");
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen düzenlemek istediğiniz bir kullanıcı seçin.");
        }
    }

    private void deleteUser(int userId, String userType) {
        boolean success = userType.equals("student")
                ? studentController.deleteStudent(userId)
                : studentController.deleteStaff(userId);

        if (success) {
            loadUsersFromDatabase();
            JOptionPane.showMessageDialog(this, "Kullanıcı başarıyla silindi.");
        } else {
            JOptionPane.showMessageDialog(this, "Kullanıcı silinirken bir hata oluştu.");
        }
    }

    private void showAddUserDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JComboBox<String> userTypeComboBox = new JComboBox<>(new String[]{"student", "staff"});

        panel.add(new JLabel("Kullanıcı Adı:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Kullanıcı Türü:"));
        panel.add(userTypeComboBox);

        int option = JOptionPane.showConfirmDialog(this, panel, "Yeni Kullanıcı Ekle", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String email = emailField.getText();
            String userType = (String) userTypeComboBox.getSelectedItem();

            User newUser = new User(0, username, "", userType, email);
            boolean success = userType.equals("student")
                    ? studentController.addStudent(newUser)
                    : studentController.addStaff(newUser);

            if (success) {
                loadUsersFromDatabase();
                JOptionPane.showMessageDialog(this, "Kullanıcı başarıyla eklendi.");
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı eklenirken bir hata oluştu.");
            }
        }
    }

    private void showEditUserDialog(int userId, String userType) {
        User user = userType.equals("student")
                ? studentController.getStudentById(userId)
                : studentController.getStaffById(userId);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Kullanıcı bilgisi bulunamadı.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField(user.getUsername());
        JTextField emailField = new JTextField(user.getEmail());

        panel.add(new JLabel("Kullanıcı Adı:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int option = JOptionPane.showConfirmDialog(this, panel, 
                userType.equals("student") ? "Öğrenci Düzenle" : "Personel Düzenle", 
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            user.setUsername(usernameField.getText());
            user.setEmail(emailField.getText());

            boolean success = userType.equals("student")
                    ? studentController.updateStudent(user)
                    : studentController.updateStaff(user);

            if (success) {
                loadUsersFromDatabase();
                JOptionPane.showMessageDialog(this, "Kullanıcı güncellendi.");
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı güncellenemedi.");
            }
        }
    }
}
