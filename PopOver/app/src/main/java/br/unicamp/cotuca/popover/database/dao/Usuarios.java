package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Usuario;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuarios {
    
    private final ConexaoMySQL CONEX;
    
    public Usuarios() throws Exception {
        CONEX = new ConexaoMySQL();
    }
    
    public void incluir(Usuario usuario) throws Exception {
        String cmd;
        
        cmd = "INSERT INTO pUsuario (login,nome,senha,email,reputacao) VALUES ("
                + "'" + usuario.getLogin()  + "',"
                + "'" + usuario.getNome()   + "',"
                + "'" + usuario.getSenha()  + "',"
                + "'" + usuario.getEmail()  + "',"
                + usuario.getReputacao()    + ")";
        
        try {
            CONEX.execComando(cmd);
        } catch (SQLException e) {
            throw new Exception("Usuario ja cadastrado");
        }
    }
    
    public boolean existeUsuario(int id) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pUsuario WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }

    public boolean existeUsuarioLogin(String login) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pUsuario WHERE login='" + login + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }
    
    public boolean existeUsuarioEmail(String email) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pUsuario WHERE email='" + email + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }

    public boolean confirmarLogin(String email, String senha) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pUsuario WHERE email='" + email + "' AND senha='" + senha + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }
    
    public void excluir(int id) throws Exception {
        String cmd;
        if (this.existeUsuario(id)) {
            cmd = "DELETE FROM pUsuario WHERE id =" + id;
            CONEX.execComando(cmd);
        } else {
            throw new Exception("Impossivel excluir, usuario não existente");
        }
    }
    
    public void alterar(Usuario usuario) throws Exception {
        
        if (!existeUsuario(usuario.getID())) {
            throw new Exception("Usuario nao cadastrado");
        }
        
        String cmd;
        cmd = "UPDATE pUsuario SET "
                + "nome = '" + usuario.getNome() + "', "
                + "senha = '" + usuario.getSenha() + "', "
                + "email = '" + usuario.getEmail() + "', "
                + "login = '" + usuario.getLogin() + "', "
                + "reputacao = " + usuario.getReputacao() 
                + " WHERE id = " + usuario.getID(); 
        CONEX.execComando(cmd);
        
    }
    
    public Usuario getUsuario(int id) throws Exception {
        
        if (!existeUsuario(id)) {
            return null;
        }
        /*
         declarar aqui os tipos de retorno 
         ex: String, int
         */
        String qry = "SELECT * FROM pUsuario WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Usuario usuario = new Usuario(resultado.getInt("id"), resultado.getString("login"), resultado.getString("nome"), resultado.getString("senha"),
                resultado.getString("email"), resultado.getInt("reputacao"));
        return usuario;
    }
    
    public int getUsuarioID(String login) throws Exception {
        
        if (!existeUsuarioLogin(login)) {
            return -1;
        }        
        String qry = "SELECT * FROM pUsuario WHERE login='" + login + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        return resultado.getInt("id");
    }
    
    public ResultSet getUsuarios() throws Exception {
        String qry = "SELECT * FROM pUsuario";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }
    
    public ResultSet getEntidadesSeguidas(int id) throws Exception{
       if (!existeUsuario(id)) {
            throw new Exception("Usuario nao cadastrado");
        }
        String qry = "SELECT * FROM pUsuarioEntidade WHERE idUsuario=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;     
    }
    
    public void fechaConec() {
        try {
            CONEX.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
