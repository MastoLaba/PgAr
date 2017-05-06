package ieee.primoes.grafi;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public class Main {

	public static void main(String[] args){
		Grafo g = null;
		try {
			g = Grafo.getDaXML("input.xml");
		} catch (FileNotFoundException | XMLStreamException e) { } 
		
		System.out.println("Grafo:\n" + g);
		System.out.println("Kruskal:\n" + g.Kruskal());
		
		try {
			g.Kruskal().toXML("krusko.xml");
			(new Dijkstra(g)).toXML("dijkstra.xml");
		} catch (IOException | XMLStreamException e) { }
		
		
	}

}
