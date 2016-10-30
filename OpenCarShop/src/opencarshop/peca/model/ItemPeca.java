package opencarshop.peca.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ItemPeca implements Serializable {

    private int cdVenda;
    private Date data;
    private double valor;
    private boolean pago;
    private String formaPagamento;
    private List<ItemPeca> itensDeVenda;
    private int quantidade;
    private int cdCliente;
    private String nomeCliente;
    

    public ItemPeca() {
       
        
    }

    public ItemPeca(int cdvenda, Date data, double valor, boolean pago ,int cdCliente ,String nomeCliente, int quantidade, String formaPagamento) {
        this.cdVenda = cdvenda;
        this.data = data;
        this.valor = valor;
        this.pago = pago;
        this.formaPagamento = formaPagamento;
        this.cdCliente = cdCliente;
        this.nomeCliente = nomeCliente;
        this.quantidade = quantidade;
    }
    
        /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(int cdVenda) {
        this.cdVenda = cdVenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public List<ItemPeca> getItensDeVenda() {
        return itensDeVenda;
    }

    public void setItensDeVenda(List<ItemPeca> itensDeVenda) {
        this.itensDeVenda = itensDeVenda;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }
    
    
    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    
    /**
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }


    public boolean size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}