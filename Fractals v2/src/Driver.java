import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener {

	/* Attributes a.k.a. Instance Variables */
	int w = 1920, h = 1080;

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK); 
		g.fillRect(0, 0, w, h);
		//rings(g, 200, 0, 0);
		g.setColor(Color.ORANGE);
		//squares(g, w/2-200, h/2-150, 300);
		g.setColor(Color.WHITE);
		//triangles(g, w/2, h/2+400, 400);
		//clover(g, w/2, h/2, 200);
		flaker(g, w/2, h/2, 200);
		

	}// end of paint method - put code above for anything dealing with drawing -
	public void squares(Graphics g, int x, int y, int width) {
		if (width < 1) {
			return;
		}
		int seg = width / 3;
		
		g.fillRect(x, y, width, width);

		g.setColor(Color.MAGENTA);
		squares(g, x - seg * 2, y - seg * 2, seg);

		g.setColor(Color.RED);
		squares(g, x + seg, y - seg * 2, seg);

		g.setColor(Color.WHITE);
		squares(g, x + seg * 4, y - seg * 2, seg);

		g.setColor(Color.GREEN);
		squares(g, x - seg * 2, y + seg, seg);

		g.setColor(Color.BLUE);
		squares(g, x + seg * 4, y + seg, seg);

		g.setColor(Color.PINK);
		squares(g, x - seg * 2, y + seg * 4, seg);

		g.setColor(Color.YELLOW);
		squares(g, x + seg, y + seg * 4, seg);

		g.setColor(Color.CYAN);
		squares(g, x + seg * 4, y + seg * 4, seg);
	}
	
	public void clover(Graphics g, int x, int y, int width) {
		if (width < 1)
			return;
		
		//bottomRight
		g.drawOval(x, y, width, width);
		//bottomLeft
		g.drawOval(x - width, y, width, width);
		//topLeft
		g.drawOval(x - width, y - width, width, width);
		//topRight
		g.drawOval(x, y - width, width, width);
		clover(g, x, y, width - 10);
	}
	
	public void flaker(Graphics g, int x, int y, double len) {
		if (len < 1) {
			return;
		}

		for (int i = 1; i < 7; i++) {
			int tmpX = x + (int) (len * Math.cos(Math.PI * i / 3));
			int tmpY = y + (int) (len * Math.sin(Math.PI * i / 3));

			g.drawLine(x, y, tmpX, tmpY);

			flaker(g, tmpX, tmpY, len / 3);
		}
	}
	
	public void triangles(Graphics g, int x, int y, int leg) {
		if(leg<1) {
			return;
		}
		int height = (int) (leg/2 * 1.732050);
		int[] xCords = {x-leg/2,x,x+leg/2};
		int[] yCords = {y-height,y,y-height};
		g.drawPolygon(xCords, yCords, 3);
		//Right
		xCords[0] = x; xCords[1] = x+leg/2; xCords[2] = x+leg;
		yCords[0] = y; yCords[1] = y-height; yCords[2] = y;
		g.drawPolygon(xCords, yCords, 3);
		triangles(g, x+leg/2, y, leg/2);
		//Left
		xCords[0] = x; xCords[1] = x-leg/2; xCords[2] = x-leg;
		yCords[0] = y; yCords[1] = y-height; yCords[2] = y;
		g.drawPolygon(xCords, yCords, 3);
		triangles(g, x-leg/2, y, leg/2);
		//On top
		xCords[0] = x-leg/2; xCords[1] = x; xCords[2] = x+leg/2;
		yCords[0] = y-height; yCords[1] = y-height*2; yCords[2] = y-height;
		g.drawPolygon(xCords, yCords, 3);
		triangles(g, x, y-height, leg/2);
	}

	public void rings(Graphics g, int radius, int x, int y) {
		// each method call draws one part of the fractal
		g.drawOval(x, y, radius, radius);
		
		if(radius >1) {
			rings(g, radius-4, x+2,y+2);
		}
	}

	/**
	 * Update the positions of the ball and paddle. Update the scores, counter
	 * and time
	 */
	public void update() {

	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	/* Instantiate any attributes here (instance variables */
	public Driver() {

		JFrame f = new JFrame();
		f.setTitle("Pong");
		f.setSize(w, h);
		f.setBackground(Color.BLACK);
		f.setResizable(false);

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	Timer t;

}
