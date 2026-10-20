package lab3.task1;

public class Teacher extends Persona {
    private String department;
    private String position;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, int age, String department, String position) {
        super(firstName, lastName, age);
        this.department = department;
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void Show() {
        System.out.println("Викладач: " + getFirstName() + " " + getLastName() + ", вік: " + getAge() +
                ", кафедра: " + department + ", посада: " + position);
    }
}
