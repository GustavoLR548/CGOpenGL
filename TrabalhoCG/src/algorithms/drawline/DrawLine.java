package algorithms.drawline;

import java.awt.Graphics;

/**
 * Classe para gerenciar algoritmos de geracao de linha
 * @author gustavolr
 * @version 0.1
 */
public class DrawLine {

	private static int brush_size = 10;
	
	public static void set_brush_size(int value) {
		brush_size = value;
	}
	
	/**
	 * Gerar uma linha usando o algoritmo DDA
	 * 
	 * @param g eh o <code>Graphics</code> que ira renderizar os pontos na tela
	 * @param x1 eh o x do ponto inicial
	 * @param y1 eh o y do ponto inicial
	 * @param x2 eh o x do ponto final
	 * @param y2 eh o y do ponto final
	 */
	public static void dda(Graphics g, int x1,int y1,int x2,int y2) {
		int dx, dy, steps;
		float x_i, y_i, x, y;
		
		dx = x2 - x1;
		dy = y2 - y1;
		
		if(Math.abs((float)dx) > Math.abs((float)dy)) {
			steps = (int)Math.abs((float)dx);
		} else {
			steps = (int)Math.abs((float)dy);
		}
		
		x_i = (float)dx / (float)steps;
		y_i = (float)dy / (float)steps;
		x = (float)x1;
		y = (float)y1;
		
		g.fillRect((int)Math.round(x), (int)Math.round(y), brush_size, brush_size);
		
		for(int i = 1; i < steps; i++) {
			x += x_i;
			y += y_i;
			
			g.fillRect((int)Math.round(x), (int)Math.round(y), brush_size, brush_size);
		}
		
	}
	
	/**
	 * Gerar uma linha usando o algoritmo Bresenham
	 * 
	 * @param g eh o <code>Graphics</code> que ira renderizar os pontos na tela
	 * @param x1 eh o x do ponto inicial
	 * @param y1 eh o y do ponto inicial
	 * @param x2 eh o x do ponto final
	 * @param y2 eh o y do ponto final
	 */
	public static void bresenham(Graphics g, int x1,int y1,int x2,int y2) {
		int dx, dy, x, y, p, const1, const2, x_i, y_i;
		
		dx = Math.abs (x2-x1);
		dy = Math.abs (y2-y1);
		
		p = 2*dy - dx;
		
		if(dx == 0)
			return;
		
		int slope = dy/dx;
		
		if(slope >= 1) {
			const1 = 2 * dx;
			const2 = 2 * dx-2* dy;
			
		} else {
			const1 = 2 * dy;
			const2 = 2 * dy-2* dx;
		}
		
		x = x1;
		y = y1;

		if(y2>y1) 
			y_i = 1;
		
		else 
			y_i = -1;
		
		
		g.fillOval(x, y, brush_size, brush_size);

		if(x2>x1) {
			x_i = 1;
			while (x <= x2) {
				
				g.fillOval(x, y, brush_size, brush_size);
				
				if(slope >= 1)
					y+=y_i;
					
				else 
					x+=x_i;
				
				
				if (p < 0)
					p += const1;
				
				else {
					p += const2;
					if(slope >= 1) 
						x+=x_i;
					
					else 
						y+=y_i;
					
	
				}
				
			}
		} else {
			x_i = -1;
			while (x >= x2) {
				
				g.fillOval(x, y, brush_size, brush_size);
				
				if(slope >= 1)
					y+=y_i;
					
				else 
					x+=x_i;
				
				
				if (p < 0)
					p += const1;
				
				else {
					p += const2;
					if(slope >= 1) 
						x+=x_i;
					
					else 
						y+=y_i;
					
	
				}
				
			}
		}

	}
}
