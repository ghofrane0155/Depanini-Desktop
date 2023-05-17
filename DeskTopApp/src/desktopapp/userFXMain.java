package desktopapp;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/** 
 *
 * @author Ghof
 */
public class userFXMain extends Application {
   
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/User/Login.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("/GUI/produit/Firstpage.fxml"));

       //Parent root = FXMLLoader.load(getClass().getResource("/GUI/Categorie/FXMLCategorie.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLOffre.fxml"));
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}