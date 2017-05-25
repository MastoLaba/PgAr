package it.unibs.ing.ieee.magicsquare;

import java.util.ArrayList;
import java.util.List;

import it.unibs.fp.mylib.InputDati;

public class MagicSquare {
	List<Integer> output = new ArrayList<>();
	int n;
	int[][] mat;

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void esegui() {
		
		int diag=0;
		int adiag = 0;
		
		for(int i = 0 ; i<n; i++) {
			diag += mat[i][i];
			adiag += mat[i][n-i-1];			
		}
		
		if(adiag != diag) {
			output.add(0);
		}
			
		
		for(int c=0; c<n ; c++) {
			int sum = 0;
			for(int r=0 ; r<n; r++){
				
				sum += mat[r][c];

			}
			if(sum != diag) {
				output.add(-1*(c+1));
				
			}
		}
		
		
		for(int c=0; c<n ; c++) {
			int sum = 0;
			for(int r=0 ; r<n; r++){
				
				sum += mat[c][r];

			}
			if(sum != diag) {
				output.add(c+1);
				
			}
		}
		
	}
	
	public void inserisci(){
		n = InputDati.leggiInteroNonNegativo("n: ");
		mat = new int[n][n];
		
		for(int c=0; c<n ; c++) {
			String riga = InputDati.leggiStringaNonVuota("riga"+ c + ": ");
			String[] pezzi = riga.split(" ");
			for(int i = 0; i<n ; i++) {
				mat[c][i] = Integer.parseInt(pezzi[i]);
			}
		}
		
		for(int c=0; c<n ; c++) {
			for(int w=0; w<n ; w++) {
				System.out.print(mat[c][w]);
			}
			System.out.print("\n");
		}
		
	}
	
	public void print() {
		System.out.println(output.size());
		System.out.println(output);
	}
		
	
	
}	
