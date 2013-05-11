package animation;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.rmi.CORBA.Tie;

public class TankScreen implements Screen {
    
    
    public TankScreen(AnimatedFrame theFrame) {
        this.theFrame = theFrame;
        /* This path is for the sake of debugging */
        TanksMapLoader loader =
                new TanksMapLoader("TempMap");
        
        VisualMap = loader.getVisualMap();
        ResourcesMap = loader.getResourcesMap();
        
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
                if (VisualMap[x][y] == 0)
                    gr.setColor(Color.green);
                else gr.setColor(Color.gray);
                gr.drawRect(TileSize * x, TileSize * y,
                        TileSize, TileSize);
            }
        }
    }

    
    final int TileSize = 50;
    
    private AnimatedFrame theFrame;
    private int[][] VisualMap;
    private int[][] ResourcesMap;
    /*
     * No tank class as of yet.
     */
}