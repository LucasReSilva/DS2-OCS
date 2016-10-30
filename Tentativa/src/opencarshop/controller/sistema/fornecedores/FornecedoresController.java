/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.controller.sistema.fornecedores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tharlysson breno
 */
public class FornecedoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void criarFornecedor(ActionEvent event) throws IOException 
    {
        showFornecedores();
    }
     private void showFornecedores()
    {
        Parent root = null;
        try 
        {
            root = FXMLLoader.load(getClass().getResource("/opencarshop/view/Fornecedor.fxml"));

            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            //nStage.setMaximized(true);
            nStage.setMaxHeight(768);
            nStage.setMaxWidth(1024);
            nStage.setTitle("OpenCarShop"); 
            
            // Icone
            nStage.getIcons().add(new Image(getClass().getResourceAsStream("/recursos/icones/car-white.png")));
        
            // Desabilita maximizar
            nStage.setResizable(false);
            
            nStage.setTitle("OpenCarShop");
            nStage.show();
           // Stage stage = (Stage) cadastroLink.getScene().getWindow();
           // stage.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }   
}
