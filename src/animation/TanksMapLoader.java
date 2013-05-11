package animation;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thomas
 */
public class TanksMapLoader {

    /* This function is gross, don't remind me */
    public TanksMapLoader(String fName) {
        Scanner fileReader;
        File file;
        /* Describes whether you're in a specific section */
        String currentSection = "";


        System.out.print("Reached Try Catch\n");
        try {

            file = new File(fName);
            fileReader = new Scanner(file);

            System.out.print("Opened map\n");
            
            if (!fileReader.nextLine().equals("[SIZE]")) {
                Exception up = new Exception("invalid file format");
                throw up;
            }

            
            int _Width = fileReader.nextInt();
            int _Height = fileReader.nextInt();

            System.out.print("Obtained the map size: (" + _Width+"," + _Height+ ")\n");

            /* Initialize the Maps using the data stored in the file */
            VisualMap = new int[_Width][_Height];
            ResourcesMap = new int[_Width][_Height];

            System.out.print("Initialized maps\n");
            /* Get the player start info */
            while (fileReader.hasNext()) {
                System.out.print("Reader has next value\n");                
                System.out.print("Checking Section\n");
                
                if (currentSection.equals("[PLAYER]")) {
                    /* I can assume that the format is correct 
                     * as we are writing everything. */
                    if (fileReader.hasNextInt()) {
                        int x = fileReader.nextInt(),
                                y = fileReader.nextInt();
                        PlayerPositions.add(new Point(x, y));
                    } else if (fileReader.nextLine().equals("[PLAYER]")) {
                        currentSection = "";
                        continue;
                    }
                }

                /* Once again, assuming that the file is formatted correctly
                 * when loading the visual map*/
                if (currentSection.equals("[VISUAL]")) {
                    if (fileReader.hasNextInt()) {
                        for (int[] x : VisualMap) {
                            for (int y : x) {
                                y = fileReader.nextInt();
                            }
                        }
                    }
                } else if (fileReader.nextLine().equals("[VISUAL]")) {
                    currentSection = "";
                    continue;
                }
                
                /* Finally the resources map */
                if (currentSection.equals("[RESOURCE]")) {
                    if (fileReader.hasNextInt()) {
                        for (int[] x : ResourcesMap) {
                            for (int y : x) {
                                y = fileReader.nextInt();
                            }
                        }
                    } else if (fileReader.nextLine().equals("[RESOURCE]")) {
                        currentSection = "";
                        continue;
                    }
                }
            }


        } catch (Exception e) {
            System.out.printf("ERROR: Could not load level %s due to %s",
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

    public ArrayList<Point> getPlayerPositions() {
        return PlayerPositions;
    }
    /* Represents the two kinds of maps we are using */
    int[][] VisualMap;
    int[][] ResourcesMap;
    /* Represents the player start positions*/
    ArrayList<Point> PlayerPositions;
}
