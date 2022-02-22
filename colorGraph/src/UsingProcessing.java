import graph.GraphController;
import processing.core.PApplet;

public class UsingProcessing extends PApplet {
	
	GraphController graph;
	
	public void settings() {
		//size(283, 283);
		fullScreen();
		graph = new GraphController(this);	
		
	}

	public void setup() {
		//no test
		noLoop();
		
	}

	public void draw() {
		translate(width / 2, height / 2);
		graph.run();
	}

	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}
	
	@Override
	public void mousePressed() {
		
		
		
	}
	
	@Override
	public void mouseReleased() {
		
		
		
	}
	
}
