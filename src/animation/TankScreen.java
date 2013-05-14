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
    }

    @Override
    public void update() {
        InputEvent ev = theFrame.getNextEvent();
        
        if (ev == null)
            return;
        
        if (ev instanceof MouseEvent) {
            /* Do what mouses do */
        } else if ( ev instanceof KeyEvent) {
            /* Do what keys do */
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
}