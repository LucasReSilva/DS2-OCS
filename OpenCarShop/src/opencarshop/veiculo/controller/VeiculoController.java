package opencarshop.veiculo.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import opencarshop.Endereco;
import opencarshop.funcionario.model.Contrato;
import opencarshop.funcionario.model.Funcionario;
import opencarshop.funcionario.model.FuncionarioDAO;
import opencarshop.util.Utilidades;
import opencarshop.veiculo.model.Veiculo;

public class VeiculoController implements Initializable {

        // TELA DE CADASTRO
    @FXML
    private TextField tf_nomeCadastro;
    @FXML
    private DatePicker dp_dataNascimentoCadastro;
    @FXML
    private TextField tf_cpfCadastro;
    @FXML
    private PasswordField pf_senhaCadastro;
    
        @FXML
    private void cadastrar(ActionEvent event) throws ParseException {
        //cb_cargoCadastro.setItems(cargos);
        Funcionario  func    = new Funcionario();
        Endereco     end     = new Endereco();
        Contrato     contr   = new Contrato();
        FuncionarioDAO f     = new FuncionarioDAO();
        Veiculo veic = new Veiculo();
        Utilidades u = new Utilidades();
       /*
        //OBJETO VEICULO
        vei 
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
        */
    }
        
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
