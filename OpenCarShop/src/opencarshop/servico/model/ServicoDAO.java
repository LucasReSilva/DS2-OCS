/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.servico.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import opencarshop.util.ConexaoMySQL;

/**
 *
 * @author keomas
 */
public class ServicoDAO {
 
    
    
    public boolean insertServico(Servico servico){
    
    ConexaoMySQL c = new ConexaoMySQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno=true; 
        String query = "INSERT INTO `opencarshop`.`Servico` (`descricao`, `valorPadrao`, `valorFixo`) VALUES (?, ?, ?)";
        
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
    
}
