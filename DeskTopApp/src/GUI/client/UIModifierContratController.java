/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.client;

import Entity.Contrats;
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
import services.ContratsService;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class UIModifierContratController implements Initializable {

    @FXML
    private TextField cin;
    @FXML
    private ImageView buttonmodifier;
    
    int id_contrat ;
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
    private void modifier(MouseEvent event) throws SQLException, Exception {
                  ContratsService sc= new ContratsService();
                 if ( cin.getText().isEmpty())
    {
        Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
    }else if (sc.existe("cin", cin.getText()) > 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("cin déjà existe, veuillez choisir un autre !");
            al.showAndWait();

        } else
        {
           
            sc.modifier(new Contrats (id_contrat,Integer.parseInt(cin.getText()))) ;
                JOptionPane.showMessageDialog(null,"contrat modifiee avec succes");
        }
    }

    void setTextFields(Contrats New) {
   cin.setText(String.valueOf(New.getDate()));
         id_contrat = New.getIDContrat();

            }
    }
    

