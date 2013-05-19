package animation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/*
 * Really basic as of yet
 */
public class Tank {

    private double x, y;
    private int hp, dolards;
    private double fuel;

    public Tank(double x, double y) throws IOException {
        this.x = x;
        this.y = y;
        this.hp = 100;
        this.fuel = 100;
        this.dolards = 0;
        this.isMoving = false;
        this.isDead = false;
	this.xTarget = 0;
	this.yTarget = 0;
	this.speed = 5;
	this.turnSpeed = Math.toRadians(5);
        this.
	
        image = ImageIO.read(new File("RedTank.png"));
        TankRect = new Rectangle((int)x, (int)y, width , height);
        retPoint = new Point((int)x, (int)y);
    }
    
    public void Draw(Graphics2D gr) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Direction, x + width / 2, y + height / 2);
        transform.translate(x, y);
        gr.drawImage(image, transform, null);

        //        gr.drawImage(image, (int)(x),(int) (y),
//                (int)(x + width), (int)(y + height),
//                0 , 0, 50, 50,null);
    }
    
    public void moveForwards()
    {
	if (fuel <= 0)
	    return;
	
	// Get the x and y componets.
	double xComp = (speed * Math.cos(Direction));
	double yComp = (speed * Math.sin(Direction));
	
	// Collision checking.

        boolean CanMoveX = x + xComp <= map.length * width - width;
        boolean CanMoveY = y + yComp <= map[0].length * height - height;
        
	// Collision checking.
	
        if (CanMoveX)
            x += xComp;
	if (CanMoveY)
            y += yComp;
        
        if (CanMoveX || CanMoveY)
            --fuel;

        TankRect.setLocation((int)x, (int)y);
    }
    
    public void moveBackwards()
    {
	if (fuel <= 0)
	    return;
	
	// Get the x and y componets.
	double xComp = (speed * Math.cos(Direction));
	double yComp = (speed * Math.sin(Direction));
	
        boolean CanMoveX = x + xComp >= 0;
        boolean CanMoveY = y + yComp >= 0;
        
	// Collision checking.
	
	// Subtract, because it's going backwards.
        if (CanMoveX)
            x -= xComp;
	if (CanMoveY)
            y -= yComp;
        
        if (CanMoveX || CanMoveY)
            --fuel;
      
        TankRect.setLocation((int)x, (int)y);

    }
    
    public void Update() {
        if (fuel == 0)
            isMoving = false;
        if (hp == 0)
            isDead = true;
    }
    
    private boolean CheckCollision(double x, double y) {
        return true;
    }
    
    public void turnClockWise() {
        Direction -= turnSpeed;
	if (Direction < 0) // Loop the angle back.
	    Direction += Math.PI * 2;
    }
    
    public void turnCounterClockWise()
    {
	Direction += turnSpeed;
	if (Direction >= Math.PI * 2) // Loop the angle back.
	    Direction -= Math.PI * 2;
    }
    
    public void shoot()
    {
	// Shoot
    }
    
    public double getTargetX()
    {
	return xTarget;
    }
    
    public double getTargetY()
    {
	return yTarget;
    }
    
    public void setTarget(double x, double y)
    {
	xTarget = x;
	yTarget = y;
    }

    public static void setMap(TanksMap.Ground[][] map) {
        Tank.map = map;
    }

    public double getFuel() {
        return fuel;
    }
    
    public void refill() {
        fuel = 100;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
    
    private static TanksMap.Ground[][] map;
    private static BufferedImage image;
    private AffineTransform gunTransformation;
    private Rectangle TankRect;
    
    public static final int width = 50;
    public static final int height = 50;
    
    private double speed;
    private double turnSpeed;
    
    private boolean isMoving;
    private boolean isDead;
    
    private double xTarget;
    private double yTarget;
    private double Direction;
    private Point retPoint;
    
    

}
