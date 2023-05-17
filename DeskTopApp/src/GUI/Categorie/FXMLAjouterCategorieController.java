/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Categorie;
import Entity.Categories;

import Utils.MyDB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.CategoriesServices;
import static services.metiersUser.Verif.showAlert;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLAjouterCategorieController implements Initializable {
Connection connexion ;
    Statement stm ;
 
      @FXML
    private TextField nomcateg;
    @FXML
    private Button btn_img;
    @FXML
    private Button btnaout1;
    @FXML
    private ImageView return_home;
    @FXML
    private ImageView img;
   
    
    Categories c= new Categories();
    
CategoriesServices cs = new CategoriesServices();
public FXMLAjouterCategorieController() {
        connexion = MyDB.getInstance().getConx();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        return_home.setOnMouseClicked(((event) -> {
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Categorie/FXMLCategorie.fxml"));
                Scene scene =new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }));
    }    
    
    
    /****************************************************************************/
    FileChooser fc;
    File selectedFile;
    @FXML
    public void img_clicked(ActionEvent event) throws IOException {
        fc=new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMG Files", "*.png","*.jpg"));
        
        selectedFile=fc.showOpenDialog(null);
        if(selectedFile!=null){
            BufferedImage bufferdimg=ImageIO.read(selectedFile);
            Image image =SwingFXUtils.toFXImage(bufferdimg, null);
            img.setImage(image);
        }
        else
            System.out.println("file is not valid");
    }
      /****************************************************************************/ 

   

    @FXML
    private void on_click_ajouter(ActionEvent event) throws SQLException {
       
  
    if (nomcateg.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
            System.out.println("1");
        }



        else{
            
           
            c.setNom_categorie(nomcateg.getText());
            c.setImage_categorie(btn_img.getText());
           
            cs.ajouter(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Ajout effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }
    
    }
    
   /****************************************************************************************************************/
 
    
    
    
    
  
       
}
