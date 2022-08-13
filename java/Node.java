class Node {
	public final String value;
	public destination[] neighbors;
	public Node parent;
	public int heuristic;

	public Node(String val, int heur) {

		value = val;
		heuristic = heur;
	}

	public Node(Node node) {
		int i = 0;
		neighbors = new destination[node.neighbors.length];
		value = node.value;
		for (destination e : node.neighbors) {
			neighbors[i++] = e;
		}
		parent = node.parent;
	}

	public String toString() {
		return value + "(" + heuristic + ")";
	}

}
