import java.util.Vector;

public class Archi {
	private Vector<Node> tree;
	private Vector<Integer> weight_kruskal;
	private Vector<String> archi;//archi
	BubblesSort bubble= new BubblesSort();
	
	public Archi(Vector<Node> _tree){
		this.weight_kruskal = new Vector<Integer>();
		this.archi=new Vector<String>();
		this.tree= _tree;

		
	}
	
	
	public void eliminaCoppie(){//elimina coppie archi
		for( int i=0;i<archi.size();i++){		
			for(int j=1;j<archi.size();j++){
				if (archi.get(i).equals(archi.get(j))){
					archi.remove(j);
					weight_kruskal.remove(j);
				}
			}
		}
	}





	public void creaLabel(Vector<Node> tree){
		for(int i=0;i<tree.size();i++){
			String tmp=tree.get(i).getLabel();
			for(int j=0;j<tree.get(i).getEdge().size();j++){
				archi.add(tmp+tree.get(i).getEdge().get(j).getLabel());
				weight_kruskal.add(tree.get(i).getWeight_nodes().get(j));				
			}

			eliminaCoppie();
			bubble.bubbleSort(archi, weight_kruskal);

			for( i=0;i<archi.size();i++){
				archi.get(i);
				weight_kruskal.get(i);
				
			}

		}

	}
	
}


