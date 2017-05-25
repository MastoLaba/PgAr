package magicsquare;

import java.util.ArrayList;

import utils.Input;

public class MagicSquare {
	private final int NUM_ROWS;
	private final int[][] square;
	
	public MagicSquare(int num){
		NUM_ROWS = num;
		square = new int[NUM_ROWS][NUM_ROWS];		
	}
	
	public boolean input(){
		System.out.println("Inserire per righe i numeri:");
		for(int i=0; i<NUM_ROWS; i++){
			String s = Input.inputRiga();
			String[] numeri = s.split(" ");
			for(int j=0; j<NUM_ROWS && j<numeri.length; j++)
				square[i][j] = Integer.parseInt(numeri[j]);
		}
		return true;			
	}
	
	private int getSommaDiag(){
		int s=0;
		for(int i=0; i<NUM_ROWS; i++)
			s += square[i][i];
		return s;
	}
		
	public ArrayList<Integer> getWrongs(){
		int sommaDiag = getSommaDiag();
		ArrayList<Integer> wrongs = new ArrayList<>();
		for(int i=0; i<NUM_ROWS; i++){
			int somma = 0;
			for(int j=0; j<NUM_ROWS; j++)
				somma+= square[i][j];			
			if(somma != sommaDiag)
				wrongs.add(i+1);
		}
		for(int i=0; i<NUM_ROWS; i++){
			int somma = 0;
			for(int j=0; j<NUM_ROWS; j++)
				somma+= square[j][i];			
			if(somma != sommaDiag)
				wrongs.add(-(i+1));
		}
		int somma=0;
		for(int i=0; i<NUM_ROWS; i++)
			somma += square[i][NUM_ROWS-i-1];		
		if(somma != sommaDiag)
			wrongs.add(0);
		return wrongs;
	}
	
	public int getNumWrongs(){
		return getWrongs().size();
	}
	
	public String toString(){
		String s="";
		for(int i=0; i<NUM_ROWS; i++){
			for(int j=0; j<NUM_ROWS; j++)
				s += square[i][j] + " ";
			s += "\n";
		}
		return s;
	}
}
