

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Offre;

import Entity.Offres;
import Utils.MyDB;
import com.itextpdf.text.BaseColor;
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
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.OffresServices;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLAfficherController implements Initializable {
    java.sql.Connection connexion = MyDB.getInstance().getConx();
     String requete2 = "SELECT id_offre , id_user ,prix_offre,description_offre,localisation_offre,nom_offre,image_offre,type_offre,type_cat FROM  offres";
public List<Offres>listec;
private OffresServices sc = new OffresServices();
  private Stage stage;
      private Scene scene;
      private Parent root;

    @FXML
    private TableView<Offres> table;
    @FXML
    private Button afficher_offre;
    public Offres o;
    public List<Offres> offres;
    private OffresServices os=new OffresServices();
    @FXML
    private TableColumn<Offres, Integer> id_offre;
    @FXML
    private TableColumn<Offres, Integer> id_user;
    @FXML
    private TableColumn<Offres, Double> prix_offre;
    @FXML
    private TableColumn<Offres, String> description_offre;
    @FXML
    private TableColumn<Offres, String> nom_offre;
    @FXML
    private TableColumn<Offres, String> image_offre;
    @FXML
    private TableColumn<Offres, Integer> id_categorie;
    @FXML
    private TableColumn<Offres, Enum> type_offre;
    @FXML
    private Button supp;
    @FXML
    private ImageView return_home;
    @FXML
    private TextField filter;
    @FXML
    private Button Rechercher;
    @FXML
    private Button Stats;
    @FXML
    private TableColumn<Offres, String> localisation;
    @FXML
    private ImageView map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        return_home.setOnMouseClicked(((event) -> {
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLOffre.fxml"));
                Scene scene =new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }));
        
        
        map.setOnMouseClicked(event -> {
            Stage mapStage = new Stage();
            WebView webView = new WebView();
            webView.getEngine().load("https://maps.google.com");
            BorderPane root = new BorderPane();
            root.setCenter(webView);
            Scene scene = new Scene(root, 800, 600);
            mapStage.setScene(scene);
            mapStage.show();
        });
              
        
        
                
                }    
/**************refrech table*****************************************************/
    private void refreshTable(){    
        ObservableList<Offres> offreList = FXCollections.observableArrayList();
  
        id_offre.setCellValueFactory(new PropertyValueFactory<Offres,Integer>("id_offre"));
        id_user.setCellValueFactory(new PropertyValueFactory<Offres,Integer>("id_user"));
        prix_offre.setCellValueFactory(new PropertyValueFactory<Offres,Double>("prix_offre"));
        description_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("description_offre"));
        //localisation_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("localisation_offre"));  
        nom_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("nom_offre"));
        image_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("image_offre"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<Offres,Integer>("type_cat"));
        type_offre.setCellValueFactory(new PropertyValueFactory<Offres,Enum>("type_offre"));
        localisation.setCellValueFactory(new PropertyValueFactory<Offres,String>("location_offre"));

           try {
            offres=os.afficher();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        offreList.addAll(offres);
        table.setItems(offreList);
    }
/***********on apui sur affiche =>refresh table*********************************/
    @FXML
    private void Afficher(ActionEvent event) {
        refreshTable();
    }
/*************************delete****************************************************/
    @FXML
    private void supp(ActionEvent event) throws SQLException {
        Offres offre_selected=table.getSelectionModel().getSelectedItem();
        int id=offre_selected.getId_offre();
        os.supprimer(id);
        refreshTable();
    }
@FXML
    private void chercher(ActionEvent event) {
        
    }
    
    
    
    @FXML
    private void Rechercher(ActionEvent event) {
        String nom = filter.getText();

            ObservableList<Offres> offrelist = FXCollections.observableArrayList();
   id_offre.setCellValueFactory(new PropertyValueFactory<Offres,Integer>("id_offre"));
        id_user.setCellValueFactory(new PropertyValueFactory<Offres,Integer>("id_user"));
        prix_offre.setCellValueFactory(new PropertyValueFactory<Offres,Double>("prix_offre"));
        description_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("description_offre"));
        //localisation_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("localisation_offre"));  
        nom_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("nom_offre"));
        image_offre.setCellValueFactory(new PropertyValueFactory<Offres,String>("image_offre"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<Offres,Integer>("type_cat"));
        type_offre.setCellValueFactory(new PropertyValueFactory<Offres,Enum>("type_offre"));
        
           try {
            listec=sc.Recherche(nom);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        offrelist.addAll(listec);
        table.setItems(offrelist);
    }

    @FXML
    private void Stats(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLStats.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    

    @FXML
    private void imprimer(ActionEvent event)throws SQLException, DocumentException, IOException {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

               try {
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\leila\\Desktop\\pdf\\Offre.pdf"));
       document.open();
       
//       Image img = Image.getInstance("C:\\Users\\21655\\Downloads\\badelBd\\projet de amine 2\\projet de amine 2\\projet de amine\\projet de amine\\PidevJava\\src\\gui\\dronify.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_LEFT);
//       document.open();
//       document.add(img);
    
       //document.add(new Paragraph("Liste des paiements"));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Liste des offres ", font);
       p.setAlignment(Element.ALIGN_CENTER);
       document.add(p);
       document.add(new Paragraph(" "));
       document.add(new Paragraph(" "));
 

       PdfPTable tabpdf = new PdfPTable(9);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("ID Offre", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("ID User", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Localisation", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Image", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
        cell = new PdfPCell(new Phrase("type offre", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
        cell = new PdfPCell(new Phrase("categorie", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       
       
       
       
       PreparedStatement pst = connexion.prepareStatement(requete2);
       ResultSet rs = pst.executeQuery();
       while (rs.next()) {
           cell = new PdfPCell(new Phrase(rs.getString("id_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("id_user"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("prix_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("description_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
          
           cell = new PdfPCell(new Phrase(rs.getString("localisation_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
     
           cell = new PdfPCell(new Phrase(rs.getString("nom_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("image_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
            cell = new PdfPCell(new Phrase(rs.getString("type_offre"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
            cell = new PdfPCell(new Phrase(rs.getString("type_cat"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           
       }
          document.add(tabpdf);
          JOptionPane.showMessageDialog(null, "Success !");
          document.close();
          Desktop.getDesktop().open(new File("C:\\Users\\leila\\Desktop\\pdf\\Offre.pdf"));
//          notif("Succes","Votre document a été enregistré au format PDF !");
       }
 
        catch (SQLException | DocumentException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }
    }
    
}
