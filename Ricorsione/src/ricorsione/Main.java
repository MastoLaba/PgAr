package ricorsione;

public class Main {
	private static String soloCifre(String s){
		if(s.length()==0) return s;
		if(s.charAt(0)>='0' && s.charAt(0)<='9')
			return s.charAt(0) + soloCifre(s.substring(1));
		else 
			return soloCifre(s.substring(1));
	}
	
	public static int occorrenzeChar(String s, char c){
		if(s.length()==0) return 0;
		if(s.charAt(0) == c)
			return 1+occorrenzeChar(s.substring(1), c);
		else 
			return occorrenzeChar(s.substring(1), c);
	}
	
	public static String codifica(String s, char c1, char c2){
		if(s.length()==0) return s;
		if(s.charAt(0) == c1)
			return c2+codifica(s.substring(1), c1, c2);
		else
			return s.charAt(0)+codifica(s.substring(1), c1, c2);
	}

	public static void main(String[] args) {
		String str = "stringhettina123";
		char c = 'i';
		
		System.out.println("str: "+str);
		System.out.println("char: "+c);
		System.out.println("str con solo cifre: "+soloCifre(str));
		System.out.println("conta carattere: "+occorrenzeChar(str, c));
		System.out.println("str cifrata: "+codifica(str, c, '$'));

	}

}
