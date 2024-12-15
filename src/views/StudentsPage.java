package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.StudentController;
import models.User;

import java.awt.*;
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

        // Geri Dön Butonu
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(e -> {
            dispose();
            HomePage homePage = new HomePage("staff");
            homePage.setVisible(true);
        });
        add(backButton, BorderLayout.SOUTH);

        loadUsersFromDatabase();

        // Mouse Click Events
        addTableMouseListeners();
        setLocationRelativeTo(null);
    }

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

    private void addTableMouseListeners() {
        // Öğrenci Tablosu Çift Tıklama
        studentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = studentsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int userId = (int) studentTableModel.getValueAt(selectedRow, 0);
                        showUserOptionsDialog(userId, "student");
                    }
                }
            }
        });

        // Personel Tablosu Çift Tıklama
        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = staffTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int userId = (int) staffTableModel.getValueAt(selectedRow, 0);
                        showUserOptionsDialog(userId, "staff");
                    }
                }
            }
        });
    }

    private void showUserOptionsDialog(int userId, String userType) {
        int option = JOptionPane.showOptionDialog(this,
                userType.equals("student") ? "Öğrenci için işlem seçin:" : "Personel için işlem seçin:",
                "Kullanıcı İşlemleri",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Sil", "Düzenle", "Vazgeç"},
                "Vazgeç");

        if (option == 0) {
            boolean success = userType.equals("student")
                    ? studentController.deleteStudent(userId)
                    : studentController.deleteStaff(userId);

            if (success) {
                loadUsersFromDatabase();
                JOptionPane.showMessageDialog(this, userType.equals("student") 
                    ? "Öğrenci başarıyla silindi." 
                    : "Personel başarıyla silindi.");
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı silinirken bir hata oluştu.");
            }
        } else if (option == 1) {
            showEditUserDialog(userId, userType);
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
