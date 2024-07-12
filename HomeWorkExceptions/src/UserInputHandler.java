import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UserInputHandler {
    private Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public User collectUserData() throws InvalidInputException {
        String lastName = promptForString("Введите фамилию:");
        String firstName = promptForString("Введите имя:");
        String middleName = promptForString("Введите отчество:");
        String gender = promptForGender();
        String birthDate = promptForBirthDate();
        String phoneNumber = promptForPhoneNumber();

        return new User(lastName, firstName, middleName, gender, birthDate, phoneNumber);
    }

    private String promptForString(String prompt) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            } else {
                System.out.println("Поле не может быть пустым. Пожалуйста, введите данные заново.");
            }
        }
        return input;
    }

    private String promptForGender() throws InvalidInputException {
        String gender;
        while (true) {
            System.out.println("Введите пол (м/ж):");
            gender = scanner.nextLine().trim();
            if (gender.equals("м") || gender.equals("ж")) {
                break;
            } else {
                System.out.println("Пол должен быть 'м' или 'ж'. Пожалуйста, введите данные заново.");
            }
        }
        return gender;
    }

    private String promptForBirthDate() throws InvalidInputException {
        String birthDate;
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
        return birthDate;
    }

    private String promptForPhoneNumber() throws InvalidInputException {
        String phoneNumber;
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
        return phoneNumber;
    }
}
