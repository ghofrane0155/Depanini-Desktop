/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.participate;

import Entity.Event;
import Entity.Ticket;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import services.TicketService;
import services.metiersUser.SessionUser;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class TicketController implements Initializable {

    @FXML
    private ImageView Qrimage;
    @FXML
    private Label eventname;
    @FXML
    private Label login;
    @FXML
    private Label Quantite;
    @FXML
    private Label prixtotal;
    @FXML
    private Button delete;
    @FXML
    private Label idt;
    Connection cnx = MyDB.getInstance().getConx();
    Image image = new Image("\\GUI\\images\\qr.png");
    @FXML
    private HBox hboxdel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette ligne ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                TicketService ts = new TicketService();
                ts.delete(Integer.valueOf(idt.getText()));
                 
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        hboxdel.setStyle("-fx-background-color:#FB3640;");
        
    }

    void getEvent(Ticket T) throws SQLException {
//       Image image = new Image("file:" + T.getImageEvent());
//        
//            img.setImage(image);
        idt.setText(String.valueOf(T.getIDTicket()));
        
//            idev.setText(String.valueOf(T.getIDEvent()));
        PreparedStatement userStatement = cnx.prepareStatement("SELECT NomEvent FROM events WHERE IDEvent = ?");
        userStatement.setString(1, String.valueOf(T.getIDEvent()));
        ResultSet userResult = userStatement.executeQuery();
        String nomevent = "";
        if (userResult.next()) {
            nomevent = userResult.getString("NomEvent");
            System.out.println(nomevent);
        }
        eventname.setText(nomevent);
        login.setText(SessionUser.getNom_user()+" "+SessionUser.getPrenom_user());

        Quantite.setText(String.valueOf( T.getPrixTicket())+ " Tickets");
        prixtotal.setText(String.valueOf(T.getPrixtotal())+ " DT");

        Ticket Ticket = T;
    }

}
