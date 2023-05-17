/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Categorie;

import Entity.Offres;
import Entity.Categories;
import static GUI.Offre.FXMLAjouterController.showAlert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import services.CategoriesServices;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class ModifierCategorieController implements Initializable {
    @FXML
    private TextField idcategorie;
     @FXML
    private TextField nomcategorie;
       
   @FXML
    private ImageView img;
    
   
    @FXML
    private ImageView return_home;
    @FXML
    private Button modifiercategorie;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        return_home.setOnMouseClicked(((event) -> {
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Categorie/ModifierCategories.fxml"));
                Scene scene =new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }));
        
    }    
        CategoriesServices cs = new CategoriesServices();

    @FXML
    private void Modifiercategorie(ActionEvent event) throws Exception {
            int id_categorie=Integer.parseInt(idcategorie.getText());
      
         Categories c2 = cs.afficherById(id_categorie);
        if(c2!=null)
        {
            
           
            c2.setNom_categorie(nomcategorie.getText());
       
            System.out.println(c2);

            cs.modifier(c2);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Modification a été effectué avec succès!", "");
        }
        else
         showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner Une categorie existante !");
    }
    }
    
   
      