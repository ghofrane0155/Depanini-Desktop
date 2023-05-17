package GUI.Offre;

import Entity.Offres;
import Entity.Enum.TypeC;
import Entity.Enum.TypeO;
import Utils.MyDB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
//import org.controlsfx.control.Notifications;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLAjouterController implements Initializable {
    Connection connexion ;
    Statement stm ;


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
    private RadioButton enligne;
    @FXML
    private ImageView img;
    @FXML
    private TextField categorie;
    
    

  

    OffresServices os=new OffresServices();
    /**
     * Initializes the controller class.
     */
     
    @FXML
    private Button btnaout1;
    @FXML
    private ImageView return_home;
    @FXML
    private ToggleGroup type_o;
    @FXML
    private Button btn_img;
     public FXMLAjouterController() {
        connexion = MyDB.getInstance().getConx();
    }
     String typeo;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
/******************RADIO BUTTON********************************************/
    @FXML
    public void gettype(ActionEvent event) {
     if (presentiel.isSelected()) {
    typeo = "presentiel";
} else if (enligne.isSelected()) {
    typeo = "enligne";
}};

/*******************************************************************/

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

    @FXML
    private void on_click_ajouter(ActionEvent event) throws SQLException {
        if(verif()) 
        {
        int id_user=Integer.parseInt(ID_user.getText());
        Double prix_offre=Double.parseDouble(prix.getText())  ;
        String description_offre=description.getText();
        String localisation_offre=localisation.getText();   
        String nom_offre=nom.getText();        

            
        String photo=selectedFile.getAbsolutePath();
        if (presentiel.isSelected()) {
    typeo = "presentiel";
} else if (enligne.isSelected()) {
    typeo = "enligne";
}
        
        Offres o=new Offres(id_user,prix_offre, description_offre, localisation_offre, nom_offre, photo,typeo );
        os.ajouter(o);
//        Notification();
//        
    }
}
/****************************************************************************************************************/
private boolean verif() {
    if (ID_user.getText().isEmpty() || (controleTextFieldNumerique(prix)) || description.getText().isEmpty() || (controleTextField2(localisation))
            || (controleTextField2(nom))  ) {
                  
        showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner tous les champs !");        
        
        return false;
    }
    return true; 
}



public boolean controleTextField2(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Veuillez saisir des lettres");
//            JOptionPane.showMessageDialog(null,"Veuillez saisir des lettres");
    alert.showAndWait();
            return true;
        }
        return false;
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
  
    

/**
     * @throws java.sql.SQLException************************************************************************************************/
   
//  public void Notification() throws SQLException{
//        String req = "select * from offres where prix_offre ='"+prix.getText()+"' ";
//        stm = connexion.createStatement(); 
//        //ensemble de resultat
//        ResultSet rst = stm.executeQuery(req);
//        while (rst.next()) {
//        Notifications notifications=Notifications.create();
//        notifications.text(" Nom : "+rst.getString("nom_offre")+" \n Localisation: "+rst.getString("localisation_offre")+" \n Prix : "+rst.getDouble("prix_offre"));
//        notifications.title("Offre Enregistée");
//        notifications.hideAfter(Duration.seconds(10));
//        notifications.darkStyle();
//        notifications.position(Pos.BOTTOM_RIGHT);
//        notifications.show();
//        }   
//    
//  }
    
    
}    



/**************************************************************************************************/


 
