public class Output {

	private StringBuffer cifre;
	private int occorrenze;
	private StringBuffer codificata;
	
	public Output(StringBuffer cifre, int occorrenze, StringBuffer codificata) {
		this.cifre = cifre;
		this.occorrenze = occorrenze;
		this.codificata = codificata;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cifre);
		sb.append("\n" + String.valueOf(occorrenze));
		sb.append("\n" + codificata);
		return sb.toString();
	}
}
