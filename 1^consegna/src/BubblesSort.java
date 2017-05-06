import java.util.Vector;

public class BubblesSort {


	public void bubbleSort(Vector<String> archi,Vector<Integer> weight_kruskal){

		for(int i = 0; i < weight_kruskal.size(); i++) {
			boolean flag = false;
			for(int j = 0; j < weight_kruskal.size()-1; j++){
				if(weight_kruskal.get(j)>weight_kruskal.get(j+1)) {
					int tmpWeight = weight_kruskal.get(j);
					weight_kruskal.setElementAt(j+1, j); 
					weight_kruskal.setElementAt(tmpWeight, j+1);

					for(int k = 0; k < archi.size()-1; k++){
						String tmpArco = archi.get(k);
						archi.setElementAt(archi.get(k), k);   
						archi.setElementAt(tmpArco, k+1);

						flag=true; 

					}
				}
				if(!flag) break;
			}
		}
	}
}


