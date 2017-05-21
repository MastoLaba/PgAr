package deathstar.consegna4;

import java.time.DayOfWeek;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Classe che gestisce l'interfaccia utente del programma.
 * 
 * @author Michele Dusi
 * 
 */
public class Main {
	
	// Menu - Formattazione
	private static final String MENU_VOCE = "%d) %s;";
	private static final String MENU_ESCI = "0) Esci.";
	private static final String MENU_SALVA = "0) Salva ed esci.";
	private static final String MENU_INDIETRO = "0) Indietro.";
	private static final char [] CORNICE_CHAR = {'~', '-', '='};
	private static int CORNICE_ASPETTO = 0;
	private static final int MENU_ESCI_VALUE = 0;
	
	// Inserimenti - Formattazione
	private static final int INS_NULL_VALUE = -1;
	private static final String CURSOR = "-> ";
	
	// Menu Principale Clinica
	private static final String PRINCIPALE_TITOLO = "Menu Principale";
	private static final String [] PRINCIPALE_VOCI = {
			"Area Pazienti", 
			"Area Medici", 
			"Visualizzazione Appuntamenti",
			"Visualizzazione Orari",
			"Impostazioni"};
	private static final String MAIN_USCITA = "Sto uscendo dal programma...";
	private static final String PROGRAMMA_TERMINATO = "Programma terminato.";

	// Menu Area Pazienti
	private static final String AREA_PAZIENTI_TITOLO = "Area Pazienti";
	private static final String [] AREA_PAZIENTI_VOCI = {
			"Cerca un paziente",
			"Visualizza l'elenco dei pazienti",
			"Aggiungi un nuovo paziente",
			"Rimuovi un paziente esistente"};
	private static final String AREA_PAZIENTI_CONFERMA_RIMOZIONE = "Vuoi rimuovere i dati del paziente selezionato dalla clinica?";
	private static final String AREA_PAZIENTI_CONFERMA_AGGIUNTA = "Vuoi confermare l'aggiunta del nuovo paziente al database dalla clinica?";
	private static final String AREA_PAZIENTI_RIFIUTO_AGGIUNTA = "Non è stato aggiunto alcun paziente al database.";
	private static final String AREA_PAZIENTI_RIFIUTO_RIMOZIONE = "Non è stato rimosso alcun paziente dal database.";
	
	// Menu Ricerca Pazienti
	private static final String RICERCA_PAZIENTI_TITOLO = "Ricerca Pazienti";
	private static final String [] RICERCA_PAZIENTI_VOCI = {
			"Cerca un paziente per nome",
			"Cerca un paziente per ID",};
	private static final String RICERCA_PAZIENTI_AVVISO_SELEZIONATO = "Si è selezionato il paziente: ";

	// Menu Gestione Paziente
	private static final String GESTIONE_PAZIENTE_TITOLO = "Gestione Paziente";
	private static final String [] GESTIONE_PAZIENTE_VOCI = {
			"Visualizza gli appuntamenti",
			"Prenota un appuntamento",
			"Modifica i dati personali"};
	
	// Menu Modifica Dati Paziente
	private static final String MODIFICA_DATI_PAZIENTE_TITOLO = "Modifica Dati Paziente "; 
	private static final String [] MODIFICA_DATI_PAZIENTE_VOCI = {
			"Nome:      ",
			"Cognome:   ",
			"Codice:    ",};
	
	// Menu Selezione Codice Urgenza
	private static final String SELEZIONE_CODICE_URGENZA_TITOLO = "Selezione Codice d'Urgenza";
	private static final String [] SELEZIONE_CODICE_URGENZA_VOCI = {
			"Codice ROSSO",
			"Codice GIALLO",
			"Codice NERO",
			"Codice MARRONE"};
	
	// Menu Visualizzazione Pazienti
	private static final String VISUALIZZA_PAZIENTI_TITOLO = "Visualizza Elenco Pazienti";
	private static final String [] VISUALIZZA_PAZIENTI_VOCI = {
			"Visualizza l'elenco completo",
			"Visualizza solo i pazienti con un appuntamento"};

	// Menu Area Medici
	private static final String AREA_MEDICI_TITOLO = "Area Medici";
	private static final String [] AREA_MEDICI_VOCI = {
			"Cerca un medico",
			"Visualizza tutti i medici",
			"Aggiungi un nuovo medico",
			"Rimuovi un medico esistente"};
	private static final String AREA_MEDICI_CONFERMA_AGGIUNTA = "Vuoi aggiungere il medico appena creato alla Clinica?";
	private static final String AREA_MEDICI_CONFERMA_RIMOZIONE = "Vuoi rimuovere i dati del medico selezionato?";
	private static final String AREA_MEDICI_RIFIUTO_AGGIUNTA = "Non è stato aggiunto alcun medico al database.";
	private static final String AREA_MEDICI_RIFIUTO_RIMOZIONE = "Non è stato rimosso alcun medico dal database.";
	
	
	// Menu Ricerca Medici
	private static final String RICERCA_MEDICI_TITOLO = "Ricerca Medici";
	private static final String [] RICERCA_MEDICI_VOCI = {
			"Cerca un medico per ID",
			"Cerca un medico per nome",
			"Cerca un medico per anno di laurea"};
	private static final String RICERCA_MEDICI_AVVISO_SELEZIONATO = "Si è selezionato il medico: ";
	
	// Menu Gestione Medico
	private static final String GESTIONE_MEDICO_TITOLO = "Gestione Medico";
	private static final String [] GESTIONE_MEDICO_VOCI = {
			"Visualizza il numero di appuntamenti",
			"Visualizza l'elenco degli appuntamenti",
			"Verifica la disponibilità oraria",
			"Visualizza gli orari di lavoro",
			"Modifica gli orari di lavoro",
			"Modifica i dati personali"};
	private static final String GESTIONE_MEDICO_NUMERO_APPUNTAMENTI = "Numero di appuntamenti programmati: ";
	private static final String GESTIONE_MEDICO_INSERISCI_DATA = "Inserisci la data per la quale vuoi controllare la disponibilità del medico:";
	private static final String GESTIONE_MEDICO_INSERISCI_ORARIO = "Inserisci l'intervallo di tempo desiderato:";
	private static final String GESTIONE_MEDICO_OCCUPATO = "Il medico è già occupato nell'orario richiesto.";
	private static final String GESTIONE_MEDICO_LIBERO = "Il medico è disponibile nell'orario richiesto.";
	private static final String GESTIONE_MEDICO_DOMANDA_PRENOTAZIONE_APPUNTAMENTO = "Si vuole prenotare un appuntamento con questo medico all'orario selezionato?";
	
