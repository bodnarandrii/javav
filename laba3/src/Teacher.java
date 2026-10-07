public class Teacher extends Person {
    protected String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void Show() {
        System.out.println("Викладач [Ім'я: " + name + ", Вік: " + age + ", Предмет: " + subject + "]");
    }
}