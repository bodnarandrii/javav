package lab3.task2;

import java.util.Scanner;

public class Task2Runner {

    public static void run(Scanner scanner) {
        System.out.println("\n--- Завдання 2: Абстрактний клас Figure ---");
        System.out.print("Введіть кількість фігур для масиву: ");
        int count = readPositiveInt(scanner);

        Figure[] figures = new Figure[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nОберіть фігуру #" + (i + 1) + ":");
            System.out.println("1 - Прямокутник");
            System.out.println("2 - Коло");
            System.out.println("3 - Трапеція");
            System.out.print("Ваш вибір: ");
            int type = readInt(scanner);

            switch (type) {
                case 1: {
                    System.out.print("Введіть сторону a: ");
                    double a = readPositiveDouble(scanner);
                    System.out.print("Введіть сторону b: ");
                    double b = readPositiveDouble(scanner);
                    figures[i] = new Rectangle(a, b);
                    break;
                }
                case 2: {
                    System.out.print("Введіть радіус r: ");
                    double r = readPositiveDouble(scanner);
                    figures[i] = new Circle(r);
                    break;
                }
                case 3: {
                    while (true) {
                        System.out.print("Введіть основу a: ");
                        double a = readPositiveDouble(scanner);
                        System.out.print("Введіть основу b: ");
                        double b = readPositiveDouble(scanner);
                        System.out.print("Введіть бічну сторону c: ");
                        double c = readPositiveDouble(scanner);
                        System.out.print("Введіть бічну сторону d: ");
                        double d = readPositiveDouble(scanner);

                        if (Trapezium.isValid(a, b, c, d)) {
                            figures[i] = new Trapezium(a, b, c, d);
                            break;
                        } else {
                            System.out.println("Помилка! Трапеція з такими сторонами не існує (a!=b та нерівність трикутника). Спробуйте ще раз.");
                        }
                    }
                    break;
                }
                default:
                    System.out.println("Невірний вибір! Створено коло за замовчуванням.");
                    figures[i] = new Circle(1.0);
                    break;
            }
        }

        System.out.println("\n--- Розрахунки для масиву фігур ---");
        for (int i = 0; i < figures.length; i++) {
            System.out.println("[" + (i + 1) + "] " + figures[i].toString());
            System.out.printf("    Площа: %.2f | Периметр: %.2f%n", figures[i].getArea(), figures[i].getPerimeter());
        }

        // Демонстрація equals()
        System.out.println("\n--- Демонстрація equals() ---");
        if (figures.length > 1) {
            boolean hasEqual = false;
            for (int i = 0; i < figures.length; i++) {
                for (int j = i + 1; j < figures.length; j++) {
                    if (figures[i].equals(figures[j])) {
                        System.out.println("Фігура [" + (i + 1) + "] та Фігура [" + (j + 1) + "] рівні (equals == true)");
                        hasEqual = true;
                    }
                }
            }
            if (!hasEqual) {
                System.out.println("У масиві немає однакових фігур.");
            }

            System.out.println("\nБажаєте порівняти дві конкретні фігури? (1 - так, 2 - ні): ");
            int ans = readInt(scanner);
            if (ans == 1) {
                System.out.print("Введіть номер першої фігури (1-" + figures.length + "): ");
                int idx1 = readInt(scanner) - 1;
                System.out.print("Введіть номер другої фігури (1-" + figures.length + "): ");
                int idx2 = readInt(scanner) - 1;

                if (idx1 >= 0 && idx1 < figures.length && idx2 >= 0 && idx2 < figures.length) {
                    boolean eq = figures[idx1].equals(figures[idx2]);
                    System.out.println("Результат equals(): " + eq);
                } else {
                    System.out.println("Невірні індекси.");
                }
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

    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            int val = readInt(scanner);
            if (val > 0) return val;
            System.out.print("Число має бути більшим за 0! Введіть знову: ");
        }
    }

    private static double readPositiveDouble(Scanner scanner) {
        while (true) {
            try {
                double val = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (val > 0) return val;
                System.out.print("Значення має бути більшим за 0! Введіть знову: ");
            } catch (Exception e) {
                System.out.print("Помилка! Введіть число: ");
            }
        }
    }
}
