/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.funcionario.controller;

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
import opencarshop.funcionario.model.Funcionario;
import opencarshop.funcionario.model.FuncionarioDAO;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private Label labelErro;
    @FXML
    private TextField tf_cpf;
    @FXML
    private PasswordField pf_senha;
    @FXML
    private Hyperlink cadastroLink;
    @FXML
    private void autenticar(ActionEvent event) 
    {
        Funcionario funcionario;
        FuncionarioDAO func = new FuncionarioDAO();
        funcionario =  (Funcionario) func.getFuncionario(tf_cpf.getText());
        
        if(funcionario.getCpf() != null)
        {
            if(funcionario.getSenha().equals(pf_senha.getText()))
            {
                Parent root = null;
                try 
                {
                    root = FXMLLoader.load(getClass().getResource("/opencarshop/TelaPrincipal.fxml"));
                    Scene scene = new Scene(root);
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    //nStage.setMaximized(true);
                    nStage.setMaxHeight(768);
                    nStage.setMaxWidth(1024);
                    nStage.setTitle("Principal - OpenCarShop");
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
            else
            {
                labelErro.setText("Login ou senha errado!!!");
            }
        }
        else
        {
            labelErro.setText("Login ou senha errado!!!");
        }
    }
    
    @FXML
    private void cadastrar(ActionEvent event) {
        System.out.println("Clicou em cadastrar");
    }
    
    @FXML
    private void buscar(ActionEvent event) {
        System.out.println("Filtrando Busca");
    }
    
}
