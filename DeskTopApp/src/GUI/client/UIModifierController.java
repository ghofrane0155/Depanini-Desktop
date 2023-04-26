/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.client;

import Entity.Demandes;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.DemandesService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class UIModifierController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField budget;
    @FXML
    private TextField descripton;
    @FXML
    private ImageView buttonmodifier;

    /**
     * Initializes the controller class.
     */
    
                    Image image = new Image(getClass().getResourceAsStream("/GUI/images/modifie.PNG"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         buttonmodifier.setImage(image);
    }    

    @FXML
    private void ControleString(KeyEvent event) {
    }

    @FXML
    private void ControleBudget(KeyEvent event) {
    }

    @FXML
    private void modifier(MouseEvent event) throws SQLException {
              
                 if ( nom.getText().isEmpty() | budget.getText().isEmpty() | descripton.getText().isEmpty())
    {
        Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
    }
        else
        {
            DemandesService sc= new DemandesService();
            sc.modifier(new Demandes (nom.getText(),Float.parseFloat(budget.getText()), descripton.getText())) ;
                JOptionPane.showMessageDialog(null,"Demande modifiee avec succes");
        }
    }

    void setTextFields(Demandes New) {
   budget.setText(String.valueOf(New.getBudget()));
        descripton.setText(New.getDescription());
         nom.setText(New.getNom());    }
    
}
