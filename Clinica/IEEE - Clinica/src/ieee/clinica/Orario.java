package ieee.clinica;

public class Orario {
    private boolean[][] a;
    
    private final String[] giorni = {"LUN", "MAR", "MER", "GIO", "VEN", "SAB", "DOM"};

    public Orario() {
        a = new boolean[7][24];
        imposta();
    }
    
    private void imposta() {
        for (boolean[] a1 : a)
            for (int j = 0; j < a1.length; j++) a1[j] = false;
    }
    
    /**
     * Imposta l'orario di lavoro del dottore dall'ora 'p' all'ora 'd' del
     * giorno g
     * @param g giorno (0 - 6)
     * @param p prima ora (0 - 23)
     * @param d ultima ora (0 - 24)
     * @return 
     */
    public boolean setOrarioDiLavoro(int g, int p, int d) {
        if((g < 0 || g >= giorni.length) || (p < 0 || p > 23)
                || (d < 0 || d > 24)) {
            System.out.println("ERRORE formato ora scorretto");
            return false;
        }
        if(p > d) setOrarioDiLavoro(g, d, p);
        while(p < d){ a[g][p] = true; p++; }
        return true;
    }
    
    
    public boolean setOrarioDiFerie(int g, int p, int d) {
        if((g < 0 || g >= giorni.length) || (p < 0 || p > 23)
                || (d < 0 || d > 24)) {
            System.out.println("ERRORE formato ora scorretto");
            return false;
        }
        if(p > d) setOrarioDiLavoro(g, d, p);
        while(p < d){ a[g][p] = false; p++; }
        return true;
    }
    /**
     * Imposta l'orario di lavoro del dottore dall'ora 'p' all'ora 'd' dei
     * giorni compresi tra 'gp' e 'gd'
     * @param gp primo giorno (0 - 6)
     * @param gd ultimo giorno (0 - 6)
     * @param p prima ora (0 - 23)
     * @param d ultima ora (0 - 24)
     * @return ritorno
     */
    public boolean setOrarioDiLavoroPerGiorni(int gp, int gd, int p, int d) {
        boolean ritorno = true;
        while(gp <= gd && ritorno) {
            ritorno = setOrarioDiLavoro(gp, p, d);
            gp++;
        }
        return ritorno;
    }
    
    public boolean setOrarioDiFeriePerGiorni(int gp, int gd, int p, int d) {
        boolean ritorno = true;
        while(gp <= gd && ritorno) {
            ritorno = setOrarioDiFerie(gp, p, d);
            gp++;
        }
        return ritorno;
    }
    
    public boolean isDisponibile(int g, int o) { return a[g][o]; }
    
    /**
     * Non modificare assolutamentissimamente
     * @return s
     */
    @Override
    public String toString() {
        String s = ""; boolean inInt = false, stGio = false;
        for(int i = 0; i < a.length; i ++) {
            for(int j = 0; j < a[i].length; j++) {
                if(a[i][j]) {
                    if(!inInt) {
                        if(!stGio) { s += "\t" + giorni[i] + ": "; stGio = true; }
                        inInt = true; s += j + ":00-";
                    }
                } else 
                    if(inInt) { s += j + ":00 "; inInt = false; }
            }
            if(inInt) s += "24:00";
//            s = s.trim();
            if(stGio) s += "\n";
            inInt = false; stGio = false;
        }
        return "ORARI\n\t" + s.trim();
    }
    
}
