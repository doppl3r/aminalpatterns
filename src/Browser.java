import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import textures.Resizer;
import buttons.Button;

/**
 */
public class Browser {
	//cropping features
	private int cropX;
	private int cropY;
	private int downX;
	private int downY;
	private int dragX;
	private int dragY;
	private double ratio;
	private boolean drag;
	private boolean quality;
	private boolean init;
	//display features
	private int mainX;
	private int mainY;
	private Button browse;
	private Button filter;
	private Paint gradient;
	private BufferedImage img1; //original
	private BufferedImage img2; //resized
	JFileChooser fileChooser;
	
	/**
	 * Constructor for Browser.
	 * @param x int
	 * @param y int
	 */
	public Browser(int x, int y){
		mainX = x;
		mainY = y;
		browse = new Button(Window.tt.browse, mainX + 101, mainY -  5, false);
		filter = new Button(Window.tt.filter, mainX + 101, mainY + 27, false);
		fileChooser = new JFileChooser(new File("."));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif", "bmp", "gif"));

		gradient = new GradientPaint(
				0, 0, new Color(170,150,150),
                0, 480, new Color(70,50,50)); //480 = height
		img1 = null;
		img2 = null;
		//quality = true;
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		//sample image panel
		g.setPaint(gradient);
		g.fillRect(mainX-5, mainY-5, 106, 106);
		g.setColor(new Color(75,75,75));
		g.drawRect(mainX-1, mainY-1, 96+1, 96+1);
		g.setColor(new Color(255,255,255));
		g.fillRect(mainX, mainY, 96, 96);
		if (img2 != null){
			g.drawImage(img2, mainX, mainY, mainX+96, mainY+96, 
					cropX+((downX-dragX)/3), cropY+((downY - dragY)/3), 
					cropX+((downX-dragX)/3)+32, cropY+((downY - dragY)/3)+32,  null);
		}
		//buttons
		browse.draw(g);
		filter.draw(g);
	}
	public void update(){
		if (!init) init();
	}
	/**
	 * Method down.
	 * @param x int
	 * @param y int
	 */
	public void down(int x, int y){
		checkCropBox(x, y);
		browse.down(x, y);
		filter.down(x, y);
	}
	/**
	 * Method move.
	 * @param x int
	 * @param y int
	 */
	public void move(int x, int y){
		checkCropBox(x, y);
		if (!drag){
			browse.move(x, y);
			filter.move(x, y);
		}
	}
	/**
	 * Method up.
	 * @param x int
	 * @param y int
	 */
	public void up(int x, int y){
		if (browse.up(x, y)) { 
			openImage(null); 
			resize();
			updateMap();
		}
		else if (filter.up(x, y)){
			quality = !quality;
			resize();
			updateMap();
		}
		//adjust drag
		if (img1 != null){
			cropX += (downX-dragX)/3;
			cropY += (downY-dragY)/3;
			if (cropX < 0) cropX = 0;
			if (cropY < 0) cropY = 0;
			if (cropX+32 > img2.getWidth()) cropX = img2.getWidth()-32;
			if (cropY+32 > img2.getHeight()) cropY = img2.getHeight()-32;
			if (drag) updateMap();
			downX = downY = dragX = dragY = 0;
			drag = false;
		}
		if (x > mainX){
			Window.panel.cursor.animate(0);
		}
	}
	/**
	 * Method hover.
	 * @param x int
	 * @param y int
	 */
	public void hover(int x, int y){
		if (x > mainX) {
			if (x >= mainX && x < mainX+96 && y >= mainY && y < mainY +96) Window.panel.cursor.animate(1);
			else Window.panel.cursor.animate(0);
		}
	}
	/**
	 * Method checkCropBox.
	 * @param x int
	 * @param y int
	 */
	public void checkCropBox(int x, int y){
		if (x >= mainX && x < mainX+96 && y >= mainY && y < mainY +96){
			if (!drag){
				drag = true;
				downX = dragX = x;
				downY = dragY = y;
			}
			else{ 
				Window.panel.cursor.animate(2);
				if (ratio != 0){
					if (ratio > 1) dragX = x; 
					else if (ratio < 1) dragY = y; 
				}
			}
		}
		else{
			if (drag){
				if (ratio != 0){
					if (ratio > 1) dragX = x; 
					else if (ratio < 1) dragY = y; 
				}
			}
		}
		
	}
	/**
	 * Method openImage.
	 * @param e ActionEvent
	 */
	public void openImage(ActionEvent e) {
		cropX = cropY = 0;
		/*if (fileChooser == null){
			fileChooser = new JFileChooser(new File("."));
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif", "bmp", "gif"));
		}*/
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try { 
				img1 = ImageIO.read(fileChooser.getSelectedFile());
				//give it white background
				BufferedImage bufferedImage = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics g = bufferedImage.createGraphics();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, img1.getWidth(), img1.getHeight());
				g.drawImage(img1, 0, 0, null);
				g.dispose();
				img1 = bufferedImage;
			} 
			catch (IOException ex) { ex.printStackTrace(); }
		} 
	}
	public void resize(){
		if (img1 != null){
			double width  = img1.getWidth();
			double height = img1.getHeight();
			ratio = width/height;
			width  = ratio >  1 ? 32 * ratio : 32;
			height = ratio <= 1 ? 32 / ratio : 32;
			if (!quality) img2 = Resizer.NEAREST_NEIGHBOR.resize(img1, (int)width, (int)height);
			else img2 = Resizer.PROGRESSIVE_BICUBIC.resize(img1, (int)width, (int)height);
		}
	}
	public void init(){
		//this initializes the first image for the whole program
		int width  = Window.tt.icon.getWidth(null);
		int height = Window.tt.icon.getHeight(null);
		BufferedImage bufferedImage = new BufferedImage(width, height,  BufferedImage.TYPE_INT_ARGB);
	    Graphics g = bufferedImage.createGraphics();
	    g.drawImage(Window.tt.icon, 0, 0, null);
	    img2 = bufferedImage;
	    g.dispose();
	    //set as new content
		resize();
		updateMap();
		init = true;
	}
	public void updateMap(){
		Window.panel.editor.updateMap(img2,cropX,cropY);
	}
}
