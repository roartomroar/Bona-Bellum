package animation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class TanksMap
{
    public enum Ground
    {
	GRASS,
	DIRT,
	SAND,
	WATER,
	WALL,
    }
    
    public enum Level
    {
	EASY,
	MEDIUM,
	HARD
    }
    
    private Ground[][] map;
    
    private static final int TileSize = 50;
    public static BufferedImage Grass;
    public static BufferedImage Dirt;
    public static BufferedImage Sand;
    public static BufferedImage Water;
    public static BufferedImage Wall;
    
    public TanksMap(Level level)
    {
        try {
            Grass = ImageIO.read(new File("Grass.png"));
            Wall = ImageIO.read(new File("Wall.png"));
            Water = ImageIO.read(new File("Water.png"));
            Dirt = ImageIO.read(new File("Dirt.png"));
        } catch (IOException ex) {
            System.err.println("Could not load images!\n");
            System.exit(-1);
        }

        
	String fName = "";
	
	switch (level)
	{
	    case EASY:
	    case MEDIUM:
	    case HARD:
	    default:
		fName = "TempMap.map";
	}
	
	Scanner fileReader;
	File file;
	
	try
	{
	    file = new File(fName);
	    fileReader = new Scanner(file);
	    
	    if (!fileReader.nextLine().equals("[SIZE]"))
	    {
		IOException e = new IOException("Invalid file format.");
		throw e;
	    }
           
	    int width = fileReader.nextInt();
	    int height = fileReader.nextInt();

            tanks = new ArrayList<Tank>();
            /* Scanning for player positions, assuming correct format  */
            while (fileReader.hasNextInt()) {
                int tankX = fileReader.nextInt(),
                        tankY = fileReader.nextInt();
                tanks.add(new Tank(tankX, tankY));
            } 
            

	    map = new Ground[width][height];
	    
            /* Literally just skipping lines in the file until I get to 
             * where I need to be*/
            while (fileReader.nextLine().equals("[VISUAL]"));
            
	    for (int x = 0; x < width; x++)
	    {
		for (int y = 0; y < height; y++)
		{
                    if (!fileReader.hasNextInt())
                        break;
		    int spot = fileReader.nextInt();
		    switch (spot)
		    {
			case 0:
			    map[x][y] = Ground.GRASS;
			    break;
			case 1:
			    map[x][y] = Ground.DIRT;
			    break;
			case 2:
			    map[x][y] = Ground.SAND;
			    break;
			case 3:
			    map[x][y] = Ground.WATER;
			    break;
		    }
		}
	    }
	    
	} catch (IOException e)
	{
	    System.out.printf("ERROR: Could not load level %s due to %s",
                    fName, e.getMessage());
            map = null;
            Wall = null;
            Water = null;
            Dirt = null;
            Grass = null;
            /* Exit from an error I don't feel like recovering from */
            System.exit(1);

	}
    }
    
    public void DrawMap (Graphics2D gr) {
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[x].length; ++y) {
                int xFinal = (x * TileSize) + TileSize;
                int yFinal = (y * TileSize) + TileSize;
                
                switch (map[x][y]) {
                    case GRASS:
                        gr.drawImage(Grass, x  * TileSize, y * TileSize,
                                xFinal, yFinal, 0, 0, 50, 50, null);
                    case DIRT:
                        gr.drawImage(Dirt, x  * TileSize, y * TileSize,
                                xFinal, yFinal, 0, 0, 50, 50, null);
                    case WATER:
                        gr.drawImage(Water, x  * TileSize, y * TileSize,
                                xFinal, yFinal, 0, 0, 50, 50, null);
                    case WALL:
                        gr.drawImage(Wall, x  * TileSize, y * TileSize,
                                xFinal, yFinal, 0, 0, 50, 50, null);

                }
            }
        }
    }

    public Ground[][] getMap() {
        return map;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }
    
    
    ArrayList<Tank> tanks;
}