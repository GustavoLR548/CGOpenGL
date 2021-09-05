package scene.canvas;

/**
 * Enum para gerenciar as possíveis ações no canvas
 * @author gustavolr
 *
 */
public enum CanvasAction {
	
	Nothing(-1),
	Selection(0),
	Draw_line_dda(1),
	Draw_line_bresenham(2),
	Draw_circle(3),
	Clipping_cohen(4),
	Clear_screen(5),
	Clipping_liang(6);
	
	private int action_code;
	
	CanvasAction(int action_code) {
		this.action_code = action_code;
	}
	
	public int get_action_code() {
		return this.action_code;
	}
}
