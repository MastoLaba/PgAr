import java.io.FileNotFoundException;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

/**
 * Classe di Test dell'Algoritmo di Dijsktra e di Kruskal.
 * @author Qwertyteam
 * @version 1.4
 * @since 1.0
 */

public class Test {
	public static final String CONTAINS_CYCLES = "Il grafo contiene cicli!";
	public static final String NOT_CONTAINS_CYCLES = "Il grafo non contiene cicli!";
	public static final String KRUSKAL_INIT = "Inizio esecuzione dell'Algoritmo di Kruskal...";
	public static final String KRUSKAL_END = "Esecuzione dell'Algoritmo terminata.";
	
	public static void main(String[] args) throws CloneNotSupportedException, XMLStreamException, FileNotFoundException{
		//(new XMLReader()).XMLRead(); //ipotetica lettura XML (funzionante.... ma non troppo! Lettura funziona ma manca l'integrazione con il programma)
		System.out.println();
		
		/**
		 * Test dell'Algoritmo di Dijkstra
		 */
		
		Nodo nodoA = new Nodo('A');
		nodoA.setAsSourceNode();
		Nodo nodoB = new Nodo(1, 'B');
		Nodo nodoC = new Nodo(4, 'C');
		Nodo nodoD = new Nodo(2, 'D');
		Nodo nodoE = new Nodo(5, 'E');
		Nodo nodoF = new Nodo(3, 'F');
		Nodo nodoG = new Nodo(6, 'G');
		nodoG.setAsDestinationNode();
	
		Vector<Nodo> nodes = new Vector<Nodo>(7);
		nodes.add(nodoA);
		nodes.add(nodoB);
		nodes.add(nodoF);
		nodes.add(nodoC);
		nodes.add(nodoE);
		nodes.add(nodoD);
		nodes.add(nodoG);

		MatriceAdiacenze matrix = new MatriceAdiacenze(nodes.size(), false);
		matrix.linkNode(nodoA.getIDNode(), nodoB.getIDNode(), 2);
		matrix.linkNode(nodoA.getIDNode(), nodoC.getIDNode(), 8);
		matrix.linkNode(nodoD.getIDNode(), nodoC.getIDNode(), 2);
		matrix.linkNode(nodoB.getIDNode(), nodoE.getIDNode(), 5);
		matrix.linkNode(nodoB.getIDNode(), nodoD.getIDNode(), 2);
		matrix.linkNode(nodoE.getIDNode(), nodoG.getIDNode(), 8);
		matrix.linkNode(nodoC.getIDNode(), nodoF.getIDNode(), 5);
		matrix.linkNode(nodoD.getIDNode(), nodoF.getIDNode(), 7);
		matrix.linkNode(nodoF.getIDNode(), nodoG.getIDNode(), 2);
		
		Graph graph = new Graph(nodes, matrix);
		
		Dijkstra dijkstra = new Dijkstra(graph, "dijkstra.xml");
		dijkstra.executeAlgorithm();
		
		/**
		 * Test dell'Algoritmo di Kruskal.
		 */
		
		MatriceAdiacenze matrix2 = new MatriceAdiacenze(8, true);
		Nodo nodoS = new Nodo(0, 'S');
		nodoS.setAsSourceNode();
		Nodo nodo1 = new Nodo(1, '1');
		Nodo nodo2 = new Nodo(2, '2');
		Nodo nodo3 = new Nodo(3, '3');
		Nodo nodo4 = new Nodo(4, '4');
		Nodo nodo5 = new Nodo(5, '5');
		Nodo nodo6 = new Nodo(6, '6');
		Nodo nodoT = new Nodo(7, 'T');
		nodoT.setAsDestinationNode();
		matrix2.linkNode(nodoS.getIDNode(), nodo1.getIDNode(), 12);
		matrix2.linkNode(nodoS.getIDNode(), nodo2.getIDNode(), 2);
		matrix2.linkNode(nodo1.getIDNode(), nodo4.getIDNode(), 4);
		matrix2.linkNode(nodo2.getIDNode(), nodo1.getIDNode(), 2);
		matrix2.linkNode(nodo2.getIDNode(), nodo4.getIDNode(), 3);
		matrix2.linkNode(nodo2.getIDNode(), nodo5.getIDNode(), 2);
		matrix2.linkNode(nodo2.getIDNode(), nodo3.getIDNode(), 12);
		matrix2.linkNode(nodo5.getIDNode(), nodo3.getIDNode(), 3);
		matrix2.linkNode(nodo5.getIDNode(), nodo6.getIDNode(), 1);
		matrix2.linkNode(nodo3.getIDNode(), nodo6.getIDNode(), 9);
		matrix2.linkNode(nodo6.getIDNode(), nodoT.getIDNode(), 10);
		matrix2.linkNode(nodo4.getIDNode(), nodoT.getIDNode(), 4);
		
		Vector<Nodo> nodi = new Vector<Nodo>(8);
		nodi.add(nodoS);
		nodi.add(nodo1);
		nodi.add(nodo2);
		nodi.add(nodo3);
		nodi.add(nodo4);
		nodi.add(nodo5);
		nodi.add(nodo6);
		nodi.add(nodoT);
		Graph grafo = new Graph(nodi, matrix2);
		
		System.out.println();
		System.out.println(KRUSKAL_INIT);
		if(grafo.isCyclic()) 
			System.out.println(CONTAINS_CYCLES);
		else 
			System.out.println(NOT_CONTAINS_CYCLES);
		System.out.println(KRUSKAL_END);
		
		/**
		 * Applicazione dell'algoritmo di Dijkstra al grafo di Kruskal.
		 */
		
		Dijkstra dijkstra2 = new Dijkstra(grafo, "kruskal.xml");
		dijkstra2.executeAlgorithm();
		
	}
}
