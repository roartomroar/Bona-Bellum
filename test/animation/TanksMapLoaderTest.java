package animation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thomas
 */
public class TanksMapLoaderTest {

    public TanksMapLoaderTest() {
    }
    
    @Test
    public void TestLoad (){
        TanksMapLoader loader = new TanksMapLoader("TempMap");
        int[][] visualMap = loader.getVisualMap();
        
        assertTrue( visualMap != null);
        
        for (int[] x : visualMap)
            for ( int y: x)
                System.out.print(y + " ");
        
        visualMap = null;
        
        int[][] ResourceMap = loader.getResourcesMap();
        assertTrue( ResourceMap != null);

        for (int[] x : ResourceMap)
            for ( int y: x)
                System.out.print(y + " ");

    }

}