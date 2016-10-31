package opencarshop.servico.model;

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
import opencarshop.util.Utilidades;

public class ServicoDAO {

    public boolean insertServico(Servico servico) {

        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = true;
        String query = "INSERT INTO Servico (descrição, valorPadrão, valorFixo) VALUES (?, ?, ?)";

        try {
            conn = c.conectar();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getValorPadrao());
            stmt.setBoolean(3, servico.getValorFixo());
            retorno = stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public List<Servico> getAllServicos() throws Exception {

        String query = "SELECT * FROM Servico";
        List<Servico> retorno = new ArrayList<>();
        Utilidades u = new Utilidades();
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        conn = c.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setDescricao(resultado.getString("descrição"));
                servico.setValorPadrao(resultado.getDouble("valorPadrão"));
                servico.setValorFixo(resultado.getBoolean("valorFixo"));
                servico.setValorF(resultado.getBoolean("valorFixo"));
                servico.setValorP(resultado.getDouble("valorPadrão"));
                servico.setId(resultado.getInt("id"));
                retorno.add(servico);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        conn.close();
        return retorno;
    }

    public Boolean alteraServico(Servico srv) throws SQLException {
        String query = "UPDATE Servico SET descrição=?, valorPadrão=?, valorFixo=? WHERE id=?";

        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        try {
            conn = c.conectar();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, srv.getDescricao());
            stmt.setDouble(2, srv.getValorPadrao());
            stmt.setBoolean(3, srv.getValorFixo());
            stmt.setInt(4, srv.getId());

            stmt.execute();
            conn.close();
            return true;
        } catch (Exception ex) {

            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
