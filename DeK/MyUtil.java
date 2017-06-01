package dijkstra_kruskal.myUtil.myStatic;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

public class MyUtil {

	private static final String SPACE=" ";
	/**
	 * taglia una stringa da un determinato carattere
	 * a un altro
	 * @param string da tagliare
	 * @param inizio carattere da cui tagliare
	 * @param fine carattere fino a cui tagliare
	 * @return stringa tagliata
	 * @author mauro
	 * 
	 * example: string = "abcdefg"  inizio="c"   fine="f"
	 * return="de"
	 */
	public static String subString(String string, String inizio, String fine){
		String ritorno=null, stringaDaButtare=string;
		if(contains(string, inizio) && contains(string, fine)){
			
			int firstPosition = string.indexOf(inizio);
			firstPosition++;
			int secondPosition = string.indexOf(fine, firstPosition);
			ritorno=stringaDaButtare.substring(firstPosition, secondPosition);
			
		}
		return ritorno;
	}
	
	/**
	 * controlla se la string2 è contenuta nella string1
	 * se string2 è più lunga di string1 torna false
	 * @param string1 la stringa madre che dovrebbe contenere l'altra
	 * @param string2 la string che dovrebbe essere contenuta nell'altra
	 * @return true se la seconda stringa è contenuta nell'altra
	 * @return false se la seconda stringa non è contenuta nell'altra
	 */
	public static boolean contains(String string1, String string2){
		boolean condition=false;
		string1=string1+SPACE;
		if(string1.length()<string2.length()) return condition;
		for(int i=0; i+string2.length()<string1.length(); i++){
			String stringaDaButtare=string1.substring(i, string2.length()+i);
			if(stringaDaButtare.equalsIgnoreCase(string2)) {
				condition=true;
				break;
			}
		}
		return condition;
	}
	
	
	/**
	 * cambia di posizione due oggetti in una linkedlist
	 * @param linkedList la collection in cui apportare la modifica
	 * @param object1 l'oggetto che verrà spostato nella posizione 2
	 * @param object2 l'oggetto che verrà spostato nella posizione 1
	 * @author mauro
	 */
	public static void switchObject(LinkedList linkedList, Object object1, Object object2){
		if(linkedList.contains(object1) && linkedList.contains(object2)){
			int position1=linkedList.indexOf(object1);
			int position2=linkedList.indexOf(object2);
		
			linkedList.set(position2, object1);
			linkedList.set(position1, object2);
		}
	}
	
	/**
	 * cambia di posizione due oggetti in un vettore
	 * @param vector la collection in cui apportare la modifica
	 * @param object1 l'oggetto che verrà spostato nella posizione 2
	 * @param object2 l'oggetto che verrà spostato nella posizione 1
	 * @author mauro
	 */
	public static void swigObject(Vector vector, Object object1, Object object2){
		if(vector.contains(object1) && vector.contains(object2)){
			
			int position1=vector.indexOf(object1);
			int position2=vector.indexOf(object2);
		
			vector.setElementAt(object1, position2);
			vector.setElementAt(object2, position1);
		}
			
	}
	
}
