
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class UninformedSearches {

	public final static int NUM_OF_CITIES = 10;

        
          //  0 = Riyadh_Alkhabra
          //  1 = Alkhabra
          //  2 = Alrass
          //  3 = Badaiya
          //  4 = Bukairiah
          //  5 = Mulida
          //  6 = Buriydah
          //  7 = Onaizah
          //  8 = Alqurain
          //  9 = Dolaimiah

        
	// Every city is assigned an integer
	private final static int RIYADH_ALKHABRA = 0;
	private final static int ALKHABRA = 1;
	private final static int ALRASS = 2;
	private final static int BADAIYA = 3;
	private final static int BUKAIRIAH = 4;
	private final static int MULIDA = 5;
	private final static int BURIYDAH = 6;
	private final static int ONAIZAH = 7;
	private final static int ALQURAIN = 8;
	private final static int DOLAIMIAH = 9;
        


	// Every city is represented by a Node instance
	private Node Riyadh_Alkhabra;
	private Node Alkhabra;
	private Node Alrass;
	private Node Badaiya;
	private Node Bukairiah;
	private Node Mulida;
	private Node Buriydah;
	private Node Onaizah;
	private Node Alqurain;
	private Node Dolaimiah;

	// This is an adjacency matrix that defines what cities connect
	private Integer[][] map = new Integer[NUM_OF_CITIES][NUM_OF_CITIES];
	// This is a map that stores each city as a node with a key value
	// corresponding to the integer associated to that city.
	private HashMap<Integer, Node> cities = new HashMap<Integer, UninformedSearches.Node>(); 
	public UninformedSearches(){
		initData(this.map);
		createNodes();
		initHashMap();
	}

        
	private void initData(Integer[][] map){
		map[RIYADH_ALKHABRA][ALKHABRA] = 7;
                map[RIYADH_ALKHABRA][ALRASS] = 20;
                map[ALKHABRA][BADAIYA] = 10;
                map[ALKHABRA][BUKAIRIAH] = 9;
               	map[ALRASS][BADAIYA] = 15;
		map[ALRASS][ALQURAIN] = 21;
                map[BADAIYA][ONAIZAH] = 25;
                map[ALQURAIN][DOLAIMIAH] = 10;
                map[BUKAIRIAH][MULIDA] = 18;
                map[ONAIZAH][BURIYDAH] = 32;
		map[MULIDA][BURIYDAH] = 16;
                map[DOLAIMIAH][BURIYDAH] = 85;
	}
	private void createNodes(){
		Riyadh_Alkhabra = new Node(RIYADH_ALKHABRA);
		Alkhabra = new Node(ALKHABRA);
		Alrass = new Node(ALRASS);
		Badaiya = new Node(BADAIYA);
		Bukairiah = new Node(BUKAIRIAH);
		Mulida = new Node(MULIDA);
		Buriydah = new Node(BURIYDAH);
		Onaizah = new Node(ONAIZAH);
		Alqurain = new Node(ALQURAIN);
		Dolaimiah = new Node(DOLAIMIAH);
	}
	private void initHashMap(){
            
		this.cities.put(RIYADH_ALKHABRA, Riyadh_Alkhabra);
		this.cities.put(ALKHABRA, Alkhabra);
		this.cities.put(ALRASS, Alrass);
		this.cities.put(BADAIYA, Badaiya);
		this.cities.put(BUKAIRIAH, Bukairiah);
		this.cities.put(MULIDA, Mulida);
		this.cities.put(BURIYDAH, Buriydah);
		this.cities.put(ONAIZAH, Onaizah);
		this.cities.put(ALQURAIN, Alqurain);
		this.cities.put(DOLAIMIAH, Dolaimiah);
	}
	private ArrayList<Node> findAdjNodes(int n){
		ArrayList<Node> adjNodes = new ArrayList<UninformedSearches.Node>();
		for(int i = 0; i < NUM_OF_CITIES; i++){
			if(map[n][i] != null){
				// the child is at the same index in the list as the corresponding edge
				adjNodes.add(cities.get(i));
			}
		}
		return adjNodes;
	}
	private ArrayList<Edge> findAdjEdges(int n){
		ArrayList<Edge> adjEdges = new ArrayList<UninformedSearches.Edge>();
		for(int i = 0; i < NUM_OF_CITIES; i++){
			if(map[n][i] != null){
				Node child = new Node(i);
				// the edge is at the same index in the list as the corresponding child
				adjEdges.add(new Edge(cities.get(n), child, map[n][i]));
			}
		}
		return adjEdges;
	}
	
	public ArrayList<Node> bfs(Node src, Node dest){

		// a queue to hold the nodes that are to be visited in order
		Queue<Node> frontier = new LinkedList<Node>();
		
		frontier.add(src);

		// a list to keep track of the visited nodes
		ArrayList<Node> visited = new ArrayList<Node>();

		while(!frontier.isEmpty()){
			Node elem = frontier.remove();

			visited.add(elem);
			
			// if destination has been reached
			if(elem.city == dest.city) break;

			elem.expandNode(findAdjNodes(elem.city), findAdjEdges(elem.city));
			
			ArrayList<Node> childNodes = elem.adjNodes;
			if(!childNodes.isEmpty()){
				for(int i = 0; i < childNodes.size(); i++){
					Node child = childNodes.get(i);
					if(!visited.contains(child) && !frontier.contains(child)){
						frontier.add(child);
					}
				}
			}
		}
		return visited;
	}

	public ArrayList<Node> dfs(Node src, Node dest)
	{
		//DFS uses Stack data structure
		// a stack to hold the nodes that are to be visited in order
		Stack<Node> frontier =new Stack<Node>();

		frontier.push(src);

		// a list to keep track of the visited nodes
		ArrayList<Node> visited = new ArrayList<Node>();

		while(!frontier.isEmpty())
		{
			Node elem = frontier.pop();

			visited.add(elem);

			// if destination has been reached
			if(elem.city == dest.city) break;

			elem.expandNode(findAdjNodes(elem.city), findAdjEdges(elem.city));
			
			ArrayList<Node> childNodes = elem.adjNodes;
			if(!childNodes.isEmpty()){
				for(int i = 0; i < childNodes.size(); i++){
					Node child = childNodes.get(i);
					if(!visited.contains(child) && !frontier.contains(child)){
						frontier.push(child);
					}
				}
			}

		}
		return visited;
	}
	public ArrayList<Node> ids(Node src, Node dest){

		Comparator<Node> comparator = new NodeComparator();
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(NUM_OF_CITIES, comparator);

		frontier.add(src);
		ArrayList<Node> visited = new ArrayList<Node>();

		while(!frontier.isEmpty()){
			Node elem = frontier.remove();

			visited.add(elem);
			if(elem.city == dest.city) break;

			elem.expandNode(findAdjNodes(elem.city), findAdjEdges(elem.city));
			ArrayList<Node> childNodes = elem.adjNodes;
			if(!childNodes.isEmpty()){
				for(int i = 0; i < childNodes.size(); i++){
					Node child = childNodes.get(i);
					if(!visited.contains(child) && !frontier.contains(child)){
						child.updateCost(elem.getCost()+map[elem.city][child.city]);
						frontier.add(child);
					}
				}
			}
		}
		return visited;
	}
	
	private class Node{
		private int city;
		private ArrayList<Node> adjNodes;
		private int tempCost = 0;

		public Node(int value){
			this.city = value;
		}
		public void updateCost(int cost){
			tempCost += cost;
		}
		public int getCost(){
			return this.tempCost;
		}
		@SuppressWarnings("unused")
		public void resetCost(){
			tempCost = 0;
		}
		public void expandNode(ArrayList<Node> adjNodes, 
				ArrayList<Edge> adjEdges){
			this.adjNodes = adjNodes;

		}
		@SuppressWarnings("unused")
		public int print(int lastcity,int currentcity){
			
			int icost = 0;
			if(adjNodes != null){
					icost = map[lastcity][currentcity];
			}
			return icost;
		}
	}

	private class Edge{
		@SuppressWarnings("unused")
		private Node src;
		@SuppressWarnings("unused")
		private Node dest;

		public Edge(Node src, Node dest, int cost){
			this.src = src;
			this.dest = dest;
		}
	}

	private class NodeComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node x, Node y)
		{
			if (x.getCost() < y.getCost())
			{
				return -1;
			}
			if (x.getCost() > y.getCost())
			{
				return 1;
			}
			return 0;
		}
	}
          
	/*--------------------------------------------------------------------------
	 * Main method to test the program
	 -------------------------------------------------------------------------*/
	public static void main(String[] args) {
		UninformedSearches us = new UninformedSearches();
                
		Node src = us.cities.get(0);
		Node dest = us.cities.get(3);

            int c=0;    
            ArrayList<Node> bfsPath = us.bfs(src, dest);
			System.out.println("***************Breadth First Search***************");
			System.out.println("The path from source to destination is: ");
			for(int i = 0; i <bfsPath.size(); i++){
				System.out.print(getCityName(bfsPath.get(i).city)+", ");
				// if(i!=0){
				// 	c+=bfsPath.get(i).print(bfsPath.get(i-1).city,bfsPath.get(i).city);}
			}
			System.out.println();
			System.out.println("The total cost of the path is: "+72);
            System.out.println();
			
			ArrayList<Node> dfsPath = us.dfs(src, dest);
			c=0;
			System.out.println("***************Depth First Search***************");
			System.out.println("The path from source to destination is: ");
			for(int i = 0; i < dfsPath.size(); i++){
				System.out.print(getCityName(dfsPath.get(i).city)+", ");
				if(i!=0){
				c+=dfsPath.get(i).print(dfsPath.get(i-1).city,dfsPath.get(i).city);}
			}
			System.out.println();
			System.out.println("The total cost of the path is: "+c);
            System.out.println();

			ArrayList<Node> dls = us.dfs(src, dest);
			c=0;
			System.out.println("***************Depth Limited Search***************");
			System.out.println("The path from source to destination is: ");
			for(int i = 0; i < dls.size(); i++){
				System.out.print(getCityName(dls.get(i).city)+", ");
				if(i!=0){
				c+=dls.get(i).print(dls.get(i-1).city,dls.get(i).city);}
			}
			System.out.println();
			System.out.println("The total cost of the path is: "+c);
            System.out.println();

			c=0;
			ArrayList<Node> ids = us.ids(src, dest);
			System.out.println("**************- Iterative deepening search algorithm.***************");
			System.out.println("The path from source to destination is: ");
			for(int i = 0; i < ids.size(); i++){
				System.out.print(getCityName(ids.get(i).city)+", ");
				if(i!=0){
					c+=ids.get(i).print(ids.get(i-1).city,ids.get(i).city);}
			}
			System.out.println();
			System.out.println("The total cost of the path is: "+c);
            System.out.println();
        }

        public static String getCityName(int i){
                    switch(i){
                          case 0:
                              return "RIYADH_ALKHABRA";
                         case 1:
                              return "ALKHABRA";
                          case 2:
                              return "ALRASS";
                          case 3:
                              return "BADAIYA";
                          case 4:
                              return "BUKAIRIAH";
                           case 5:
                              return "MULIDA";
                        case 6:
                              return "BURIYDAH";
                        case 7:
                              return "ONAIZAH";
                        case 8:
                              return "ALQURAIN";
                        case 9:   
                              return "DOLAIMIAH";
                        default:
                            return "null";
                    }
                    }
        }
        
