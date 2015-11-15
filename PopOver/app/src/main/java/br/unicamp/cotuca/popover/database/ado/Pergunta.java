package br.unicamp.cotuca.popover.database.ado;

/**
 *
 * @author u13195
 */
public class Pergunta {

    private int idPergunta;
    private String pergunta;
    
    public Pergunta (int id, String perg){
     this.idPergunta = id; 
     this.pergunta = perg;
    }
    
    public Pergunta (String perg){
     this.pergunta = perg;
    }
    
    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

}
