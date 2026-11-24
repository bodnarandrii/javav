package lab5;

import lab5.task1.Task1Main;
import lab5.task2.Task2Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("Лабораторна робота №5 (Колекції в Java)");
            System.out.println("1. Завдання 1 (Фігури: ArrayList, Comparable, Comparator)");
            System.out.println("2. Завдання 2, варіант 8 (Додавання многочленів через HashMap)");
            System.out.println("0. Вихід");
            System.out.print("Оберіть пункт меню: ");

            String choice = scanner.next();
            System.out.println();

            switch (choice) {
                case "1":
                    Task1Main.run(scanner);
                    break;
                case "2":
                    Task2Main.run(scanner);
                    break;
                case "0":
                    System.out.println("Роботу завершено.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
