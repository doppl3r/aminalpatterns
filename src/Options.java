import java.awt.Graphics2D;
import java.io.IOException;

import buttons.Button;

/**
 */
public class Options {
	private int mainX = 480;
	private int mainY = 48;
	public Browser browser;
	public ColorDisplay colorDisplay;
	public Button link;
	public boolean linked;
	
	public Options(){
		browser = new Browser(mainX, mainY);
		colorDisplay = new ColorDisplay(mainX, mainY+128);
		link = new Button(Window.tt.link, 640-144, 480-96, false);
		//browse.resize(1);
		
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		browser.draw(g);
		colorDisplay.draw(g);
		link.draw(g);
	}
	public void update(){
		browser.update();
		colorDisplay.update();
	}
	/**
	 * Method down.
	 * @param x int
	 * @param y int
	 */
	public void down(int x, int y){
		browser.down(x, y);
		colorDisplay.down(x, y);
		link.down(x,y);
	}
	/**
	 * Method move.
	 * @param x int
	 * @param y int
	 */
	public void move(int x, int y){
		browser.move(x, y);
		colorDisplay.move(x, y);
		link.move(x, y);
	}
	/**
	 * Method up.
	 * @param x int
	 * @param y int
	 */
	public void up(int x, int y){
		browser.up(x, y);
		colorDisplay.up(x, y);
		if (link.up(x, y)){
			if (!linked){
				try { java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://doppl3r.com/aminalpatterns.html"));
				} catch (IOException e) { e.printStackTrace(); }
				link.update(640-144, 480-32);
				linked = true;
			}
			else{
				linked = false;
				link.update(640-144, 480-96);
			}
		}
	}
	/**
	 * Method hover.
	 * @param x int
	 * @param y int
	 */
	public void hover(int x, int y){
		browser.hover(x, y);
	}
}
