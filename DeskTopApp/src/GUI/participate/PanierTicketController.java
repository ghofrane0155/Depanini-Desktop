/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.participate;


import Entity.Event;
import Entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.EventService;
import services.TicketService;

import services.metiersUser.SessionUser;



/**
 * FXML Controller class
 *
 * @author noure
 */
public class PanierTicketController implements Initializable {


    private Label eventname;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label loginid;
    ArrayList<Ticket> Ticket = new ArrayList<>();
   // Image image = new Image("/src/images/qr.png");
    @FXML
    private VBox pnItems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       TicketService serviceTicket = new TicketService();
        try {
            System.out.println(serviceTicket.getAll());
            Ticket.addAll(serviceTicket.getAll(SessionUser.getLogin()));//serviceTicket.getAll(session.getLogin())
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
       pnItems.getChildren().clear();
       
        
        Node [] nodes = new  Node[15];
        for(int i = 0; i<Ticket.size() ; i++)
        {
             
                try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/participate/Ticket.fxml"));
                AnchorPane abc = fxmlLoader.load();
                TicketController itemcontroller = fxmlLoader.getController();
                itemcontroller.getEvent(Ticket.get(i));
                
                
                
                
                    pnItems.getChildren().add(abc);
                } catch (IOException ex) {
                    
                } catch (SQLException ex) {
               Logger.getLogger(PanierTicketController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }    
    }
        
    
       

    @FXML
    private void backToHomeUserPage(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/participate/participate.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

}
