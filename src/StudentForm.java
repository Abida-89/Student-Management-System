import javax.swing.*;
import java.awt.*;

public class StudentForm extends JDialog {
    private JTextField txtFirstName, txtLastName, txtEmail, txtPhone;
    private JButton btnSave, btnCancel;
    private StudentDAO studentDAO;
    private StudentManagementApp mainApp;
    private Student studentToEdit;

    public StudentForm(StudentManagementApp mainApp, Student studentToEdit) {
        this.mainApp = mainApp;
        this.studentToEdit = studentToEdit;
        this.studentDAO = new StudentDAO();

        setTitle(studentToEdit == null ? "Add Student" : "Edit Student");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(mainApp);
        setLayout(new GridLayout(5, 2, 10, 10));

        getContentPane().setBackground(new Color(10, 25, 49)); // Navy blue

        txtFirstName = createStyledTextField();
        txtLastName = createStyledTextField();
        txtEmail = createStyledTextField();
        txtPhone = createStyledTextField();

        if (studentToEdit != null) {
            txtFirstName.setText(studentToEdit.getFirstName());
            txtLastName.setText(studentToEdit.getLastName());
            txtEmail.setText(studentToEdit.getEmail());
            txtPhone.setText(studentToEdit.getPhone());
        }

        btnSave = createStyledButton("Save");
        btnCancel = createStyledButton("Cancel");

        btnSave.addActionListener(e -> saveStudent());
        btnCancel.addActionListener(e -> dispose());

        addStyledLabel("First Name:");
        add(txtFirstName);
        addStyledLabel("Last Name:");
        add(txtLastName);
        addStyledLabel("Email:");
        add(txtEmail);
        addStyledLabel("Phone:");
        add(txtPhone);
        add(btnSave);
        add(btnCancel);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(230, 230, 250));
        field.setForeground(Color.BLACK);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(173, 216, 230)); // Light blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    private void addStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        add(label);
    }

    private void saveStudent() {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return;
        }

        if (studentToEdit == null) {
            Student newStudent = new Student(firstName, lastName, email, phone);
            studentDAO.addStudent(newStudent);
        } else {
            studentToEdit.setFirstName(firstName);
            studentToEdit.setLastName(lastName);
            studentToEdit.setEmail(email);
            studentToEdit.setPhone(phone);
            studentDAO.updateStudent(studentToEdit);
        }

        mainApp.refreshTable(); // Assumes this method is public
        dispose();
    }
}
