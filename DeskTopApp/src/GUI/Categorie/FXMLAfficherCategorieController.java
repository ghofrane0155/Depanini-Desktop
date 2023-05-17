/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Categorie;

import Entity.Categories;


import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import services.CategoriesServices;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLAfficherCategorieController implements Initializable {
    java.sql.Connection connexion = MyDB.getInstance().getConx();
    String requete2 = "SELECT id_categorie , nom_categorie ,image_categorie FROM  categories";
    public List<Categories>listes;
    private Categories cat = new Categories();
    private Stage stage;
      private Scene scene;
      private Parent root;

    @FXML
    private TableView<Categories> table;
    @FXML
    private TableColumn<Categories, Integer> id_categorie;
    @FXML
    private TableColumn<Categories, String> nom_categorie;
     @FXML
    private TableColumn<Categories, String> image_categorie;
   
    
    @FXML
    private Button afficher_categorie;
    
    @FXML
    private Button supp;
    
        public CategoriesServices s = new CategoriesServices() ;
    public List<Categories> categories_list;
    
    @FXML
    private ImageView return_home;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           return_home.setOnMouseClicked(((event) -> {
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Categorie/FXMLCategorie.fxml"));
                Scene scene =new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }));
        
    } 
    
    @FXML
    private void Afficher(ActionEvent event)  {
        try {
            refreshTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**************refrech table*****************************************************/
    private void refreshTable() throws SQLException{  
        ObservableList<Categories> list_services = FXCollections.observableArrayList();
        id_categorie.setCellValueFactory(new PropertyValueFactory<Categories,Integer>("id_categorie"));
        nom_categorie.setCellValueFactory(new PropertyValueFactory<Categories,String>("nom_categorie"));
        image_categorie.setCellValueFactory(new PropertyValueFactory<Categories,String>("image_categorie"));
          
        
         listes = s.afficher();
        list_services.addAll(listes);
        table.setItems(list_services);
        
    }
    


    @FXML
    private void supp(ActionEvent event) throws SQLException{
    Categories categorie_selected=table.getSelectionModel().getSelectedItem();
        int id=categorie_selected.getId_categorie();
        s.supprimer(id);
        refreshTable();
    } 
    }