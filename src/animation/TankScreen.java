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
        InputEvent event = theFrame.getNextEvent();

        
        if (event == null)
            return;

        
        if (event instanceof MouseEvent) {
            MouseEvent mEvent = (MouseEvent) event;
            System.out.println("Got an event!");
            if (mEvent.getButton() == MouseEvent.BUTTON1)
                CurrentTank.Move(mEvent.getX(), mEvent.getY());
            else if (mEvent.getButton() == MouseEvent.BUTTON2)
                return;
            else if (mEvent.getButton() == MouseEvent.BUTTON3)
                return;
        } else if (event instanceof KeyEvent) {
            /* Do nothing as of yet */
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