	// Menu Gestione Orari Medico
	private static final String GESTIONE_ORARI_TITOLO = "Gestione Orari";
	private static final String [] GESTIONE_ORARI_VOCI = {
			"Aggiungi un orario lavorativo",
			"Libera un giorno lavorativo",
			"Visualizza l'orario"};
	private static final String GESTIONE_ORARI_SELEZIONA_GIORNO_SETTIMANA = "Seleziona il giorno della settimana:";
	private static final String GESTIONE_ORARI_INSERISCI_INTERVALLO_DA_AGGIUNGERE = "Seleziona il giorno della settimana a cui aggiungere un nuovo orario:";
	private static final String GESTIONE_ORARI_SELEZIONA_GIORNO_SETTIMANA_DA_LIBERARE = "Seleziona il giorno da liberare:";
	
	// Menu Giorni Settimana
	private static final String GIORNI_SETTIMANA_TITOLO = "Selezione Giorno Settimanale";
	
	// Menu Modifica Dati Medico
	private static final String MODIFICA_DATI_MEDICO_TITOLO = "Modifica Dati Medico "; 
	private static final String [] MODIFICA_DATI_MEDICO_VOCI = {
			"Nome:             ",
			"Cognome:          ",
			"Anno Laurea:      ",
			"Anno Assunzione:  "};
	
	// Menu Visualizzazione Appuntamenti
	private static final String VISUALIZZA_APPUNTAMENTI_TITOLO = "Visualizzazione Appuntamenti";
	private static final String [] VISUALIZZA_APPUNTAMENTI_VOCI = {
			"Visualizza gli appuntamenti di oggi",
			"Visualizza gli appuntamenti fino a una certa data",
			"Visualizza gli appuntamenti di una data specifica"};
	private static final String VISUALIZZA_APPUNTAMENTI_INSERISCI_DATA_FINALE = "Inserisci la data entro cui cercare gli appuntamenti:";
	private static final String VISUALIZZA_APPUNTAMENTI_INSERISCI_DATA_SPECIFICA = "Inserisci la data per cui cercare gli appuntamenti:";

	// Menu Visualizzazione Orari e Date
	private static final String VISUALIZZA_ORARI_TITOLO = "Visualizzazione Orari";
	private static final String [] VISUALIZZA_ORARI_VOCI = {
			"Visualizza gli orari di tutti i medici",
			"Visualizza gli orari di un medico cercato"};
	private static final String [] VISUALIZZA_ORARI_QUERY_CHARS = {"I-", "N-", "L-"};
	private static final String VISUALIZZA_ORARI_TIPO_RICERCA = "In base a cosa vuoi cercare il medico?";
	
	// Menu Impostazioni
	private static final String IMPOSTAZIONI_TITOLO = "Impostazioni";
	private static final String [] IMPOSTAZIONI_VOCI = {
			"Informazioni sulla Clinica",
			"Modifica aspetto dei menu",
			"Visualizza chiamate getTime() [Modalità Debug]"};
	
	// Menu Modifica Aspetto Menu
	private static final String MODIFICA_ASPETTO_TITOLO = "Modifica Aspetto Menu";
	private static final String [] MODIFICA_ASPETTO_VOCI = {
			"Aspetto [1]",
			"Aspetto [2]",
			"Aspetto [3]"};
	
	// Ricerca Paziente
	private static final String RICERCA_PAZIENTE_INSERISCI_NOME = "Inserisci il nome del paziente cercato:";
	private static final String RICERCA_PAZIENTE_INSERISCI_ID = "Inserisci l'ID del paziente cercato:";
	
	// Selezione Paziente Da Lista
	private static final String SELEZIONE_PAZIENTE_RISULTATI_MULTIPLI = "Attenzione: sono stati trovati più pazienti corrispondenti alle tue richieste:";
	private static final String SELEZIONE_PAZIENTE_TITOLO = "Seleziona il paziente desiderato:";
	
	// Ricerca Medico
	private static final String RICERCA_MEDICO_INSERISCI_NOME = "Inserisci il nome del medico cercato:";
	private static final String RICERCA_MEDICO_INSERISCI_ID = "Inserisci l'ID del medico cercato:";
	private static final String RICERCA_MEDICO_INSERISCI_ANNO_LAUREA = "Inserisci l'anno di laurea del medico cercato:";
	
	// Selezione Medico Da Lista
	private static final String SELEZIONE_MEDICO_RISULTATI_MULTIPLI = "Attenzione: sono stati trovati più medici corrispondenti alle tue richieste:";
	private static final String SELEZIONE_MEDICO_TITOLO = "Seleziona il medico desiderato:";
	
	// Informazioni Clinica
	private static final String INFO_TITOLO = "Programma \"GESTIONE CLINICA\" - Progetto Arnaldo 2017";
	private static final String INFO_NOME = "Clinica \"La Morte Nera\"";
	private static final String INFO_VERSIONE = "Version 1.41 (2017)";
	private static final String INFO_AUTORI = "Author: Michele Dusi, Lorenzo Nodari";
	private static final int PAUSA = 3;
	
