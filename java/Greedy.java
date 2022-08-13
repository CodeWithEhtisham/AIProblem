

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Greedy {

	public static void UniformCostSearch(Node source, Node goal) {

		List<Node> explored = new ArrayList<Node>();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {

			public int compare(Node i, Node j) {
				if ((i.heuristic > j.heuristic)) {
					return 1;
				}

				else if (i.heuristic < j.heuristic) {
					return -1;
				}

				else {
					return 0;
				}
			}
		}

		);

		queue.add(source);
		List<Node> path = new ArrayList<Node>();
		int c=0;
		System.out.println("Path:");
		do {

			path.clear();
			Node current;
                    current = queue.poll();
			System.out.println(current.value);
			explored.add(current);
			for (Node node = current; node != null; node = node.parent) {
				path.add(node);
			}
			if (current.value.equals(goal.value)) {
				// System.out.println("Least cost solution found : " + current.neighbors);
				break;

			}
			for (destination e : current.neighbors) {
				Node neighbors = e.target;
				if ((queue.contains(neighbors) || explored.contains(neighbors)) && !path.contains(neighbors)) {
					Node n = new Node(neighbors);
					n.parent = current;
					queue.add(n);
					
					c+=n.heuristic;
				} else if (!path.contains(neighbors)) {
					neighbors.parent = current;
					queue.add(neighbors);
					c+=neighbors.heuristic;
				}

			}
		} while (!queue.isEmpty());
		System.out.println("Least cost solution found : " + c);

	}
		//  1 = Riyadh_Alkhabra
        //  2 = Alkhabra
        //  3 = Alrass
        //  4 = Badaiya
        //  5 = Bukairiah
        //  6 = Mulida
        //  7 = Buriydah
        //  8 = Onaizah
        //  9 = Alqurain
        //  10 = Dolaimiah

	public static void main(String[] args) {
		Node n1 = new Node("Riyadh_Alkhabra",8);
		Node n2 = new Node("Alkhabra",12);
		Node n3 = new Node("Alrass",23);
		Node n4 = new Node("Badaiya",34);
		Node n5 = new Node("Bukairiah",11);
		Node n6 = new Node("Mulida",43);
		Node n7 = new Node("Buriydah",23);
		Node n8 = new Node("Onaizah",31);
		Node n9 = new Node("Alqurain",44);
		Node n10 = new Node("Dolaimiah",7);

		n1.neighbors = new destination[] { new destination(n2, 7), new destination(n3, 20)};

		n2.neighbors = new destination[] { new destination(n4, 10), new destination(n5, 9) };

		n5.neighbors = new destination[] { new destination(n6, 71)};

		n6.neighbors = new destination[] { new destination(n7, 16)};

		n4.neighbors = new destination[] { new destination(n3, 15), new destination(n8, 25) };

		n8.neighbors = new destination[] { new destination(n7, 32) };

		n3.neighbors = new destination[] { new destination(n9, 21) };

		n9.neighbors = new destination[] { new destination(n10, 10) };

		n10.neighbors = new destination[] { new destination(n7, 85) };

		n10.neighbors = new destination[] { new destination(n9, 70)};

		
		UniformCostSearch(n1, n7);

	}

}
