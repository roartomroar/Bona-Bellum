package animation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class TankScreen implements Screen {

    public TankScreen(AnimatedFrame theFrame) {
        this.theFrame = theFrame;
        /* This path is for the sake of debugging */
        TanksMapLoader loader =
                new TanksMapLoader("TempMap" + ".map");

        VisualMap = loader.getVisualMap();
        ResourcesMap = loader.getResourcesMap();
        players = loader.getPlayerPositions();
        try {
            grassImage = ImageIO.read(new File("Grass.png"));
            tanks = ImageIO.read(new File("RedTank.png"));
        } catch (IOException ex) {
            System.err.println("Could not load images!\n");
            System.exit(-1);
        }


    }

    @Override
    public void update() {
        /* TODO: Implement the updating */
    }

    @Override
    public Screen getNextScreen() {
        return null;
    }

    @Override
    public void render(Graphics2D gr) {
        for (int x = 0; x < VisualMap.length; ++x) {
            for (int y = 0; y < VisualMap[x].length; ++y) {
                if (VisualMap[x][y] == 0) {
                    gr.drawImage(grassImage, x * TileSize, y * TileSize,
                            x * TileSize + TileSize, y * TileSize + TileSize,
                            0, 0, 50, 50, null);
                } else if (VisualMap[x][y] == 1) {
                    gr.drawImage(grassImage, x * TileSize, y * TileSize,
                            x * TileSize + TileSize, y * TileSize + TileSize,
                            0, 0, 50, 50, null);
                }
            }
       }
        
        for (int j = 0; j < players.size(); ++j) {
            int x = players.get(j).x * TileSize;
            int y = players.get(j).y * TileSize;
            
            gr.drawImage(tanks, x, y, x + TileSize, y + TileSize,
                    0, 0, 50, 50, null);
        }

    }
    
    final int TileSize = 50;
    private AnimatedFrame theFrame;
    private int[][] VisualMap;
    private int[][] ResourcesMap;
    /*
     * No tank class as of yet.
     */
    ArrayList<Point> players;
    BufferedImage grassImage;
    BufferedImage tanks;
}