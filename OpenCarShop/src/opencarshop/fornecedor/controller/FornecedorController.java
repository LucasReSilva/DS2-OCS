package opencarshop.fornecedor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import opencarshop.fornecedor.model.Fornecedor;

public class FornecedorController implements Initializable {


    @FXML
    private TextField textFieldRazaoSocial;
    @FXML
    private TextField textFieldCNPJ;
    @FXML
    private TextField textFieldFornecedorEmail;
    @FXML
    private TextField textFieldFornecedorDescricao;
    @FXML
    private TextField textFieldFornecedorTelefone1;
    @FXML
    private TextField textFieldFornecedorTelefone2;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Fornecedor fornecedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = this.fornecedor;
        this.textFieldRazaoSocial.setText(this.fornecedor.getRazaoSocial());
        this.textFieldCNPJ.setText(this.fornecedor.getCnpj());
        this.textFieldFornecedorEmail.setText(this.fornecedor.getEmail());
        this.textFieldFornecedorDescricao.setText(this.fornecedor.getDescricao());
        this.textFieldFornecedorTelefone1.setText(this.fornecedor.getTelefone1());
        this.textFieldFornecedorTelefone2.setText(this.fornecedor.getTelefone2());
    }

    @FXML
    public void handleButtonConfirmar() {

        //if (validarEntradaDeDados()) {

            //fornecedor.setNome(textFieldClienteNome.getText());
            ///fornecedor.setCpf(textFieldClienteCPF.getText());
            //fornecedor.setTelefone(textFieldClienteTelefone.getText());
            //fornecedor.setEmail(textFieldClienteEmail.getText());
            //fornecedor.setData_nascimento(textFieldClienteDataNascimento.getText());
            
            //buttonConfirmarClicked = true;
            //dialogStage.close();
        //}

    }

    //@FXML
    //public void handleButtonCancelar() {
        //dialogStage.close();
    //}

    /*Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldClienteNome.getText() == null || textFieldClienteNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldClienteCPF.getText() == null || textFieldClienteCPF.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
        }
        if (textFieldClienteTelefone.getText() == null || textFieldClienteTelefone.getText().length() == 0) {
            errorMessage += "Telefone inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    */
     
  
}
