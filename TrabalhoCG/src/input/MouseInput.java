package input;

import entity.Entity;

/**
 * Classe para gerenciar inputs do mouse do usuario
 * @author gustavolr
 *
 */
public class MouseInput {

	// Coordenadas
	private static int x;
	private static int y;
	
	// Possíveis botoes do mouse
	private static boolean right_mouse_button;
	private static boolean left_mouse_button;
	
	// getters
	
	public static int get_x() {
		return x;
	}
	
	public static int get_y() {
		return y;
	}
	
	public static Entity get_mouse_entity() {
		return new Entity(x,y,10,10);
	}
	
	public static boolean is_right_button_pressed() {
		boolean value = right_mouse_button;
		if(right_mouse_button) 
			right_mouse_button = false;
		return value;
	}

	public static boolean is_left_button_pressed() {
		return left_mouse_button;
	}
	
	//setters
	
	public static void set_x(int new_x) {
		x = new_x;
	}

	public static void set_y(int new_y) {
		y = new_y;
	}

	public static void set_right_button_press(boolean new_value) {
		right_mouse_button = new_value;
	}
	
	public static void set_left_button_press(boolean new_value) {
		left_mouse_button = new_value;
	}

}
