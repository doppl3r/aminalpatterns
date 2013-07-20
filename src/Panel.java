import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import textures.SpriteSheet;

/**
 */
public class Panel extends JPanel implements KeyListener,
	MouseListener, MouseMotionListener, Runnable{
	private static final long serialVersionUID = 1L;
	private GUI gui;
	public Editor editor;
	public Options options;
	private Background background;
	private Cursor blankCursor;
	public SpriteSheet cursor;
	public boolean capture;
	//modifier stats
	private Timer t;
	
	public Panel(){
		gui = new GUI();
		editor = new Editor();
		options = new Options();
		background = new Background();
		//cursor
		BufferedImage cursorImg = new BufferedImage(16,16, BufferedImage.TYPE_INT_ARGB);
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			cursorImg, new Point(0,0), "blank cursor");
		cursor = new SpriteSheet(Window.tt.cursor, 1, 3, 0.0);
		//set listeners and thread
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		run();
	}
	/**
	 * Method run.
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		t = new Timer(1000/60, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		t.start();
	}
	/**
	 * Method paintComponent.
	 * @param g1 Graphics
	 */
	@Override
	public void paintComponent(Graphics g1){
		Graphics2D g = (Graphics2D)g1;
		super.paintComponent(g);
		//super.paint(g);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		setBackground(Color.BLACK);
		//draw components
		background.draw(g);
		editor.draw(g);
		options.draw(g);
		gui.draw(g);
		//draw cursor
		setCursor(blankCursor);
		cursor.draw(g);
		//screenshot
		if (capture){
			// component is the component we want to take a screen shot of
	        BufferedImage img = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_4BYTE_ABGR);
	        Graphics gx = img.getGraphics();
	        paint(gx);
	        try {
				ImageIO.write(img, "png", new File("screenshot.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			capture = false;
		}
	}
	public void update(){
		//update the components
		gui.update();
		editor.update();
		options.update();
		background.update();
	}
	//key bindings
	/**
	 * Method keyPressed.
	 * @param e KeyEvent
	 * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { editor.keyUp(); }
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { editor.keyRight(); }
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { editor.keyDown(); }
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { editor.keyLeft(); }
		if (key == KeyEvent.VK_F12) Window.panel.captureScreen();
	}
	/**
	 * Method keyReleased.
	 * @param e KeyEvent
	 * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {}
	/**
	 * Method keyTyped.
	 * @param arg0 KeyEvent
	 * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
	 */
	public void keyTyped(KeyEvent arg0) {}
	/**
	 * Method mouseEntered.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseEntered(MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {}
	/**
	 * Method mouseExited.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseExited(MouseEvent)
	 */
	public void mouseExited(MouseEvent e) { }
	/**
	 * Method mouseClicked.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseClicked(MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) { }
	/**
	 * Method mousePressed.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
	 */
	public void mousePressed(MouseEvent e) { //down
		int x = e.getX();
		int y = e.getY();
		editor.down(x, y);
		options.down(x, y);
		gui.down(x, y);
	}
	/**
	 * Method mouseDragged.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseMotionListener#mouseDragged(MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) { //move
		int x = e.getX();
		int y = e.getY();
		editor.move(x, y);
		options.move(x, y);
		gui.down(x, y);
		cursor.update(x-4, y-6);
	}
	/**
	 * Method mouseReleased.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) { //up
		int x = e.getX();
		int y = e.getY();
		editor.up(x, y);
		options.up(x, y);
		gui.up(x, y);
	}
	/**
	 * Method mouseMoved.
	 * @param e MouseEvent
	 * @see java.awt.event.MouseMotionListener#mouseMoved(MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
		//update cursor
		int x = e.getX();
		int y = e.getY();
		editor.hover(x, y);
		options.hover(x, y);
		cursor.update(x-4, y-6);
	}
	public void captureScreen(){ 
		BufferedImage img = new BufferedImage(Window.width,Window.height, BufferedImage.TYPE_INT_ARGB); 
		Graphics g = img.createGraphics();
		paintComponent(g);
		g.dispose();
		try {
		    File outputfile = new File("screenshot.png");
		    ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {}
	}
}
