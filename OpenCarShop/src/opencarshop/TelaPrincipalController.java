package opencarshop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TelaPrincipalController implements Initializable {

    @FXML
    private StackPane acContent;

    @FXML
    private Text tx_info;

    @FXML
    private void cadastrarFuncionario(ActionEvent event) {
        System.out.println("Cadastrar Funcionario");
        tx_info.setText("Cadastrar Funcionario");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/funcionario/view/Cadastrar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void listarFuncionario(ActionEvent event) {
        System.out.println("Buscar Funcionario");
        tx_info.setText("Listar Funcionario");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/funcionario/view/Buscar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void cadastrarFornecedor(ActionEvent event) {
        System.out.println("Cadastrar Fornecedor");
        tx_info.setText("Cadastrar Fornecedor");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/fornecedor/view/Cadastrar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void listarFornecedor(ActionEvent event) {
        System.out.println("Buscar Fornecedor");
        tx_info.setText("Listar Fornecedor");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/fornecedor/view/Buscar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void cadastrarCliente(ActionEvent event) {
        System.out.println("Cadastrar Cliente");
        tx_info.setText("Cadastrar Cliente");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/cliente/view/Cadastrar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void listarCliente(ActionEvent event) {
        System.out.println("Buscar Cliente");
        tx_info.setText("Listar Cliente");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/cliente/view/Buscar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void cadastrarServico(ActionEvent event) {
        System.out.println("Cadastrar Servico");
        tx_info.setText("Cadastrar Serviço");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/servico/view/Cadastrar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void listarServico(ActionEvent event) {
        System.out.println("Buscar Servico");
        tx_info.setText("Listar Servico");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/servico/view/Buscar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void cadastrarVeiculo(ActionEvent event) {
        System.out.println("Cadastrar Veiculo");
        tx_info.setText("Cadastrar Veiculo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/veiculo/view/Cadastrar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void listarVeiculo(ActionEvent event) {
        System.out.println("Buscar Veiculo");
        tx_info.setText("Listar Veiculo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/veiculo/view/Buscar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void cadastrarPeca(ActionEvent event) {
        System.out.println("Cadastrar Peca");
        tx_info.setText("Cadastrar Peca");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/peca/view/CadastroPeca.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void listarPeca(ActionEvent event) {
        System.out.println("Buscar Peca");
        tx_info.setText("Listar Peça");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/peca/view/Buscar.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void venderPeca(ActionEvent event) {
        System.out.println("Vender Peca");
        tx_info.setText("Vender Peça");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/peca/view/VendaPecas.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);

    }

    @FXML
    private void relatorioVendaPecaMes(ActionEvent event) {
        System.out.println("Relatorio Peca/Mes");
        tx_info.setText("Relatorio Peca/Mes");

        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/opencarshop/peca/view/GraficosVendasPorMes.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
