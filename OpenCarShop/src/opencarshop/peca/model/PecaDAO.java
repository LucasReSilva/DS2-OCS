/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.peca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.peca.model.Peca;

/**
 *
 * @author Dimitri
 */
public class PecaDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Peca peca) {
        String sql = "INSERT INTO peca(nome, preco, quantidade) VALUES(?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getPreco());
            stmt.setInt(3, peca.getQuantidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    
    public boolean atualizar(Peca peca){
         String sql = "UPDATE peca SET nome=?, preco=?, quantidade=? WHERE cdPeca=?";
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getPreco());
            stmt.setInt(3, peca.getQuantidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean remover(Peca peca) {
        String sql = "DELETE FROM peca WHERE cdPeca=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, peca.getCdPeca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public List<Peca> listar() {
        String sql = "SELECT * FROM peca";
        List<Peca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Peca peca = new Peca();
                peca.setCdPeca(resultado.getInt("cdPeca"));
                peca.setNome(resultado.getString("nome"));
                peca.setPreco(resultado.getDouble("preco"));
                peca.setQuantidade(resultado.getInt("quantidade"));
               
                retorno.add(peca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
     
     public Peca buscar(Peca peca) {
        String sql = "SELECT * FROM peca WHERE cdPeca=?";
        Peca retorno = new Peca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, peca.getCdPeca());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                peca.setNome(resultado.getString("nome"));
                peca.setPreco(resultado.getDouble("preco"));
                peca.setQuantidade(resultado.getInt("quantidade"));
     
                retorno = peca;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
