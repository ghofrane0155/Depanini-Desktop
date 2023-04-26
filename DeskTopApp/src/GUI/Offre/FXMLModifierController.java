/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Offre;

import Entity.Categories;
import Entity.Offres;
import static GUI.Offre.FXMLAjouterController.showAlert;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.OffresServices;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLModifierController implements Initializable {

    @FXML
    private TextField ID_user;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private TextField localisation;
    @FXML
    private TextField nom;
    @FXML
    private RadioButton presentiel;
    @FXML
    private ToggleGroup type_o;
    @FXML
    private RadioButton enligne;
    @FXML
    private Button modifier_offre;
/*************************************************/
    Entity.Enum.TypeO typeo=null;
     
    ObservableList <String> typeList=FXCollections.observableArrayList("Informatique","Baby_sitting","Demenagement","Couture","Beaute","Traduction");

    OffresServices os=new OffresServices();
/************************************************/    
    @FXML
    private ComboBox<String> tf_cat;
    @FXML
    private ImageView img;
    @FXML
    private Button btn_img;
    @FXML
    private ImageView return_home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_cat.setItems(typeList);
         return_home.setOnMouseClicked(((event) -> {
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLOffre.fxml"));
                Scene scene =new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }));
        
}

    @FXML
    private void gettype(ActionEvent event) {
        if(presentiel.isSelected())
            typeo=Entity.Enum.TypeO.presentiel;
        else if(enligne.isSelected())
            typeo=Entity.Enum.TypeO.enligne;
    }
/****************************************************************************/
    FileChooser fc;
    File selectedFile;
    
    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        int id_offre=Integer.parseInt(ID_user.getText());
        Offres o2=os.afficherById(id_offre);
        
        if(o2!=null)
        {
            o2.setPrix_offre(Double.parseDouble(prix.getText()));
            o2.setDescription_offre(description.getText());
            o2.setLocation_offre(localisation.getText());
            o2.setNom_offre(nom.getText());
            o2.setType_cat(Entity.Enum.TypeC.valueOf(tf_cat.getValue()));
            o2.setImage_offre(selectedFile.getAbsolutePath());
            o2.setType_offre(typeo);

            System.out.println(o2);

            os.modifier(o2);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Modification a été effectué avec succès!", "");
        }
        else
         showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner Un offre existant !");        

    }
/*****************************************************************/
    @FXML
    private void img_clicked(ActionEvent event) throws IOException {
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
    
}
