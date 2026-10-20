package lab3.task1;

public class Student extends Persona {
    private String group;
    private int course;
    private double grade;

    public Student() {
    }

    public Student(String firstName, String lastName, int age, String group, int course, double grade) {
        super(firstName, lastName, age);
        this.group = group;
        this.course = course;
        this.grade = grade;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public void Show() {
        System.out.println("Студент: " + getFirstName() + " " + getLastName() + ", вік: " + getAge() +
                ", група: " + group + ", курс: " + course + ", бал: " + grade);
    }
}
