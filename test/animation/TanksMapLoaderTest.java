/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        
        assertFalse( visualMap == null);
        
        for (int[] x : visualMap)
            for ( int y: x)
                System.out.print(y + " ");
        
        visualMap = null;
        
        int[][] ResourceMap = loader.getResourcesMap();
        assertFalse( ResourceMap == null);

        for (int[] x : ResourceMap)
            for ( int y: x)
                System.out.print(y + " ");

    }

}