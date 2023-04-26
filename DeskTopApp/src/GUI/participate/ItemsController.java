/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.participate;

import Entity.Event;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class ItemsController implements Initializable {
public Event e ;
    @FXML
    private AnchorPane abc;
    @FXML
    private Label NomEvenementss;
    @FXML
    private Label organisateur;
    @FXML
    private Label DescEvents;
    @FXML
    private Button delete;
    @FXML
    private Button details1;
    @FXML
    private Button ModifEVts;
    @FXML
    private Label dateEvs;
    @FXML
    private Label LieuEvss;
    @FXML
    private Label TarifEvenetss1;
    @FXML
    private Label IDeven;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void getEvent(Event E){
        
        Image image = new Image("file:" + E.getImageEvent());
        
            img.setImage(image);
            NomEvenementss.setText(E.getNomEvent());
            
            organisateur.setText(E.getOrganisateurEvent());
            DescEvents.setText(E.getDescriptionEvent());
            LieuEvss.setText(E.getLieuEvent());
            TarifEvenetss1.setText(String.valueOf(E.getPrixEvent()));
            
            
            dateEvs.setText(E.getDateDabEvent());
            
           
    Event Event = E;
        
    }

    @FXML
    private void deletevv(ActionEvent event) {
    }

    @FXML
    private void btndetailsEv(ActionEvent event) {
    }

    @FXML
    private void BTNmodifEves(ActionEvent event) {
    }
}
