package dijkstra_kruskal.myUtil.myStatic;

public class Opzioni {
	
	private final static String SI = "S";
	private final static String NO = "N";
	
	/**
	 * date due possibilità stampate a video c'è fa scegliere all'utente
	 * quale delle due opzioni desidera
	 * @param messaggio
	 * @return
	 */
	public static boolean yesOrNo(String messaggio){
		boolean condition=false;
		String risposta;
		
		do{
			risposta=InputClass.readString(messaggio);
		}while(risposta.equalsIgnoreCase(SI) && risposta.equalsIgnoreCase(NO));
		
		if (risposta.equalsIgnoreCase(SI)) condition=true;
		return condition;
	}

}
