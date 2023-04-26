/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.feedback;

import Entity.Feedback;
import Entity.user;
import GUI.User.UserProfileController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.FeedbackService;
import services.UserService;
import static services.UserService.search_user;
import services.metiersUser.SessionUser;
import static services.metiersUser.Verif.showAlert;


/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class FXMLFeedbackController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private Rating rating;
    
    Number stars = 0.0;
    FeedbackService fs ;
    @FXML
    private TextArea commentaire;
    @FXML
    private Button reclamation;
    @FXML
    private Button Events;
    @FXML
    private Button bar11;
    @FXML
    private Button bar22;
    @FXML
    private TextField search;
    @FXML
    private Button ring;
    @FXML
    private Button feedback;
    /**
     * Initializes the controller class.
     */
    
    Stage stage;
    Scene scene;
    Parent root;
      UserService us =new UserService();
    user utilisateur=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
    }

    @FXML
    private void envoyer_feedback(ActionEvent event) {
        if (commentaire.getText().length() == 0) {
            commentaire.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(commentaire).play();     
        }else{
            // on suppose que l id_user connecter est 1
        int id_freelancer = 1;
        fs = new FeedbackService();
         LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);   
        // on suppose que l id_client connecter est 2
        int id_client = 1;
       
        
       
            
        Feedback f = new Feedback(id_freelancer, id_client, commentaire.getText(), date,rating.getRating());           
        try {
            fs.ajouter(f);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Feedback bien reçu, merci!");
        alert.showAndWait();
        rating.setRating(0.0);
        commentaire.setText(null);
        
        }
        
    }

    @FXML
    private void depanini_clicked(MouseEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePageFreelancer.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    private void acc(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
      Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/reclamation/FXMLReclamation.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void EventsList(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/participate/participate.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void run1(MouseEvent event) {
    }

    @FXML
    private void run2(MouseEvent event) {
    }

    @FXML
    private void search(MouseEvent event) throws IOException {
        
        if(search_user("nom_user",search.getText())!=null)
            utilisateur=search_user("nom_user",search.getText());
        else
            if(search_user("prenom_user",search.getText())!=null)
                utilisateur=search_user("prenom_user",search.getText());
            else
               if(search_user("login",search.getText())!=null)
                  utilisateur=search_user("login",search.getText());
               else{
               }
        
        if(utilisateur==null)
           showAlert(Alert.AlertType.ERROR, "Données erronés", "", "Utilisateur n'existe pas ");  
        else{

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/User/userProfile.fxml"));
            root =loader.load();
            
            UserProfileController userprofile=loader.getController();
            userprofile.displayInfo(utilisateur); 
      
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene =new Scene(root);
                Stage stage = new Stage();
            stage.setScene(scene);   
            stage.show();  
        }
    }

    @FXML
    private void notificationEvent(ActionEvent event) {
    }

    @FXML
    private void feedback(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/feedback/FXMLFeedback.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

}
