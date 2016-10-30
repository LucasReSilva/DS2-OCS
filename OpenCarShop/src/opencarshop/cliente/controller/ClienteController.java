package opencarshop.cliente.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import opencarshop.Endereco;
import opencarshop.cliente.model.Cliente;
import opencarshop.cliente.model.ClienteDAO;
import opencarshop.funcionario.model.Contrato;
import opencarshop.funcionario.model.Funcionario;
import opencarshop.funcionario.model.FuncionarioDAO;
import opencarshop.util.Utilidades;

public class ClienteController implements Initializable {
    // TELA DE CADASTRO
    @FXML
    private TextField tf_cpfCadastro;
    @FXML
    private TextField tf_nomeCadastro;
    @FXML
    private DatePicker dp_dataNascimentoCadastro;
    @FXML
    private TextField tf_emailCadastro;
    @FXML
    private TextField tf_telefone1Cadastro;   
    @FXML
    private TextField tf_telefone2Cadastro;
    
    
    @FXML
    private ComboBox<String> cb_tipoCadastro;
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
    
    
    @FXML
    private void cadastrar(ActionEvent event) throws ParseException {
        //cb_cargoCadastro.setItems(cargos);
        Cliente      cli     = new Cliente();
        Endereco     end     = new Endereco();
        ClienteDAO     c     = new ClienteDAO();
      
        // OBJETO FUNCIONARIO
        cli.setCpf(tf_cpfCadastro.getText());
        cli.setNome(tf_nomeCadastro.getText());
        cli.setDataNascimento(dp_dataNascimentoCadastro.getValue());
        cli.setEmail(tf_emailCadastro.getText());
        cli.setTelefone1(tf_telefone1Cadastro.getText());
        cli.setTelefone2(tf_telefone2Cadastro.getText());
        cli.setAtivo(true);

        // OBJETO ENDERECO
        end.setCEP(tf_cepCadastro.getText());
        end.setEstado(tf_estadoCadastro.getText());
        end.setCidade(tf_cidadeCadastro.getText());
        end.setBairro(tf_bairroCadastro.getText());
        end.setRua(tf_ruaCadastro.getText());
        end.setNumero(Integer.parseInt(tf_numeroCadastro.getText()));
        end.setComplemento(tf_complementoCadastro.getText());
        end.setTipo(cb_tipoCadastro.getValue().charAt(0));

        
        if(c.cadastraCliente(cli, end))
        {
            resultadoCadastro.setText("Cadastrado com sucesso!!");
        }
        else
        {
            resultadoCadastro.setText("Erro ao cadastrar!! Tente novamente.");
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
