/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.produit;

import Entity.Produits;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ProduitsService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherProduitController implements Initializable {
    
    @FXML
    private Button btnretour;
    
    @FXML
    private Button btnrefresh;

   
     @FXML
    private TableView<Produits> produitTable;

    @FXML
    private TableColumn<Produits, String> tcategorie;

    @FXML
    private TableColumn<Produits, String> tdate;

    @FXML
    private TableColumn<Produits, String> tdescription;

    @FXML
    private TableColumn<Produits, Integer> tid;

    @FXML
    private TableColumn<Produits, Integer> tbare;
    
    @FXML
    private TableColumn<Produits, String> tnom;
      
    @FXML
    private TableColumn<Produits, String> timage;

    @FXML
    private TableColumn<Produits, Double> tprix;

    
     ProduitsService ps= new ProduitsService();
     public List<Produits> prods;
    
    
    
    @FXML
    void refresh() throws SQLException {  
    loaddata();
    }
       
     
    void loaddata() {
     ObservableList<Produits> produitsList = FXCollections.observableArrayList();
         
   tid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id_produits"));
   tnom.setCellValueFactory(new PropertyValueFactory<Produits, String>("nom_produits"));
   tcategorie.setCellValueFactory(new PropertyValueFactory<Produits, String>("categorie_produits"));
   tprix.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prix_produits"));
   tdescription.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
   tdate.setCellValueFactory(new PropertyValueFactory<Produits, String>("date_creation"));
   timage.setCellValueFactory(new PropertyValueFactory<Produits, String>("image_produits"));
   tbare.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("barecode"));
  
             try {
                 
                  prods = ps.afficherproduits();
                                
                 } catch (SQLException ex) {
                  System.out.println(ex.getMessage());   
                    }
                    produitsList.addAll(prods);
                    produitTable.setItems(produitsList); }
    
    
  
    @FXML
    void retour(ActionEvent event) throws IOException {
 Parent root = FXMLLoader.load (getClass().getResource("Firstpage.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    String query = null;
    Connection connexion = null;
    PreparedStatement pr = null;
    ResultSet resultset = null;
    Produits produit = null;
    
    
  
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        loaddata();
    }    
    
    
    
    
    }
    

