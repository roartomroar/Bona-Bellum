package animation;

import java.awt.Color;
import java.awt.Graphics2D;

public class BouncingBallScreen implements Screen
{
	public BouncingBallScreen(AnimatedFrame theFrame)
	{
		this.theFrame = theFrame;
		ballX = ballY = 10;
		xDelta = 12;
		yDelta = 7;
	}

	@Override
	public void update()
	{
		ballX += xDelta;
		if (ballX < 0 || ballX > theFrame.getWidth() - ballSize)
			xDelta = -xDelta;

		ballY += yDelta;
		if (ballY < 0 || ballY > theFrame.getHeight() - ballSize)
			yDelta = -yDelta;
	}

	@Override
	public void render(Graphics2D gr)
	{
		gr.setColor(Color.BLACK);
		gr.fillRect(0, 0, theFrame.getWidth(), theFrame.getHeight());

		gr.setColor(Color.WHITE);
		gr.fillOval(ballX, ballY, ballSize, ballSize);
	}

	@Override
	public Screen getNextScreen()
	{
		return null;  // Never change to another screen.
	}

	private AnimatedFrame theFrame;
	private int ballX;
	private int ballY;
	private int xDelta;
	private int yDelta;
	private static final int ballSize = 10;
}
