/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.reclamation;

import Entity.Reclamation;
import Entity.Type;
import Entity.user;
import GUI.User.UserProfileController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ReclamationService;
import static services.UserService.search_user;
import static services.metiersUser.Verif.showAlert;

/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class FXMLReclamationController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField Phone;
    @FXML
    private ChoiceBox<String> Type_rec_option;
    @FXML
    private Button select_file_btn;
    @FXML
    private Button submit_btn;
    @FXML
    private TextArea description;

    private String[] type = {"Information sur votre compte", "Information sur vos commandes", "Suggestions et remarques sur le site", "Signaler un dysfonctionnement", "Autre"};
    @FXML
    private Label name_file_selected;
    @FXML
    private Label emailError;
    @FXML
    private Label typeError;
    @FXML
    private Label descroptionError;
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
    user utilisateur = null;
    Stage stage;
    Scene scene;
    Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Type_rec_option.getItems().addAll(type);

    }
    // ***********Email Validator function *********************

    public static boolean isValidEmail(String email) {
        // Expression régulière pour vérifier l'adresse e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        // Vérifier si l'adresse e-mail correspond à l'expression régulière
        return matcher.matches();
    }
    // *************************************************

    // ***********Submit function *********************
    @FXML
    private void submit(ActionEvent event) throws IOException {

        if (email.getText().length() == 0) {
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(email).play();
            emailError.setText("L'e-mail est obligatoire.");
            emailError.setVisible(true);
        } else if (!isValidEmail(email.getText())) {
            emailError.setVisible(false);
            email.setStyle(null);
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(email).play();
            emailError.setText("L'e-mail est invalide.");
            emailError.setVisible(true);
        } else if (Type_rec_option.getValue() == null) {
            email.setStyle("-fx-border-color: green; -fx-border-width: 2px ;");
            emailError.setVisible(false);
            typeError.setText("Le Type est obligatoire.");
            typeError.setVisible(true);

        } else if (description.getText().length() == 0) {
            email.setStyle("-fx-border-color: green; -fx-border-width: 2px ;");
            typeError.setVisible(false);
            description.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
            new animatefx.animation.Shake(description).play();
            descroptionError.setText("La Description est obligatoire.");
            descroptionError.setVisible(true);
        } else {
            email.setStyle("-fx-border-color: green; -fx-border-width: 2px :");
            description.setStyle(null);
            descroptionError.setVisible(false);

            ReclamationService rs = new ReclamationService();
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
            Type t = null;
            switch (Type_rec_option.getValue()) {
                case "Information sur votre compte":
                    t = Type.ACCOUNT_INFORMATION;
                    break;
                case "Information sur vos commandes":
                    t = Type.ORDER_INFORMATION;
                    break;
                case "Suggestions et remarques sur le site":
                    t = Type.WEBSITE_FEEDBACK;
                    break;
                case "Signaler un dysfonctionnement":
                    t = Type.REPORT_ISSUE;
                    break;
                case "Autre":
                    t = Type.OTHER;
                    break;
            }
            Reclamation rec = new Reclamation(t.toString(), description.getText(), date, name_file_selected.getText());
            try {
                rs.ajouterSelonEmailUser(email.getText(), rec);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("l'ajout avec succes");
            alert.setHeaderText("Results:");
            alert.setContentText("Reclamation envoyer avec success!");
            alert.show();
            this.Type_rec_option.setValue(null);
            this.Phone.setText(null);
            this.description.setText(null);
            this.email.setText(null);
            this.name_file_selected.setText(null);
        }

    }
    // *************************************************

    // ***********Upload File function *********************
    @FXML
    private void select_file(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            name_file_selected.setText(selectedFile.getName());
        } else {
            System.out.println("file is not valid");
        }
    }
// *************************************************

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
        if (search_user("nom_user", search.getText()) != null) {
            utilisateur = search_user("nom_user", search.getText());
        } else if (search_user("prenom_user", search.getText()) != null) {
            utilisateur = search_user("prenom_user", search.getText());
        } else if (search_user("login", search.getText()) != null) {
            utilisateur = search_user("login", search.getText());
        } else {
        }

        if (utilisateur == null) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "", "Utilisateur n'existe pas ");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/User/userProfile.fxml"));
            root = loader.load();

            UserProfileController userprofile = loader.getController();
            userprofile.displayInfo(utilisateur);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void notificationEvent(ActionEvent event) {
    }
}