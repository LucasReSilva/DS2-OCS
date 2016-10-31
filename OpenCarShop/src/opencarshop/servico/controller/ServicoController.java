package opencarshop.servico.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import opencarshop.servico.model.Servico;
import opencarshop.servico.model.ServicoDAO;

public class ServicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Variavéis  
    @FXML
    private TextField tfdescricao;

    @FXML
    private TextField tfvalor;

    @FXML
    private CheckBox chkValue;

    @FXML
    private TextField tfid;

    @FXML
    private Label labelErroServ;

    @FXML
    private Label confirmaAtualizacao;

//Colunas da tabela listar serviços
    @FXML
    private TableColumn<Servico, String> col_descricao;
    @FXML
    private TableColumn<Servico, Double> col_valor;
    @FXML
    private TableColumn<Servico, String> col_tpvalor;
    @FXML
    private TableView<Servico> tbl_servico;

    //metodos
    @FXML
    public void cadastraServico(ActionEvent event) {
        //istancia objeto serviço para configuraçao de atributos
        Servico servico;
        //instacia objeto para inserção de objeto cadasrtado no banco
        ServicoDAO servDao = new ServicoDAO();

        String descri = tfdescricao.getText();
        String valor = tfvalor.getText();

        //Testando valores do cadastro
        if ((descri.length() > (int) 45) || ("".equals(descri))) {
            labelErroServ.setText("Descrição deve ter até 45 caracteres");

        }
        if (Pattern.matches("[a-zA-Z]+", valor) == true) {
            labelErroServ.setText("por favor insira apenas numeros e virgula/ponto");
        }

        //passando para double
        valor = valor.replace(",", ".");
        Double valorDouble = Double.parseDouble(valor);

        //Criando objeto serviço
        servico = new Servico(descri, valorDouble, chkValue.isSelected());

        if (!servDao.insertServico(servico)) {

            labelErroServ.setText("Serviço cadastrado...retornando ");

        }
    }

    @FXML
    private void carregaTabelaServico() throws Exception {

        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_valor.setCellValueFactory(new PropertyValueFactory<>("valorP"));
        col_tpvalor.setCellValueFactory(new PropertyValueFactory<>("valorF"));

        ServicoDAO serv = new ServicoDAO();
        List<Servico> listaServico = serv.getAllServicos();
        ObservableList<Servico> observableListServico;

        observableListServico = FXCollections.observableArrayList(listaServico);
        tbl_servico.setItems(observableListServico);
    }

    @FXML
    public void alterarServico(ActionEvent event) throws SQLException {
        Servico srv = new Servico();

        srv.setId(Integer.valueOf(tfid.getText()));
        srv.setDescricao(tfdescricao.getText());
        srv.setValorPadrao(Double.valueOf(tfvalor.getText()));
        srv.setValorFixo(chkValue.isSelected());

        ServicoDAO s = new ServicoDAO();

        if (s.alteraServico(srv)) {
            confirmaAtualizacao.setText("Alteração realizada com sucesso!!");
        } else {
            confirmaAtualizacao.setText("Erro ao realizar a alteração!!");
        }

    }

    public void selecionarItemTablelaServico(Servico servico) {
        if (servico.getDescricao() != null) {
            tfdescricao.setText(servico.getDescricao());
            tfvalor.setText(String.valueOf(servico.getValorPadrao()));
            chkValue.setSelected(servico.getValorFixo());
            tfid.setText(String.valueOf(servico.getId()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //System.out.println("Chamou");
            carregaTabelaServico();
            tbl_servico.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTablelaServico(newValue));
        } catch (Exception ex) {

        }

    }

}
