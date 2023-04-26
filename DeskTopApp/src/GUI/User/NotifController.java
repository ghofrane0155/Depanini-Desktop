/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entity.Notification;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import services.NotificationService;

/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class NotifController implements Initializable {
    
    @FXML
    private Label user_name;
    @FXML
    private Label nombre_stars;
    @FXML
    private Button btn_notif;
    NotificationService nfs;
    @FXML
    private Label id_no;
    @FXML
    private HBox grid;
    @FXML
    private ImageView gif_notif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_no.setOpacity(0);
    }
    
    @FXML
    private void marked_as_read(ActionEvent event) throws SQLException {
       
      int id_notification =  Integer.valueOf(id_no.getText());
      
      nfs = new NotificationService();
 
         nfs.modifier(id_notification); 
         Image newImage = new Image("GUI/images/icons8-rappels-de-rendez-vous_del.gif");
        gif_notif.setImage(newImage);
        
        grid.setStyle("-fx-background-color: #4D82CB;");
        
           Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Notification marked as read");
        alert.setHeaderText(null);
        alert.setContentText("The notification has been marked as read.");

        // Customize the alert tyle
        //alert.getDialogPane().getStyleClass().add("read-notification");
        alert.showAndWait();
        
        
        
    }
    
    void getNotif(Notification nt) throws SQLException {
       
        String s = String.valueOf(nt.getId_notification());
        id_no.setText(s);
      
      user_name.setText( nt.getName_user());
      String nb_s = String.valueOf(nt.getNb_stars());
        nombre_stars.setText(nb_s);
        
    }
    
}
