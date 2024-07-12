import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        UserInputHandler inputHandler = new UserInputHandler();
        FileManager fileManager = new FileManager();

        try {
            User user = inputHandler.collectUserData();
            fileManager.writeToFile(user);
            System.out.println("Данные успешно записаны.");
        } catch (InvalidInputException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
