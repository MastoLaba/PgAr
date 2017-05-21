import java.util.TreeMap;
import java.util.Vector;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Interfaccia {
	public String stampaMedici();
	public TreeMap<Integer, Vector<LocalTime>> stampaOrari(Medico m);
	public TreeMap<Integer, Vector<Appuntamento>> stampaAppuntamenti(int ID);
	public String stampaPazienti();
	public Vector<Appuntamento> stampaAppuntamenti(LocalDateTime inizio, LocalDateTime fine);
	public String stampaNumAppuntamenti(int matricola);
	public void getTime();
}
