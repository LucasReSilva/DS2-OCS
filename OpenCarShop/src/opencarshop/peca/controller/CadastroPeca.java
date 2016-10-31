/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.peca.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import opencarshop.peca.model.PecaDAO;
import opencarshop.util.ConexaoMySQL;
import opencarshop.peca.model.Peca;

/**
 *
 * @author Dimitri
 */
public class CadastroPeca implements Initializable {

    @FXML
    private TableView<Peca> tableViewPecas;
    @FXML
    private TableColumn<Peca, String> tableColumnPecaNome;
    @FXML
    private TableColumn<Peca, String> tableColumnPecaQuantidade;
    @FXML
    private Label labelPecaCodigo;
    @FXML
    private Label labelPecaNome;
    @FXML
    private Label labelPecaPreco;
    @FXML
    private Label labelPecaQuantidade;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonInativar;

    private List<Peca> listPecas;
    private ObservableList<Peca> observableListPecas;

    //Atributos para manipulação de Banco de Dados
    private final PecaDAO pecaDAO = new PecaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregarTableViewPeca();
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewPecas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPecas(newValue));
    }

    public void carregarTableViewPeca() {
        tableColumnPecaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPecaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        listPecas = pecaDAO.listar();

        observableListPecas = FXCollections.observableArrayList(listPecas);
        tableViewPecas.setItems(observableListPecas);
    }

    public void selecionarItemTableViewPecas(Peca peca) {
        if (peca != null) {
            String cdPeca = String.valueOf(peca.getId());
            labelPecaCodigo.setText(cdPeca);
            labelPecaNome.setText(peca.getNome());
            String preco = String.valueOf(peca.getValor());
            labelPecaPreco.setText(preco);
            String quantidade = String.valueOf(peca.getQuantidade());
            labelPecaQuantidade.setText(quantidade);

        } else {
            labelPecaCodigo.setText("");
            labelPecaNome.setText("");
            labelPecaPreco.setText("");
            labelPecaQuantidade.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException {
        Peca peca = new Peca();
        boolean buttonConfirmarClicked = showCadastroPecaDialog(peca);
        System.out.println(peca.getNome());
        if (buttonConfirmarClicked) {
            pecaDAO.inserir(peca);
            carregarTableViewPeca();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Peca peca = tableViewPecas.getSelectionModel().getSelectedItem();
        if (peca != null) {
            boolean buttonConfirmarClicked = showCadastroPecaDialog(peca);
            if (buttonConfirmarClicked) {
                pecaDAO.atualizar(peca);
                carregarTableViewPeca();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma peca na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonInativar() throws IOException {
        Peca peca = tableViewPecas.getSelectionModel().getSelectedItem();
        if (peca != null) {
            pecaDAO.inativar(peca);
            carregarTableViewPeca();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma peca na Tabela!");
            alert.show();
        }
    }

    public boolean showCadastroPecaDialog(Peca peca) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadastroPecaDialog.class.getResource("/opencarshop/peca/view/CadastroPecaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Pecas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o peca no Controller.
        CadastroPecaDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPeca(peca);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
