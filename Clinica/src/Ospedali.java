import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.plaf.synth.SynthSpinnerUI;
@SuppressWarnings("resource")
public class Ospedali implements Interfaccia {
	/*private*/public Vector<Dottori> dottori;
	/*private*/public Vector<Pazienti> pazienti;
	/*private*/public Vector<Appuntamenti> appuntamenti;

	public Ospedali() {
		super();
		dottori = new Vector<Dottori>();
		pazienti = new Vector<Pazienti>();
		appuntamenti = new Vector<Appuntamenti>();

	}
	
	public void eliminaAppuntamento(int matricola, String codFis, Date data){
		boolean trovato = false;
		for(int i=0;i<appuntamenti.size();i++){
			if(appuntamenti.get(i).getMedico().getMatricola()== matricola && pazienti.get(i).getCodFiscale().equals(codFis) && appuntamenti.get(i).getData().equals(data)){
				appuntamenti.remove(i);
				trovato = true;
				System.out.println("appuntamento cancellato con successo");
			}
		}
		if(!trovato){
			System.out.println("appuntamento non trovato");
		}
	}
	
	public void eliminaPaziente(String codFisc){
		boolean trovato =false;
		for(int i=0;i<pazienti.size();i++){
			if(pazienti.get(i).getCodFiscale().equals(codFisc)){
				pazienti.remove(i);
				trovato = true;
				System.out.println("paziente rimosso con successo");
			}
		}
		if(!trovato){
			System.out.println("paziente non trovato");
		}
	}
	
	public void eliminaDottore(int matricola){
		boolean trovato =false;
		for(int i=0;i<dottori.size();i++){
			if(dottori.get(i).getMatricola() == matricola){
				dottori.remove(i);
				trovato = true;
				System.out.println("Dottore rimosso con successo");
			}
		}
		if(!trovato){
			System.out.println("Dottore non trovato");
		}
	}
		
	public void aggiungiAppuntamento(){
		Date data,oraFine;
		String urgenza;
		int matricola;
		Dottori dottore = new Dottori();
		Pazienti paziente = new Pazienti();
		boolean t=false;
		Scanner in = new Scanner(System.in);
		int tmp=5;
		do{
			System.out.println("Inserisci urgenza:\n0-nero\n1-marrone\n2-giallo\n3-rosso");
			tmp = Integer.parseInt(in.next());
		}while(tmp!= 0 && tmp!= 1 && tmp!= 2 && tmp!= 3);
		urgenza = Appuntamenti.URGENZE[tmp];
		System.out.println("inserisci la matricola del medico");
		matricola = Integer.parseInt(in.next());
		for(int i=0;i<dottori.size();i++){
			if(dottori.get(i).getMatricola()==matricola){
				dottore = dottori.get(i);
				t=true;
			}
		}
		if(t){
			t=false;
			System.out.println("inserisci codice Fiscale paziente");
			String codice =in.next();
			for(int i=0;i<pazienti.size();i++){
				if(pazienti.get(i).getCodFiscale().equals(codice)){
					paziente = pazienti.get(i);
					t=true;
				}
			}
			if(t){
				t=false;
				Date orat = new Date();
				System.out.println("inserisci giorno della settimana");
				String giorno = in.next();
				System.out.println("Inserisci data inizio appuntamento");
				data = this.creaData();
				System.out.println("inserisci orario inizio appuntamento");
				orat = this.creaOra();
				System.err.println(orat.getHours()+"\n"+orat.getMinutes());
				data .setHours(orat.getHours());
				data.setMinutes(orat.getMinutes());
				Date dataTempo = new Date();
				System.out.println("Inserisci data fine appuntamento");
				oraFine = this.creaData();
				System.out.println("inserisci ora fine appuntamento");
				dataTempo = this.creaOra();
				System.err.println(dataTempo.getHours()+"\n"+dataTempo.getMinutes());
				oraFine.setHours(dataTempo.getHours());
				oraFine.setMinutes(dataTempo.getMinutes());
				if(dottore.isInServizio(giorno, data, oraFine)){
					if(!this.isImpegnato(matricola, data, oraFine)){
						appuntamenti.add(new Appuntamenti(data, oraFine, urgenza, dottore, paziente));
					}else{
						System.out.println("il medico è già impegnato");
					}
				}else{
					System.out.println("il dottore non è in servizio in quella data");
				}
						
			}else{
				System.out.println("paziente non trovato");
			}
			
		}else{
			System.out.println("Medico non trovato");
		}
	}
	
