/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.commande;

import Entity.Commandes;
import Entity.Produits;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.CommandeService;
import services.ProduitsService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CommandeController implements Initializable {

    @FXML
    private Button btn_ajouter_au_panier;

    @FXML
    private ImageView btn_panier;

    @FXML
    private Label descriptionproduit;

    @FXML
    private Label nomproduit;

    @FXML
    private Label prixproduit;

    @FXML
    private Label exit;

    @FXML
    private Label idproduit;

    @FXML
    private VBox produitchoisit;

    @FXML
    private TextField chercherField;

    @FXML
    private TableView<Produits> produitTable;

    @FXML
    private TableColumn<Produits, String> tcategorie;

    @FXML
    private TableColumn<Produits, String> tdescription;

    @FXML
    private TableColumn<Produits, Integer> tid;

    @FXML
    private TableColumn<Produits, String> tnom;

    @FXML
    private TableColumn<Produits, Double> tprix;

    @FXML
    private TableColumn<Produits, String> timage;

    @FXML
    private Label path;
    @FXML
    private ImageView taswirtii;

    @FXML

    ProduitsService ps = new ProduitsService();
    public List<Produits> prods;

    ObservableList<Produits> produitsList = FXCollections.observableArrayList();

    PreparedStatement pr;
    Connection connexion;

    void loaddata() throws SQLException {

        tid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id_produits"));
        tnom.setCellValueFactory(new PropertyValueFactory<Produits, String>("nom_produits"));
        tcategorie.setCellValueFactory(new PropertyValueFactory<Produits, String>("categorie_produits"));
        tprix.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prix_produits"));
        tdescription.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        timage.setCellValueFactory(new PropertyValueFactory<Produits, String>("image_produits"));

        prods = ps.afficherproduits();
        produitsList.addAll(prods);
        produitTable.setItems(produitsList);
    }

    @FXML
    void produit_clicked(MouseEvent event) throws SQLException {
        //Produits produit = null ;
        Produits clickedProduit = produitTable.getSelectionModel().getSelectedItem();

        nomproduit.setText(String.valueOf(clickedProduit.getNom_produits()));
        prixproduit.setText(String.valueOf(clickedProduit.getPrix_produits()) + " DTN");
        descriptionproduit.setText(String.valueOf(clickedProduit.getDescription()));
        idproduit.setText(String.valueOf(clickedProduit.getId_produits()));
        String pathI = clickedProduit.getImage_produits();
        Image newImage = new Image(pathI);
        taswirtii.setImage(newImage);

    }

    @FXML
    void Ajouter_au_panier(ActionEvent event) throws SQLException {

        String nom = nomproduit.getText();
        Integer id = Integer.parseInt(idproduit.getText());
        Commandes c = new Commandes(id, 3);
        CommandeService cs = new CommandeService();
        cs.ajouter(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText("Commande ajoutée avec succes ");
        alert.show();
        System.out.println("Produit ajouté au panier");

    }

    @FXML
    void chercher_produit() throws SQLException {

        loaddata();
        FilteredList<Produits> filteredData = new FilteredList<>(produitsList, b -> true);
        chercherField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(b -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String chercherFieldkeyword = newValue.toLowerCase();

                if (b.getNom_produits().toLowerCase().indexOf(chercherFieldkeyword) > -1) {
                    return true; //found a match in t (user)
                } else if (b.getCategorie_produits().toLowerCase().indexOf(chercherFieldkeyword) > -1) {
                    return true; //found a match in t (user)
                } else if (b.getDescription().toLowerCase().indexOf(chercherFieldkeyword) > -1) {
                    return true; //found a match in t (user)
                } else {
                    return false;//no match found
                }                //no match found

            });
        });

        SortedList<Produits> sortedData = new SortedList<>(filteredData);
        //bind sorted result with table view
        sortedData.comparatorProperty().bind(produitTable.comparatorProperty());
        //apply filtered and sorted data to the table
        produitTable.setItems(sortedData);

    }

    void visiter_panier(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/commande/InterfacePanier.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void visiter_panier_image(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/commande/InterfacePanier.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            chercher_produit();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void exit(MouseEvent event) {

    }
}
