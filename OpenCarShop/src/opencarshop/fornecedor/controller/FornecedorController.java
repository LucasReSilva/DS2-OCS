package opencarshop.fornecedor.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import opencarshop.Endereco;
import opencarshop.fornecedor.model.Fornecedor;
import opencarshop.fornecedor.model.FornecedorDAO;
import opencarshop.util.Utilidades;

public class FornecedorController implements Initializable {

       /**
     * Initializes the controller class.
     */
   
    
    // TELA DE AUTENTICACAO
    @FXML
    private Label labelErro;
    @FXML
    private TextField tf_cpf;
    @FXML
    private Hyperlink cadastroLink;
    
    // TELA DE CADASTRO
    @FXML
    private TextField tf_cnpjCadastro;
    @FXML
    private TextField tf_razaoCadastro;
    @FXML
    private TextField tf_descricaoCadastro;
    
  
    
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
    private Label resultadoCadastro;
    
    // TABELA Fornecedor
    @FXML
    private TableColumn<Fornecedor, String> col_cnpj;
    @FXML
    private TableColumn<Fornecedor, String> col_razaoSocial;
    @FXML
    private TableColumn<Fornecedor, String> col_email;
    @FXML
    private TableColumn<Fornecedor, String> col_telefone1;
    @FXML
    private TableColumn<Fornecedor, String> col_telefone2;
    @FXML
    private TableColumn<Fornecedor, String> col_descricao;
    @FXML
    private TableView<Fornecedor> tbl_fornecedor;
    
  /*  
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
                    Stage stage = (Stage) cadastroLink.getScene().getWindow();
                    stage.close();
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
    */
    @FXML
    private void cadastrar(ActionEvent event) throws ParseException {
    
        Fornecedor  fornecedor    = new Fornecedor();
        Endereco     end     = new Endereco();
        
        FornecedorDAO f     = new FornecedorDAO();
        Utilidades u = new Utilidades();
      
        // OBJETO FORNECEDOR
        fornecedor.setCnpj(tf_cnpjCadastro.getText());
        fornecedor.setRazaoSocial(tf_razaoCadastro.getText());
        fornecedor.setEmail(tf_emailCadastro.getText());
        fornecedor.setTelefone1(tf_telefone1Cadastro.getText());
        fornecedor.setTelefone2(tf_telefone2Cadastro.getText());
        fornecedor.setDescricao(tf_descricaoCadastro.getText());

        // OBJETO ENDERECO
        end.setCEP(tf_cepCadastro.getText());
        end.setEstado(tf_estadoCadastro.getText());
        end.setCidade(tf_cidadeCadastro.getText());
        end.setBairro(tf_bairroCadastro.getText());
        end.setRua(tf_ruaCadastro.getText());
        end.setNumero(Integer.parseInt(tf_numeroCadastro.getText()));
        end.setComplemento(tf_complementoCadastro.getText());
        end.setTipo(cb_tipoCadastro.getValue().charAt(0));

        
        
        if(f.cadastraFornecedor(fornecedor, end))
        {
            resultadoCadastro.setText("Cadastrado com sucesso!!");
        }
        else
        {
            resultadoCadastro.setText("Erro ao cadastrar!! Tente novamente.");
        }
    }
    
    @FXML
    private void buscar(ActionEvent event) throws Exception {

        
    }
    
    
    private void carregaTabelaFornecedor() throws Exception
    {
        col_cnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        col_razaoSocial.setCellValueFactory(new PropertyValueFactory<>("razaoSocial"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_telefone1.setCellValueFactory(new PropertyValueFactory<>("telefone1"));
        col_telefone2.setCellValueFactory(new PropertyValueFactory<>("telefone2"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        FornecedorDAO f = new FornecedorDAO();
        List<Fornecedor> listaFornecedor = f.getAllFornecedor();
        ObservableList<Fornecedor> observableListFornecedor;

        observableListFornecedor = FXCollections.observableArrayList(listaFornecedor);
        tbl_fornecedor.setItems(observableListFornecedor);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregaTabelaFornecedor();
        } catch (Exception ex) {
            //Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
