import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class MagicSquare {
	private int[][] square;
	private TreeMap<Integer, Integer> noMagicSquare;
	private int mainDiagonalValue;

	public MagicSquare(){
		noMagicSquare = new TreeMap<>();
		inizializzaMatrice();
		calcolaDiagonalePrincipale();
	}
	
	public void inizializzaMatrice(){
		Scanner in = new Scanner(System.in);
		int dim=in.nextInt();
		while(dim<0||dim>600){
			dim = in.nextInt();
		}
		this.square = new int[dim][dim];
		for(int i=0; i<square.length; i++){
			for(int j=0; j<square.length; j++){
				square[i][j] = in.nextInt();
			}
		}
		in.close();
	}
	
	public void calcolaDiagonalePrincipale(){
		for(int i=0; i<square.length; i++) mainDiagonalValue += square[i][i];
	}
	
	private void calcolaAntiDiagonale(){
		int somma = 0;
		for(int i=0; i<square.length; i++){
			somma += square[square.length-i-1][i];
		}
		if(somma!=mainDiagonalValue) noMagicSquare.put(0, somma);
	}
	private void calcolaRighe(){
		int somma;
		for(int i=0; i<square.length; i++){
			somma = 0;
			for(int j=0; j<square[i].length; j++){
				somma += square[i][j];
			}
			if(somma!=mainDiagonalValue) noMagicSquare.put(i+1, somma);
		}
	}
	private void calcolaColonne(){
		int somma;
		for(int i=0; i<square.length; i++){
			somma = 0;
			for(int j=0; j<square[i].length; j++){
				somma += square[j][i];
			}
			if(somma!=mainDiagonalValue) noMagicSquare.put(-(i+1), somma);
		}
	}
	
	public void valutaQuadrato(){
		calcolaAntiDiagonale();
		calcolaRighe();
		calcolaColonne();
	}
	
	public void stampaQuadrato(){
		if(noMagicSquare==null)
			System.out.println(0);
		Iterator<Integer> keySet = noMagicSquare.keySet().iterator();
		System.out.println(noMagicSquare.keySet().size());
		while(keySet.hasNext()){
			System.out.println(keySet.next());
		}
	}
	

}
