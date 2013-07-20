
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 */
public class Background{
	private int cols;
	private int rows;
	private int tileSize;
	private double x;
	private double y;
	private double speed;
	private BufferedImage background;
	
	public Background(){
		cols = 12;
		rows = 9;
		speed = 0.5;
		tileSize = Window.tt.bg.getWidth(null);
		renderBackground();
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		g.setColor(new Color(255,255,255));
		g.drawImage(background, (int)x, (int)y, null);
		//g.drawString("mod: "+mod+", ticks: "+ticks, 0, 10);
	}
	public void update(){
		x -= (speed); x %= tileSize;
		y -= (speed); y %= tileSize;		
	}
	/**
	 * Method down.
	 * @param x int
	 * @param y int
	 */
	public void down(int x, int y){
		
	}
	/**
	 * Method move.
	 * @param x int
	 * @param y int
	 */
	public void move(int x, int y){
		
	}
	/**
	 * Method up.
	 * @param x int
	 * @param y int
	 */
	public void up(int x, int y){
		
	}
	public void renderBackground(){
		Image image = Window.tt.bg;
		BufferedImage bufferedImage = new BufferedImage(cols*tileSize, rows*tileSize,  BufferedImage.TYPE_INT_ARGB);
	    Graphics g = bufferedImage.createGraphics();
	    
	    for (int col = 0; col < cols; col++){
	    	for (int row = 0; row < rows; row++){
	    		g.drawImage(image, col*tileSize, row*tileSize, tileSize, tileSize, null);
	    	}
	    }

	    background = bufferedImage;
	    g.dispose();
	}
}
