package br.unicamp.cotuca.popover.database.ado;


public class Usuario {

    int id;
    String login;
    String nome;
    String senha;
    String email;
    int reputacao = 0;

    public Usuario(int id,String login, String nome, String senha, String email, int reputacao) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.reputacao = reputacao;
    }
    public Usuario(String login, String nome, String senha, String email, int reputacao) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.reputacao = reputacao;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getID() {
        return id;
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

    public void setReputacao(int reputacao) {
        this.reputacao = reputacao;
    }

    public void incReputacao(int reputacao) {
        this.reputacao += reputacao;
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

    public int getReputacao() {
        return reputacao;
    }

}
