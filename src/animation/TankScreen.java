package animation;

import java.awt.Color;
import java.awt.Graphics2D;
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
	// Keep going untill all of the events are handled.
        while (event != null)
	{
	    switch (event)
	    {
		case Shoot:
		    CurrentTank.shoot();
		    break;
		case Turn_CW:
		    CurrentTank.turnClockWise();
		    break;
		case Turn_CCW:
		    CurrentTank.turnCounterClockWise();
		    break;
		case Move_Forwards:
		    CurrentTank.moveForwards();
		    break;
		case Move_Backwards:
		    CurrentTank.moveBackwards();
		    break;
	    }
	    event = theFrame.getNextEvent();
        if (CurrentTank.getFuel() <= 0) {
            int index = tanks.indexOf(CurrentTank);
            //Refill tanks when you're done with them
            if (index >= tanks.size() - 1){
                for (int i = 0; i < tanks.size(); ++i)
                    (tanks.get(i)).refill();
                index = -1;
            }
            CurrentTank = tanks.get(index + 1);
        }
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
        
        gr.setColor(Color.black);
        gr.drawString("Fuel: " + CurrentTank.getFuel(), (int)CurrentTank.getX(),
                (int)CurrentTank.getY());
        }    
    public Tank getCurrentTank()
    {
	return CurrentTank;
    }
    
    private AnimatedFrame theFrame;
    private TanksMap map;
    private ArrayList<Tank> tanks;
    private Tank CurrentTank;
    private javax.swing.JScrollPane mapPane;
}
