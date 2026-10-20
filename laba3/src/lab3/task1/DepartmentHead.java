package lab3.task1;

public class DepartmentHead extends Teacher {
    private String faculty;
    private int experienceYears;

    public DepartmentHead() {
    }

    public DepartmentHead(String firstName, String lastName, int age, String department,
                          String position, String faculty, int experienceYears) {
        super(firstName, lastName, age, department, position);
        this.faculty = faculty;
        this.experienceYears = experienceYears;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public void Show() {
        System.out.println("Завідувач кафедри: " + getFirstName() + " " + getLastName() + ", вік: " + getAge() +
                ", кафедра: " + getDepartment() + ", посада: " + getPosition() +
                ", факультет: " + faculty + ", стаж: " + experienceYears + " р.");
    }
}
