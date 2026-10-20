package lab3.task1;

import java.util.Scanner;

public class Task1Runner {

    public static void run(Scanner scanner) {
        System.out.println("\n--- Завдання 1: Ієрархія класів Persona ---");
        System.out.print("Введіть кількість персон для масиву: ");
        int count = readInt(scanner);

        Persona[] people = new Persona[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nОберіть тип для персони #" + (i + 1) + ":");
            System.out.println("1 - Persona");
            System.out.println("2 - Student");
            System.out.println("3 - Teacher");
            System.out.println("4 - DepartmentHead");
            System.out.print("Ваш вибір: ");
            int type = readInt(scanner);

            System.out.print("Введіть ім'я: ");
            String firstName = scanner.nextLine();
            System.out.print("Введіть прізвище: ");
            String lastName = scanner.nextLine();
            System.out.print("Введіть вік: ");
            int age = readInt(scanner);

            switch (type) {
                case 1:
                    people[i] = new Persona(firstName, lastName, age);
                    break;
                case 2:
                    System.out.print("Введіть групу: ");
                    String group = scanner.nextLine();
                    System.out.print("Введіть курс: ");
                    int course = readInt(scanner);
                    System.out.print("Введіть бал: ");
                    double grade = readDouble(scanner);
                    people[i] = new Student(firstName, lastName, age, group, course, grade);
                    break;
                case 3:
                    System.out.print("Введіть кафедру: ");
                    String department = scanner.nextLine();
                    System.out.print("Введіть посаду: ");
                    String position = scanner.nextLine();
                    people[i] = new Teacher(firstName, lastName, age, department, position);
                    break;
                case 4:
                    System.out.print("Введіть кафедру: ");
                    String dep = scanner.nextLine();
                    System.out.print("Введіть посаду: ");
                    String pos = scanner.nextLine();
                    System.out.print("Введіть факультет: ");
                    String faculty = scanner.nextLine();
                    System.out.print("Введіть стаж (років): ");
                    int exp = readInt(scanner);
                    people[i] = new DepartmentHead(firstName, lastName, age, dep, pos, faculty, exp);
                    break;
                default:
                    System.out.println("Невірний вибір! Створено як базову Persona.");
                    people[i] = new Persona(firstName, lastName, age);
                    break;
            }
        }

        System.out.println("\n--- Результати (поліморфний виклик Show()) ---");
        for (int i = 0; i < people.length; i++) {
            System.out.print("[" + (i + 1) + "] ");
            people[i].Show();
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Помилка! Введіть ціле число: ");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (Exception e) {
                System.out.print("Помилка! Введіть дійсне число: ");
            }
        }
    }
}
