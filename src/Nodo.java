/**
 * Classe che rappresenta un singolo nodo di un grafo.
 * @author Qwertyteam
 * @version 1.4
 * @since 1.0
 */

public class Nodo {
	private boolean isDestination;
	private boolean isSource;
	private int idNode;
	private char label;
	
	/**
	 * Costruttore della classe Nodo.
	 * @param idNode ID che dovrà avere il nodo.
	 * @param label Etichetta che dovrà avere il nodo.
	 */
	
	public Nodo(int idNode, char label){
		this.idNode = idNode;
		if(idNode == 0)
			setAsSourceNode();
		this.label = label;
	}
	
	/**
	 * Costruttore della classe Nodo.
	 * @param label Etichetta che dovrà avere il nodo.
	 */
	
	public Nodo(char label){
		this.label = label;
	}
	
	/**
	 * Costruttore della classe Nodo.
	 * @param idNode ID che dovrà avere il nodo.
	 */
	
	public Nodo(int idNode){
		this.idNode = idNode;
	}
	
	/**
	 * Metodo per ricavare l'ID del nodo.
	 * @return ID del nodo
	 */
	
	public int getIDNode(){
		return idNode;
	}
	
	/**
	 * Metodo per ricavare l'etichetta del nodo.
	 * @return Etichetta del nodo
	 */
	
	public char getLabel(){
		return label;
	}
	
	/**
	 * Metodo per settare l'ID del nodo.
	 * @param idNode Nuovo ID del nodo.
	 */
	
	public void setIDNode(int idNode){
		this.idNode = idNode;
	}
	
	/**
	 * Metodo per settare l'etichetta del nodo.
	 * @param label Nuova etichetta del nodo.
	 */
	
	public void setLabelNode(char label){
		this.label = label;
	}
	
	/**
	 * Metodo che permette di restituire se il nodo è di destinazione o meno.
	 * @return True se è di destinazione, false altrimenti.
	 */
	
	public boolean isDestinationNode(){
		return isDestination;
	}
	
	/**
	 * Metodo che permette di restituire se il nodo è la sorgente o meno.
	 * @return True se è la sorgente, false altrimenti.
	 */
	
	public boolean isSourceNode(){
		return isSource;
	}
	
	/**
	 * Setta il nodo a nodo destinazione.
	 */
	
	public void setAsDestinationNode(){
		isDestination = true;
	}
	
	/**
	 * Setta il nodo a nodo sorgente.
	 */
	
	public void setAsSourceNode(){
		setIDNode(0);
		isSource = true;
	}
}
