package bonnabellum;

import java.awt.Graphics2D;

public interface Screen
{
	void update();
	void render(Graphics2D gr);
	Screen getNextScreen();
}
