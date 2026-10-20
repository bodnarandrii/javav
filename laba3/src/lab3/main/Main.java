package lab3.main;

import lab3.task1.Task1Runner;
import lab3.task2.Task2Runner;
import lab3.task3.Task3Runner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1 - Завдання 1 (Ієрархія класів Persona)");
            System.out.println("2 - Завдання 2 (Абстрактний клас Figure)");
            System.out.println("3 - Завдання 3 (Інтерфейс FigureInterface)");
            System.out.println("0 - Вихід");
            System.out.print("Оберіть пункт меню: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1:
                    Task1Runner.run(scanner);
                    break;
                case 2:
                    Task2Runner.run(scanner);
                    break;
                case 3:
                    Task3Runner.run(scanner);
                    break;
                case 0:
                    System.out.println("Роботу завершено. До побачення!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
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
}
