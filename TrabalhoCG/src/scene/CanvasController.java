package scene;

import java.awt.Color;
import java.awt.Graphics;

import entity.ui.Button;
import input.MouseInput;

public class CanvasController extends Scene {
	
	Color my_color;
	Button button;
	
	public CanvasController(int x, int y, int width, int height) {
		super(x, y, width, height, "canvas_controller", (byte)0);
		my_color = new Color(211,211,211);
		button = new Button(50, 50, 200, 40);
	}
	
	public void tick() {
		
		if(isColidding(MouseInput.get_mouse_entity())) 
			button.tick();
		
	}
	
	public void render(Graphics g) {
		g.setColor(my_color);
		g.fillRect(0, 0, this.width, this.height);
		
		button.render(g);

	}

}
