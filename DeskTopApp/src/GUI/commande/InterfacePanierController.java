/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.commande;

import Entity.Commandes;
import Entity.Produits;
import Utils.MyDB;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class InterfacePanierController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddata();    }    
    
   
 @FXML
    private ImageView back;
 
    @FXML
    private Button btn_supprimer;

    @FXML
    private TextField id_produit;

    @FXML
    private TableView<Commandes> commandeTable;

    @FXML
    private TextField description;

    @FXML
    private TextField filterField;

    @FXML
    private Button imprimer;

    @FXML
    private TextField id_user;

    @FXML
    private TextField id_commande;

    @FXML
    private TableColumn<Commandes, Integer> tcommande;

    @FXML
    private TableColumn<Commandes, String> tdate;

    @FXML
    private TableColumn<Commandes, Integer> tid;

    @FXML
    private TableColumn<Commandes, Integer> tidproduit;

    
    
    CommandeService cs= new CommandeService();
     public List<Commandes> comm;
     Connection connexion;
     
     void loaddata() {
     ObservableList<Commandes> commandesList = FXCollections.observableArrayList();
         
   tid.setCellValueFactory(new PropertyValueFactory<Commandes, Integer>("id_user"));
   tidproduit.setCellValueFactory(new PropertyValueFactory<Commandes, Integer>("id_produit"));
   tcommande.setCellValueFactory(new PropertyValueFactory<Commandes, Integer>("id_commande"));
   tdate.setCellValueFactory(new PropertyValueFactory<Commandes, String>("date_commande"));
  
  
             try {
                 
                  comm = cs.afficher();
                                
                 } catch (SQLException ex) {
                  System.out.println(ex.getMessage());   
                    }
                    commandesList.addAll(comm);
                    commandeTable.setItems(commandesList); }
    
    
    
     @FXML
    void back(MouseEvent event) throws IOException {
 Parent root = FXMLLoader.load (getClass().getResource("Commande.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    PreparedStatement ps=null;
    @FXML
    void imprimer_on_click(ActionEvent event) {
     
        
        
           com.itextpdf.text.Document document = new com.itextpdf.text.Document();

               try {
       PdfWriter.getInstance(document,new FileOutputStream("FACTURE.pdf"));
       document.open();
       
    
       //document.add(new Paragraph("Liste des paiements"));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("\":::::::******* Vous trouvez ci joint vos commandes*******:::::::\" ", font);
       p.setAlignment(Element.ALIGN_CENTER);
       document.add(p);
       document.add(new Paragraph(" "));
       document.add(new Paragraph(" "));
 

       PdfPTable tabpdf = new PdfPTable(9);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       
       cell = new PdfPCell(new Phrase("ID Commande", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
        cell = new PdfPCell(new Phrase("ID Produit", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("ID User", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
      
     
      
       
//        cell = new PdfPCell(new Phrase("Date De Commande", FontFactory.getFont("Times New Roman", 11)));
//       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//       cell.setBackgroundColor(BaseColor.WHITE);
//       tabpdf.addCell(cell);
       
       
       
       
       
        Connection connexion = MyDB.getInstance().getConx();
                   
                            
                        String query="select * from commandes";
                PreparedStatement ps=connexion.prepareStatement(query);
                   System.out.println(ps);
                   ResultSet  rs=ps.executeQuery(query);
                   
       while (rs.next()) {
           
             cell = new PdfPCell(new Phrase(rs.getString(String.valueOf(rs.getInt(1))), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString(String.valueOf(rs.getInt(2))), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString(String.valueOf(rs.getInt(3))), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
          
           
        
//           cell = new PdfPCell(new Phrase(rs.getString("date_commande"), FontFactory.getFont("Times New Roman", 11)));
//           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//           cell.setBackgroundColor(BaseColor.WHITE);
//           tabpdf.addCell(cell);
     
              document.add(tabpdf);
       }
       
          JOptionPane.showMessageDialog(null, "Success !");
          document.close();
          Desktop.getDesktop().open(new File("FACTURE.pdf"));
//          notif("Succes","Votre document a été enregistré au format PDF !");
       }
 
        catch (SQLException | DocumentException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }
    }
   
        
        
        
        
        
        
//        try {
//     String file_name = "facture.pdf";
//                Document document = new Document ();
//                PdfWriter.getInstance (document, new FileOutputStream (file_name));
//                document.open();
//                
//                Paragraph para = new Paragraph(
//                         ":::::::::::::::::::******************** Vous trouvez ci joint vos commandes********************:::::::::::::::::::: "
//                        + "\n--------------------------------------------------------Commandes--------------------------------------------------------\n");
//                document.add(para);
//                
//               Connection connexion = MyDB.getInstance().getConnexion();
//                    PreparedStatement ps=null;
//                            ResultSet rs=null;
//                        String query="select * from commandes";
//                        ps=connexion.prepareStatement (query);
//                        rs=ps.executeQuery();
//                while (rs.next()) {
//                Paragraph para1=new Paragraph ("{ id user:" +rs.getInt ("id_user")+" "
//                        +" id produit:"+rs.getInt ("id_produit")+" "
//                        +" id commande:" +rs.getInt ("id_commande")
//                        +"}");
//                    document.add (para1);
//                    document.add(new Paragraph (" "));
//                    }
//                
//  
//                Paragraph para2 = new Paragraph(
//                         "---------------------------------------------------------------------------------------------------------------------------------\n"
//                        +"\n\n\n\n\n\n\n\n\n\n****************************************Merci pour votre confiance****************************************\n"
//                 );
//                document.add(para2);
//                
//                
//                document.close();
//                
//                
//                System.out.println("✔Facture exportée✔");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//    alert.setTitle("success");
//    alert.setContentText("✔Facture exportée✔");
//    alert.show();
//    
//      } catch (Exception e) {
//                System.err.println(e);}
//       
    
    

    @FXML
    void commande_clicked(MouseEvent event) {
 Commandes clickedCommande = commandeTable.getSelectionModel().getSelectedItem();
       id_user.setText(String.valueOf(clickedCommande.getId_user()));
       id_produit.setText(String.valueOf(clickedCommande.getId_produit()));
       id_commande.setText(String.valueOf(clickedCommande.getId_commande()));
        
    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException, IOException {
ObservableList<Commandes> commandesactuels =commandeTable.getItems();
        int iduser = Integer.parseInt(id_user.getText());
        int idproduit = Integer.parseInt(id_produit.getText());
        
        for (Commandes commande : commandesactuels) {
            if ((commande.getId_user()== iduser)&&(commande.getId_produit()== idproduit))
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setContentText("Produit supprimée");
    alert.show();
    
    commandeTable.setItems(commandesactuels);
        commandeTable.refresh();
        cs.supprimer(idproduit);
        
             Parent root = FXMLLoader.load (getClass().getResource("InterfacePanier.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // root.getChildren().addAll(esemy l buttons normalement);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        break;
        }
        }
    }



}
