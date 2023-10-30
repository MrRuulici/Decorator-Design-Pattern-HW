import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDataSource implements DataSource {

    @Override
    public String read(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Output the content to the console
            }
        }
        return null; // This DataSource doesn't return the content as a string
    }

    @Override
    public void write(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static void main(String[] args) {
        FileDataSource dataSource = new FileDataSource();

        // Example: Reading from a file and outputting content to the console
        try {
            System.out.println("Reading from file and outputting to console:");
            dataSource.read("example.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example: Writing content to a file on disk
        try {
            String contentToWrite = "This is some content to write to the file.";
            dataSource.write("output.txt", contentToWrite);
            System.out.println("Successfully wrote to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
