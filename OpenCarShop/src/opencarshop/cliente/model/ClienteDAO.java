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
import opencarshop.util.ConexaoMySQL;
import opencarshop.util.Utilidades;

public class ClienteDAO {

    private Connection conn;
    private final ConexaoMySQL c = new ConexaoMySQL();

    public boolean cadastraCliente(Cliente cli, Endereco end) {
        Connection conn = null;

        PreparedStatement stmtEnd = null;
        PreparedStatement stmtCli = null;

        String queryEnd = "INSERT INTO Endereco (cep, estado, cidade, bairro, rua, numero, complemento,tipo) VALUES (?,?,?,?,?,?,?,?)";
        String queryFun = "INSERT INTO Cliente  (cpf, nome, dataNascimento, email, telefone1, telefone2, endereco, ativo) VALUES (?,?,?,?,?,?,(select LAST_INSERT_ID()),?)";

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 
    
    
    
    public List<Cliente> getAllCliente() throws Exception
    {
        String query = "SELECT * FROM Cliente";
        List<Cliente> retorno = new ArrayList<>();
        Utilidades u = new Utilidades();
        conn = c.conectar();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                System.out.println(resultado.getString("nome"));
                Cliente cliente = new Cliente();
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setDataNascimento(u.toLocalDate(resultado.getDate("dataNascimento")));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone1(resultado.getString("telefone1"));
                cliente.setTelefone2(resultado.getString("telefone2"));
                cliente.setAtivo(resultado.getBoolean("ativo"));

                retorno.add(cliente);
            }
       } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.close();
        return retorno;
    }

    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM Cliente WHERE cpf=?";
        Cliente retorno = new Cliente();
        Utilidades u = new Utilidades();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setDataNascimento(u.toLocalDate(resultado.getDate("dataNascimento")));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone1(resultado.getString("telefone1"));
                cliente.setTelefone2(resultado.getString("telefone2"));
                cliente.setAtivo(resultado.getBoolean("ativo"));
                retorno = cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Boolean alteraCliente(Cliente cli) throws SQLException {
        String query = "UPDATE Cliente SET nome=?, dataNascimento=?, email=?, telefone1=?, telefone2=?, ativo=? WHERE cpf=?";

        try {
            conn = c.conectar();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, cli.getNome());
            stmt.setDate(2, Date.valueOf(cli.getDataNascimento()));
            stmt.setString(3, cli.getEmail());
            stmt.setString(4, cli.getTelefone1());
            stmt.setString(5, cli.getTelefone2());
            stmt.setBoolean(6, cli.getAtivo());
            stmt.setString(7, cli.getCpf());
            stmt.execute();
            conn.close();
            return true;
        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
    }
}
