/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Offre;

import Entity.Chatbot;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLChatBotController implements Initializable {
  private Stage stage;
     private Scene scene;
     private Parent root;
    @FXML
    private TextField inputTextField;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Button btnRetour;
    @FXML
    private TextArea outputLabel;
    private Chatbot chatbot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         chatbot = new Chatbot();
    }    

    @FXML
    private void processInput(ActionEvent event) {
        String input = inputTextField.getText();
        String output = chatbot.processInput(input);
        outputLabel.setText(output);
        inputTextField.clear();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLOffre.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}