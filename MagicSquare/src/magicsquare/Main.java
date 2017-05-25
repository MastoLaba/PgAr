package magicsquare;

import utils.Input;

public class Main {
	public static void main(String[] args) {
		System.out.print("Inserire dimensione quadrato: ");
		int NUM = Input.inputNumeroTra(1, 600);
		MagicSquare ms = new MagicSquare(NUM);
		ms.input();

		System.out.print("Numero di righe/colonne errate: ");
		System.out.println(ms.getNumWrongs());
		System.out.println("Righe/Colonne errate: ");
		for(Integer i: ms.getWrongs())
			System.out.println(i);

	}

}
