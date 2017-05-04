package deathstar.consegna1;

import java.util.Comparator;

public class ArcComparator implements Comparator<Arc> {

	// Costanti
	private final static int PRIMO_NODO = 0;
	private final static int SECONDO_NODO = 1;

	public ArcComparator() {}
	
	@Override
	/**
	 * Metodo che compara due archi in base a (in ordine):
	 * - I nodi che linkano (se linkano gli stessi nodi vengono considerati uguali, e quindi scartati nel SortedSet).
	 * - Il loro peso (ordine crescente).
	 * - La loro etichetta (solamente in caso di pari peso).
	 */
	public int compare(Arc arc0, Arc arc1) {
		if (arc0.equals(arc1)) {
			return 0;
		} else {
			int difference_weight = arc0.getWeight() - arc1.getWeight();
			if (difference_weight != 0) {
				return difference_weight;
			} else {
				// Caso "peso uguale": implemento un ordinamento di comodo basato sugli Id.
				int difference_id = arc0.getId()[PRIMO_NODO] - arc1.getId()[PRIMO_NODO];
				if (difference_id != 0) {
					return difference_id;
				} else {
					return arc0.getId()[SECONDO_NODO] - arc1.getId()[SECONDO_NODO];		
				}
			}
		}
	}
}
