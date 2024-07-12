import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.*;

public class UserDataProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество дата рождения(dd.mm.yyyy) номер телефона пол(м/ж)):");
        String input = scanner.nextLine();

        try {
            String[] userData = input.split(" ");
            if (userData.length != 6) {
                throw new InvalidInputException("Неверное количество данных. Ожидается 6 полей.");
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String birthDate = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            String dataToWrite = String.join(" ", userData);
            writeToFile(lastName, dataToWrite);

            System.out.println("Данные успешно записаны.");

        } catch (InvalidInputException | ParseException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateData(String lastName, String firstName, String middleName,
                                     String birthDate, String phoneNumber, String gender)
            throws ParseException, InvalidInputException {

        // Проверка формата даты
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        dateFormat.parse(birthDate);

        // Проверка формата номера телефона
        try {
            Long.parseUnsignedLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Неверный формат номера телефона.");
        }

        // Проверка пола
        if (!gender.equals("m") && !gender.equals("f")) {
            throw new InvalidInputException("Пол должен быть 'м' или 'ж'.");
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
