package dijkstra_kruskal;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public class Main {

	private static final String PATHNAME ="/home/mauro/Documenti/UNIBS II° semestre/IEEE/Repository/git/PgAr/#1/input.xml";
	private static final String PATHNAME2="/home/mauro/Documenti/UNIBS II° semestre/IEEE/Repository/git/PgAr/#1/input2.xml";
	public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException {
		// TODO Auto-generated method stub

		Graph prova = new Graph(PATHNAME);
		prova.printAll();
		StaticGraph.printXMLGraph(prova, prova.algorithmDijkstra(), "Dijkstra1");
		StaticGraph.printXMLGraph(prova, prova.algorithmKruskal(), "Kruskal1");
		
		Graph prova2 = new Graph(PATHNAME2);
		prova2.printAll();
		StaticGraph.printXMLGraph(prova2, prova2.algorithmDijkstra(), "Dijkstra2");
		StaticGraph.printXMLGraph(prova2, prova2.algorithmKruskal(), "Kruskal2");
		
	}

}
