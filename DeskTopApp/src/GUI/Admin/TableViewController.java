package GUI.Admin;

import Entity.admin;
import Entity.user;
import Utils.MyDB;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.AdminService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ghof
 */
public class TableViewController implements Initializable {
    
    @FXML
    private Button btnOverview,btnOrders,btnCustomers,btnMenus,btnPackages,btnSettings,btnSignout;   
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private TableView<user> tftree;
    @FXML
    private TableColumn<user, String> colnom,colprenom,collogin,colpass,colemail,coladresse,coltel,colsexe,colrole;
    @FXML
    private TableColumn<user, Date> coldate;
    @FXML
    private ImageView tfimg;
    @FXML
    private Label tfnom;
 /*********************************************************/   
    public user u;
    public admin a;
    public List<user> users;
    private UserService us=new UserService();
    private AdminService as=new AdminService();
 /***************************************/
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField search;
    @FXML
    private JFXButton exportToxl;
    @FXML
    private PieChart piechart;
    @FXML
    private PieChart piechart1;
    @FXML
    private Label tfnom1;

/************stat**************************/
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    ObservableList<PieChart.Data>info=FXCollections.observableArrayList();
    
    ObservableList<PieChart.Data>info2=FXCollections.observableArrayList();
    int n;
/*********************************************/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
        cnx = MyDB.getInstance().getConx();
        stat();
        stat2();
  }
