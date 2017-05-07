package dijkstra_kruskal;

import java.util.LinkedList;

public class Path {
	
	private LinkedList<Edge> path = new LinkedList();
	private int weight;
	
	/**
	 * costruttore vuoto
	 * @author mauro
	 */
	public Path(){
		
	}
	
	/**
	 * costruttore in cui il path coinciderà con
	 * l'arco che gli passo come parametro
	 * @param edge 
	 * @author mauro
	 */
	public Path(Edge edge){
		this.path.add(edge);
		weight=edge.getWeight();
	}
	
	/**
	 * costruttore in cui a un path passato come parametro
	 * aggiungo un arco 
	 * @param path
	 * @param edge
	 * NB se non sono consecutivi l'istanza sarà soltanto
	 * il path passato come parametro
	 * @author mauro
	 */
	public Path(Path path, Edge edge){
		this.path.addAll(path.getPath());
		this.weight=path.getWeight();
		addEdge(edge);
		
	}

	public LinkedList<Edge> getPath() {
		return path;
	}

	public void setPath(LinkedList<Edge> path) {
		this.path = path;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * aggiunge un arco alla LinkedList<Edge> edges aumentando
	 * anche il peso del path
	 * NB se edge non è consecutivo non lo aggiunge
	 * @param edge
	 * @return true se lo ha aggiunto
	 * @author mauro
	 */
	public boolean addEdge(Edge edge){
		boolean condition=false;
		if(path.getLast().getNodeA().equals(edge.getNodeA()) ||
			path.getLast().getNodeA().equals(edge.getNodeB()) ||
			path.getLast().getNodeB().equals(edge.getNodeA()) ||
			path.getLast().getNodeB().equals(edge.getNodeB())){
			
			path.add(edge);
			weight+=edge.getWeight();
			condition=true;
		}
		
		return condition;
	}
	
	/**
	 * ritorna l'ultimo nodo, ovvero quello più 
	 * "lontano" da start
	 * @return torna l'ultimo nodo di path
	 * @author mauro
	 */
	public Node lastNode(){
		Node ritorno=null;
		if(path.size()==1){
			if(path.getFirst().getNodeA().isStart()) ritorno=path.getFirst().getNodeB();
			else ritorno=path.getFirst().getNodeB();
		}
		else if(path.size()>1){
			Node nodo = StaticGraph.nodeInCommon(path.getLast(), path.get(path.size()-2));
			ritorno=path.getLast().otherNode(nodo);
		}
		return ritorno;
	}

}
