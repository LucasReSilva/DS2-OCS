package opencarshop.peca.model;

import java.io.Serializable;

public class Peca implements Serializable {
    private int cdPeca;
    private String nome;
    private double preco;
    private int quantidade;

    public Peca() {
        
    }

    public Peca(int cdPeca, String nome, double preco, int quantidade) {
        this.cdPeca = cdPeca;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCdPeca() {
        return cdPeca;
    }

    public void setCdPeca(int cdPeca) {
        this.cdPeca = cdPeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
