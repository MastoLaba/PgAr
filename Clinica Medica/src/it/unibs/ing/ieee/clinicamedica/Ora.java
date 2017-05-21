package it.unibs.ing.ieee.clinicamedica;

public class Ora implements Comparable<Ora>{
	private int ora;
	private int minuti;

	public int getOra() {
		return ora;
	}

	public void setOra(int ora) {
		this.ora = ora;
	}

	public int getMinuti() {
		return minuti;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public Ora(int _ora, int _minuti) {

		if (_ora < 0)
			_ora = Math.abs(_ora);

		if (_ora > 23)
			_ora %= 24;
		
		if(_minuti < 0)
			_minuti = Math.abs(_minuti);
		
		if(_minuti > 59)
			_minuti %= 60;

		this.ora = _ora;
		this.minuti = _minuti;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d", ora, minuti);
	}
	
	@Override
	public boolean equals(Object obj) {
		Ora o1 = (Ora) obj;
		if(o1.ora == this.ora && o1.minuti == this.minuti)
			return true;
		return false;
	}

	@Override
	public int compareTo(Ora o1) {
		int c;
		c = Integer.compare(this.ora, o1.ora);
		if (c == 0)
			c = Integer.compare(this.minuti, o1.minuti);
		return c;
	}
	
	

}
