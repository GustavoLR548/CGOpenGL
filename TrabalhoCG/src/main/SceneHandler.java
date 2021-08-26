package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import scene.Scene;
import scene.SceneState;

public class SceneHandler {

	private static ArrayList<Scene> all_scenes;
	private static Scene curr_running_scene;
	
	public SceneHandler() {
		curr_running_scene = null;
		all_scenes = new ArrayList<Scene>();
	}
	
	public void tick() {
		
		if(curr_running_scene != null) {
			curr_running_scene.tick();
			
		} else {
			System.err.println("[ERROR]: The current scene cannot be null");
			System.exit(0);
		}
	}
	
	public void render(Graphics g) {
		
		if(curr_running_scene != null) {
			curr_running_scene.render(g);
			
		} else {
			System.err.println("[ERROR]: The current scene cannot be null");
			System.exit(0);
		}
		
		// Fazer uma ordenacao das cenas usando o parametro
		// 'render_priority'
		Collections.sort(all_scenes, Scene.sceneSorter);
		
		for(Scene sc : all_scenes) {
			
			if(sc.get_curr_state() != SceneState.Stopped) 
				sc.render(g);
			
		}
	}
	
}
