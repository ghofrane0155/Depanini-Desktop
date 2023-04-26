/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Service;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLServicesController implements Initializable {

    @FXML
    private Button AjouterScene;
    @FXML
    private Button retour;
    @FXML
    private Button Modifier;
    @FXML
    private Button affiche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangeScene1(ActionEvent event) throws Exception {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLAjouterServices.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();

    }

    private void ChangeScene2(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLModifierServices.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    private void ChangeScene3(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLAfficherServices.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    private void ChangeScene4(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLAfficherServices.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePageFreelancer.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Service/ModifierServices.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
        
    }

    @FXML
    private void affiche(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLAfficherServices.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

}
