/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.participate;

import Entity.Event;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Utils.MyDB;

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

    private Image image;
    Connection conx = MyDB.getInstance().getConx();
    @FXML
    private Label prix;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    /***********************************remplir les champs des details***************************/
    
    void setTextFields (Event e){
        
        String nom_event = "";
        String req2 = "Select `NomEvent` from `Events` where IDEvent=" + e.getIDEvent();

        Statement stm;
        try {
            stm = conx.createStatement();
            ResultSet rs = stm.executeQuery(req2);
            while (rs.next()) {
                nom_event = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

         nomevenement.setText(nom_event);
         datedeb.setText(e.getDateDabEvent());
         datefin.setText(e.getDateFinEvent());
         description.setText(e.getDescriptionEvent());
         nbrlim.setText(String.valueOf(e.getNombreLimEvent()));
         oraganisateurEvent.setText(e.getOrganisateurEvent());
         prix.setText(String.valueOf(e.getPrixEvent()));
         
         String uri ="file:" +e.getImageEvent();
         image=new Image(uri, 229, 146 ,false , true);
         imgEvent.setImage(image);
         
         
        exit.setOnMouseClicked((MouseEvent event) -> {
          Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });
        
    }
    
}
