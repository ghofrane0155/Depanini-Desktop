/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Service;

import Entity.Offres;
import Entity.Services;
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
import services.OffresServices;
import services.ServiceServices;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLAfficherServicesController implements Initializable {
    java.sql.Connection connexion = MyDB.getInstance().getConx();
    String requete2 = "SELECT id_service , nom_service ,prix_service,descrption_service FROM  services";
    public List<Services>listes;
    private Services serv = new Services();
    private Stage stage;
      private Scene scene;
      private Parent root;

    @FXML
    private TableView<Services> table;
    @FXML
    private TableColumn<Services, Integer> id_service;
    @FXML
    private TableColumn<Services, String> nom_service;
    @FXML
    private TableColumn<Services, String> prix_service;
    
    @FXML
    private Button afficher_service;
    
    @FXML
    private Button supp;
    
        public ServiceServices s = new ServiceServices() ;
    public List<Services> services_list;
    @FXML
    private TableColumn<Services, String> descrption_service;
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
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Service/FXMLServices.fxml"));
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
        ObservableList<Services> list_services = FXCollections.observableArrayList();
        id_service.setCellValueFactory(new PropertyValueFactory<Services,Integer>("id_service"));
        nom_service.setCellValueFactory(new PropertyValueFactory<Services,String>("nom_service"));
        prix_service.setCellValueFactory(new PropertyValueFactory<Services,String>("prix_service"));
        descrption_service.setCellValueFactory(new PropertyValueFactory<Services,String>("description_service"));   
        
         listes = s.afficher();
        list_services.addAll(listes);
        table.setItems(list_services);
        
    }
    


    @FXML
    private void supp(ActionEvent event) throws SQLException{
    Services service_selected=table.getSelectionModel().getSelectedItem();
        int id=service_selected.getId_service();
        s.supprimer(id);
        refreshTable();
    } 
    }