package br.udesc.weparty.Model;

public class Perfil {
    private String nome;
    private String email;

    public Perfil(User user) {
        this.nome = user.getName();
        this.email = user.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

