package scene;

import java.awt.Graphics;
import java.util.Comparator;

/**
 * Classe template para as cenas do programa
 * @author gustavolr
 *
 */
public class Scene {
	
	private SceneState curr_state;
	private byte render_priority;
	private String name;

	public Scene(String name) {
		this.name            = name;
		this.curr_state      = SceneState.Stopped;
		this.render_priority = 0;
	}
	
	public Scene(String name, byte render_priority) {
		this.name            = name;
		this.curr_state      = SceneState.Stopped;
		this.render_priority = render_priority;
	}
	
	/**
	 * 
	 * @return Retornar o atual estado que a cena esta
	 */
	public SceneState get_curr_state() {
		return this.curr_state;
	}
	
	public byte get_render_priority() {
		return this.render_priority;
	}
	
	public String get_scene_name() {
		return this.name;
	}
	
	public void set_curr_state(SceneState curr_state) {
		this.curr_state = curr_state;
	}
	
	public void set_render_priority(byte render_priority) {
		this.render_priority = render_priority;
	}
	
	public void set_scene_name(String name) {
		this.name = name;
	}
	
	public static Comparator<Scene> sceneSorter = new Comparator<Scene>() {
		
		@Override
		public int compare(Scene sc1, Scene sc2) {
			if (sc1.get_render_priority() < sc2.get_render_priority()) {
				return + 1;
			}
			if (sc1.get_render_priority() > sc2.get_render_priority()) {
				return - 1;
			}
			return 0;
		}
	};
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}
