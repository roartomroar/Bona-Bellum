package animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
    private int x;
    private int y;
    
    public static final BufferedImage Grass;
    public static final BufferedImage Dirt;
    public static final BufferedImage Sand;
    public static final BufferedImage Water;
    public static final BufferedImage Wall;
    
    public TanksMap(Level level)
    {
	if (Grass == null)
	    
	
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
	    
	    map = new Ground[width][height];
	    
	    for (int x = 0; x < width; x++)
	    {
		for (int y = 0; y < height; y++)
		{
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
	    
	}
    }
    
    
}