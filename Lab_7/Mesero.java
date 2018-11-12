import java.util.*;

public class Mesero{
  static Graph r;

  public static void loadGraph(String arch){
    BufferedReader read = new BufferedReader(new FileReader(arch));
    int n = Integer.parseInt(read.readLine.trim());
    this.r = new Graph(n);
    for(int i=0; i<n; i++){
        String line = read.readLine().trim();
        String xy = line.split(" ");
        this.r.addNode(i,Integer.parseInt(xy[0]),Integer.parseInt(xy[1]));
    }
    int m = Integer.parseInt(read.readLine.trim());
    for(i=0; i<m; i++){
      String line = read.readLine().trim();
      String ends = line.split(" ");
      int u = Integer.parseInt(ends[0]);
      int v = Integer.parseInt(ends[1]);
      double w = (this.r.list[v].x - this.r.list[u].x) * (this.r.list[v].x - this.r.list[u].x);
      w = w + (this.r.list[v].y - this.r.list[u].y) * (this.r.list[v].y - this.r.list[u].y);
      w = sqrt(w);
      Edge e = new Edge(u, v, w);
      this.r.list[v].inc.add(e);
      this.r.list[u].inc.add(e);
    }
  }

  public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		if(args.length < 1){
			System.err.println("Uso: java Cliente <nombreArchivo>");
			return;
		}
		loadGraph(args);
	}
}
