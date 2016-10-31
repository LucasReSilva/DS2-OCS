package opencarshop.peca.model;

import java.io.Serializable;

public class Peca implements Serializable {

    private int id;
    private String nome;
    private double valor;
    private int quantidade;
    private boolean ativa;

    public Peca() {

    }

    public Peca(int id, String nome, double preco, int quantidade, boolean ativa) {
        this.id = id;
        this.nome = nome;
        this.valor = preco;
        this.quantidade = quantidade;
        this.ativa = ativa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    /**
     * @return the ativa
     */
    public boolean isAtiva() {
        return ativa == true;
    }

    /**
     * @param ativa the ativa to set
     */
    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
