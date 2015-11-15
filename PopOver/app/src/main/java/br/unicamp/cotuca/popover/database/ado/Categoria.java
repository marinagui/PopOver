package br.unicamp.cotuca.popover.database.ado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author u13195
 */
public class Categoria {
    
    private int idCategoria;
    private String categoria;
    
    public Categoria (int id, String cat){
     this.idCategoria = id; 
     this.categoria = cat;
    }
    
    public Categoria (String cat){
     this.categoria = cat;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
   

}
