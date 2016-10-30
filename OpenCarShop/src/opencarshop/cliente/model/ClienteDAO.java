package opencarshop.cliente.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.Endereco;
import opencarshop.cliente.model.Cliente;
import opencarshop.funcionario.model.Contrato;
import opencarshop.funcionario.model.Funcionario;
import opencarshop.util.ConexaoMySQL;

public class ClienteDAO {
 
    public boolean cadastraCliente(Cliente cli, Endereco end)
    {
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        
        PreparedStatement stmtEnd = null;
        PreparedStatement stmtCli = null;
        
        String queryEnd = "INSERT INTO Endereco (cep, estado, cidade, bairro, rua, numero, complemento,tipo) VALUES (?,?,?,?,?,?,?,?)";
        String queryFun = "INSERT INTO Cliente  (cpf, nome, dataNascimento, email, telefone1, telefone2, endereco, ativo) VALUES (?,?,?,?,?,?,(select LAST_INSERT_ID()),?)";
        
        
        try
        {
            conn = c.conectar();
            conn.setAutoCommit(false);
            
            stmtEnd = conn.prepareStatement(queryEnd);
            stmtCli = conn.prepareStatement(queryFun);
            
            
            stmtEnd.setString(1, end.getCEP());
            stmtEnd.setString(2, end.getEstado());
            stmtEnd.setString(3, end.getCidade());
            stmtEnd.setString(4, end.getBairro());
            stmtEnd.setString(5, end.getRua());
            stmtEnd.setInt(6, end.getNumero());
            stmtEnd.setString(7, end.getComplemento());
            stmtEnd.setString(8, Character.toString(end.getTipo()));
            
            stmtCli.setString(1, cli.getCpf());
            stmtCli.setString(2, cli.getNome());
            stmtCli.setDate(3, Date.valueOf(cli.getDataNascimento()));
            stmtCli.setString(4, cli.getEmail());
            stmtCli.setString(5, cli.getTelefone1());
            stmtCli.setString(6, cli.getTelefone2());
            stmtCli.setBoolean(7, true);
            
        
            stmtEnd.execute();
            stmtCli.execute();
            
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
}
