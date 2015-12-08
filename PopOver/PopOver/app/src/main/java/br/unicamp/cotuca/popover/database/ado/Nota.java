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
public class Nota {
    int idPergunta;
    int nota;
    int idUsuario;
    int codEvento;

    public Nota (int idPergunta, int nota, int idUsuario, int codEvento){
        this.idPergunta  = idPergunta;
        this.idUsuario = idUsuario;
        this.nota = nota;
        this.codEvento = codEvento;
    }



    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    

}
