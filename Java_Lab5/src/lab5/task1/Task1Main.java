package lab5.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task1Main {
    private static final String CIRCLES_FILE = "data/circles.txt";
    private static final String RECTANGLES_FILE = "data/rectangles.txt";
    private static final String TRAPEZIUMS_FILE = "data/trapeziums.txt";
    private static final String OUTPUT_FILE = "data/sorted_figures.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    public static void run(Scanner scanner) {
        System.out.println("=== ЗАВДАННЯ 1: Колекції ArrayList, Comparable та Comparator ===");

        // 1. Читання даних з файлів у колекції відповідного типу
        ArrayList<Circle> circles = readCircles(CIRCLES_FILE);
        ArrayList<Rectangle> rectangles = readRectangles(RECTANGLES_FILE);
        ArrayList<Trapezium> trapeziums = readTrapeziums(TRAPEZIUMS_FILE);

        // 2. Виведення прочитаних даних
        System.out.println("\n--- Прочитані дані з файлів ---");
        printList("Кола", circles);
        printList("Прямокутники", rectangles);
        printList("Трапеції", trapeziums);

        // 3. Сортування за допомогою Collections.sort() (Comparable)
        Collections.sort(circles);
        Collections.sort(rectangles);
        Collections.sort(trapeziums);

        System.out.println("\n--- Відсортовані колекції (Comparable) ---");
        printList("Кола (за радіусом)", circles);
        printList("Прямокутники (за площею, потім периметром)", rectangles);
        printList("Трапеції (за площею, потім периметром)", trapeziums);

        // 4. Додавання по одному запису з клавіатури в кожну колекцію
        System.out.println("\n--- Додавання нових записів з клавіатури ---");
        circles.add(inputCircle(scanner));
        rectangles.add(inputRectangle(scanner));
        trapeziums.add(inputTrapezium(scanner));

        // 5. Повторне сортування та виведення
        Collections.sort(circles);
        Collections.sort(rectangles);
        Collections.sort(trapeziums);

        System.out.println("\n--- Відсортовані колекції після додавання ---");
        printList("Кола", circles);
        printList("Прямокутники", rectangles);
        printList("Трапеції", trapeziums);

        // 6. Загальний ArrayList на основі абстрактного класу Figure
        ArrayList<Figure> allFigures = new ArrayList<>();
        allFigures.addAll(circles);
        allFigures.addAll(rectangles);
        allFigures.addAll(trapeziums);

        // 7. Сортування загального списку за допомогою Comparator
        Collections.sort(allFigures, new FigureComparator());

        System.out.println("\n--- Загальний список Figure (відсортований за площею через FigureComparator) ---");
        printList("Усі фігури", allFigures);

        // 8. Запис відсортованого ArrayList у файл
        writeFiguresToFile(allFigures, OUTPUT_FILE);
        System.out.println("\nВідсортований список фігур успішно записано у файл: " + OUTPUT_FILE);
    }

    private static ArrayList<Circle> readCircles(String filename) {
        ArrayList<Circle> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                double r = Double.parseDouble(line.replace(',', '.'));
                if (r > 0) {
                    list.add(new Circle(r));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Помилка читання файлу " + filename + ": " + e.getMessage());
        }
        return list;
    }

    private static ArrayList<Rectangle> readRectangles(String filename) {
        ArrayList<Rectangle> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    double a = Double.parseDouble(parts[0].replace(',', '.'));
                    double b = Double.parseDouble(parts[1].replace(',', '.'));
                    if (a > 0 && b > 0) {
                        list.add(new Rectangle(a, b));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Помилка читання файлу " + filename + ": " + e.getMessage());
        }
        return list;
    }

    private static ArrayList<Trapezium> readTrapeziums(String filename) {
        ArrayList<Trapezium> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length >= 4) {
                    double a = Double.parseDouble(parts[0].replace(',', '.'));
                    double b = Double.parseDouble(parts[1].replace(',', '.'));
                    double c = Double.parseDouble(parts[2].replace(',', '.'));
                    double d = Double.parseDouble(parts[3].replace(',', '.'));
                    if (Trapezium.isValid(a, b, c, d)) {
                        list.add(new Trapezium(a, b, c, d));
                    } else {
                        System.err.println("Пропущено некоректну трапецію у файлі: " + line);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Помилка читання файлу " + filename + ": " + e.getMessage());
        }
        return list;
    }

    private static Circle inputCircle(Scanner scanner) {
        while (true) {
            System.out.print("Введіть радіус кола (r > 0): ");
            double r = readDouble(scanner);
            if (r > 0) {
                return new Circle(r);
            }
            System.out.println("Радіус має бути додатним числом.");
        }
    }

    private static Rectangle inputRectangle(Scanner scanner) {
        while (true) {
            System.out.print("Введіть сторону a прямокутника (a > 0): ");
            double a = readDouble(scanner);
            System.out.print("Введіть сторону b прямокутника (b > 0): ");
            double b = readDouble(scanner);
            if (a > 0 && b > 0) {
                return new Rectangle(a, b);
            }
            System.out.println("Сторони прямокутника мають бути додатними.");
        }
    }

    private static Trapezium inputTrapezium(Scanner scanner) {
        while (true) {
            System.out.println("Введіть сторони трапеції (основи a != b та бічні сторони c, d):");
            System.out.print("  a = ");
            double a = readDouble(scanner);
            System.out.print("  b = ");
            double b = readDouble(scanner);
            System.out.print("  c = ");
            double c = readDouble(scanner);
            System.out.print("  d = ");
            double d = readDouble(scanner);
            if (Trapezium.isValid(a, b, c, d)) {
                return new Trapezium(a, b, c, d);
            }
            System.out.println("Трапеція з такими сторонами не існує. Спробуйте ще раз (напр. 10 4 5 5).");
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            String token = scanner.next();
            try {
                return Double.parseDouble(token.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.print("Некоректне число. Введіть ще раз: ");
            }
        }
    }

    private static void printList(String title, List<?> list) {
        System.out.println(title + " (" + list.size() + "):");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + list.get(i));
        }
    }

    private static void writeFiguresToFile(List<Figure> figures, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Figure figure : figures) {
                bw.write(figure.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Помилка запису у файл " + filename + ": " + e.getMessage());
        }
    }
}
