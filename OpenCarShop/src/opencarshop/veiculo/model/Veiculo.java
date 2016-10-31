package opencarshop.veiculo.model;

public class Veiculo {

    private String modelo;
    private String versao;
    private int ano;
    private int quantidade;
    private double valor;
    private boolean opcionalVidrosEletricos;
    private boolean opcionalTravasEletricas;
    private boolean opcionalAr;
    private boolean opcionalFarolNeblina;
    private boolean opcionalAltoFalantes;

    public Veiculo() {

    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isOpcionalVidrosEletricos() {
        return opcionalVidrosEletricos;
    }

    public void setOpcionalVidrosEletricos(boolean opcionalVidrosEletricos) {
        this.opcionalVidrosEletricos = opcionalVidrosEletricos;
    }

    public boolean isOpcionalTravasEletricas() {
        return opcionalTravasEletricas;
    }

    public void setOpcionalTravasEletricas(boolean opcionalTravasEletricas) {
        this.opcionalTravasEletricas = opcionalTravasEletricas;
    }

    public boolean isOpcionalAr() {
        return opcionalAr;
    }

    public void setOpcionalAr(boolean opcionalAr) {
        this.opcionalAr = opcionalAr;
    }

    public boolean isOpcionalFarolNeblina() {
        return opcionalFarolNeblina;
    }

    public void setOpcionalFarolNeblina(boolean opcionalFarolNeblina) {
        this.opcionalFarolNeblina = opcionalFarolNeblina;
    }

    public boolean isOpcionalAltoFalantes() {
        return opcionalAltoFalantes;
    }

    public void setOpcionalAltoFalantes(boolean opcionalAltoFalantes) {
        this.opcionalAltoFalantes = opcionalAltoFalantes;
    }

}
