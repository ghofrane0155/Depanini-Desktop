/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.commande;

import Entity.Produits;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ProduitsService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProduitController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label nomlabel;

    @FXML
    private Label prixlabel;
    
        ProduitsService ps= new ProduitsService();

    Produits produit;
 String path=produit.getImage();
 Image img=new Image(getClass().getResourceAsStream(path));
    /**
     *
     * @param produit
     */
    public void setdata(Produits p) throws SQLException{
        List<Produits> prods = new ArrayList<>();
        Connection connexion = null;
          String req = "select * from produit";
      Statement  stm = connexion.createStatement();
        ResultSet res = stm.executeQuery(req);      
        while(res.next()) {  
            
            p = new Produits(
                    res.getString("nom_produit"),
                    res.getString("categorie_produit"), 
                    res.getDouble("prix_produit"), 
                    res.getString("description"),    
                   res.getString("image_produit") 
                    
            );        
                    
           
            prods.add(p);
        }

   this.produit = p;
   nomlabel.setText(p.getNom_produits());
   prixlabel.setText(Double.toString(p.getPrix_produits())+ " DTN");
   image.setImage(img);
   
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
