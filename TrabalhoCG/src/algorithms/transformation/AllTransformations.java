package algorithms.transformation;

import entity.geometry.Vertex;

public class AllTransformations {

	
	public Vertex translate(Vertex v1, int x, int y ) {
		
		Vertex new_vertex = new Vertex(0,0);
		
		new_vertex.set_x(v1.get_x() + x);
		new_vertex.set_y(v1.get_y() + y);
		
		
		return new_vertex;
	}
	
	public Vertex scaling(Vertex v1, int scale ) {
		
		Vertex new_vertex = new Vertex(0,0);
		
		new_vertex.set_x(v1.get_x() * scale);
		new_vertex.set_y(v1.get_y() * scale);
		
		
		return new_vertex;
	}

	public Vertex scaling(Vertex v1, int scale_x, int scale_y ) {
		
		Vertex new_vertex = new Vertex(0,0);
		
		new_vertex.set_x(v1.get_x() * scale_x);
		new_vertex.set_y(v1.get_y() * scale_y);
		
		
		return new_vertex;
	}
	
	public Vertex rotating(Vertex v1, double angle) {
		
		Vertex new_vertex = new Vertex(0,0);
		
		new_vertex.set_x((int)(v1.get_x()*Math.cos(angle) - v1.get_y()*Math.sin(angle)));
		new_vertex.set_y((int)(v1.get_x()*Math.sin(angle) + v1.get_y()*Math.cos(angle)));
		
		return new_vertex;
	}

	public Vertex reflexion(Vertex v1, int x, int y ) {
		
		Vertex new_vertex = new Vertex(0,0);
		
		new_vertex.set_x(v1.get_x() * x);
		new_vertex.set_y(v1.get_y() * y);
		
		return new_vertex;
	}
}
