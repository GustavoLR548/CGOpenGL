package algorithms.clipping;

public class LineClipping {
	
	// Defining region codes
	private final int INSIDE = 0; // 0000
	private final int LEFT = 1; // 0001
	private final int RIGHT = 2; // 0010
	private final int BOTTOM = 4; // 0100
	private final int TOP = 8; // 1000
	  
	// Defining x_max, y_max and x_min, y_min for
	// clipping rectangle. Since diagonal points are
	// enough to define a rectangle
	private final int x_max = 10;
	private final int y_max = 8;
	private final int x_min = 4;
	private final int y_min = 4;
	  
	// Function to compute region code for a point(x, y)
	int computeCode(double x, double y)
	{
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
	  
	
	// Implementing Cohen-Sutherland algorithm
	// Clipping a line from P1 = (x2, y2) to P2 = (x2, y2)
	public void cohenSutherlandClip(double x1, double y1, double x2, double y2) {
		
	    // Compute region codes for P1, P2
	    int code1 = computeCode(x1, y1);
	    int code2 = computeCode(x2, y2);
	  
	    // Initialize line as outside the rectangular window
	    boolean accept = false;
	  
	    while (true) {
	        if ((code1 == 0) && (code2 == 0)) {
	            // If both endpoints lie within rectangle
	            accept = true;
	            break;
	        }
	        else if ((code1 & code2) > 0) {
	            // If both endpoints are outside rectangle,
	            // in same region
	            break;
	        }
	        else {
	            // Some segment of line lies within the
	            // rectangle
	            int code_out;
	            double x = 0, y = 0;
	  
	            // At least one endpoint is outside the
	            // rectangle, pick it.
	            if (code1 != 0)
	                code_out = code1;
	            else
	                code_out = code2;
	  
	            // Find intersection point;
	            // using formulas y = y1 + slope * (x - x1),
	            // x = x1 + (1 / slope) * (y - y1)
	            if ((code_out & TOP) > 0) {
	                // point is above the clip rectangle
	                x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
	                y = y_max;
	            }
	            else if ((code_out & BOTTOM) > 0) {
	                // point is below the rectangle
	                x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
	                y = y_min;
	            }
	            else if ((code_out & RIGHT) > 0) {
	                // point is to the right of rectangle
	                y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
	                x = x_max;
	            }
	            else if ((code_out & LEFT) > 0) {
	                // point is to the left of rectangle
	                y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
	                x = x_min;
	            }
	  
	            // Now intersection point x, y is found
	            // We replace point outside rectangle
	            // by intersection point
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
	    if (accept) {
	    	System.out.println("Line accepted from " + x1 + ", " + y1 + " to " + x2 + ", " + y2  );
	        // Here the user can add code to display the rectangle
	        // along with the accepted (portion of) lines
	    }
	    else
	    	System.out.println("Line rejected");
	}
	
/*
	public boolean clipTest (float p,float q, float t1, float t2){
		float r;
		boolean retVal = true;
		  
		//line entry point
		if (p < 0.0) {    
		      
		    r = q /p ;
		      
		    // line portion is outside the clipping edge
		    if ( r > t2 )                         
		    retVal = false;
		      
		    else
			    if (r > t1 )
			    	t1 = r; 
		}
		  
		else
		  
		//line leaving point
		if (p>0.0) {                             
		    r = q/p ;
		      
		    // line portion is outside     
		    if ( r < t1 )                         
		        retVal = true;    
		          
		    else 
		    	if (r < t2)
		        t2 = r;
		}
		  
		// p = 0, so line is parallel to this clipping edge 
		else    
		  
		// Line is outside clipping edge 
		if (q<0.0)                                 
		retVal = false;
		  
		return retVal;
	}
	  
	void clipLine (dcPt winMin, dcPt winMax, wcPt2 pl , wcPt2 p2) { 
		float t1 = 0, t2 = 1, dx = p2.x-p1.x, dy;
		
		 // inside test wrt left edge
		if(clipTest (-dx, p1.x - winMin.x, &t1, &t2))    
			
			 // inside test wrt right edge 
			if(clipTest (dx, winMax.x - p1.x, &t1, &t2)) {                
			    dy = p2.y - p1.y;
			      
			    // inside test wrt bottom edge 
			    if(clipTest (-dy, p1.y - winMin.y, &t1, &t2))
			      
			        // inside test wrt top edge 
			        if(clipTest (dy, winMax.y - p1.y, &t1, &t2)) {
			              
				        if(t2 < 1.0) {
				            p2.x = p1.x + t2*dx;
				            p2.y = p1.y + t2*dy;
				        }
				          
				        if(t1 > 0.0) {
				            p1.x += t1*dx;
				            p1.y += t1*dy;
				        }
				          
				        lineDDA ( ROUND(p1.x), ROUND(p1.y), ROUND(p2.x), ROUND(p2.y) );
			          
			        }
			}
		  
		} 
		*/
}
