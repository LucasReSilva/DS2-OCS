/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.peca.model;

import java.sql.Date;
import java.util.List;
import opencarshop.cliente.model.Cliente;

public class VendaPeca {

    private int id;
    private List<ItemPeca> itemsVendidos;
    private Date dataVenda;
    private double valor;
    private boolean pago;
    private Cliente cliente;

    public VendaPeca() {

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
     * @return the itemsVendidos
     */
    public List<ItemPeca> getItemsVendidos() {
        return itemsVendidos;
    }

    /**
     * @param itemsVendidos the itemsVendidos to set
     */
    public void setItemsVendidos(List<ItemPeca> itemsVendidos) {
        this.itemsVendidos = itemsVendidos;
    }

    /**
     * @return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param dataVenda the dataVenda to set
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
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
     * @return the pago
     */
    public boolean isPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(boolean pago) {
        this.pago = pago;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
