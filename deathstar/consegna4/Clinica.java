package deathstar.consegna4;

import java.util.Set;
import java.util.List;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.function.BiConsumer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;


/** Classe per la rappresentazione di una clinica medica, caratterizzata
 *  da un insieme di Medici e Pazienti
 * 
 * @author Lorenzo Nodari
 *
 */
public class Clinica implements Interfaccia<String, Integer, GregorianCalendar> {
	
	private ArrayList<Medico> listaMedici;
	private ArrayList<Paziente> listaPazienti;
	
	private boolean getTimeEverCalled = false;
	private ArrayList<Long> getTimeCallsDelay = new ArrayList<>();
	private long lastNanoTime = 0L;
	
	private static final char ID_QUERY_CHAR = 'I';
	private static final char NAME_QUERY_CHAR = 'N';
	private static final char YEAR_QUERY_CHAR = 'L';
	private static final String GETTIME_FORMAT = "dd/MM/yyyy";
	
	public Clinica() {
		listaMedici = new ArrayList<>();
		listaPazienti = new ArrayList<>();
	}
	
	public Clinica(ArrayList<Medico> listaMedici, ArrayList<Paziente> listaPazienti) {
		this.listaMedici = listaMedici;
		this.listaPazienti = listaPazienti;
	}
	
	public void aggiungiMedico(Medico m) {
		listaMedici.add(m);
	}
	
	public void aggiungiPaziente(Paziente p) {
		listaPazienti.add(p);
	}
	
	public void rimuoviMedico(Medico m) {
		listaMedici.remove(m);
	}
	
	public void rimuoviPaziente(Paziente p) {
		listaPazienti.remove(p);
	}
	
