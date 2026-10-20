package lab3.task3;

import java.util.Scanner;

public class Task3Runner {

    public static void run(Scanner scanner) {
        System.out.println("\n--- Завдання 3: Інтерфейс FigureInterface ---");
        System.out.print("Введіть кількість фігур для масиву: ");
        int count = readPositiveInt(scanner);

        FigureInterface[] figures = new FigureInterface[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nОберіть фігуру #" + (i + 1) + ":");
            System.out.println("1 - Прямокутник (RectangleImpl)");
            System.out.println("2 - Коло (CircleImpl)");
            System.out.println("3 - Трапеція (TrapeziumImpl)");
            System.out.print("Ваш вибір: ");
            int type = readInt(scanner);

            switch (type) {
                case 1: {
                    System.out.print("Введіть сторону a: ");
                    double a = readPositiveDouble(scanner);
                    System.out.print("Введіть сторону b: ");
                    double b = readPositiveDouble(scanner);
                    figures[i] = new RectangleImpl(a, b);
                    break;
                }
                case 2: {
                    System.out.print("Введіть радіус r: ");
                    double r = readPositiveDouble(scanner);
                    figures[i] = new CircleImpl(r);
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

                        if (TrapeziumImpl.isValid(a, b, c, d)) {
                            figures[i] = new TrapeziumImpl(a, b, c, d);
                            break;
                        } else {
                            System.out.println("Помилка! Трапеція з такими сторонами не існує. Спробуйте ще раз.");
                        }
                    }
                    break;
                }
                default:
                    System.out.println("Невірний вибір! Створено коло за замовчуванням.");
                    figures[i] = new CircleImpl(1.0);
                    break;
            }
        }

        System.out.println("\n--- Розрахунки через інтерфейс FigureInterface ---");
        for (int i = 0; i < figures.length; i++) {
            System.out.println("[" + (i + 1) + "] " + figures[i].toString());
            System.out.printf("    Назва: %s | Площа: %.2f | Периметр: %.2f%n",
                    figures[i].getName(), figures[i].getArea(), figures[i].getPerimeter());
        }

        if (figures.length > 1) {
            System.out.println("\n--- Порівняння об'єктів (equals) ---");
            boolean hasEqual = false;
            for (int i = 0; i < figures.length; i++) {
                for (int j = i + 1; j < figures.length; j++) {
                    if (figures[i].equals(figures[j])) {
                        System.out.println("Фігура [" + (i + 1) + "] та Фігура [" + (j + 1) + "] однакові");
                        hasEqual = true;
                    }
                }
            }
            if (!hasEqual) {
                System.out.println("У масиві немає однакових фігур.");
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
