package screen;

import org.mini2Dx.core.graphics.Graphics;

public interface Screen 
{
	public void initialise();
	public void update();
	public void render(Graphics g);
	public void reset();
}
