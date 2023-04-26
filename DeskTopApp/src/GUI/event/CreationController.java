/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.event;

import com.jfoenix.controls.JFXTextArea;
import Entity.Event;
import Entity.admin;
import Entity.user;
import static java.awt.SystemColor.text;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import static java.nio.channels.AsynchronousFileChannel.open;
import static java.nio.channels.AsynchronousFileChannel.open;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static org.omg.CORBA.UShortSeqHelper.type;
import services.AdminService;
import services.EventService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class CreationController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private VBox side_ankerpane;
    @FXML
    private Button btn_creer;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_aff;
   
    @FXML
    private TextArea OrgaEvent;
    @FXML
    private TextArea NbrLimEvent;
    private DatePicker DateDebEvent;
    @FXML
    private TextArea DescriptEvent;
/********************************************************/
    SimpleDateFormat sp=new SimpleDateFormat("yyyy/MM/dd");
 /****************************************************/
    FileChooser fc; 
   File selectedFile;
    FileInputStream fis;
   /******************************************************/
   LocalDate localdate=null;
   LocalDate localdate1=null;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Event e = null;
    EventService es=new EventService();
    /*****************************************************/
    //Date systemDate = new Date();
    //LocalDate systemDate = LocalDate.now();
    /********************************************************/
    @FXML
    private JFXTextArea NomEventid;
    private DatePicker DateDebutEventid;
    private DatePicker DateFinEventid;
    @FXML
    private JFXTextArea LieuEventid;
    @FXML
    private Button btnajout;
    @FXML
    private DatePicker dateid;
    @FXML
    private DatePicker dateid1;
    @FXML
    private JFXTextArea prixEvent;
    @FXML
    private Button bt_importer;
    @FXML
    private ImageView img_img;
    @FXML
    private Button btn_dash;
    
    
    String photo="/GUI/images/user1.png";
    @FXML
    private Button btn_ticket;
    @FXML
    private Button back;
    @FXML
    private ImageView tfimg;
    @FXML
    private Text tfnom;
    
    /*********************************************************/   
    public user u;
    public admin a;
    public List<user> users;
    private UserService us=new UserService();
    private AdminService as=new AdminService();
 /***************************************/
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         a = as.getAdminByLogin("admin");
        String path = a.getPhoto_admin();
        Image img = new Image(getClass().getResourceAsStream(path));
        tfimg.setImage(img);
        tfnom.setText(a.getNom_admin() + " " + a.getPrenom_admin());
      
        
    }  

   

    


    @FXML
    private void getdate(ActionEvent event) {
        localdate=dateid.getValue();
            System.out.println(localdate);
    }

  

    @FXML
    private void getdate1(ActionEvent event) {
        localdate1=dateid1.getValue();
        System.out.println(localdate1);
    }

   
    @FXML
    private void ChangeScene1(MouseEvent event) throws IOException {
         Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/creation.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
        
    }

    /********************************************************************/
    @FXML
    private void on_click_import(ActionEvent event) throws IOException {
        fc = new FileChooser();
        fc.setTitle("open Image File");
       
        
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("IMG Files", "*.png","*.jpg","*.gif"));
         
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            BufferedImage bufferdimg= ImageIO.read(selectedFile);
            Image image =SwingFXUtils.toFXImage(bufferdimg, null);
            img_img.setImage(image);
            photo="/GUI/images/"+selectedFile.getName();
            
        } else {
            System.out.println("file is not valid");
        }
    }

    
    /**********************************************************************/
    @FXML
    private void Save(ActionEvent event) throws SQLException {
        if(verif()){
//                String ID=id_event.getText();
               
                String Nom= NomEventid.getText();
                String NombreLim=NbrLimEvent.getText();
                String Lieu=LieuEventid.getText();
                String Description=DescriptEvent.getText();
                String Organisateur=OrgaEvent.getText();
                String prixEven=prixEvent.getText();
                
                Date date=null;
                date=Date.valueOf(localdate);
            
                Date date1=null;
                date1=Date.valueOf(localdate1);
                          
                String DateDebut= String.valueOf(date.toString());
                String DateFin= String.valueOf(date1.toString());
                
               // int id_ev=Integer.parseInt(ID);
                int nbrl = Integer.parseInt(NombreLim);
                int pr=Integer.parseInt(prixEven);
                String photo=selectedFile.getAbsolutePath();
                photo= photo.replace("\\", "\\\\");
               
                Event e=new Event( Nom, nbrl, Lieu, Organisateur, DateDebut, DateFin, Description,photo,pr);
                System.out.println(e);   
                es.ajouter(e); 
                Alert alert=new Alert (Alert. AlertType.ERROR);
                            alert.setHeaderText (null);
                            alert.setContentText ("ajouté avec succees");
                            alert.showAndWait (); 
                    }
                
    }

    



   

    @FXML
    private void affichage(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/affichage.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    /**************************************CONTROLE DE SAISIE*****************************************************/
    private boolean verif() {
        /*************************EMPTY*******************************/
         if ( NomEventid.getText().isEmpty() || NbrLimEvent.getText().isEmpty() || LieuEventid.getText().isEmpty()
            || DescriptEvent.getText().isEmpty() || OrgaEvent.getText().isEmpty()||dateid.getValue() == null||dateid1.getValue() == null) {
                  
        showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner tous les champs !");        
        return false;}
         else{
    /*****************************champs text*********************************/         
               
               int s=Integer.parseInt(NbrLimEvent.getText());
                if(s>100 && s<1){
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "nombre invalid ! ");
                    NbrLimEvent.requestFocus();
                    return false;}
                
 /************************Date*********************************/
               if (dateid.getValue().toString().length() != 10 || !Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", dateid.getValue().toString()) ) {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner votre date de naissance.\nExemple : 2001-06-23");
                    dateid.requestFocus();
                    return false;}
               
               
               if (dateid1.getValue().toString().length() != 10 || !Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", dateid1.getValue().toString()) ) {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner votre date de naissance.\nExemple : 2001-06-23");
                    dateid1.requestFocus();
                    return false;}
               
               if ((dateid.getValue().getYear() > dateid1.getValue().getYear())&&(dateid1.getValue().getMonthValue()> dateid.getValue().getMonthValue()) &&(dateid.getValue().getDayOfMonth()> dateid1.getValue().getDayOfMonth()) ) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "date debut > date fin !!");
                dateid.requestFocus();
                return false;}
               
               
               
            
        return true;
        
    }
    }
/**********************************ALERT*******************************/
    private void showAlert(Alert.AlertType alertType, String données_erronés, String verifier_les_données, String veuillez_bien_renseigner_tous_les_champs_) {
        Alert alert = new Alert(alertType);
        alert.setTitle(données_erronés);
        alert.setHeaderText(verifier_les_données);
        alert.setContentText(veuillez_bien_renseigner_tous_les_champs_);
        alert.showAndWait();
    }

    @FXML
    private void dash(ActionEvent event) throws IOException {
         Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/welcome.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void creation(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/creation.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void modification(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/modification.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void Tickets(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/Ticket/affichageTicket.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void Saveeee(MouseEvent event) {
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/Admin/TableView.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    

    
    
}