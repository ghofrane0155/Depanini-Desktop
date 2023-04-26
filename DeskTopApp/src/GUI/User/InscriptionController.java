package GUI.User;

import Entity.user;
import Entity.Enum.Sexe;
import Entity.Enum.TypeR;
import static services.metiersUser.PasswordHashing.hashPassword;
import services.metiersUser.Verif;
import static services.metiersUser.Verif.showAlert;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXCheckBox;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXButton bt_inscri;
    @FXML
    private Label titre;
    @FXML
    private JFXPasswordField tfmdp;
    @FXML
    private JFXTextField tfmail,tfnom,tfprenom,tfadresse,tflogin,tftel;
    @FXML
    private JFXDatePicker tfdate;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private Button bt_importer;
    @FXML
    private JFXComboBox<String> tftype;  
    @FXML
    private JFXRadioButton btnfemme,btnhomme;
    @FXML
    private ImageView img_img;
    @FXML
    private Label titre1;
/****************************/
    user u = null;
    UserService us = new UserService();
    ObservableList<String> typeList = FXCollections.observableArrayList("Freelancer", "Client");
   
    Sexe gender=null;
    TypeR type=null;
    LocalDate localdate=null;
/************************************/
   FileChooser fc; 
   File selectedFile;
   String photo="/GUI/images/user1.png";
    FileInputStream fis;
/******************************/   
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private JFXCheckBox show;
    @FXML
    private JFXTextField passwordfield;

/*********************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tftype.setItems(typeList);
      //  tfdate.setConverter(new StringConverter<LocalDate>());

        tfmdp.setVisible(true);
        passwordfield.setVisible(false);
       
    }  
/***************radio button type enum SEXE********************************/
    @FXML
    private void getsexe(ActionEvent event) {
        if(btnfemme.isSelected())
            gender=Sexe.valueOf(btnfemme.getText());
        else if(btnhomme.isSelected())
            gender=Sexe.valueOf(btnhomme.getText());      
    }
/***************DatePicker**************************************************/
        @FXML
    private void getDate(ActionEvent event) {
        localdate=tfdate.getValue();
    }
/**************Importer img**************************************************/
    @FXML
    private void on_click_import(ActionEvent event) throws IOException {
        fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new ExtensionFilter("IMG Files", "*.png","*.jpg","*.gif"));
         
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
/**************************************************************************/
    @FXML
    private void on_click_inscri(ActionEvent event) throws FileNotFoundException, SQLException {

        
        if(show.isSelected())
                tfmdp.setText(passwordfield.getText());

       if(valide1()) 
       {String nom=tfnom.getText();
        String prenom=tfprenom.getText();
        String adr=tfadresse.getText();
        String login=tflogin.getText();

            
            String mdp=tfmdp.getText();
            mdp=hashPassword(mdp);
             
           mdp=hashPassword(tfmdp.getText());
            
        String mail=tfmail.getText();
        String tel=tftel.getText();
        
        Date date=null;
        date=Date.valueOf(localdate);     
        type=TypeR.valueOf(tftype.getValue());
               

        user u=new user(nom, prenom, login, mdp,date,mail, adr, tel,gender,type,photo);
        us.ajouter(u);
        
        showAlert(Alert.AlertType.INFORMATION, "Success", "Inscription a été effectué avec succès!", "");
           
           try {
               root =FXMLLoader.load(getClass().getResource("Login.fxml"));
               stage=(Stage)((Node)event.getSource()).getScene().getWindow();
               Scene scene =new Scene(root);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               System.out.println(ex.getMessage());
           }
           
       }
    }
/****************************************************************************************************************/
private boolean valide1() {
    if (tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfadresse.getText().isEmpty() || tflogin.getText().isEmpty()
            || tfmdp.getText().isEmpty() || tfmail.getText().isEmpty() || tftel.getText().isEmpty() || tfdate.getValue() == null) {
                  
        showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner tous les champs !");        
        return false;}
    else{
        if(!Verif.validateNom(tfnom.getText())){
            tfnom.requestFocus();
            return false;
        }
        
        if(!Verif.validatePrenom(tfprenom.getText())){
                tfprenom.requestFocus();
                return false;
            }
    /***************telephone 8 chiffres***************************************************************************************/              
            if(!Verif.validateTel(tftel.getText())){
                tftel.requestFocus();
                return false;
            }
    /************************DATE***************************************************************************************/       
        if(!Verif.validateDate(tfdate.getValue())){
                tfdate.requestFocus();
                return false;
            }
    /*************login start with an alphabet + alphabets, numbers or an underscore (length=6-30)*********************************************************************/      
            if(!Verif.validateLogin(tflogin.getText())){
                tflogin.requestFocus();
                return false;
            }
    /*************password minimum 8*********************************************************************/      
        if(!Verif.validatePass(tfmdp.getText())){
            tfmdp.requestFocus();
            return false;
            }
    /*******************mail (randomString)@(randomString2).(2-3 characters) ********************************************************/
            if(!Verif.validateMail(tfmail.getText())){
                tfmail.requestFocus();
                return false;
            }
        
         if (us.existe("email", tfmail.getText())>0) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Email déjà existe, veuillez choisir un autre");
                tfmail.requestFocus();
                return false;
            }
    /***********************login dans table user***************************************************************************/
         if (us.existe("login", tflogin.getText())>0) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Login déjà existe, veuillez choisir un autre");
                tflogin.requestFocus();
                return false;
            }
    }
     return true;  
}

    @FXML
    private void changeVisibility(ActionEvent event) {
        if(show.isSelected()){
            passwordfield.setText(tfmdp.getText());
            passwordfield.setVisible(true);
            tfmdp.setVisible(false);
            return;
        }
        tfmdp.setVisible(true);
        passwordfield.setVisible(false);
        tfmdp.setText(passwordfield.getText());
   
    }
/**************************************************************************************************/
    
}
