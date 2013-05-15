package animation;

import java.awt.Graphics2D;
import java.awt.event.InputEvent;
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
        InputEvent event = theFrame.getNextEvent();

        if (event == null)
            return;
        
        switch (event.getID()) {
        case InputEvent.BUTTON1_DOWN_MASK:
            break;
        case InputEvent.BUTTON2_DOWN_MASK:
            break;
        case InputEvent.BUTTON3_DOWN_MASK:
            break;
        }
         
    }

    @Override
    public Screen getNextScreen() {
        return null;
    }

    @Override
    public void render(Graphics2D gr) {
        map.DrawMap(gr);
        for (Tank t : tanks)
            t.Draw(gr);
    }
    
    private AnimatedFrame theFrame;
    private TanksMap map;
    private ArrayList<Tank> tanks;
    private Tank CurrentTank;
}
