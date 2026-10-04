public class Administrator extends Person {
    private String role;

    public Administrator() {
        super();
    }

    public Administrator(int id, String firstName, String lastName, String email, String phone, String role) {
        super(id, firstName, lastName, email, phone);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
