package animation;

import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TankScreen implements Screen {

    public TankScreen(AnimatedFrame theFrame) {
        this.theFrame = theFrame;
        
        map = new TanksMap(TanksMap.Level.EASY);
        tanks = map.getTanks();
        CurrentTank = tanks.get(0);
    }

    @Override
    public void update() {
        AnimatedFrame.Event event = theFrame.getNextEvent();

        if (event == null)
            return;
         
        switch (event) {
        case LeftButtonClick:
            System.out.println("Hello");
            break;
        case RightButtonClick:
            System.out.println("Hello");
            break;
        default:
                /* Do nothing */
        }
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
    private Tank CurrentTank;
}
