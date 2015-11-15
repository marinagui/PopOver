package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Nota;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Notas {
    private final ConexaoMySQL CONEX;

    public Notas() throws Exception {
        CONEX = new ConexaoMySQL();
    }

    public void incluir(Nota nota) throws Exception {
        String cmd;

        cmd = "INSERT INTO pNota (codEvento,idPergunta,idUsuario,nota) VALUES ("
                + nota.getCodEvento()  + ","
                + nota.getIdPergunta() + ","
                + nota.getIdUsuario()  + ","
                + nota.getNota()       + ")";

        try {
            CONEX.execComando(cmd);
        } catch (SQLException e) {
            throw new Exception("Nota ja cadastrada");
        }
    }

    public boolean existeNota(int codEvento,int idPergunta ,int idUsuario) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pNota WHERE idPergunta= " + idPergunta + " AND idUsuario= " +
                idUsuario + " AND codPergunta = " + codEvento;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }
      
    public void excluir(int codEvento,int idPergunta ,int idUsuario) throws Exception {
        String cmd;
        if (this.existeNota(codEvento,idPergunta,idUsuario)) {
            cmd = "DELETE FROM pNota WHERE idPergunta= " + idPergunta + " AND idUsuario= " +
                idUsuario + " AND codPergunta = " + codEvento;
            CONEX.execComando(cmd);
        } else {
            throw new Exception("Impossivel excluir, nota não existente");
        }
    }
    
    public void alterar(Nota nota) throws Exception {

        if (!existeNota(nota.getCodEvento(), nota.getIdPergunta(), nota.getIdUsuario())) {
            throw new Exception("Nota nao cadastrada");
        }

        String cmd;
        cmd = "UPDATE pNota SET "
                + "nota = " + nota.getNota()            
                + " WHERE codEvento = " + nota.getCodEvento() + "AND idPergunta = " + nota.getIdPergunta()+
                "AND idUsuario = " + nota.getIdUsuario();
        CONEX.execComando(cmd);
    }
    


    public Nota getNota(int codEvento,int idPergunta ,int idUsuario) throws Exception {
        
        if (!existeNota(codEvento,idPergunta,idUsuario)) {
            return null;
        }
        String qry = "SELECT * FROM pNota WHERE idPergunta= " + idPergunta + " AND idUsuario= " +
                idUsuario + " AND codPergunta = " + codEvento;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Nota nota = new Nota(resultado.getInt("idPergunta"),resultado.getInt("nota"),resultado.getInt("idUsuario"),
                resultado.getInt("codEvento"));
        return nota;
    }
 
    public ResultSet getNotas() throws Exception {
        String qry = "SELECT * FROM pNota";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }

    public void fechaConec() {
        try {
            CONEX.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}