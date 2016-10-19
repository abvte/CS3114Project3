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
     * Buffer pool
     */
    protected BufferPool pool;
    /**
     * Stat file generator 
     */
    protected StatFileGenerator statFile;
    
    public World(String dataFile, int buffNum, String stat) {
        pool = new BufferPool(buffNum);
        statFile = new StatFileGenerator(stat);
    }
}