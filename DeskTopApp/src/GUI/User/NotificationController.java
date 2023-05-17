/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;
import Entity.Notification;
import Entity.user;
import java.io.IOException;
import services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.NotificationService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */

public class NotificationController extends ProfileController implements Initializable {

    @FXML
    private Label tfnom_prenom;
/***************************************/
    private Stage stage;
    private Scene scene;
    private Parent root;
/****************************************/
    user utilisateur=null;
    UserService us=new UserService();
    @FXML
    private ImageView tfimg;
/*****************************************/
    ArrayList<Notification> notifications = new ArrayList<>();
    @FXML
    private VBox pnItems;
    @FXML
    private AnchorPane anchorpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     displayInfo(user.Current_User);
     
      NotificationService nfs = new NotificationService();
        try {
            
            
            notifications.addAll(nfs.afficherNameNemberStars(user.Current_User.getId_user()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
       pnItems.getChildren().clear();
       
        
        Node [] nodes = new  Node[15];
        for(int i = 0; i<notifications.size() ; i++)
        {         
                try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/User/Notif.fxml"));
                AnchorPane abc = fxmlLoader.load();
                NotifController itemcontroller = fxmlLoader.getController();

                itemcontroller.getNotif(notifications.get(i));
                  pnItems.getChildren().add(abc);
                } catch (IOException ex) {
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage()); 
          }}
        
     
       ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pnItems);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

      
        anchorpane.getChildren().add(scrollPane);
    
        
    }  
/*****************************************************/
    public void displayInfo(user u) {
        //utilisateur=u;


        tfnom_prenom.setText(u.getNom_user()+" "+u.getPrenom_user());
        String path="/GUI/Images/"+u.getPhoto_user();
        Image img=new Image(getClass().getResourceAsStream(path));
        tfimg.setImage(img);
	}

}
