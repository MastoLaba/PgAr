package it.unibs.ing.ieee.clinicamedica;

public class Persona {

	private int id;
	private String nome;
	private String cognome;

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public Persona(String _nome, String _cognome) {
		nome = _nome;
		cognome = _cognome;
	}

}
