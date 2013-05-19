package animation;

import static animation.Tank.Color.RED;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Gun
{
    double Direction, x, y;
    private Tank.Color color;

    public Gun(double x, double y, BufferedImage image, Tank.Color color) {
        this.Direction = 0;
        this.x = x;
        this.y = y;
        this.turnSpeed = Math.toRadians(5);
        this.color = color;
        
        this.image = image;
    }
    
    
    
    public void Draw(Graphics2D gr) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Direction, x + width / 2, y + height / 2);
        transform.translate(x, y);
        switch (color) {
            case RED:
                gr.drawImage(image.getSubimage(0, 0, 50, 50), transform, null);
                break;
            case YELLOW:
                gr.drawImage(image.getSubimage(50, 50, 50, 50), transform, null);
                break;
            case PURPLE:
                gr.drawImage(image.getSubimage(100, 50, 50, 50), transform, null);
                break;
            case WHITE:
                gr.drawImage(image.getSubimage(150, 50, 50, 50), transform, null);
                break;
            case GREEN: 
                gr.drawImage(image.getSubimage(200, 50, 50, 50), transform, null);
                break;
            case BLUE:
                gr.drawImage(image.getSubimage(250, 50, 50, 50), transform, null);
                break;
        }
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
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
    
    private static BufferedImage image;
    private double turnSpeed;
    
    
    public static final int width = 50;
    public static final int height = 50;
    


    
}