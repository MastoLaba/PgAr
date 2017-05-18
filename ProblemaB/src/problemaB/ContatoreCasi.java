package problemaB;

public class ContatoreCasi {
	public static int caso2P(int valore){
		int scomposto2=(int)(valore/2);
		int contTmp=0;
		contTmp+=scomposto2;
		contTmp++;
		return contTmp;
	}

	public static int caso5P(int valore){
		int contTmp=0;
		int scomposto5=(int)(valore/5);
		int valoreTemp=(int)valore;
		int contatore=scomposto5;
		do{
			contTmp+=caso2P(valoreTemp);
			valoreTemp-=5;	
			contatore--;
		}
		while(contatore>0);
		if((int)valore>=5)
			contTmp+=1;
				return contTmp;
	}
	public static int caso10P(int valore){
		int contTmp=0;
		int scomposto10=(int)(valore/10);
		int valoreTemp=(int)valore;
		int contatore=scomposto10;
		do{
			contTmp+=caso5P(valoreTemp);
			valoreTemp-=10;	
			contatore--;
		}
		while(contatore>0);
		if((int)valore>=10)
			contTmp+=1;
				return contTmp;
	}
	public static int caso20P(int valore){
		int contTmp=0;
		int scomposto20=(int)(valore/20);
		int valoreTemp=(int)valore;
		int contatore=scomposto20;
		do{
			contTmp+=caso10P(valoreTemp);
			valoreTemp-=20;	
			contatore--;
		}
		while(contatore>0);
		if((int)valore>=20)
			contTmp+=1;
				return contTmp;
	}
	public static int caso50P(int valore){
		int contTmp=0;
		int scomposto50=(int)(valore/50);
		int valoreTemp=(int)valore;
		int contatore=scomposto50;
		do{
			contTmp+=caso20P(valoreTemp);
			valoreTemp-=50;	
			contatore--;
		}
		while(contatore>0);
		if((int)valore>=50)
			contTmp+=1;
				return contTmp;
	}
	public static int caso1£(int valore){
		int contTmp=0;
		int scomposto100=(int)(valore/100);
		int valoreTemp=(int)valore;
		int contatore=scomposto100;
		do{
			contTmp+=caso50P(valoreTemp);
			valoreTemp-=100;	
			contatore--;
		}
		while(contatore>0);
		if((int)valore>=100)
			contTmp+=1;
				return contTmp;
	}
	public static int caso2£(int valore){
		if(valore>0){
		int contTmp=0;
		int scomposto200=(int)(valore/200);
		int valoreTemp=(int)valore;
		int contatore=scomposto200;
		do{
			contTmp+=caso1£(valoreTemp);
			valoreTemp-=200;	
			contatore--;
		}
		while(contatore>0);
		if((int)valore>=200)
		contTmp+=1;
			return contTmp;
		}
		else if(valore==0){
			int contTmp=0;
			return contTmp;
		}
		else
			System.out.println("valore errato");
			return -1;
		}
}

