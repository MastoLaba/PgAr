package deathstar.consegna1;

import javax.xml.stream.XMLStreamException;

/** Classe contenente il main del programma
 * 
 * @author Lorenzo Nodari
 *
 */
public class GraphManipulator {

	private static final String FILE_NAME_INPUT = "input.xml";
	private static final String FILE_NAME_DIJKSTRA = "dijkstra.xml";
	private static final String FILE_NAME_KRUSKAL = "kruskal.xml";
	
	public static void main(String [] args) throws XMLStreamException {
		XMLUtil parser;
		Graph graph = null;
		
		try {
			// Creazione del grafo da file XML
			parser = new XMLUtil(FILE_NAME_INPUT);
			graph = new Graph(parser.getNodi());
			
//			System.out.println(graph.toString());
			
			// Creazione del minimo cammino con Dijkstra.
			Graph path = graph.getMinimumPath();
			parser.setNodes(path.getNodes());
			parser.save(FILE_NAME_DIJKSTRA);
			
			// Creazione del minimo albero con Kruskal.
			graph.forceUndirected();
			Graph tree = graph.getMinimumSpanningTree();
			parser.setNodes(tree.getNodes());
			parser.save(FILE_NAME_KRUSKAL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * INTERFACCIA DA LINEA DI COMANDO:
	 * 
	 * Mi dispiaceva cancellarla.
	
	//Help messages
	private static final String VERSION = "GraphManipulator v0.1 - Student Branch IEEE UniBS - 2017";
	private static final String AUTHORS = "by Lorenzo Nodari, Michele Dusi, Alessandro Ghirardi Luna";
	private static final String USAGE = "Usage: java GraphManipulator.class <operation> <inputfile>";
	private static final String OPERATIONS = "Operations:";
	private static final String OP_DIJKSTRA = "-d\t:Applies Dijkstra Algorithm to the graph represented by the given XML file";
	private static final String OP_KRUSKALL = "-k\t:Applies Kruskall Algorithm to the graph represented by the given XML file";
	private static final String OUTPUT_NOTE = "The program will output the result in a file called output.xml in the current directory";
	
	//Error messages
	private static final String ARGS_ERROR = "ERROR: Wrong usage, call with no arguments to print help page";

	public static void main(String[] args) {
		if (args.length == 0) {
			printHelp();
		}
		else if (args.length != 2) {
			System.out.println(ARGS_ERROR);
		}
		else {
			System.out.println("Ok");
		}
	}
	
	private static void printHelp() {
		System.out.println(VERSION);
		System.out.println(AUTHORS);
		System.out.println();
		System.out.println(USAGE);
		System.out.println();
		System.out.println(OPERATIONS);
		System.out.println(OP_DIJKSTRA);
		System.out.println(OP_KRUSKALL);
		System.out.println(OUTPUT_NOTE);
	}
	
	*/
}
