import java.util.Arrays;

/**
 * Classe che permette di modellizzare una matrice delle adiacenze che tiene conto dei collegamenti tra
 * i vari nodi e i rispettivi pesi.
 * @author Qwertyteam
 * @version 1.4
 * @since 1.0
 */

public class MatriceAdiacenze implements Cloneable {
	private double[][] adjacencyMatrix;
	private boolean orientedGraph;
	
	/**
	 * Costruttore della classe.
	 * @param totalNodes Numero totale dei nodi che compongono la matrice.
	 * @param orientedGraph Se il grafo è orientato o meno.
	 */
	
	public MatriceAdiacenze(int totalNodes, boolean orientedGraph){
		adjacencyMatrix = new double[totalNodes][totalNodes];
		this.orientedGraph = orientedGraph;
		prepareMatrix(totalNodes);
	}
	
	/**
	 * Metodo che permette di settare le diagonali a zero, essendo che la distanza tra un nodo e 
	 * sè stesso è per definizione zero.
	 * @param totalNodes Numero totale dei nodi che compongono la matrice.
	 */
	
	public void prepareMatrix(int totalNodes){
		for(int i = 0; i < adjacencyMatrix.length; i++){
			adjacencyMatrix[i][i] = 0;
		}
	}
	
	/**
	 * Metodo che permette di verificare se due nodi sono collegati o meno.
	 * @param startNode Nodo di partenza
	 * @param endNode Nodo di arrivo
	 * @return ID del nodo 
	 */
	
	public int areNodesLinked(int startNode, int endNode){
		for(int i = 0; i < adjacencyMatrix.length; i++){
			if(adjacencyMatrix[endNode][i]!=0) 
				return i;
		}
		return -1;
	}
	/**
	 * Metodo che permette di collegare due nodi. Se il grafo non è orientato
	 * il collegamento è simmetrico, altrimenti il collegamento viene effettuato
	 * in direzione del nodo di destinazione.
	 * @param startNode Nodo di partenza
	 * @param endNode Nodo di destinazione
	 * @param weight Peso del collegamento tra i due nodi.
	 */
	
	public void linkNode(int startNode, int endNode, int weight){
		if(!orientedGraph){
			adjacencyMatrix[startNode][endNode] = weight;
			adjacencyMatrix[endNode][startNode] = weight;
		}else
			adjacencyMatrix[startNode][endNode] = weight;
	}
	
	/**
	 * Metodo che, dato l'ID di un nodo, permette di restituire l'ID dei nodi collegati ad esso.
	 * @param idNode ID del nodo iniziale.
	 * @return Lista degli ID dei nodi collegati al nodo iniziale.
	 */
	
	public int [] getIndexesOfLinkedNodes(int idNode){
		int indexArray = 0;
		int [] indexesOfLinkedNodes = new int[0];
		for(int j = 0; j < adjacencyMatrix.length; j++){
			if(adjacencyMatrix[idNode][j] != 0){ //se il nodo è collegato
				indexArray++;
				indexesOfLinkedNodes = Arrays.copyOf(indexesOfLinkedNodes, indexArray); //rimodellizza dimensione array
				indexesOfLinkedNodes[indexArray-1] = j; //memorizza idNodo (che coincide con l'index "j")
			}	
		}
		return indexesOfLinkedNodes;
	}
	
	/**
	 * Metodo che permette di stabilire se il nodo è collegato al grafo
	 * oppure se è irraggiungibile.
	 * @param idNode ID del nodo da analizzare.
	 * @return Riscontro dell'analisi.
	 */
	
	public boolean isLinkedToGraph(int idNode){
		for(int i = 0; i < adjacencyMatrix.length; i++){
			if(adjacencyMatrix[idNode][i] != 0)
				return true;
			
			
		}
		return false;
	}
	
	/**
	 * Metodo che permette di restituire la matrice di adiacenza.
	 * @return Matrice di adiacenza.
	 */
	
	public double [][] getAdjacencyMatrix(){
		return adjacencyMatrix;
	}
	
	/**
	 * Metodo che permette di mostrare su interfaccia a linea di comando 
	 * la composizione della matrice di adiacenza.
	 */
	
	public void printAdjacencyMatrix(){
		for(int i = 0; i < adjacencyMatrix.length; i++){
			for(int j = 0; j < adjacencyMatrix.length; j++)
				System.out.print("\t" + adjacencyMatrix[i][j]);
			System.out.println();
		}
	}
	
	/**
	 * Metodo che permette di clonare la matrice delle adiacenze in modo da 
	 * non modificare quella originale.
	 */
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		final MatriceAdiacenze clone;
        try {
            clone = (MatriceAdiacenze) super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException("superclass messed up", ex);
        }
        clone.adjacencyMatrix = this.adjacencyMatrix.clone();
        return clone;
	}
}
