package deathstar.consegna5;

import java.util.Scanner;
//L'import della classe File e' utilizzato in fase di testing per verificare l'efficienza del programma senza dover tener conto dei tempi di input dell'utente
//TEST import java.io.File;

/** Classe contenente il main del programma che risolve il problema assegnato Magic Square.
 *  L'approccio utilizzato e' stato prettamente funzionale in quanto non si e' ritenuto necessario
 *  sfruttare i costrutti della programmazione ad oggetti per la risoluzione del problema.
 * 
 * @author Lorenzo Nodari
 * @author Michele Dusi
 *
 */
public class Solver {
	
	public static void main(String[] args) throws Exception {
		
		//TEST long start = System.nanoTime();
		
		int[][] matrice;
		int dimensione;
		Scanner input = new Scanner(System.in);
		//TEST Scanner input = new Scanner(new File("input.txt"));
		int somma_diagonale = 0;
		int somma_antidiagonale = 0;
		int[] somme_righe;
		int[] somme_colonne;
		
		//Numero di righe, colonne (ed eventualmente anche l'antidiagonale) che non rispettano la somma "magica"
		int linee_non_magiche = 0;
		
		//Buffer in cui creiamo la stringa che da la soluzione testuale al problema richiesta
		StringBuffer buffer = new StringBuffer();		
		
		//Acquisisco la dimensione della matrice
		dimensione = input.nextInt();
		if (dimensione < 1 || dimensione > 600) {
			System.out.println("ERRORE: Inserire una dimensione tra 1 e 600 inclusi");
			System.exit(-1);
		}
		matrice = new int[dimensione][dimensione];
		somme_righe = new int[dimensione];
		somme_colonne = new int[dimensione];
		
		//Acquisisco la matrice e mentre la acquisisco vado a calcolare le somme di righe, colonne e diagonali
		for (int i = 0; i < dimensione; i++) {
			for (int j = 0; j < dimensione; j++) {
				matrice[i][j] = input.nextInt();
				int elemento = matrice[i][j];
				
				//Somma diagonale principale
				if (i == j) {
					somma_diagonale += elemento;
				}
				//Somma antidiagonale
				if (i + j == dimensione - 1) {
					somma_antidiagonale += elemento;
				}
				
				//Somme degli elementi delle righe e somma degli elementi delle colonne
				somme_righe[i] += elemento;
				somme_colonne[j] += elemento;
			}
		}
		
		//A questo punto ho calcolato gia' tutte le somme di righe, colonne e diagonali
		//Vado quindi a verificare quante linee non rispettano la somma "magica"
		
		//Prima le colonne
		for (int j = dimensione - 1; j >= 0; j--) {
			if (somme_colonne[j] != somma_diagonale) {
				linee_non_magiche++;
				buffer.append(String.valueOf(-(j + 1)) + "\n");
			}
		}
		
		//Poi l'antidiagonale
		if (somma_antidiagonale != somma_diagonale) {
			linee_non_magiche++;
			buffer.append("0\n");
		}
		
		//Infine le righe
		for (int i = 0; i < dimensione; i++) {
			if (somme_righe[i] != somma_diagonale) {
				linee_non_magiche++;
				buffer.append(String.valueOf(i + 1) + "\n");
			}
		}
		
		//Ora stampo la soluzione al problema
		System.out.println(linee_non_magiche);
		System.out.print(buffer.toString());
		
		//TEST System.out.println("Elapsed: " + String.valueOf((System.nanoTime() - start) / 1e9) + "s");
		
		
	}

}
