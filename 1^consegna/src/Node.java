import java.util.Vector;

/* La classe NODE è così definita
Attributi
  String ID;
	String label;
	boolean start=false,end=false;
	Vector<Node> linked_nodes
    analogo per i pesi dei rami
Il metodo addNode, collega il nodo dato in input a quello attuale.
*/
public class Node {
	private static final Integer INTEGER = (Integer) null;
	//Attributi 
	private String ID;
	private String label;
	private boolean start, end;
	private Vector<Node> linked_nodes;
	private Vector<Integer> weight_nodes;
	
	public Node(){
		linked_nodes = new Vector<Node>();
		weight_nodes = new Vector<Integer>();
		this.start = false;
		this.end = false;
	}
	
	//GET
	
	public String getID() {
		return this.ID;
	}
	public Vector<Integer> getWeight_nodes(){
		return weight_nodes;
	}
	public String getWeightEdge(String id) {
		for(int i=0; i<linked_nodes.size();i++){
			if(linked_nodes.get(i).getID().equals(id)){
				return ""+weight_nodes.get(i).intValue();
			}
		}
		return null;
	}
	
	public int getWeightEdgeInt(String id) {
		for(int i=0; i<linked_nodes.size();i++){
			if(linked_nodes.get(i).getID().equals(id)){
				return weight_nodes.get(i).intValue();
			}
		}
		return INTEGER;
	}
	
	public Vector<Node> getEdge() {
		return linked_nodes;
	}

	public boolean hasEdge() {
		if(linked_nodes.isEmpty())
			return false;
		else
			return true;
	}

	public String getLabel() {
		return this.label;
	}

	public boolean isEnd() {
		return end;
	}

	public boolean isStart() {
		return start;
	}
	
	
	//SETTERS
	public void setLabel(String data) {
		this.label = data;
	}

	public void setID(String data) {
		this.ID = data;
	}

	public void setEnd() {
		this.end = true;
		
	}

	public void setStart() {
		this.start = true;
	}
	// metodi
	public void addNode(Node nodeByID, String weight) {
		if(nodeByID != null){
			linked_nodes.add(nodeByID);
			weight_nodes.add(Integer.parseInt(weight));
		}
	}
	public String toString(){
		String tmp ="label "+label+ " id "+ID+ " ";
		if(start) 
			tmp+="start"+" ";
		if(end)
			tmp+="end"+" ";
		tmp+="\nil nodo è collegato ai nodi\n";
		if(linked_nodes.size()==0){
			tmp+="Nessuno";
		}
		else{
			for(int i=0;i<linked_nodes.size();i++){
				tmp+=linked_nodes.get(i).getLabel();
				tmp+=" con peso "+weight_nodes.get(i).intValue()+"\n";
			}
		}
		return tmp;
	}
	
	public void reset_linked(){
		linked_nodes.clear();
		weight_nodes.clear();
	}
	public void addLinked(Node link, int peso){
		linked_nodes.add(link);
		weight_nodes.add(peso);
	}
}
