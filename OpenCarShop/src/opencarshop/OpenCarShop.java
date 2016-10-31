package opencarshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import opencarshop.funcionario.controller.FuncionarioController;

public class OpenCarShop extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("funcionario/view/Autenticar.fxml"));
        
        Scene scene = new Scene(root);
        
        FuncionarioController.setPrevStage(stage);  
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("recursos/icones/account-circle-white.png"));
        stage.setTitle("Autenticação");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
