/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entity.user;
import static services.metiersUser.Verif.showAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class ForgotPasswordController extends LoginController implements Initializable {

    @FXML
    private TextField tfmail;
/***********************************************/
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton tfconx;
    @FXML
    private JFXButton tfconx1;
    @FXML
    private JFXTextField tflogin;
    @FXML
    private JFXTextField tfpass;
/*******************************************/

/***********************************************/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void on_click_rechercher(ActionEvent event) throws IOException {
        String mail=tfmail.getText();
        
        if(mail.equals(""))
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien entrer votre mail !");        
        else
          { u=us.getUserByEmail(mail);
            if(u!=null){    
                FXMLLoader loader=new FXMLLoader(getClass().getResource("FP_second.fxml"));
                root =loader.load();
                        
                FP_secondController fp=loader.getController();
                fp.u2=u;
                
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.show();
                }
            else
                showAlert(Alert.AlertType.ERROR, "Données erronés","Verifier les données", "E-mail invalide!");        
           }
    }
    
/*******************************************************************/
    @FXML
    private void on_click_Annuler(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("Login.fxml"));
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
