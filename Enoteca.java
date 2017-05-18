package enoteca;

import java.util.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.*;

import it.unibs.fp.mylib.InputDati;

public class Enoteca 
{
	private ArrayList<Vino> vini= new ArrayList<Vino>();
	

  //Stampare tutti i vini
	public Enoteca(String filename) throws FileNotFoundException, XMLStreamException{
		File file = null;
		try{
			file= new java.io.File(filename);
		}catch (Exception e){
			System.out.println(filename + "not found");
		}
		
		XMLInputFactory factory = XMLInputFactory.newFactory();
		XMLStreamReader reader = factory.createXMLStreamReader( new FileInputStream( file));
		
		String nome="", produttore="", regione="", valuta="";
		int annata=0, qnt=0;
		double numero=0;
		Prezzo prezzo=null;
		
		boolean readName = false, readProduttore=false, readRegione=false,
				readAnnata = false, readQnt = false, readNumero=false;
		
		
		while(reader.hasNext()){
			
			switch (reader.next()){
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Start reading document");
				break;

			case XMLStreamConstants.START_ELEMENT:
				if("name".equals(reader.getLocalName())){
					readName=true;
				}
				else {if("price".equals(reader.getLocalName())){
					valuta=reader.getAttributeValue(0).toString().trim();
					readNumero=true;					
				}
				else if("cont".equals(reader.getLocalName()))
					readQnt=true;
				else if("date".equals(reader.getLocalName()))
					readAnnata=true;
				else if("geo".equals(reader.getLocalName()))
					readRegione=true;
				else if("farmer".equals(reader.getLocalName()))
					readProduttore=true;
				}
				//TODO if start_element == name => boolean readname==true
				break;

			case XMLStreamConstants.CHARACTERS:
				//TODO if readname nome=reader.getText().trim() readname=false;
				if(readName){
					nome=reader.getText().toString().trim();
					readName=false;
				}
				if(readNumero){
					numero=Double.parseDouble(reader.getText().toString().trim());
					prezzo= new Prezzo(numero, valuta);
					readNumero=false;
				}
				if(readQnt){
					String stringa=reader.getText().toString().trim();
					if(!stringa.isEmpty()) qnt=Integer.parseInt(stringa);
					readQnt=false;
				}

				if(readAnnata){
					annata=Integer.parseInt(reader.getText().toString().trim());
					readAnnata=false;
				}

				if(readRegione){
					regione=reader.getText().trim();
				}

				if(readProduttore){
					produttore=reader.getText().trim();
					readProduttore=false;
				}
				break;



			case XMLStreamConstants.END_ELEMENT:
				switch(reader.getLocalName()){
				case "wine":
					Vino vino= new Vino(nome, prezzo, annata, regione, produttore, qnt);
					if(vini.add(vino)){
						//System.out.println("vino aggiunto  -" +vino.getNome());
						nome=""; produttore=""; regione=""; valuta="";
						annata=0; qnt=0;
						numero=0;
						prezzo=null;
					}

					break;
				case "wines":
					stampaVini();
					break;
				}
				break;

			case XMLStreamConstants.END_DOCUMENT:
				System.out.println("end reading");
				break;


			}//switch
		}//while
	}//enoteca
	
	public void  stampaVini()
	{
		for(Vino vino: vini)
			System.out.println(vino.getNome() +" "+ vino.getPrezzo().getCosto() +vino.getPrezzo().getValuta() );		
	}
		
 

		
	//Stampare la quantit� di bottiglie per ogni Vino	
	public void qntXvino()
	{
		int i=0;
		for(i=0;i<vini.size();i++)
		{
			System.out.println("i vini "+vini.get(i).getNome()+" sono "+ vini.get(i).getQnt());
		}
	}
	
	//Stampare la quantit� di Bottiglie per ogni Produttore
	public void qntXproduttore()
	{
		int i =0;
		int k=0;
		int count=0;
		Vector<String> produttori= new Vector<String>();
		
		for(i=0;i<vini.size();i++)//questo for crea un vector di string con all' interno la lista dei produttori 
		{
			String produttore= vini.get(i).getProduttore();
			if( nellaLista(produttori,produttore)==1)
			produttori.add(produttore);
		}
		
		for(i=0;i<produttori.size();i++)
		{
			for(k=0;k<vini.size();k++)
			{
				String produttore=vini.get(k).getProduttore();
				if(produttore.equals(produttori.get(i)))
				{
					count+=vini.get(k).getQnt();
				}
			}
			System.out.println("ci sono "+count+" vini del produttore "+produttori.get(i));
		}
		
	}
	
	public int nellaLista(Vector<String> vettore,String cercato)//questo metodo controlla se la stringa passata � presente nella lista di stringhe passata (ritorna: 1 se non � presente,0 altrimenti)
	{
		int noinlista=1;
		int siinlista=0;
		int i=0;
	    int count=0;
		for(i=0;i<vettore.size();i++)
		{
			if(vettore.get(i).equals(cercato))
			count++;
		}
		if(count==0) return  noinlista;
		if(count>0) return siinlista;
		else return noinlista;
	}
	
	//Data una certa fascia di Annata (es. dal 1990 al 2015) stampare tutti i vini
	public void inAnno()
	{
		int inizio=InputDati.leggiInteroPositivo("inserisci la data di partenza");
		int fine=InputDati.leggiInteroPositivo("inserisci la data di fine");
		for(int i=0;i<vini.size();i++)
		{
			if(vini.get(i).getAnnata()>=inizio && vini.get(i).getAnnata()<fine) 
			{
				System.out.print("il vino"+vini.get(i).getNome()+" � compreso nell'annata specificata");
			}
		}
	}
//	Stampare il possibile guadagno in Multi-valuta, data come variabile esterna la valuta desiderata
	public void guadagno()
	{
		final double tassoED =1.1164 ;
		final double tassoDE = 0.8957;
		double totale=0;
		String valutaRis=InputDati.leggiStringaNonVuota("inserisci la valuta desiderata");
		String euro="€";
		String dollari="$";
		for(int i=0;i<vini.size();i++)//con questo for calcolo in guadagno totale in dollari
		{
			Vino vino= vini.get(i);
			String valutaVino=vino.getPrezzo().getValuta();
			if(valutaVino.equals(euro))
			{
				Double inDollari=(vino.getPrezzo().getCosto())*tassoED;
				totale +=inDollari;
			}
		
			if(valutaVino.equals(dollari))
			{
				totale+=vino.getPrezzo().getCosto();
			}
		}
		if(valutaRis.equals(dollari))//stampa il possibile guadagno in dollari
			System.out.println("il possibile guadagno in dollari �: "+totale);
		if(valutaRis.equals(euro))//stampa il possibile guadagno in euro
		{
			totale*=tassoDE;
			System.out.println("il possibile guadagno in euro �: "+totale);
		}
				
	}
	
	
	
	
}
