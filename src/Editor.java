import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;

import buttons.Button;

/**
 */
public class Editor {
	private Pixel[][] map;
	private int cols;
	private int rows;
	private int mainX;
	private int mainY;
	private int pSize;
	private int width;
	private int height;
	private int zoomX;
	private int zoomY;
	private int zoomWidth;
	private int zoomHeight;
	private int downX;
	private int downY;
	private int dragX;
	private int dragY;
	private boolean draw;
	private boolean drag;
	private boolean showGrid;
	private boolean highlight;
	private boolean numbers;
	private Paint gradient;
	private Button zoomIn;
	private Button zoomOut;
	private Button gridButton;
	private Button highlightButton;
	private Button numbersButton;
	public Selector selector;
	private BufferedImage grid;
	Font font;
	
	public Editor(){
		cols = 32;
		rows = 32;
		pSize = 12;
		width = cols*pSize;
		height = rows*pSize;
		zoomX = 0;
		zoomY = 0;
		zoomWidth  = cols;
		zoomHeight = rows;
		mainX = 64;
		mainY = 48;
		map = new Pixel[cols][rows];
		constructArray();
		renderGrid();
		font = new Font("Arial", Font.PLAIN, 9);
		gradient = new GradientPaint(
				0, 0, new Color(170,150,150),
                0, 480, new Color(70,50,50)); //480 = height
		zoomIn  = new Button(Window.tt.zoomIn,  mainX - 37, mainY - 5 , false);
		zoomOut = new Button(Window.tt.zoomOut, mainX - 37, mainY + 27, false);
		gridButton = new Button(Window.tt.grid, mainX - 37, mainY + 59, false);
		highlightButton = new Button(Window.tt.highlight, mainX - 37, mainY + 91, false);
		numbersButton = new Button(Window.tt.numbers, mainX - 37, mainY + 123, false);
		selector = new Selector(mainX, mainY, cols, rows);
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		//draw behind tile map
		g.setPaint(gradient);
		g.fillRect(mainX-5, mainY-5, width+10, height+10);
		g.setColor(new Color(75,75,75));
		g.drawRect(mainX-1, mainY-1, width+1, height+1);
		//draw each tile
		g.setFont(font);
		for (int col = 0; col < cols; col++){
			for (int row = 0; row < rows; row++){
				map[col][row].draw(
					(col*pSize)+mainX,
					(row*pSize)+mainY,
					pSize,g);
			}
		}
		//draw Grid
		if (showGrid) g.drawImage(grid, mainX, mainY, null);
		//draw selector
		selector.draw(g);
		//draw zoomed area
		g.setColor(new Color(0,0,0,150));
		g.fillRect(mainX,mainY,width,(zoomY*pSize)-(downY-dragY)); //top
		g.fillRect(mainX-(downX-dragX)+((zoomX+zoomWidth)*pSize),mainY-(downY-dragY)+zoomY*pSize,width-(mainX-(downX-dragX)+((zoomX+zoomWidth)*pSize))+mainX,zoomHeight*pSize); //right
		g.fillRect(mainX, mainY-(downY-dragY)+(zoomY+zoomHeight)*pSize, width, height-(mainY-(downY-dragY)+((zoomY+zoomHeight)*pSize))+mainY); //bottom
		g.fillRect(mainX, mainY-(downY-dragY)+(zoomY*pSize),-(downX-dragX)+(zoomX*pSize), zoomHeight*pSize); //left
		g.setColor(new Color(50,50,50));
		g.drawRect((zoomX*pSize)+(mainX-(downX-dragX))-1, (zoomY*pSize)+(mainY-(downY-dragY))-1, zoomWidth*pSize+1, zoomHeight*pSize+1);
		//draw buttons
		zoomIn.draw( g);
		zoomOut.draw(g);
		gridButton.draw(g);
		highlightButton.draw(g);
		numbersButton.draw(g);
	}
	public void update(){
		//update selector
		selector.update();
	}
	public void constructArray(){
		//constructs the grid once
		for (int col = 0; col < cols; col++){
			for (int row = 0; row < rows; row++){
				map[col][row] = new Pixel();
			}
		}
	}
	/**
	 * Method down.
	 * @param x int
	 * @param y int
	 */
	public void down(int x, int y){ 
		checkCanvas(x,y,"down"); 
		zoomIn.down(x,  y);
		zoomOut.down(x, y);
		gridButton.down(x, y);
		highlightButton.down(x,y);
		numbersButton.down(x,y);
		//update selector position
		
	}
	/**
	 * Method move.
	 * @param x int
	 * @param y int
	 */
	public void move(int x, int y){	
		checkCanvas(x,y,"move"); 
		if (!draw && !drag){
			zoomIn.move(x,  y);
			zoomOut.move(x, y);
			gridButton.move(x, y);
			highlightButton.move(x, y);
			numbersButton.move(x, y);
		}
	}
	/**
	 * Method hover.
	 * @param x int
	 * @param y int
	 */
	public void hover(int x, int y){
		checkCanvas(x,y,"hover");
	}
	/**
	 * Method up.
	 * @param x int
	 * @param y int
	 */
	public void up(int x, int y){ 
		checkCanvas(x,y,"up"); 
		//check buttons
		if (zoomIn.up(x,  y)) {
			if (zoomWidth == cols){ //if fully selected
				zoomWidth = cols/2;
				zoomHeight = rows/2;
				zoomX += cols/4;
				zoomY += rows/4;
			}
			else if (zoomWidth == cols/2){ //if half selected
				zoomWidth = 10;
				zoomHeight = 10;
				zoomX += 3;
				zoomY += 3;
			}
			adjustZoomBounds(x,y);
		}
		else if (zoomOut.up(x, y)) {
			
			if (zoomWidth == 10){ //if smallest selection
				zoomWidth = cols/2;
				zoomHeight = rows/2;
				zoomX -= 3;
				zoomY -= 3;
			}
			else if (zoomWidth == cols/2){ //if half selection
				zoomWidth = cols;
				zoomHeight = rows;
				zoomX -= cols/2;
				zoomY -= cols/2;
			}
			adjustZoomBounds(x,y);
		}
		else if (gridButton.up(x, y)) showGrid = !showGrid; 
		else if (highlightButton.up(x, y)) highlight = !highlight;
		else if (numbersButton.up(x, y)) numbers = !numbers;
		draw = drag = false;
		zoomX -= (downX-dragX)/pSize;
		zoomY -= (downY-dragY)/pSize;
		downX = downY = dragX = dragY = 0;
		//adjustZoomBounds();
	}
	public void keyUp(){    selector.move(0); }
	public void keyRight(){ selector.move(1); }
	public void keyDown(){  selector.move(2); }
	public void keyLeft(){  selector.move(3); }
	/**
	 * Method checkCanvas.
	 * @param x int
	 * @param y int
	 * @param action String
	 */
	public void checkCanvas(int x, int y, String action){
		if ((x-mainX-pSize)/pSize >= 0 && (x-mainX)/pSize < (cols) &&
				(y-mainY-pSize)/pSize >= 0 && (y-mainY)/pSize < (rows)){
			if (x >= (zoomX*pSize)+(mainX-(downX-dragX)) && x < (zoomX*pSize)+(zoomWidth* pSize)+(mainX-(downX-dragX)) &&
				y >= (zoomY*pSize)+(mainY-(downY-dragY)) && y < (zoomY*pSize)+(zoomHeight*pSize)+(mainY-(downY-dragY)) && !drag){
				//set the pixel value within the 2d array
				if (action.equals("down")) {
					draw = true;
					selector.setXY((x-mainX)/pSize, (y-mainY)/pSize);
				}
				else if (action.equals("move")){
					selector.setXY((x-mainX)/pSize, (y-mainY)/pSize);
				}
				else if (action.equals("hover")){ 
					if (!drag) Window.panel.cursor.animate(0); 
				}
			}
			else{ //if dragging the window is true
				if (action.equals("down")) {
					Window.panel.cursor.animate(2);
					downX = dragX = (x/pSize)*pSize;
					downY = dragY = (y/pSize)*pSize;
					drag = true;
				}
				else if (action.equals("move")){
					if (drag && !draw){
						if (Window.panel.cursor.getCurrentFrame() != 2) Window.panel.cursor.animate(2);
						dragX = (x/pSize)*pSize;
						dragY = (y/pSize)*pSize;
						adjustZoomBounds(x,y);
					}
				}
				else if (action.equals("hover")){ Window.panel.cursor.animate(1); }
				else if (action.equals("up")) { Window.panel.cursor.animate(1); }
			}
		}
		else{ 
			if (drag && !draw){
				if (action.equals("move")){
					dragX = (x/pSize)*pSize;
					dragY = (y/pSize)*pSize;
					adjustZoomBounds(x,y);
				}
				else if (action.equals("up")) Window.panel.cursor.animate(1);
			}
			if (x < 480 && !drag && !draw) Window.panel.cursor.animate(0);
		} //resets cursor
	}
	/**
	 * Method adjustZoomBounds.
	 * @param x int
	 * @param y int
	 */
	public void adjustZoomBounds(int x, int y){
		//adjust out of bounds window
		if ((zoomX-(downX-dragX)/pSize) < 0){ zoomX = 0; downX = dragX = (x/pSize)*pSize; }
		if ((zoomX-(downX-dragX)/pSize) > (width/pSize)-zoomWidth){ zoomX = (width/pSize)-zoomWidth; downX = dragX = (x/pSize)*pSize; }
		if ((zoomY-(downY-dragY)/pSize) < 0){ zoomY = 0; downY = dragY = (y/pSize)*pSize; }
		if ((zoomY-(downY-dragY)/pSize) > (height/pSize)-zoomHeight){ zoomY = (height/pSize)-zoomHeight; downY = dragY = (y/pSize)*pSize; }
		//adjust selector bounds
		selector.setMinMax(zoomX-(downX-dragX)/pSize, 
				zoomY-(downY-dragY)/pSize, 
				zoomX+zoomWidth-(downX-dragX)/pSize, 
				zoomY+zoomHeight-(downY-dragY)/pSize);
		//check for highlighted colors
		for (int col = 0; col < cols; col++){
			for (int row = 0; row < rows; row++){
				map[col][row].checkInRange(col, row, zoomX-(downX-dragX)/pSize, 
					zoomY-(downY-dragY)/pSize, 
					zoomX+zoomWidth-(downX-dragX)/pSize, 
					zoomY+zoomHeight-(downY-dragY)/pSize);
			}
		}
	}
	public void renderGrid(){
		BufferedImage bufferedImage = new BufferedImage(cols*pSize, rows*pSize,  BufferedImage.TYPE_INT_ARGB);
	    Graphics g = bufferedImage.createGraphics();
	    g.setColor(new Color(75,75,75,75));
	    for (int col = 0; col < cols; col++){
	    	for (int row = 0; row < rows; row++){
	    		g.drawRect(col*pSize-1, row*pSize-1, pSize, pSize);
	    	}
	    }
	    g.fillRect(0, (height/2) - 1, width, 2);
	    g.fillRect((width/2) - 1, 0, 2, height);
	    grid = bufferedImage;
	    g.dispose();
	}
	/**
	 * Method editPixel.
	 * @param row int
	 * @param col int
	 * @param r int
	 * @param g int
	 * @param b int
	 */
	public void editPixel(int row, int col, int r, int g, int b){
		map[col][row].setRGB(r, g, b);
	}
	/**
	 * Method getMap.
	 * @return Pixel[][]
	 */
	public Pixel[][] getMap(){ return map; }
	/**
	 * Method updateMap.
	 * @param img BufferedImage
	 * @param x int
	 * @param y int
	 */
	public void updateMap(BufferedImage img, int x, int y){
		if (img != null){
			Color c;
			int index = 0;
			for (int col = x; col < x+32; col++){
				for (int row = y; row < y+32; row++){
					c = new Color(img.getRGB(col,row));
				    index = Window.panel.options.colorDisplay.pallete.getColorIndex(
				    	c.getRed(), c.getGreen(), c.getBlue());
				    map[col-x][row-y].setRGBtoIndex(index);
				}
			}
			selector.updatePaletteColorIndex();
		}
	}
	/**
	 * Method getHighlight.
	 * @return boolean
	 */
	public boolean getHighlight(){ return highlight; }
	public boolean getNumbers(){ return numbers; }
	public void setFontSize(int size){ font = new Font("Arial", Font.PLAIN, size); }
}
