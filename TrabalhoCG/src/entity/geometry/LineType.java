package entity.geometry;

import scene.canvas.CanvasAction;

/**
 * Enum para os tipos de linhas possíveis
 * @author gustavolr
 *
 */
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
	
	/**
	 * Função para converter uma CanvasAction, caso o mesmo seja válido
	 * @param ca é a ação do canvas enviado
	 * @return
	 */
	public static LineType convertCanvasAction(CanvasAction ca) {
		
		if(ca == CanvasAction.Draw_line_bresenham) {
			return LineType.Bresenham; 
		} else if(ca == CanvasAction.Draw_line_dda) {
			return LineType.DDA;
		}
		
		return LineType.Invalid;
	}
}
