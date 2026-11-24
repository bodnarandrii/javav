package lab5.task2;

import java.util.Scanner;

public class Task2Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    public static void run(Scanner scanner) {
        System.out.println("=== ЗАВДАННЯ 2 (Варіант 8): Додавання двох многочленів (HashMap) ===");

        int degree = readNonNegativeInt(scanner, "Введіть степінь многочленів (n >= 0): ");

        System.out.println("\nВведіть коефіцієнти першого многочлена P1(x):");
        Polynomial p1 = readPolynomial(scanner, degree);

        System.out.println("\nВведіть коефіцієнти другого многочлена P2(x):");
        Polynomial p2 = readPolynomial(scanner, degree);

        Polynomial sum = p1.add(p2);

        System.out.println("\n--- Результат ---");
        System.out.println("P1(x) = " + p1);
        System.out.println("  HashMap P1: " + p1.getCoefficients());
        System.out.println("P2(x) = " + p2);
        System.out.println("  HashMap P2: " + p2.getCoefficients());
        System.out.println("P1(x) + P2(x) = " + sum);
        System.out.println("  HashMap суми: " + sum.getCoefficients());
    }

    private static Polynomial readPolynomial(Scanner scanner, int degree) {
        Polynomial poly = new Polynomial();
        for (int i = degree; i >= 0; i--) {
            System.out.print("  Коефіцієнт при x^" + i + ": ");
            double coeff = readDouble(scanner);
            poly.setCoefficient(i, coeff);
        }
        return poly;
    }

    private static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String token = scanner.next();
            try {
                int val = Integer.parseInt(token);
                if (val >= 0) {
                    return val;
                }
                System.out.println("Степінь має бути >= 0.");
            } catch (NumberFormatException e) {
                System.out.println("Некоректне ціле число.");
            }
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
}
