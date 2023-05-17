
package GUI.User;

import Entity.user;
import static services.metiersUser.PasswordHashing.hashPassword;
import services.metiersUser.Verif;
import static services.metiersUser.Verif.showAlert;
import com.jfoenix.controls.JFXPasswordField;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

public class PasswordController extends ProfileController implements Initializable {

    @FXML
    private Label tfnom_prenom;
    @FXML
    private JFXPasswordField tfnewpass;
    @FXML
    private JFXPasswordField tfconfirmpass;
/****************************************************/  
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    user utilisateur=null;
    UserService us=new UserService();
    @FXML
    private ImageView tfimg;
/******************************************************/
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

        tfnom_prenom.setText(u.getNom_user()+" "+u.getPrenom_user());
        String path="/GUI/Images/"+u.getPhoto_user();
        Image img=new Image(getClass().getResourceAsStream(path));
        tfimg.setImage(img);
	}

    @FXML
    private void sauvegarder_clicked(ActionEvent event) throws SQLException {
        String pass=tfnewpass.getText();
        String pass2=tfconfirmpass.getText();
        
        if(Verif.validatePass(pass))
            if(Verif.confirmPass(pass, pass2)){
                
//                utilisateur.setPassword(hashPassword(pass));
            utilisateur.setPassword(pass);

                us.modifier(utilisateur);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Mot de passe a été modifier avec succès!", "");
            }
            else
                tfconfirmpass.requestFocus();
        else
            tfnewpass.requestFocus();
        
    }
}
