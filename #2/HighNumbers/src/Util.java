import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe utilità. Progettata per contenere metodi che permettono di risolvere il problema
 * C della consegna 3, in particolare di manipolare la struttura di tipo LinkedHashMap.
 * @author qwertyteam
 * @version 1.0
 */

public class Util {
	
	/**
	 * Metodo che permette di ridurre il valore dei numeri su scala logaritmica. Si applica
	 * il logaritmo a ogni potenza e si sfrutta una delle note proprietà dei logaritmi: 
	 * l'esponente della potenza diventa coefficiente del logaritmo. Questo permette di
	 * lavorare con dei dati che possono essere utilizzabili da un normale calcolatore.
	 * @param bases ArrayList che contiene la lista delle basi delle potenze.
	 * @param exponents ArrayList che contiene la lista degli esponenti delle potenze.
	 * @return Ritorna una nuova LinkedHashMap contenente i valori ridotti su scala
	 * logaritmica.
	 */
	
	public static LinkedHashMap<Integer, Double> reduceValues(ArrayList<Integer> bases, ArrayList<Integer> exponents){
		LinkedHashMap<Integer, Double> reducedNumbers = new LinkedHashMap<>();
		
		for(int index = 0; index < bases.size() && index < exponents.size(); index++){
			reducedNumbers.put(index, exponents.get(index) * Math.log10(bases.get(index)));
		}
		
		return reducedNumbers;
	}
	
	/**
	 * Metodo che permette, attraverso l'ausilio di un Comparator, il riordinamento della
	 * LinkedHashMap in modo che sia applicato sulla struttura il criterio di crescenza.
	 * @param mapNotOrdered LinkedHashMap con i valori ridotti non ordinati
	 * @return LinkedHashMap con i valori ordinati in senso crescente.
	 */
	
	public static LinkedHashMap<Integer, Double> sortByComparator(LinkedHashMap<Integer, Double> mapNotOrdered)
    {
        List<Entry<Integer, Double>> list = new LinkedList<Entry<Integer, Double>>(mapNotOrdered.entrySet());

        Collections.sort(list, new Comparator<Entry<Integer, Double>>()
        {
            public int compare(Entry<Integer, Double> firstNumber,
                    Entry<Integer, Double> secondNumber)
            {
                    return firstNumber.getValue().compareTo(secondNumber.getValue());
            }
        });

        LinkedHashMap<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
        
        for (Entry<Integer, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	
	/**
	 * Metodo che permette di restituire dalla LinkedHashMap la chiave relativa alla cifra
	 * con il valore più basso.
	 * @param reducedNumbers LinkedHashMap da analizzare
	 * @return La chiave relativa alla cifra con il valore più basso.
	 */
	
	public static int getKeyOfLowestValue(LinkedHashMap<Integer, Double> reducedNumbers){
		Entry<Integer, Double> min = reducedNumbers.entrySet().iterator().next();
		return min.getKey();
	}
	
	/**
	 * Metodo che permette di restituire dalla LinkedHashMap la chiave relativa alla cifra
	 * con il valore più alto.
	 * @param reducedNumbers LinkedHashMap da analizzare
	 * @return La chiave relativa alla cifra con il valore più alto.
	 */
	
	public static int getKeyOfGreatestValue(LinkedHashMap<Integer, Double> reducedNumbers){
		List<Entry<Integer, Double>> entryList =
				    new ArrayList<Map.Entry<Integer, Double>>(reducedNumbers.entrySet());
		Entry<Integer, Double> max = entryList.get(entryList.size()-1);
		return max.getKey();
	}
	
	/**
	 * Di norma se l'insieme dei numeri n da analizzare è di numero pari, la mediana è
	 * rappresentata dal valore n/2. Se invece n è un numero dispari la mediana è per
	 * definizione la media tra il valore n/2 e (n+1)/2. In questo metodo se il numero
	 * dei dati da analizzare è pari viene restituita la chiave relativa al numero rappresentante
	 * la mediana, altrimenti vengono restituite le chiavi dei numeri su cui successivamente
	 * verranno indicati come bersagli della media.
	 * @param reducedValues LinkedHashMap da analizzare
	 * @return Chiave/i relativa/e alla/e cifra/e che rappresenta/no la mediana tra tutti i
	 * numeri dell'elenco.
	 */
	
	public static int [] getKeysOfMediana(LinkedHashMap<Integer, Double> reducedValues){
		 List<Entry<Integer, Double>> entryList =
				    new ArrayList<Map.Entry<Integer, Double>>(reducedValues.entrySet());
		 
		 if(entryList.size() % 2 != 0){
			 return new int[]{entryList.get(((entryList.size() + 1)/2) - 1).getKey()};
		 }else{
			 return new int[]{entryList.get((entryList.size()/2) - 1).getKey(), 
					 entryList.get((entryList.size()/2)).getKey()}; 
		 }
	 }	
}
