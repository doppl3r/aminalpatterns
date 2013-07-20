import java.awt.Color;
import java.awt.Graphics2D;


/**
 */
public class Pixel {
	private int r;
	private int g;
	private int b;
	private int colorIndex; //0-14 according to animal crossing's 15 colors
	private boolean highlight;
	private boolean inRange;
	
	public Pixel(){
		r=g=b=255; //initialize as white
		highlight=true;
		inRange=true;
	}
	/**
	 * Method draw.
	 * @param x int
	 * @param y int
	 * @param pixelSize int
	 * @param g1 Graphics2D
	 */
	public void draw(int x, int y, int pixelSize, Graphics2D g1){
		g1.setColor(new Color(r,g,b));
		g1.fillRect(x, y, pixelSize, pixelSize);
		//adjust highlight
		if (highlight && Window.panel.editor.getHighlight()){
			if (colorIndex == Window.panel.options.colorDisplay.pallete.getColorIndex()){
				if (inRange){
					g1.setColor(new Color((int)Window.panel.editor.selector.getAlpha(),
						(int)Window.panel.editor.selector.getAlpha(),
						(int)Window.panel.editor.selector.getAlpha()));
					g1.drawRect(x, y, pixelSize-2, pixelSize-2);
				}
			}
		}
		//show numbers
		if (Window.panel.editor.getNumbers()){
			if (inRange){
				g1.setColor(Color.BLACK);
				g1.drawString(""+(colorIndex+1),x,y+9);
				g1.drawString(""+(colorIndex+1),x+2,y+9);
				g1.drawString(""+(colorIndex+1),x+1,y+8);
				g1.drawString(""+(colorIndex+1),x+1,y+10);
				g1.setColor(Color.WHITE);
				g1.drawString(""+(colorIndex+1),x+1,y+9);
			}
		}
	}
	/**
	 * Method setRGB.
	 * @param r int
	 * @param g int
	 * @param b int
	 */
	public void setRGB(int r, int g, int b){
		this.r=r;
		this.g=g;
		this.b=b;
	}
	/**
	 * Method setColorIndex.
	 * @param i int
	 */
	public void setColorIndex(int i){ colorIndex = i; }
	/**
	 * Method setRGBtoIndex.
	 * @param i int
	 */
	public void setRGBtoIndex(int i){
		r = Window.panel.options.colorDisplay.pallete.getList()[i].getR();
		g = Window.panel.options.colorDisplay.pallete.getList()[i].getG();
		b = Window.panel.options.colorDisplay.pallete.getList()[i].getB();
		colorIndex = i;
	}
	/**
	 * Method checkInRange.
	 * @param x int
	 * @param y int
	 * @param minX int
	 * @param minY int
	 * @param maxX int
	 * @param maxY int
	 */
	public void checkInRange(int x, int y, int minX, int minY, int maxX, int maxY){
		if (x >= minX && x < maxX && y >= minY && y < maxY) inRange = true;
		else inRange = false;
	}
	public void toggleHighlight(){ highlight = !highlight; }
	/**
	 * Method getR.
	 * @return int
	 */
	public int getR(){ return r; }
	/**
	 * Method getG.
	 * @return int
	 */
	public int getG(){ return g; }
	/**
	 * Method getB.
	 * @return int
	 */
	public int getB(){ return b; }
	/**
	 * Method getColorIndex.
	 * @return int
	 */
	public int getColorIndex(){ return colorIndex; }
	/**
	 * Method getRGB.
	 * @return String
	 */
	public String getRGB(){ return "["+r+","+g+","+b+"]"; }
}
