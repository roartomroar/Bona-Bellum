package animation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author thomas
 */
public class TanksMapLoader {

    public TanksMapLoader(String fName){
        BufferedReader fileReader;
        File file;
        
    
        try {
            
            file = new File(fName);
            fileReader =  new BufferedReader(new FileReader(file));
            
        } catch (FileNotFoundException e){
            System.err.printf("Could not load %s due to %s",
                    fName, e.toString());
            VisualMap = null;
            ResourcesMap = null;
            /* Exit from an error I don't feel like recovering from */
            System.exit(1);
        } catch (IOException e) {
            System.err.printf("Could not read %s due to %s",
                    fName, e.toString());
            VisualMap = null;
            ResourcesMap = null;
            /* Exit from an error I don't feel like recovering from */
            System.exit(1);
        }
        
    }

    public int[][] getResourcesMap() {
        return ResourcesMap;
    }

    public int[][] getVisualMap() {
        return VisualMap;
    }
    
    /* Represents the two kinds of maps we are using */
    int[][] VisualMap;
    int[][] ResourcesMap;
    
    /* Represents the player start positions*/
    ArrayList<Point> PlayerPositions;
    


}
