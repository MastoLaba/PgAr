package it.unibs.ing.fp.kruskalxml;

/**
 * Classe struttura dati di tipo GroupString. Il nostro algoritmo di Kruskal
 * utilizza questa struttura dati per controllare se, aggiungendo un nodo al MST
 * si forma un ciclo.
 * 
 * @author Stefano Poma
 *
 */
public class GroupString {

	// StringBuffer che conterrà i set del gruppo corrispondente
	StringBuffer set = new StringBuffer();

	/**
	 * Aggiunge un set (un carattere solo o anche più caratteri)
	 * 
	 * @author Stefano Poma
	 * 
	 * @param str
	 *            Stringa che contiene i set (i label dei singoli nodi)
	 * 
	 */
	public void addSet(String str) {
		set.append(str);
	}

	/**
	 * Ritorna il set
	 * 
	 * @author Stefano Poma
	 * 
	 * @return Stringa che contiene il set
	 */
	public String getSet() {
		return set.toString();
	}

	/**
	 * Controlla se nello StringBuffer set è presente uno specifico set (uno o
	 * più caratteri)
	 * 
	 * @author Stefano Poma
	 * 
	 * @param a
	 *            Una CharSequence che contiene il set da confrontare
	 * 
	 * @return vero se lo contiene, falso altrimenti
	 */
	public boolean contain1Set(CharSequence a) {
		boolean flag = false;
		String ritorno = set.toString();
		if (ritorno.contains(a))
			flag = true;

		return flag;
	}

	/**
	 * Controlla se 2 set sono contenuti entrambi nello StringBuffer
	 * 
	 * @author Stefano Poma
	 * 
	 * @param a
	 *            Set da controllare
	 * @param b
	 *            Set da controllare
	 * 
	 * @return Vero se li contiene, falso altrimenti
	 */
	public boolean contain2Set(CharSequence a, CharSequence b) {
		boolean flag = false;

		if (contain1Set(a) && contain1Set(b))
			flag = true;

		return flag;
	}

}
