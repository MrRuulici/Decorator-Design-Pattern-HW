import java.io.IOException;

public interface DataSource {
    String read(String filePath) throws IOException;
    void write(String filePath, String content) throws IOException;
}