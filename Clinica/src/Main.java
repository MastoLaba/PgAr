import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Main {

	public static void main(String[] args) {
		Ospedali ospedale = new Ospedali();
		ospedale.dottori.addElement(new Dottori("roberto","cavagnoli","cod","li",new Date(1991,12,1),123,new Date(1991,12,1),new Date(1991,12,1),ospedale.creaOrario()));
		ospedale.pazienti.addElement(new Pazienti("pippo","paperino","poi","li",new Date(1991,12,1),"quello"));
		Scanner in = new Scanner(System.in);
		Vector<Appuntamenti> tmp = new Vector<Appuntamenti>();
		int matricola,select;
		String cf;
		Date data,oraFine;
		do{
			stampaMenu();
			try{
				select = Integer.parseInt(in.nextLine());
			}catch(Exception e){
				select=123;
			}
			switch(select){
				case 0:
					System.out.println("Arrivederci!");
					System.exit(0);
				case 1:
					ospedale.aggiungiDottore();
					break;
				case 2:
					ospedale.aggiungiPaziente();
					break;
				case 3:
					ospedale.aggiungiAppuntamento();
					break;
				case 4:
					System.out.println("inserisci la matricola del medico da eliminare");
					matricola = Integer.parseInt(in.nextLine());
					ospedale.eliminaDottore(matricola);
					break;
				case 5:
					System.out.println("inserisci il codice fiscale del paziente da eliminare");
					cf = in.nextLine();
					ospedale.eliminaPaziente(cf);
					break;
				case 6:
					System.out.println("inserisci la matricola del medico");
					matricola = Integer.parseInt(in.nextLine());
					System.out.println("inserisci il codice fiscale del paziente");
					cf = in.nextLine();
					Date orat;
					System.out.println("Inserisci data inizio appuntamento");
					data = ospedale.creaData();
					System.out.println("inserisci orario inizio appuntamento");
					orat = ospedale.creaOra();
					data .setHours(orat.getHours());
					data.setMinutes(orat.getMinutes());
					ospedale.eliminaAppuntamento(matricola, cf, data);
					break;
				case 7:
					System.out.println(ospedale.stampaMedici());
					break;
				case 8:
					System.out.println("inserisci la matricola del medico");
					matricola = Integer.parseInt(in.nextLine());
					ospedale.stampaOrari(matricola);
					break;
				case 9:
					System.out.println("inserisci la matricola del medico");
					matricola = Integer.parseInt(in.nextLine());
					tmp = ospedale.stampaAppuntamenti(matricola);
					break;
				case 10:
					System.out.println(ospedale.stampaPazienti());
					break;
				case 11:
					//TODO
					System.out.println("Inserisci data inizio periodo");
					data = ospedale.creaData();
					System.out.println("inserisci orario inizio periodo");
					orat = ospedale.creaOra();
					data .setHours(orat.getHours());
					data.setMinutes(orat.getMinutes());
					orat =null;
					System.out.println("Inserisci data fine periodo");
					oraFine = ospedale.creaData();
					System.out.println("inserisci ora fine periodo");
					orat = ospedale.creaOra();
					oraFine.setHours(orat.getHours());
					oraFine.setMinutes(orat.getMinutes());
					ospedale.stampaAppuntamenti(data, oraFine);
					break;
				case 12:
					System.out.println("inserisci la matricola del medico");
					matricola = Integer.parseInt(in.nextLine());
					System.out.println(ospedale.stampaNumAppuntamenti(matricola));
					break;
				default:
					System.out.println("Errore di inserimento, scrivi il numero corrispondente all'azione desiderata.");
					break;
			}	
		}while(true);
	}
	public static void stampaMenu(){
		String tmp="";
		tmp+="\nSelezione l'operazione da eseguire\n0-Esci";
		tmp+="\n1-Aggiungi medico\n2-Aggiungi paziente\n3-Aggiungi appuntamento"
				+ "\n4-Elimina medico\n5-elimina paziente\n6-elimina appuntamento"
				+ "\n7-Stampa medici\n8-stampa orario di un medico\n9-stampa appuntamenti"
				+ "\n10-stampa pazienti\n11-Stampa tutti gli appuntamenti di un periodo"
				+ "\n12-stampa numero appuntamenti di un medico";
		System.out.println(tmp);
	}

}

