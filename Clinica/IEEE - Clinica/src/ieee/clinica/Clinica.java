package ieee.clinica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class Clinica implements Interfaccia{
    private ArrayList<Dottore> dottori;
    private ArrayList<Appuntamento> appuntamenti;
    private Pazienti pazienti;
    
    public Clinica(){
        dottori = new ArrayList<>();
        appuntamenti = new ArrayList<>();
        pazienti = new Pazienti();
        
        volteChiamate = new ArrayList<>();
    }
    
    public boolean aggiungiPaziente(Paziente p){ return pazienti.aggiungiPaziente(p); }

    public boolean aggiungiPaziente(String nome, int ID){ return aggiungiPaziente(new Paziente(nome, ID)); }
    
    public Paziente getPazientePerID(int ID){ return pazienti.getPerID(ID); }
    
    public ArrayList<Paziente> getPazientePerNome(String n) { return pazienti.getPerNome(n); }
    
    public int getPazientiSize() { return pazienti.getSize(); }
    
    public boolean aggiungiDottore(Dottore d){ 
        if(getDottorePerMatricola(d.getMatricola()) == null)
            return dottori.add(d);
        else return false;
    }
    
    public boolean aggiungiDottore(String nome, int matricola, int anno){ return aggiungiDottore(new Dottore(nome, matricola, anno)); }
    
    public Dottore getDottorePerMatricola(int matricola){
        for(Dottore d: dottori)
            if(d.getMatricola() == matricola)
                return d;
        return null;
    }
    
    public ArrayList<Dottore> getDottorePerNome(String s) {
        ArrayList<Dottore> dot = new ArrayList<>();
        for(Dottore d: dottori){
            if(d.getNome().equals(s)){
                dot.add(d);
            }
        }
        if(!dot.isEmpty()){
            return dot;
        }
        
        return null;
    
    }
    
    public int getDottoriSize() { return dottori.size(); }
    
    private boolean giaOccupato(Dottore d, Data dat) {
        for(int i = 0; i < appuntamenti.size(); i++)
            if(appuntamenti.get(i).getData().confrontaCon(dat) == 0)
                return true;
        return false;
    }
    
    public boolean aggiungiAppuntamento(Paziente paz, Dottore dot, Data dat, int cod){
       
        GregorianCalendar g = new GregorianCalendar(dat.getAnno(), dat.getMese() - 1, dat.getGiorno());
        int giorno = g.get(Calendar.DAY_OF_WEEK)-1;
        int [] giorni = new int[]{6,0,1,2,3,4,5};
        if(dot.isDisponibile(giorni[giorno], dat.getOra()) && !giaOccupato(dot, dat))
            return appuntamenti.add(new Appuntamento(paz, dot, dat, cod));
        else return false;
    }
    
    @Override
    public String stampaMedici() {
        String s="";
        for(Dottore d: dottori) s += d+"\n";
        return s;
    }

    @Override
    public Collection<Orario> stampaOrari(int matricola) {
        Dottore d = getDottorePerMatricola(matricola);
        ArrayList<Orario> a = new ArrayList<>();
        a.add(d.getOrario());
        return a;
    }

    @Override
    public Collection<Appuntamento> stampaAppuntamenti(int matricola) {
        ArrayList<Appuntamento> a = new ArrayList<>();
        for(Appuntamento app: appuntamenti)
            if(app.getDottore().getMatricola()==matricola)
                a.add(app);
        return a;
    }

    @Override
    public String stampaPazienti() { return pazienti.toString(); }

    @Override
    public Collection<Appuntamento> stampaAppuntamenti(Data dataInizio, Data dataFine) {
        ArrayList<Appuntamento> a = new ArrayList<>();
        for(Appuntamento app: appuntamenti)
            if((app.getData().confrontaCon(dataInizio)==-1)&&((app.getData().confrontaCon(dataFine)==1)))
                a.add(app);
        return a;
    }

    @Override
    public String stampaNumAppuntamenti(int matricola) {
        return getDottorePerMatricola(matricola)+", num appuntamenti: "+stampaAppuntamenti(matricola).size();
    }
    
    private ArrayList<Long> volteChiamate;
    @Override
    public void getTime() {
        volteChiamate.add(System.currentTimeMillis());
        if(volteChiamate.size()>1){
            System.out.println("Tempi: ");
            for(int i=1; i<volteChiamate.size(); i++)
                System.out.println("t"+i+": "+(volteChiamate.get(i)-volteChiamate.get(i-1)+" millis")); 
        } 
    }
    
}
