package textures;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 */
public class SpriteSheet {
	private double rate;
	private double currentFrame;
	private boolean finished;
	private boolean centered;
	private int imgWidth;
	private int imgHeight;
	private int spriteWidth;
	private int spriteHeight;
	private int hFrames;
	private int vFrames;
	private int frames;
	private int x;
	private int y;
	private Image image;
	private Rect spriteRect, destRect;
	
	/**
	 * Constructor for SpriteSheet.
	 * @param image Image
	 * @param hFrames int
	 * @param vFrames int
	 * @param rate double
	 */
	public SpriteSheet(Image image, int hFrames, int vFrames, double rate){
		this.hFrames=hFrames;
		this.vFrames=vFrames;
		this.image=image;
		this.rate=rate;
		spriteWidth =imgWidth =image.getWidth(null) /this.hFrames;
		spriteHeight=imgHeight=image.getHeight(null)/this.vFrames;
		
		frames = (int)(hFrames*vFrames);
		spriteRect = new Rect(0,0,imgWidth,imgHeight);
		destRect = new Rect();
	}
	/**
	 * Method animate.
	 * @return boolean
	 */
	public boolean animate(){
		finished = false;
		if (currentFrame+rate < frames) currentFrame+=rate;
		else{
			finished = true;
			currentFrame = 0;
		}
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
		return finished;
	}
	/**
	 * Method animate.
	 * @param frame int
	 */
	public void animate(int frame){
		//adjust sprite location
		spriteRect.top = ((frame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = ((frame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	/**
	 * Method animate.
	 * @param start int
	 * @param end int
	 */
	public void animate(int start, int end){
		end++;
		//animates between a certain frame
		if (currentFrame < start) currentFrame = start;
		if (currentFrame+rate < end) currentFrame+=rate;
		else{
			finished = true;
			currentFrame = start;
		}
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	/**
	 * Method build.
	 * @param x double
	 * @param y double
	 * @param xSize int
	 * @param ySize int
	 */
	public void build(double x, double y, int xSize, int ySize){
		update(x,y,xSize,ySize);
		this.x=(int)x;
		this.y=(int)y;
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g){
		g.drawImage(getImage(), 
				getDestRectLeft(), getDestRectTop(),
				getDestRectRight(), getDestRectBottom(), 
				getSpriteLeft(), getSpriteTop(), 
				getSpriteRight(), getSpriteBottom(), null);
	}
	/**
	 * Method update.
	 * @param x double
	 * @param y double
	 */
	public void update(double x, double y){
		//texture placement
		if (centered){
			destRect.top = (int)(y-(imgHeight/2));
			destRect.bottom = destRect.top + imgHeight;
			destRect.left = (int)(x-(imgWidth/2));
			destRect.right = destRect.left + imgWidth;
		}
		else{
			destRect.top = (int)y;
			destRect.bottom = destRect.top + imgHeight;
			destRect.left = (int)x;
			destRect.right = destRect.left + imgWidth;
		}
	}
	/**
	 * Method update.
	 * @param x double
	 * @param y double
	 * @param xSize int
	 * @param ySize int
	 */
	public void update(double x, double y, int xSize, int ySize){
		imgWidth = xSize;
		imgHeight = ySize;
		update(x,y);
	}
	/**
	 * Method updateSprite.
	 * @param x1 int
	 * @param y1 int
	 * @param x2 int
	 * @param y2 int
	 */
	public void updateSprite(int x1, int y1, int x2, int y2){
		spriteRect.top = y1;
		spriteRect.right = x2;
		spriteRect.bottom = y2;
		spriteRect.left = x1;
	}
	public void reflect(){
		int oldLeft = spriteRect.left;
		spriteRect.left = spriteRect.right;
		spriteRect.right = oldLeft;
	}
	public void flip(){
		int oldTop = spriteRect.top;
		spriteRect.top = spriteRect.bottom;
		spriteRect.bottom = oldTop;
	}
	/**
	 * Method resize.
	 * @param imgWidth int
	 * @param imgHeight int
	 */
	public void resize(int imgWidth, int imgHeight){
		this.imgWidth=imgWidth;
		this.imgHeight=imgHeight;
	}
	public void resetDest(){
		destRect.top = 0;
		destRect.bottom = 0;
		destRect.left = 0;
		destRect.right = 0;
	}
	/**
	 * Method setImage.
	 * @param image Image
	 */
	public void setImage(Image image){ this.image = image; }
	/**
	 * Method getImage.
	 * @return Image
	 */
	public Image getImage(){ return image; }
	/**
	 * Method getDestRect.
	 * @return Rect
	 */
	public Rect getDestRect(){ return destRect; }
	/**
	 * Method getSpriteRect.
	 * @return Rect
	 */
	public Rect getSpriteRect(){ return spriteRect; }
	/**
	 * Method getImageWidth.
	 * @return int
	 */
	public int getImageWidth(){ return imgWidth; }
	/**
	 * Method getImageHeight.
	 * @return int
	 */
	public int getImageHeight(){ return imgHeight; }
	/**
	 * Method getHFrames.
	 * @return int
	 */
	public int getHFrames(){ return hFrames; }
	/**
	 * Method getVFrames.
	 * @return int
	 */
	public int getVFrames(){ return vFrames; }
	/**
	 * Method getCurrentFrame.
	 * @return int
	 */
	public int getCurrentFrame(){ return (int)currentFrame; }
	/**
	 * Method getSpriteWidth.
	 * @return int
	 */
	public int getSpriteWidth(){ return spriteWidth; }
	/**
	 * Method getSpriteHeight.
	 * @return int
	 */
	public int getSpriteHeight(){ return spriteHeight; }
	/**
	 * Method getSpriteTop.
	 * @return int
	 */
	public int getSpriteTop(){ return spriteRect.top; }
	/**
	 * Method getSpriteRight.
	 * @return int
	 */
	public int getSpriteRight(){ return spriteRect.right; }
	/**
	 * Method getSpriteBottom.
	 * @return int
	 */
	public int getSpriteBottom(){ return spriteRect.bottom; }
	/**
	 * Method getSpriteLeft.
	 * @return int
	 */
	public int getSpriteLeft(){ return spriteRect.left; }
	/**
	 * Method getDestRectTop.
	 * @return int
	 */
	public int getDestRectTop(){ return destRect.top; }
	/**
	 * Method getDestRectRight.
	 * @return int
	 */
	public int getDestRectRight(){ return destRect.right; }
	/**
	 * Method getDestRectBottom.
	 * @return int
	 */
	public int getDestRectBottom(){ return destRect.bottom; }
	/**
	 * Method getDestRectLeft.
	 * @return int
	 */
	public int getDestRectLeft(){ return destRect.left; }
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
	 * Method getRate.
	 * @return double
	 */
	public double getRate(){ return rate; }
	public void center(){ centered = true; }
	/**
	 * Method isCentered.
	 * @return boolean
	 */
	public boolean isCentered(){ return centered; }
	/**
	 * Method isFinished.
	 * @return boolean
	 */
	public boolean isFinished(){ return finished; }
	/**
	 * Method isAnimating.
	 * @return boolean
	 */
	public boolean isAnimating(){ return currentFrame > 0; }
	//basic rectangle class
	/**
	 */
	public class Rect {
		public int left;
		public int right;
		public int top;
		public int bottom;
		
		public Rect(){ 
			
		}
		/**
		 * Constructor for Rect.
		 * @param left int
		 * @param top int
		 * @param right int
		 * @param bottom int
		 */
		public Rect(int left, int top, int right, int bottom){
			this.left=left;
			this.top=top;
			this.right=right;
			this.bottom=bottom;
		}
	}
}
