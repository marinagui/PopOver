package br.unicamp.cotuca.popover.database.ado;


import java.sql.Date;
import java.sql.Time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author u13195
 */
public class Evento {

    String nome;
    String descricao;
    Date data;
    String local;
    Time hora;
    Float latitude;
    Float longitude;
    int idEntidade;
    int categoria;
    int nota = 0;
    int id;

    public Evento(int id,String nome, String descricao, Date data,Time hora, String local, Float latitude, Float longitude, int idEntidade, int categoria, int nota) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.hora = hora;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idEntidade = idEntidade;
        this.categoria = categoria;
        this.nota = nota;
    }
    
    public Evento(String nome, String descricao, Date data,Time hora, String local, Float latitude, Float longitude, int idEntidade, int categoria, int nota) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.hora = hora;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idEntidade = idEntidade;
        this.categoria = categoria;
        this.nota = nota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(int idEntidade) {
        this.idEntidade = idEntidade;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getNota() {
        return nota;
    }

}
