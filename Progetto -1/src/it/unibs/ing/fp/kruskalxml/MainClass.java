package it.unibs.ing.fp.kruskalxml;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

public class MainClass {

	public static void main(String[] args) {

		XmlParser xp = new XmlParser();
		XmlWriter xw = new XmlWriter();
		Graph graph = new Graph();

		try {
			graph = xp.parseXml("input2.xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		System.out.println("\nAlgoritmo di Kruskal");

		try {
			xw.saveKruskal("kruskal.xml", KruskalAlgorithm.startAlgorithm(graph));
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n\nAlgoritmo di Dijkstra");

		try {
			xw.saveDijkstra("dijkstra.xml", DijkstraAlgorithm.startAlgorithm(graph));
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
