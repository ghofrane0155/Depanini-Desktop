/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author yasmine
 */
public class NewMain1 extends Application {

    /**
     * @param args the command line arguments
     */
         Parent parent;
 Stage stage;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
      Parent root = FXMLLoader.load(getClass().getResource("/GUI/client/UIDemande.fxml"));
        //   Parent root = FXMLLoader.load(getClass().getResource("/GUI/client/UIContrat.fxml"));

       // Parent root = FXMLLoader.load(getClass().getResource("/GUI/client/FXMLContrat.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("/GUI/client/FXMLDemande.fxml"));
             
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion demandes et contrats");
        stage.show();
                }
    
    public static void main(String[] args) {
        launch(args);
    }
    }
   
