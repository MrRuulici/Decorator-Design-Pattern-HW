import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.Base64;

public class CompressionDecorator implements DataSource {
    private DataSource dataSource;

    public CompressionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) throws IOException {
        String compressedContent = dataSource.read(filePath);
        // Decompress the content
        String decompressedContent = decompress(compressedContent);
        return decompressedContent;
    }

    @Override
    public void write(String filePath, String content) throws IOException {
        // Compress the content
        String compressedContent = compress(content);
        dataSource.write(filePath, compressedContent);
    }

    // Implement compression and decompression methods as needed
    private String compress(String content) {
        byte[] input = content.getBytes();
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        byte[] output = new byte[input.length];
        int compressedDataLength = deflater.deflate(output);
        return Base64.getEncoder().encodeToString(output, 0, compressedDataLength);
    }

    private String decompress(String content) throws DataFormatException {
        byte[] compressedData = Base64.getDecoder().decode(content);
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);
        byte[] output = new byte[content.length() * 3]; // Provide a reasonable initial size
        int decompressedDataLength = inflater.inflate(output);
        inflater.end();
        return new String(output, 0, decompressedDataLength);
    }
}
