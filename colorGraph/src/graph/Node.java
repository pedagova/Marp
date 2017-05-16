package graph;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

class Node {
	int name;
	double x, y;
	int color;
	ArrayList<Node> neighbours;
	public Node(int _name, double d, double e) {
		name = _name;
		x = d;
		y = e;
		color = 0;
		neighbours = new ArrayList<>();
	
	}

	public void show(PApplet s, Color c) {
		s.fill(c.getRed(), c.getGreen(), c.getBlue());
		showNode(s);
		
	}
	
	public void showNode(PApplet s) {
		s.ellipse((float)x , (float)y , 40, 40);
		s.fill(0);
		s.text(Integer.toString(name), (float)x, (float)y);
	}

	public ArrayList<Node> getNeighboours(){
		return neighbours;
	}

	public void showEdges(PApplet s) {
		for(Node n : neighbours)
			s.line((float)x, (float)y, (float)n.x, (float)n.y);	
	}

}