package opencarshop.fornecedor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.fornecedor.model.Fornecedor;

public class FornecedorDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor(cnpj, razaosocial, email, descricao, telefone1,telefone2) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getRazaoSocial());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getDescricao());
            stmt.setString(5, fornecedor.getTelefone1());
            stmt.setString(5, fornecedor.getTelefone2());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /*
    public boolean alterar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET cnpj=?, razaosocial=?, telefone1=? WHERE cnpj=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCpf());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setInt(4, fornecedor.getCdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM fornecedor WHERE cnpj=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getCdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    */
    public List<Fornecedor> listar() {
        String sql = "SELECT * FROM fornecedor";
        List<Fornecedor> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setRazaoSocial(resultado.getString("razaosocial"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setDescricao(resultado.getString("descricao"));
                fornecedor.setTelefone1(resultado.getString("telefone1"));
                fornecedor.setTelefone2(resultado.getString("telefone2"));
            
                retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Fornecedor buscar(Fornecedor fornecedor) {
        String sql = "SELECT * FROM fornecedor WHERE cnpj=?";
        Fornecedor retorno = new Fornecedor();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getCnpj());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setRazaoSocial(resultado.getString("razaosocial"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setDescricao(resultado.getString("descricao"));
                fornecedor.setTelefone1(resultado.getString("telefone1"));
                fornecedor.setTelefone2(resultado.getString("telefone2"));
                retorno = fornecedor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
