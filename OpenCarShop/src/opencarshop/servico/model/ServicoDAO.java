package opencarshop.servico.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import opencarshop.util.ConexaoMySQL;
import opencarshop.util.Utilidades;

public class ServicoDAO {
 
    
    
    public boolean insertServico(Servico servico){
    
    ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno=true; 
        String query = "INSERT INTO `opencarshop`.`Servico` (`descrição`, `valorPadrão`, `valorFixo`) VALUES (?, ?, ?)";
        
        try{
            conn = c.conectar();
            stmt = conn.prepareStatement(query);
            stmt.setString(1,servico.getDescrição());
            stmt.setDouble(2,servico.getValorPadrão());
            stmt.setBoolean(3,servico.isValorFixo());
            
            
            retorno = stmt.execute();
        
        
        }
        catch(Exception e)
        {
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
            while(resultado.next()){
                Servico servico = new Servico();
                servico.setDescrição(resultado.getString("descrição"));
                servico.setValorP(resultado.getDouble("valorPadrão"));
                servico.setValorF(resultado.getBoolean("valorFixo"));
                retorno.add(servico);
            
            
            
            }
        }
        catch(Exception e){
        e.printStackTrace();
        
    }
        conn.close();
        return retorno;
    }
    
}
