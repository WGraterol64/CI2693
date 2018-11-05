class Graph{

	private ArrayList<Vertex> list;
	private int r;
	private int[][] distance;
	private Pair[][] predecessor;

	public Graph(int n){
		this.r = n;
		this.list = new ArrayList<Vertex>(n);
	}

	public void addRoomTransition(int u, int v){

		this.list[u].addRoom(v);
		this.list[v].addRoom(u);
	}

	public void addSwitchTransition(int u, int v){

		this.list[u].addSwitch(v);
		this.list[v].addSwitch(u);
	}

	public boolean solve(){

		this.predecessor = new Pair[size][(1<<size)];
		this.distance = new int[size][(1<<size)];
		for( int[] row : distance)
			Arrays.fill(row,-1);
		
		/*  El vertice inicial es siempre 0.
			El estado inicial es todas las luces apagadas 
		    excepto por la habitacion 0. Este estado se representa 
		    con la mascara de bits 1  */

		distance[0][1] = 0;
		bfs(0);
		
		return distance[r][(1<<r)] == -1;
	}

	public void bfs(int r){

		Queue<Pair> queue = new LinkedList<>();
		Pair p = new Pair(r,(1<<r));
		queue.add(p);
		while(!queue.empty()){
			Pair p = queue.poll();
			int u = p.first;
			int mask = p.second;

			
			
		}

	}
}