package model;

public class Matriculas {

    private int id;
    private int usuario_id;
    private String email;
    private double valor;
    private String plano;
    private String data_inicio;
    private String data_fim;
    private String status;

    public Matriculas(int usuario_id, String email , String plano , double valor , String data_inicio, String data_fim, String status){
        this.usuario_id = usuario_id;
        this.email = email;
        this.plano = plano;
        this.valor = valor;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.status = status;
    }
    public Matriculas(){}

    //Getters


    public int getId() {
        return id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public String getEmail(){
        return email;
    }

    public String getPlano() {
        return plano;
    }

    public double getValor() {
        return valor;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public String getData_fim() {
        return data_fim;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
