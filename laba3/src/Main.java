import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Завдання 1 (Ієрархія класів Person)");
            System.out.println("2. Завдання 2 (Абстрактний клас Figure)");
            System.out.println("3. Завдання 3 (Інтерфейс IFigure)");
            System.out.println("0. Вихід");
            
            int choice = getValidInt(scanner, "Оберіть завдання: ");

            switch (choice) {
                case 1: runTask1(scanner); break;
                case 2: runTask2(scanner); break;
                case 3: runTask3(scanner); break;
                case 0: 
                    System.out.println("Роботу завершено.");
                    return;
                default: 
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void runTask1(Scanner scanner) {
        System.out.println("\n--- Завдання 1 ---");
        int count = getValidInt(scanner, "Введіть кількість об'єктів для масиву: ");
        if (count <= 0) {
            System.out.println("Кількість має бути більшою за 0.");
            return;
        }

        Person[] people = new Person[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Кого додати? (1 - Персона, 2 - Студент, 3 - Викладач, 4 - Зав. кафедри): ");
            int type = getValidInt(scanner, "Тип: ");
            
            scanner.nextLine(); // Очищення буфера
            System.out.print("Введіть ім'я: ");
            String name = scanner.nextLine();
            int age = getValidInt(scanner, "Введіть вік: ");

            switch (type) {
                case 1:
                    people[i] = new Person(name, age);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Введіть групу: ");
                    String group = scanner.nextLine();
                    people[i] = new Student(name, age, group);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Введіть предмет: ");
                    String subject = scanner.nextLine();
                    people[i] = new Teacher(name, age, subject);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Введіть предмет: ");
                    String subj = scanner.nextLine();
                    System.out.print("Введіть назву кафедри: ");
                    String dep = scanner.nextLine();
                    people[i] = new HeadOfDepartment(name, age, subj, dep);
                    break;
                default:
                    System.out.println("Невірний тип, додано як базову Персону.");
                    people[i] = new Person(name, age);
            }
        }

        System.out.println("\nРезультат масиву (виклик Show()):");
        for (Person p : people) {
            p.Show();
        }
    }

    private static void runTask2(Scanner scanner) {
        System.out.println("\n--- Завдання 2 ---");
        Figure[] figures = new Figure[3];
        
        System.out.println("Введення прямокутника:");
        double rectA = getValidDouble(scanner, "Сторона A: ");
        double rectB = getValidDouble(scanner, "Сторона B: ");
        figures[0] = new Rectangle(rectA, rectB);

        System.out.println("Введення кола:");
        double r = getValidDouble(scanner, "Радіус: ");
        figures[1] = new Circle(r);

        System.out.println("Введення трапеції:");
        double a = getValidDouble(scanner, "Основа 1: ");
        double b = getValidDouble(scanner, "Основа 2: ");
        double c = getValidDouble(scanner, "Бічна сторона 1: ");
        double d = getValidDouble(scanner, "Бічна сторона 2: ");
        double h = getValidDouble(scanner, "Висота: ");
        figures[2] = new Trapezium(a, b, c, d, h);

        System.out.println("\nРезультати (Абстрактний клас):");
        for (Figure f : figures) {
            System.out.println(f.toString());
            System.out.printf("Площа: %.2f | Периметр: %.2f\n", f.getArea(), f.getPerimeter());
        }
    }

    private static void runTask3(Scanner scanner) {
        System.out.println("\n--- Завдання 3 ---");
        System.out.println("Демонстрація роботи через інтерфейс (Прямокутник):");
        double a = getValidDouble(scanner, "Сторона A: ");
        double b = getValidDouble(scanner, "Сторона B: ");
        
        IFigure rect = new RectangleI(a, b);
        System.out.println(rect.toString());
        System.out.printf("Площа: %.2f | Периметр: %.2f\n", rect.getArea(), rect.getPerimeter());
    }

    private static int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Помилка! Введіть ціле число.");
                scanner.next(); 
            }
        }
    }

    private static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Помилка! Значення має бути додатним.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка! Введіть коректне числове значення (використовуйте кому для дробів).");
                scanner.next();
            }
        }
    }
}