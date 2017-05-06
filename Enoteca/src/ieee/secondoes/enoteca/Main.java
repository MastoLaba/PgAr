package ieee.secondoes.enoteca;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.xml.stream.XMLStreamException;

public class Main {
    private static final String INTERFACCIA = 
            "1) Palesa tutti i vini che c'ho\n" +
            "2) Conta le bottiglie di un Vino\n" +
            "3) Conta le bottiglie di un Produttore\n" +
            "4) Conta i soldi totali\n" +
            "5) Mostra i Vini prodotti tra gli anni...\n" +
            "6) Esci\n" +
            "Scegli un'opzione: ";
    
    @SuppressWarnings("resource")
	private static int scanNumero(){        
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            System.out.println("Input errato! Inserire un numero intero");
            sc.next();
        }
        return sc.nextInt();
    }
    
    @SuppressWarnings("resource")
	private static String scanParola(){ return (new Scanner(System.in)).next(); }    
    
    public static void main(String[] args) {
        int scelta = 0;
        Enoteca enoteca = null;
        try {
            enoteca = new Enoteca("enoteca.xml");
        } catch (FileNotFoundException | XMLStreamException ex) { }
        
        while(scelta != 6){
            System.out.print(INTERFACCIA);
            scelta = scanNumero();
            switch(scelta){
                case 1:  
                    System.out.println(enoteca.stampaVino());
                    break;
                case 2:
                    System.out.print("Inserisci il Vino che vuoi contare: ");
                    String nomeVino = scanParola();
                    System.out.println("Ci sono "+ enoteca.numeroViniPerNomeVino(nomeVino) +" di \""+nomeVino+"\"");
                    break;
                case 3:
                    System.out.print("Inserisci il Produttore di cui vuoi contare i vini: ");
                    String nomeProduttore = scanParola();
                    System.out.println("Ci sono "+ enoteca.numeroViniPerNomeProduttore(nomeProduttore) +" del produttore \""+nomeProduttore+"\"");                    
                    break;
                case 4:
                    System.out.println("Quale valuta preferisci?\n1) Euri, 2) Dollari");
                    int sceltaValuta = scanNumero();
                    if(sceltaValuta == 1)
                        enoteca.possibileGuadagno("€");
                    else if(sceltaValuta == 2)
                        enoteca.possibileGuadagno("$");
                    else
                        System.out.println("Valuta non valida");
                    break;
                case 5:
                    System.out.print("Inserisci primo anno: ");
                    int annoUno = scanNumero();
                    System.out.print("Inserisci secondo anno: ");
                    int annoDue = scanNumero();
                    if(annoUno>annoDue){
                        int t = annoUno;
                        annoUno = annoDue;
                        annoDue = t;
                    }
                    for(Vino v: enoteca.stampaFasciaVini(annoUno, annoDue)){
                        System.out.println(v);
                    }
                    break;
                case 6:
                    System.out.println("Addio");
                    return;
                default:
                    System.out.println("Inserire una scelta valida!");
                    break;
            }
            System.out.println("________________________________________________");
        }
    }
}
