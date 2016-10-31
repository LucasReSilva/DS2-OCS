package opencarshop.veiculo.controller;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import opencarshop.veiculo.model.Veiculo;
import opencarshop.veiculo.model.VeiculoDAO;

public class VeiculoController implements Initializable {

    //Variavéis  
    @FXML
    private Label lb_result;

    @FXML
    private TextField tf_modelo;

    @FXML
    private TextField tf_ano;

    @FXML
    private TextField tf_versao;

    @FXML
    private TextField tf_qntd;

    @FXML
    private TextField tf_valor;

    @FXML
    private CheckBox cb_VidrosEletricos;

    @FXML
    private CheckBox cb_TravasEletricas;

    @FXML
    private CheckBox cb_Ar;

    @FXML
    private CheckBox cb_FarolNeblina;

    @FXML
    private CheckBox cb_AltoFalante;

    //TABELA VEICULO
    @FXML
    private TableColumn<Veiculo, String> col_modelo;

    @FXML
    private TableColumn<Veiculo, String> col_ano;

    @FXML
    private TableColumn<Veiculo, String> col_versao;

    @FXML
    private TableColumn<Veiculo, String> col_qntd;

    @FXML
    private TableColumn<Veiculo, String> col_valor;

    @FXML
    private TableColumn<Veiculo, String> col_vidrosEletricos;

    @FXML
    private TableColumn<Veiculo, String> col_travasEletricas;

    @FXML
    private TableColumn<Veiculo, String> col_ar;

    @FXML
    private TableColumn<Veiculo, String> col_farolNeblina;

    @FXML
    private TableColumn<Veiculo, String> col_altoFalantes;

    // tabela
    @FXML
    private TableView<Veiculo> tbl_veiculo;

    @FXML
    private void cadastrarVeiculo(ActionEvent event) {

        //instanciando objeto
        Veiculo veiculo = new Veiculo();
        //instancia objeto para inserção de objeto cadastrado no banco
        VeiculoDAO veiculoDao = new VeiculoDAO();

        veiculo.setModelo(tf_modelo.getText());
        veiculo.setAno(Integer.parseInt((tf_ano.getText())));
        veiculo.setVersao(tf_versao.getText());
        veiculo.setQuantidade(Integer.parseInt(tf_qntd.getText()));
        veiculo.setValor(Double.parseDouble(tf_valor.getText()));
        veiculo.setOpcionalAltoFalantes(Boolean.parseBoolean(cb_AltoFalante.getText()));
        veiculo.setOpcionalAr(Boolean.parseBoolean(cb_Ar.getText()));
        veiculo.setOpcionalFarolNeblina(Boolean.parseBoolean(cb_FarolNeblina.getText()));
        veiculo.setOpcionalTravasEletricas(Boolean.parseBoolean(cb_TravasEletricas.getText()));
        veiculo.setOpcionalVidrosEletricos(Boolean.parseBoolean(cb_VidrosEletricos.getText()));

        if (veiculoDao.insertVeiculo(veiculo)) {
            lb_result.setText("Veículo cadastrado com sucesso");
        } else {
            lb_result.setText("Erro ao Cadastrar!! tente novamente.");
        }
    }

    private void carregaTabelaVeiculo() throws Exception {
        col_modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        col_versao.setCellValueFactory(new PropertyValueFactory<>("versao"));
        col_ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
        col_qntd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        col_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        col_vidrosEletricos.setCellValueFactory(new PropertyValueFactory<>("vidrosEletricos"));
        col_travasEletricas.setCellValueFactory(new PropertyValueFactory<>("travasEletricas"));
        col_ar.setCellValueFactory(new PropertyValueFactory<>("ar"));
        col_farolNeblina.setCellValueFactory(new PropertyValueFactory<>("farolNeblina"));
        col_altoFalantes.setCellValueFactory(new PropertyValueFactory<>("altoFalantes"));

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        List<Veiculo> listaVeiculo = veiculoDAO.getAllVeiculo();
        ObservableList<Veiculo> observableListVeiculo;

        observableListVeiculo = FXCollections.observableArrayList(listaVeiculo);
        tbl_veiculo.setItems(observableListVeiculo);
    }

    public void selecionarItemTabelaVeiculo(Veiculo veiculo) {
        if (veiculo.getModelo() != null) {
            tf_modelo.setText(veiculo.getModelo());
            tf_versao.setText(veiculo.getVersao());
            tf_ano.setText(String.valueOf(veiculo.getAno()));
            tf_valor.setText(String.valueOf(veiculo.getValor()));
            tf_qntd.setText(String.valueOf(veiculo.getQuantidade()));
            cb_AltoFalante.setText(String.valueOf(veiculo.isOpcionalAltoFalantes()));
            cb_Ar.setText(String.valueOf(veiculo.isOpcionalAr()));
            cb_FarolNeblina.setText(String.valueOf(veiculo.isOpcionalFarolNeblina()));
            cb_TravasEletricas.setText(String.valueOf(veiculo.isOpcionalTravasEletricas()));
            cb_VidrosEletricos.setText(String.valueOf(veiculo.isOpcionalVidrosEletricos()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregaTabelaVeiculo();
         tbl_veiculo.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTabelaVeiculo(newValue));
        } catch (Exception ex) {
            //Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
;
}
