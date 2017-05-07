package dijkstra_kruskal;

import java.util.LinkedList;
import java.util.Vector;
import java.io.*;


import javax.xml.stream.*;

import myStatic.MyUtil;

public class StaticGraph {

	private static final String filePath="../";
	
	/**
	 * riordina tutti gli archi in base a loro peso
	 * dal minore al maggiore
	 * @author mauro
	 */
	public static void reorderEdges(LinkedList<Edge> edges){
		for (int i=0; i<edges.size(); i++){
			for (int j=i; j<edges.size(); j++){
				if(edges.get(i).getWeight()>edges.get(j).getWeight())
					MyUtil.switchObject(edges, edges.get(i), edges.get(j));
			}
		}
	}
	
	/**
	 * aggiunge un arco ad una linkedlist di edge 
	 * modificando ai nodi dell'arco la variabile visited in true 
	 * @param edge
	 * @param edges
	 */
	public static void firstAddEdge(Edge edge, LinkedList<Edge> edges){
		edges.add(edge);
		edge.visited();
	}

	/**
	 * aggiunge un arco a una linkedlist di edge soltanto se
	 * almeno uno dei due suoi nodi è stato visitato, in tal caso
	 * modifica la variabile visited in true a entrambi i nodi
	 * @param edge arco da aggiungere
	 * @param edges linkedlist di archi
	 * @return true se lo ha aggiunto
	 * @return false se non lo ha aggiunto
	 * @author mauro
	 */
	public static boolean addEdge(Edge edge, LinkedList<Edge> edges){
		boolean condition=false;
		
		
			for(Edge arco: edges){
		
				if(arco.contains(edge.getNodeA()) || arco.contains(edge.getNodeB())  ){
						edges.add(edge);
						edge.visited();
						condition=true;
						break;
					}
			}
		
		return condition;
	}

	/**
	 * controlla se esist
	 * @param edge1
	 * @param edge2
	 * @return
	 */
	public static boolean nodeInCommonBoolean(Edge edge1, Edge edge2){
		boolean condition=false;
		if(edge1.getNodeA().equals(edge2.getNodeA()) || 
				edge1.getNodeA().equals(edge2.getNodeB())) {
			condition=true;
			
		}
		
		else if(edge1.getNodeB().equals(edge2.getNodeA()) ||
				edge1.getNodeB().equals(edge2.getNodeB())){
			condition=true;
			
		}
		
		return condition;
	}
	
	/**
	 * trova il nodo in comune a due edge
	 * @param edge1 
	 * @param edge2
	 * @return il nodo in comune
	 */
	
	public static Node nodeInCommon(Edge edge1, Edge edge2){
		Node ritorno=null;
		if(edge1.getNodeA().equals(edge2.getNodeA()) ||
				edge1.getNodeA().equals(edge2.getNodeB()))  ritorno=edge1.getNodeA();
		else if(edge1.getNodeB().equals(edge2.getNodeA()) ||
				edge1.getNodeB().equals(edge2.getNodeB())) ritorno=edge1.getNodeB();
		return ritorno;
	}
	/**
	 * se un nodoA e un nodoB hanno un edge in comune appartente
	 * alla LinkedList
	 * @param nodoA 
	 * @param nodoB
	 * @param listPath
	 * @return true se è vero che ce n'è almeno uno
	 * @author mauro
	 */
	public static boolean edgeInCommon(Node nodoA, Node nodoB, LinkedList<Edge> listPath){
		boolean condition=false;
		for(Edge path: listPath){
			if(nodoA.getCollegamenti().contains(path)){
				if(path.otherNode(nodoA).equals(nodoB)){

					condition=true;
					break;
				}
			}
		}
		
		return condition;
		
	}
	
	/**
	 * se un nodo ha in comune un edge di una LinkedList con almeno un nodo
	 * di una LinkedList
	 * @param nodo
	 * @param listNodi
	 * @param listPath
	 * @return true se c'è almeno un edge in comune
	 * @author mauro
	 */
	public static boolean edgeInCommon(Node nodo, Vector<Node> listNodi, LinkedList<Edge> listPath){
		boolean condition=false;
		for(Node altroNodo: listNodi){
			if (edgeInCommon(nodo, altroNodo, listPath)){
				condition=true;
				break;
			}
		}
		return condition;
	}
	
	public static int edgeLessWeight(LinkedList<Edge> edges){
		int posizione=0;
		int lessWeight=edges.getFirst().getWeight();
		for(int i=0; i<edges.size(); i++){
			if(lessWeight>edges.get(i).getWeight()){
				lessWeight=edges.get(i).getWeight();
				posizione=i;
			}
		}
		return posizione;
	}

	/**
	 * stampa un file xml di un percorso
	 * @param grafo la struttura dove si ha fatto le varie operazioni
	 * @param path il percorso tornato dall'algoritmo di kruskal o di dijkstra
	 * @param nomeFile il nome con cui si vuole salvare il file
	 * @return ritorna se effettivamente è avvenuta la stampa o meno
	 * @throws XMLStreamException
	 * @author mauro
	 */
	public static boolean printXMLGraph(Graph grafo, LinkedList<Edge> path, String nomeFile)
	throws XMLStreamException{
		
		System.out.println("Scrittura su file");
		Vector <Node> nodiAggiunti = new Vector();
		
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		
		try{
			writer = output.createXMLStreamWriter( new FileWriter(filePath + nomeFile));
			
			writer.writeStartDocument("utf-8", "1.0");
			writer.writeStartElement("tree");		//apro l'albero
			
			
			
			for (Node nodo : grafo.getNodes()){
				writer.writeStartElement("node");	//apro il nodo
				
				if(nodo.isStart()) writer.writeAttribute("start", "true");
				if(nodo.isEnd()) writer.writeAttribute("end", "true");
				
				writer.writeStartElement("label");
				writer.writeCharacters(nodo.getNameNode());
				writer.writeEndElement();
				
				if(edgeInCommon(nodo, nodiAggiunti, path)){
					writer.writeStartElement("edges");	//apro edges
				
				//System.out.println(String.format(arg0, arg1));
					for (Node altroNodo : nodiAggiunti){
						if (edgeInCommon(nodo, altroNodo, path)){
							writer.writeStartElement("edge"); //apro il edge
							String peso=String.format("%d", grafo.findEdge(nodo, altroNodo).getWeight());;
							
							writer.writeAttribute("weight", peso);
							writer.writeCharacters(altroNodo.getNameNode());
							writer.writeEndElement();		//chiudo il edge
						}
					}
					writer.writeEndElement();			//chiudo edges
											
				}
				nodiAggiunti.add(nodo);
				writer.writeEndElement();				//chiudo il nodo	
			}
			writer.writeEndElement();					//chiudo l'albero
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			
		}catch(IOException e){
			System.out.println("errore durante il salvataggio");
			e.printStackTrace();
			return false;
		}
		System.out.println("salvataggio riuscito");
		return true;
	}

	

	
	//TODO ../../nomedelfile torna indietro di due directory da quella corrente =shell linux
	
	
	
	
	
	
	
	
	
	
	
	
}
