package enoteca;

import java.util.*;
public class Vino {

	private String nome;
	private String produttore;
	private int annata;
	private String regione;
	private Prezzo prezzo;
	private int qnt;
	
	
	public Vino(String nome,Prezzo prezzo,int annata,String regione,String produttore,int qnt)
	{
		this.nome=nome;
		this.produttore=produttore;
		this.annata=annata;
		this.regione=regione;
		this.prezzo=prezzo;	
		this.qnt=qnt;
	}
	
	public int getQnt() {
		return qnt;
	}
	
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getProduttore() {
		return produttore;
	}
	
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}
	
	public int getAnnata() {
		return annata;
	}
	
	public void setAnnata(int annata) {
		this.annata = annata;
	}
	
	public String getRegione() {
		return regione;
	}
	
	public void setRegione(String regione) {
		this.regione = regione;
	}
	
	public Prezzo getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(Prezzo prezzo) {
		this.prezzo = prezzo;
	}

}
