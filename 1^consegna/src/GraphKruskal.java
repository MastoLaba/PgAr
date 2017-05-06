import java.util.Vector;

public class GraphKruskal {
	
	private Vector<Node> tree;
	private Vector<String> kruskal;
	Archi archiCreati=new Archi(tree);
	private Vector<String> checkCycle;
	private Vector<String> edges;
	private boolean[] checked;
	private int n_vertices;


	public GraphKruskal(Vector<Node> _tree) {
		 this.n_vertices = _tree.size();
		 this.checked = new boolean[n_vertices];
		this.edges = new Vector<String>();
		this.checkCycle = new Vector<String>();
		this.tree= _tree;
		this.kruskal = new Vector<String>();
		archiCreati.creaLabel(tree);
		
	}
	public void kruskal(Vector<String> archi,Vector<Integer> weight_kruskal){
		
		for(int i=0;i<archi.size();i++){
			checkCycle.add(archi.get(i));
			isCyclic(archi,checkCycle);
			if(isCyclic(archi,checkCycle)==false){
			kruskal.add(archi.get(i)+weight_kruskal.get(i));
			}
			else
				break;
		}
	}

	public void EdgesReader(Vector<Node> tree){
		for(int i=0;i<tree.size();i++){
		edges.add(tree.get(i).getLabel());
		checked[i]=false;
		}
		
		
	}

	public boolean isCyclic(Vector<String> archi,Vector<String> checkCycle){
		boolean visited=false;
		for(int j=0;j<checkCycle.size();j++){
			if(checked[j]==false){
			checkCycle.get(j);
			if(j>=1 && checkCycle.get(j).substring(0, 0).equals(checkCycle.get(j-1).substring(0,0))||
			checkCycle.get(j).substring(0, 0).equals(checkCycle.get(j-1).substring(1,1)))		
				visited=true;
				checked[j]=true;
			}
				
			else
				break;
		}
		
		return visited;
}




}




