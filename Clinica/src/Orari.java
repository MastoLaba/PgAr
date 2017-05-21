import java.util.Date;
import java.util.Vector;

public class Orari {
	public static final String[] SETTIMANA = {"Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato","Domenica"};
	private Vector<Date> inizio, fine;
	
	public Orari() {
		super();
		this.inizio=new Vector<Date>();
		this.fine=new Vector<Date>();
		for(int i=0;i<SETTIMANA.length;i++){
			inizio.add(new Date());
			fine.add(new Date());
		}
	}
	public String toString(){
		String tmp="";
		for(int i=0;i<SETTIMANA.length;i++){
			tmp+=SETTIMANA[i]+" dalle "+inizio.get(i).getHours()+":"+inizio.get(i).getMinutes();
			tmp+=" alle "+fine.get(i).getHours()+":"+fine.get(i).getMinutes()+"\n";
		}
		return tmp;
	}

	public Vector<Date> getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio,int ind) {
		System.out.println(inizio.getHours()+":"+inizio.getMinutes());

		this.inizio.set(ind, inizio);
	}

	public Vector<Date> getFine() {
		return fine;
	}

	public void setFine(Date fine, int ind) {
		System.out.println(fine.getHours()+":"+fine.getMinutes());

		this.fine.set(ind, fine);
	}
	
}