	public Medico getMedicoDaId(int id) {
		for (Medico m : listaMedici) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	public Paziente getPazienteDaId(int id) {
		for (Paziente p : listaPazienti) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}
	
	public List<Medico> getMediciDaNome(String nomeCercato) {
		List<Medico> mediciTrovati = new ArrayList<Medico>();
		for (Medico m : listaMedici) {
			String nome_cognome = m.getNome() + " " + m.getCognome();
			String cognome_nome = m.getCognome() + " " + m.getNome();
			if (nome_cognome.equalsIgnoreCase(nomeCercato) || cognome_nome.equalsIgnoreCase(nomeCercato))
				mediciTrovati.add(m);
		}
		return mediciTrovati;
	}
	
	public List<Paziente> getPazientiDaNome(String nomeCercato) {
		List<Paziente> pazientiTrovati = new ArrayList<Paziente>();
		for (Paziente p : listaPazienti) {
			String nome_cognome = p.getNome() + " " + p.getCognome();
			String cognome_nome = p.getCognome() + " " + p.getNome();
			if (nome_cognome.equalsIgnoreCase(nomeCercato) || cognome_nome.equalsIgnoreCase(nomeCercato))
				pazientiTrovati.add(p);
		}
		return pazientiTrovati;
	}
	
	public List<Medico> getMediciDaAnnoLaurea(int annoCercato) {
		List<Medico> mediciTrovati = new ArrayList<Medico>();
		for (Medico m : listaMedici) {
			if (m.getAnnoLaurea() == annoCercato)
				mediciTrovati.add(m);
		}
		return mediciTrovati;
	}
	
	@Override
	public String stampaMedici() {
		StringBuffer buffer = new StringBuffer();
		for (Medico m : listaMedici) {
			buffer.append(m.toString());
			buffer.append(System.lineSeparator());
		}
		return buffer.toString();
	}
	
	@Override
	public String stampaPazienti() {
		StringBuffer buffer = new StringBuffer();
		for (Paziente p : listaPazienti) {
			buffer.append(p.toString());
			buffer.append(System.lineSeparator());
		}
		return buffer.toString();
	}
	
	@Override
	public String stampaNumAppuntamenti(int matricola) throws IllegalArgumentException {
		for (Medico m : listaMedici) {
			if (m.getId() == matricola)
				return String.valueOf(m.getNumAppuntamenti());
		}
		throw new IllegalArgumentException("Il medico cercato non esiste");
	}
	
	@Override
	public void getTime() {
		if (!getTimeEverCalled) {
			Date today = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat(GETTIME_FORMAT);
			System.err.println(dateFormatter.format(today));
			lastNanoTime = System.nanoTime();
			getTimeEverCalled = true;
		}
		else {
			long nano = System.nanoTime();
			getTimeCallsDelay.add(nano - lastNanoTime);
			for (Long delay : getTimeCallsDelay) {
				int callNumber = getTimeCallsDelay.indexOf(delay);
				System.err.println("Call " + String.valueOf(callNumber) + ": " + delay.toString() + " ns");
			}
		}
	}
	
	@Override
	public List<String> stampaAppuntamenti(Integer matricola) throws IllegalArgumentException {
		Medico medicoCercato = null;
		
		for (Medico m : listaMedici) {
			if (m.getId() == matricola) {
				medicoCercato = m;
				break;
			}
		}
		
		if (medicoCercato == null) 
			throw new IllegalArgumentException("Il medico cercato non esiste");
		
		TreeMap<Appuntamento, Paziente> elencoAppuntamenti = medicoCercato.getAppuntamenti();
		ArrayList<String> descrizioni = new ArrayList<>(elencoAppuntamenti.size());
		BiConsumer<Appuntamento, Paziente> generaDescrizioneAppuntamento = (appuntamento, paziente) -> {
			StringBuffer buffer = new StringBuffer();
			buffer.append(appuntamento.toString() + " - ");
			buffer.append("Paziente: " + paziente.toString() + ", ");
			buffer.append("Codice " + appuntamento.getUrgenza());
			descrizioni.add(buffer.toString());
		};
		
		elencoAppuntamenti.forEach(generaDescrizioneAppuntamento);
		return descrizioni;
	}
	
	public List<String> stampaAppuntamentiPaziente(int matricola) throws IllegalArgumentException {
		Paziente pazienteCercato = null;
		
		for (Paziente p : listaPazienti) {
			if (p.getId() == matricola) {
				pazienteCercato = p;
				break;
			}
		}
		
		if (pazienteCercato == null)
			throw new IllegalArgumentException("Il paziente cercato non esiste");
		
		TreeMap<Appuntamento, Medico> elencoAppuntamenti = pazienteCercato.getAppuntamenti();
		ArrayList<String> descrizioni = new ArrayList<>(elencoAppuntamenti.size());
		BiConsumer<Appuntamento, Medico> generaDescrizioneAppuntamento = (appuntamento, medico) -> {
			StringBuffer buffer = new StringBuffer();
			buffer.append(appuntamento.toString() + " - ");
			buffer.append("Medico: " + medico.toStringCorto() + ", ");
			buffer.append("Codice " + appuntamento.getUrgenza());
			descrizioni.add(buffer.toString());
		};
		
		elencoAppuntamenti.forEach(generaDescrizioneAppuntamento);
		return descrizioni;
	}
	
	@Override
	public List<String> stampaAppuntamenti(GregorianCalendar data_inizio, GregorianCalendar data_fine) {
		ArrayList<String> appuntamentiCercati = new ArrayList<>();
		
		for (Medico m : listaMedici) {
			NavigableMap<Appuntamento, Paziente> appuntamentiMedico = m.getAppuntamentiTra(data_inizio, data_fine);
			if (appuntamentiMedico != null) {
				Set<Appuntamento> keySet = appuntamentiMedico.keySet();
				for (Appuntamento a : keySet) {
					StringBuffer buffer = new StringBuffer();
					buffer.append(m.toStringCorto() + " - ");
					buffer.append(a.toString() + " - ");
					buffer.append(appuntamentiMedico.get(a).toString());
					appuntamentiCercati.add(buffer.toString());
				}
			}
		}
		
		
		return appuntamentiCercati;
	}
	
	@Override
	public List<String> stampaOrari(String check) {
		char queryTypeChar = check.charAt(0);
		String queryContent = check.substring(2);
		List<String> result = new ArrayList<String>();
		List<Medico> mediciTrovati;
		
		switch (queryTypeChar) {
		case ID_QUERY_CHAR:
			Medico m = getMedicoDaId(Integer.parseInt(queryContent));
			mediciTrovati = new ArrayList<>(1);
			if (m != null)
				mediciTrovati.add(m);
			break;
		
		case NAME_QUERY_CHAR:
			mediciTrovati = getMediciDaNome(queryContent);
			break;
		
		case YEAR_QUERY_CHAR:
			mediciTrovati = getMediciDaAnnoLaurea(Integer.parseInt(queryContent));
			break;
			
		default:
			mediciTrovati = new ArrayList<>();
		}
		
		for (Medico m : mediciTrovati) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(m.toStringCorto());
			buffer.append(m.getOrario().toString());
			result.add(buffer.toString());
		}
		
		
		return result;
	}
	
	public List<String> stampaPazientiConAppuntamento() {
		HashMap<Integer, List<Paziente>> pazientiPerNumero = new HashMap<>();
		List<String> result = new ArrayList<String>();
		for (Paziente p : listaPazienti) {
			int numAppuntamenti = p.getNumAppuntamenti();
			
			if (numAppuntamenti >= 1) {
				if (pazientiPerNumero.containsKey(numAppuntamenti))
					pazientiPerNumero.get(numAppuntamenti).add(p);
				else {
					pazientiPerNumero.put(numAppuntamenti, new ArrayList<Paziente>());
					pazientiPerNumero.get(numAppuntamenti).add(p);
				}
				
			}
		}
		
		Set<Integer> keySet = pazientiPerNumero.keySet();
		
		for (Integer i : keySet) {
			List<Paziente> tmpList = pazientiPerNumero.get(i);
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(String.format("Pazienti con %d appuntamenti:%n", i));
			for (Paziente p : tmpList) {
				buffer.append(p.toString() + System.lineSeparator());
			}
			result.add(buffer.toString());
		}
		
		return result;
	}
	
	public List<String> stampaTuttiGliOrari() {
		List<String> result = new ArrayList<>();
		
		for (Medico m : listaMedici) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(m.toStringCorto() + ":" + System.lineSeparator());
			buffer.append(m.getOrario().toString());
			result.add(buffer.toString());
		}
		
		return result;
	}
	

}
