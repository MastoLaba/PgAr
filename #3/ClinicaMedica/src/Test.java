import java.io.FileNotFoundException;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

/**
 * Classe di Test del progetto ClinicaMedica.
 * @author qwertyteam
 * @version 1.0
 */

public class Test {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		XMLReader reader = new XMLReader("clinica.xml");
		Vector<Paziente> listaPazienti = reader.getPazienti();
		Vector<Medico> listaMedici = reader.getMedici();
		Vector<Appuntamento> listaAppuntamenti = reader.getAppuntamenti();
		
		Clinica clinica = new Clinica("Clinica di Qwertyteam", 
				listaPazienti, listaMedici);
		
		Paziente paziente = listaPazienti.get(0);
		Paziente paziente2 = listaPazienti.get(1);
		Paziente paziente3 = listaPazienti.get(2);
		Medico medico = listaMedici.get(0);
		Appuntamento appuntamento = listaAppuntamenti.get(0);
		Appuntamento appuntamento1 = listaAppuntamenti.get(1);
		Appuntamento appuntamento2 = listaAppuntamenti.get(2);
		Appuntamento appuntamento3 = listaAppuntamenti.get(3);
		paziente.prendiAppuntamento(clinica, medico, appuntamento);
		paziente.prendiAppuntamento(clinica, medico, appuntamento1);
		paziente2.prendiAppuntamento(clinica, medico, appuntamento2);
		paziente3.prendiAppuntamento(clinica, medico, appuntamento3);
		//System.out.println(clinica.stampaAppuntamenti(medico.getID()));
		//System.out.println(medico.getOrariAppuntamenti());
		//System.out.println(medico.getOrariAppuntamenti());
		System.out.println(clinica.stampaPazientiPerAppuntamento());
		System.out.println(medico.getOrariAppuntamenti());
		medico.getAppuntamentiTemporale();
		System.out.println(medico.getOrariAppuntamenti());
		
	}

}
