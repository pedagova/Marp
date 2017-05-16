package graph;

import processing.core.PApplet;

public class GraphController {
	
	GraphModel model;
	GraphView view;
	PApplet sckech;
	public GraphController(PApplet p) {
		model = new GraphModel();
		view = new GraphView(p);
		sckech = p;
		model.addObserver(view);
	}
	
	public void run(){
		model.run();
	}

	public void update() {
		
	}
}
