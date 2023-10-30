import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Client {

	public static void main(String[] args) {
		// Create a base FileDataSource
        DataSource fileDataSource = new FileDataSource();

        // Create an EncryptionDecorator and wrap the FileDataSource
        DataSource encryptedDataSource = new EncryptionDecorator(fileDataSource);

        // Create a CompressionDecorator and wrap the EncryptionDecorator
        DataSource compressedAndEncryptedDataSource = new CompressionDecorator(encryptedDataSource);

        // Example: Writing compressed and encrypted content to a file
        try {
            String contentToWrite = "This is some content to write to the file.";
            compressedAndEncryptedDataSource.write("output.txt", contentToWrite);
            System.out.println("Successfully wrote compressed and encrypted content to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example: Reading and decompressing and decrypting content from a file
        try {
            System.out.println("Reading from file, decompressing and decrypting:");
            String content = compressedAndEncryptedDataSource.read("output.txt");
            System.out.println("Read content:\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
