/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.reclamation;

import Entity.Reclamation;
import Entity.Statut;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class FXMLReclamationDetailsController implements Initializable {

    @FXML
    private Label label_id_recl;
    @FXML
    private TextField email1;
    @FXML
    private TextField type_reclamation;
    @FXML
    private TextArea description;
    @FXML
    private TextField etat_reclamation;
    @FXML
    private DatePicker date_fin_reclamation;
    @FXML
    private DatePicker date_debut_reclamation;
   

    
    Connection conx = MyDB.getInstance().getConx();
    @FXML
    private TextField piece_jointe;
    @FXML
    private ImageView exit;
    
    Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    void setMyData(Reclamation r) {
        
        String email_user = "";
        String req2 = "Select email from users where id_user=" + r.getId_user();

        Statement stm;
        try {
            stm = conx.createStatement();
            ResultSet rs = stm.executeQuery(req2);
            while (rs.next()) {
                email_user = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        label_id_recl.setText("" + r.getId_reclamation());

        email1.setText(email_user);
        email1.setDisable(true);

        // Create a Calendar object and set the time to the Date object
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(r.getDate_reclamation());

        int year_of_reclamtion = calendar.get(Calendar.YEAR);
        int month_of_reclamtion = calendar.get(Calendar.MONTH);
        int day_of_reclamtion = calendar.get(Calendar.DATE);

        
        LocalDate date = LocalDate.of(year_of_reclamtion, month_of_reclamtion, day_of_reclamtion);
        date_debut_reclamation.setValue(date);
        date_debut_reclamation.setDisable(true);

        type_reclamation.setText(r.getType().toString());
        type_reclamation.setDisable(true);
        
        etat_reclamation.setText(r.getStatut().getLabel());
        etat_reclamation.setDisable(true);

        description.setText(r.getDescription());
        description.setDisable(true);
    // Create a Calendar object and set the time to the Date object
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(r.getDate_reclamation());

        int year_of_reclamtion1 = calendar1.get(Calendar.YEAR);
        int month_of_reclamtion1 = calendar1.get(Calendar.MONTH);
        int day_of_reclamtion1 = calendar1.get(Calendar.DATE);

 
        LocalDate date1 = LocalDate.of(year_of_reclamtion1, month_of_reclamtion1, day_of_reclamtion1);
        date_fin_reclamation.setValue(date);
        date_fin_reclamation.setDisable(true);

        piece_jointe.setText(r.getPiece_jointe());
        piece_jointe.setDisable(true);
        
        exit.setOnMouseClicked((MouseEvent event) -> {
          Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    private void getdate(ActionEvent event) {
    }
    
    
}