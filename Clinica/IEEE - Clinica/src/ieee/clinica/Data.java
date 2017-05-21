package ieee.clinica;

public class Data {
    private int anno, mese, giorno, ora;

    public Data(int anno, int mese, int giorno, int ora) {
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
        this.ora = ora;
    }
    
    public static Data inserisciData(){
        return null;
    }

    // Getters
    public int getAnno() { return anno; }
    public int getMese() { return mese; }
    public int getGiorno() { return giorno; }
    public int getOra() { return ora; }

    // Setters
    public void setAnno(int anno) { this.anno = anno; }        
    public void setMese(int mese) { this.mese = mese; }
    public void setGiorno(int giorno) { this.giorno = giorno; }
    public void setOra(int ora) { this.ora = ora; }
    
    /**
     * Confrona questa data con un'altra
     * @param d2 la seconds data
     * @return -1 se la data d2 viene prima (temporalmente) di questa, 1 se viene dopo, 0 se coincidono
     */
    public int confrontaCon(Data d2){
        int differenza = 0;
        if(anno < d2.getAnno()) differenza = 1;
        else if (anno > d2.getAnno()) differenza = -1;
        else if (mese < d2.getMese()) differenza = 1;
        else if (mese > d2.getMese()) differenza = -1;
        else if (giorno < d2.getGiorno()) differenza = 1;
        else if (giorno > d2.getGiorno()) differenza = -1;
        else if (ora < d2.getOra()) differenza = 1;
        else if (ora > d2.getOra()) differenza = -1;
        return differenza;
    }

    @Override
    public String toString() { return giorno+"/"+mese+"/"+anno+", all'ora "+ora+":00"; }
    
    
}
