package GUI.User;

import Entity.admin;
import Entity.user;
import static services.metiersUser.PasswordHashing.hashPassword;
import static services.metiersUser.Verif.showAlert;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXCheckBox;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.AdminService;

import services.metiersUser.SessionUser;

import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */


public class LoginController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpass;
    @FXML
    private JFXButton tfconx;
    @FXML
    private Label tfinscri;
    @FXML
    private AnchorPane login;

    /**
     * *****************************************
     */
    user u = null;
    admin a = null;
    UserService us = new UserService();
    AdminService as = new AdminService();
    Stage stage;
    private Scene scene;
    Parent root;
    @FXML
    private Label tfinscri1;
    @FXML
    private JFXCheckBox show;
    @FXML
    private TextField textfield;

    /**
     * ******************************************
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tfpass.setVisible(true);
        textfield.setVisible(false);

    }

    @FXML
    public void on_click_connexion(ActionEvent event) throws IOException, SQLException {
        String login = tflogin.getText();

        if (show.isSelected()) {
            tfpass.setText(textfield.getText());
        }

      //  String mdp = hashPassword(tfpass.getText());
      String mdp = tfpass.getText();

        if (login.equals("") || mdp.equals("")) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner tous les champs !");
        } else {
            u = us.getUserByLogin(login);
            if (u != null) {
                if (u.getPassword().equals(mdp)) {

                    user.setCurrent_User(u);
                    SessionUser.getInstace(u.getId_user(), u.getNom_user(), u.getPrenom_user(), u.getLogin(), u.getPassword(), u.getDate_nais_user(), u.getEmail(), u.getAdresse(), u.getTel(), u.getSexe(), u.getRoles(), u.getPhoto_user());
                    
                    if(u.getRoles().toString().equals("[\"ROLE_FREELANCER\"]"))
                      root = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePageFreelancer.fxml"));  
                    else
                        root = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePageClient.fxml"));
                    
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Mot de passe invalide!");
                }
            } else {
                a = as.getAdminByLogin(login);
                if (a != null) {

                    if (a.getPassword_admin().equals(mdp)) {
                        root = FXMLLoader.load(getClass().getResource("/GUI/Admin/TableView.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Mot de passe invalide!");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "user invalide!");
                }
            }
        }

    }

    @FXML
    private void sinscrire_Clicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void forgot_clicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void changeVisibility(ActionEvent event) {

        if (show.isSelected()) {
            textfield.setText(tfpass.getText());
            textfield.setVisible(true);
            tfpass.setVisible(false);
            return;
        }
        tfpass.setVisible(true);
        textfield.setVisible(false);
        tfpass.setText(textfield.getText());

    }

}

