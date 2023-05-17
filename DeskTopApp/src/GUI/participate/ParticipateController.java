/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.participate;

import GUI.participate.DetailEventController;
import com.jfoenix.controls.JFXButton;
import Entity.user;
import com.google.zxing.WriterException;
import Entity.Event;
import Entity.Ticket;
import GUI.User.UserProfileController;
import services.metierEvent.qrcode;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.EventService;
import services.TicketService;
import Utils.MyDB;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import static services.UserService.search_user;
import services.metiersUser.SessionUser;
import static services.metiersUser.Verif.showAlert;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class ParticipateController implements Initializable {

    @FXML
    private TableView<Event> eventstable;
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
    private TableColumn<Event, String> coldesc;
    @FXML
    private Button details;
    @FXML
    private Pane eventImage;
    @FXML
    private ImageView imageEvent;
    @FXML
    private Label prix_tot;
    @FXML
    private Button panier;

    public PreparedStatement st;
    Connection cnx = MyDB.getInstance().getConx();
    /**
     *
     * ***********************************************
     */
    Statement stm;
    private Image image;
//    Connection cnx=MyDB.getInstance().getConx();
    public Event e;
    Event e1 = null;
    public List<Event> Events;
    EventService es = new EventService();
    TicketService ts = new TicketService();

    /**
     * **********************************************
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton events;
    @FXML
    private Label Eventnom;
    @FXML
    private Label prixEvent;
    @FXML
    private Button clear;
    @FXML
    private Spinner<Integer> spinner1;

    @FXML
    private AnchorPane parentContainer;
    @FXML
    private AnchorPane rootPane;

    //////////////////////////pour recuperer les info de cette interface
    private StringProperty EventName = new SimpleStringProperty("");
    private StringProperty prixTotal = new SimpleStringProperty("");
    private IntegerProperty quantite = new SimpleIntegerProperty(0);
    @FXML
    private Label tfnom_prenom;
    @FXML
    private ImageView tfimg;
    @FXML
    private Label tfrole;
    @FXML
    private ImageView btn_home;
    @FXML
    private AnchorPane notif;
    @FXML
    private Button acc;
    @FXML
    private Button reclamation;
    @FXML
    private Button produits;
    @FXML
    private Button Event;
    @FXML
    private Button bar11;
    @FXML
    private Button bar22;
    @FXML
    private TextField search;
    @FXML
    private Button ring;
 user utilisateur=null;
    @FXML
    private Button feedback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        events_clicked();
//        events_clicked();
//        String path=SessionUser.getPhoto_user();
//        Image img=new Image(getClass().getResourceAsStream(path));
//        tfimg.setImage(img);
//        
//        tfrole.setText(SessionUser.getRole().toString());
//        tfnom_prenom.setText(SessionUser.getNom_user()+" "+SessionUser.getPrenom_user());
    }
   

    //////affichage de nom event prix et image et le nombre lim sur le spinner
    private SpinnerValueFactory<Integer> PrixTicket;

    @FXML
    public void selectEventShow() throws SQLException {
        e1 = eventstable.getSelectionModel().getSelectedItem();
        //int qte_par_nomEvent = 0;
        int num = eventstable.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        String uri = "file:" + e1.getImageEvent();
        image = new Image(uri, 139, 93, false, true);
        imageEvent.setImage(image);

        Eventnom.setText(e1.getNomEvent());

        prixEvent.setText(String.valueOf(e1.getPrixEvent()));

        PrixTicket = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, e1.getNombreLimEvent(), 0);

        spinner1.setValueFactory(PrixTicket);

    }

    ///// l'apparition de prix total
    private int total = 0;
    private int q;

    @FXML
    public void getSpinnerValue() {

        q = spinner1.getValue();
        int pr = Integer.parseInt(prixEvent.getText());
        total = (q * pr);
        prix_tot.setText(String.valueOf(total) );

    }

    ///////////////Ticket
    @FXML
    private void creerTicket(ActionEvent event) throws SQLException, WriterException, IOException {
        e1 = eventstable.getSelectionModel().getSelectedItem();
        if (Eventnom.getText().isEmpty() || prixEvent.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("il faut , tout d'abords , choisir un évenement !! ");
            alert.showAndWait();
        } else if (spinner1.getValue() < (e1.getNombreLimEvent() - spinner1.getValue())) {
            String eventName = Eventnom.getText();
            String sql = "SELECT IDEvent FROM events WHERE NomEvent = ?";
            PreparedStatement eventStatement = cnx.prepareStatement(sql);
            eventStatement.setString(1, eventName);
            ResultSet eventResult = eventStatement.executeQuery();
            int eventid = 0;
            if (eventResult.next()) {
                eventid = eventResult.getInt("IDEvent");
            };
            //////////////////////////////////////////////////////////////


//        PreparedStatement nbreStatement = cnx.prepareStatement("SELECT sum(quantite) as q FROM tickets WHERE IDEvent = ");
//        System.out.println(n);
//        userStatement.setInt(1, n);
//        ResultSet nbreResult = nbreStatement.executeQuery();
//        System.out.println(nbreResult);
//        int nbre = 0;
//        if (userResult.next()) {
//            nbre = userResult.getInt("q"); 
//        }
            ////////////////////////////////////////////

//            String outputQR = "C:\\Users\\noure\\OneDrive\\Bureau\\desktopapp2\\src\\GUI\\images\\qr.png";
//            String text = "Login :" + SessionUser.getNom_user()+" "+SessionUser.getPrenom_user() + "\n" + "Nom de l'événement :" + eventName + "\n" + "Quantité de ticket acheté :" + Integer.toString(spinner1.getValue()) + "\n" + "Prix Total" + prix_tot.getText();
//
//
//            
//            qrcode.generateQRcode(text, 1250, 1250, outputQR);

//            userResult.close();
//            userStatement.close();
//            eventResult.close();
//            eventStatement.close();
            int prix=Integer.valueOf(prix_tot.getText());
           
            int Quantity = spinner1.getValue();
//        if (spinner1.getValue()<(e1.getNombreLimEvent()-nbre-spinner1.getValue())){
            Ticket t1=new Ticket(eventid, SessionUser.getId_user(), Quantity, prix,  SessionUser.getLogin());
            //Ticket t = new Ticket(eventid, userId, Quantity,loginid.getText());
            ts.ajouter(t1);
           

            Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Information");
                info.setHeaderText(null);
                info.setContentText("votre Ticket existe dans le panier!");
                Optional<ButtonType> result2 = info.showAndWait();
            
            FXMLLoader loader =new FXMLLoader(getClass().getResource("panierTicket.fxml"));
            root = loader.load();
            PanierTicketController panierTicketController =loader.getController();

//            panierTicketController.displayEventName(EventName1);            
           // makeFadeOut();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("this event is full ");
            alert.showAndWait();
        }
    }
    
    public String getEventNameLabel() {
    return Eventnom.getText();
}


    

    @FXML
    private void depanini_clicked(MouseEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePageFreelancer.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void prifile_clicked(ActionEvent event) {
    }

    @FXML
    private void settings_clicked(ActionEvent event) {
    }

    @FXML
    private void details(ActionEvent event) {
        e1 = eventstable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Gui/participate/DetailEvent.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        DetailEventController d = loader.getController();
        d.setTextFields(e1);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

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

    @FXML
    private void events_clicked() {
        ObservableList<Event> EventList = FXCollections.observableArrayList();

        colnom.setCellValueFactory(new PropertyValueFactory<Event, String>("NomEvent"));
        colorg.setCellValueFactory(new PropertyValueFactory<Event, String>("OrganisateurEvent"));
        coldeb.setCellValueFactory(new PropertyValueFactory<Event, String>("DateDabEvent"));
        colfin.setCellValueFactory(new PropertyValueFactory<Event, String>("DateFinEvent"));
        collieu.setCellValueFactory(new PropertyValueFactory<Event, String>("LieuEvent"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Event, String>("DescriptionEvent"));

        try {
            Events = es.getAll();
            System.out.println(Events);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        EventList.addAll(Events);
        eventstable.setItems(EventList);

    }

    @FXML
    private void ClearTicket(ActionEvent event) {
        Eventnom.setText("");
        prixEvent.setText("");
        imageEvent.setImage(null);
        prix_tot.setText("");
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePage.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void notif_clicked(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/participate/panierTicket.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void home(MouseEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePage.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }


    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/reclamation/FXMLReclamation.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void produit(ActionEvent event) {
    }

    @FXML
    private void EventsList(ActionEvent event) {
    }

    @FXML
    private void panier(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/participate/panierTicket.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void run1(MouseEvent event) {
    }

    @FXML
    private void run2(MouseEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void search(MouseEvent event) throws IOException {
        if(search_user("nom_user",search.getText())!=null)
            utilisateur=search_user("nom_user",search.getText());
        else
            if(search_user("prenom_user",search.getText())!=null)
                utilisateur=search_user("prenom_user",search.getText());
            else
               if(search_user("login",search.getText())!=null)
                  utilisateur=search_user("login",search.getText());
               else{
               }
        
        if(utilisateur==null)
           showAlert(Alert.AlertType.ERROR, "Données erronés", "", "Utilisateur n'existe pas ");  
        else{

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/User/userProfile.fxml"));
            root =loader.load();
            
            UserProfileController userprofile=loader.getController();
            userprofile.displayInfo(utilisateur); 
      
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene =new Scene(root);
                Stage stage = new Stage();
            stage.setScene(scene);   
            stage.show();  
        }
    }

    @FXML
    private void notificationEvent(ActionEvent event) {
    }

    @FXML
    private void notification(MouseEvent event) {
    }

    @FXML
    private void feedback(ActionEvent event) throws IOException {
         Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/feedback/FXMLFeedback.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

}