	// Errori
	private static final String ERRORE_INSERIMENTO_NUMERICO = "Errore: inserire un valore numerico per selezionare l'opzione desiderata.";
	private static final String ERRORE_INSERIMENTO_NUMERICO_FUORI_ESTREMI = "Errore: inserire un valore numerico compreso fra %d e %d.\n";
	private static final String ERRORE_INSERIMENTO_INTERVALLO_NEGATIVO = "Non è stato possibile utilizzare l'intervallo inserito poiché l'orario di inizio era successivo all'orario di fine.\nSi prega di riprovare.";
	private static final String ERRORE_PAZIENTE_NON_TROVATO = "Non è stato trovato alcun paziente corrispondente.";
	private static final String ERRORE_PAZIENTE_LISTA_VUOTA = "Non sono presenti pazienti";
	private static final String ERRORE_MEDICO_NON_TROVATO = "Non è stato trovato alcun medico corrispondente.";
	private static final String ERRORE_LISTA_APPUNTAMENTI_VUOTA = "Non sono presenti appuntamenti.";
	private static final String ERRORE_LISTA_APPUNTAMENTI_NULL = "Non sono stati trovati appuntamenti.";
	private static final String ERRORE_APPUNTAMENTO_NON_DISPONIBILE = "Non è stato possibile completare la prenotazione dell'appuntamento.\nControllare la disponibilità di medico e paziente per l'orario selezionato.";
	private static final String ERRORE_APPUNTAMENTO_NON_FISSATO = "Non è stato possibile completare la prenotazione dell'appuntamento.";
	private static final String ERRORE_VISUALIZZA_ORARI_VUOTI = "Non sono presenti orari da visualizzare";

	// Creazione Paziente
	private static final String CREA_PAZIENTE_INSERIMENTO_NOME = "Inserisci il nome:";
	private static final String CREA_PAZIENTE_INSERIMENTO_COGNOME = "Inserisci il cognome:";
	
	// Creazione Medico
	private static final String CREA_MEDICO_INSERIMENTO_NOME = "Inserisci il nome:";
	private static final String CREA_MEDICO_INSERIMENTO_COGNOME = "Inserisci il cognome:";
	private static final String CREA_MEDICO_INSERIMENTO_ANNO_LAUREA = "Inserisci l'anno di laurea:";
	private static final String CREA_MEDICO_INSERIMENTO_ANNO_ASSUNZIONE = "Inserisci l'anno di assunzione alla clinica:";
	private static final String CREA_MEDICO_INSERIMENTO_ORARIO = "Definisci gli orari di lavoro del nuovo medico:";
	
	// Creazione Appuntamento
	private static final String CREAZIONE_APPUNTAMENTO_INSERIMENTO_DATA = "Inserisci la data dell'appuntamento [DD-MM-YYYY]";
	private static final String CREAZIONE_APPUNTAMENTO_INSERIMENTO_ORARIO = "Definisci l'orario dell'appuntamento";
	private static final String CREAZIONE_APPUNTAMENTO_SELEZIONE_URGENZA = "Seleziona il codice d'urgenza dell'appuntamento:";
	
	// Inserimento Data
	private static final int MIN_ANNO_POSSIBILE = 1800;
	private static final int MAX_ANNO_POSSIBILE = 2199;
	
	// Inserimento Data
	private static final String INSERIMENTO_INTERVALLO_INIZIO = "Inserisci l'ora di inizio [HH:MM]";
	private static final String INSERIMENTO_INTERVALLO_FINE = "Inserisci l'ora di fine [HH:MM]";
	
	// Prenotazione Appuntamento
	private static final String PRENOTAZIONE_APPUNTAMENTO_SELEZIONE_PAZIENTE = "Seleziona il paziente:";
	private static final String PRENOTAZIONE_APPUNTAMENTO_SELEZIONE_MEDICO = "Seleziona il medico con cui fissare l'appuntamento:";
	
	// Inserimento Stringhe
	private static final String MODIFICA_DATI_INSERISCI_NOME = "Inserisci il nuovo nome:";
	private static final String MODIFICA_DATI_INSERISCI_COGNOME = "Inserisci il nuovo cognome:";
	private static final String MODIFICA_DATI_INSERISCI_ANNO_LAUREA = "Inserisci l'anno di laurea:";
	private static final String MODIFICA_DATI_INSERISCI_ANNO_ASSUNZIONE = "Inserisci l'anno di assunzione:";
	
	// Clinica
	private static Clinica clinica;
	
	public static void main (String [] args) {
		// Sistemazione scanner
		InputDati.READER.useDelimiter(System.getProperty("line.separator"));
		// Inizializzo la clinica
		clinica = new Clinica();
		// Stampo informazioni iniziali
		stampaInfoIniziali();
		// Menu Principale
		menuPrincipale();
		System.out.println(PROGRAMMA_TERMINATO);
	}
	
