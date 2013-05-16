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
        this.fuel = 50;
        this.dolards = 0;
        this.isMoving = false;
        this.isDead = false;
        image = ImageIO.read(new File("RedTank.png"));
        TankRect = new Rectangle((int)x, (int)y, width , height);
        retPoint = new Point((int)x, (int)y);
    }
    
    
    public void Draw(Graphics2D gr) {
        gr.drawImage(image, (int)(x * width),(int) (y * height),
                (int)(x * width + width), (int)(y * height + height),
                0 , 0, 50, 50,null);
    }
    
    public void Move(double xLoc, double yLoc) {
        if (fuel == 0)
            return;
        
        if (Direction / 90 == 0 || Direction / 90 == 2) {
            
        if (CheckCollision(x + 1, y)) {
            if (x != xLoc)
                    ++x;
            } else {
                Turn (5);
                fuel -= 1;
            }
        } else if (Direction / 90 == 1 || Direction / 90 == 3) {
            /* At a vertical slope */
            if (CheckCollision(x, y + 1)) {
                if (y != yLoc)
                    if (y < yLoc)
                        ++y;
                     else
                        --y;
             } else {
                Turn (5);
            }
        } else if (Direction / 45 == 1 ||
                Direction / 45 == 3 ||
                Direction / 45 == 5 ||
                Direction / 45 == 7 ) {
            /* At a diagnal slope */
            ++x;
            ++y;
        } else {
            int xSlope = (int) (xLoc - x);
            int ySlope = (int) (yLoc - y);

            x += xSlope;
            y += ySlope;
        }
        
    }
    
    public void Update() {
        if (xDelta == 0 && yDelta == 0 || fuel == 0)
            isMoving = false;
        if (hp == 0)
            isDead = true;
    }
    
    private boolean CheckCollision(double x, double y) {
        return true;
    }
    
    public void Turn(double theta) {
        Direction += theta;
    }
    
    private static TanksMap.Ground[][] map;
    private static BufferedImage image;
    private AffineTransform gunTransformation;
    private Rectangle TankRect;
    
    public static final int width = 50;
    public static final int height = 50;
    
    private double xDelta;
    private double yDelta;
    
    private boolean isMoving;
    private boolean isDead;
    
    private double Direction;
    private Point retPoint;

}
