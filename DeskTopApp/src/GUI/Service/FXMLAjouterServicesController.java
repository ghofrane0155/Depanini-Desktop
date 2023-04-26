/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Service;

import Entity.Services;
import static GUI.Offre.FXMLAjouterController.showAlert;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceServices;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLAjouterServicesController implements Initializable {

      Connection connexion ;
    Statement stm ;
 
   
    
    ServiceServices cs = new ServiceServices();
    /**
     * Initializes the controller class.
     */
     @FXML
    private Button btnaout1;
    @FXML
    private ImageView return_home;
    @FXML
    private TextField prix_service;
    @FXML
    private TextArea descrption_service;
    @FXML
    private TextField nom_service;
     public FXMLAjouterServicesController() {
        connexion = MyDB.getInstance().getConx();
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        return_home.setOnMouseClicked(((event) -> {
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLServices.fxml"));
                Scene scene =new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }));
    }        
        

public boolean controleTextFieldNumerique(TextField textField) {
        if (!textField.getText().matches(".*[1-9].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Votre prix est invalide");
            alert.showAndWait();
            return true;
                    }

             return false;
    }
 /**************************************************************************************************/ 
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    @FXML
    private void on_click_ajouter(ActionEvent event) throws SQLException {
         if(verif()) 
        {
        
        String prix_service1=prix_service.getText()  ;
        String nom_service1=nom_service.getText();
        String description_service1=descrption_service.getText();
         
        Services c = new Services(nom_service1,prix_service1,description_service1);
      
        cs.ajouter(c);
    }
    }
/*********************************************/
    private boolean verif() {
    
        return true;
    

    
    }
    
    
}
        
   
