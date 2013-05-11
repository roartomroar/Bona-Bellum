package animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class TankScreen implements Screen {
    
    
    public TankScreen(AnimatedFrame theFrame) {
        this.theFrame = theFrame;
        /* This path is for the sake of debugging */
        TanksMapLoader loader =
                new TanksMapLoader("TempMap");
        
        VisualMap = loader.getVisualMap();
        ResourcesMap = loader.getResourcesMap();
        players = loader.getPlayerPositions();
        
        
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
            for (int y = 0; y < VisualMap[x].length; ++y){
                if (VisualMap[x][y] == 0) {
                    gr.setColor(Color.green);
                    gr.setBackground(Color.green);
                } else {
                    gr.setColor(Color.gray);
                    gr.setBackground(Color.gray);
                }
                gr.drawRect(TileSize * x, TileSize * y,
                        TileSize, TileSize);
            }
        }
        
        gr.setBackground(Color.blue);
        for (Point p: players) {
            gr.drawOval( TileSize * p.x, TileSize * p.y,
                    TileSize, TileSize);
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
}