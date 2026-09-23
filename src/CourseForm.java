import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CourseForm extends JDialog {
    private JComboBox<String> courseDropdown;
    private JButton assignButton, cancelButton;
    private Student student;
    public EnrollmentManager enrollmentManager;

    private Course[] availableCourses = {
        new Course(101, "Object Oriented Programming", "Object-oriented Java", new Instructor(1, "Umme Israt Afroz", "OOP", "oop@univ.edu", "123", "CS")),
        new Course(1011, "Object Oriented Programming Laboratory", "Object-oriented Java", new Instructor(1, "Umme Israt Afroz", "OOPL", "oop@univ.edu", "123", "CS")),
        new Course(102, "Data Structure", "Learn stacks, queues, etc.", new Instructor(2, "Farhana Shirin", "DS", "ds@univ.edu", "456", "CS")),
        new Course(103, "Internet Programming", "HTML, CSS, JS and more", new Instructor(3, "Tamim Hossain", "IP", "db@univ.edu", "789", "CS")),
        new Course(104, "Numerical Method", "Calculating", new Instructor(4, "Nafiza Anjum", "NM", "db@univ.edu", "789", "CS"))
    };

    public CourseForm(JFrame parent, Student student, EnrollmentManager enrollmentManager) {
        super(parent, "Assign Course", true);
        this.student = student;
        this.enrollmentManager = enrollmentManager;

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Background
        getContentPane().setBackground(new Color(10, 25, 49)); // Navy blue

        // Dropdown
        courseDropdown = new JComboBox<>();
        for (Course c : availableCourses) {
            courseDropdown.addItem(c.getCourseName());
        }

        // Buttons
        assignButton = new JButton("Assign");
        cancelButton = new JButton("Cancel");

        // Button styling
        Color lightBlue = new Color(173, 216, 230);
        assignButton.setBackground(lightBlue);
        cancelButton.setBackground(lightBlue);

        // Layout Panel
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setOpaque(false); // Let background show through
        panel.add(new JLabel("Select Course:"));
        panel.add(courseDropdown);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(assignButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        assignButton.addActionListener(e -> {
            int selectedIndex = courseDropdown.getSelectedIndex();
            if (selectedIndex >= 0) {
                Course selectedCourse = availableCourses[selectedIndex];
                enrollmentManager.enroll(student, selectedCourse);
                JOptionPane.showMessageDialog(this, "Course assigned successfully!");
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }
}
