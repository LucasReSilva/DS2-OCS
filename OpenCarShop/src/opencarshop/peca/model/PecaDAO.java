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
import opencarshop.util.ConexaoMySQL;

/**
 *
 * @author Dimitri
 */
public class PecaDAO {

    private Connection connection;
    private final ConexaoMySQL database = new ConexaoMySQL();

    public PecaDAO() {
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

    public boolean inserir(Peca peca) {
        String sql = "INSERT INTO Peca(nome, valor, quantidade, ativa) VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getValor());
            stmt.setInt(3, peca.getQuantidade());
            stmt.setBoolean(4, true);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean atualizar(Peca peca) {
        String sql = "UPDATE Peca SET nome=?, valor=?, quantidade=? , ativa=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getValor());
            stmt.setInt(3, peca.getQuantidade());
            stmt.setBoolean(4, true);
            stmt.setInt(5, peca.getId());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean inativar(Peca peca) {
        String sql = "UPDATE Peca SET nome=? , valor=? , quantidade=? , ativa=?  WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getValor());
            stmt.setInt(3, peca.getQuantidade());
            stmt.setBoolean(4, false);
            stmt.setInt(5, peca.getId());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Peca peca) {
        String sql = "DELETE FROM Peca WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, peca.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Peca> listar() {
        String sql = "SELECT * FROM Peca WHERE ativa = 1";
        List<Peca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Peca peca = new Peca();
                peca.setId(resultado.getInt("id"));
                peca.setNome(resultado.getString("nome"));
                peca.setValor(resultado.getDouble("valor"));
                peca.setQuantidade(resultado.getInt("quantidade"));
                retorno.add(peca);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Peca buscar(Peca peca) {
        String sql = "SELECT * FROM Peca WHERE id=?";
        Peca retorno = new Peca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, peca.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                peca.setNome(resultado.getString("nome"));
                peca.setValor(resultado.getDouble("valor"));
                peca.setQuantidade(resultado.getInt("quantidade"));

                retorno = peca;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
