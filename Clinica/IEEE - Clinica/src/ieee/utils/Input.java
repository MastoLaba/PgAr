package ieee.utils;

import java.util.Scanner;

public class Input {
    private static Scanner sc;
    
    public static String inputRiga(){
		sc = new Scanner(System.in)
        String s = "";
        s = sc.next();
        return s;
    }
    
    public static int inputNumero(){
		sc = new Scanner(System.in)
        while(!sc.hasNextInt()){
            System.err.print("Errore nell'input! Inserire un numero intero: ");
            sc.next();
        }
        return sc.nextInt();
    }
    
    /**
     * Input da tastiera di un numero compreso tra min e max 
     * @param min minimo incluso
     * @param max massimo incluso
     * @return il numero inserito
     */
    public static int inputNumeroTra(int min, int max){
        int ritorno = inputNumero();
        while(ritorno<min || ritorno>max){
            System.err.print("Errore nell'input! Inserire un numero compreso tra "+min+" e "+max+": ");
            ritorno = inputNumero();
        }
        return ritorno;
    }
}
