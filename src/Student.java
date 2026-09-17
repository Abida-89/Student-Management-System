public class Student extends Person {
    public Student() {
        super();
    }

    public Student(int id, String firstName, String lastName, String email, String phone) {
        super(id, firstName, lastName, email, phone);
    }

    public Student( String firstName, String lastName, String email, String phone) {
        super(0, firstName, lastName, email, phone);
    }
}
