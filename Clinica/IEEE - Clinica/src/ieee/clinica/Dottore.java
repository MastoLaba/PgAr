package ieee.clinica;

public class Dottore {
    private String nome;
    private int matricola;
    private int annoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServaSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui;
    private Orario orario;
    
    public Dottore(String nome, int matricola, int a){
        this.nome = nome;
        this.matricola = matricola;
        annoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServaSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui = a;
        orario = new Orario();
        orario.setOrarioDiLavoroPerGiorni(0, 4, 8, 13);
        orario.setOrarioDiLavoroPerGiorni(0, 4, 15, 18);
    }

    // Getters
    public String getNome() { return nome; }
    public int getMatricola() { return matricola; }
    public int getAnnoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServeSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui() { return annoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServaSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui; }    
    public Orario getOrario() { return orario; }
    
    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setMatricola(int matricola) { this.matricola = matricola; }
    public void setAnnoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServeSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui(int annoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServeSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui) { this.annoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServaSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui = annoDiLaureaCheèImportanteCheSiSaMaiCheAQualcunoInPericoloDiVitaServeSapereTuttiIDottoriCheSiSonoLaueratiStoAnnoQui; }
       
    public boolean isDisponibile(int g, int o){ return orario.isDisponibile(g, o); }
    
    @Override
    public String toString(){
        return "Dott. "+nome+" ("+matricola+")";
    }
}
