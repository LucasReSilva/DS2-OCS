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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.cliente.model.Cliente;
import opencarshop.cliente.model.ClienteDAO;
import opencarshop.util.ConexaoMySQL;

/**
 *
 * @author Dimitri
 */
public class VendaPecaDAO {

    private Connection connection;
    private final ConexaoMySQL database = new ConexaoMySQL();

    public VendaPecaDAO() {
        try {
            connection = database.conectar();
        } catch (Exception ex) {
            Logger.getLogger(VendaPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(VendaPeca vendaPeca) {
        String sql = "INSERT INTO VendaPeca(dataVenda, valor, pago, cpfCliente) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, vendaPeca.getDataVenda());
            stmt.setDouble(2, vendaPeca.getValor());
            stmt.setBoolean(3, vendaPeca.isPago());
            stmt.setString(4, vendaPeca.getCliente().getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public VendaPeca buscar(VendaPeca venda) {
        String sql = "SELECT * FROM VendaPeca WHERE id=?";
        VendaPeca retorno = new VendaPeca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                venda.setId(resultado.getInt("id"));
                venda.setDataVenda(resultado.getDate("dataVenda"));
                venda.setValor(resultado.getDouble("valor"));
                venda.setPago(resultado.getBoolean("pago"));
                cliente.setCpf(resultado.getString("cpf"));
                venda.setCliente(cliente);
                retorno = venda;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public VendaPeca buscarUltimaVenda() {
        String sql = "SELECT max(id) as maximo FROM VendaPeca";
        VendaPeca retorno = new VendaPeca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setId(resultado.getInt("maximo"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<VendaPeca> listar() {
        String sql = "SELECT * FROM VendaPeca";
        List<VendaPeca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                VendaPeca venda = new VendaPeca();
                Cliente cliente = new Cliente();
                List<ItemPeca> itemPeca = new ArrayList();

                venda.setId(resultado.getInt("id"));
                venda.setDataVenda(resultado.getDate("dataVenda"));
                venda.setValor(resultado.getDouble("valor"));
                venda.setPago(resultado.getBoolean("pago"));

                cliente.setCpf(resultado.getString("cpfCliente"));

                //Obtendo os dados completos do Cliente associado à Venda
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                cliente = clienteDAO.buscar(cliente);

                //Obtendo os dados completos dos Itens de Venda associados à Venda
                ItemPecaDAO itemPecaDAO = new ItemPecaDAO();
                itemPecaDAO.setConnection(connection);
                itemPeca = itemPecaDAO.listarPorVenda(venda);

                venda.setCliente(cliente);
                venda.setItemsVendidos(itemPeca);
                retorno.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Map<Integer, ArrayList> listarQuantidadeVendasPorMes() {
        String sql = "select count(id) as contador, extract(year from dataVenda) as ano, extract(month from dataVenda) as mes from VendaPeca group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano"))) {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("contador"));
                    retorno.put(resultado.getInt("ano"), linha);
                } else {
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("contador"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(VendaPecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
