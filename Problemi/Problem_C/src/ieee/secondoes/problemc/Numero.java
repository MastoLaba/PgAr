package ieee.secondoes.problemc;

public class Numero {
    private double base;
    private double esponente;

    public Numero() { }

    public Numero(double b, double e) { base = b; esponente = e; }
    
    public void setBase(double b) { base = b; }
    
    public void setEsponente(double e) { esponente = e; }
    
    public double getBase() { return base; }
    
    public double getEsponente() { return esponente; }
    
    public double getValore() { return Math.pow(base, esponente); }
    
    public boolean maggiore(Numero n) {
        boolean r = false;
        if(base == n.getBase() && esponente == n.getEsponente()) return r;
        if(n.getEsponente() > esponente) return !n.maggiore(this);
        
        double e = esponente / n.getEsponente();
        if(Math.pow(base, e) > n.getBase()) r = true;
        return r;
    }
    
    public void ugualia(Numero n) {
        esponente = n.getEsponente();
        base = n.getBase();
    }
    
    public void moltiplica(double n) { base = base * Math.pow(n, 1/esponente); }

    @Override
    protected Numero clone() {
        return new Numero(base, esponente);
    }
    
    @Override
    public String toString() {
        return base + "^" + esponente;
    }
    
}
