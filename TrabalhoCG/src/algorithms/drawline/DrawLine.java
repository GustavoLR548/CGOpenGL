package algorithms.drawline;

import java.awt.Graphics;

/**
 * Classe para gerenciar algoritmos de geracao de linha
 * @author gustavolr
 * @version 0.1
 */
public class DrawLine {

	/**
	 * Gerar uma linha usando o algoritmo DDA
	 * 
	 * @param g eh o <code>Graphics</code> que ira renderizar os pontos na tela
	 * @param x1 eh o x do ponto inicial
	 * @param y1 eh o y do ponto inicial
	 * @param x2 eh o x do ponto final
	 * @param y2 eh o y do ponto final
	 */
	public static void DDA(Graphics g, int x1,int y1,int x2,int y2) {
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
		
		g.fillRect((int)Math.round(x), (int)Math.round(y), 10, 10);
		
		for(int i = 1; i < steps; i++) {
			x += x_i;
			y += y_i;
			
			g.fillRect((int)Math.round(x), (int)Math.round(y), 10, 10);
		}
		
	}
}