	public boolean isImpegnato(int matricola, Date oraI, Date oraF){
		for(int i=0;i<appuntamenti.size();i++){
			if(appuntamenti.get(i).getMedico().getMatricola() == matricola){
				if(appuntamenti.get(i).getData().after(oraI) && appuntamenti.get(i).getData().before(oraF)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void aggiungiPaziente(){
		String nome,cognome,codFiscale,luogoNascita,gruppoSanguineo;
		Date dataNascita;
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci il cognome del paziente");
		cognome = in.next();
		System.out.println("Inserisci il nome del paziente");
		nome = in.next();
		System.out.println("Inserisci il codice fiscale del paziente");
		codFiscale = in.next();
		System.out.println("Inserisci il gruppo sanguineo del paziente");
		gruppoSanguineo = in.next();
		System.out.println("Inserisci luogo di nascita del paziente");
		luogoNascita = in.next();
		System.out.println("Inserisci data di nascita");
		dataNascita = this.creaData();
		pazienti.add(new Pazienti(nome, cognome, codFiscale, luogoNascita, dataNascita, gruppoSanguineo));
		//in.close()
	}
	
	public void aggiungiDottore(){
		Orari torario;
		String nome,cognome,codFiscale,luogoNascita;
		Date dataNascita;
		int matricola=0;
		Date annoLaurea, annoAssunzione;
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci il cognome del dottore");
		cognome = in.next();
		System.out.println("Inserisci il nome del dottore");
		nome = in.next();
		System.out.println("Inserisci il codice fiscale del dottore");
		codFiscale = in.next();
		System.out.println("Inserisci luogo di nascita del dottore");
		luogoNascita = in.next();
		System.out.println("Inserisci data di nascita");
		dataNascita = this.creaData();
		System.out.println("Inserisci numero matricola");
		/*boolean corretto = false;
		while (!corretto) {
	        System.out.print("Inserisci matricola");
	        if (in.hasNextInt()) {
	            matricola = in.nextInt();
	            corretto=true;
	        } else {
	             // to clear Scanner
	            System.out.println("Inserisci un numero intero per favore");
	            matricola=in.nextInt();
	            System.out.println();
	        }
	    }*/

		matricola = in.nextInt();
		System.out.println("Inserisci data di laurea");
		annoLaurea = this.creaData();
		System.out.println("inserisci data assunzione");
		annoAssunzione = this.creaData();
		torario = this.creaOrario();
		dottori.add(new Dottori(nome, cognome, codFiscale, luogoNascita, dataNascita, matricola, annoLaurea, annoAssunzione, torario));
		//in.close()
	}
	
	public Date creaOra(){
		Scanner in = new Scanner (System.in);
		int ora=0,minuti=0;
		boolean corretto = false;
		while (!corretto) {
	        System.out.print("Inserisci ora ");
	        if (in.hasNextInt()) {
	            ora = in.nextInt();
	            corretto=true;
	            if (ora<0 || ora>24) {
		        	in.nextLine(); // to clear Scanner
		        	System.out.println("Il numero inserito non è valido");
		  	        System.out.println();
		  	        corretto = false;
		        }
	        } else {
	             in.nextLine(); // to clear Scanner
	            System.out.println("Inserisci un numero intero per favore");
	            System.out.println();
	        }

	    }
		corretto = false;
		while (!corretto) {
	        System.out.print("Inserisci minuti");
	        if (in.hasNextInt()) {
	            minuti = in.nextInt();
	            corretto=true;
	            if (minuti<0 || minuti>60) {
		        	in.nextLine(); // to clear Scanner
		        	System.out.println("Il numero inserito non è valido");
		  	        System.out.println();
		  	        corretto = false;
		        }
	        } else {
	             in.nextLine(); // to clear Scanner
	            System.out.println("Inserisci un numero intero per favore");
	            System.out.println();
	        }

	    }
		////in.close()
		Date tmp = new Date();
		tmp.setHours(ora);
		tmp.setMinutes(minuti);
		return tmp;
	}
	
	public Orari creaOrario(){
		Date tmp = new Date();
		Date tmp2 = new Date();
		Orari or = new Orari();
		Scanner in = new Scanner (System.in);
		int ora=0,minuti=0;
		for(int i=0;i<Orari.SETTIMANA.length;i++){
			System.out.println("orario di "+Orari.SETTIMANA[i]);
			boolean corretto = false;
			while (!corretto) {
		        System.out.print("Inserisci ora inizio");
		        if (in.hasNextInt()) {
		            ora = in.nextInt();
		            corretto=true;
		            if (ora<0 || ora>24) {
			        	in.nextLine(); // to clear Scanner
			        	System.out.println("Il numero inserito non è valido");
			  	        System.out.println();
			  	        corretto = false;
			        }
		        } else {
		             in.nextLine(); // to clear Scanner
		            System.out.println("Inserisci un numero intero per favore");
		            System.out.println();
		        }

		    }
			corretto = false;
			while (!corretto) {
		        System.out.print("Inserisci minuti inizio");
		        if (in.hasNextInt()) {
		            minuti = in.nextInt();
		            corretto=true;
		            if (minuti<0 || minuti>60) {
			        	in.nextLine(); // to clear Scanner
			        	System.out.println("Il numero inserito non è valido");
			  	        System.out.println();
			  	        corretto = false;
			        }
		        } else {
		             in.nextLine(); // to clear Scanner
		            System.out.println("Inserisci un numero intero per favore");
		            System.out.println();
		        }

		    }
			tmp.setHours(ora);
			tmp.setMinutes(minuti);
			or.setInizio(tmp, i);
			
			ora=0;minuti=0;
				corretto = false;
				while (!corretto) {
			        System.out.print("Inserisci ora fine");
			        if (in.hasNextInt()) {
			            ora = in.nextInt();
			            corretto=true;
			            if (ora<0 || ora>24) {
				        	in.nextLine(); // to clear Scanner
				        	System.out.println("Il numero inserito non è valido");
				  	        System.out.println();
				  	        corretto = false;
				        }
			        } else {
			             in.nextLine(); // to clear Scanner
			            System.out.println("Inserisci un numero intero per favore");
			            System.out.println();
			        }

			    }
				corretto = false;
				while (!corretto) {
			        System.out.print("Inserisci minuti fine");
			        if (in.hasNextInt()) {
			            minuti = in.nextInt();
			            corretto=true;
			            if (minuti<0 || minuti>60) {
				        	in.nextLine(); // to clear Scanner
				        	System.out.println("Il numero inserito non è valido");
				  	        System.out.println();
				  	        corretto = false;
				        }
			        } else {
			             in.nextLine(); // to clear Scanner
			            System.out.println("Inserisci un numero intero per favore");
			            System.out.println();
			        }

				}
				tmp2.setHours(ora);
				tmp2.setMinutes(minuti);
				or.setFine(tmp2, i);
		}
		//in.close()
		return or;
	}
	
	
	public Date creaData(){
		Scanner in = new Scanner(System.in);
		int anno=0,mese=0,giorno=0;
		boolean corretto = false;
		while (!corretto) {
	        System.out.print("Inserisci giorno");
	        if (in.hasNextInt()) {
	            giorno = in.nextInt();
	            corretto=true;
	            if (giorno<1 || giorno>31) {
		        	in.nextLine(); // to clear Scanner
		        	System.out.println("Il numero inserito non è valido");
		  	        System.out.println();
		  	        corretto = false;
		        }
	        } else {
	             in.nextLine(); // to clear Scanner
	            System.out.println("Inserisci un numero intero per favore");
	            System.out.println();
	        }

	    }
		corretto = false;
		while (!corretto) {
	        System.out.print("Inserisci mese");
	        if (in.hasNextInt()) {
	        	mese = in.nextInt();
	            corretto=true;
	            if (mese<1 || mese>12) {
		        	in.nextLine(); // to clear Scanner
		        	System.out.println("Il numero inserito non è valido");
		  	        System.out.println();
		  	        corretto = false;
		        }
	        } else {
	             in.nextLine(); // to clear Scanner
	            System.out.println("Inserisci un numero intero per favore");
	            System.out.println();
	        }

	    }
		corretto = false;
		while (!corretto) {
	        System.out.print("Inserisci anno");
	        if (in.hasNextInt()) {
	            anno = in.nextInt();
	            corretto=true;
	            if (anno<1900 || anno>2017) {
		        	in.nextLine(); // to clear Scanner
		        	System.out.println("Il numero inserito non è valido");
		  	        System.out.println();
		  	        corretto = false;
		        }
	        } else {
	             in.nextLine(); // to clear Scanner
	            System.out.println("Inserisci un numero intero per favore");
	            System.out.println();
	        }

	    }
		//in.close()
		Date data = new Date(anno,mese,giorno);
		return data;
	}
	
	@Override
	public String stampaMedici() {
		String tmp="";
		for(int i=0;i<dottori.size();i++){
			tmp+=dottori.get(i).toString();
			tmp+="\n";
		}
		if(tmp.equals(""))
			return "nessun medico in memoria";
		else
			return tmp;
	}

	@Override
	public void stampaOrari(int matricola) {
		boolean trovato=false;
		for(int i=0;i<dottori.size();i++){
			if(dottori.get(i).getMatricola()==matricola){
				System.out.println("Orario:");
				System.out.println(dottori.get(i).getOrario().toString());
				trovato = true;
			}
		}
		if(!trovato)
			System.out.println("Medico non trovato");
	}

	@Override
	public Vector<Appuntamenti> stampaAppuntamenti(int matricola) {
		Vector<Appuntamenti> tmp = new Vector<Appuntamenti>();
		for(int i=0;i<appuntamenti.size();i++){
			if(appuntamenti.get(i).getMedico().getMatricola()==matricola){
				tmp.add(appuntamenti.get(i));
			}
		}
		return tmp;
	}

	@Override
	public String stampaPazienti() {
		String tmp="";
		for(int i=0;i<pazienti.size();i++){
			tmp+=pazienti.get(i).toString();
			tmp+="\n";
		}
		if(tmp.equals(""))
			return "nessun paziente in memoria";
		else
			return tmp;
	}

	@Override
	public void stampaAppuntamenti(Date dataInizio, Date dataFine) {
		boolean trovato = false;
		for(int i =0;i<appuntamenti.size();i++){
			if(appuntamenti.get(i).getData().after(dataInizio) && appuntamenti.get(i).getOrafine().before(dataFine)){
				System.out.println(appuntamenti.get(i).toString());
				trovato = true;
			}
		}
		if(!trovato)
			System.out.println("nessun appuntamento trovato");
	}

	@Override
	public String stampaNumAppuntamenti(int matricola) {
		int conta =0;
		boolean trovato = false;
		for(int i=0;i<appuntamenti.size();i++){
			if(appuntamenti.get(i).getMedico().getMatricola()==matricola){
				conta++;
				trovato = true;
			}
		}
		if(!trovato)
			return "medico non trovato";
		return ""+conta;
			
	}

	@Override
	public void getTime() {
		// TODO //Recupera la data attuale (almeno i millisecondi).
		//Dopo la prima esecuzione, stampa in Debug il delay time di chiamata in chiamata, partendo dalla prima volta.

	}

}
