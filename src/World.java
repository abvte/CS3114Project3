import java.io.IOException;

/**
 * World class which allows class objects to 
 * communicate. 
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
    
    public World(String dataFile, int buffNum, String stat) throws IOException {
        fileProcess = new FileProcessor(dataFile);
        pool = new BufferPool(buffNum);
        statFile = new StatFileGenerator(stat);
    }
    
}