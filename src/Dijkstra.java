import java.util.Arrays;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

/**
 * Classe che fornisce i metodi per applicare ad un dato grafo l'algoritmo di Dijkstra.
 * @author Qwertyteam
 * @version 1.4
 * @since 1.0
 */

public class Dijkstra {
	private Graph graph;
	private Double [] nodeDistances; 
	private Vector<Nodo> orderVisitedNodes;
	private int [] previousSteps;
	private String fileName;
	private static final String ALGORITHM_INITIAL_STATUS = "Inizio esecuzione dell'Algoritmo di Dijkstra...";
	private static final String ALGORITHM_FINAL_STATUS = "Esecuzione dell'Algoritmo terminata.";
	private static final String ALGORITHM_ERROR = "Problema. Esecuzione dell'algoritmo interrotta.";
	
	/**
	 * Costruttore della classe.
	 * @param graph Grafo contenente i nodi del sistema.
	 * @param matrix Matrice delle adiacenze che fornisce i collegamenti fra i nodi e i rispettivi pesi.
	 */
	
	public Dijkstra(Graph graph, String fileName) throws CloneNotSupportedException{
		this.graph = (Graph)graph.clone();
		this.fileName = fileName;
		orderVisitedNodes = new Vector<>();
		previousSteps = new int[graph.getNodes().length];
		nodeDistances = new Double[graph.getNodes().length];
	}
	
	/**
	 * Metodo che permette di eseguire l'algoritmo di Dijkstra sul grafo.
	 * @return Variabile booleana che permette di mostrare se l'algoritmo
	 * è stato eseguito correttamente o se ci sono stati problemi.
	 */
	
	public boolean executeAlgorithm() throws XMLStreamException{
		
		/*
		 * Fase iniziale: assegnamento. Tutte le distanze potenziali tra i nodi vengono settate 
		 * ad infinito. Inoltre l'array che tiene traccia dei nodi precedenti già visitati è settato
		 * a null dalla JVM.
		 */
		
		if(graph.checkIntegrity(graph)){
			System.out.println(ALGORITHM_INITIAL_STATUS);
			for(Nodo node : graph.getNodes()){
				nodeDistances[node.getIDNode()] = Double.POSITIVE_INFINITY;
			}
			
			nodeDistances[0] = 0.0;
			previousSteps[0] = 0;
			
			/*
			 * Fase intermedia: ciclo principale. Il ciclo non si interrompe finchè c'è un blocco nel
			 * grafo causato da nodi irraggiungibili oppure se è stato raggiunto il nodo di destinazione.
			 * 
			 */
			
			while(true){
				int indexNode = getIndexOfMinimumNodeDistance(); //imposta l'indice del nodo che ha distanza minore
				orderVisitedNodes.addElement(graph.getNodes()[indexNode]); //aggiungi il nodo alla lista dei nodi visitati
				if(graph.getNodes()[indexNode].isDestinationNode()){ //se si tratta del nodo di destinazione
					printOrderVisitedNodes();
					printShortestPath(getShortestPath(indexNode));
					break;
				}
				
				graph.getNodes()[indexNode] = null; //libera la memoria
				
				/*
				 * Fase importante: ciclo interno. Viene eseguito per ogni nodo adiacente a quello che ha
				 * distanza minore. La variabile alt tiene in memoria il costo per raggiungere il nodo adiacente
				 * a partire dal nodo iniziale. Se il costo contenuto nella variabile è minore del costo 
				 * che il nodo aveva precedentemente esso viene aggiornato. Se la condizione è vera viene 
				 * registrato il passo per arrivare al nodo adiacente (ossia si tiene conto del nodo che ha 
				 * permesso di raggiungere quello adiacente con il meno costo possibile).
				 */
				
				int [] idLinkedNodes = graph.getAdjacencyMatrix().getIndexesOfLinkedNodes(indexNode);
				for(int indexLinkedNode : getIDNodesNotVisited(idLinkedNodes) ){
					double temporaryWeight = nodeDistances[indexNode] + graph.getAdjacencyMatrix().getAdjacencyMatrix()[indexNode][indexLinkedNode];
					if(temporaryWeight < nodeDistances[indexLinkedNode]){
						nodeDistances[indexLinkedNode] = temporaryWeight;
						previousSteps[indexLinkedNode] = indexNode;
					}
				}
				nodeDistances[indexNode] = null; //libera la memoria
			}
		}else{
			System.out.println(ALGORITHM_ERROR);
			return false;
		}
		System.out.println(ALGORITHM_FINAL_STATUS);
		return true;
	}
	
