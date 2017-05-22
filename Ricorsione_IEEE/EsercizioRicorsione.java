public class EsercizioRicorsione {
	
	private static StringBuffer buffer_cifre = new StringBuffer();
	private static int occorrenze = 0;
	private static StringBuffer buffer_codifica = new StringBuffer();
	
	public static void main(String[] args) {
		Output out;
		compute("ciaoateamicocaro5comestai7oggi", 'o');
		out = new Output(buffer_cifre, occorrenze, buffer_codifica);
		System.out.println(out.toString());
	}
	
	private static void compute(String str, char c) {
		if (str.length() == 1) {
			if (Character.isDigit(str.charAt(0))) {
				buffer_cifre.append(str.charAt(0));
			}
			if (str.charAt(0) == c) {
				occorrenze++;
				buffer_codifica.append('@');
			}
			else {
				buffer_codifica.append(str.charAt(0));
			}
		}
		else {
			compute(str.substring(0, str.length() / 2), c);
			compute(str.substring(str.length() / 2, str.length()), c);
		}
	}
}
