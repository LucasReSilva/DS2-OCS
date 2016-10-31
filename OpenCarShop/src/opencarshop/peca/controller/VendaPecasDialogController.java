/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.peca.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import opencarshop.cliente.model.Cliente;
import opencarshop.peca.model.ItemPeca;
import opencarshop.peca.model.Peca;
import opencarshop.peca.model.PecaDAO;
import opencarshop.cliente.model.ClienteDAO;
import opencarshop.peca.model.VendaPeca;

public class VendaPecasDialogController implements Initializable {

    @FXML
    private ComboBox comboBoxVendaCliente;
    @FXML
    private DatePicker datePickerVendaData;
    @FXML
    private CheckBox checkBoxVendaPago;
    @FXML
    private ComboBox comboBoxVendaPeca;
    @FXML
    private TableView<ItemPeca> tableViewItensDeVenda;
    @FXML
    private TableColumn<ItemPeca, Peca> tableColumnItemDeVendaPeca;
    @FXML
    private TableColumn<ItemPeca, Integer> tableColumnItemDeVendaQuantidade;
    @FXML
    private TableColumn<ItemPeca, Double> tableColumnItemDeVendaValor;
    @FXML
    private TextField textFieldVendaValor;
    @FXML
    private TextField textFieldVendaQuantidade;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAdicionar;

    private List<Cliente> listClientes;
    private List<Peca> listPecas;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Peca> observableListPecas;
    private ObservableList<ItemPeca> observableListItensDeVenda;

    private final PecaDAO pecaDAO = new PecaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private VendaPeca venda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregarComboBoxClientes();
        carregarComboBoxPecas();
        tableColumnItemDeVendaPeca.setCellValueFactory(new PropertyValueFactory<>("peca"));
        tableColumnItemDeVendaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeVendida"));
        tableColumnItemDeVendaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    public void carregarComboBoxClientes() {

        try {
            listClientes = clienteDAO.getAllCliente();
            for (Cliente c : listClientes) {
                System.out.println(c.getNome());
            }

        } catch (Exception ex) {
            Logger.getLogger(VendaPecasDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        observableListClientes = FXCollections.observableArrayList(listClientes);
        comboBoxVendaCliente.setItems(observableListClientes);
        System.out.println(comboBoxVendaCliente.getItems());

    }

    public void carregarComboBoxPecas() {
        listPecas = pecaDAO.listar();
        observableListPecas = FXCollections.observableArrayList(listPecas);
        comboBoxVendaPeca.setItems(observableListPecas);
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

    public VendaPeca getVenda() {
        return this.venda;
    }

    public void setVenda(VendaPeca venda) {
        this.venda = venda;
    }

    @FXML
    public void handleButtonAdicionar() {
        Peca peca;
        ItemPeca itemPeca = new ItemPeca();

        if (comboBoxVendaPeca.getSelectionModel().getSelectedItem() != null) {
            peca = (Peca) comboBoxVendaPeca.getSelectionModel().getSelectedItem();
            int quantidade = Integer.parseInt(textFieldVendaQuantidade.getText());
            if (peca.getQuantidade() >= quantidade) {
                itemPeca.setPeca(peca);
                itemPeca.setValor(peca.getValor() * quantidade);
                itemPeca.setQuantidadeVendida(quantidade);

                venda.getItemsVendidos().add(itemPeca);
                venda.setValor(venda.getValor() + itemPeca.getValor());

                observableListItensDeVenda = FXCollections.observableArrayList(itemPeca);
                tableViewItensDeVenda.getItems().add(itemPeca);

                textFieldVendaValor.setText(String.format("%.2f", venda.getValor()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas na escolha do produto!");
                alert.setContentText("Não existe a quantidade de produtos disponíveis no estoque!");
                alert.show();
            }
        }
    }

    public void handleButtonConfirmar() {

        Cliente c = (Cliente) comboBoxVendaCliente.getSelectionModel().getSelectedItem();
        venda.setCliente(c);
        venda.setPago(checkBoxVendaPago.isSelected());

        Date date = java.sql.Date.valueOf(datePickerVendaData.getValue());
        venda.setDataVenda(date);

        buttonConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

}
