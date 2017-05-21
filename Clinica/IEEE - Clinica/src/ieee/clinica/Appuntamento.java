package ieee.clinica;

public class Appuntamento {
    private Paziente paziente;
    private Dottore dottore;
    private Data data;
    private int codice;
    
    private final String[] codici = { "Rosso", "Giallo", "Nero", "Marrone" };
    
    // Costruttori
    public Appuntamento(){ }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Appuntamento(Paziente paz, Dottore dott, Data dat, int cod) {
        this.paziente = paz;
        this.dottore = dott;
        this.data = dat;
        if(!setCodice(cod)) this.codice = 1;
    }

    // Getters
    public Paziente getPaziente() { return paziente; }
    public Dottore getDottore() { return dottore; }
    public Data getData() { return data; }
    public int getCodice() { return codice; }
    

    // Setters
    public void setPaziente(Paziente paziente) { this.paziente = paziente; }
    public void setDottore(Dottore dottore) { this.dottore = dottore; }
    public void setData(Data data) { this.data = data; }
    public boolean setCodice(int cod) {
        if(cod < 0 || cod > 3) return false;
        this.codice = cod;
        return true; 
    }
    
    @Override
    public String toString() {
        String s = "Il paziente " + paziente + " sa che il giorno " + data;
        s += " sarà malatissimo, avrà addirittura un codice " + codici[codice];
        s += ". (Menomale che lo sa in anticipo).\n";
        s += "Ha prenotato quindi un colloquio con il dottore " + dottore;
        return s += "\n";
    }
    
}
