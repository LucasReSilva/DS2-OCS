/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.peca.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.peca.model.ItemPeca;

/**
 *
 * @author Dimitri
 */
public class ItemPecaDAO {
    
      private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
     public boolean inserir(ItemPeca venda) {
        String sql = "INSERT INTO venda_peca(data_venda, valor, pago, cdCliente, nomeCliente , cditem_peca,tipo_pagamento ) VALUES(?,?,?,?,?,?,?)";
         try {
            //for(int i = 0; i < venda.getItensDeVenda().size() ; i++){
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setDate(1, venda.getData());
                stmt.setDouble(2, venda.getValor());
                stmt.setBoolean(3,venda.getPago());
                stmt.setInt(4, venda.getCdCliente());
                stmt.setString(5 , venda.getNomeCliente());
               
                
                //int itemPeca = venda.getItensDeVenda().get(0).getCdItemPeca();
                stmt.setInt(6, 0);
                 stmt.setString(7 , "Cartao");
            
                stmt.execute();
            
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     
      public List<ItemPeca> listar() {
        String sql = "SELECT * FROM venda_peca";
        List<ItemPeca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemPeca vendaPeca = new ItemPeca();
                vendaPeca.setData(resultado.getDate("data_venda"));
                String pago;
              
                vendaPeca.setPago(resultado.getBoolean("pago"));
                vendaPeca.setValor(resultado.getDouble("valor"));
                vendaPeca.setCdCliente(resultado.getInt("cdCliente"));
                vendaPeca.setNomeCliente(resultado.getString("nomeCliente"));
                
                //vendaPeca.setItensDeVenda(resultado.);
  
                retorno.add(vendaPeca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    
    
}
