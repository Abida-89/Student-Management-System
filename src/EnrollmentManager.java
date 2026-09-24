import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class EnrollmentManager {
    public List<EnrollmentRecord> records = new ArrayList<>();
    private int nextId = 1;

    public void enroll(Student student, Course course) {
        EnrollmentRecord record = new EnrollmentRecord(nextId++, student, course, new Date());
        records.add(record);
    }

    public List<EnrollmentRecord> getEnrollments() {
        return records;
    }

    public void viewEnrollmentsGUI(JFrame parent) {
        if (records.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No enrollment records found.");
            return;
        }

        EnrollmentViewer viewer = new EnrollmentViewer(parent, getEnrollments());
        viewer.setVisible(true);
    }
}
