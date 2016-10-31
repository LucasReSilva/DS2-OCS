package opencarshop.peca.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import opencarshop.peca.model.Peca;

public class CadastroPecaDialog implements Initializable {

    @FXML
    private Label labelPecaNome;
    @FXML
    private Label labelPecaPreco;
    @FXML
    private Label labelPecaQuantidade;
    @FXML
    private TextField textFieldPecaNome;
    @FXML
    private TextField textFieldPecaPreco;
    @FXML
    private TextField textFieldPecaQuantidade;

    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Peca peca;

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

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
        this.textFieldPecaNome.setText(peca.getNome());
        String preco = String.valueOf(peca.getValor());
        this.textFieldPecaPreco.setText(preco);
        String quantidade = String.valueOf(peca.getQuantidade());
        this.textFieldPecaQuantidade.setText(quantidade);
    }

    @FXML
    public void handleButtonConfirmar() {
        peca.setNome(textFieldPecaNome.getText());
        Double preco = Double.parseDouble(textFieldPecaPreco.getText());
        peca.setValor(preco);
        int quantidade = Integer.parseInt(textFieldPecaQuantidade.getText());
        peca.setQuantidade(quantidade);

        buttonConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }
}
