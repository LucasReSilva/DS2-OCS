/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author tharlysson breno
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label labelErro;
    
    @FXML
    private TextField login;
    
    @FXML
    private PasswordField senha;
    
    @FXML
    private Hyperlink cadastroLink;
        
    @FXML
    private void enviar(ActionEvent event) {
        if(login.getText().equals("breno") && senha.getText().equals("123"))
        {
            carregaSistema();
        }
        else
        {
            labelErro.setText("Login ou senha errado!!!");
        }
    }
    
    
    @FXML
    private void criarNovaConta(ActionEvent event) throws IOException 
    {
        carregaCadastro();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void carregaCadastro() 
    {
        Parent root = null;
        try 
        {

            root = FXMLLoader.load(getClass().getResource("/opencarshop/view/Cadastro.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            //nStage.setMaximized(true);
            nStage.setMaxHeight(768);
            nStage.setMaxWidth(1024);
            nStage.setTitle("Cadastro - OpenCarShop");
            nStage.setResizable(false);
            nStage.show();
            Stage stage = (Stage) cadastroLink.getScene().getWindow();
            stage.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    private void carregaSistema()
    {
        Parent root = null;
        try 
        {
            root = FXMLLoader.load(getClass().getResource("/opencarshop/view/Sistema.fxml"));

            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            //nStage.setMaximized(true);
            nStage.setMaxHeight(768);
            nStage.setMaxWidth(1024);
            nStage.setTitle("OpenCarShop");
            nStage.setResizable(false);
            nStage.show();
            Stage stage = (Stage) cadastroLink.getScene().getWindow();
            stage.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
