/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Offre;

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
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLOffreController implements Initializable {

    @FXML
    private Button AjouterScene;
    @FXML
    private Button retour;
    @FXML
    private Button info;
    @FXML
    private Button info1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangeScene1(ActionEvent event) throws Exception {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLAjouter.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();

    }

    @FXML
    private void ChangeScene2(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLModifier.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    @FXML
    private void ChangeScene3(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLAfficher.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }
                                                  
    
    

    @FXML
    private void ChangeScene4(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLAfficher.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/userHome/UserHomePageFreelancer.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void chatbot(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLChatBot.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    @FXML
    private void sound(ActionEvent event) throws IOException {
         String soundFilePath = "/GUI/sound/botVoice.wav";
        AudioClip audioClip = new AudioClip(getClass().getResource(soundFilePath).toString());
        audioClip.play();
    }

}
