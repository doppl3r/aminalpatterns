import java.awt.Color;
import java.awt.Graphics2D;


/**
 */
public class Selector {
	private int x;
	private int y;
	private int mainX;
	private int mainY;
	private int pSize;
	//used for keyboard
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	private int colorIndex;
	private double alpha;
	private double alphaRate;
	private boolean increaseAlpha;
	
	/**
	 * Constructor for Selector.
	 * @param mainX int
	 * @param mainY int
	 * @param cols int
	 * @param rows int
	 */
	public Selector(int mainX, int mainY, int cols, int rows){
		this.mainX=mainX;
		this.mainY=mainY;
		minX=0;
		minY=0;
		maxX=cols;
		maxY=rows;
		pSize = 12;
		alphaRate = 10.0;
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		g.setColor(new Color(255,255,255));
		g.drawRect(mainX+(x*pSize)-1, mainY+(y*pSize)-1, pSize, pSize); //visible white
		g.drawRect(mainX+(x*pSize), mainY+(y*pSize), pSize-2, pSize-2); //visible white
		g.setColor(new Color(70,0,0,(int)alpha));
		g.drawRect(mainX+(x*pSize)-1, mainY+(y*pSize)-1, pSize, pSize); //black
		g.drawRect(mainX+(x*pSize), mainY+(y*pSize), pSize-2, pSize-2); //black
	}
	public void update(){
		//give the selector an animated-flash quality
		if (increaseAlpha){
			if (alpha + alphaRate < 255.0) alpha += alphaRate;
			else{ alpha = 255; increaseAlpha = false; }
		}
		else {
			if (alpha - alphaRate > 70) alpha -= alphaRate;
			else{ alpha = 70; increaseAlpha = true; }
		}
	}
	/**
	 * Method move.
	 * @param direction int
	 */
	public void move(int direction){
		switch (direction){
			//adjust all key movements so that it goes to the next row/col
			case(0): if (y > minY) y--; else{ if (x > minX){ y=maxY-1; x--; } else { x=maxX-1; y=maxY-1; }} break;
			case(1): if (x < maxX-1) x++; else{ if (y < maxY-1){ y++; x=minX; } else { y=minY; x=minX; }} break;
			case(2): if (y < maxY-1) y++; else{ if (x < maxX-1){ y=minY; x++; } else { x=minX; y=minY; }} break;
			case(3): if (x > minX) x--; else{ if (y > minY){ y--; x=maxX-1; } else { y=maxY-1; x=maxX-1; }} break;
			default: move(0); break; //if non-specific, move up
		}
		updatePaletteColorIndex();
	}
	/**
	 * Method setX.
	 * @param x int
	 */
	public void setX(int x){ this.x=x; }
	/**
	 * Method setY.
	 * @param y int
	 */
	public void setY(int y){ this.y=y; }
	/**
	 * Method setXY.
	 * @param x int
	 * @param y int
	 */
	public void setXY(int x, int y){ 
		this.x=x; 
		this.y=y; 
		updatePaletteColorIndex();
	}
	/**
	 * Method setWidth.
	 * @param width int
	 */
	public void setWidth(int width){ this.pSize=width; }
	/**
	 * Method setHeight.
	 * @param height int
	 */
	public void setHeight(int height){ this.pSize=height; }
	/**
	 * Method setColorIndex.
	 * @param i int
	 */
	public void setColorIndex(int i){ colorIndex = i; }
	/**
	 * Method setMinMax.
	 * @param minX int
	 * @param minY int
	 * @param maxX int
	 * @param maxY int
	 */
	public void setMinMax(int minX, int minY, int maxX, int maxY){
		this.minX=minX;
		this.minY=minY;
		this.maxX=maxX;
		this.maxY=maxY;
		if (x < minX) x=minX;
		if (x > maxX-1) x=maxX-1;
		if (y < minY) y=minY;
		if (y > maxY-1) y=maxY-1;
		updatePaletteColorIndex();
	}
	
	/**
	 * Method getX.
	 * @return int
	 */
	public int getX(){ return x; }
	/**
	 * Method getY.
	 * @return int
	 */
	public int getY(){ return y; }
	/**
	 * Method getWidth.
	 * @return int
	 */
	public int getWidth(){ return pSize; }
	/**
	 * Method getHeight.
	 * @return int
	 */
	public int getHeight(){ return pSize; }
	/**
	 * Method getColorIndex.
	 * @return int
	 */
	public int getColorIndex(){ return colorIndex; }
	public void updatePaletteColorIndex(){
		Window.panel.options.colorDisplay.pallete.setColorIndex(
		Window.panel.editor.getMap()[x][y].getColorIndex());
	}
	/**
	 * Method getAlpha.
	 * @return double
	 */
	public double getAlpha(){ return alpha; }
}
