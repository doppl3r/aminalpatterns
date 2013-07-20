import java.awt.Color;
import java.awt.Graphics2D;


/**
 */
public class Pallete {
	private RGB[] list;
	private int colors;
	private int currentPalette;
	private int colorIndex;
	
	public Pallete() {
		colors = 15;
		colorIndex = -1;
		currentPalette = 0;
		list = new RGB[colors];
		buildPalette();
		fillPalette(currentPalette);
	}
	/**
	 * Method draw.
	 * @param g Graphics2D
	 * @param mainX int
	 * @param mainY int
	 */
	public void draw(Graphics2D g, int mainX, int mainY){
		for (int i = 0; i < list.length; i++){
			g.setColor(list[i].getColor());
			g.fillRect(mainX+((i%3)*32), mainY+((i/3)*32), 32, 32);
			g.drawImage(Window.tt.palette, mainX+((i%3)*32), mainY+((i/3)*32), 32, 32, null);
			if (colorIndex >= 0) g.drawImage(Window.tt.selected, mainX+((colorIndex%3)*32), mainY+((colorIndex/3)*32), 32, 32, null);
			if (Window.panel.editor.getNumbers()){
				g.setColor(Color.BLACK);
				g.drawString(""+(i+1),mainX+8+((i%3)*32)-1, mainY+16+((i/3)*32));
				g.drawString(""+(i+1),mainX+8+((i%3)*32)+1, mainY+16+((i/3)*32));
				g.drawString(""+(i+1),mainX+8+((i%3)*32), mainY+16+((i/3)*32)-1);
				g.drawString(""+(i+1),mainX+8+((i%3)*32), mainY+16+((i/3)*32)+1);
				
				g.setColor(Color.WHITE);
				g.drawString(""+(i+1),mainX+8+((i%3)*32), mainY+16+((i/3)*32));
			}
		}
	}
	public void update(){
		
	}
	public void buildPalette(){
		//constructs the null array into rgb values
		for (int i = 0; i < colors; i++){
			list[i] = new RGB();
		}
	}
	/**
	 * Method nextPalette.
	 * @param i int
	 */
	public void nextPalette(int i){
		if (i > 0) currentPalette++;
		else currentPalette--;
		if (currentPalette < 0) currentPalette = 11;
		if (currentPalette > 11) currentPalette = 0;
		fillPalette(currentPalette);
	}
	/**
	 * Method fillPalette.
	 * @param palette int
	 */
	public void fillPalette(int palette){
		switch(palette){
			case(0): //pallete 1
				list[0]. setRGB(255,200,200); //color 1
				list[1]. setRGB(255,144, 50); //color 2
				list[2]. setRGB(255,  0,  0); //color 3
				list[3]. setRGB(255,216,  0); //color 4
				list[4]. setRGB( 90,255,  0); //color 5
				list[5]. setRGB( 40,130,  0); //color 6
				list[6]. setRGB( 90,185,255); //color 7
				list[7]. setRGB(  0,148,255); //color 8
				list[8]. setRGB(  0, 38,255); //color 9
				list[9]. setRGB(255,180,210); //color 10
				list[10].setRGB(255, 75,235); //color 11
				list[11].setRGB(160,100, 60); //color 12
				list[12].setRGB(255,255,255); //color 13
				list[13].setRGB(165,165,165); //color 14
				list[14].setRGB(  0,  0,  0); //color 15
			break;
			case(1): //pallete 2
				list[0]. setRGB(255,255,255); //color 1
				list[1]. setRGB(238,238,238); //color 2
				list[2]. setRGB(221,221,221); //color 3
				list[3]. setRGB(204,204,204); //color 4
				list[4]. setRGB(187,187,187); //color 5
				list[5]. setRGB(170,170,170); //color 6
				list[6]. setRGB(153,153,153); //color 7
				list[7]. setRGB(136,136,136); //color 8
				list[8]. setRGB(119,119,119); //color 9
				list[9]. setRGB(102,102,102); //color 10
				list[10].setRGB( 85, 85, 85); //color 11
				list[11].setRGB( 68, 68, 68); //color 12
				list[12].setRGB( 51, 51, 51); //color 13
				list[13].setRGB( 34, 34, 34); //color 14
				list[14].setRGB( 17, 17, 17); //color 15
			break;
			case(2): //pallete 3
				list[0]. setRGB(248,253,240); //color 1
				list[1]. setRGB(244,249,244); //color 2
				list[2]. setRGB(233,238,229); //color 3
				list[3]. setRGB(235,232,219); //color 4
				list[4]. setRGB(227,224,194); //color 5
				list[5]. setRGB(212,197,160); //color 6
				list[6]. setRGB(195,182,129); //color 7
				list[7]. setRGB(179,148,103); //color 8
				list[8]. setRGB(160,129, 79); //color 9
				list[9]. setRGB(141,102, 49); //color 10
				list[10].setRGB(119, 87, 18); //color 11
				list[11].setRGB(109, 71, 19); //color 12
				list[12].setRGB( 73, 47,  7); //color 13
				list[13].setRGB( 48, 33,  0); //color 14
				list[14].setRGB( 33, 29,  0); //color 15
			break;
			case(3): //pallete 4
				list[0]. setRGB(230,224,224); //color 1
				list[1]. setRGB(222,189,182); //color 2
				list[2]. setRGB(208,157,126); //color 3
				list[3]. setRGB(237,244,235); //color 4
				list[4]. setRGB(235,238,228); //color 5
				list[5]. setRGB(236,233,217); //color 6
				list[6]. setRGB(233,224,197); //color 7
				list[7]. setRGB(232,219,195); //color 8
				list[8]. setRGB(209,192,162); //color 9
				list[9]. setRGB(180,149,102); //color 10
				list[10].setRGB(154,126, 76); //color 11
				list[11].setRGB(138,107, 47); //color 12
				list[12].setRGB( 94, 67,  2); //color 13
				list[13].setRGB( 37,  7,  0); //color 14
				list[14].setRGB(  2,  0,  0); //color 15
			break;
			case(4): //pallete 5
				list[0]. setRGB(211,242,234); //color 1
				list[1]. setRGB(168,215,185); //color 2
				list[2]. setRGB(139,166,142); //color 3
				list[3]. setRGB(167,223,225); //color 4
				list[4]. setRGB(105,177,184); //color 5
				list[5]. setRGB( 54, 79,127); //color 6
				list[6]. setRGB(202,187,220); //color 7
				list[7]. setRGB(150,145,166); //color 8
				list[8]. setRGB( 75, 52, 80); //color 9
				list[9]. setRGB(186,166,139); //color 10
				list[10].setRGB(112, 84, 61); //color 11
				list[11].setRGB( 58, 40, 19); //color 12
				list[12].setRGB(251,255,251); //color 13
				list[13].setRGB(162,180,182); //color 14
				list[14].setRGB( 25, 27, 10); //ckiolor 15
			break;
			case(5): //pallete 6
				list[0]. setRGB(247,251,240); //color 1
				list[1]. setRGB(235,217, 67); //color 2
				list[2]. setRGB(219,173, 31); //color 3
				list[3]. setRGB(246,235,236); //color 4
				list[4]. setRGB(234,189,217); //color 5
				list[5]. setRGB(199, 91, 12); //color 6
				list[6]. setRGB(223,172,202); //color 7
				list[7]. setRGB(198,117, 41); //color 8
				list[8]. setRGB(164, 61, 16); //color 9
				list[9]. setRGB(184, 84,131); //color 10
				list[10].setRGB(143, 49, 72); //color 11
				list[11].setRGB( 85, 54, 46); //color 12
				list[12].setRGB(251,255,250); //color 13
				list[13].setRGB(205,224,227); //color 14
				list[14].setRGB(  7,  1,  0); //color 15
			break;
			case(6): //pallete 7
				list[0]. setRGB(223,245,238); //color 1
				list[1]. setRGB(134,213,203); //color 2
				list[2]. setRGB( 52,161,156); //color 3
				list[3]. setRGB(129,217,217); //color 4
				list[4]. setRGB( 38,175,183); //color 5
				list[5]. setRGB(  5, 20,116); //color 6
				list[6]. setRGB(154,192,217); //color 7
				list[7]. setRGB( 69,135,169); //color 8
				list[8]. setRGB( 92,105,136); //color 9
				list[9]. setRGB(167, 88,198); //color 10
				list[10].setRGB( 74, 15,122); //color 11
				list[11].setRGB( 50, 52, 74); //color 12
				list[12].setRGB(249,253,249); //color 13
				list[13].setRGB(201,213,221); //color 14
				list[14].setRGB(  0,  0,  0); //color 15
			break;
			case(7): //pallete 8
				list[0]. setRGB(200, 90, 26); //color 1
				list[1]. setRGB(173, 71, 33); //color 2
				list[2]. setRGB(157, 73, 99); //color 3
				list[3]. setRGB(200,126, 53); //color 4
				list[4]. setRGB(156, 93, 70); //color 5
				list[5]. setRGB(112, 78, 74); //color 6
				list[6]. setRGB(237,236,177); //color 7
				list[7]. setRGB(231,225, 22); //color 8
				list[8]. setRGB(166,159, 26); //color 9
				list[9]. setRGB(218,219, 60); //color 10
				list[10].setRGB(206,198, 20); //color 11
				list[11].setRGB(118,121, 25); //color 12
				list[12].setRGB(229,237,229); //color 13
				list[13].setRGB(152,172,181); //color 14
				list[14].setRGB( 32, 29,  2); //color 15
			break;
			case(8): //pallete 9k
				list[0]. setRGB(240,235,176); //color 1
				list[1]. setRGB(235,222, 23); //color 2
				list[2]. setRGB(210,197, 25); //color 3
				list[3]. setRGB(235,178,181); //color 4
				list[4]. setRGB(216, 81, 27); //color 5
				list[5]. setRGB(180, 70, 23); //color 6
				list[6]. setRGB(150,196, 15); //color 7
				list[7]. setRGB( 66,155, 67); //color 8
				list[8]. setRGB( 75,107,  1); //color 9
				list[9]. setRGB(241,227,228); //color 10
				list[10].setRGB(239,206,201); //color 11
				list[11].setRGB(186,143,113); //color 12
				list[12].setRGB(242,247,246); //color 13
				list[13].setRGB(  3,190,215); //color 14
				list[14].setRGB( 32, 23,  7); //color 15
			break;
			case(9): //pallete 10
				list[0]. setRGB(205,241,235); //color 1
				list[1]. setRGB( 54,211,219); //color 2
				list[2]. setRGB(147,192,206); //color 3
				list[3]. setRGB(153,226,235); //color 4
				list[4]. setRGB( 30,175,206); //color 5
				list[5]. setRGB( 78,132,176); //color 6
				list[6]. setRGB(186,211,157); //color 7
				list[7]. setRGB(146,192, 34); //color 8
				list[8]. setRGB( 68,123, 85); //color 9
				list[9]. setRGB( 97,175, 84); //color 10
				list[10].setRGB( 87,123, 27); //color 11
				list[11].setRGB( 55, 72, 28); //color 12
				list[12].setRGB(247,254,249); //color 13
				list[13].setRGB(192,173,128); //color 14
				list[14].setRGB(103, 71, 24); //color 15
			break;
			case(10): //pallete 11
				list[0]. setRGB(234,233,219); //color 1
				list[1]. setRGB(230,216, 79); //color 2
				list[2]. setRGB(229,199, 86); //color 3
				list[3]. setRGB(189,222,220); //color 4
				list[4]. setRGB(151,198,167); //color 5
				list[5]. setRGB(131,175, 84); //color 6
				list[6]. setRGB(208,224,234); //color 7
				list[7]. setRGB(137,204,218); //color 8
				list[8]. setRGB(101,168,202); //color 9
				list[9]. setRGB(234,213,227); //color 10
				list[10].setRGB(228,166,175); //color 11
				list[11].setRGB(221,138,125); //color 12
				list[12].setRGB(233,231,229); //color 13
				list[13].setRGB(203,190,175); //color 14
				list[14].setRGB(122, 87, 74); //color 15
			break;
			case(11): //pallete 12
				list[0]. setRGB(229,202,178); //color 1
				list[1]. setRGB(223,152, 60); //color 2
				list[2]. setRGB(175,111, 98); //color 3
				list[3]. setRGB(203,205, 54); //color 4
				list[4]. setRGB(137,206, 90); //color 5
				list[5]. setRGB(106,137, 41); //color 6
				list[6]. setRGB(104,168,190); //color 7
				list[7]. setRGB(103,131,169); //color 8
				list[8]. setRGB( 71, 75,117); //color 9
				list[9]. setRGB(161,138,190); //color 10
				list[10].setRGB(150, 61,173); //color 11
				list[11].setRGB( 89, 61, 41); //color 12
				list[12].setRGB(205,218,238); //color 13
				list[13].setRGB(129,139,158); //color 14
				list[14].setRGB( 62, 55, 40); //color 15
			break;
			default: fillPalette(0); break;
		}
	}
	/**
	 * Method getList.
	 * @return RGB[]
	 */
	public RGB[] getList(){ return list; }
	/**
	 * Method setColorIndex.
	 * @param i int
	 */
	public void setColorIndex(int i){ colorIndex = i; }
	/**
	 * Method getColorIndex.
	 * @return int
	 */
	public int getColorIndex(){ return colorIndex; }
	/**
	 * Method getColorIndex.
	 * @param r int
	 * @param g int
	 * @param b int
	 * @return int
	 */
	public int getColorIndex(int r, int g, int b){
		int colorIndex = 0;
		double distance = 0;
		double minDistance = 255;
		//scan for shortest distant color
		for (int i = 0; i < list.length; i++){
			distance = Math.sqrt(Math.pow(r-list[i].getR(),2)+Math.pow(g-list[i].getG(),2)+Math.pow(b-list[i].getB(),2));
			if (distance < minDistance){
				minDistance = distance;
				colorIndex = i;
			}
		}
		return colorIndex;
	}
	//RGB class with content
	/**
	 */
	public class RGB{
		private int r;
		private int g;
		private int b;
		private Color color;
		
		public RGB(){
			
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
			color = new Color(r,g,b);
		}
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
		 * Method getColor.
		 * @return Color
		 */
		public Color getColor(){ return color; }
	}
}
