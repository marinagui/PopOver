package br.unicamp.cotuca.popover.database.ado;


public class Entidade {

    int id;
    String login;
    String nome;
    String senha;
    String email;
    int popularidade;
    int categoria; 

    public Entidade(int id,String login, String nome, String senha, String email, int popularidade, int categoria) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.popularidade = popularidade;
        this.categoria = categoria;
    }
    
    public Entidade(String login, String nome, String senha, String email, int popularidade, int categoria) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.popularidade = popularidade;
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }

    public int getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(int popularidade) {
        this.popularidade = popularidade;
    }
    
    public void incPopularidade(int popularidade) {
        this.popularidade += popularidade;
    }
    public void decPopularidade(int popularidade) {
        this.popularidade -= popularidade;
    }
   
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
