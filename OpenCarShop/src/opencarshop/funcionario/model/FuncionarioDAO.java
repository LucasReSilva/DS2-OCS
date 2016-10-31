package opencarshop.funcionario.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opencarshop.util.ConexaoMySQL;
import opencarshop.Endereco;
import opencarshop.util.Utilidades;

public class FuncionarioDAO {

    public Funcionario getFuncionario(String cpf) {
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        Utilidades u = new Utilidades();

        String query = "SELECT * FROM Funcionario WHERE cpf=?";

        Funcionario funcionario = new Funcionario();
        try {
            conn = c.conectar();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, cpf);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setDataNascimento(u.toLocalDate(resultado.getDate("dataNascimento")));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setTelefone1(resultado.getString("telefone1"));
                funcionario.setTelefone2(resultado.getString("telefone2"));
                funcionario.setAtivo(resultado.getBoolean("ativo"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public boolean cadastraFuncionario(Funcionario func, Endereco end, Contrato cont) {
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;

        PreparedStatement stmtEnd = null;
        PreparedStatement stmtCon = null;
        PreparedStatement stmtFun = null;

        String queryEnd = "INSERT INTO Endereco (cep, estado, cidade, bairro, rua, numero, complemento,tipo) VALUES (?,?,?,?,?,?,?,?)";
        String queryFun = "INSERT INTO Funcionario (cpf, nome, senha, dataNascimento, email, telefone1, telefone2, endereco, ativo) VALUES (?,?,?,?,?,?,?,(select LAST_INSERT_ID()),?)";
        String queryCon = "INSERT INTO Contrato (cargo, salario, dataInicio, dataTermino,funcionario) VALUES (?,?,?,?,?)";

        try {
            conn = c.conectar();
            conn.setAutoCommit(false);

            stmtEnd = conn.prepareStatement(queryEnd);
            stmtFun = conn.prepareStatement(queryFun);
            stmtCon = conn.prepareStatement(queryCon);

            stmtEnd.setString(1, end.getCEP());
            stmtEnd.setString(2, end.getEstado());
            stmtEnd.setString(3, end.getCidade());
            stmtEnd.setString(4, end.getBairro());
            stmtEnd.setString(5, end.getRua());
            stmtEnd.setInt(6, end.getNumero());
            stmtEnd.setString(7, end.getComplemento());
            stmtEnd.setString(8, Character.toString(end.getTipo()));

            stmtFun.setString(1, func.getCpf());
            stmtFun.setString(2, func.getNome());
            stmtFun.setString(3, func.getSenha());
            stmtFun.setDate(4, Date.valueOf(func.getDataNascimento()));
            stmtFun.setString(5, func.getEmail());
            stmtFun.setString(6, func.getTelefone1());
            stmtFun.setString(7, func.getTelefone2());
            stmtFun.setBoolean(8, true);

            stmtCon.setString(1, Character.toString(cont.getCargo()));
            stmtCon.setDouble(2, cont.getSalario());
            stmtCon.setDate(3, Date.valueOf(cont.getDataInicio()));
            stmtCon.setDate(4, Date.valueOf(cont.getDataInicio()));
            stmtCon.setString(5, func.getCpf());

            stmtEnd.execute();
            stmtFun.execute();
            stmtCon.execute();

            conn.commit();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> getAllFuncionario() throws Exception {
        String query = "SELECT * FROM Funcionario";
        List<Funcionario> retorno = new ArrayList<>();
        Utilidades u = new Utilidades();
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        conn = c.conectar();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setDataNascimento(u.toLocalDate(resultado.getDate("dataNascimento")));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setTelefone1(resultado.getString("telefone1"));
                funcionario.setTelefone2(resultado.getString("telefone2"));
                funcionario.setAtivo(resultado.getBoolean("ativo"));

                retorno.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return retorno;
    }

    public Boolean alteraFuncionario(Funcionario func) throws SQLException {
        String query = "UPDATE Funcionario SET nome=?, senha=?, dataNascimento=?, email=?, telefone1=?, telefone2=?, ativo=? WHERE cpf=?";

        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        try {
            conn = c.conectar();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getSenha());
            stmt.setDate(3, Date.valueOf(func.getDataNascimento()));
            stmt.setString(4, func.getEmail());
            stmt.setString(5, func.getTelefone1());
            stmt.setString(6, func.getTelefone2());
            stmt.setBoolean(7, func.getAtivo());
            stmt.setString(8, func.getCpf());
            stmt.execute();
            conn.close();
            return true;
        } catch (Exception ex) {

            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
