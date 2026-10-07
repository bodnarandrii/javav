public class Student extends Person {
    private String group;

    public Student(String name, int age, String group) {
        super(name, age);
        this.group = group;
    }

    @Override
    public void Show() {
        System.out.println("Студент [Ім'я: " + name + ", Вік: " + age + ", Група: " + group + "]");
    }
}