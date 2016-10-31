package opencarshop.funcionario.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import opencarshop.funcionario.model.Contrato;
import opencarshop.funcionario.model.Funcionario;
import opencarshop.funcionario.model.FuncionarioDAO;
import opencarshop.Endereco;
import opencarshop.util.Utilidades;

public class FuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    
    // TELA DE AUTENTICACAO
    @FXML
    private Label labelErro;
    @FXML
    private TextField tf_cpf;
    @FXML
    private PasswordField pf_senha;
  
    
    // TELA DE CADASTRO
    @FXML
    private TextField tf_cpfCadastro;
    @FXML
    private PasswordField pf_senhaCadastro;
    @FXML
    private TextField tf_nomeCadastro;
    @FXML
    private DatePicker dp_dataNascimentoCadastro;
    
    @FXML
    private ComboBox<String> cb_tipoCadastro;
    @FXML
    private TextField tf_emailCadastro;
    @FXML
    private TextField tf_telefone1Cadastro;   
    @FXML
    private TextField tf_telefone2Cadastro;
    @FXML
    private TextField tf_ruaCadastro;
    @FXML
    private TextField tf_cidadeCadastro;
    @FXML
    private TextField tf_estadoCadastro;
    @FXML
    private TextField tf_bairroCadastro;
    @FXML
    private TextField tf_cepCadastro;
    @FXML
    private TextField tf_numeroCadastro;
    @FXML
    private TextField tf_complementoCadastro;
    
    @FXML
    private TextField tf_salarioCadastro;
    @FXML
    private ComboBox<String> cb_cargoCadastro;
    @FXML
    private DatePicker dp_dataInicioCadastro;
    @FXML
    private DatePicker dp_dataTerminoCadastro;
   
    @FXML
    private Label resultadoCadastro;
    
    // TABELA FUNCIONARIO
    @FXML
    private TableColumn<Funcionario, String> col_nome;
    @FXML
    private TableColumn<Funcionario, String> col_cpf;
    @FXML
    private TableColumn<Funcionario, String> col_telefone1;
    @FXML
    private TableColumn<Funcionario, String> col_telefone2;
    @FXML
    private TableColumn<Funcionario, String> col_email;
    
    @FXML
    private TableView<Funcionario> tbl_funcionario;
    
        @FXML
    private CheckBox cb_ativo;

    @FXML
    private Label confirmaAlteracao;
    
    static Stage stageAnterior;

    public static void setPrevStage(Stage stage){
         FuncionarioController.stageAnterior = stage;
    }
    
    
    @FXML
    private void autenticar(ActionEvent event) 
    {
        Funcionario funcionario;
        FuncionarioDAO func = new FuncionarioDAO();
        funcionario = func.getFuncionario(tf_cpf.getText());
        
        if(funcionario.getCpf() != null)
        {
            if(funcionario.getSenha().equals(pf_senha.getText()))
            {
                Parent root = null;
                try 
                {
                    root = FXMLLoader.load(getClass().getResource("/opencarshop/TelaPrincipal.fxml"));
                    Scene scene = new Scene(root);
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    //nStage.setMaximized(true);
                    nStage.setMaxHeight(768);
                    nStage.setMaxWidth(1024);
                    nStage.setTitle("OpenCarShop");
                    nStage.setResizable(false);
                    nStage.show();
                    //Stage stage = (Stage) cadastroLink.getScene().getWindow();
                    
                    
                    stageAnterior.close();
                    //stage.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            else
            {
                labelErro.setText("Login ou senha errado!!!");
            }
        }
        else
        {
            labelErro.setText("Login ou senha errado!!!");
        }
    }
    
    @FXML
    private void cadastrar(ActionEvent event) throws ParseException {
        //cb_cargoCadastro.setItems(cargos);
        Funcionario  func    = new Funcionario();
        Endereco     end     = new Endereco();
        Contrato     contr   = new Contrato();
        FuncionarioDAO f     = new FuncionarioDAO();
        Utilidades u = new Utilidades();
      
        // OBJETO FUNCIONARIO
        func.setCpf(tf_cpfCadastro.getText());
        func.setNome(tf_nomeCadastro.getText());
        func.setSenha(pf_senhaCadastro.getText());
        func.setDataNascimento(dp_dataNascimentoCadastro.getValue());
        func.setDataNascimento(dp_dataNascimentoCadastro.getValue());
        func.setEmail(tf_emailCadastro.getText());
        func.setTelefone1(tf_telefone1Cadastro.getText());
        func.setTelefone2(tf_telefone2Cadastro.getText());

        // OBJETO ENDERECO
        end.setCEP(tf_cepCadastro.getText());
        end.setEstado(tf_estadoCadastro.getText());
        end.setCidade(tf_cidadeCadastro.getText());
        end.setBairro(tf_bairroCadastro.getText());
        end.setRua(tf_ruaCadastro.getText());
        end.setNumero(Integer.parseInt(tf_numeroCadastro.getText()));
        end.setComplemento(tf_complementoCadastro.getText());
        end.setTipo(cb_tipoCadastro.getValue().charAt(0));

        // OBJETO CONTRATO
        contr.setCargo( cb_cargoCadastro.getValue().charAt(0));
        contr.setSalario(DecimalFormat.getInstance().parse(tf_salarioCadastro.getText()).doubleValue());
        contr.setDataInicio(dp_dataInicioCadastro.getValue());
        contr.setDataTermino(dp_dataTerminoCadastro.getValue());
	
        
        if(f.cadastraFuncionario(func, end, contr))
        {
            resultadoCadastro.setText("Cadastrado com sucesso!!");
        }
        else
        {
            resultadoCadastro.setText("Erro ao cadastrar!! Tente novamente.");
        }
    }
    
 @FXML
    private void alterarCadastro(ActionEvent event) throws Exception {
        Funcionario  func    = new Funcionario();
        func.setCpf(tf_cpfCadastro.getText());
        func.setNome(tf_nomeCadastro.getText());
        func.setSenha(pf_senhaCadastro.getText());
        func.setDataNascimento(dp_dataNascimentoCadastro.getValue());
        func.setEmail(tf_emailCadastro.getText());
        func.setTelefone1(tf_telefone1Cadastro.getText());
        func.setTelefone2(tf_telefone2Cadastro.getText());
        func.setAtivo(cb_ativo.isSelected());
        
        FuncionarioDAO f     = new FuncionarioDAO();
        if(f.alteraFuncionario(func))
        {
            confirmaAlteracao.setText("Alteração realizada com sucesso!!");
        }else
        {
            confirmaAlteracao.setText("Erro ao realizar a alteração!!");
        }
    }
   public void selecionarItemTablelaFuncionario(Funcionario funcionario){
        if (funcionario.getCpf() != null) {
              tf_cpfCadastro.setText(funcionario.getCpf());
              tf_nomeCadastro.setText(funcionario.getNome());
              pf_senhaCadastro.setText(funcionario.getSenha());
              tf_emailCadastro.setText(funcionario.getEmail());
              tf_telefone1Cadastro.setText(funcionario.getTelefone1());
              tf_telefone2Cadastro.setText(funcionario.getTelefone2());
              dp_dataNascimentoCadastro.setValue(funcionario.getDataNascimento());
              cb_ativo.setSelected(funcionario.getAtivo());
              
        } 
    }
    
    
    private void carregaTabelaFuncionario() throws Exception
    {
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        col_telefone1.setCellValueFactory(new PropertyValueFactory<>("telefone1"));
        col_telefone2.setCellValueFactory(new PropertyValueFactory<>("telefone2"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        FuncionarioDAO f = new FuncionarioDAO();
        List<Funcionario> listaFuncionario = f.getAllFuncionario();
        ObservableList<Funcionario> observableListFuncionatio;

        observableListFuncionatio = FXCollections.observableArrayList(listaFuncionario);
        tbl_funcionario.setItems(observableListFuncionatio);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
try {
            carregaTabelaFuncionario();
            tbl_funcionario.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTablelaFuncionario(newValue));
        } catch (Exception ex) {
            //Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
