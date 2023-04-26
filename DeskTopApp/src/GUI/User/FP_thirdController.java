/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entity.user;
import static services.metiersUser.Verif.showAlert;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.NativeString;
import services.metiersUser.SessionUser;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class FP_thirdController extends FP_secondController implements Initializable {

    @FXML
    private TextField tfcode;
    @FXML
    private Label label_num;
   
    Stage stage;
    private Scene scene;
    Parent root;
    user u3;
    String codeV;
    @FXML
    private Label label_email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void on_click_continue(ActionEvent event) throws IOException {
        String c=tfcode.getText();
        if(c.equals(codeV))
        {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Reset.fxml"));
                root =loader.load();
                        
                ResetController fp=loader.getController();
                fp.u4=u3;
                
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene =new Scene(root);
                stage.setScene(scene);
                stage.show();            
            
        }
        else
            showAlert(Alert.AlertType.ERROR, "Données erronés","Verifier votre code", "Code invalide!");              
    }

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

    public void display_num(user u3) {
        label_num.setText(" *****"+u3.getTel().substring(5,8));
    }
    
    public void display_mail(user u3) {
        label_email.setText(u3.getEmail());
    }   
}
