package graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class GraphView implements Observer {

	PApplet sckech;
	ArrayList<Color> colors;

	public GraphView(PApplet _sckech) {
		sckech = _sckech;
		colors = new ArrayList<>();
		colors.add(new Color(0, 0, 0));
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
		if (arg == null)
			showAll((GraphModel) o);
		else {
			
			Node n = (Node) (((Object[]) arg)[0]);
			int colorIndex = (int) (((Object[]) arg)[1]);
			
			if (colorIndex >= colors.size() - 1) {
				int r = (int) (Math.random() * 255);
				int g = (int) (Math.random() * 255);
				int b = (int) (Math.random() * 255);
				colors.add(new Color(r, g, b));
			}
			
			//showAll((GraphModel) o);
			n.show(sckech, colors.get(colorIndex));
		}
		
		sckech.redraw();
	}

	private void showAll(GraphModel m) {
		ArrayList<Node> nodes = m.getNodes();
		for (Node n : nodes) {
			n.showEdges(sckech);
		}
		for (Node n : nodes) {
			n.show(sckech, colors.get(n.color));
		}
	}

	/*
	 * public void print(boolean[][] bs, Node[] nodes) { sckech.translate(50,
	 * 50); for (int i = 0; i < 20; i++) { for (int j = 0; j < i - 1; j++) { if
	 * (bs[i][j]) { sckech.line((float)nodes[i].x , (float)nodes[i].y ,(float)
	 * nodes[j].x,(float) nodes[j].y ); } } }
	 * 
	 * for (int i = 0; i < 20; i++) { nodes[i].show(sckech); } }
	 */
}
