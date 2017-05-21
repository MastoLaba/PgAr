package ieee.clinica;

import java.util.ArrayList;

public class Pazienti {
    private ArrayList<Paziente> pazienti;
    
    public Pazienti(){ pazienti = new ArrayList<>(); }
    
    public boolean aggiungiPaziente(Paziente p){
        if(getPerID(p.getID()) == null)
            return pazienti.add(p);
        else 
            return false;
    }
    
    public Paziente get(int index){ return pazienti.get(index); }
    
    public int getSize() { return pazienti.size(); }
    
    public Paziente getPerID(int ID){
        for(Paziente p: pazienti)
            if(p.getID() == ID)
                return p;
        return null;
    }
    
    public ArrayList<Paziente> getPerNome(String n) {
        ArrayList<Paziente> paz = new ArrayList<>();
        for(Paziente p: pazienti)
            if(p.getNome().equals(n))
                paz.add(p);
        if(!paz.isEmpty())
            return paz;
        return null;
    }
    
    @Override
    public String toString() {
        String s = "";
        for(Paziente p: pazienti) s += p + "\n";
        return s;
    }
    
}
