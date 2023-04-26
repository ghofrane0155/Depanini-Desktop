/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *

 * @author Mega-pc
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException  { 
             Parent root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLOffre.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLServices.fxml"));
            Scene sc = new Scene(root);
            primaryStage.getIcons().add(new Image("/GUI/images/logo.png"));
            primaryStage.setTitle("Reclamation User Page");                 
            primaryStage.setScene(sc);
            primaryStage.show();
            
            
            
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
