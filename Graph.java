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

	public int backtracking(){

		this.predecessor = new Pair[size][(1<<size)];
		this.distance = new int[size][(1<<size)];
		for( int[] row : distance)
			Arrays.fill(row,-1);
		
		/*  El vertice inicial es siempre 0.
			El estado inicial es todas las luces apagadas 
		    excepto por la habitacion 0. Este estado se 
		    representa con la mascara de bits 1         */

		Pair p = new Pair(0,1);
		predecessor[0][1] = p;
		distance[0][1] = 0;
		bfs(0);
		
		return distance[r][(1<<r)];
	}

	public void bfs(int r){

		Queue<Pair> queue = new LinkedList<>();
		Pair p = new Pair(r,(1<<r));
		queue.add(p);
		while(!queue.empty()){
			Pair p = queue.poll();
			int u = p.first;
			int mask = p.second;

			for(int v : u.lSwitch){
				int newMask = mask ^ (1<<v);
				if(d[u][newMask]==-1){
					d[u][newMask] = d[u][mask] + 1;
					Pair pp = new Pair(u,newMask);
					queue.add(pp);
				}
			}

			for(int v : u.rooms){
				if(mask&(1<<v) && d[v][mask] == -1){
					d[v][mask] = d[u][mask] + 1;
					Pair pp = new Pair(v,mask);
					queue.add(pp);
				}
			}			
		}
	}

	public void printSol(){

		Stack<String> stack = new Stack<String>();
		int u = r;
		int mask = (1<<r);
		while( u!=0 || mask!=1 ){

			int v = predecessor[u][mask].first;
			int nMask = predecessor[u][mask].second;

			if(u==v){
				int dif = mask ^ nMask;
				int m=0;
				while(dif != 1){
					dif = (dif>>1);
					m++;
				}
				if((1<<m)&nMask){
					String s = "- Enciende la luz en la habitacion "+valueOf(m)+".";
					stack.push(s);
				}else{
					String s = "- Apaga la luz en la habitacion "+valueOf(m)+".";
					stack.push(s);
				}
			}else{
				String s = "- Muevete a la habitacion "+valueOf(m)+".";
				stack.push(s);
			}

			u = v;
			mask = nMask;
		}

	}
}