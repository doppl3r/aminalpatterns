package buttons;
import java.awt.Graphics2D;
import java.awt.Image;

import audio.AudioHandler;


import textures.SpriteSheet;

/**
 */
public class Button {
	private boolean pressed;
	private boolean hide;
	private boolean center;
	private SpriteSheet sprite;
	private double x;
	private double y;
	private double xSize;
	private double ySize;
	private double padding;
	
	/**
	 * Constructor for Button.
	 * @param newImage Image
	 * @param x int
	 * @param y int
	 * @param center boolean
	 */
	public Button(Image newImage, int x, int y, boolean center){
		SpriteSheet newSprite = new SpriteSheet(newImage, 1, 2, 0.0);
		this.x=x;
		this.y=y;
		this.center=center;
		sprite = new SpriteSheet(newSprite.getImage(), 
				newSprite.getHFrames(), newSprite.getVFrames(), newSprite.getRate());
		xSize = sprite.getImageWidth();
		ySize = sprite.getImageHeight();
		if (center) sprite.center();
		sprite.update(x, y);
		padding=0;
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		if (!hide) sprite.draw(g);
	}
	/**
	 * Method resize.
	 * @param newWidth int
	 * @param newHeight int
	 */
	public void resize(int newWidth, int newHeight){
		xSize=newWidth;
		ySize=newHeight;
		sprite.resize(newWidth, newHeight);
		sprite.update(x, y);
	}
	/**
	 * Method resize.
	 * @param ratio int
	 */
	public void resize(int ratio){
		xSize *= ratio;
		ySize *= ratio;
		sprite.resize((int)xSize, (int)ySize);
		sprite.update(x, y);
	}
	/**
	 * Method down.
	 * @param x1 int
	 * @param y1 int
	 * @return boolean
	 */
	public boolean down(int x1, int y1){
		if (!hide){
			if (center){
				if (Math.abs(x1-x) < ((xSize/2)+padding) && Math.abs(y1-y) < ((ySize/2)+padding)){
					pressed = true;
					sprite.animate(1, 0);
				} else { pressed = false; sprite.animate(0, 0); }
			}
			else{
				if (Math.abs(x1-x-(xSize/2)) < ((xSize/2)+padding) && Math.abs(y1-y-(ySize/2)) < ((ySize/2)+padding)){
					pressed = true;
					sprite.animate(1, 0);
				} else { pressed = false; sprite.animate(0, 0); }
			}
		}
		return pressed;
	}
	/**
	 * Method move.
	 * @param x1 int
	 * @param y1 int
	 * @return boolean
	 */
	public boolean move(int x1, int y1){
		if (!hide) down(x1, y1);
		return false;
	}
	/**
	 * Method up.
	 * @param x1 int
	 * @param y1 int
	 * @return boolean
	 */
	public boolean up(int x1, int y1){ 
		if (pressed && !hide){
			sprite.animate(0, 0);
			pressed = false;
			AudioHandler.POP1.play();
			return true;
		}
		else return false; 
	}
	/**
	 * Method setPadding.
	 * @param padding int
	 */
	public void setPadding(int padding){ this.padding=padding; }
	public void hide(){ hide = true; }
	public void reveal(){ hide = false; }
	/**
	 * Method update.
	 * @param x double
	 * @param y double
	 */
	public void update(double x, double y){ 
		this.x=x;
		this.y=y;
		sprite.update(x, y); 
	}
	/**
	 * Method isPressed.
	 * @return boolean
	 */
	public boolean isPressed(){ return pressed; }
	/**
	 * Method getX.
	 * @return double
	 */
	public double getX(){ return x; }
	/**
	 * Method getY.
	 * @return double
	 */
	public double getY(){ return y; }
}
