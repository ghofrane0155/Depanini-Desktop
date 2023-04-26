package GUI.User;

import Entity.user;
import services.metiersUser.Verif;
import static services.metiersUser.Verif.showAlert;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import services.UserService;
import services.metiersUser.SessionUser;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class ProfileController implements Initializable {

    @FXML
    private Label tfnom_prenom;
    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXTextField tfprenom;
    @FXML
    private JFXTextField tflogin;
    @FXML
    private JFXTextField tftel;
    @FXML
    private JFXTextField tfmail;
    @FXML
    private ImageView tfimg;
/***************************************/
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Image img;
/****************************************/
    user utilisateur=user.Current_User;
    UserService us=new UserService();    
/**************************************/
   FileChooser fc; 
   File selectedFile;
   String photo="/GUI/images/user1.png";
    FileInputStream fis;
/******************************/ 
    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
       displayInfo(utilisateur);
    }    
    
    
    public void displayInfo(user u) {
        
        tfnom.setText(u.getNom_user());
        tfprenom.setText(u.getPrenom_user());
        tflogin.setText(u.getLogin());
        tftel.setText(u.getTel());
        tfmail.setText(u.getEmail()); 
        tfnom_prenom.setText(u.getNom_user()+" "+u.getPrenom_user());
        
        String path=u.getPhoto_user();
        Image img=new Image(getClass().getResourceAsStream(path));
        tfimg.setImage(img);
	}

/****************change scene to Profile****************************************************/
    @FXML
    private void prifile_clicked(ActionEvent event) throws IOException {

        root =FXMLLoader.load(getClass().getResource("Profile.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/****************change scene to Settings****************************************************/
    @FXML
    private void settings_clicked(ActionEvent event) throws IOException {

        root =FXMLLoader.load(getClass().getResource("Settings.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/***************change scene to notif*******************************/
    @FXML
    private void notif_clicked(ActionEvent event) throws IOException {

        root =FXMLLoader.load(getClass().getResource("Notification.fxml"));

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/***************change scene to password*******************************/
    @FXML
    private void password_clicked(ActionEvent event) throws IOException {

        root =FXMLLoader.load(getClass().getResource("Password.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

/******************controle de saisie*************************************************************/
    private boolean valide(){
        if(!Verif.validateNom(tfnom.getText())){
            tfnom.requestFocus();
            return false;
        }
        else{
            if(!Verif.validatePrenom(tfprenom.getText())){
                tfprenom.requestFocus();
                return false;
            }
        ///////////////////////////////////////////////////////////   
            if(!Verif.validateLogin(tflogin.getText())){
                tflogin.requestFocus();
                return false;
            }
        /**************login dans table user******************************/
            if(!utilisateur.getLogin().equals(tflogin.getText()))
                if(UserService.existe("login",tflogin.getText())>0){
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Login déjà existe, veuillez choisir un autre");
                    tflogin.requestFocus();
                    return false;
                }
  
        ///////////////////////////////////////////////////////
            if(!Verif.validateTel(tftel.getText())){
                tftel.requestFocus();
                return false;
            }
         ////////////////////////////////////////////////////////////////////////////////////   
            if(!Verif.validateMail(tfmail.getText())){
                tfmail.requestFocus();
                return false;
            }
            
            if(!utilisateur.getEmail().equals(tfmail.getText()))
                if(UserService.existe("email",tfmail.getText())>0){
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Email déjà existe, veuillez choisir un autre");
                    tfmail.requestFocus();
                     return false;
                }
     
        }
        return true;
    }
/***************update**********************************************************/
    @FXML
    private void sauvegarder_clicked(ActionEvent event) {

        if(valide()){
            utilisateur.setNom_user(tfnom.getText());
            utilisateur.setPrenom_user(tfprenom.getText());
            utilisateur.setLogin(tflogin.getText());
            utilisateur.setTel(tftel.getText());
            utilisateur.setEmail(tfmail.getText());           
            try {
                us.modifier(utilisateur);              
        /*****************update Session*********************/
                user.setCurrent_User(utilisateur);
                 SessionUser.setNom_user(tfnom.getText());
                 SessionUser.setPrenom_user(tfprenom.getText());
                 SessionUser.setLogin(tflogin.getText());
                 SessionUser.setTel(tftel.getText());
                 SessionUser.setEmail(tfmail.getText());  
                 SessionUser.setPhoto_user(utilisateur.getPhoto_user()); 
        /*****************************************************/  
                showAlert(Alert.AlertType.INFORMATION, "Success", "Modification a été effectué avec succès!", "");
                displayInfo(utilisateur);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());}
                 
        }
    }
/***********************************************************************/
    @FXML
    private void decon_clicked(MouseEvent event) throws IOException, SQLException {
        SessionUser.cleanUserSession();
        
        root =FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 /********************************************************************************/   
    @FXML
    private void depanini_clicked(MouseEvent event) throws IOException { 
        if(SessionUser.getRole().toString().equals("Freelancer"))
            root = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePageFreelancer.fxml"));  
        else
            root = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePage.fxml"));
        
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }

    
    
    @FXML
    private void edit_clicked(ActionEvent event) throws IOException {
        fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("IMG Files", "*.png","*.jpg","*.gif"));
         
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            BufferedImage bufferdimg= ImageIO.read(selectedFile);
            Image image =SwingFXUtils.toFXImage(bufferdimg, null);
            tfimg.setImage(image);
            
            utilisateur.setPhoto_user("/GUI/images/"+selectedFile.getName());    
        } else {
            System.out.println("file is not valid");
        }
    }
    
}
