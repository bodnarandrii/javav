public class HeadOfDepartment extends Teacher {
    private String department;

    public HeadOfDepartment(String name, int age, String subject, String department) {
        super(name, age, subject);
        this.department = department;
    }

    @Override
    public void Show() {
        System.out.println("Завідувач кафедри [Ім'я: " + name + ", Вік: " + age + 
                           ", Предмет: " + subject + ", Кафедра: " + department + "]");
    }
}