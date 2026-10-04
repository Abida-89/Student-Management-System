import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EnrollmentViewer extends JDialog {
    public EnrollmentViewer(JFrame parent, List<EnrollmentRecord> enrollments) {
        super(parent, "Enrollment Details", true);
        setSize(700, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Set background color
        getContentPane().setBackground(new Color(10, 25, 49)); // Navy blue

        String[] columnNames = {
            "Enrollment ID", "Student", "Course Code", "Course Name",
            "Instructor", "Instructor Email", "Date"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Table styling
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(173, 216, 230)); // Light blue
        table.getTableHeader().setForeground(Color.BLACK);

        for (EnrollmentRecord record : enrollments) {
            Course course = record.getCourse();
            Person instructor = course.getInstructor();
            tableModel.addRow(new Object[]{
                record.getRecordId(),
                record.getStudent().getFirstName(),
                course.getCourseId(),
                course.getCourseName(),
                instructor.getFirstName(),
                instructor.getEmail(),
                record.getEnrollmentDate().toString()
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(173, 216, 230)); // Light blue
        closeBtn.setFocusPainted(false);

        JPanel panel = new JPanel();
        panel.setOpaque(false); // Transparent so background shows
        panel.add(closeBtn);
        add(panel, BorderLayout.SOUTH);

        closeBtn.addActionListener(e -> dispose());
    }
}
