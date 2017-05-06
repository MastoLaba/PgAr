import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String args[]) {
    	Scanner in = new Scanner(System.in);
    	System.out.println("Inserisci il file da leggere:");
    	String nomeFile = in.next();
    	FromXML2Nodes lettore;
    	try {
			lettore = new FromXML2Nodes(nomeFile);
			Vector<Node> _tree =lettore.getTree();
			Graph g1 = new Graph(lettore.getTree());		
			System.out.println("Calcolo Dijkstra");
			g1.dijkstra();
			_tree = g1.getDijkstra();
			lettore.save("Dijkstra.xml",_tree);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			in.close();
			e.printStackTrace();
			System.exit(0);
		} 
    }

}
