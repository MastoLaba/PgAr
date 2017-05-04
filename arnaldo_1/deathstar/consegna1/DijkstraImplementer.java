package deathstar.consegna1;

import java.util.LinkedList;
import java.util.HashMap;

/** Classe statica contenente l'implementazione dell'algoritmo di Dijkstra
 * 
 * @author Lorenzo Nodari
 * 
 */
public class DijkstraImplementer {
	
	private static final int NOT_FOUND = -1;
	private static final float LOAD_FACTOR = 1.0F; //Check HashMap Constructors documentation
	private static final int NON_DEFINITA = Integer.MAX_VALUE; //Mi serve per segnalare una distanza infinita

	//CARE: Non ancora gestito il caso in cui il nodo finale sia irraggiungibile
	
	/** Applica l'algoritmo di Dijkstra al grafo passato come parametro.
	 *  NB: Il grafo passato come parametro non viene modificato in quanto
	 *  il metodo agisce su una copia esatta di tale grafo.
	 * 
	 * @param grafo Il grafo su cui applicare l'algoritmo
	 * @return Il grafo contenente il cammino minimo o null in caso qualcosa vada storto
	 */
	public static Graph applyDijkstra(Graph grafoInput) {
		System.out.println("---------------- INIZIO DIJKSTRA ---------------");
		//Clono il grafo dato in input
		Graph grafo = grafoInput.clone();
		//Cerco il nodo iniziale e quello finale
		//Inizializzo a meno uno cosi da poter verificare di averli trovati
		int startNodeId = NOT_FOUND;
		int endNodeId = NOT_FOUND;
		boolean startIdFound = false;
		boolean endIdFound = false;
		Node nodoIniziale = null;
		Node nodoFinale = null;
		
		
		for (Node n : grafo.getNodes()) {
			if (n.isStart() && !startIdFound) {
				startNodeId = n.getId();
				startIdFound = true;
			}
			else if (n.isStart() && startIdFound){
				System.out.println("ERRORE: Sono presenti piu' nodi marcati come iniziali");
			}
			
			if (n.isEnd() && !endIdFound) {
				endNodeId = n.getId();
				endIdFound = true;
			}
			else if (n.isEnd() && endIdFound){
				System.out.println("ERRORE: Sono presenti piu' nodi marcati come finali");
			}
		}
		
		//Se non li trovo entrambi termino l'esecuzione in quanto il grafo e' mal formato
		if (!startIdFound || !endIdFound) {
			System.out.println("Attenzione: il grado non ha un nodo iniziale e/o un nodo finale!");
			return null;
		}
		
		//Creo le varie strutture dati necessarie all'algoritmo
		
		//Il grafo nel quale costruiro' il cammino minimo
		Graph grafoOutput = new Graph();
		nodoIniziale = grafo.getNodeById(startNodeId);
		nodoFinale = grafo.getNodeById(endNodeId);
		
		//Nodi gia' analizzati
		LinkedList<Node> nodiAnalizzati = new LinkedList<Node>();
		nodiAnalizzati.add(nodoIniziale);
		
		//Nodi ancora da analizzare
		//I.E. Tutti tranne il nodo di partenza
		LinkedList<Node> nodiDaAnalizzare = new LinkedList<Node>();
		for (Node n : grafo.getNodes()) {
			if (n.getId() != startNodeId) {
				nodiDaAnalizzare.add(n);
			}
		}
		
		//Etichette associate ai vari nodi
		//Le salvo come coppie ID - Valore etichetta
		
		//DISTANZA CUMULATIVA DI UN NODO DA QUELLO INIZIALE
		//In questo caso mappo Nodo - distanza di tale nodo dal nodo iniziale
		//Inizializzo le etichette: 0 per il nodo iniziale
		HashMap<Node, Integer> distanzaNodoIniziale = new HashMap<>(grafo.getNumNodes(), LOAD_FACTOR);
		distanzaNodoIniziale.put(nodoIniziale, 0);
		
		//NODO PRECEDENTE NEL CAMMINO MINIMO
		//In questo caso mappo id nodo - id nodo precedente nel cammino minimo
		HashMap<Node, Node> nodoPrecedente = new HashMap<>();
		nodoPrecedente.put(nodoIniziale, null);
		
		//Per i restanti nodi:
		for (int i = 1; i < grafo.getNumNodes(); i++) {
			Node tmpNode = nodiDaAnalizzare.get(i - 1);
			int otherNodeId = tmpNode.getId();
			//Per i nodi adiacenti al primo inizializzo al peso del collegamento la prima etichetta
			//e all'id del nodo iniziale la seconda
			if (nodoIniziale.hasArc(otherNodeId)) {
				distanzaNodoIniziale.put(tmpNode, nodoIniziale.getWeightArc(otherNodeId));
				nodoPrecedente.put(tmpNode, nodoIniziale);
			}
			//Per gli altri segno la prima etichetta come non definita
			else {
				distanzaNodoIniziale.put(tmpNode, NON_DEFINITA);
			}
			
		}
		
		
		//Fine fase di preparazione per l'algoritmo, inizio l'effettiva computazione
		System.out.println("Inizio computazione...");
		while (!nodiDaAnalizzare.isEmpty()) {
			//Trovo il nodo tra i rimanenti da analizzare con distanza cumulativa minima dall'origine
			Node tmpNode = nodoMinimaDistanzaOrigine(nodiDaAnalizzare, distanzaNodoIniziale);
			if (tmpNode == null || tmpNode == nodoFinale) {
				System.out.println("! ESCO DAL CICLO PRINCIPALE !");
				break;
			}
			nodiDaAnalizzare.remove(tmpNode);
			nodiAnalizzati.add(tmpNode);
			
			//Fase etichette provvisorie
			//Per ogni nodo tra quelli ancora da analizzare adiacenti a tmpNode
			for (Node n : tmpNode.getLinked()) {
				if (nodiDaAnalizzare.contains(n)) {
					//Se la distanza di tale nodo dall' orgine e' maggiore della somma
					//tra la distanza di tmpNode dall'origine e la distanza tra i due
					//sistemo le etichette
					//Valore soglia: f(j) + p(i,j)
					int valoreSoglia = distanzaNodoIniziale.get(tmpNode) + tmpNode.getWeightArc(n.getId());
					if (distanzaNodoIniziale.get(n) > valoreSoglia) {
						distanzaNodoIniziale.replace(n, valoreSoglia);
						nodoPrecedente.put(n, tmpNode);
					}
				}
			}
		}
		
		//Terminata la computazione, ora ricostruisco il percorso minimo e ne creo il grafo corrispondente
		System.out.println();
		System.out.println("Inizio ricostruzione percorso");
		grafoOutput.addNode(nodoFinale);
		Node tmpNode = nodoFinale;
		//Arc tmpArc ... Aggiungere
		do {
			tmpNode = nodoPrecedente.get(tmpNode);
			System.out.println("Nodo: " + tmpNode.getLabel());
			grafoOutput.addNode(tmpNode);
			System.out.println("Ricostruisco...");
		} while (tmpNode != nodoIniziale);
		grafoOutput.addNode(nodoIniziale);
		
		System.out.println();
		System.out.println("Elimino collegamenti non necessari tra i nodi del grafo risultante...");
		for (Node n : grafoOutput.getNodes()) {
			for (Node linked : n.getLinked()) {
				if (!grafoOutput.containsNode(linked)) {
					n.removeArc(linked.getId());
				}
			}
		}
		return grafoOutput;
		
	}
	
	//Metodo di utilita', restituisce l'id del nodo tra quelli da analizzare
	//con minore distanza cumulativa dall'origine
	private static Node nodoMinimaDistanzaOrigine(LinkedList<Node> nodiDaAnalizzare, HashMap<Node, Integer> distanzaNodoIniziale) {
		System.out.println();
		System.out.println("X --- NODO MINIMA DISTANZA ORIGINE --- X");
		Node foundNode = null;
		int distanzaMinima = NON_DEFINITA;
		for (Node n : nodiDaAnalizzare) {
			System.out.println("-Nodo " + n.getLabel());
			int tmpDistanza = distanzaNodoIniziale.get(n);
			System.out.println("  tmpDistanza = " + tmpDistanza);
			if (tmpDistanza < distanzaMinima) {
				System.out.println("    E' minore! Sostituisco");
				distanzaMinima = tmpDistanza;
				foundNode = n;
			}
		}
		return foundNode;
	}
}
