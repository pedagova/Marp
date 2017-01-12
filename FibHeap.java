package fibHeap;

public class FibHeap {

	class Node {
		Node next, prev;
		Node child, parent;
		int value, degree;
		boolean marked;
		
		Node copy() {
			return new Node(next, prev, child, parent, value, degree, marked);
		}

		public Node(Node next, Node prev, Node child, Node parent, int value, int degree,
				boolean marked) {
			this.next = next;
			this.prev = prev;
			this.child = child;
			this.parent = parent;
			this.value = value;
			this.marked = marked;
			this.degree = degree;
		}
		
		private void merge(Node n2){
			if(n2 == null)return;
			
			Node n1Next = next;
			Node n2Prev = n2.prev;
			
			next = n2;
			n2.prev = this;
			n1Next.prev = n2Prev;
			n2Prev.next = n1Next;
		}
	}

	private Node heap;

	void extactMin() {

		Node z = heap.copy();

		if (z == null)
			return;

		Node evalNode = (z.child == null) ? z.child : z.child.copy() ;
		//busco entre los hijos hasta que el padre sea null, pues este sería el 1º que he intercambiado
		while (evalNode.parent != null) {
			//como todos los hijos han de ser mayores en merge no daría problemas
			//pues el intercambio de nodos no podría producirse
			heap.merge(evalNode);
			evalNode.parent = null;
			evalNode = evalNode.next;
		}
		
		heap.next.prev = heap.prev;
		heap.prev.next = heap.next;
		
		if(z == z.next) heap = null;
		else {
			heap = z.next;
			consolidate();
		}
		heap.degree --;
		
	}

	private void consolidate() {
		int D = heap.degree;
		Node[] nodes = new Node[D];
		Node startNode = heap;
		Node evalNode = heap.next;
		while(evalNode != startNode){
			int d = evalNode.degree;
			while(nodes[d] != null){
				Node y = nodes[d];
				if(evalNode.value > y.value) {
					Node temp = evalNode;
					evalNode = y;
					y = temp;
				}
				link(y, evalNode);
				nodes[d] = null;
				d++;
			}
			nodes[d] = evalNode;
		}
		heap = null;
		
		for (int i = 0; i < D; i++){
			
			if(nodes[i] != null){
				if(heap == null){
					nodes[i].next = nodes[i];
					nodes[i].prev = nodes[i];
					heap = nodes[i];
				}	
				else{
					heap.merge(nodes[i]);
					if(nodes[i].value < heap.value) heap = nodes[i];
				}
			}
			
		}
	}

	private void link(Node n1, Node n2) {
		n1.next.prev = n1.prev;
		n1.prev.next = n1.next;
		n1.parent = n2;
		n2.child.merge(n1);
		n2.degree += n1.degree;
		
	}
}
