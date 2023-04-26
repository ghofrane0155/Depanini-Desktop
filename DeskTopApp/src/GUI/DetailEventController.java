/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class DetailEventController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private ImageView imgEvent;
    @FXML
    private Label nomevenement;
    @FXML
    private Label oraganisateurEvent;
    @FXML
    private Label description;
    @FXML
    private Label nbrlim;
    @FXML
    private Label datedeb;
    @FXML
    private Label datefin;
    @FXML
    private Label prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
