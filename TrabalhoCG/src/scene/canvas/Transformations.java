package scene.canvas;

public enum Transformations {
	
	Selection(0),
	Scaling(1),
	Rotation(2),
	Reflexion(3);
	
	private int action_code;
	
	Transformations(int action_code) {
		this.action_code = action_code;
	}
	
	public int get_action_code() {
		return this.action_code;
	}
}
