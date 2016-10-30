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

        if (validarEntradaDeDados()) {
            fornecedor.setCnpj(textFieldCNPJ.getText());
            fornecedor.setRazaoSocial(textFieldRazaoSocial.getText());
            fornecedor.setEmail(textFieldFornecedorEmail.getText());
            fornecedor.setDescricao(textFieldFornecedorDescricao.getText());
            fornecedor.setTelefone1(textFieldFornecedorTelefone1.getText());
            fornecedor.setTelefone2(textFieldFornecedorTelefone2.getText());
            
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldCNPJ.getText() == null || textFieldRazaoSocial.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
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
}
