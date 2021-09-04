package algorithms.rasterisation;

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
		
		// Calcular a variacao de x e y
		dx = x2 - x1;
		dy = y2 - y1;
		
		// Determinar quantas iteracoes serao necessarias
		// para formar a reta
		if(Math.abs(dx) > Math.abs(dy)) 
			steps = Math.abs(dx);
		else 
			steps = Math.abs(dy);
		
		// Determinar encontrar o incrementador de x de y
		x_i = (float)dx / (float)steps;
		y_i = (float)dy / (float)steps;
		
		// Definir pontos inicias da reta 
		x = x1;
		y = y1;
		
		// Criar o primero ponto
		g.fillOval((int)Math.round(x), (int)Math.round(y), brush_size, brush_size);
		
		// Criar a reta
		for(int i = 1; i < steps; i++) {
			x += x_i;
			y += y_i;
			
			g.fillOval((int)Math.round(x), (int)Math.round(y), brush_size, brush_size);
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
		
		//Calcular a variacao de x e y
		dx = Math.abs (x2-x1);
		dy = Math.abs (y2-y1);
		
		// Calcular P, para futuramente calcular por qual pixel
		// a reta deve passar
		p = 2*dy - dx;
		
		// Quando o programa iniciar, ele pode as vezes calcular uma divisao por zero
		// e gerar uma ArithmeticException, para evitar esse problema, adicionamos essa linha
		if(dx == 0)
			return;
		
		// Calcular o angulo da reta que sera gerado
		int slope = dy/dx;
		
		// Calcular constantes para saber o valor de P a cada
		// iteracao da geracao da reta
		if(slope >= 1) {
			const1 = 2 * dx;
			const2 = 2 * dx-2* dy;
			
		} else {
			const1 = 2 * dy;
			const2 = 2 * dy-2* dx;
		}
		
		// definir valores iniciais da reta
		x = x1;
		y = y1;

		// definir como a reta deve expandir, seja essa
		// reta orientada para cima ou para baixo
		if(y2>y1) 
			y_i = 1; // y_i y_incrementor
		
		else 
			y_i = -1;
		
		
		// Inserir primeiro ponto da reta
		g.fillOval(x, y, brush_size, brush_size);

		// definir como a reta deve expandir, seja essa
		// reta orientada para e esquerda ou para a direita
		if(x2>x1) {
			
			x_i = 1; //x_i x_incrementor
			
			// Enquanto a reta nao chegar a tal ponto, crie ela
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
			
			// Enquanto a reta nao chegar a tal ponto, crie ela
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
