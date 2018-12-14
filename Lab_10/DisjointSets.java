import java.util.Arrays;

public class DisjointSets{
  private int[] sets;
  private int[] range;

  public DisjointSets(int n){
    this.sets = new int[n];
    for(int i=0; i<n; i++)
      this.sets[i] = i;
    this.range = new int[n];
    Arrays.fill(this.range, 0);
  }

  public int find(int a){
    int y = a;
    while(a!=this.sets[a]){
      y = find(this.sets[a]);
      this.sets[a] = y;
    }
    return y;
  }

  public void join(int v, int w){
    if(range[v]>range[w]){
      sets[w]=v;
    }else{
      sets[v] = w;
      if(range[v] == range[w])
        range[w] +=1;
    }
  }

}
