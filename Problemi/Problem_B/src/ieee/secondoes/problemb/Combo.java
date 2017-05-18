package ieee.secondoes.problemb;

public class Combo {
	int[] combo;
	
	public Combo(int somma){ this.combo = new int[somma]; }
	
	public void addMoneta(int moneta){
		int i=0;
		for(; i<combo.length && combo[i]!=0; i++){;}
		combo[i] = moneta;	
	}
	
	public int getMoneta(int i){ return combo[i]; }
	
	public void rimuoviUltima(){
		for(int i=1; i<combo.length; i++)
			if(combo[i] == 0){
				combo[i-1] = 0;
				i = combo.length;
			}
	}
	
	public int ultimaGrande(){
		for(int i=1; i<combo.length; i++)
			if(combo[i] == 1 || combo[i] == 0)
				return i-1;
		return -1;
	}
	
	public void taglia(){
		for(int i=ultimaGrande(); i<combo.length && combo[i]!=0; i++)
			combo[i] = 0;
	}
	
	public int totale(){
		int s=0;
		for(int i=0; i<combo.length && combo[i]!=0; i++)
			s += Combos.monete[combo[i]];
		return s;
	}
	
	public Combo clone(){
		Combo c = new Combo(combo.length);
		for(int i: combo)
			c.addMoneta(i);
		return c;
	}
	
	public String toString(){
		String ritorno="";
		int[]contatori = new int[Combos.monete.length];
		for(int i=0; i<combo.length && combo[i]!=0; i++)
			contatori[combo[i]]++;
		for(int i=Combos.monete.length-1; i>0; i--)
			if(contatori[i]!=0)
				ritorno += contatori[i]+"*("+Combos.monete[i]+"c)\t";
		return ritorno;// + "tot="+totale()+"";
	}
	
}
