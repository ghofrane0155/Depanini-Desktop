package GUI.User;

import Entity.Enum.Sexe;
import Entity.user;

import services.metiersUser.Verif;
import static services.metiersUser.Verif.showAlert;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */

public class SettingsController extends ProfileController implements Initializable {

    @FXML
    private Label tfnom_prenom;
    @FXML
    private JFXTextField tfadr;
    @FXML
    private JFXDatePicker tfdate;
 /**************************************/
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    user utilisateur=null;
    UserService us=new UserService();
    @FXML
    private Label labeldate;
    @FXML
    private Label labelsexe;
    @FXML
    private JFXRadioButton btnfemme;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private JFXRadioButton btnhomme;
    @FXML
    private ImageView tfimg;
/***************************************/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayInfo(user.Current_User);

    }    
/*****************************************************/
    public void displayInfo(user u) {
        utilisateur=u;
        
        tfadr.setText(u.getAdresse());
        labelsexe.setText(u.getSexe().toString());
        
        labeldate.setText(u.getDate_nais_user().toString());
       
        tfnom_prenom.setText(u.getNom_user()+" "+u.getPrenom_user());
        String path=u.getPhoto_user();
        Image img=new Image(getClass().getResourceAsStream(path));
        tfimg.setImage(img);
	}

/***************radio button type enum SEXE********************************/
        Sexe gender=null;
    @FXML
    private void getsexe(ActionEvent event) {
        if(btnfemme.isSelected())
            gender=Entity.Enum.Sexe.valueOf(btnfemme.getText());
        else if(btnhomme.isSelected())
            gender=Entity.Enum.Sexe.valueOf(btnhomme.getText());      
    }
/***********************************************************************/
          LocalDate localdate=null;
    @FXML
    private void getDate(ActionEvent event) {
        localdate=tfdate.getValue();
    }
/*********************************************************************/
    @FXML
    private void sauvegarder_clicked(ActionEvent event) throws SQLException {        
        int x=0;
        if(localdate!=null)
            if(Verif.validateDate(tfdate.getValue())){
                Date date=Date.valueOf(localdate);
                utilisateur.setDate_nais_user(date);
                x=-1;
            }
           
        if(!utilisateur.getAdresse().equals(tfadr.getText())){
             utilisateur.setAdresse(tfadr.getText());
             x=-1;
        }
        if(gender!=null){
            utilisateur.setSexe(gender);
            x=-1;
        }
        if(x!=0){
            us.modifier(utilisateur);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Modification a été effectué avec succès!", "");
            displayInfo(utilisateur);
        }
    }

}
