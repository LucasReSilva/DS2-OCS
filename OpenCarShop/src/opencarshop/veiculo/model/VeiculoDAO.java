package opencarshop.veiculo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import opencarshop.util.ConexaoMySQL;

public class VeiculoDAO {

    public List<Veiculo> getAllVeiculo() throws Exception {
        String query = "SELECT * FROM Veiculo";
        List<Veiculo> retorno = new ArrayList<>();
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        conn = c.conectar();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                Veiculo veiculo = new Veiculo();
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setAno(resultado.getInt("ano"));
                veiculo.setVersao(resultado.getString("versao"));
                veiculo.setQuantidade(resultado.getInt("qntd"));
                veiculo.setValor(resultado.getDouble("valor"));
                veiculo.setOpcionalAltoFalantes(resultado.getBoolean("altoFalantes"));
                veiculo.setOpcionalAr(resultado.getBoolean("ar"));
                veiculo.setOpcionalFarolNeblina(resultado.getBoolean("farolNeblina"));
                veiculo.setOpcionalTravasEletricas(resultado.getBoolean("travasEletricas"));
                veiculo.setOpcionalVidrosEletricos(resultado.getBoolean("vidrosEletricos"));

                retorno.add(veiculo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return retorno;
    }

    /*
    //public Veiculo buscarVeiculo(String modelo)
    public Veiculo buscarVeiculo()
    {
        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        
       // String query = "SELECT * FROM opencarshop.Veiculo Where modelo like ?";
        String query = "SELECT * FROM opencarshop.Veiculo";
        Veiculo veiculo = new Veiculo();
        
        try
        {
            conn = c.conectar();
            stmt = conn.prepareStatement(query);
           // stmt.setString(1,modelo);
            
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next())
            {
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setAno(resultado.getInt("ano"));
                veiculo.setVersao(resultado.getString("versao"));
                veiculo.setQuantidade(resultado.getInt("qntd"));
                veiculo.setValor(resultado.getDouble("valor"));
                veiculo.setOpcionalAltoFalantes(resultado.getBoolean("altoFalantes"));
                veiculo.setOpcionalAr(resultado.getBoolean("ar"));
                veiculo.setOpcionalFarolNeblina(resultado.getBoolean("farolNeblina"));
                veiculo.setOpcionalTravasEletricas(resultado.getBoolean("travasEletricas"));
                veiculo.setOpcionalVidrosEletricos(resultado.getBoolean("vidrosEletricos"));             
            }
            
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return veiculo;
    }*/
    public boolean insertVeiculo(Veiculo veiculo) {

        ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;

        //String query = "INSERT INTO teste(modelo) VALUES(?)";
        String query = "INSERT INTO opencarshop.Veiculo(modelo, ano, versao, quantidade, valor,"
                + " opcionalVidrosEletricos, opcionalTravasEletricas, opcionalAr, opcionalFarolNeblina, opcionalAltoFalantes)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = c.conectar();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(query);

            stmt.setString(1, veiculo.getModelo());
            stmt.setInt(2, veiculo.getAno());
            stmt.setString(3, veiculo.getVersao());
            stmt.setInt(4, veiculo.getQuantidade());
            stmt.setDouble(5, veiculo.getValor());
            stmt.setBoolean(6, veiculo.isOpcionalVidrosEletricos());
            stmt.setBoolean(7, veiculo.isOpcionalTravasEletricas());
            stmt.setBoolean(8, veiculo.isOpcionalAr());
            stmt.setBoolean(9, veiculo.isOpcionalFarolNeblina());
            stmt.setBoolean(10, veiculo.isOpcionalAltoFalantes());

            stmt.execute();
            conn.commit();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
