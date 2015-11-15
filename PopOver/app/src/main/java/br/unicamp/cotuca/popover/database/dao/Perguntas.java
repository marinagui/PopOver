package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Pergunta;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Perguntas {
    private final ConexaoMySQL CONEX;
    public Perguntas() throws Exception {
        CONEX = new ConexaoMySQL();
    }
    
    
    
    public boolean existePergunta(int id) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pPergunta WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }   
    
    public boolean existePergunta(String perg) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pPergunta WHERE pergunta= '" + perg + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }  
    
    public Pergunta getPergunta(int id) throws Exception {
        
        if (!existePergunta(id)) {
            return null;
        }
        /*
         declarar aqui os tipos de retorno 
         ex: String, int
         */
        String qry = "SELECT * FROM pPergunta WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Pergunta pergunta = new Pergunta(resultado.getInt("id"), resultado.getString("pergunta"));
        return pergunta;
    }
    
    public int getPerguntaID(String pergunta) throws Exception {
        
        if (!existePergunta(pergunta)) {
            return -1;
        }        
        String qry = "SELECT * FROM pPergunta WHERE pergunta='" + pergunta + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        return resultado.getInt("id");
    }
    
    public ResultSet getPerguntas() throws Exception {
        String qry = "SELECT * FROM pPergunta";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }
    
    public void fechaConec() {
        try {
            CONEX.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(Perguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
