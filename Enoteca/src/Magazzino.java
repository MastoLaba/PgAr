import java.util.Scanner;
import java.util.Vector;

public class Magazzino {
	Scanner lettore=new Scanner(System.in);
	private Vector<Vino> vini;
	private double guadagnoDollari=0;
	private double guadagnoEuro=0;


	Valuta euro = new Valuta("€", 1.11);
	Valuta dollaro = new Valuta("$", 0.90);
	
	
	public  Magazzino(Vector<Vino> vino){
		this.vini=vino;

	}



	public void listaVini(){
		for(int i=0;i<vini.size();i++){
			System.out.println(vini.get(i).toString());
		}

	}

	public void quantBottVino(){
		for(int i=0;i<vini.size();i++){
			System.out.println("vino: "+vini.get(i).getName()+" : "+vini.get(i).getCont());
		}


	}

	public void quantBottProduttore(){
		for(int i=0;i<vini.size();i++){
			System.out.println("vino:"+vini.get(i).getProduttore()+" : "+vini.get(i).getCont());
		}

	}
	public void possGuadagno(String valuta){
		double guadagno = 0;
		for(int i=0;i<vini.size();i++){
			if(vini.get(i).getValuta().equalsIgnoreCase(valuta)){
				guadagno += vini.get(i).getPrezzo()*vini.get(i).getCont();
			}else{
				if(valuta.equals(dollaro.getValuta())){
					guadagno += vini.get(i).getPrezzo()*vini.get(i).getCont()*dollaro.getTassoRiferimento();
				}else{
					guadagno += vini.get(i).getPrezzo()*vini.get(i).getCont()*euro.getTassoRiferimento();
				}
			}
		}
		System.out.println("il valore totale di tutte le bottiglie è di:"+guadagno+valuta);
	}	

	public void annata(){
		System.out.println("inserisci la prima data");
		int anno1=lettore.nextInt();
		System.out.println("inserisci la seconda data");
		int anno2=lettore.nextInt();
		int min=0;
		int max=0;
		if(anno1<anno2){//imposta anno Max e min
			min=anno1;
			max=anno2;
		}
		else{
			min=anno2;
			max=anno1;
		}
		
		for(int i=0;i<vini.size();i++){
			if(vini.get(i).getData()>=min && vini.get(i).getData()<=max)
				System.out.println(vini.get(i).toString());
		}



	}


}
