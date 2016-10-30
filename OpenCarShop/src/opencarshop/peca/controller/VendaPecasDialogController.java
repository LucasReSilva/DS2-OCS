///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package opencarshop.peca.controller;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//import javafxmvc.model.dao.ClienteDAO;
//import javafxmvc.model.dao.ItemPecaDAO;
//import javafxmvc.model.dao.PecaDAO;
//import javafxmvc.model.dao.ItemPecaDAO;
//import javafxmvc.model.database.MysqlConnect;
//import javafxmvc.model.domain.Cliente;
//import javafxmvc.model.domain.ItemPeca;
//import javafxmvc.model.domain.Peca;
//import javafxmvc.model.domain.ItemPeca;
//
///**
// *
// * @author Dimitri
// */
//public class VendaPecasDialogController implements Initializable{
//    @FXML
//    private ComboBox comboBoxVendaCliente;
//    @FXML
//    private DatePicker datePickerVendaData;
//    @FXML
//    private CheckBox checkBoxVendaPago;
//    @FXML
//    private ComboBox comboBoxVendaPeca;
//    @FXML
//    private TableView<ItemPeca> tableViewItensDeVenda;
//    @FXML
//    private TableColumn<ItemPeca, Peca> tableColumnItemDeVendaPeca;
//    @FXML
//    private TableColumn<ItemPeca, Integer> tableColumnItemDeVendaQuantidade;
//    @FXML
//    private TableColumn<ItemPeca, Double> tableColumnItemDeVendaValor;
//    @FXML
//    private TextField textFieldVendaValor;
//    @FXML
//    private TextField textFieldVendaQuantidade;
//    @FXML
//    private Button buttonConfirmar;
//    @FXML
//    private Button buttonCancelar;
//    @FXML
//    private Button buttonAdicionar;
//
//    private List<Cliente> listClientes;
//    private List<Peca> listPecas;
//    private ArrayList<ItemPeca> itemsVendido;
//    private ObservableList<Cliente> observableListClientes;
//    private ObservableList<Peca> observableListPecas;
//    private ObservableList<ItemPeca> observableListItensDeVenda;
//
//    //Atributos para manipulação de Banco de Dados
//    private final MysqlConnect database = new MysqlConnect();
//    private final Connection connection = database.connect();
//    private final PecaDAO pecaDAO = new PecaDAO();
//    private final ClienteDAO clienteDAO = new ClienteDAO();
//    private final ItemPecaDAO itemPecaDAO = new ItemPecaDAO();
//    private final ItemPecaDAO vendaPecaDAO = new ItemPecaDAO();
//
//    private Stage dialogStage;
//    private boolean buttonConfirmarClicked = false;
//    private ItemPeca venda;
//    
//    
//    @Override
//   public void initialize(URL url, ResourceBundle rb) {
//        itemsVendido = new ArrayList();
//        clienteDAO.setConnection(connection);
//        pecaDAO.setConnection(connection);
//        carregarComboBoxClientes();
//        carregarComboBoxPecas();
//        tableColumnItemDeVendaPeca.setCellValueFactory(new PropertyValueFactory<>("peca"));
//        tableColumnItemDeVendaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
//        tableColumnItemDeVendaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
//    }
//   
//    public void carregarComboBoxClientes() {
//            listClientes = clienteDAO.listar();
//            observableListClientes = FXCollections.observableArrayList(listClientes);
//            comboBoxVendaCliente.setItems(observableListClientes);
//    }
//
//    public void carregarComboBoxPecas() {
//        listPecas = pecaDAO.listar();
//        observableListPecas = FXCollections.observableArrayList(listPecas);
//        comboBoxVendaPeca.setItems(observableListPecas);
//    }
//    
//     public Stage getDialogStage() {
//        return dialogStage;
//    }
//
//    public void setDialogStage(Stage dialogStage) {
//        this.dialogStage = dialogStage;
//    }
//    
//    public boolean isButtonConfirmarClicked() {
//        return buttonConfirmarClicked;
//    }
//    
//    @FXML
//    public void handleButtonAdicionar() {
//        Peca peca;
//        venda = new ItemPeca();
//       
//        if (comboBoxVendaPeca.getSelectionModel().getSelectedItem() != null) {
//            peca = (Peca) comboBoxVendaPeca.getSelectionModel().getSelectedItem();
//            int quantidade = Integer.parseInt(textFieldVendaQuantidade.getText());
//            if (peca.getQuantidade() >= quantidade) {
//                peca.setQuantidade(peca.getQuantidade() - quantidade);
//                venda.setValor(peca.getPreco() * quantidade);
//                venda.setQuantidade(quantidade);
//                ItemPeca x = new ItemPeca();
//                //x.setPeca(peca);
//               
//                itemsVendido.add(x);          
//                venda.setItensDeVenda(itemsVendido);
//   
//                observableListItensDeVenda = FXCollections.observableArrayList(venda);
//                tableViewItensDeVenda.getItems().add(venda);
//                textFieldVendaValor.setText(String.format("%.2f", venda.getValor() ));
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText("Problemas na escolha do produto!");
//                alert.setContentText("Não existe a quantidade de produtos disponíveis no estoque!");
//                alert.show();
//            }
//        }
//    }
//    
//    public void setVenda(ItemPeca venda){
//        this.venda = venda;
//        //String valor = String.valueOf(venda.getValor());
//        //this.textFieldVendaValor.setText(valor);
//        //String quantidade = String.valueOf(venda.getQuantidade());
//        //this.textFieldVendaQuantidade.setText(quantidade);
//        
//     
//        
//    }
//    
//        
//    public void handleButtonConfirmar() {
//            Cliente c = (Cliente) comboBoxVendaCliente.getSelectionModel().getSelectedItem();
//            //System.out.println(c.getCdCliente());
//            //System.out.println(venda);
//            venda.setCdCliente(c.getCdCliente());
//            venda.setNomeCliente(c.getNome());
//        
//            venda.setData( new Date(21,05,1993));
//            venda.setPago(checkBoxVendaPago.isSelected());
//          
//            buttonConfirmarClicked = true;
//            dialogStage.close();
//    }   
//        
//        
//          @FXML
//    public void handleButtonCancelar() {
//        getDialogStage().close();
//    }
//
//    
//    
//    
//}
