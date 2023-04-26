package GUI.User;

import Entity.user;
import static services.metiersUser.PasswordHashing.generateOTP;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.metiersUser.SessionUser;
import static services.metiersUser.Verif.showAlert;
import services.metiersUser.sendSMS;
import static services.metiersUser.sendSMS.sendSms;


/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class FP_secondController extends LoginController implements Initializable {

    @FXML
    private JFXRadioButton radiomail;
    @FXML
    private ToggleGroup mdp;
    @FXML
    private JFXRadioButton radiotexto;
    @FXML
    private JFXTextField tflogin;
    @FXML
    private JFXTextField tfpass;
    @FXML
    private JFXButton tfconx;

    int btn=0;
    user u2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void on_click_continuer(ActionEvent event) throws IOException {
        if(btn==1 ||btn==2){
            String code=generateOTP(6);
                 System.out.println(code);
                 
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FP_third.fxml"));
            root =loader.load();                       
            FP_thirdController fp=loader.getController();
            
            fp.codeV=code;
            fp.u3=u2;
            
            if(btn==1){
                    System.out.println(u2.getLogin());
                us.sendEmail(u2.getEmail(), code,u2.getLogin());
                fp.display_mail(u2);}
            else{
                sendSms(code,u2.getTel());
                fp.display_num(u2);
            }

            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene =new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else 
           showAlert(Alert.AlertType.ERROR, "Choix erroné", "Verifier votre choix", "Veuillez bien choisir la façon pour recevoir le code !");        

    }
    @FXML
    private void getMeth(ActionEvent event) {
        if(radiomail.isSelected())
            btn=1;
        else if(radiotexto.isSelected())
            btn=2;  
    }
    
    @FXML
    private void on_click_nestPasVous(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_depanini(MouseEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
