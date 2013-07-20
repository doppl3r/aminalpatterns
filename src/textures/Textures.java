package textures;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 */
public class Textures {
	public Image bg;
	public Image cursor;
	public Image browse;
	public Image upload;
	public Image filter;
	public Image zoomIn;
	public Image zoomOut;
	public Image grid;
	public Image highlight;
	public Image numbers;
	public Image palette;
	public Image selected;
	public Image icon;
	public Image icon_hd;
	public Image left;
	public Image right;
	public Image link;
	
	public Textures(){
		addResources();
	}
	public void addResources(){
		bg = new ImageIcon(this.getClass().getResource("/gui/bg.png")).getImage();
		cursor = new ImageIcon(this.getClass().getResource("/gui/cursor.png")).getImage();
		browse = new ImageIcon(this.getClass().getResource("/gui/browse.png")).getImage();
		upload = new ImageIcon(this.getClass().getResource("/gui/upload.png")).getImage();
		filter = new ImageIcon(this.getClass().getResource("/gui/filter.png")).getImage();
		zoomIn  = new ImageIcon(this.getClass().getResource("/gui/zoomIn.png" )).getImage();
		zoomOut = new ImageIcon(this.getClass().getResource("/gui/zoomOut.png")).getImage();
		grid = new ImageIcon(this.getClass().getResource("/gui/grid.png")).getImage();
		highlight = new ImageIcon(this.getClass().getResource("/gui/highlight.png")).getImage();
		numbers = new ImageIcon(this.getClass().getResource("/gui/numbers.png")).getImage();
		palette = new ImageIcon(this.getClass().getResource("/gui/palette.png")).getImage();
		selected = new ImageIcon(this.getClass().getResource("/gui/selected.png")).getImage();
		icon = new ImageIcon(this.getClass().getResource("/gui/icon.png")).getImage();
		icon_hd = new ImageIcon(this.getClass().getResource("/gui/icon_hd.png")).getImage();
		left = new ImageIcon(this.getClass().getResource("/gui/left.png")).getImage();
		right = new ImageIcon(this.getClass().getResource("/gui/right.png")).getImage();
		link = new ImageIcon(this.getClass().getResource("/gui/nook.png")).getImage();
	}
}
