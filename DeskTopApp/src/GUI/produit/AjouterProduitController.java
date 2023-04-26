package GUI.produit;

import Entity.Produits;
import com.barcodelib.barcode.Linear;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.ProduitsService;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;


public class AjouterProduitController implements Initializable{

    
    @FXML
    private ImageView imageprod;
    
    @FXML
    private ImageView check;
    
      @FXML
    private TextField categorie_produit;
        
    @FXML
    private TextField code_a_barre;
            
    @FXML
    private Button btn_importer_image;
    
    @FXML
    private Button ajouter_produit;

    @FXML
    private Button btnretour;


    @FXML
    private TextField description_produit;

    @FXML
    private TextField nom_produit;

    @FXML
    private TextField prix_produit;
    
   
    

//    Produit p=new Produit();
//    ProduitService ps;
    FileChooser fc; 
   File selectedFile;
   String pic="/GUI/images/user1.png";
   
    ObservableList<String> cat = FXCollections.observableArrayList("Technique", "Design", "Logo", "Site web", "Formation en ligne");
    
    public void initialize(URL url, ResourceBundle rb) {


    }
    
    @FXML
    void importer_image(ActionEvent event) throws IOException {

     
        fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new ExtensionFilter("IMG Files", "*.png","*.jpg","*.gif"));
         
         selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            BufferedImage bufferdimg= ImageIO.read(selectedFile);
            javafx.scene.image.Image image= SwingFXUtils.toFXImage(bufferdimg, null);
            imageprod.setImage(image);
            
         pic="/GUI/images"+selectedFile.getName();    
        } else {
            System.out.println("file is not valid");
        }
      
    } 
    @FXML

            void Ajouterproduit(ActionEvent event) throws IOException, SQLException {
   
        Alert erreur = new Alert(AlertType.ERROR);
        if ((nom_produit.getText().isEmpty()) || (categorie_produit.getText().isEmpty()) || (prix_produit.getText().isEmpty()) || (description_produit.getText().isEmpty())) {
            erreur.setTitle("Message d'erreur");
            erreur.setContentText("Remplissez tous les champs"); 
            erreur.show();
        }
        else {
        String nom = nom_produit.getText();
        String prixstr = prix_produit.getText();
        double prix = Double.parseDouble(prixstr);        
        String desc = description_produit.getText();
        String categorie = categorie_produit.getText();
        String code = code_a_barre.getText();
        
      
        
//        switch (categorie_produit.getValue()) {
//            case "Technique":
//                String categorie = "Technique";
//                
//                break;
//            case "Design":
//                categorie = "Design";
//                 
//                break;
//            case "Logo":
//                categorie = "Logo";
//                break;
//            case "Site web":
//                categorie = "Site web"; break;
//            case "Formation en ligne":
//                categorie = "Formation en ligne";
//                
//                break;
//        
//        }
    ProduitsService sp = new ProduitsService();
    Produits p; 
                 p = new Produits(nom, categorie, prix, desc, pic, Integer.parseInt(code));
                 sp.ajouterP(p);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setContentText("Demande d'ajout envoyée avec succes");
    alert.show();
    System.out.println("Demande ajoutee");
    }
        
        
    }
            @FXML
    void check_on_click(MouseEvent event) {

        //code à barre
        try {
            
           Linear barcode = new Linear();
           barcode.setType(Linear.CODE128B);
            barcode.setData(code_a_barre.getText());
            barcode.setI(11.0f);
            
            String codename = code_a_barre.getText();
            barcode.renderBarcode("..\\"+ codename +".png");
            
        } catch (Exception e) {
        }
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setContentText("Code à barre créer");
    alert.show();
    }
    
      @FXML
    void code_on_click(MouseEvent event) {
 Random random = new Random();
         int randomNumber = random.nextInt(1000000000); // generates a random integer between 0 and 99
         code_a_barre.setText(String.valueOf(randomNumber));
    }
            
    @FXML
    void recherche_produit(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Firstpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
