package fibonacciHeap;

import java.util.HashMap;
import java.util.Stack;

public class FibHeap {

	Node heap;
	int card;

	public void insert(Node n) {
		if (heap == null) {
			heap = n;
			return;
		}
		heap.merge(n);
		if (heap.value > n.value) {
			heap = n;
		}
		card++;
	}

	public static class Node {
		Node next, prev;
		Node child, parent;
		int value, degree;
		boolean marked;

		Node copy() {
			return new Node(next, prev, child, parent, value, degree, marked);
		}

		public Node(int _value) {
			value = _value;
			next = this;
			prev = this;
			degree = 0;
			marked = false;
		}

		public Node(Node next, Node prev, Node child, Node parent, int value, int degree, boolean marked) {
			this.next = next;
			this.prev = prev;
			this.child = child;
			this.parent = parent;
			this.value = value;
			this.marked = marked;
			this.degree = degree;
		}

		private void merge(Node n2) {
			if (n2 == null)
				return;

			Node n1Next = next;
			Node n2Prev = n2.prev;

			next = n2;
			n2.prev = this;
			n1Next.prev = n2Prev;
			n2Prev.next = n1Next;
		}

		public String toString() {
			int lvl = 0;

			return this._toString(lvl);
		}

		private String _toString(int lvl) {
			Node act = this;
			String out = "";
			String separator = "|";
			for (int i = 0; i < lvl; i++) {
				separator += "    |";
			}

			do {
				out += ((lvl != 0) ? separator : "") + ((lvl != 0) ? "----" : "") + act.value + "\n"
						+ ((act.child != null) ? act.child._toString(lvl + 1) : "");
				act = act.next;
			} while (act != this);

			/*
			 * do{ out += act.value + (lvl + "") + "\n" + separator; out += "\n"
			 * + offset + ""+ "|\n" ; if(act.child != null) out += "|" + "----"
			 * + act.child._toString(lvl + 1); act = act.next; }while(act !=
			 * this);
			 */
			return out;
		}
	}

	void extactMin() {
		Node z = heap;

		if (z == null)
			return;

		if (heap.child != null) {
			// subo los hijos al root
			unParentHeapChild();
			Node nextNode = heap.next;
			Node lastChild = heap.child.prev;
			heap.next = heap.child;
			heap.child.prev = heap.next;
			nextNode.prev = lastChild;
			lastChild.next = nextNode;
		}
		heap.next.prev = heap.prev;
		heap.prev.next = heap.next;

		if (z == z.next)
			heap = null;
		else {
			heap = z.next;
			consolidate();
		}
		card--;

	}

	public void decreaseKey(Node nod, int k) {
		if (k >= nod.value)
			return;

		nod.value = k;
		Node y = nod.parent;
		if (y != null && nod.value < y.value) {
			cut(nod, y);
			cascadingCut(y);
		}
		if (nod.value < heap.value) {
			heap = nod;
		}
	}

	private void cascadingCut(Node y) {
		Node z = y.parent;
		if (z != null) {
			if (!y.marked)
				y.marked = true;
			else {
				cut(y, z);
				cascadingCut(z);
			}
		}
	}

	private void cut(Node nod, Node y) {

		nod.next.prev = nod.prev;
		nod.prev.next = nod.next;
		y.degree--;

		if (y.child == nod) {
			y.child = nod.next;
		}

		if (y.degree == 0) {
			y.child = null;
		}

		nod.prev = heap;
		nod.next = heap.next;
		heap.next = nod;
		nod.next.prev = nod;
		nod.parent = null;
		nod.marked = false;
	}

	private void unParentHeapChild() {
		Node evalNode = heap.child;
		if (evalNode == null)
			return;
		do {
			evalNode.marked = false;
			evalNode.parent = null;
			evalNode = evalNode.next;
		} while (evalNode != heap.child);
	}

	private void consolidate() {
		int numOfElem = 0;
		Node[] nodes = new Node[card + 1];
		Node evalNode = heap;
		if (evalNode != null)
			do {
				numOfElem++;
				evalNode = evalNode.next;
			} while (evalNode != heap);

		while (numOfElem > 0) {
			int d = evalNode.degree;
			Node nNode = evalNode.next;

			while (nodes[d] != null) {

				Node y = nodes[d];
				if (evalNode.value > y.value) {
					Node temp = evalNode;
					evalNode = y;
					y = temp;
				}
				link(y, evalNode);
				nodes[d] = null;
				d++;
			}
			nodes[d] = evalNode;
			evalNode = nNode;
			numOfElem--;
		}
		heap = null;

		for (int i = 0; i < card + 1; i++) {

			if (nodes[i] != null) {
				if (heap == null) {
					heap = nodes[i];
				} else {
					nodes[i].prev.next = nodes[i].next;
					nodes[i].next.prev = nodes[i].prev;
					nodes[i].prev = this.heap;
					nodes[i].next = this.heap.next;
					this.heap.next = nodes[i];
					nodes[i].next.prev = nodes[i];

					if (nodes[i].value < this.heap.value) {
						this.heap = nodes[i];
					}
				}
			}

		}
	}

	private void link(Node n1, Node n2) {
		n1.next.prev = n1.prev;
		n1.prev.next = n1.next;
		n1.parent = n2;
		if (n2.child == null) {
			n2.child = n1;
			n1.next = n1;
			n1.prev = n1;
		} else {
			n1.prev = n2.child;
			n1.next = n2.child.next;
			n2.child.next = n1;
			n1.next.prev = n1;
		}
		n2.degree++;
		n1.marked = false;
	}

	public String toString(){
		return heap.toString();
	}

	public static void main(String[] args) {
		FibHeap heap = new FibHeap();
		for (int i = 0; i < 2000; i++) {
			heap.insert(new Node(i));
		}
		Node node20 = new Node(20);
		heap.insert(node20);

		heap.extactMin();

		System.out.println(heap.toString());
	}

}
