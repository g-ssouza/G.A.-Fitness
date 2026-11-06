package model;

public class Usuario {


    //Variaveis usuarios
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private String telefone;
    private String cpf;

    public Usuario(String email, String nome , String senha, String tipo, String telefone, String cpf){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Usuario(){}

    //Getters


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
