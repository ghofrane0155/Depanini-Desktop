/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entity.user;
import static services.metiersUser.PasswordHashing.hashPassword;
import services.metiersUser.Verif;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class ResetController implements Initializable {

    @FXML
    private TextField tfpass;
/************************************************/
    Stage stage;
    private Scene scene;
    Parent root;
    user u4;
    UserService us=new UserService();
/*************************************************/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void on_click_continue(ActionEvent event) throws SQLException, IOException {
       
        us = new UserService();
        String newpass=tfpass.getText();
        if(Verif.validatePass(newpass)){
//            u4.setPassword(hashPassword(newpass));        
            u4.setPassword(newpass);  
            us.modifier(u4);
            
            root =FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene =new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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
    
}
