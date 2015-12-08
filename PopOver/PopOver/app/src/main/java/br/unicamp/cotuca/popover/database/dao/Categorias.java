package br.unicamp.cotuca.popover.database.dao;

import br.unicamp.cotuca.popover.database.ado.Categoria;
import br.unicamp.cotuca.popover.database.bd.ConexaoMySQL;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categorias {
    private final ConexaoMySQL CONEX;
    
    
    public Categorias() throws Exception {
        CONEX = new ConexaoMySQL();
    }
     
    public boolean existeCategoria(int id) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pCategoria WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }   
    
    public boolean existeCategoria(String perg) throws Exception {
        String qry = "SELECT COUNT(*) AS QUANTOS FROM pCategoria WHERE categoria= '" + perg + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        if (resultado.getInt("QUANTOS") == 0) {
            return false;
        }
        return true;
    }  
    
    public Categoria getCategoria(int id) throws Exception {
        
        if (!existeCategoria(id)) {
            return null;
        }
        /*
         declarar aqui os tipos de retorno 
         ex: String, int
         */
        String qry = "SELECT * FROM pCategoria WHERE id=" + id;
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        Categoria categoria = new Categoria(resultado.getInt("id"), resultado.getString("categoria"));
        return categoria;
    }
    
    public int getCategoriaID(String categoria) throws Exception {
        
        if (!existeCategoria(categoria)) {
            return -1;
        }        
        String qry = "SELECT * FROM pCategoria WHERE categoria='" + categoria + "'";
        ResultSet resultado = CONEX.execConsulta(qry);
        resultado.first();
        return resultado.getInt("id");
    }
    
    public ResultSet getCategorias() throws Exception {
        String qry = "SELECT * FROM pCategoria";
        ResultSet resultado = CONEX.execConsulta(qry);
        return resultado;
    }
    
    public void fechaConec() {
        try {
            CONEX.fecharConexao();
        } catch (Exception ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
