/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entity.user;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.FeedbackService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class UserProfileController implements Initializable {

    @FXML
    private Label label_np;
    @FXML
    private Label label_role;
    @FXML
    private Label label_mail;
    @FXML
    private Label label_phone;
    @FXML
    private Label label_adr;
    @FXML
    private Label label_id_recl;
    @FXML
    private Label label_log;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView img_profile;
    @FXML
    private Label stars;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {exit.setOnMouseClicked((MouseEvent event) -> {
          Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();           
        });
    }

    public void displayInfo(user u) {

        label_np.setText(u.getNom_user() + " " + u.getPrenom_user());
        if(u.getRoles().equals("[\"ROLE_FREELANCER\"]"))
            label_role.setText("FREELANCER");
        else
            label_role.setText("CLIENT");
        label_log.setText("@" + u.getLogin());
        label_phone.setText(u.getTel());
        label_mail.setText(u.getEmail());
        label_adr.setText(u.getAdresse());

        String path = u.getPhoto_user();
        Image img = new Image(getClass().getResourceAsStream(path));
        img_profile.setImage(img);
        // youssef ajouter le metier qui permet de calculer le nombre des etoile de chaque user
        FeedbackService fs = new FeedbackService();
        try {
            Double res = fs.calculeStars(u.getId_user());
            int total_stars = Double.valueOf(res).intValue();
            stars.setText("" + total_stars);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
