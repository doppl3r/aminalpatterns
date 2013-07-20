import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;

import buttons.Button;


/**
 */
public class ColorDisplay {
	private int mainX;
	private int mainY;
	private Paint gradient;
	public Pallete pallete;
	public Button left;
	public Button right;
	
	/**
	 * Constructor for ColorDisplay.
	 * @param x int
	 * @param y int
	 */
	public ColorDisplay(int x, int y){
		mainX = x;
		mainY = y;
		pallete = new Pallete();
		
		gradient = new GradientPaint(
				0, 0, new Color(170,150,150),
                0, 480, new Color(70,50,50)); //480 = height
		
		left  = new Button(Window.tt.left,  mainX+16, mainY + 165, false);
		right = new Button(Window.tt.right, mainX+48, mainY + 165, false);
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		//sample colors
		g.setPaint(gradient);
		g.fillRect(mainX-5, mainY-5, 106, 170);
		g.setColor(new Color(75,75,75));
		g.drawRect(mainX-1, mainY-1, 96+1, 160+1);
		g.setColor(new Color(255,255,255));
		g.fillRect(mainX, mainY, 96, 160);
		//draw pallete colors
		pallete.draw(g, mainX, mainY);
		left.draw(g);
		right.draw(g);
	}
	public void update(){
		pallete.update();
	}
	/**
	 * Method down.
	 * @param x int
	 * @param y int
	 */
	public void down(int x, int y){
		left.down(x, y);
		right.down(x, y);
	}
	/**
	 * Method move.
	 * @param x int
	 * @param y int
	 */
	public void move(int x, int y){
		left.move(x, y);
		right.move(x, y);
	}
	/**
	 * Method up.
	 * @param x int
	 * @param y int
	 */
	public void up(int x, int y){
		if (left.up(x, y)){
			pallete.nextPalette(1);
			Window.panel.options.browser.updateMap();
		}
		if (right.up(x, y)){
			pallete.nextPalette(-1);
			Window.panel.options.browser.updateMap();
		}
	}
}
