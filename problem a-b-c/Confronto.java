package problemi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;



public class Confronto{
	public class NumeriGrandi {

		private int base;
		private int esponente;

		public NumeriGrandi (int base, int esponente){
			this.base=base;
			this.esponente=esponente;
		}

		public int getBase() {
			return base;
		}

		public int getEsponente() {
			return esponente;
		}
	}
	
	
	public static void calcolaMaxMin(String filename) throws FileNotFoundException, XMLStreamException{
		Confronto.NumeriGrandi max = new Confronto().new NumeriGrandi(0,0); 
		Confronto.NumeriGrandi min = new Confronto().new NumeriGrandi (0, 0);
		Confronto.NumeriGrandi maxNepero = new Confronto().new NumeriGrandi (0, 0);
		Confronto.NumeriGrandi minNepero = new Confronto().new NumeriGrandi (0, 0);
		
		LogTime logtime = new LogTime();
		
		Confronto.NumeriGrandi numero;
		File file;
		
		try{
			file=new File(filename);
		}catch(Exception e){
			System.out.println("File at "+filename+" is not avaiable or correctly patthed");
			return;
		}
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
		int base=0, esponente=0;
		
		
		logtime.getTime();
		while(reader.hasNext()){
			switch(reader.next()){
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Start reading Doc");
				break;
			case XMLStreamConstants.START_ELEMENT:
				if("candidates".equals(reader.getLocalName()))
					System.out.println("inizio la lettura");
				if("candiate".equals(reader.getLocalName())){

					if("b".equals(reader.getAttributeName(0).toString()))
						base=Integer.parseInt(reader.getAttributeValue(0).toString().trim());

					if("e".equals(reader.getAttributeName(1).toString()))
						esponente=Integer.parseInt(reader.getAttributeValue(1).toString().trim());

					numero = new Confronto().new NumeriGrandi(base, esponente);
					
					if(min.getBase()==0) {
						max=numero;
						min=numero;
						maxNepero=numero;
						minNepero=numero;
					}
					else{
						//max = confrontoMax(max, numero);
						//min = confrontoMin(min, numero);
						maxNepero = confrontoMaxNepero(maxNepero, numero);
						minNepero = confrontoMinNepero(minNepero, numero);
					}
				}//if
				break;

			}//switch
		} //del while
		logtime.getTime();
		logtime.getDelta();
		//Confronto.NumeriGrandi min1 = confrontoMin(min, minNepero);
		//Confronto.NumeriGrandi min2 = confrontoMinNepero(min, minNepero);
		
		

		//System.out.println("il max è b=" + max.getBase() + " e=" + max.getEsponente() +
		//		"e il min è b=" +min.getBase() + " e=" + min.getEsponente()) ;
		System.out.println("il maxNepero è b=" + maxNepero.getBase() + " e=" + maxNepero.getEsponente() +
				" e il minNepero è b=" +minNepero.getBase() + " e=" + minNepero.getEsponente()) ;
		//System.out.println("min1: b=" +min1.getBase() + " e=" + min1.getEsponente());
		//System.out.println("min2: b=" +min2.getBase() + " e=" + min2.getEsponente());

		salva(max, minNepero);
	

	}//del metodo

	
	public static Confronto.NumeriGrandi confrontoMax(Confronto.NumeriGrandi numero1, Confronto.NumeriGrandi numero2){
		double rapportoE=numero2.getEsponente()/numero1.getBase();
		if(Math.log10(numero1.getBase())  >=  rapportoE*Math.log10(numero2.getEsponente()))  return numero1;
		else return numero2;
	}
	
	public static Confronto.NumeriGrandi confrontoMin(Confronto.NumeriGrandi numero1, Confronto.NumeriGrandi numero2){
		double rapportoE=numero2.getEsponente()/numero1.getEsponente();
		if(Math.log10(numero1.getBase())  <=   rapportoE*Math.log10(numero2.getBase())) return numero1;
		else return numero2;
	}
	
	public static Confronto.NumeriGrandi confrontoMaxNepero(Confronto.NumeriGrandi numero1, Confronto.NumeriGrandi numero2){
		double rapportoE=numero2.getEsponente()/numero1.getBase();
		if(Math.log10(numero1.getBase())  >=  rapportoE*Math.log(numero2.getEsponente()))  return numero1;
		else return numero2;
	}
	
	public static Confronto.NumeriGrandi confrontoMinNepero(Confronto.NumeriGrandi numero1, Confronto.NumeriGrandi numero2){
		double rapportoE=numero2.getEsponente()/numero1.getEsponente();
		if(Math.log10(numero1.getBase())  <=  rapportoE*Math.log(numero2.getBase())) return numero1;
		else return numero2;
	}
	
	public static boolean salva(Confronto.NumeriGrandi max, Confronto.NumeriGrandi min) throws XMLStreamException{
		System.out.println("Scrittura su file");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter("problema 3"));

			writer.writeStartDocument();
			writer.writeStartElement("answer");	//answer

			writer.writeStartElement("max_number"); //max
			String stringa = String.format("%d", max.getBase());
			writer.writeAttribute("b", stringa);
			stringa = String.format("%d", min.getEsponente());
			writer.writeAttribute("e", stringa);
			writer.writeEndElement();   //max

			writer.writeStartElement("min_number"); //min
			stringa = String.format("%d", min.getBase());
			writer.writeAttribute("b", stringa);
			stringa = String.format("%d", min.getEsponente());
			writer.writeAttribute("e", stringa);
			writer.writeEndElement(); //min


			writer.writeEndElement();  //answer
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			System.out.println("doc salvato");


		}catch (IOException e){
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;


	}
}