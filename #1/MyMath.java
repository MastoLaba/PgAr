package dijkstra_kruskal.myUtil.myStatic;

public class MyMath {
	
	public static double sommatoria (double ... addendi){
		double somma=0;
		for (int i=0; i<addendi.length; i++){
			somma+=addendi[i];
		}
		return somma;
	}
	
	public static double media (double ... addendi){
		double media;
		media=sommatoria(addendi)/addendi.length;
		return media;				
	}

}
