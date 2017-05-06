/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ieee.secondoes.enoteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.stream.*;

/**
 *
 * @author Ice
 */
public class Enoteca implements Interfaccia{
    private ArrayList <Vino> vini;
    
    public static final double DOLLARO =  1.0931;
//    public static final double STERLINA = 0.8473;
//    public static final double YEN = 123.4217;
//    public static final double FRANCO_SVIZZERO = 1.0847;
//    public static final double EURO = 1.0;
//    private final String[] sValute = {"dollari", "sterline", "yen", "franchi svizzeri", "euro"};
//    private final double[] valute = {DOLLARO, STERLINA, YEN, FRANCO_SVIZZERO, EURO};

    public Enoteca(ArrayList<Vino> vini) { this.vini = vini; }

    public Enoteca() { vini = new ArrayList<>(); }
    
    public Enoteca (String filename) throws FileNotFoundException, XMLStreamException{
        File file = new File(filename);
        if(!file.exists()) return;
        XMLInputFactory fattoria = XMLInputFactory.newInstance();
        XMLStreamReader lettore = fattoria.createXMLStreamReader(new FileInputStream(file));
        String dati = "", val;
        int cont;
        Vino temp = null;
        boolean inizio = false;
        while(lettore.hasNext()) {
            int bandierina;
            bandierina = lettore.next();
            switch(bandierina) {
                case XMLStreamConstants.START_DOCUMENT:
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    if("wines".equals(lettore.getLocalName())) {
                        vini = new ArrayList<>();
                        inizio = true;
                    } else if("wine".equals(lettore.getLocalName()))
                        temp = new Vino();
                    else if ("price".equals(lettore.getLocalName())){
                        val = lettore.getAttributeValue(0).trim();
                        temp.setValuta(val.charAt(0));
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    dati = lettore.getText().trim();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(inizio)
                        switch(lettore.getLocalName()) {
                            case "wine":
                                vini.add(temp);
                                break;
                            case "name":
                                temp.setNome(dati.trim());
                                break;
                            case "date":
                                temp.setAnnata(Integer.parseInt(dati));
                                break;
                            case "cont":
                                try{
                                    cont = Integer.parseInt(dati);
                                }catch(Exception e){ cont = 1; }
                                temp.setQuantita(cont);
                                break;
                            case "geo":
                                temp.setRegione(dati);
                                break;
                            case "farmer":
                                temp.setProduttore(dati);
                                break;
                            case "price":
                                temp.setPrezzo(Double.parseDouble(dati));
                        }
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    break; 
            }
        }
    }
            
    public void addVino(Vino v){ vini.add(v); }

    @Override
    public String stampaVino() {
        String elenco = "";
        for(Vino vino : vini) elenco+=vino.toString() + "\n";
        return elenco.trim();
    }

    @Override
    public long numeroViniPerNomeVino(String nome) {;
        for(Vino v: vini) 
            if(v.getNome().equals(nome)) return v.getQuantita();
        return 0;
    }
    
    @Override
    public long numeroViniPerNomeProduttore(String nProduttore){
        int num = 0;
        for(Vino v : vini) 
            if(v.getProduttore().equals(nProduttore)) num += v.getQuantita();
        return num;
    }
    
    @Override
    public void possibileGuadagno(String sValuta) {
        char valuta = sValuta.charAt(0);
        double prezzo = 0;
        for(Vino v: vini) prezzo += v.getPrezzo();
        if(valuta == '$') prezzo *= DOLLARO;
        System.out.println("L'enoteca vale "+valuta+prezzo);        
    }

    @Override
    public Collection<Vino> stampaFasciaVini(int annoInizio, int annoFine) {
        ArrayList<Vino> ritorno = new ArrayList<>();
        for(Vino v: vini) 
            if(v.getAnnata() >= annoInizio && v.getAnnata() <= annoFine) 
                ritorno.add(v);
        return ritorno;
    }

    @Override
    public String toString() {
        String elenco = "";
        for(Vino vino : vini) 
            elenco+=vino.toString() + "\n";
        return elenco.trim();
    }    
}
