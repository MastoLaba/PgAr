
public class conta {

	public static void main(String[] args) {
		int numeroCombinazioni = 0;
		int obbiettivo = 200;	
	    for (int a = obbiettivo; a >= 0; a = a - 200) {
	        for (int b = a; b >= 0; b = b - 100) {
	            for (int c = b; c >= 0; c = c - 50) {
	                for (int d = c; d >= 0; d = d - 20) {
	                    for (int e = d; e >= 0; e = e - 10) {
	                        for (int f = e; f >= 0; f = f- 5) {
	                            for (int g = f; g >= 0; g = g - 2) {
	                                numeroCombinazioni++;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	    System.out.println(Integer.toString(numeroCombinazioni));

	}

}
