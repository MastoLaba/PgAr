package ieee.secondoes.problemb;

public class Main {
	
	public static void main(String[] args) {		
		Combos c = new Combos(45);

		long t1 = System.currentTimeMillis();
//		c.ricorsione();
		c.calcolaCombos();
		long t2 = System.currentTimeMillis();
		
		System.out.println(c);		
		System.out.println("finito, tempo: "+(t2-t1));
	}

}
