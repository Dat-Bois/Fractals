import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;


public class MandelbrotSet extends JPanel implements ActionListener, MouseInputListener{

	/* Attributes a.k.a. Instance Variables */
	int WIDTH = 800, HEIGHT = 600;
	int MAX_ITER = 200;
	// Plot window
	int pX = 0;
	int pY = 0;
	double RE_START = -2; //-2
	double RE_END = 1; //1
	double IM_START = -1; //-1
	double IM_END = 1; //1
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		makeFractal(g);

	}// end of paint method - put code above for anything dealing with drawing -
	public static double log2(double N)
    {
        double result = (Math.log(N) / Math.log(2));
        return result;
    }
	public double mandelbrot(Complex c) {
	    Complex z = new Complex(0);
	    int n = 0;
	    while(z.abs() <= 2 && n < MAX_ITER) {
	        z = c.plus((z.times(z)));
	        n += 1;
	    }
	    if(n== MAX_ITER) {
	    	return MAX_ITER;
	    }
	    //System.out.println(z.abs()+ "  "+ Math.log(z.abs()));
	    return (n+ (1 - Math.log(log2(z.abs()))));
	}
	
	public double map(double x, double in_min, double in_max, double out_min, double out_max) {
		  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
		}
	
	public void makeFractal(Graphics g) {
			for(int x=100;x<WIDTH;x+=1) {
				for(int y=0;y<HEIGHT;y+=1) {
					double real = (double) x/WIDTH;
					double imag = (double) y/HEIGHT;
					real = (double) real * (RE_END - RE_START);
					imag = (double) imag * (IM_END - IM_START);
					real+= RE_START;
					imag+= IM_START;
					Complex c = new Complex(real, imag);
					double m = mandelbrot(c);
					float hue = (float) (m/MAX_ITER);
					float saturation = (float) 1.00;
					float value = (float) 0.0;
					if(m<MAX_ITER) {value = (float) 1.0;}
					g.setColor(Color.getHSBColor(hue,saturation,value));
					g.drawLine(x,y,x,y);
				}
			}
	}
	
	public void update() {

	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		MandelbrotSet d = new MandelbrotSet();
	}
	
	/* Instantiate any attributes here (instance variables */
	public MandelbrotSet() {

		JFrame f = new JFrame();
		f.setTitle("MandelBrotSet");
		f.setSize(WIDTH, HEIGHT);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.add(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
	

	Timer t;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("mouse clicked at point: " + e.getX() + " " + e.getY());
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse pressed at point: " + e.getX() + " " + e.getY());
		pX = e.getX();
		pY = e.getY(); 
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse released at point: " + e.getX() + " " + e.getY());
		//108 - 1907
		//33-1066
		
		double t_RE_START = map(pX, 0, WIDTH, RE_START, RE_END);
		double t_RE_END = map(e.getX(), 0, WIDTH, RE_START, RE_END);
		double t_IM_START = map(e.getY(), 0, HEIGHT, IM_START, IM_END);
		double t_IM_END = map(pY, 0, HEIGHT, IM_START, IM_END);;
		RE_START = t_RE_START;
		RE_END = t_RE_END;
		IM_START = t_IM_START;
		IM_END = t_IM_END;
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
