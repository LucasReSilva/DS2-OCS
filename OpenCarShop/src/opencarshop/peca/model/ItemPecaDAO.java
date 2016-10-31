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
import opencarshop.cliente.model.ClienteDAO;
import opencarshop.util.ConexaoMySQL;

public class ItemPecaDAO {

    private Connection connection;
    private final ConexaoMySQL database = new ConexaoMySQL();

    public ItemPecaDAO() {
        try {
            connection = database.conectar();
        } catch (Exception ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ItemPeca item) {
        String sql = "INSERT INTO ItemPeca(valor, quantidadeVendida, idPeca , idVenda) "
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, item.getValor());
            stmt.setInt(2, item.getQuantidadeVendida());
            stmt.setInt(3, item.getPeca().getId());
            stmt.setInt(4, item.getVendaPeca().getId());

            stmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public ItemPeca buscar(ItemPeca itemPeca) {
        String sql = "SELECT * FROM itensDeVenda WHERE id=?";
        ItemPeca retorno = new ItemPeca();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemPeca.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Peca peca = new Peca();
                VendaPeca venda = new VendaPeca();
                itemPeca.setId(resultado.getInt("id"));
                itemPeca.setQuantidadeVendida(resultado.getInt("quantidadeVendida"));
                itemPeca.setValor(resultado.getDouble("valor"));

                peca.setId(resultado.getInt("id"));
                venda.setId(resultado.getInt("id"));

                //Obtendo os dados completos do Cliente associado à Venda
                PecaDAO pecaDAO = new PecaDAO();
                pecaDAO.setConnection(connection);
                peca = pecaDAO.buscar(peca);

                VendaPecaDAO vendaDAO = new VendaPecaDAO();
                vendaDAO.setConnection(connection);
                venda = vendaDAO.buscar(venda);

                itemPeca.setPeca(peca);
                itemPeca.setVendaPeca(venda);

                retorno = itemPeca;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public List<ItemPeca> listar() {
        String sql = "SELECT * FROM ItemPeca";
        List<ItemPeca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Peca peca = new Peca();
                ItemPeca item = new ItemPeca();
                VendaPeca venda = new VendaPeca();

                item.setId(resultado.getInt("id"));
                item.setValor(resultado.getDouble("valor"));
                item.setQuantidadeVendida(resultado.getInt("quantidadeVendida"));

                peca.setId(resultado.getInt("id"));
                venda.setId(resultado.getInt("id"));

                PecaDAO pecaDAO = new PecaDAO();
                pecaDAO.setConnection(connection);
                peca = pecaDAO.buscar(peca);

                VendaPecaDAO vendaDAO = new VendaPecaDAO();
                vendaDAO.setConnection(connection);
                venda = vendaDAO.buscar(venda);

                item.setPeca(peca);
                item.setVendaPeca(venda);

                retorno.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<ItemPeca> listarPorVenda(VendaPeca venda) {
        String sql = "SELECT * FROM ItemPeca WHERE id=?";
        List<ItemPeca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemPeca item = new ItemPeca();
                Peca peca = new Peca();
                VendaPeca v = new VendaPeca();
                item.setId(resultado.getInt("id"));
                item.setQuantidadeVendida(resultado.getInt("quantidadeVendida"));
                item.setValor(resultado.getDouble("valor"));

                peca.setId(resultado.getInt("id"));
                v.setId(resultado.getInt("id"));

                //Obtendo os dados completos do Produto associado ao Item de Venda
                PecaDAO pecaDAO = new PecaDAO();
                pecaDAO.setConnection(connection);
                peca = pecaDAO.buscar(peca);

                item.setPeca(peca);
                item.setVendaPeca(v);

                retorno.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
