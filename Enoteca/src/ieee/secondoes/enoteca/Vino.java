package ieee.secondoes.enoteca;

public class Vino{
    private String nome, produttore, regione;
    private int annata, quantita;
    private double prezzo;
    private char valuta;

    public Vino(String nome, String produttore, int annata, String regione, double prezzo, int quantita) {
        this.nome = nome;
        this.produttore = produttore;
        this.annata = annata;
        this.regione = regione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public Vino() { }

    public int getAnnata() { return annata; }

    public String getNome() { return nome; } 

    public double getPrezzo() {
        switch (valuta) {
            case '€':
                return prezzo;
            case '$':
                return prezzo / Enoteca.DOLLARO;
            default:
                return 0;
        }
    }

    public String getProduttore() { return produttore; }

    public String getRegione() { return regione; }

    public char getValuta() { return valuta; }

    public void setAnnata(int annata) { this.annata = annata; }

    public int getQuantita() { return quantita; }

    public void setNome(String nome) { this.nome = nome; }

    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

    public void setProduttore(String produttore) { this.produttore = produttore; }

    public void setRegione(String regione) { this.regione = regione; }

    public void setValuta(char valuta) { this.valuta = valuta; }

    public void setQuantita(int quantita) { this.quantita = quantita; }   
    
    @Override
    public String toString() {
        return nome + ", " + produttore + " (" + valuta + prezzo + ") x" + quantita;
    }
    
}
