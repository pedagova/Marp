package graph;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;

public class GraphModel extends Observable {

	ArrayList<Node> nodes;

	public GraphModel() {
		nodes = new ArrayList<>();
		try {
			System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildRandom();
		setChanged();
		notifyObservers(null);
	}

	private void buildRandom() {
		for(int i = 0; i < 20; i++){
			Node aux = new Node(i, 500  * Math.cos(Math.PI * 2/ 20 * i), 500  * Math.sin(Math.PI * 2/ 20 * i));
			for(Node n : nodes){
				if(Math.random() > 0.90){
					aux.neighbours.add(n);
					n.neighbours.add(aux);
				}
			}
			nodes.add(aux);
		}
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void run() {
		runOpt();
		//setChanged();
		//notifyObservers();
	}

	/*
	 * public void runOpt(){ Node Y = nodes.get(0); PriorityQueue<Node> C = new
	 * PriorityQueue<>();
	 * 
	 * C.add(Y);
	 * 
	 * int bestCost = nodes.size();
	 * 
	 * while (!C.isEmpty() && coste_estimado(C.peek()) < bestCost){ Y =
	 * C.poll(); setColor(Y); if(isValid(Y)){ for(Node X : Y.getNeighboours()){
	 * 
	 * } } } }
	 */

	public void runOpt() {
		int colors = 0;
		//para cada nodo
		for (Node n : nodes) {
			//para cada color
			int i = 1;
			for (; i <= colors; i++) {
				boolean coincide = false;
				setChanged();
				Object[] o = {n, i};
				notifyObservers(o);
				//para cada vecino
				for (Node x : n.getNeighboours()) {
					//si color coincide
					if (x.color == i) {
						//siguiente valor
						coincide = true;
						break;
					}
				}
				if (!coincide) {
					break;
				}
			}
			if(i > colors) colors++;
			n.color = i;
		}
	}
	
	public void show() {
		// TODO Auto-generated method stub
		
		for(Node n : nodes){
			System.out.print("nodo " + n.name + " color " + n.color + "\n\t");
			for(Node x : n.neighbours){
				System.out.print(x.name + ", ");
			}
			System.out.println("");
		}
		
		
		System.out.println("\n\n\n--------------------\n\n\n" );
		
		for(Node n : nodes){
			System.out.print("nodo " + n.name + " color " + n.color + "\n\t");
			for(Node x : n.neighbours){
				System.out.print(x.color + ", ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		GraphModel m = new GraphModel();
		m.run();
		m.show();
	}
}
