///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package opencarshop.peca.controller;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import opencarshop.peca.model.PecaDAO;
//import opencarshop.peca.model.ItemPecaDAO;
//import javafxmvc.model.database.MysqlConnect;
//import opencarshop.peca.model.ItemPeca;
//
///**
// *
// * @author Dimitri
// */
//public class VendaPecasController implements Initializable{
//     @FXML
//    private TableView<ItemPeca> tableViewVendas;
//    @FXML
//    private TableColumn<ItemPeca, Integer> tableColumnVendaCodigo;
//    @FXML
//    private TableColumn<ItemPeca, LocalDate> tableColumnVendaData;
//    @FXML
//    private TableColumn<ItemPeca, String> tableColumnVendaCliente;
//    @FXML
//    private Button buttonInserir;
//    @FXML
//    private Button buttonAlterar;
//    @FXML
//    private Button buttonRemover;
//    @FXML
//    private Label labelVendaCodigo;
//    @FXML
//    private Label labelVendaData;
//    @FXML
//    private Label labelVendaValor;
//    @FXML
//    private Label labelVendaPago;
//    @FXML
//    private Label labelVendaCliente;
//    @FXML
//    private Label labelItemVendidos;
//    
//
//    private List<ItemPeca> listVendas;
//    private ObservableList<ItemPeca> observableListVendas;
//    
//     //Atributos para manipulação de Banco de Dados
//    private final MysqlConnect database = new MysqlConnect();
//    private final Connection connection = database.connect();
//    
//    private final ItemPecaDAO vendaDAO = new ItemPecaDAO();
//    private final ItemPecaDAO itemDeVendaDAO = new ItemPecaDAO();
//    private final PecaDAO pecaDAO = new PecaDAO();
//    
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        vendaDAO.setConnection(connection);
//        carregarTableViewVendas();
//
//        selecionarItemTableViewVendas(null);
//        
//         // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
//        tableViewVendas.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selecionarItemTableViewVendas(newValue));       
//    }
//    
//    public void selecionarItemTableViewVendas(ItemPeca venda) {
//        if (venda != null) {
//            labelVendaCodigo.setText(String.valueOf(venda.getCdVenda()));
//            labelVendaData.setText(String.valueOf(venda.getData()));
//            labelVendaValor.setText(String.format("%.2f", venda.getValor()));
//            String pago;
//             if(venda.getPago() == true){
//                    pago = "Sim";
//                }
//                else{
//                    pago = "Nao";
//                }
//            labelVendaPago.setText(pago);
//            labelVendaCliente.setText(venda.getNomeCliente());
//            
//            /*StringBuilder x = new StringBuilder();
//            
//            for(int i = 0; i < venda.getItensDeVenda().size() ; i++){
//                Peca peca = venda.getItensDeVenda().get(i).getPeca();
//                x.append(peca.getNome());
//            
//            }
//            labelItemVendidos.setText(x.toString());
//            */
//        } else {
//            labelVendaCodigo.setText("");
//            labelVendaData.setText("");
//            labelVendaValor.setText("");
//            labelVendaPago.setText("");
//            labelVendaCliente.setText("");
//        }
//    }
//       
//       
//    public void carregarTableViewVendas() {
//            tableColumnVendaCodigo.setCellValueFactory(new PropertyValueFactory<>("cdVenda"));
//            tableColumnVendaData.setCellValueFactory(new PropertyValueFactory<>("data"));
//            tableColumnVendaCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
//
//            listVendas = vendaDAO.listar();
//
//            observableListVendas = FXCollections.observableArrayList(listVendas);
//            tableViewVendas.setItems(observableListVendas);
//    }
//        
//    @FXML
//    public void handleButtonInserir() throws IOException, SQLException {
//        ItemPeca venda = new ItemPeca();
//        //List<ItemPeca> listItensDeVenda = new ArrayList<>();
//        //venda.setItensDeVenda(listItensDeVenda);
//        boolean buttonConfirmarClicked = showVendaPecasDialog(venda);
//        System.out.println(venda.getCdCliente());
//        System.out.println(venda.getCdVenda());
//        System.out.println(venda.getData());
//        System.out.println(venda.getFormaPagamento());
//        System.out.println(venda.getValor());
//        System.out.println(venda.getQuantidade());
//   
//        if (buttonConfirmarClicked) {
//             
//            try {
//                connection.setAutoCommit(false);
//                vendaDAO.setConnection(connection);
//                vendaDAO.inserir(venda);
//                itemDeVendaDAO.setConnection(connection);
//                pecaDAO.setConnection(connection);
//                System.out.println(venda.getItensDeVenda());
//                /*for (ItemPeca v : venda.getItensDeVenda()) {
//                    
//                    
//                    Peca peca = v.getPeca();
//                    
//                    itemDeVendaDAO.inserir(peca);
//                    peca.setQuantidade(peca.getQuantidade() - venda.getQuantidade());
//                    
//                    pecaDAO.atualizar(peca);
//                    itemDeVendaDAO.inserir(peca);
//                               
//                    //v.setVenda(vendaDAO.buscarUltimaVenda());
//                    //itemDeVendaDAO.inserir();
//           
//                }
//*/
//                connection.commit();
//                carregarTableViewVendas();
//            } catch (SQLException ex) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex1) {
//                    Logger.getLogger(VendaPecasDialogController.class.getName()).log(Level.SEVERE, null, ex1);
//                }
//                Logger.getLogger(VendaPecasDialogController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//    
//    @FXML
//    public void handleButtonRemover() throws IOException {
//       
//    }
//    
//    
//    public boolean showVendaPecasDialog(ItemPeca venda) throws IOException {
//        
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(VendaPecasDialogController.class.getResource("/javafxmvc/view/VendaPecasDialog.fxml"));
//        AnchorPane page = (AnchorPane) loader.load();
//       
//        // Criando um Estágio de Diálogo (Stage Dialog)
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Registro de Vendas");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        // Setando a Venda no Controller.
//        VendaPecasDialogController controller = loader.getController();
//        controller.setDialogStage(dialogStage);
//        controller.setVenda(venda);
//        //controller.setVenda(venda);
//        // Mostra o Dialog e espera até que o usuário o feche
//        dialogStage.showAndWait();
//        return controller.isButtonConfirmarClicked();
//    }
//    
//}
