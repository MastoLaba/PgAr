package dijkstra_kruskal.myUtil.myStatic;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Output {

	
	/**
	 * stampa a video una data
	 * @param data
	 */
	public static void printDate(GregorianCalendar data){
		System.out.println("la data è " + data.get(Calendar.YEAR) + "/" 
							+ data.get(Calendar.MONTH) + "/" 
							+ data.get(Calendar.DAY_OF_MONTH));
	}

}
