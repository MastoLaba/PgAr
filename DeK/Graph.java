package dijkstra_kruskal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

import myStatic.InputClass;
import myStatic.MyUtil;

public class Graph{
	
	private String name;
	private LinkedList<Node> nodes = new LinkedList<Node>();
	private LinkedList<Edge> edges = new LinkedList<Edge>();
	
	private final static String NODE="<node";	
	private final static String EDGE = "<edge";
	private final static String EDGES = "<edges>";
	private final static String EDGES1 = "</edges";
	private final static String OPEN= "<";
	private final static String CLOSE= ">";
	private final static String QUOTE="\"";
	
	private final static String START ="start";
	private final static String END ="end";
	
	
	public Graph(){
		
	}
	
	/**
	 * inizializza il weightPath a zero.
	 * legge da tastiera l'indirizzo del file da leggere,
	 * successivamente aggiunge i vari nodi al LinkedList nodes
	 * e al LinkedList edges aggiunge i vari edge
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author mauro
	 * @author alessandro
	 */
	public Graph(String indirizzo) throws FileNotFoundException, IOException{
		//imposto il peso del percoso =0
		//String pathname = InputClass.readString(INSERIMENTO_FILE); //inserimento dell'indirizzo del file di testo

		Vector <String> vector = new Vector<String>();
		
		vector = InputClass.readStreamText(indirizzo); //nel vector inserisco le varie stringhe
		String nomeNodo;
		String riga;
		
		for (int i=0; i<vector.size(); i++){ // con questo ciclo analizzo tutte le stringhe
			riga = vector.get(i);
			
			if(MyUtil.contains(riga, NODE)){ //se trovo un nodo faccio questo
				
				i++;											//vado alla riga dopo 
				riga=vector.get(i);
				nomeNodo=MyUtil.subString(riga, CLOSE, OPEN);	//leggo il nome
				Node node = new Node(nomeNodo);					//creo e aggiungo il nodo alla LinkedList
				nodes.addLast(node);
				i--;											//torno indietro e controllo se il nodo è
				riga=vector.get(i);							//inizio o fine del percorso
				if (MyUtil.contains(riga, START)) nodes.getLast().start();
				if (MyUtil.contains(riga, END)) nodes.getLast().end();
			}
		
		}
		for (int i=0; i<vector.size(); i++){
			riga=vector.get(i);
			if(MyUtil.contains(riga, EDGES)){
				i--;											// torno indietro e cerco il primo nodo
				riga=vector.get(i);
				nomeNodo=MyUtil.subString(riga, CLOSE, OPEN);	//e lo memorizzo per poi creare l'oggetto edge
				int positionA = findNode(nomeNodo);
				Node nodeA = nodes.get(positionA);						
				
				i=i+2;											//vado avanti di due righe
				Node nodeB;
				int weight;
				riga=vector.get(i);							//nella variabile string salvo la riga
				while(MyUtil.contains(riga, EDGE)){
					
					riga=vector.get(i);						//cerco il mio secondo nodo
					if(MyUtil.contains(riga, EDGES1)) break;
					nomeNodo=MyUtil.subString(riga, CLOSE, OPEN);		
					int positionB = findNode(nomeNodo);
					nodeB=nodes.get(positionB);					//lo memorizzo
					
					riga=vector.get(i);						//trovo il peso di edge
					weight=weightEdge(riga);
					
					Edge edge = new Edge(nodeA, nodeB, weight);	//creo l'oggetto edge e lo aggiungo alla linked list
					setCollegamentiNode(edge);
					edges.add(edge);
					
					i++;
				}
				
			
			}//{ dello switch
		}//{ del for
		
	StaticGraph.reorderEdges(edges);	

	}//{ di graph

	
	public LinkedList<Node> getNodes() {
		return nodes;
	}


	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}


	public LinkedList<Edge> getEdges() {
		return edges;
	}


	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}

	
	/**
	 * cerca all'interno della LinkedList edges
	 * quel edge con i due nodi passati per parametro
	 * @param nodeA 
	 * @param nodeB
	 * @return ritorna il edge corrispondente
	 * @author mauro 
	 */
	public Edge findEdge(Node nodeA, Node nodeB){
		Edge ritorno=null;
		Edge scarto;
		for(int i=0; i<nodeA.getCollegamenti().size(); i++){
			scarto=nodeA.getCollegamenti().get(i);
			if(scarto.getNodeA().equals(nodeB) ||
					scarto.getNodeB().equals(nodeB)){
				ritorno=nodeA.getCollegamenti().get(i);
				break;			
			}
		}
		return ritorno;
	}
	
		
	/**
	 * dalla stringa data legge il peso di un edge
	 * @param string
	 * @return
	 * @author mauro
	 */
	public int weightEdge(String string){
		int ritorno;
		String stringa=MyUtil.subString(string, QUOTE, QUOTE);
		ritorno=Integer.parseInt(stringa);
		return ritorno;
	}
	
	/**
	 * trova il nodo con quel nome
	 * @param nomeNodo String 
	 * @return torna la posizione del nodo
	 */
	public int findNode(String nomeNodo){
		int ritorno=0;
		for(Node nodo: nodes){
			if(nodo.getNameNode().equals(nomeNodo))
				ritorno=nodes.lastIndexOf(nodo);
		}
		return ritorno;
	}
	
	/**
	 * aggiungere i collegamenti a tutti i nodi di edge
	 * @param edge l'arco
	 * @author mauro
	 */
	public void setCollegamentiNode(Edge edge){
		Node nodoA=edge.getNodeA();
		Node nodoB=edge.getNodeB();
		
		int posNodoA=findNode(nodoA.getNameNode());
		int posNodoB=findNode(nodoB.getNameNode());
		
		nodes.get(posNodoA).getCollegamenti().add(edge);
		nodes.get(posNodoB).getCollegamenti().add(edge);
		
		edge.setNodeA(nodes.get(posNodoA));
		edge.setNodeB(nodes.get(posNodoB));
	}
	
	/**
	 * stampa tutti i nodi e stampa tutti gli archi
	 * @author mauro
	 */
	public void printAll(){
		for(int i=0; i<nodes.size(); i++){
			if(nodes.get(i).isStart()) System.out.println(nodes.get(i).getNameNode() + " is start");
			else if (nodes.get(i).isEnd()) System.out.println(nodes.get(i).getNameNode() + " is end");
			else System.out.println(nodes.get(i).getNameNode());
		}
		System.out.println(" ");
		for(int i=0; i<edges.size(); i++){
			System.out.println(edges.get(i).getNameEdge() + " " + edges.get(i).getWeight());
		}
		
	}
	
	
	
	/**
	 * algoritmo di kruskal
	 * @return linkedlist di edge che sono i vari archi che collegano
	 * tutti i nodi con il meno peso possibile
	 * @author maur
	 */
	public LinkedList<Edge>  algorithmKruskal(){
		LinkedList<Edge> ritorno = new LinkedList();
		LinkedList<Edge> daAggiungere = new LinkedList();
		StaticGraph.reorderEdges(edges);						//riordino tutti gli archi
		LinkedList<Edge> copia = new LinkedList();
		copia.addAll(edges);									//copio edge in una LL
		int weight=0;
		StaticGraph.firstAddEdge(copia.getFirst(), ritorno);	//aggiunge il primo arco e mette true a entrambi i nodi
		weight+=copia.getFirst().getWeight();					//aumenta il peso
		
		for(Edge arco: copia){
			if(!arco.bothVisited()){  					//se almeno uno dei due nodi non è stato visitato
														//provo ad aggiungerlo
				if(StaticGraph.addEdge(arco, ritorno)){ //se posso aggiungerlo
					weight+=arco.getWeight();
					for(Edge arco2: daAggiungere){
						if(StaticGraph.addEdge(arco2, ritorno)) weight+=arco2.getWeight(); 
						//controllo se adesso uno degli archi che ho memorizzato posso aggiungerlo																		
					}
				}
				else daAggiungere.add(arco); //altrimenti memorizzo
			}
		}
		
		System.out.printf("Algoritmo di Kruskal\nil peso totale è: %d\n", weight);
		for(Edge arco:ritorno) System.out.printf("%s di peso %d\n",arco.getNameEdge(), arco.getWeight());
		StaticGraph.reorderEdges(ritorno);
		return ritorno;		
	}
	
	/**
	 * algoritmo di dijkstra
	 * @return il percorso con il minore peso
	 * @author mauro
	 */
	public LinkedList<Edge> algorithmDijkstra(){
		Node start=null, end=null;
		for(Node nodo: nodes){
			if(nodo.isStart()) start=nodo;
			else if(nodo.isEnd()) end=nodo;
		}
		
		LinkedList<Path> possibiliSoluzioni = new LinkedList();
		LinkedList<Edge> edgeDaAnalizzare = new LinkedList();
		
		edgeDaAnalizzare.addAll(start.getCollegamenti());  // ci metto gli edge che partono da start
		Path soluzione= new Path(edgeDaAnalizzare.get(StaticGraph.edgeLessWeight(edgeDaAnalizzare)));
		//metto in soluzione quello con il peso minore
		edgeDaAnalizzare.removeAll(soluzione.getPath()); 	//lo rimuovo da tutti gli altri
		for(Edge edge: edgeDaAnalizzare){
			Path nuovoPath = new Path(edge);   	
			possibiliSoluzioni.add(nuovoPath);	//metto tutto nella linkedlist possibili soluzioni
		}
		start=soluzione.lastNode();			//mi sposto da start all'altro nodo dell'ultimo edge
		while(!start.equals(end)){
			int posDaAggiungere;				//la posizione dell'edge che pesa meno
			edgeDaAnalizzare.clear();			//pulisco edgeDaAnalizzare
			
			edgeDaAnalizzare.addAll(start.getCollegamenti()); //qui piazzo i possibili edge soluzione
			posDaAggiungere=StaticGraph.edgeLessWeight(edgeDaAnalizzare);
			
			for(Edge edge: edgeDaAnalizzare){
				Path nuovoPath = new Path(soluzione, edge); //salvo i path path che ho analizzato
				possibiliSoluzioni.add(nuovoPath);			
			}
			soluzione.addEdge(edgeDaAnalizzare.get(posDaAggiungere)); //quello che pesa di meno lo aggiungo
			
			for(int i=0; i<possibiliSoluzioni.size(); i++){
				if(soluzione.getWeight()>possibiliSoluzioni.get(i).getWeight()){
					Path scarto = soluzione;			//creo un path per memorizzare soluzione
					possibiliSoluzioni.add(scarto);		//lo aggiungo alle possibili soluzioni
					soluzione = possibiliSoluzioni.get(i);//aggiorno la soluzione
				}
					
			}
			start=soluzione.lastNode();			//mi sposto sull'estremo del path soluzione
			
		}
		System.out.printf("Algoritmo di Dijkstra\nIl peso totale del percorso è:%d\n", soluzione.getWeight());
		for(Edge arco: soluzione.getPath()){
			System.out.printf("%s di peso %d\n",arco.getNameEdge(), arco.getWeight());
		}
		
		return soluzione.getPath();
	}
	
	
}