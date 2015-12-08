package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Problema;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Problemas {
    private final ConexaoMySQL CONEX;


    public Problemas() throws Exception {
        CONEX = new ConexaoMySQL();
    }

    public void incluir(Problema problema) throws Exception {
        String cmd;

        cmd = "INSERT INTO pProblema (descricao,local,foto,lagitude,longitude,categoria) VALUES ("
                + "'" + problema.getDescricao()  + "',"
                + "'" + problema.getLocal()      + "',"
                + "'" + problema.getFoto()       + "',"
                +       problema.getLatitude()   +  "," 
                +       problema.getLongitude()  +  "," 
                +       problema.getCategoria()  +  ")";

        try {
            CONEX.execComando(cmd);
        } catch (SQLException e) {
            throw new Exception("Evento já cadastrada");
        }
    }
    
    public boolean existeProblema(int id) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pProblema WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) 
            return false;
        return true;
    }
    
     public boolean existeProblema(int categoria, float latitude, float longitude) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pProblema WHERE categoria=" + categoria + "AND latitude = " 
                + latitude + " AND longitude=" + longitude ;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }
    
    public void excluir(int id) throws Exception {
        String cmd;
        if (this.existeProblema(id)) {
            cmd = "DELETE FROM pProblema WHERE id=" + id;
            CONEX.execComando(cmd);
        } else 
            throw new Exception("Impossivel excluir, problema não existente");
    }
    

    public void alterar(Problema problema ) throws Exception {
        
        if (!existeProblema(problema.getId())) {
            throw new Exception("Impossivel alterar, problema não cadastrado");
        }

        String cmd;
        cmd = "UPDATE pProblema SET " +
              "categoria = "  + problema.getCategoria() + ", " +
              "descricao = '" + problema.getDescricao() + "', "+
              "foto = '"      + problema.getFoto()      + "', "+
              "local ='"      + problema.getLocal()     + "', "+
              "latitude ="    + problema.getLatitude()  + ", " +
              "longitude ="   + problema.getLongitude() +
              " WHERE id  ="    + problema.getId();
            
        CONEX.execComando(cmd);
    }

    public Problema getProblema(int id) throws Exception {
        
       if (!existeProblema(id)) {
           return null;
        } 
       /*
         declarar aqui os tipos de retorno 
         ex: String, int
       */
        String qry = "SELECT * FROM pProblema WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Problema problema = new Problema(id,resultado.getString("descricao"),resultado.getString("local"),
                resultado.getFloat("latitude"),resultado.getFloat("longitude"),resultado.getString("foto"),
                resultado.getInt("categoria"));
        return problema;
    }

    public ResultSet getProblemas() throws Exception {
        String qry = "SELECT * FROM pProblema";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }
     public int getEventoID(int categoria, float latitude, float longitude) throws Exception {
        
       if (!existeProblema(categoria, latitude,longitude)) {
           return -1;
        } 
       /*
         declarar aqui os tipos de retorno 
         ex: String, int
       */
        String qry = "SELECT * FROM pProblema WHERE categoria=" + categoria + "AND latitude = " + latitude 
                + " AND longitude=" + longitude ;
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
