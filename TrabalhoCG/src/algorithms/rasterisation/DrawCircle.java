package algorithms.rasterisation;

import java.awt.Graphics;

public class DrawCircle {

	private static int brush_size = 5;
	
	// Function to put pixels
	// at subsequence points
	private static void draw_circle(Graphics g, int xc, int yc, int x, int y) {
		g.fillOval(xc+x, yc+y, brush_size, brush_size);
		g.fillOval(xc-x, yc+y, brush_size, brush_size);
		g.fillOval(xc+x, yc-y, brush_size, brush_size);
		g.fillOval(xc-x, yc-y, brush_size, brush_size);
		g.fillOval(xc+y, yc+x, brush_size, brush_size);
		g.fillOval(xc-y, yc+x, brush_size, brush_size);
		g.fillOval(xc+y, yc-x, brush_size, brush_size);
		g.fillOval(xc-y, yc-x, brush_size, brush_size);
	}
	 
	// Function for circle-generation
	// using Bresenham's algorithm
	public static void bresenham(Graphics g, int xc, int yc, int r) {
	    int x = 0, y = r;
	    int d = 3 - 2 * r;
	    draw_circle(g,xc, yc, x, y);
	    while (y >= x)
	    {
	        // for each pixel we will
	        // draw all eight pixels
	         
	        x++;
	 
	        // check for decision parameter
	        // and correspondingly
	        // update d, x, y
	        if (d > 0)
	        {
	            y--;
	            d = d + 4 * (x - y) + 10;
	        }
	        else
	            d = d + 4 * x + 6;
	        draw_circle(g, xc, yc, x, y);
	    }
	}
}
