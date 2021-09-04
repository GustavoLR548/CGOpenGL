package entity.geometry;

import scene.canvas.CanvasAction;

public enum LineType {
	
	Invalid(-1),
	DDA(0),
	Bresenham(1);
	
	private int type_code;
	
	LineType(int type_code) {
		this.type_code = type_code;
	}
	
	public int get_type_code() {
		return this.type_code;
	}
	
	public static LineType convertCanvasAction(CanvasAction ca) {
		
		if(ca == CanvasAction.Draw_line_bresenham) {
			return LineType.Bresenham; 
		} else if(ca == CanvasAction.Draw_line_dda) {
			return LineType.DDA;
		}
		
		return LineType.Invalid;
	}
}
