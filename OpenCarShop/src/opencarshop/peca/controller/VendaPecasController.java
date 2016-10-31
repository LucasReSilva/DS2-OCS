/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.peca.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import opencarshop.peca.model.PecaDAO;
import opencarshop.peca.model.ItemPecaDAO;
import opencarshop.peca.model.ItemPeca;
import opencarshop.peca.model.Peca;
import opencarshop.peca.model.VendaPeca;
import opencarshop.peca.model.VendaPecaDAO;
import opencarshop.util.ConexaoMySQL;

public class VendaPecasController implements Initializable {

    @FXML
    private TableView<VendaPeca> tableViewVendas;
    @FXML
    private TableColumn<VendaPeca, Integer> tableColumnVendaCodigo;
    @FXML
    private TableColumn<VendaPeca, LocalDate> tableColumnVendaData;
    @FXML
    private TableColumn<VendaPeca, String> tableColumnVendaValorTotal;
    @FXML
    private TableColumn<VendaPeca, String> tableColumnVendaNomeCliente;

    @FXML
    private TableView<ItemPeca> tableViewPecasVendidas;
    @FXML
    private TableColumn<ItemPeca, Peca> tableColumnNomePeca;
    @FXML
    private TableColumn<ItemPeca, Double> tableColumnValorPeca;
    @FXML
    private TableColumn<ItemPeca, Integer> tableColumnQuantidadePeca;

    @FXML
    private Button buttonInserir;
    @FXML
    private Label labelVendaCodigo;
    @FXML
    private Label labelVendaData;
    @FXML
    private Label labelVendaValor;
    @FXML
    private Label labelVendaPago;
    @FXML
    private Label labelVendaCliente;

    private List<VendaPeca> listVendas;
    private ObservableList<VendaPeca> observableListVendas;

    private final ConexaoMySQL database = new ConexaoMySQL();
    private Connection connection;
    private final VendaPecaDAO vendaDAO = new VendaPecaDAO();
    private final ItemPecaDAO itemDeVendaDAO = new ItemPecaDAO();
    private final PecaDAO pecaDAO = new PecaDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = database.conectar();
            vendaDAO.setConnection(connection);
        } catch (Exception ex) {
            Logger.getLogger(VendaPecasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        carregarTableViewVendas();
        selecionarItemTableViewVendas(null);
        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewVendas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewVendas(newValue));
    }

    public void mostrarItemTable(ItemPeca peca) {
        tableColumnNomePeca.setCellValueFactory(new PropertyValueFactory<>("peca"));
        tableColumnValorPeca.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnQuantidadePeca.setCellValueFactory(new PropertyValueFactory<>("quantidadeVenda"));
    }

    public void selecionarItemTableViewVendas(VendaPeca venda) {
        if (venda != null) {
            labelVendaCodigo.setText(String.valueOf(venda.getId()));
            labelVendaData.setText(String.valueOf(venda.getDataVenda()));
            labelVendaValor.setText(String.format("%.2f", venda.getValor()));
            String pago;
            if (venda.isPago() == true) {
                pago = "Sim";
            } else {
                pago = "Nao";
            }
            labelVendaPago.setText(pago);
            labelVendaCliente.setText(venda.getCliente().getNome());

            for (ItemPeca i : venda.getItemsVendidos()) {
                //tableViewPecasVendidas.getSelectionModel().selectedItemProperty().addListener(
                //(observable, oldValue, newValue) -> mostrarItemTable(i));
                mostrarItemTable(i);
            }

        } else {
            labelVendaCodigo.setText("");
            labelVendaData.setText("");
            labelVendaValor.setText("");
            labelVendaPago.setText("");
            labelVendaCliente.setText("");
        }
    }

    public void carregarTableViewVendas() {
        tableColumnVendaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnVendaData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        tableColumnVendaValorTotal.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnVendaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        listVendas = vendaDAO.listar();

        observableListVendas = FXCollections.observableArrayList(listVendas);
        tableViewVendas.setItems(observableListVendas);
    }

    @FXML
    public void handleButtonInserir() throws IOException, SQLException {
        VendaPeca venda = new VendaPeca();
        List<ItemPeca> listItensDeVenda = new ArrayList<>();
        venda.setItemsVendidos(listItensDeVenda);
        boolean buttonConfirmarClicked = showVendaPecasDialog(venda);

        if (buttonConfirmarClicked) {
            try {
                vendaDAO.inserir(venda);

                connection.setAutoCommit(false);
                vendaDAO.setConnection(connection);
                itemDeVendaDAO.setConnection(connection);
                pecaDAO.setConnection(connection);

                for (ItemPeca itemPeca : venda.getItemsVendidos()) {
                    Peca peca = itemPeca.getPeca();
                    itemPeca.setVendaPeca(vendaDAO.buscarUltimaVenda());
                    itemDeVendaDAO.inserir(itemPeca);
                    peca.setQuantidade(peca.getQuantidade() - itemPeca.getQuantidadeVendida());
                    pecaDAO.atualizar(peca);
                }
                connection.commit();
                carregarTableViewVendas();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(VendaPecasController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(VendaPecasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean showVendaPecasDialog(VendaPeca venda) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VendaPecasDialogController.class.getResource("/opencarshop/peca/view/VendaPecasDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Vendas");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        // Setando a Venda no Controller.
        VendaPecasDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setVenda(venda);
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

}
