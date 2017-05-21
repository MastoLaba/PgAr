package ieee.clinica;

public class Paziente {
    private String nome;
    private int ID;

    public Paziente(String nome, int ID) {
        this.nome = nome;
        this.ID = ID;
    }

    // Getters
    public String getNome() { return nome; }
    public int getID() { return ID; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setID(int ID) { this.ID = ID; }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
        
}
