package dijkstra_kruskal.myUtil.myStatic;

import java.util.Random;

public class Input {
	
	//GENERAZIONE DI NUMERI CASUALI CON UN ESTREMO MINORE E UNO MAGGIORE
		public static  int generationRandom(int min, int max){
			Random numero= new Random();
			int ritorno;
			do{
				ritorno=numero.nextInt(max);
			}while(ritorno<=min); 
			return ritorno;		
		}
		
		/*public static double generationRandom(double min, double max){
			Random numero= new Random();
			double ritorno;
			do{
				ritorno=numero.nextDouble();
			}while(ritorno<=min); 
			return ritorno;
		}
		
		public static int generationRandomUp(double numero, int range){
			Random casuale = new Random();
			int ritorno=generationRandom(numero-(range/2), numero+(range/2));
			return ritorno;
		}*/
		
		public static void generationRandomNext(double vettore[], double numero, double limiteInf){

			for(int i=0; i<vettore.length; i++){
				double scarto1=	numero*-Math.cos(((double)(i)/9)*Math.PI);
				Random random= new Random(); 
				vettore[i]=limiteInf + scarto1;
			}
		}
		
		public static void generationRandomNextUp(double vettore[], double numero, double limiteInf, double range){
			Random random = new Random ();
			generationRandomNext(vettore, numero, limiteInf);
			for(int i=0; i<vettore.length; i++){
				vettore[i]+=range*random.nextDouble();
			}
		}
		

}
