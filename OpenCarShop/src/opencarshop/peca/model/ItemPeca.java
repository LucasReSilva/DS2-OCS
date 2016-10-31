package opencarshop.peca.model;

import java.io.Serializable;

public class ItemPeca implements Serializable {

    private int id;
    private int quantidadeVendida;
    private double valor;
    private Peca peca;
    private VendaPeca vendaPeca;

    public ItemPeca() {

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the quantidadeVendida
     */
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    /**
     * @param quantidadeVendida the quantidadeVendida to set
     */
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the peca
     */
    public Peca getPeca() {
        return peca;
    }

    /**
     * @param peca the peca to set
     */
    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    /**
     * @return the vendaPeca
     */
    public VendaPeca getVendaPeca() {
        return vendaPeca;
    }

    /**
     * @param vendaPeca the vendaPeca to set
     */
    public void setVendaPeca(VendaPeca vendaPeca) {
        this.vendaPeca = vendaPeca;
    }

}
