import java.util.Date;

public class EnrollmentRecord {
    private int recordId;
    private Student student;
    private Course course;
    private Date enrollmentDate;

    public EnrollmentRecord() {}

    public EnrollmentRecord(int recordId, Student student, Course course, Date enrollmentDate) {
        this.recordId = recordId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
