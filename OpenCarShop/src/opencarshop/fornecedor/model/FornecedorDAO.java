/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.fornecedor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.Endereco;
import opencarshop.util.ConexaoMySQL;
import opencarshop.util.Utilidades;

/**
 *
 * @author JomarR
 */
public class FornecedorDAO {
    
public Fornecedor getFornecedor(String cnpj)
    {
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        Utilidades u = new Utilidades();
        
        String query = "SELECT * FROM Fornecedor WHERE cnpj=?";
        
        Fornecedor fornecedor = new Fornecedor();
        try
        {
            conn = c.conectar();
            stmt = conn.prepareStatement(query);
            stmt.setString(1,cnpj);
            
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next())
            {
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setDescricao(resultado.getString("razaoSocial"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setTelefone1(resultado.getString("telefone1"));
                fornecedor.setTelefone2(resultado.getString("telefone2"));
                 fornecedor.setDescricao(resultado.getString("descricao"));
                 fornecedor.setAtivo(resultado.getBoolean("ativo"));
            }
            
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return fornecedor;
    }
    
    public boolean cadastraFornecedor(Fornecedor fornecedor, Endereco end)
    {
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        
        PreparedStatement stmtEnd = null;
        
        PreparedStatement stmtFornecedor = null;
        
        String queryEnd = "INSERT INTO Endereco (cep, estado, cidade, bairro, rua, numero, complemento,tipo) VALUES (?,?,?,?,?,?,?,?)";
        String queryFornecedor = "INSERT INTO Fornecedor (cnpj, razaoSocial, email, telefone1, telefone2, descricao, endereco, ativo) VALUES (?,?,?,?,?,?,(select LAST_INSERT_ID()),?)";
       
        
        try
        {
            conn = c.conectar();
            conn.setAutoCommit(false);
            
            stmtEnd = conn.prepareStatement(queryEnd);
            stmtFornecedor = conn.prepareStatement(queryFornecedor);
            
            
            
            stmtEnd.setString(1, end.getCEP());
            stmtEnd.setString(2, end.getEstado());
            stmtEnd.setString(3, end.getCidade());
            stmtEnd.setString(4, end.getBairro());
            stmtEnd.setString(5, end.getRua());
            stmtEnd.setInt(6, end.getNumero());
            stmtEnd.setString(7, end.getComplemento());
            stmtEnd.setString(8, Character.toString(end.getTipo()));
            
            stmtFornecedor.setString(1, fornecedor.getCnpj());
            stmtFornecedor.setString(2, fornecedor.getRazaoSocial());
            stmtFornecedor.setString(3, fornecedor.getEmail());
            stmtFornecedor.setString(4, fornecedor.getTelefone1());
            stmtFornecedor.setString(5, fornecedor.getTelefone2());
            stmtFornecedor.setString(6, fornecedor.getDescricao());
            stmtFornecedor.setBoolean(7, true);
            

          
            
            stmtEnd.execute();
            stmtFornecedor.execute();
            
            
            conn.commit();
            
            conn.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Fornecedor> getAllFornecedor() throws Exception
    {
        String query = "SELECT * FROM Fornecedor";
        List<Fornecedor> retorno = new ArrayList<>();
        Utilidades u = new Utilidades();
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        conn = c.conectar();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setRazaoSocial(resultado.getString("razaoSocial"));
                fornecedor.setEmail(resultado.getString("email"));
                fornecedor.setTelefone1(resultado.getString("telefone1"));
                fornecedor.setTelefone2(resultado.getString("telefone2"));
                fornecedor.setDescricao(resultado.getString("descricao"));
                fornecedor.setAtivo(resultado.getBoolean("ativo"));
                retorno.add(fornecedor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return retorno;
    }
    
    public Boolean alteraFornecedor(Fornecedor fornecedor) throws SQLException
    {
        String query = "UPDATE Fornecedor SET cnpj=?, razaoSocial=?, email=?, telefone1=?, telefone2=?, descricao =?, ativo=? WHERE cnpj=?";
        
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        try {
            conn = c.conectar();
            PreparedStatement stmt = conn.prepareStatement(query);
        
     
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getRazaoSocial());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone1());
            stmt.setString(5, fornecedor.getTelefone2());
            stmt.setString(6, fornecedor.getDescricao());
            stmt.setBoolean(7, fornecedor.getAtivo());
            stmt.setString(8, fornecedor.getCnpj());
            stmt.execute();
            conn.close();
            return true;
        } catch (Exception ex) {
            
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

