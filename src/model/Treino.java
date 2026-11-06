package model;

public class Treino {

    private int id;
    private int alunoId;
    private int funcionarioId;
    private String nome;
    private String descricao;
    private String dataCriacao;
    private String status;

    public Treino(){}

    //Getters

    public int getId() {
        return id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    //SETTERS


    public void setId(int id) {
        this.id = id;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
