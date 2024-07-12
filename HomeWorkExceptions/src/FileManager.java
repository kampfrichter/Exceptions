import java.io.*;
import java.nio.file.*;

public class FileManager {
    public void writeToFile(User user) throws IOException {
        String lastName = user.getLastName();
        Path filePath = Paths.get(lastName + ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(user.toString());
            writer.newLine();
        }
    }
}
