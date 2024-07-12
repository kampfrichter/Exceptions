import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.*;

public class UserDataProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String lastName = "";
        String firstName = "";
        String middleName = "";
        String gender = "";
        String birthDate = "";
        String phoneNumber = "";

        // Запрашиваем фамилию, имя и отчество
        while (true) {
            System.out.println("Введите фамилию:");
            lastName = scanner.nextLine().trim();
            System.out.println("Введите имя:");
            firstName = scanner.nextLine().trim();
            System.out.println("Введите отчество:");
            middleName = scanner.nextLine().trim();

            if (lastName.isEmpty() || firstName.isEmpty() || middleName.isEmpty()) {
                System.out.println("Фамилия, имя и отчество не могут быть пустыми. Пожалуйста, введите данные заново.");
            } else {
                break;
            }
        }

        // Запрашиваем пол
        while (true) {
            System.out.println("Введите пол (м/ж):");
            gender = scanner.nextLine().trim();

            if (!gender.equals("м") && !gender.equals("ж")) {
                System.out.println("Пол должен быть 'м' или 'ж'. Пожалуйста, введите данные заново.");
            } else {
                break;
            }
        }

        // Запрашиваем дату рождения
        while (true) {
            System.out.println("Введите дату рождения (dd.MM.yyyy):");
            birthDate = scanner.nextLine().trim();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                dateFormat.setLenient(false);
                dateFormat.parse(birthDate);
                break;
            } catch (ParseException e) {
                System.out.println("Неверный формат даты рождения. Пожалуйста, введите данные заново.");
            }
        }

        // Запрашиваем номер телефона
        while (true) {
            System.out.println("Введите номер телефона:");
            phoneNumber = scanner.nextLine().trim();

            try {
                Long.parseUnsignedLong(phoneNumber);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат номера телефона. Пожалуйста, введите данные заново.");
            }
        }

        // Формируем строку данных для записи в файл
        String dataToWrite = "<" + String.join("><",lastName, firstName, middleName,birthDate, phoneNumber, gender) + ">";
        try {
            writeToFile(lastName, dataToWrite);
            System.out.println("Данные успешно записаны.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String lastName, String data) throws IOException {
        Path filePath = Paths.get(lastName + ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(data);
            writer.newLine();
        }
    }
}

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
