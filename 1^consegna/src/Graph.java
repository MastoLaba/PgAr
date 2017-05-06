import java.util.Iterator;
import java.util.Vector;

//import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * A program to detect if a given undirected, unweighted graph contains loops
 * http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 */

public class Graph {
	public final static int MAX_VERT = 99999;
    private int n_vertices;
    private Vector<Integer> adjacency[]; // Adjacency Matrix
    private Vector<Node> tree;
    private Vector<Node> dijkstra;
    private Vector<Integer> weight_dijkstra;
    private boolean[] passati;
    
    // Constructor of the class Graph
	public Graph(Vector<Node> _tree) {
        this.n_vertices = _tree.size();
        this.adjacency = new Vector[n_vertices];
        this.dijkstra = new Vector<Node>();
        this.weight_dijkstra = new Vector<Integer>();
        this.passati = new boolean[n_vertices];
        for(int i=0;i<n_vertices;i++){
        	passati[i]=false;
        }
        
        
        tree = _tree;
        
        //Initialization of adjacency matrix
        for (int i = 0; i < n_vertices; ++i) {
            adjacency[i] = new Vector<Integer>();
        }
        for(int i=0;i<n_vertices;i++){
        		for(int j=0;j<n_vertices;j++){
        			adjacency[i].add(0);
        		}
        }
        
        for(int i=0;i<n_vertices;i++){
        	if(tree.get(i).getWeight_nodes().size() > 0){
        		for(int j=0;j<tree.get(i).getWeight_nodes().size();j++){
        			addEdge(Integer.parseInt(tree.get(i).getID()), Integer.parseInt(tree.get(i).getEdge().get(j).getID()), tree.get(i).getWeight_nodes().get(j));
        			//System.out.println("ind1 "+Integer.parseInt(tree.get(i).getID())+"\n ind2 " +Integer.parseInt(tree.get(i).getEdge().get(j).getID())+"\n Value "+ tree.get(i).getWeight_nodes().get(j));
        		}
        	}
        }
    }
    public Vector<Node> getDijkstra(){
    	return dijkstra;
    }
    // Function to add an edge into the graph
    public void addEdge(int ind1, int ind2, int weight) {
        adjacency[ind1].set(ind2, weight);
        adjacency[ind2].set(ind1, weight);
    }
    public void dijkstra(){	
    	int tmp = findStart();
    	for(int i=0;i<n_vertices;i++){
    		tmp = findNext(tmp);
    	}
    	
    	for(int i=dijkstra.size()-1;i>=0;i--){
    		if(i!=0){
	    			int ind2 = i-1;
	    			//Clear vettore collegamento e peso di dijkstra[i]
	    			dijkstra.get(i).reset_linked();	
		    		dijkstra.get(i).addLinked(dijkstra.get(ind2), weight_dijkstra.get(i));
    		}
    		else{
    			int ind3 = i+1;
    			dijkstra.get(i).reset_linked();
    		}
    		
    	}
    	/*for(int i=0;i<dijkstra.size();i++){
    		for(int j=0;j<dijkstra.get(i).getEdge().size();j++)
    			System.out.println(dijkstra.get(i).getEdge().get(j).toString());
    	}
    	System.out.println("Risultato Dijkstra");
    	for(int i=0;i<n_vertices;i++){
    		System.out.println(dijkstra.get(i).getLabel() + " " +weight_dijkstra.get(i));
    	}
    	System.out.println("fine risultato");*/
    }
    public int findNext(int indStart){
    	//TODO salvare il risultato in modo da poterlo scrivere su file xml
    	int min = maxAdjacency();
    	int indMin = MAX_VERT;
    	for(int i=0;i<n_vertices;i++){
    		if(adjacency[indStart].get(i)>0){
    			if(passati[i]==false){
	    			if(adjacency[indStart].get(i)<=min){
	    				min =adjacency[indStart].get(i);
	    				indMin = i;
	    			}else{
	    				
	    			}
    			}else{
    				
    			}
    		}else{
    			
    		}
    	}
    	if(indMin!=MAX_VERT){
	    	passati[indStart] = true;
	    	Node tmp =findNodeByID(""+indMin);
	    	dijkstra.add(tmp);
	    	weight_dijkstra.addElement(min);
    	}
    	return indMin;
    	
    }
    
    public Node findNodeByID(String id){
    	for(int i=0;i<tree.size();i++){
    		if(tree.get(i).getID().equals(id)){
    			return tree.get(i);
    		}
    	}
    	return null;
    }
    public int maxAdjacency(){
    	int max=0;
    	for(int i=0;i<n_vertices;i++)
    		for(int j=0;j<n_vertices;j++)
    			if(adjacency[i].get(j)>max)
    				max=adjacency[i].get(j);
    	return max;
    }
    public int findStart(){
    	for(int i=0;i<tree.size();i++){
    		if(tree.get(i).isStart()){
    			passati[i] = false;
    			dijkstra.add(tree.get(i));
    			weight_dijkstra.addElement(0);
    			return Integer.parseInt(tree.get(i).getID());
    	
    		}
    	}
    	//return (Integer) null;
    	return 0;
    }
    public int findEnd(){
    	for(int i=0;i<tree.size();i++){
    		if(tree.get(i).isEnd()){
    			//dijkstra.add(tree.get(i));
    			//weight_dijkstra.addElement(0);
    			return Integer.parseInt(tree.get(i).getID());
    	
    		}
    	}
    	//return (Integer) null;
    	return 0;
    }
    // A recursive function that uses visited[] and parent to detect
    // cycle in subgraph reachable from vertex v
    public boolean isCyclicUtil(int current_vertex, boolean visited[], int parent_vertex) {
        // Mark the current node as visited
        visited[current_vertex] = true;
        int adj_vertex;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adjacency[current_vertex].iterator();
        while (it.hasNext()) {
            adj_vertex = it.next();

            // If an adjacent is not visited, then recur for that adjacent
            if (!visited[adj_vertex])
                if (isCyclicUtil(adj_vertex, visited, current_vertex))
                    return true;

            // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle. This control excludes the case in which
            // we visit the parent of the node we are analyzing.
            if (adj_vertex != parent_vertex)
                return true;
        }
        return false;
    }

    // Returns true if the graph contains a cycle, else false.
    boolean isCyclic() {
        // Mark all the vertices as not visited and not part of
        // recursion stack
        boolean visited[] = new boolean[n_vertices];
        for (int i = 0; i < n_vertices; i++)
            visited[i] = false;

        // Call the recursive helper function to detect cycle in
        // different DFS trees
        for (int u = 0; u < n_vertices; u++)
            if (!visited[u]) // Don't recur for u if already visited
                if (isCyclicUtil(u, visited, -1))
                    return true;
        return false;
    }

   
}
