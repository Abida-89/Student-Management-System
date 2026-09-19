    public class Instructor extends Person {
        private String department;

        public Instructor(int id, String firstName, String lastName, String email, String phone, String department) {
            super(id, firstName, lastName, email, phone);
            this.department = department;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        @Override
        public String toString(){
            return getFirstName() + " "+ getLastName() + " (" + department + ")";
        }

    }