import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to generate the stat file associated with quicksort.
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class StatFileGenerator {
    private String fileName;

    /**
     * Constructor
     * 
     * @param statFile
     *            Name of the file
     */
    public StatFileGenerator(String statFile) {
        fileName = statFile;
    }

    /**
     * Method to write stats to the file
     * 
     * @param hits
     *            Number of cache hits
     * @param reads
     *            Number of disk reads
     * @param writes
     *            Number of disk writes
     * @param time
     *            Time it took to quicksort in ms
     * @param file
     *            File name
     * @throws IOException
     */
    public void writeStats(int hits, int reads, int writes, int time,
            String file) throws IOException {
        FileWriter fileWrite;
        fileWrite = new FileWriter(fileName, true);
        fileWrite.write("Sort on " + file + "\n");
        fileWrite.write("Cache Hits: " + hits + "\n");
        fileWrite.write("Disk Reads: " + reads + "\n");
        fileWrite.write("Disk Writes: " + writes + "\n");
        fileWrite.write("Time is " + time + "\n");
        fileWrite.close();
    }
}