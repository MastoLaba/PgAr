import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

public class ProblemaC {

	public static void main(String[] args) {
		LogTime logtime=new LogTime();
		logtime.getTime();
		Vector<Integer> base;
		Vector<Integer> exp;
		double max,min,tmp;
		int indMin, indMax;
		String file;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Dimmi il nome del file (problem_c.xml)");
		file = in.next();
		try {
			logtime.getTime();
			FromXml lettore =new FromXml(file);
			base = lettore.getBase();
			exp = lettore.getExp();
			max = calcolo(base.get(0), exp.get(0));
			min = max;
			indMin = 0;
			indMax = 0;
			logtime.getTime();
			for(int i=1; i< base.size();i++){
				tmp = calcolo(base.get(i), exp.get(i));
				if(tmp>max){
					max = tmp;
					indMax = i;
				}
				if(tmp<min){
					min = tmp;
					indMin = i;
				}
			}
			System.out.println("il numero più grande è "+base.get(indMax)+"^"+exp.get(indMax));
			System.out.println("il numero più piccolo è "+base.get(indMin)+"^"+exp.get(indMin));
			logtime.getDelta();
		} catch (FileNotFoundException e) {
			System.out.println("file non trovato");
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}
	public static double calcolo(int base, int exp){
		return exp* Math.log10(base);
	}

}
