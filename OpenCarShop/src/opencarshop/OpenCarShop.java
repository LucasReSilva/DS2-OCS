/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class OpenCarShop extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));        
        
        Scene scene = new Scene(root); 
        // Icone
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/recursos/icones/account-circle-white.png")));
        
        // Desabilita maximizar
        stage.setResizable(false);

        // Titulo da janela
        stage.setTitle("Login");

        stage.setScene(scene);
        stage.setMaxHeight(768);
        stage.setMaxWidth(1024);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
