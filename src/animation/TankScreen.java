package animation;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class TankScreen implements Screen {

    public TankScreen(AnimatedFrame theFrame) {
        this.theFrame = theFrame;
        
        map = new TanksMap(TanksMap.Level.EASY);
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
        map.DrawMap(gr);
    }
    
    private AnimatedFrame theFrame;
    private TanksMap map;
    private ArrayList<Tank> tanks;
}