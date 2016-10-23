import java.io.IOException;

/**
 * World class which allows class objects to communicate.
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class World {
    /**
     * File processor
     */
    protected FileProcessor fileProcess;
    /**
     * Buffer pool
     */
    protected BufferPool pool;
    /**
     * Stat file generator
     */
    protected StatFileGenerator statFile;
    /**
     * Class that performs quick sort on the file
     */
    protected Sort sorter;

    /**
     * Constructor
     * 
     * @param dataFile
     *            Data file
     * @param buffNum
     *            Number of buffers
     * @param stat
     *            Statistics file
     * @throws IOException
     */
    public World(String dataFile, int buffNum, String stat) throws IOException {
        fileProcess = new FileProcessor(dataFile);
        pool = new BufferPool(buffNum, fileProcess);
        statFile = new StatFileGenerator(stat); 
        sorter = new Sort(pool);
    }
    
    /**
     * Runs the quicksort algorithm by communicating to
     * various classes
     * @throws IOException 
     */
    public void run() throws IOException {
        sorter.sortData();
        sorter.flushPool();
    }

}