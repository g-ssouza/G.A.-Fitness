package model;

public class Planos {
    private int id;
    private int usuario_id;
    private String plano;
    private double valor;
    private int duracao_dias;
    private String status;

    public Planos(int usuario_id, String plano, double valor, int duracao_dias, String status){
        this.usuario_id = usuario_id;
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

    public int getUsuario_id() {
        return usuario_id;
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

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
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
