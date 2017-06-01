
public class Data {
	
	public static final String CORNICE="-------------------------------------------";
	private int giorno;
	private int mese;
	private int anno;
	private int ora;
	
	public Data(int anno,int mese,int giorno, int ora)
	{
		this.anno=anno;
		this.mese=mese;
		this.giorno=giorno;
		this.ora=ora;
	}
	public String toString()
	{
		StringBuffer ris=new StringBuffer();
		ris.append(CORNICE);
		ris.append(System.lineSeparator());
		ris.append("data: "+anno+"/"+mese+"/"+giorno);
		ris.append(System.lineSeparator());
		ris.append("ora: " +ora);
		ris.append(System.lineSeparator());
		ris.append(CORNICE);
		return ris.toString();
	}
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public int getMese() {
		return mese;
	}
	public void setMese(int mese) {
		this.mese = mese;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getOra() {
		return ora;
	}
	public void setOra(int ora) {
		this.ora = ora;
	}
	
		

}
