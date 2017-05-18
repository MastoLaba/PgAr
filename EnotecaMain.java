package enoteca;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

public class EnotecaMain {
	public static void main(String args[]) throws FileNotFoundException, XMLStreamException{
		
		Enoteca enoteca = new Enoteca("enoteca.xml");
		enoteca.stampaVini();
		enoteca.qntXproduttore();
		enoteca.qntXvino();
		enoteca.guadagno();
		enoteca.inAnno();
		
	}

}
