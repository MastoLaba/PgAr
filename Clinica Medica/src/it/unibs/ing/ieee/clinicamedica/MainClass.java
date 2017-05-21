package it.unibs.ing.ieee.clinicamedica;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class MainClass {

	public static void main(String[] args) {
		Clinica clinica = new Clinica();
		boolean esci = false;
		final String[] vociMenu = {"Aggiungi medico" , "Aggiungi paziente",
				"Stampa tutti i pazienti", "Stampare lista pazienti con un appuntamento", "Stampa medici",
				"Stampa orario medico per nome", "Stampa orario medico per anno laurea", "Stampa orario medico per id", 
				"Prenota appuntamento", "Rimuovi appuntamento", "Stampa appuntamenti", "Stampare gli appuntamenti per giorno",
				"Stampa appuntamenti per intervallo di date", "Stampa appuntamento per medico inserito",
				"Stampa numero appuntamenti per medico inserito", "Stampa appuntamenti per paziente in ordine di urgenza", "Stampa tempi" };
		MyMenu menu = new MyMenu("Clinica Medica", vociMenu);
		ArrayList<Integer> log = new ArrayList<>();

		Medico m1 = clinica.addMedico("Carlo", "Ghitti", new GregorianCalendar(1998, 2, 4),
				new GregorianCalendar(2000, 5, 22));
		clinica.addMedico("Franco", "Pelizzari", new GregorianCalendar(1980, 2, 4), new GregorianCalendar(1990, 5, 22));

		clinica.addPaziente("Gianmaria", "Pola");
		Paziente p1 = clinica.addPaziente("Aldo", "Zanolla");

		clinica.addAppuntamento(p1, m1, new GregorianCalendar(1980, 5, 26, 10, 30), 1);
		clinica.addAppuntamento(p1, m1, new GregorianCalendar(1980, 2, 4, 14, 0), 8);

		while (!esci) {
			int casoScelto = menu.scegli();
			log.add(casoScelto);
			log.add(0);
			switch (casoScelto) {
			case 1:
				clinica.getTime();
				clinica.addMedico();
				clinica.getTime();
				break;
			case 2:
				clinica.getTime();
				clinica.addPaziente();
				clinica.getTime();
				break;
			case 3:
				clinica.getTime();
				clinica.stampaPazienti();
				clinica.getTime();
				break;
			case 4:
				clinica.getTime();
				clinica.ordinaPazientiPerNumeroAppuntamenti();
				clinica.getTime();
				break;
			case 5:
				clinica.getTime();
				clinica.stampaMedici();
				clinica.getTime();
				break;
			case 6:
				clinica.getTime();
				clinica.stampaOrari(InputDati.leggiStringaNonVuota("Inserisci il nome del medico: "));
				clinica.getTime();
				break;
			case 7:
				clinica.getTime();
				clinica.stampaOrari(
						new GregorianCalendar(InputDati.leggiInteroNonNegativo("Inserisci l'anno di laurea: "), 0, 0));
				clinica.getTime();
				break;
			case 8:
				clinica.getTime();
				clinica.stampaOrari(InputDati.leggiInteroNonNegativo("Inserisci l'id del medico: "));
				clinica.getTime();
				break;
			case 9:
				clinica.getTime();
				clinica.addAppuntamento();
				clinica.getTime();
				break;
			case 10:
				clinica.getTime();
				clinica.rimuoviAppuntamento();
				clinica.getTime();
				break;
			case 11:
				clinica.getTime();
				clinica.stampaAppuntamenti();
				clinica.getTime();
				break;
			
			case 12:
				clinica.getTime();
				clinica.stampaAppuntamenti(inserisciData(false));
				clinica.getTime();
				break;
			case 13:
				clinica.getTime();
				clinica.stampaAppuntamenti(inserisciData(true), inserisciData(true));
				clinica.getTime();
				break;
			case 14:
				clinica.getTime();
				clinica.stampaAppuntamenti(
						clinica.cercaIdDatoNomeCognome(InputDati.leggiStringaNonVuota("Inserisci il nome del medico: "),
								InputDati.leggiStringaNonVuota("Inserisci il cognome del medico: ")));
				clinica.getTime();
				break;
			case 15:
				clinica.getTime();
				clinica.stampaNumAppuntamenti(clinica.cercaIdDatoNomeCognome(InputDati.leggiStringaNonVuota("Inserisci il nome del medico: "),
						InputDati.leggiStringaNonVuota("Inserisci il cognome del medico: ")));
				clinica.getTime();
				break;
			case 16:
				clinica.getTime();
				clinica.stampaAppuntamentiPazientePerUrgenza(InputDati.leggiInteroNonNegativo("Inserisci l'id del paziente: "));
				clinica.getTime();
				break;
			
			case 17:
				clinica.printTime(log);
				break;
			case 0:
				esci = true;
				break;
			default:
				break;
			}
		}
	}
	
	public static GregorianCalendar inserisciData(boolean ora) {
		int year = InputDati.leggiInteroNonNegativo("Inserisci l'anno: ");
		int month = InputDati.leggiInteroNonNegativo("Inserisci il mese: ");
		int day = InputDati.leggiInteroNonNegativo("Inserisci il giorno: ");
		int hour = 0;
		int min = 0;
		
		if(ora) {
			hour = InputDati.leggiInteroPositivo("Inserisci l'ora: ");
			min = InputDati.leggiInteroPositivo("Inserisci i minuti: ");
		}
		
		return new GregorianCalendar(year, month, day, hour, min);
	}

}
