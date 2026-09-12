import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentManagementApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private StudentDAO studentDAO = new StudentDAO();
    private EnrollmentManager enrollmentManager = new EnrollmentManager();

    public StudentManagementApp() {
        setTitle("Student Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Padding between components

        // ====== TABLE ======
        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Email", "Phone"}, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ====== BUTTON PANEL ======
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1, 10, 10)); // rows, cols, hgap, vgap
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 40, 50)); // Padding around panel
        buttonPanel.setBackground(new Color(10, 25, 49)); // Neavy blue background

        JButton btnAdd = createStyledButton("Add Student");
        JButton btnEdit = createStyledButton("Edit Selected");
        JButton btnDelete = createStyledButton("Delete Selected");
        JButton btnRefresh = createStyledButton("Refresh List");
        JButton btnSearch = createStyledButton("Search");
        JButton btnAssignCourse = createStyledButton("Assign Course");
        JButton btnViewEnrollments = createStyledButton("View Enrollments");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnAssignCourse);
        buttonPanel.add(btnViewEnrollments);

        add(buttonPanel, BorderLayout.WEST);

        // ====== BUTTON ACTIONS ======
        btnAdd.addActionListener(e -> {
            StudentForm form = new StudentForm(this, null);
            form.setVisible(true);
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String first = (String) tableModel.getValueAt(selectedRow, 1);
                String last = (String) tableModel.getValueAt(selectedRow, 2);
                String email = (String) tableModel.getValueAt(selectedRow, 3);
                String phone = (String) tableModel.getValueAt(selectedRow, 4);
                Student student = new Student(id, first, last, email, phone);
                StudentForm form = new StudentForm(this, student);
                form.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Delete this student?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    studentDAO.deleteStudent(id);
                    refreshTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });

        btnRefresh.addActionListener(e -> refreshTable());

        btnSearch.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog(this, "Enter name to search:");
            if (keyword != null) {
                List<Student> results = studentDAO.searchStudents(keyword);
                updateTable(results);
            }
        });

        btnAssignCourse.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String first = (String) tableModel.getValueAt(selectedRow, 1);
                String last = (String) tableModel.getValueAt(selectedRow, 2);
                String email = (String) tableModel.getValueAt(selectedRow, 3);
                String phone = (String) tableModel.getValueAt(selectedRow, 4);
                Student student = new Student(id, first, last, email, phone);

                CourseForm form = new CourseForm(this, student, enrollmentManager);
                form.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Select a student to assign a course.");
            }
        });

        btnViewEnrollments.addActionListener(e -> {
            enrollmentManager.viewEnrollmentsGUI(this);
        });

        refreshTable(); // Load data on start
    }

    public void refreshTable() {
        List<Student> students = studentDAO.getAllStudents();
        updateTable(students);
    }

    private void updateTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getPhone()
            });
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementApp().setVisible(true));
    }
}
