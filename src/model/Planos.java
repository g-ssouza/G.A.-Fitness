package model;

public class Planos {
    private int id;
    private String plano;
    private double valor;
    private int duracao_dias;
    private String status;

    public Planos(String plano, double valor, int duracao_dias, String status){
        this.plano = plano;
        this.valor = valor;
        this.duracao_dias = duracao_dias;
        this.status = status;
    }
    public Planos(){}

    //GETTERS


    public int getId() {
        return id;
    }

    public String getPlano() {
        return plano;
    }

    public double getValor() {
        return valor;
    }

    public int getDuracao_dias() {
        return duracao_dias;
    }

    public String getStatus() {
        return status;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDuracao_dias(int duracao_dias) {
        this.duracao_dias = duracao_dias;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
