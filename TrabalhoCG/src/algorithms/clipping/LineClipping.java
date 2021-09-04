package algorithms.clipping;

import java.awt.Graphics;

import entity.geometry.Line;
import entity.geometry.Vertex;

/**
 * Algoritmos para processos de LineClipping
 * @author gustavolr
 *
 */
public class LineClipping {
	
	// Codigo das regioes para operacoes
	// de bitwise (and)
	
	private static final int INSIDE = 0; // 0000
	private static final int LEFT   = 1; // 0001
	private static final int RIGHT  = 2; // 0010
	private static final int BOTTOM = 4; // 0100
	private static final int TOP    = 8; // 1000

	// Variaveis para definir a área de clipping
	private static int x_max = -1;
	private static int y_max = -1;
	private static int x_min = -1;
	private static int y_min = -1;
	
	// Quando qualquer variável for igual a '-1'
	public static boolean rectangleExist() {
		return x_max != -1;
	}
	
	// Resetar o clipping atual
	public static void resetClippingRectangle() {
		x_max = x_min = y_max = y_min = -1;
	}
	
	// Definiir nova area de clipping
	public static void defineClippingRectangle(int new_x_min, int new_x_max, int new_y_min, int new_y_max) {
		x_max = new_x_max;
		x_min = new_x_min;
		y_max = new_y_max;
		y_min = new_y_min;
	}
	  
	// Encontrar a região em que o ponto se encontra
	private static int computeCode(double x, double y) {
	    // initialized as being inside
	    int code = INSIDE;
	  
	    if (x < x_min)      // to the left of rectangle
	        code |= LEFT;
	    else if (x > x_max) // to the right of rectangle
	        code |= RIGHT;
	    if (y < y_min)      // below the rectangle
	        code |= BOTTOM;
	    else if (y > y_max) // above the rectangle
	        code |= TOP;
	  
	    return code;
	}
	
	/**
	 * Algoritmo de cohenSutherland clipping
	 * @param g é o renderizador de gráficos
	 * @param l é a linha que será clippada
	 * @return a nova linha clipada a partir do algoritmo
	 */
	public static Line cohenSutherlandClip(Graphics g, Line l) {
		
		// Se o clipping não existir, retornar a linha original
		if(!LineClipping.rectangleExist()) 
			return l;
		
		// Resgatar os valores da linha
		double x1 = l.get_v1().get_x();
		double x2 = l.get_v2().get_x();
		
		double y1 = l.get_v1().get_y();
		double y2 = l.get_v2().get_y();
		
	    // Achar as regioes que os pontos dessa linha
		// se encontram dentro do retangulo
	    int code1 = computeCode(x1, y1);
	    int code2 = computeCode(x2, y2);
	  
	    // Inicializar como falso
	    boolean accept = false;
	  
	    while (true) {
	    	
	        if ((code1 == 0) && (code2 == 0)) {
	            // Ambos os pontos estão dentro da area
	            accept = true;
	            break;
	        }
	        else if ((code1 & code2) != 0) {
	            // Ambos os pontos estão fora da area
	            break;
	        }
	        else {
	            // Algum segmento da linha está dentro da
	        	// area
	            int code_out;
	            double x = 0, y = 0;
	  
	            // Um dos pontos está fora da linha
	            if (code1 != 0)
	                code_out = code1;
	            else
	                code_out = code2;
	  
	            // Achar pontos de interseção
	            if ((code_out & TOP) != 0) {

	                x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
	                y = y_max;
	            }
	            else if ((code_out & BOTTOM) != 0) {

	                x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
	                y = y_min;
	            }
	            else if ((code_out & RIGHT) != 0) {

	                y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
	                x = x_max;
	            }
	            else if ((code_out & LEFT) != 0) {

	                y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
	                x = x_min;
	            }
	  
	            // Pontos encontrados, agora é substituir
	            // a interseção com os pontos anteriores
	            
	            if (code_out == code1) {
	                x1 = x;
	                y1 = y;
	                code1 = computeCode(x1, y1);
	            }
	            else {
	                x2 = x;
	                y2 = y;
	                code2 = computeCode(x2, y2);
	            }
	        }
	    }
	    if (accept) 
	    	return new Line(new Vertex((int)x1, (int)y1), new Vertex((int)x2,(int)y2), l.get_line_type());
	    else
	    	return null;
	
	    
	}
	
	private static float t1,t2;

	public static boolean clipTest (float p,float q){
		float r;
		boolean retVal = true;
		  
		//line entry point
		if (p < 0.0) {    
		      
		    r = q /p ;
		      
		    // line portion is outside the clipping edge
		    if ( r > t2 )                         
		    retVal = false;
		      
		    else if (r > t1 )
			    	t1 = r; 
		}
		  
		else 
			//line leaving point
			if (p>0.0) {                             
			    r = q/p ;
			      
			    // line portion is outside     
			    if ( r < t1 )                         
			        retVal = false;    
			          
			    else if (r < t2)
			        t2 = r;
			}
		  
		// p = 0, so line is parallel to this clipping edge 
		else    
		// Line is outside clipping edge 
			if (q<0.0)                                 
				retVal = false;
			  
		return retVal;
	}

	public static Line liangBarskyClipping(Graphics g, Line l) { 
	
		if(!LineClipping.rectangleExist()) {
			return l;
		}
		
		int x1 = l.get_v1().get_x();
		int x2 = l.get_v2().get_x();
		
		int y1 = l.get_v1().get_y();
		int y2 = l.get_v2().get_y();
	
		float dx = x2-x1, dy;
		t1 = 0;
		t2 = 1;
		
		 // inside test wrt left edge
		if(clipTest (-dx, x1 - x_min))    
			
			 // inside test wrt right edge 
			if(clipTest (dx, x_max - x1)) {                
			    dy = y2 - y1;
			      
			    // inside test wrt bottom edge 
			    if(clipTest (-dy, y1 - y_min))
			      
			        // inside test wrt top edge 
			        if(clipTest (dy, y_max - y1)) {
			              
				        if(t2 < 1.0) {
				            x2 = (int)(x1 + t2*dx);
				            y2 = (int)(y1 + t2*dy);
				        }
				          
				        if(t1 > 0.0) {
				            x1 += t1*dx;
				            y1 += t1*dy;
				        }
				          
				        return new Line(new Vertex((int)x1,(int)y1), new Vertex((int)x2,(int)y2), l.get_line_type());
			          
			        }
			}
		
		
		return null;
		  
	} 
}