	/**
	 * Metodo che permette di scegliere il nodo che ha distanza minore tra quelli non ancora visitati
	 * dall'Algoritmo di Dijkstra.
	 * @return Indice del nodo scelto.
	 */
	
	public int getIndexOfMinimumNodeDistance(){
		int indexNodeWithMinimumValue = -1;
		double minimumValue = 0;
		boolean isMinimumValueAssigned = false;
		for(int index = 0; index < nodeDistances.length; index++){
			if(nodeDistances[index]!= null && isMinimumValueAssigned == false){ //se si tratta del primo elemento non null della lista
				indexNodeWithMinimumValue = index; //registralo come possibile valore minimo dell'array
				minimumValue = nodeDistances[index];
				isMinimumValueAssigned = true;
				continue;
			}
			
			/*Se ci sono altri elementi non null nell'array questa condizione permette di stabilire
			 * l'elemento con il minor valore dell'array.
			 */
			
			if(nodeDistances[index]!= null && minimumValue > nodeDistances[index]){ 
				indexNodeWithMinimumValue = index;
				minimumValue = nodeDistances[index];
			}
		}
		return indexNodeWithMinimumValue;
	}
	
	/**
	 * Metodo che permette la stampa a schermo di tutti i nodi del grafo 
	 * visitati dall'Algoritmo di Dijkstra.
	 */
	
	private void printOrderVisitedNodes(){
		System.out.print("L'ordine dei nodi visitati è: ");
		StringBuilder sb = new StringBuilder();
		for(Nodo node : orderVisitedNodes){
			sb.append(Character.toString(node.getLabel()));
			sb.append("-");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * Metodo che permette la stampa a schermo dell'albero dei cammini minimi del grafo.
	 * @param shortestPath L'albero dei cammini minimi.
	 */
	
	private void printShortestPath(int [] shortestPath) throws XMLStreamException{
		Vector<Nodo> shortest = new Vector<Nodo>();
		for(int idNode: shortestPath){
			for(Nodo nodo: orderVisitedNodes){
				if(nodo.getIDNode() == idNode){
					shortest.add(nodo);
				}
			}
		}
		
		XMLWriter.executeWrite(fileName, shortest, graph.getAdjacencyMatrix());
		
	}
	
	/**
	 * Metodo che permette di creare l'albero dei cammini minimi.
	 * @param idNode Nodo di destinazione del grafo analizzato.
	 * @return Array contenente l'ID dei nodi scelti per formare l'albero
	 * dei cammini minimi.
	 */
	
	public int [] getShortestPath(int idNode){
		Nodo [] visitedNodes = new Nodo[orderVisitedNodes.size()];
		int [] idNodesShortestPath = new int[orderVisitedNodes.size()];
		int [] idNodesShortestPathReverse = new int[orderVisitedNodes.size()];
		int indexArray = 1;
		orderVisitedNodes.toArray(visitedNodes);
		idNodesShortestPath[0] = idNode;
		boolean isSource = false;
		for(int index = idNode; index != 0 && !isSource; indexArray++){
			index = previousSteps[index];
			if(index == 0)
				isSource = true;
			idNodesShortestPath[indexArray] = index;
		}
		idNodesShortestPath = Arrays.copyOf(idNodesShortestPath, indexArray);
		System.arraycopy(idNodesShortestPath, 0, idNodesShortestPath, 0, indexArray);
		for(int i = idNodesShortestPath.length-1, j = 0; i >= 0; i--, j++){
			idNodesShortestPathReverse[j] = idNodesShortestPath[i];
		}
		idNodesShortestPathReverse = Arrays.copyOf(idNodesShortestPathReverse, indexArray);
		return idNodesShortestPathReverse;
	}
	
	/**
	 * Ricerca nell'Array l'ID dei nodi non ancora stati visitati
	 * dall'Algoritmo di Dijkstra.
	 * @param idNodes ID dei nodi da analizzare.
	 * @return Array contenente l'ID dei nodi non ancora visitati.
	 */
	
	public int [] getIDNodesNotVisited(int [] idNodes){
		int [] idNodesNotVisited = new int [idNodes.length];
		int indexArray = 0;
		boolean isVisited = false;
		
		for(int i = 0; i < idNodes.length; i++){
			for(int j = 0; j < orderVisitedNodes.size() && !isVisited ; j++){
				if(orderVisitedNodes.get(j).getIDNode() == idNodes[i]){
					isVisited = true;
				}
			}
			if(!isVisited){
				idNodesNotVisited[indexArray] = idNodes[i];
				indexArray++;
			}else{
				isVisited = false;
			}
		}
		
		return Arrays.copyOf(idNodesNotVisited, indexArray);
	}
}
