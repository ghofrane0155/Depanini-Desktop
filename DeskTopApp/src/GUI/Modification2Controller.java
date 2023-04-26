/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Entity.Event;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import services.EventService;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class Modification2Controller implements Initializable {

    @FXML
    private JFXTextField txtnom;
    @FXML
    private JFXTextField txtorg;
    @FXML
    private JFXTextField intnbr;
    @FXML
    private JFXTextField txtlieu;
    @FXML
    private JFXTextField txtdesc;
    @FXML
    private JFXDatePicker datdeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private Button btnref;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Close(MouseEvent event) {
    }
   public void setTextFields(Event R){
        txtnom.setText(R.getNomEvent());
       txtorg.setText(R.getOrganisateurEvent());
        txtlieu.setText(R.getLieuEvent());
        intnbr.setText(String.valueOf(R.getNombreLimEvent()));
        txtdesc.setText(R.getDescriptionEvent());
       
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        EventService e= new EventService();
        
         
         Integer nbr=Integer.parseInt(intnbr.getText());
         String nomE= txtnom.getText();
         String org= txtorg.getText();
         String lieu= txtlieu.getText();
         String desc= txtdesc.getText();
         
         Event R;
           R = new Event(nomE, nbr, lieu, lieu, desc, desc, desc);
           e.modifier(R);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Travel Me :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilsateur modifié");
                alert.showAndWait();
    }
    
    
}
