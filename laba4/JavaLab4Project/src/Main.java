import java.io.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(" Меню");
            System.out.println("1. Виконати Завдання 1 (Варіант 6)");
            System.out.println("2. Виконати Завдання 2 (Варіант 3)");
            System.out.println("0. Вихід");
            System.out.print("Виберіть пункт меню: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runTask1_6();
                    break;
                case "2":
                    runTask2_3();
                    break;
                case "0":
                    System.out.println("Завершення роботи.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static File getReadableFile(String prompt) {
        while (true) {
            System.out.print(prompt);
            String path = scanner.nextLine().trim();
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                return file;
            } else {
                System.out.println("Помилка: Файл \"" + path + "\" не знайдено або це не файл. Спробуйте ще раз.");
            }
        }
    }

    private static FileWriteConfig getWriteConfig(String prompt) {
        while (true) {
            System.out.print(prompt);
            String path = scanner.nextLine().trim();
            File file = new File(path);
            boolean append = false;

            if (file.exists()) {
                System.out.println("Увага: Файл \"" + path + "\" вже існує.");
                System.out.print("Бажаєте (1) Перезаписати чи (2) Дописати в кінець? [1/2]: ");
                String mode = scanner.nextLine().trim();
                if (mode.equals("2")) {
                    append = true;
                }
            } else {
                System.out.print("Файл не існує. Створити його? (y/n): ");
                String ans = scanner.nextLine().trim().toLowerCase();
                if (!ans.equals("y") && !ans.equals("yes") && !ans.equals("так") && !ans.equals("т")) {
                    System.out.println("Введення скасовано. Введіть іншу назву файлу.");
                    continue;
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("Помилка створення файлу: " + e.getMessage());
                    continue;
                }
            }
            return new FileWriteConfig(file, append);
        }
    }

    private static class FileWriteConfig {
        File file;
        boolean append;

        FileWriteConfig(File file, boolean append) {
            this.file = file;
            this.append = append;
        }
    }

    private static void runTask1_6() {
        System.out.println("\n--- Виконання Завдання 1 (Варіант 6) ---");
        File inputFile = getReadableFile("Введіть шлях до вхідного файлу з числами: ");

        List<Double> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;
                try {
                    double val = Double.parseDouble(line.replace(',', '.'));
                    numbers.add(val);
                } catch (NumberFormatException e) {
                    System.out.println("Попередження: Рядок " + lineNumber + " (\"" + line + "\") не є некоректним дробовим числом і пропущений.");
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        if (numbers.isEmpty()) {
            System.out.println("Увага: Вхідний файл не містить жодного коректного числа.");
            return;
        }

        List<Double> sortedDesc = new ArrayList<>(numbers);
        sortedDesc.sort(Collections.reverseOrder());

        System.out.println("\n--- Налаштування файлу 1 (усі числа, відсортовані за спаданням) ---");
        FileWriteConfig config1 = getWriteConfig("Введіть шлях до 1-го вихідного файлу: ");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(config1.file, config1.append))) {
            for (Double val : sortedDesc) {
                writer.write(val.toString());
                writer.newLine();
            }
            writer.flush();
            System.out.println("Дані успішно записані в " + config1.file.getName());
        } catch (IOException e) {
            System.out.println("Помилка запису в 1-й файл: " + e.getMessage());
        }

        int countToTake = Math.min(10, sortedDesc.size());
        List<Double> topNumbers = new ArrayList<>(sortedDesc.subList(0, countToTake));

        double sum = 0.0;
        for (Double val : topNumbers) {
            sum += val;
        }

        Collections.sort(topNumbers);

        System.out.println("\n--- Налаштування файлу 2 (сума та числа за зростанням) ---");
        FileWriteConfig config2 = getWriteConfig("Введіть шлях до 2-го вихідного файлу: ");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(config2.file, config2.append))) {
            writer.write("Сума найбльших " + countToTake + " чисел: " + sum);
            writer.newLine();
            writer.write("Числа, з яких складається сума (відсортовані за зростанням):");
            writer.newLine();
            for (Double val : topNumbers) {
                writer.write(val.toString());
                writer.newLine();
            }
            writer.flush();
            System.out.println("Результати успішно записані в " + config2.file.getName());
        } catch (IOException e) {
            System.out.println("Помилка запису в 2-й файл: " + e.getMessage());
        }

        System.out.println("Завдання 1 (6) успішно виконано!");
    }

    private static void runTask2_3() {
        System.out.println("\n--- Виконання Завдання 2 (Варіант 3) ---");
        File inputFile = getReadableFile("Введіть шлях до файлу для обробки (зайві пропуски будуть видалені): ");

        System.out.println("\nОберіть режим збереження:");
        System.out.println("1. Перезаписати той самий файл (прямий доступ / inplace modification)");
        System.out.println("2. Записати результат у новий файл");
        System.out.print("Ваш вибір [1/2]: ");
        String modeChoice = scanner.nextLine().trim();

        if (modeChoice.equals("1")) {
            try (RandomAccessFile raf = new RandomAccessFile(inputFile, "rw")) {
                long fileLength = raf.length();
                byte[] bytes = new byte[(int) fileLength];
                raf.readFully(bytes);

                String content = new String(bytes, "UTF-8");
                String processedContent = cleanSpaces(content);

                byte[] outBytes = processedContent.getBytes("UTF-8");

                raf.seek(0);
                raf.write(outBytes);
                raf.setLength(outBytes.length);

                System.out.println("Файл \"" + inputFile.getName() + "\" успішно відформатовано з використанням прямого доступу (RandomAccessFile)!");
            } catch (IOException e) {
                System.out.println("Помилка обробки файлу через RandomAccessFile: " + e.getMessage());
            }
        } else {
            System.out.println("\n--- Налаштування вихідного файлу ---");
            FileWriteConfig config = getWriteConfig("Введіть шлях до вихідного файлу: ");

            try {
                StringBuilder contentBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        contentBuilder.append(line).append("\n");
                    }
                }

                String processedContent = cleanSpaces(contentBuilder.toString());

                try (RandomAccessFile raf = new RandomAccessFile(config.file, "rw")) {
                    if (config.append) {
                        raf.seek(raf.length());
                    } else {
                        raf.seek(0);
                        raf.setLength(0);
                    }
                    raf.write(processedContent.getBytes("UTF-8"));
                }

                System.out.println("Результат успішно записано у файл \"" + config.file.getName() + "\"!");
            } catch (IOException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }

        System.out.println("Завдання 2 (3) успішно виконано!");
    }

    private static String cleanSpaces(String text) {
        String[] lines = text.split("\r?\n", -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String cleanedLine = line.trim().replaceAll("[ \t]+", " ");
            sb.append(cleanedLine);
            if (i < lines.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}