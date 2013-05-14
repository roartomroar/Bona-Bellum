package animation;

import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class AnimatedFrame extends JFrame
    implements Runnable, MouseListener, KeyListener
{
	public AnimatedFrame()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setIgnoreRepaint(true);
		targetFPS = 60;  // How many frames are we going to generate per second?
		nanosPerUpdate = 1000000000 / targetFPS;
        
                eventQueue = new LinkedList<InputEvent>();

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent evt)
			{
				formWindowClosing(evt);
			}
                        
		});
                
                addKeyListener(this);
                addMouseListener(this);
	}

	private Screen createInitialScreen()
	{
		return new TankScreen(this);
	}

	private void initGame()
	{
		currentScreen = createInitialScreen();

		if (initFSMode())
		{

			gameThread = new Thread(this);
			gameThread.start();
		}
		else
		{
			System.out.println("initFSMode() returned false; quitting.");
			dispose();
		}
	}

	private boolean initFSMode()
	{
		GraphicsEnvironment env =
			GraphicsEnvironment.getLocalGraphicsEnvironment();
		dev = env.getDefaultScreenDevice();
		if (!dev.isFullScreenSupported())
		{
			JOptionPane.showMessageDialog(this,
				"Full-screen mode not supported.",
				"Error",
				JOptionPane.WARNING_MESSAGE);
			return false;
		}
		dev.setFullScreenWindow(this);
		mode = dev.getDisplayMode();

		// Initial frame rate guess
		lastFPS = mode.getRefreshRate();
		if (lastFPS == 0)
			lastFPS = 30;  // Just give _some_ nonzero guess
		if (createBuffering())
			return true;
		else
			return false;
	}

	private boolean createBuffering()
	{
		bstrat = getBufferStrategy();
		createBufferStrategy(2);
		// Wait up to 1 second for a buffer strategy to be created
		long targetNanos = System.nanoTime() + 1000000000;
		do
		{
			bstrat = getBufferStrategy();
		} while (bstrat == null && targetNanos < System.nanoTime());
		if (bstrat == null)
		{
			return false;
		}
		return true;
	}

//	private void createHideCursor()
//	{
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//
//		// Get the smallest valid cursor size
//		Dimension dim = toolkit.getBestCursorSize(1, 1);
//
//		// Create a new image of that size with an alpha channel
//		BufferedImage cursorImg = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB);
//
//		// Get a Graphics2D object to draw to the image
//		Graphics2D g2d = cursorImg.createGraphics();
//
//		// Set the background 'color' with 0 alpha and clear the image
//		g2d.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
//		g2d.clearRect(0, 0, dim.width, dim.height);
//
//		// Dispose the Graphics2D object
//		g2d.dispose();
//
//		// Now create the cursor using that transparent image
//		hiddenCursor = toolkit.createCustomCursor(cursorImg, new Point(0, 0), "hiddenCursor");
//		setCursor(hiddenCursor);
//	}

	@Override
	public void run()
	{
		running = true;
		long nextFrame = System.nanoTime() + nanosPerUpdate;
		while (running)
		{
			while (nextFrame < System.nanoTime())
			{
				updateGame();
				upsCount++;
				nextFrame += nanosPerUpdate;
			}
			drawFrame();
			if (fpsTimeGoal == 0)
			{
				fpsTimeGoal = System.nanoTime() + 5000000000L;
			}
			else if (fpsTimeGoal < System.nanoTime())
			{
				lastFPS = fpsCount / 5.0f;
				lastUPS = upsCount / 5.0f;
				fpsTimeGoal += 5000000000L;
				System.out.println("5-second measurement: " +
						lastFPS + " fps; " + lastUPS + " ups");
				fpsCount = 0;
				upsCount = 0;
			}
			else
			{
				fpsCount++;
			}
		}
	}

	public void updateGame()
	{
		currentScreen.update();
		Screen next = currentScreen.getNextScreen();
		if (next != null)
			currentScreen = next;
	}

	private void drawFrame()
	{
		try
		{
			Graphics2D gr = (Graphics2D)bstrat.getDrawGraphics();
			render(gr);
			gr.dispose();
			if (!bstrat.contentsLost())
				bstrat.show();
			else
				System.out.println("Contents Lost");
			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)
			Toolkit.getDefaultToolkit().sync();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			running = false; 
		}
	}

	public void render(Graphics2D gr)
	{
		currentScreen.render(gr);
	}

	private void formWindowClosing(WindowEvent evt)
	{
		running = false;
		dev.setFullScreenWindow(null);
	}

	public static void main(String[] args)
	{
		AnimatedFrame frame = new AnimatedFrame();
		frame.initGame();
	}
    
    public InputEvent getNextEvent () {
        return eventQueue.poll();
    }



    @Override
    public void mouseClicked(MouseEvent me) {
        eventQueue.offer(me);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        /* Do Nothing */
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        /* Do Nothing */        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        /* Do Nothing */
    }

    @Override
    public void mouseExited(MouseEvent me) {
        /* Do Nothing */
    }


        @Override
    public void keyTyped(KeyEvent ke) {
            eventQueue.offer(ke);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        eventQueue.offer(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        eventQueue.offer(ke);
    }

    // Graphics mode info
	private GraphicsDevice dev;
	private DisplayMode mode;
	private BufferStrategy bstrat;
	private Cursor hiddenCursor;
	// FPS limiter
	private int targetFPS;
	private long nanosPerUpdate;
	// FPS timing
	private long fpsTimeGoal;
	private int fpsCount;
	private int upsCount;
	private float lastFPS;
	private float lastUPS;
	// Game data
	private volatile boolean running;
	private Thread gameThread;
	private Screen currentScreen;
    //For handing events
    private volatile Queue<InputEvent> eventQueue;


}
