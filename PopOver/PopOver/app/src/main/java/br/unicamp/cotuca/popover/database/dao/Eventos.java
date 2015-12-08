package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Evento;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eventos {
 
    private final ConexaoMySQL CONEX;


    public Eventos() throws Exception {
        CONEX = new ConexaoMySQL();
    }

    public void incluir(Evento evento) throws Exception {
        String cmd;

        cmd = "INSERT INTO pEvento (nome,entidade,descricao,data,horario,avaliacao,latitude,longitude, local, categoria) VALUES ("
                + "'" + evento.getNome()          + "',"
                +       evento.getIdEntidade()    + ","
                + "'" + evento.getDescricao()     + "',"
                + "'" + evento.getData()          + "',"
                + "'" + evento.getHora()          + "',"
                +       evento.getNota()          +  "," 
                +       evento.getLatitude()      +  "," 
                +       evento.getLongitude()     +  "," 
                + "'" + evento.getLocal()         + "',"
                +       evento.getCategoria()     +  ")";

        try {
            CONEX.execComando(cmd);
        } catch (SQLException e) {
            throw new Exception("Evento já cadastrado");
        }
    }
    
    public boolean existeEvento(int id) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pEvento WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) 
            return false;
        return true;
    }
    
    public boolean existeEvento(String nome, int entidade) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pEvento WHERE nome='" + nome + "' AND entidade= " + entidade ;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) 
            return false;
        return true;
    }
    
    public void excluir(int id) throws Exception {
        String cmd;
        if (this.existeEvento(id)) {
            cmd = "DELETE FROM pEvento WHERE id=" + id;
            CONEX.execComando(cmd);
        } else 
            throw new Exception("Impossivel excluir, evento não existente");
    }
    
    public void excluir(String nome, int entidade) throws Exception {
        String cmd;
        if (this.existeEvento(nome,entidade)) {
            cmd = "DELETE FROM pEvento WHERE nome='" + nome + "' AND entidade= " + entidade ;
            CONEX.execComando(cmd);
        } else 
            throw new Exception("Impossivel excluir, evento não existente");
    }

    public void alterar(Evento evento ) throws Exception {
        
        if (!existeEvento(evento.getID())) {
            throw new Exception("Impossivel alterar, evento não cadastrado");
        }

        String cmd;
        cmd = "UPDATE pEvento SET " +
              "nome = '"        + evento.getNome()         + "', "+
              "local = '"       + evento.getLocal()        + "', "+
              "horario = '"     + evento.getHora()         + "', "+
              "data =  '"      + evento.getData()          + "', " +
              "descricao ='"    + evento.getDescricao()    + "', " +
              "entidade ="     + evento.getIdEntidade()    + ", " +
              "latitude ="     + evento.getLatitude()      + ", " +
              "longitude ="    + evento.getLongitude()     + ", " +
              "avaliacao ="    + evento.getNota()          + ", " +
              "categoria ="    + evento.getCategoria()     + 
              " WHERE id  ="    + evento.getID();
            
        CONEX.execComando(cmd);
    }

    public Evento getEvento(int id) throws Exception {
        
       if (!existeEvento(id)) {
           return null;
        } 
       /*
         declarar aqui os tipos de retorno 
         ex: String, int
       */
        String qry = "SELECT * FROM pEvento WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Evento evento = new Evento(id,resultado.getString("nome"), resultado.getString("descricao"), resultado.getDate("data"),
                resultado.getTime("horario"),resultado.getString("local"),resultado.getFloat("latitude"),resultado.getFloat("longitude")
                ,resultado.getInt("entidade"), resultado.getInt("categoria"),resultado.getInt("avalicacao"));
        return evento;
    }

    public ResultSet getEventos() throws Exception {
        String qry = "SELECT * FROM pEvento";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }
     public int getEventoID(int entidade, String nome) throws Exception {
        
       if (!existeEvento(nome, entidade)) {
           return -1;
        } 

        String qry = "SELECT * FROM pEvento WHERE nome='" + nome + "' AND entidade=" + entidade;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        return resultado.getInt("id");
    }
    public void fechaConec(){
        try {
            CONEX.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