/*********************************************************************************/
    private void refreshTable(){
            a=as.getAdminByLogin("admin");
            String path=a.getPhoto_admin();
            Image img=new Image(getClass().getResourceAsStream(path));
            tfimg.setImage(img);
            tfnom.setText(a.getNom_admin()+" "+a.getPrenom_admin());
        
        ObservableList<user> userlist = FXCollections.observableArrayList();
  
        colnom.setCellValueFactory(new PropertyValueFactory<user,String>("nom_user"));
        colprenom.setCellValueFactory(new PropertyValueFactory<user,String>("prenom_user"));
        collogin.setCellValueFactory(new PropertyValueFactory<user,String>("login"));
        colpass.setCellValueFactory(new PropertyValueFactory<user,String>("mdp"));
        coldate.setCellValueFactory(new PropertyValueFactory<user,Date>("date_nais_user"));
        colemail.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
        coladresse.setCellValueFactory(new PropertyValueFactory<user,String>("adresse"));
        coltel.setCellValueFactory(new PropertyValueFactory<user,String>("tel"));
        colsexe.setCellValueFactory(new PropertyValueFactory<user,String>("sexe"));
        colrole.setCellValueFactory(new PropertyValueFactory<user,String>("role"));
        
        try {
            users=us.afficher();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        userlist.addAll(users);
        tftree.setItems(userlist);
      
    /*************Search Bar and TableView filter result as you search***************************/    
        FilteredList<user> filteredData=new FilteredList<>(userlist,b->true);
        search.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(t -> { 
                if(newValue.isEmpty() || newValue==null){
                    return true;
                }
            String searchkeyword=newValue.toLowerCase();
            
            if(t.getNom_user().toLowerCase().indexOf(searchkeyword) > -1){
                return true; //found a match in t (user)
            }else if (t.getPrenom_user().toLowerCase().indexOf(searchkeyword) > -1) {
                 return true; //found a match in t (user)
            }else if (t.getLogin().toLowerCase().indexOf(searchkeyword) > -1) {
                 return true; //found a match in t (user)
            }else if (t.getEmail().toLowerCase().indexOf(searchkeyword) > -1) {
                 return true; //found a match in t (user)
            }else if (t.getRoles().toString().toLowerCase().indexOf(searchkeyword) > -1) {
                 return true; //found a match in t (user)
            }else if (t.getTel().toLowerCase().indexOf(searchkeyword) > -1) {
                 return true; //found a match in t (user)
            }else if (t.getSexe().toString().toLowerCase().indexOf(searchkeyword) > -1) {
                 return true; //found a match in t (user)
            }else
                return false;//no match found
            });
        });
        
        SortedList<user> sortedData=new SortedList<>(filteredData);
             //bind sorted result with table view
        sortedData.comparatorProperty().bind(tftree.comparatorProperty());
              //apply filtered and sorted data to the table
        tftree.setItems(sortedData);

    }

    @FXML
    private void delete_clicked(ActionEvent event) throws SQLException {
        user user_selected=tftree.getSelectionModel().getSelectedItem();
        int id=user_selected.getId_user();
        us.supprimer(id);
        
        refreshTable();
    }
    @FXML
    private void users_clicked(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("TableView.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void signOut_clicked(ActionEvent event) throws IOException, SQLException {
        root =FXMLLoader.load(getClass().getResource("/GUI/User/Login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

/*************************************************************************/
        @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void Reclamation_view(ActionEvent event) throws IOException {
           
        root = FXMLLoader.load(getClass().getResource("/GUI/reclamation/FXMLReclamationAdmin.fxml"));          
       
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.getIcons().add(new Image("/GUI/images/logo.png"));
        stage.setTitle("Reclamation Admin Page");   
        stage.setScene(scene);
        stage.show();
    }
/**********************API Exporter to XL***********************************************************************/
    @FXML
    private void exporterToXlxs(MouseEvent event) {
        Connection cnx = MyDB.getInstance().getConx();
                try {
            String query = "select * from users";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Liste des utilisateurs");

            HSSFRow header = sheet.createRow(0);

            header.createCell(0).setCellValue("id_user");
            header.createCell(1).setCellValue("nom_user");
            header.createCell(2).setCellValue("prenom_user");
            header.createCell(3).setCellValue("login");
            header.createCell(4).setCellValue("date_nais_user");
            header.createCell(5).setCellValue("email");
            header.createCell(6).setCellValue("adresse");
            header.createCell(7).setCellValue("tel");
            header.createCell(8).setCellValue("sexe");
            header.createCell(9).setCellValue("role");

            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString(1));
                row.createCell(1).setCellValue(rs.getString(2));
                row.createCell(2).setCellValue(rs.getString(3));
                row.createCell(3).setCellValue(rs.getString(4));
                row.createCell(4).setCellValue(rs.getString(6));
                row.createCell(5).setCellValue(rs.getString(7));
                row.createCell(6).setCellValue(rs.getString(8));
                row.createCell(7).setCellValue(rs.getString(9));
                row.createCell(8).setCellValue(rs.getString(10));
                row.createCell(9).setCellValue(rs.getString(11));
                index++;

            }

            FileOutputStream fileOut = new FileOutputStream("users.xls");

            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("La table est bien été importé en xls ❗ ");
            alert.setContentText("😛😛😛😛😛😛😛");

            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/***********************Metiers************************************************************************/  
/********************stat selon Role**************************************************************************/
    private void stat() {
          try{
           
           String query ="select COUNT(*),`role` from users GROUP BY role;";

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               info.add(new PieChart.Data(rs.getString("role"),rs.getInt("COUNT(*)")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }        
    
       // piechart.setLegendSide(Side.LEFT);
        piechart.setData(info);
        
        info.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty()
                        )
                )
        );   
}
/*****************stat selon Sexe****************************************************************************/
    private void stat2() {
          try{
           
           String query ="select COUNT(*),`sexe` from users GROUP BY sexe;";

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               info2.add(new PieChart.Data(rs.getString("sexe"),rs.getInt("COUNT(*)")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }        
    
       // piechart.setLegendSide(Side.LEFT);
        piechart1.setData(info2);
        
        info2.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty()
                        )
                )
        );   
}  

    @FXML
    private void EventPage(ActionEvent event) throws IOException {
         root =FXMLLoader.load(getClass().getResource("/GUI/event/welcome.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void feedback(ActionEvent event) throws IOException {
                Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/feedback/FXMLFeedbackAdmin.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }
}
