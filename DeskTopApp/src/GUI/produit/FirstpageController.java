/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.produit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FirstpageController implements Initializable {

    @FXML
    private Button btnajouter;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    

    @FXML 
    private void int_ajouter(ActionEvent event) throws Exception
    {

           /* System.out.println("u clicked on ajouter");
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ProduitAjouter.fxml"));
            Scene scene= new Scene (root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();     //men scene l scene bel button */
   
    
Parent root = FXMLLoader.load (getClass().getResource("AjouterProduit.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }

    @FXML
    private void int_modifier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load (getClass().getResource("ModifierProduit.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void int_afficher(ActionEvent event) throws Exception { 
     Parent root = FXMLLoader.load (getClass().getResource("AfficherProduit.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    
    
}