	private static void menuPrincipale() {
		int scelta;
		do {
			scelta = scegliDaMenu(PRINCIPALE_TITOLO, PRINCIPALE_VOCI, MENU_ESCI);
			switch(scelta) {
			case 1:
				// Menu pazienti
				menuAreaPazienti();
				break;
			case 2:
				// Menu medici
				menuAreaMedici();
				break;
			case 3:
				// Visualizzazione appuntamenti
				menuVisualizzaAppuntamenti();
				break;
			case 4:
				// Visualizzazione orari
				menuVisualizzaOrari();
				break;
			case 5:
				// Impostazioni
				menuImpostazioniProgramma();
				break;
			case 0:
				// Uscita
				System.out.println(MAIN_USCITA);
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	

	private static void menuAreaPazienti() {
		int scelta;
		do {
			scelta = scegliDaMenu(AREA_PAZIENTI_TITOLO, AREA_PAZIENTI_VOCI);
			switch(scelta) {
			case 1:
				// Ricerca paziente
				Paziente paziente = menuRicercaPaziente();
				if (paziente == null)
					System.out.println(ERRORE_PAZIENTE_NON_TROVATO);
				else
					menuGestionePaziente(paziente);
				break;
			case 2:
				// Visualizzazione pazienti
				menuVisualizzaPazienti();
				break;
			case 3:
				// Aggiunta pazienti
				Paziente nuovo_paziente = creaPaziente();
				if (nuovo_paziente != null && InputDati.domandaYesOrNo(AREA_PAZIENTI_CONFERMA_AGGIUNTA))
					clinica.aggiungiPaziente(nuovo_paziente);
				else
					System.out.println(AREA_PAZIENTI_RIFIUTO_AGGIUNTA);
				break;
			case 4:
				// Rimozione pazienti
				Paziente paziente_da_rimuovere = menuRicercaPaziente();
				if (paziente_da_rimuovere != null && InputDati.domandaYesOrNo(AREA_PAZIENTI_CONFERMA_RIMOZIONE))
					clinica.rimuoviPaziente(paziente_da_rimuovere);
				else
					System.out.println(AREA_PAZIENTI_RIFIUTO_RIMOZIONE);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static Paziente menuRicercaPaziente() {
		// Questo menu è leggermente diverso, non deve essere ripetuto ma vive solo in funzione di "Area Pazienti -> Ricerca Paziente"
		int scelta = scegliDaMenu(RICERCA_PAZIENTI_TITOLO, RICERCA_PAZIENTI_VOCI);
		Paziente paziente_trovato = null;
		switch(scelta) {
		case 1:
			// Ricerca paziente per nome
			paziente_trovato = ricercaPazientePerNome();
			break;
		case 2:
			// Ricerca paziente per ID
			paziente_trovato = ricercaPazientePerId();
			break;
		}
		if (paziente_trovato != null)
			System.out.println(RICERCA_PAZIENTI_AVVISO_SELEZIONATO + paziente_trovato.toString());
		return paziente_trovato;
	}
	
	private static void menuGestionePaziente(Paziente paziente) {
		int scelta;
		do {
			scelta = scegliDaMenu(GESTIONE_PAZIENTE_TITOLO, GESTIONE_PAZIENTE_VOCI);
			switch(scelta) {
			case 1:
				// Visualizza appuntamenti
				stampaAVideoStringhe(clinica.stampaAppuntamentiPaziente(paziente.getId()), ERRORE_LISTA_APPUNTAMENTI_VUOTA);
				break;
			case 2:
				// Prenota appuntamento
				try {
					fissaAppuntamento(paziente, null, null, null, INS_NULL_VALUE);
				} catch (IllegalArgumentException e) {
					System.out.println(ERRORE_APPUNTAMENTO_NON_DISPONIBILE);
				}
				break;
			case 3:
				// Modifica dati paziente
				menuModificaDatiPaziente(paziente);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static void menuModificaDatiPaziente(Paziente paziente) {
		// Creazione delle opzioni menu personalizzate:
		int scelta;
		do {
			String [] voci_personalizzate = {
					MODIFICA_DATI_PAZIENTE_VOCI[0] + paziente.getNome(),
					MODIFICA_DATI_PAZIENTE_VOCI[1] + paziente.getCognome(),
					MODIFICA_DATI_PAZIENTE_VOCI[2] + paziente.getCondizione()};
			scelta = scegliDaMenu(MODIFICA_DATI_PAZIENTE_TITOLO + paziente.getId(), voci_personalizzate, MENU_SALVA);
			switch(scelta) {
			case 1:
				// Modifica Nome
				System.out.println(MODIFICA_DATI_INSERISCI_NOME);
				paziente.setNome(InputDati.leggiStringaNonVuota());
				break;
			case 2:
				// Modifica Cognome
				System.out.println(MODIFICA_DATI_INSERISCI_COGNOME);
				paziente.setCognome(InputDati.leggiStringaNonVuota());
				break;
			case 3:
				// Modifica condizione
				paziente.setCondizione(menuSelezioneCodiceUrgenza(paziente.getCondizione()));
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static int menuSelezioneCodiceUrgenza(int codice_default) {
		int scelta = scegliDaMenu(SELEZIONE_CODICE_URGENZA_TITOLO, SELEZIONE_CODICE_URGENZA_VOCI);
		switch(scelta) {
		case 1:
			// Codice ROSSO
			return Paziente.CODICE_ROSSO;
		case 2:
			// Codice GIALLO
			return Paziente.CODICE_GIALLO;
		case 3:
			// Codice NERO
			return Paziente.CODICE_NERO;
		case 4:
			// Codice MARRONE
			return Paziente.CODICE_MARRONE;
		default:
			return codice_default; // Questo valore verrà ritornato solo in caso si esca dal menu
			// E' un modo per evitare di perdere il valore precedente di Urgenza in caso l'utente sbagli a selezionare il menu
		}
	}
	
	private static void menuVisualizzaPazienti() {
		int scelta;
		do {
			scelta = scegliDaMenu(VISUALIZZA_PAZIENTI_TITOLO, VISUALIZZA_PAZIENTI_VOCI);
			switch(scelta) {
			case 1:
				// Visualizza tutti
				String lista_pazienti = clinica.stampaPazienti();
				if (lista_pazienti.length() == 0)
					System.out.println(ERRORE_PAZIENTE_LISTA_VUOTA);
				else
					System.out.println(clinica.stampaPazienti());
				break;
			case 2:
				// Visualizza pazienti con appuntamenti
				stampaAVideoStringhe(clinica.stampaPazientiConAppuntamento(), ERRORE_LISTA_APPUNTAMENTI_NULL);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static void menuAreaMedici() {
		int scelta;
		do {
			scelta = scegliDaMenu(AREA_MEDICI_TITOLO, AREA_MEDICI_VOCI);
			switch(scelta) {
			case 1:
				// Ricerca medico
				Medico medico = menuRicercaMedico();
				if (medico == null)
					System.out.println(ERRORE_MEDICO_NON_TROVATO);
				else
					menuGestioneMedico(medico);
				break;
			case 2:
				// Visualizzazione medici
				System.out.println(clinica.stampaMedici());
				break;
			case 3:
				// Aggiunta medico
				Medico nuovo_medico = creaMedico();
				if (nuovo_medico != null && InputDati.domandaYesOrNo(AREA_MEDICI_CONFERMA_AGGIUNTA))
					clinica.aggiungiMedico(nuovo_medico);
				else
					System.out.println(AREA_MEDICI_RIFIUTO_AGGIUNTA);
				break;
			case 4:
				// Rimozione medico
				Medico medico_da_rimuovere = menuRicercaMedico();
				if (medico_da_rimuovere != null && InputDati.domandaYesOrNo(AREA_MEDICI_CONFERMA_RIMOZIONE))
					clinica.rimuoviMedico(medico_da_rimuovere);
				else
					System.out.println(AREA_MEDICI_RIFIUTO_RIMOZIONE);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static Medico menuRicercaMedico() {
		// Questo menu è leggermente diverso, non deve essere ripetuto ma vive solo in funzione di "Area Medici -> Ricerca Medico"
		int scelta = scegliDaMenu(RICERCA_MEDICI_TITOLO, RICERCA_MEDICI_VOCI);
		Medico medico_trovato = null;
		switch(scelta) {
		case 1:
			// Ricerca medico per ID
			medico_trovato = ricercaMedicoPerId();
			break;
		case 2:
			// Ricerca medico per nome
			medico_trovato = ricercaMedicoPerNome();
			break;
		case 3:
			// Ricerca medico per anno di laurea
			medico_trovato = ricercaMedicoPerAnnoDiLaurea();
			break;
		}
		if (medico_trovato != null)
			System.out.println(RICERCA_MEDICI_AVVISO_SELEZIONATO + medico_trovato.toStringCorto());
		return medico_trovato;
	}
	
	private static void menuGestioneMedico(Medico medico) {
		int scelta;
		do {
			scelta = scegliDaMenu(GESTIONE_MEDICO_TITOLO, GESTIONE_MEDICO_VOCI);
			switch(scelta) {
			case 1:
				// Visualizza numero appuntamenti
				System.out.print(GESTIONE_MEDICO_NUMERO_APPUNTAMENTI);
				System.out.println(clinica.stampaNumAppuntamenti(medico.getId()));
				break;
			case 2:
				// Visualizza appuntamenti
				stampaAVideoStringhe(clinica.stampaAppuntamenti(medico.getId()), ERRORE_LISTA_APPUNTAMENTI_VUOTA);
				break;
			case 3:
				// Verifica disponibilità oraria
				System.out.println(GESTIONE_MEDICO_INSERISCI_DATA);
				GregorianCalendar giorno = InputDati.inserisciData();
				System.out.println(GESTIONE_MEDICO_INSERISCI_ORARIO);
				Intervallo intervallo = inserisciIntervallo();
				if (medico.liberoPerAppuntamento(giorno, intervallo)) {
					System.out.println(GESTIONE_MEDICO_LIBERO);
					if (InputDati.domandaYesOrNo(GESTIONE_MEDICO_DOMANDA_PRENOTAZIONE_APPUNTAMENTO)) {
						try {
							fissaAppuntamento(null, medico, giorno, intervallo, INS_NULL_VALUE);
						} catch (IllegalArgumentException e) {
							System.out.println(ERRORE_APPUNTAMENTO_NON_DISPONIBILE);
						}
					}
				} else
					System.out.println(GESTIONE_MEDICO_OCCUPATO);
				break;
			case 4:
				// Visualizza orari
				System.out.println(medico.getOrario().toString());
				break;
			case 5:
				// Gestisci orari
				menuGestioneOrari(medico.getOrario());
				break;
			case 6:
				// Modifica dati medico
				menuModificaDatiMedico(medico);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static void menuGestioneOrari(OrarioLavorativo orario) {
		int scelta;
		do {
			scelta = scegliDaMenu(GESTIONE_ORARI_TITOLO, GESTIONE_ORARI_VOCI);
			switch(scelta) {
			case 1:
				// Aggiungi orario lavorativo
				System.out.println(GESTIONE_ORARI_SELEZIONA_GIORNO_SETTIMANA);
				DayOfWeek dow1 = menuGiorniSettimana();
				System.out.println(GESTIONE_ORARI_INSERISCI_INTERVALLO_DA_AGGIUNGERE);
				Intervallo da_aggiungere = inserisciIntervallo();
				orario.unisciIntervallo(dow1, da_aggiungere);
				break;
			case 2:
				// Libera giorno lavorativo
				System.out.println(GESTIONE_ORARI_SELEZIONA_GIORNO_SETTIMANA_DA_LIBERARE);
				orario.liberaGiorno(menuGiorniSettimana());
				break;
			case 3:
				// Visualizza orario
				System.out.println(orario.toString());
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static DayOfWeek menuGiorniSettimana() {
		int scelta = scegliDaMenu(GIORNI_SETTIMANA_TITOLO, OrarioLavorativo.GIORNI_SETTIMANA);
		return DayOfWeek.of(scelta);
	}
	
	private static void menuModificaDatiMedico(Medico medico) {
		// Creazione delle opzioni menu personalizzate:
		int scelta;
		do {
			String [] voci_personalizzate = {
					MODIFICA_DATI_MEDICO_VOCI[0] + medico.getNome(),
					MODIFICA_DATI_MEDICO_VOCI[1] + medico.getCognome(),
					MODIFICA_DATI_MEDICO_VOCI[2] + Integer.toString(medico.getAnnoLaurea()),
					MODIFICA_DATI_MEDICO_VOCI[3] + Integer.toString(medico.getAnnoAssunzione())};
			scelta = scegliDaMenu(MODIFICA_DATI_MEDICO_TITOLO + medico.getId(), voci_personalizzate, MENU_SALVA);
			switch(scelta) {
			case 1:
				// Modifica Nome
				System.out.println(MODIFICA_DATI_INSERISCI_NOME);
				medico.setNome(InputDati.leggiStringaNonVuota());
				break;
			case 2:
				// Modifica Cognome
				System.out.println(MODIFICA_DATI_INSERISCI_COGNOME);
				medico.setCognome(InputDati.leggiStringaNonVuota());
				break;
			case 3:
				// Modifica Anno laurea
				System.out.println(MODIFICA_DATI_INSERISCI_ANNO_LAUREA);
				medico.setAnnoLaurea(InputDati.leggiInteroFra(MIN_ANNO_POSSIBILE, MAX_ANNO_POSSIBILE));
				break;
			case 4:
				// Modifica Anno assunzione
				System.out.println(MODIFICA_DATI_INSERISCI_ANNO_ASSUNZIONE);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}

	private static void menuVisualizzaAppuntamenti() {
		int scelta;
		List<String> lista_appuntamenti = null;
		do {
			scelta = scegliDaMenu(VISUALIZZA_APPUNTAMENTI_TITOLO, VISUALIZZA_APPUNTAMENTI_VOCI);
			switch(scelta) {
			case 1:
				// Visualizza Appuntamenti di oggi
				GregorianCalendar data_odierna = new GregorianCalendar();
				lista_appuntamenti = clinica.stampaAppuntamenti(data_odierna, data_odierna);
				break;
			case 2:
				// Visualizza Appuntamenti fino ad una certa data
				System.out.println(VISUALIZZA_APPUNTAMENTI_INSERISCI_DATA_FINALE);
				GregorianCalendar data_fissata = InputDati.inserisciData();
				lista_appuntamenti = clinica.stampaAppuntamenti(new GregorianCalendar(), data_fissata);
				break;
			case 3:
				// Visualizza Appuntamenti in una certa data
				System.out.println(VISUALIZZA_APPUNTAMENTI_INSERISCI_DATA_SPECIFICA);
				GregorianCalendar data_richiesta = InputDati.inserisciData();
				lista_appuntamenti = clinica.stampaAppuntamenti(data_richiesta, data_richiesta);
				break;
			}
			if (scelta != MENU_ESCI_VALUE) {
				if (lista_appuntamenti == null) 
					System.out.println(ERRORE_LISTA_APPUNTAMENTI_NULL);
				else
					stampaAVideoStringhe(lista_appuntamenti, ERRORE_LISTA_APPUNTAMENTI_VUOTA);
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static void menuVisualizzaOrari() {
		int scelta;
		do {
			scelta = scegliDaMenu(VISUALIZZA_ORARI_TITOLO, VISUALIZZA_ORARI_VOCI);
			switch(scelta) {
			case 1:
				// Visualizza tutti gli orari
				stampaAVideoStringhe(clinica.stampaTuttiGliOrari(), ERRORE_VISUALIZZA_ORARI_VUOTI);
				break;
			case 2:
				// Visualizza l'orario di un medico specifico
				System.out.println(VISUALIZZA_ORARI_TIPO_RICERCA);
				// Stampo il menu
				int scelta_ricerca = scegliDaMenu(RICERCA_MEDICI_TITOLO, RICERCA_MEDICI_VOCI);
				// Stampo il messaggio per l'utente
				String [] messaggi_inserisci_dati = {RICERCA_MEDICO_INSERISCI_ID, RICERCA_MEDICO_INSERISCI_NOME, RICERCA_MEDICO_INSERISCI_ANNO_LAUREA};
				System.out.println(messaggi_inserisci_dati[scelta_ricerca - 1]);
				// Acquisisco i dati da tastiera e restituisco gli orari
				String query = VISUALIZZA_ORARI_QUERY_CHARS[scelta_ricerca - 1] + InputDati.leggiStringaNonVuota();
				stampaAVideoStringhe(clinica.stampaOrari(query), ERRORE_VISUALIZZA_ORARI_VUOTI);
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static void menuImpostazioniProgramma() {
		int scelta;
		do {
			scelta = scegliDaMenu(IMPOSTAZIONI_TITOLO, IMPOSTAZIONI_VOCI);
			switch(scelta) {
			case 1:
				// Informazioni Clinica
				stampaInfoClinica();
				break;
			case 2:
				// Modifica Aspetto
				menuModificaAspetto();
				break;
			case 3:
				// Visualizza Data e intervallo di tempo
				clinica.getTime();
				break;
			}
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	private static void menuModificaAspetto() {
		int scelta;
		do {
			scelta = scegliDaMenu(MODIFICA_ASPETTO_TITOLO, MODIFICA_ASPETTO_VOCI, MENU_SALVA);
			if (scelta != MENU_ESCI_VALUE)
				CORNICE_ASPETTO = scelta - 1;
		} while (scelta != MENU_ESCI_VALUE);
	}
	
	// Metodi di Ricerca

	private static Paziente ricercaPazientePerNome() {
		System.out.println(RICERCA_PAZIENTE_INSERISCI_NOME);
		String nome = InputDati.leggiStringaNonVuota();
		return selezionaPazienteDaLista(clinica.getPazientiDaNome(nome));
	}
	
	private static Paziente ricercaPazientePerId() {
		System.out.println(RICERCA_PAZIENTE_INSERISCI_ID);
		return clinica.getPazienteDaId(InputDati.leggiIntero());
	}
	
	private static Paziente selezionaPazienteDaLista(List<Paziente> lista) {
		if (lista.size() == 0)
			return null;
		else if (lista.size() == 1)
			return lista.get(0);
		else {
			System.out.println(SELEZIONE_PAZIENTE_RISULTATI_MULTIPLI);
			String [] voci_provvisorie = new String[lista.size()];
			for (int i = 0; i < voci_provvisorie.length; i++)
				voci_provvisorie[i] = lista.get(i).toString();
			int index_paziente_scelto = scegliDaMenu(SELEZIONE_PAZIENTE_TITOLO, voci_provvisorie); // Creo un menu provvisorio da cui scegliere
			return lista.get(index_paziente_scelto - 1);
		}
	}

	private static Medico ricercaMedicoPerNome() {
		System.out.println(RICERCA_MEDICO_INSERISCI_NOME);
		String nome = InputDati.leggiStringaNonVuota();
		return selezionaMedicoDaLista(clinica.getMediciDaNome(nome));
	}

	private static Medico ricercaMedicoPerId() {
		System.out.println(RICERCA_MEDICO_INSERISCI_ID);
		return clinica.getMedicoDaId(InputDati.leggiIntero());
	}
	
	private static Medico ricercaMedicoPerAnnoDiLaurea() {
		System.out.println(RICERCA_MEDICO_INSERISCI_ANNO_LAUREA);
		int anno_laurea = InputDati.leggiInteroFra(MIN_ANNO_POSSIBILE, MAX_ANNO_POSSIBILE);
		return selezionaMedicoDaLista(clinica.getMediciDaAnnoLaurea(anno_laurea));
	}
	
	private static Medico selezionaMedicoDaLista(List<Medico> lista) {
		if (lista.size() == 0)
			return null;
		else if (lista.size() == 1)
			return lista.get(0);
		else {
			System.out.println(SELEZIONE_MEDICO_RISULTATI_MULTIPLI);
			String [] voci_provvisorie = new String[lista.size()];
			for (int i = 0; i < voci_provvisorie.length; i++)
				voci_provvisorie[i] = lista.get(i).toStringCorto();
			int index_medico_scelto = scegliDaMenu(SELEZIONE_MEDICO_TITOLO, voci_provvisorie);
			return lista.get(index_medico_scelto - 1);
		}
	}
	
	// Metodi per l'inserimento o la creazione dei dati struttrati

	private static Paziente creaPaziente() {
		System.out.println(CREA_PAZIENTE_INSERIMENTO_NOME);
		String nome = InputDati.leggiStringaNonVuota();
		System.out.println(CREA_PAZIENTE_INSERIMENTO_COGNOME);
		String cognome = InputDati.leggiStringaNonVuota();
		int codice = menuSelezioneCodiceUrgenza(Paziente.CODICE_MARRONE);
		return new Paziente(nome, cognome, codice);
	}
	
	private static Medico creaMedico() {
		System.out.println(CREA_MEDICO_INSERIMENTO_NOME);
		String nome = InputDati.leggiStringaNonVuota();
		System.out.println(CREA_MEDICO_INSERIMENTO_COGNOME);
		String cognome = InputDati.leggiStringaNonVuota();
		System.out.println(CREA_MEDICO_INSERIMENTO_ANNO_LAUREA);
		int anno_laurea = InputDati.leggiInteroFra(MIN_ANNO_POSSIBILE, MAX_ANNO_POSSIBILE);
		System.out.println(CREA_MEDICO_INSERIMENTO_ANNO_ASSUNZIONE);
		int anno_assunzione = InputDati.leggiInteroFra(MIN_ANNO_POSSIBILE, MAX_ANNO_POSSIBILE);
		System.out.println(CREA_MEDICO_INSERIMENTO_ORARIO);
		OrarioLavorativo orario = creaOrarioLavorativo();
		return new Medico(nome, cognome, anno_laurea, anno_assunzione, orario);
	}
	
	private static OrarioLavorativo creaOrarioLavorativo() {
		OrarioLavorativo orario = new OrarioLavorativo();
		for (int i = 0; i < OrarioLavorativo.GIORNI_SETTIMANA.length; i++) {
			if (InputDati.domandaYesOrNo(String.format("Il medico lavora di %s?", OrarioLavorativo.GIORNI_SETTIMANA[i]))) {
				Intervallo nuovo_intervallo = inserisciIntervallo();
				if (nuovo_intervallo != null)
					orario.unisciIntervallo(DayOfWeek.of(i + 1), nuovo_intervallo);
			}
		}
		return orario;
	}
	
	private static Appuntamento creaAppuntamento(GregorianCalendar giorno, Intervallo orario, int codice_urgenza) {
		if (giorno == null) {
			System.out.println(CREAZIONE_APPUNTAMENTO_INSERIMENTO_DATA);
			giorno = InputDati.inserisciData();
			if (giorno == null) // Se ho interrotto l'operazione prima del termine
				return null; // Interrompo la creazione dell'appuntamento
		}
		if (orario == null) {
			System.out.println(CREAZIONE_APPUNTAMENTO_INSERIMENTO_ORARIO);
			orario = inserisciIntervallo();
			if (orario == null) // Se ho interrotto l'operazione prima di completarla
				return null; // Termino la creazione dell'appuntamento
		}
		if (codice_urgenza == INS_NULL_VALUE) {
			System.out.println(CREAZIONE_APPUNTAMENTO_SELEZIONE_URGENZA);
			codice_urgenza = menuSelezioneCodiceUrgenza(Paziente.CODICE_MARRONE);
		}
		return new Appuntamento(giorno, orario, codice_urgenza);
	}
	
	private static Intervallo inserisciIntervallo() {
		// [HH:MM] oppure [HH.MM]
		while(true) {
			System.out.println(INSERIMENTO_INTERVALLO_INIZIO);
			int t_inizio = InputDati.inserisciIstante();
			System.out.println(INSERIMENTO_INTERVALLO_FINE);
			int t_fine = InputDati.inserisciIstante();
			if (t_inizio < t_fine)
				return new Intervallo(t_inizio, t_fine);
			else
				System.out.println(ERRORE_INSERIMENTO_INTERVALLO_NEGATIVO);
		}
	}
	
	private static void fissaAppuntamento(Paziente paziente, Medico medico, GregorianCalendar giorno, Intervallo orario, int codice_urgenza) throws IllegalArgumentException {
		if (paziente == null) {
			System.out.println(PRENOTAZIONE_APPUNTAMENTO_SELEZIONE_PAZIENTE);
			paziente = menuRicercaPaziente();
			if (paziente == null) // Se interrompo la ricerca del paziente essa restituisce null
				throw new IllegalArgumentException(); // A questo punto termino la creazione dell'appuntamento
		}
		if (medico == null) {
			System.out.println(PRENOTAZIONE_APPUNTAMENTO_SELEZIONE_MEDICO);
			medico = menuRicercaMedico();
			if (medico == null)
				throw new IllegalArgumentException();
		}
		Appuntamento appuntamento = creaAppuntamento(giorno, orario, codice_urgenza);
		try {
			// Devono essere liberi sia il medico che il paziente
			if (!medico.liberoPerAppuntamento(appuntamento.getGiorno(), appuntamento.getIntervallo()))
				throw new IllegalArgumentException();
			else if (!paziente.liberoPerAppuntamento(appuntamento.getGiorno(), appuntamento.getIntervallo()))
				throw new IllegalArgumentException();
			else {
				medico.fissaAppuntamento(appuntamento, paziente);
				paziente.fissaAppuntamento(appuntamento, medico);
			}
		}
		catch (NullPointerException e) {
			// Succede agli inizi del programma, quando non ho medici o non ho pazienti.
			System.out.println(ERRORE_APPUNTAMENTO_NON_FISSATO);
		}
	}
	
	// Metodi per la visualizzazione dei dati
	
	private static void stampaAVideoStringhe(List<String> lista, String errore_vuota) {
		if (lista.size() > 0)
			for (String stringa : lista) {
				System.out.println(stringa);
			}
		else
			System.out.println(errore_vuota);
	}
	
	private static void stampaInfoIniziali() {
		stampaCornice(INFO_TITOLO.length());
		System.out.println(INFO_TITOLO);
		System.out.println(INFO_AUTORI);
		stampaCornice(INFO_TITOLO.length());
	}
	
	private static void stampaInfoClinica() {
		// ROBE
		System.out.println(INFO_NOME);
		System.out.println(INFO_VERSIONE);
		System.out.println(INFO_AUTORI);
		try {
			TimeUnit.SECONDS.sleep(PAUSA);
		} catch (InterruptedException e) {
			e.printStackTrace();
			// In realtà non dovrebbe verificarsi
		}
	}
	
	// Metodi per la visualizzazione dei Menu
	
	private static int scegliDaMenu(String titolo, String [] voci) {
		return scegliDaMenu(titolo, voci, MENU_INDIETRO); // OPZIONE DI DEFAULT
	}
	
	private static int scegliDaMenu(String titolo, String [] voci, String uscita) {
		System.out.println();
		// Visualizzazione titolo del menu
		stampaCornice(titolo.length());
		System.out.println(titolo);
		stampaCornice(titolo.length());
		// Visualizzazione voci del menu
		for (int i = 0; i < voci.length; i++)
			System.out.println(String.format(MENU_VOCE, i + 1, voci[i]));
		System.out.println(uscita);
		stampaCornice(titolo.length());
		
		// Inserimento scelta
		return InputDati.leggiInteroFra(MENU_ESCI_VALUE, voci.length);
	}
	
	private static void stampaCornice(int n) {
		for (int i = 0; i < n; i++)
			System.out.print(CORNICE_CHAR[CORNICE_ASPETTO]);
		System.out.println();
	}
	
	private static class InputDati {

		private static final String YES = "y";
		private static final String NO = "n";
		private static final String YN_FORMAT = " [Y/N]";
		private static final String REGEX_ORARIO = "([01][0-9]|2[0-3])((:|.)[0-5][0-9])?";
		private static final String REGEX_DATA = "(0[1-9]|[12][0-9]|3[01])(/|-|.)(0[1-9]|1[012])(/|-|.)((18|19|20|21)\\d\\d)";
		
		public static final Scanner READER = new Scanner(System.in); // Scanner
		
		public static int leggiIntero() {
			int input = INS_NULL_VALUE;
			boolean intero_trovato = false;
			do {
				System.out.print(CURSOR);
				try {
					input = READER.nextInt();
					intero_trovato = true;
				} catch (InputMismatchException e) {
					System.out.println(ERRORE_INSERIMENTO_NUMERICO);
					READER.next();
				}
			} while (!intero_trovato);
			return input;
		}
		
		public static int leggiInteroFra(int minimo, int massimo) {
			int input = INS_NULL_VALUE;
			boolean trovato = false;
			do {
				input = InputDati.leggiIntero();
				if (input < minimo || input > massimo)
					System.out.printf(ERRORE_INSERIMENTO_NUMERICO_FUORI_ESTREMI, minimo, massimo);
				else
					trovato = true;
			} while (!trovato);
			return input;
		}
		
		public static String leggiStringaNonVuota() {
			String input = null;
			do {
				System.out.print(CURSOR);
				input = READER.next().trim();
			} while (input.length() == 0);
			return input.trim();
		}
		
		public static int inserisciIstante() {
			// Legge istanti con formato HH:MM o HH.MM
			// Restituisce interi con formato HHMM
			String input = leggiStringaConRegEx(REGEX_ORARIO);
			// Formazione dell'intero
			int hh = Integer.parseInt(input.substring(0, 2));
			int mm = 0;
			if (input.length() > 2)
				mm = Integer.parseInt(input.substring(3, 5));				
			return hh * Intervallo.HCOEFF + mm;
		}
		
		public static GregorianCalendar inserisciData() {
			// Legge una data con formato DD-MM-YYYY oppure DD/MM/YYYY oppure DD.MM.YYYY
			// Restituisce una data come oggetto GregorianCalendar
			GregorianCalendar data = new GregorianCalendar();
			boolean date_is_valid = true;
			do {
				String input = leggiStringaConRegEx(REGEX_DATA);
				int dd = Integer.parseInt(input.substring(0, 2));
				int mm = Integer.parseInt(input.substring(3, 5));
				int yyyy = Integer.parseInt(input.substring(6, 10));			
				// Formazione dell'oggetto data
				data.setLenient(false);
				data.set(yyyy, mm - 1, dd);
				try {
					data.getTime();
				} catch (Exception e) {
					date_is_valid = false;
				}	
			} while (!date_is_valid);
			return data;
		}
		
		private static String leggiStringaConRegEx(String regex) {
			String input = null;
			do {
				System.out.print(CURSOR);
				input = READER.next();
			} while (!Pattern.matches(regex, input));
			return input;
		}
		
		public static boolean domandaYesOrNo(String domanda) {
			System.out.println(domanda + YN_FORMAT);
			String ans = null;
			while (true) {
				System.out.print(CURSOR);
				ans = READER.next();
				if (ans.toLowerCase().equals(YES))
					return true;
				else if (ans.toLowerCase().equals(NO))
					return false;
			}
		}
			
	};
	
}
