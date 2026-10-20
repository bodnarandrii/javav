package lab3.task1;

public class Persona {
    private String firstName;
    private String lastName;
    private int age;

    public Persona() {
    }

    public Persona(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void Show() {
        System.out.println("Персона: " + firstName + " " + lastName + ", вік: " + age);
    }
}
