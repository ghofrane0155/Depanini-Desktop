/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.client;

import Entity.Contrats;
import Entity.Offres;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ContratsService;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class UISignerController implements Initializable {

    @FXML
    private TextField prix_offre;
    @FXML
    private TextField nom_offre;
    @FXML
    private TextField description_offre;
    private ImageView imageview;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker date;
    @FXML
    private ImageView signer;

    /**
     * Initializes the controller class.
     */
    Image image1 = new Image(getClass().getResourceAsStream("/GUI/images/signer.PNG"));
    @FXML
    private Label label;
    @FXML
    private Label label1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signer.setImage(image1);
    }

    void setTextFields(Offres NewO) {
        nom_offre.setText(String.valueOf(NewO.getNom_offre()));
        prix_offre.setText(String.valueOf(NewO.getPrix_offre()));
        description_offre.setText(NewO.getDescription_offre());
        
    }

    @FXML
    private void signerC(MouseEvent event) throws Exception {
        ContratsService sp = new ContratsService();

        if (sp.existe("cin", cin.getText()) > 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("cin déjà existe, veuillez choisir un autre !");
            al.showAndWait();

        } else {
            //controle de saisie
            if (cin.getText().length() == 0) {
                cin.setStyle("-fx-border-color: red");
                label.setText("Ce champ est requis");
                event.consume();
            } else {
                cin.setStyle(null);
                label.setText("");
            }

            if (date.getValue() == null) {
                date.setStyle("-fx-border-color: red");
                label1.setText("Ce champ est requis");
                event.consume();
            } else {
                date.setStyle(null);
                label1.setText("");
            }

            try {

                Contrats p = new Contrats(nom_offre.getText(), date.getValue().toString(), description_offre.getText(), Double.parseDouble(prix_offre.getText()), Integer.parseInt(cin.getText()));

                sp.ajouter(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("success");
                alert.setContentText("contrat ajoutee avec succes");
                alert.show();
                System.out.println("contrat ajoutee");
            } catch (SQLException ex) {
                ex.getMessage();
            }

        }

    }

}
