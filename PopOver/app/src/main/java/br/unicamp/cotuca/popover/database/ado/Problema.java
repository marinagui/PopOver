package br.unicamp.cotuca.popover.database.ado;

//import java.awt.Image;

public class Problema {
    int id;
    int categoria; 
    String local;
    float latitude;
    float longitude;
    String descricao; 
    String foto; 

    public Problema(int id,String descricao, String local, float latitude, float longitude, String foto, int categoria){    
        this.id = id;
        this.descricao = descricao;
        this.foto = foto;
        this.local = local;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
}
