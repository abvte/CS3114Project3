import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * This file is used to process the data file.
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class FileProcessor {
    private String fileName;
    RandomAccessFile file;
    int blockNumber;
    private static final int BLOCK_SIZE = 4096;

    /**
     * Constructor
     * 
     * @param file
     *            Data file
     * @throws FileNotFoundException
     */
    public FileProcessor(String name) throws FileNotFoundException {
        fileName = name;
        blockNumber = 0;
        file = new RandomAccessFile(fileName, "rw");
    }

    public int calculateBlocks() throws IOException {
        long size = file.length();
        blockNumber = (int) (BLOCK_SIZE / size);
        return blockNumber;
    }

    public byte[] getBytes(byte[] space, int pos) throws IOException {
        int offset = pos * 4096;
        file.read(space, offset, BLOCK_SIZE);
        return space;
    }
}