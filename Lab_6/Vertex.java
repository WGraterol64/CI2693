class Vertex{

	public int id;
	public ArrayList<Integer> rooms; // habitaciones a las que se puede llegar desde el vertice actual
	public ArrayList<Integer> lSwitch; // habitaciones cuyas luces se pueden encender desde el vertice acutal

	/**
	* Constructor de la clase
	* @param id Entero identificador del vertice
	**/
	public Vertex(int id){

		this.id = id;
		this.rooms = new ArrayList<Integer>();
		this.lSwitch = new ArrayList<Integer>();
	}

	/**
	* Metodo para agregar el vertice r a los cuartos adyacentes al vertice actual
	* @param r Identificador del vertice adyacente
	**/
	public void addRoom(int r){
		this.rooms.add(r);
	}

	/**
	* Metodo para agregar el vertice s a la lista de habitaciones cuyas luces se pueden encender
	* @param s Identificador del vertice a agregar
	**/
	public void addSwitch(int s){
		this.lSwitch.add(s);
	}
}