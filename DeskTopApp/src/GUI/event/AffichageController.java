/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.event;

import com.jfoenix.controls.JFXButton;
import Entity.Event;
import Entity.admin;
import Entity.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;
import services.EventService;
import services.ParticipantService;
import Utils.MyDB;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import services.AdminService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class AffichageController implements Initializable {

    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_creer2;
    @FXML
    private TableColumn<Event, String> colnom;
    @FXML
    private TableColumn<Event, String> colorg;
    @FXML
    private TableColumn<Event, String> coldeb;
    @FXML
    private TableColumn<Event, String> colfin;
    @FXML
    private TableColumn<Event, String> collieu;
    @FXML
    private TableColumn<Event, Integer> colnbr;
    @FXML
    private TableColumn<Event, String> coldesc;
     @FXML
    private TableColumn<Event,Integer> prix;
    @FXML
    private TableView<Event> eventstable;

    /**
     * ******************************************************
     */
  Connection cnx = MyDB.getInstance().getConx();
    public Event e;
    Event e1 = null;
    public List<Event> Events;
    private EventService es = new EventService();
    /**
     * ************************************
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_aff2;
    @FXML
    private Button delid;
    /**
     * ***********************************
     */
    private ObservableList<Event> EventList = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private Button btn_dash;
    @FXML
    private Button btn_dash1;
    @FXML
    private Button back;
    @FXML
    private ImageView tfimg;
    @FXML
    private Text tfnom;

    
    //////////////
    ///////////
    ////////
    
    
    /*********************************************************/   
    public user u;
    public admin a;
    public List<user> users;
    private UserService us=new UserService();
    private AdminService as=new AdminService();
 /***************************************/
    /**
     * Initializes the controller class.
     *
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
        refreshTable();

    }

    @FXML
    private void getAdd(MouseEvent event) {
    }
    //////////////////recherche filtre

    ////////////////tableView
    @FXML
    private void refreshTable() {
        ObservableList<Event> EventList = FXCollections.observableArrayList();

       
        colnom.setCellValueFactory(new PropertyValueFactory<Event, String>("NomEvent"));
        colorg.setCellValueFactory(new PropertyValueFactory<Event, String>("OrganisateurEvent"));
        coldeb.setCellValueFactory(new PropertyValueFactory<Event, String>("DateDabEvent"));
        colfin.setCellValueFactory(new PropertyValueFactory<Event, String>("DateFinEvent"));
        collieu.setCellValueFactory(new PropertyValueFactory<Event, String>("LieuEvent"));
        colnbr.setCellValueFactory(new PropertyValueFactory<Event, Integer>("NombreLimEvent"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Event, String>("DescriptionEvent"));
        prix.setCellValueFactory(new PropertyValueFactory<Event, Integer>("prixEvent"));

        try {
            Events = es.getAll();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        EventList.addAll(Events);
        eventstable.setItems(EventList);
        
        /**********************************search filtered**********************************/
         FilteredList<Event> filter = new FilteredList<>(EventList, e -> true);
        
        search.textProperty().addListener(( observable,  oldValue, newValue) -> {
            
            filter.setPredicate( (E) -> {

                if (newValue.isEmpty() || newValue == null) {
                    refreshTable();
                    return true;
                }
                String keySearch = newValue.toLowerCase();

                if (E.getNomEvent().toLowerCase().indexOf(keySearch) > -1) {
                     
                    return true;
                } else if (E.getDateFinEvent().toLowerCase().indexOf(keySearch) > -1) {
                     
                    return true;
                } else if (E.getLieuEvent().toLowerCase().indexOf(keySearch) > -1) {
                     
                    return true;
                } else if (E.getOrganisateurEvent().toLowerCase().indexOf(keySearch) > -1) {
                     
                    return true;
                } else if (E.getDescriptionEvent().toLowerCase().indexOf(keySearch) > -1) {
                    return true;
                }else{

                return false;}
                
            });
        });
       SortedList<Event> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(eventstable.comparatorProperty());
        eventstable.setItems(sortData);

    }

    @FXML
    private void ChangeScene1(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/creation.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void ChangeScene2(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/modification.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    private void ChangeScene3(ActionEvent event) throws IOException {

        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/suppression.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    private void ChangeScene4(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/Ticket/affichageTicket.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }


   
    public ObservableList<Event> getUserList() {
        cnx = MyDB.getInstance().getConx();

        ObservableList<Event> EventList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * FROM  events ";
            PreparedStatement smt = cnx.prepareStatement(query2);

            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Event ev = new Event(rs.getString("NomEvent"), rs.getInt("NombreLimEvent"), rs.getString("LieuEvent"), rs.getString("OrganisateurEvent"), rs.getString("DateDabEvent"), rs.getString("DateFinEvent"), rs.getString("DescriptionEvent"));
                EventList.add(ev);
            }
            System.out.println(EventList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return EventList;

    }

    public void showRec() {

        ObservableList<Event> list = getUserList();
        //colid.setCellValueFactory(new PropertyValueFactory<>("IDEvent"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("NomEvent"));
        colorg.setCellValueFactory(new PropertyValueFactory<>("OrganisateurEvent"));
        coldeb.setCellValueFactory(new PropertyValueFactory<>("DateDabEvent"));
        colfin.setCellValueFactory(new PropertyValueFactory<>("DateFinEvent"));
        collieu.setCellValueFactory(new PropertyValueFactory<>("LieuEvent"));
        colnbr.setCellValueFactory(new PropertyValueFactory<>("NombreLimEvent"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvent"));

        eventstable.setItems(list);

    }

    @FXML
    private void delete(ActionEvent event) {
        e1 = eventstable.getSelectionModel().getSelectedItem();
        System.out.println(e1);
        try {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette ligne ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                 /////////////////// requette recuperation de l@mail 
        
        String req = "select u.email from user u join tickets t on u.id_user=t.id_user join events e on t.IDEvent=e.IDEvent where e.IDEvent=?";
                System.out.println(req);
        PreparedStatement eventStatement = cnx.prepareStatement(req);
        eventStatement.setInt(1, e1.getIDEvent());
        try (ResultSet resultSet = eventStatement.executeQuery()) {
            
            List<String> emails = new ArrayList<>();
                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    emails.add(email);
                    System.out.println(email);
                }
               
            // Send email to each participant
                ParticipantService ps = new ParticipantService();
//                for (String email : emails) {
//                    ps.evoyerEmailToParticipantWhenEventDeleted(email, e1);
//                }
        }
        
        catch (SQLException e1) {
        // handle the exception
        }
                es.delete(e1.getIDEvent());
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Information");
                info.setHeaderText(null);
                info.setContentText("L'évenement a été supprimée avec succès.");
                Optional<ButtonType> result2 = info.showAndWait();
                if (result.get() == ButtonType.OK) {
                    getUserList();
                    
                } else {
                    /////////
                }

            } else {
                System.out.println("suppression annuler");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

  

    @FXML
    private void dash(ActionEvent event) throws IOException {
    Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/welcome.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void tickets(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/Ticket/affichageTicket.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }


    @FXML
    private void recherche(KeyEvent event) {
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
