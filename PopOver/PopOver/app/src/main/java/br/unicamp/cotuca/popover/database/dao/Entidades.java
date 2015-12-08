package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Entidade;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Entidades {
 private final ConexaoMySQL CONEX;


    public Entidades() throws Exception {
        CONEX = new ConexaoMySQL();
    }

    public void incluir(Entidade entidade) throws Exception {
        String cmd;

        cmd = "INSERT INTO pEntidade (email,login,senha,nome,popularidade,categoria) VALUES ("
                + "'" + entidade.getEmail()          + "',"
                + "'" + entidade.getLogin()         + "',"
                + "'" + entidade.getSenha()         + "',"
                + "'" + entidade.getNome()          + "',"
                +       entidade.getPopularidade()  +  "," 
                +       entidade.getCategoria()     +  ")";

        try {
            CONEX.execComando(cmd);
        } catch (SQLException e) {
            throw new Exception("Entidade já cadastrada");
        }
    }

    public boolean existeEntidade(int id) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pEntidade WHERE id=" + id ;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) 
            return false;
        return true;
    }
    
    public boolean existeEntidadeEmail(String email) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pEntidade WHERE email='" + email + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) 
            return false;
        return true;
    }

    public boolean confirmarLogin(String email, String senha) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pEntidade WHERE email='" + email + "' AND senha='" + senha + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }

    public void excluir(int id) throws Exception {
        String cmd;
        if (this.existeEntidade(id)) {
            cmd = "DELETE FROM pEntidade WHERE id =" + id;
            CONEX.execComando(cmd);
        } else 
            throw new Exception("Impossivel excluir, entidade não existente");
    }

    public void alterar(Entidade entidade ) throws Exception {
        
        if (!existeEntidade(entidade.getID())) {
            throw new Exception("Impossivel alterar, entidade não cadastrada");
        }

        String cmd;
        cmd = "UPDATE pEntidade SET " +
              "nome = '"        + entidade.getNome()            + "', " +
              "senha = '"       + entidade.getSenha()           + "', " +
              "login = '"       + entidade.getLogin()           + "', " +
              "popularidade = " + entidade.getPopularidade()    + ", "  +
              "categoria = "    + entidade.getCategoria()       +
              " WHERE id = "     + entidade.getID()              ;           
        CONEX.execComando(cmd);
    }

    public Entidade getEntidade(int id) throws Exception {
        
       if (!existeEntidade(id)) {
           return null;
        } 
       /*
         declarar aqui os tipos de retorno 
         ex: String, int
       */
        String qry = "SELECT * FROM pEntidade WHERE id=" + id ;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Entidade entidade = new Entidade(resultado.getInt("id"),resultado.getString("login"), resultado.getString("nome"), resultado.getString("senha"),
                resultado.getString("CNPJ"),resultado.getInt("popularidade"),resultado.getInt("categoria"));
        return entidade;
    }
    
    public int getEntidadeID(String email) throws Exception {
        
       if (!existeEntidadeEmail(email)) {
           return -1;
        } 

        String qry = "SELECT * FROM pEntidade WHERE email='" + email + "'";
        ResultSet resultado = CONEX.execConsulta(qry);   
        resultado.first();
        return resultado.getInt("id");
    }

    public ResultSet getEntidades() throws Exception {
        String qry = "SELECT * FROM pEntidade";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }
    public ResultSet getUsuariosSeguindo(int id) throws Exception{
       if (!existeEntidade(id)) {
            throw new Exception("Entidade nao cadastrada");
        }
        String qry = "SELECT * FROM pUsuarioEntidade WHERE idEntidade=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;     
    }
    
    public void fechaConec(){
        try {
            CONEX.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}