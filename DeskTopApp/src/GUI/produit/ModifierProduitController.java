package GUI.produit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Entity.Produits;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ProduitsService;
public class ModifierProduitController implements Initializable{

    @FXML
    private Button btnrefresh;
    
    
    @FXML
    private Button btnsupprimer;

    @FXML
    private Button btnretour;

    @FXML
    private Button btnsubmit;

    @FXML
    private TextField modifcategorie;

    @FXML
    private TextField modifdescription;

    @FXML
    private TextField modifnom;
    
     @FXML
    private TextField modifid;

    @FXML
    private TextField modifprix;
    
      @FXML
    private TextField modifdate;

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
    private TableColumn<Produits, String> tnom;

    @FXML
    private TableColumn<Produits, Double> tprix;
    
    @FXML
    private TextField recherche;

    
     ProduitsService ps = new ProduitsService();
     public List<Produits> prods;
    ObservableList<Produits> produitsList = FXCollections.observableArrayList();
    
    
    @FXML
    void refresh(ActionEvent event) throws SQLException, IOException {
          Parent root = FXMLLoader.load (getClass().getResource("ModifierProduit.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void loaddata() throws SQLException {  
        
         
   tid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id_produits"));
   tnom.setCellValueFactory(new PropertyValueFactory<Produits, String>("nom_produits"));
   tcategorie.setCellValueFactory(new PropertyValueFactory<Produits, String>("categorie_produits"));
   tprix.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prix_produits"));
   tdescription.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
   tdate.setCellValueFactory(new PropertyValueFactory<Produits, String>("date_creation"));
  
             try {
                 
                  prods= ps.afficherproduits();
                                
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

    
    @FXML
    void rowclicked(MouseEvent event) {
       Produits clickedProduit = produitTable.getSelectionModel().getSelectedItem();
       modifid.setText(String.valueOf(clickedProduit.getId_produits()));
       modifnom.setText(String.valueOf(clickedProduit.getNom_produits()));
       modifcategorie.setText(String.valueOf(clickedProduit.getCategorie_produits()));
        modifprix.setText(String.valueOf(clickedProduit.getPrix_produits()));
        modifdescription.setText(String.valueOf(clickedProduit.getDescription()));
        modifdate.setText(String.valueOf(clickedProduit.getDate_creation()));

    }
    
    @FXML
    void submitmodif(ActionEvent event) throws SQLException, IOException {
        ObservableList<Produits> produitsactuels =produitTable.getItems();
        int idproduit = Integer.parseInt(modifid.getText());
        double prix = Double.parseDouble(modifprix.getText());
        
        String nom = modifnom.getText();
           String categorie = modifcategorie.getText();    
        String desc = modifdescription.getText();
        String datestr= modifdate.getText();
        
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       
        
        Date date;
        date = java.sql.Date.valueOf(datestr);
        
        
//       Date date_creation;
//        date_creation = new java.sql.Date(date.getTime());
       // date = tdate.getCellData();
        
        for (Produits produit : produitsactuels) {
        if (produit.getId_produits() == idproduit)
        {
     produit.setNom_produits(nom);
      produit.setCategorie_produits(categorie);
    produit.setPrix_produits(prix);
      produit.setDescription(desc);
      
       
       
Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setContentText("produits modifiée");
    alert.show();
       
        
        produitTable.setItems(produitsactuels);
        produitTable.refresh();
        loaddata();
         Produits p = new Produits(idproduit, nom, categorie, prix, desc, (java.sql.Date) date);
   
           
    ps.modifierproduit(p);
    
    Parent root = FXMLLoader.load (getClass().getResource("ModifierProduit.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        break;
        
        }
        
        }
        
    }

       @FXML
    void submitsupprimer(ActionEvent event) throws SQLException, IOException {
ObservableList<Produits> produitsactuels =produitTable.getItems();
        int idproduit = Integer.parseInt(modifid.getText());
        
        for (Produits produit : produitsactuels) {
            if (produit.getId_produits() == idproduit)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setContentText("Produit supprimée");
    alert.show();
    
    produitTable.setItems(produitsactuels);
        produitTable.refresh();
        ps.supprimerproduit(idproduit);
        
             Parent root = FXMLLoader.load (getClass().getResource("ModifierProduit.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        break;
        }
        }
    }
    
    
    
    @FXML
    void recherche_produit() throws SQLException {

         loaddata();
    FilteredList<Produits> filteredData=new FilteredList<>(produitsList,b->true);
        recherche.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(b -> { 
                if(newValue.isEmpty() || newValue==null){
                    return true;
                }
            String recherchekeyword=newValue.toLowerCase();
            
            if(b.getNom_produits().toLowerCase().indexOf(recherchekeyword) > -1){
                return true; //found a match in t (user)
            }else if (b.getCategorie_produits().toLowerCase().indexOf(recherchekeyword) > -1) {
                 return true; //found a match in t (user)
            }else if (b.getDescription().toLowerCase().indexOf(recherchekeyword) > -1) {
                 return true; //found a match in t (user)
            }else
            return false;//no match found
                //no match found
                
            });
        });
        
        SortedList<Produits> sortedData=new SortedList<>(filteredData);
             //bind sorted result with table view
        sortedData.comparatorProperty().bind(produitTable.comparatorProperty());
              //apply filtered and sorted data to the table
        produitTable.setItems(sortedData);
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              recherche_produit();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
