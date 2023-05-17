/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.reclamation;

import Entity.Reclamation;
import Entity.Statut;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class FXMLUpdateReclamationController implements Initializable {

    @FXML
    private TextField email1;
    @FXML
    private TextField type_reclamation;
    @FXML
    private TextArea description;
    @FXML
    private ChoiceBox<String> etat_reclamation;

    private String[] etat = {"Resolu", "En attente"};

    @FXML
    private DatePicker date_fin_reclamation;
    @FXML
    private DatePicker date_debut_reclamation;

    Connection conx = MyDB.getInstance().getConx();
    @FXML
    private Label label_id_recl;
    @FXML
    private Button btn_update;
    LocalDate localdate = LocalDate.now();
    @FXML
    private Label error_date;
    @FXML
    private ImageView exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etat_reclamation.getItems().addAll(etat);
        date_fin_reclamation.setValue(LocalDate.now());
    }

    void setMyData(Reclamation r) {

       
        //recuperation id_user appartir de l'email
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

        description.setText(r.getDescription());
        description.setDisable(true);

        etat_reclamation.setValue(r.getStatut().getLabel());

        btn_update.setOnMouseClicked((MouseEvent event) -> {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("La réclamation doit être traiter");
            alert.setContentText("Veuillez traiter la réclamation avant de modifier ce champ. Êtes-vous sûr de confirmer cette modification?");         
            alert.showAndWait();
            Statut st = null;
            switch (etat_reclamation.getValue()) {
                case "Ouvert":
                    st = Statut.Ouvert;
                    break;
                case "Resolu":
                    st = Statut.Resolu;
                    break;
                case "En attente":
                    st = Statut.En_attente;
                    break;
            }
            Date date_convert = java.sql.Date.valueOf(localdate);
            ReclamationService rs = new ReclamationService();
            Reclamation rec = new Reclamation(r.getId_reclamation(),r.getId_user(), st, date_convert);
            rs.modifierpersonalise(rec);
      
        });
        
         exit.setOnMouseClicked((MouseEvent event) -> {
          Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();           
        });
    }

    @FXML
    private void getdate(ActionEvent event) {

        if (date_fin_reclamation.getValue().isBefore(LocalDate.now())) {
            date_fin_reclamation.setStyle("-fx-background-color: red;-fx-border-color: red; -fx-border-width: 2px;");
            error_date.setText("La date n'est pas valide");
            date_fin_reclamation.setValue(null);

        } else {
            date_fin_reclamation.setStyle("-fx-background-color: green;-fx-border-color: green; -fx-border-width: 2px;");
            error_date.setText("");
            localdate = date_fin_reclamation.getValue();
            
        }

    }

}