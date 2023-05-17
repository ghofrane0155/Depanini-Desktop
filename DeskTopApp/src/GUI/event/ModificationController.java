/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.event;

import java.text.SimpleDateFormat;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import Entity.Event;
import Entity.admin;
import Entity.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.EventService;
import services.ParticipantService;
import Utils.MyDB;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javax.mail.internet.AddressException;
import services.AdminService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class ModificationController implements Initializable {

    @FXML
    private Button btn_creer2;
    @FXML
    private Button btn_aff;
    @FXML
    private Button btn_modif;

    public PreparedStatement st;
//    public ResultSet result;

    @FXML
    private JFXTextField txtorg;
    @FXML
    private JFXTextField intnbr;
    @FXML
    private JFXTextField txtlieu;
    @FXML
    private JFXTextField txtdesc;
    @FXML
    private JFXDatePicker datdeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton updateid;
    @FXML
    private Button btnchercher2;
    @FXML
    private TextField searchid;

    LocalDate localdate = null;
    LocalDate localdate1 = null;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
//    Event e = null;

    Event e1 = new Event();
    EventService es = new EventService();
    Connection conx = MyDB.getInstance().getConx();
    @FXML
    private JFXTextField prix;
    @FXML
    private Button btn_part;
    @FXML
    private Button btn_dash;
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
    private void getAdd(MouseEvent event) {
    }

    @FXML
    private void getDate1(ActionEvent event) {
        localdate = datdeb.getValue();
        System.out.println(localdate);
    }

    @FXML
    private void getDate2(ActionEvent event) {
        localdate1 = datefin.getValue();
        System.out.println(localdate1);

    }

    private void refreshTable2(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/affichage.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void update(ActionEvent event) throws SQLException, MessagingException, AddressException, IOException {

        String NombreLim = intnbr.getText();
        String Lieu = txtlieu.getText();
        String Description = txtdesc.getText();
        String Organisateur = txtorg.getText();
        int p = Integer.parseInt(prix.getText());

        Date datedeb = null;
        datedeb = Date.valueOf(localdate);

        Date datefin = null;
        datefin = Date.valueOf(localdate1);

        String DateDebut = String.valueOf(datedeb.toString());
        String DateFin = String.valueOf(datefin.toString());
        java.util.Date date_convert = java.sql.Date.valueOf(localdate);

        int nbrl = Integer.parseInt(NombreLim);
        Event e = new Event(nbrl, Lieu, Organisateur, DateDebut, DateFin, Description, p);

        String nomevent = searchid.getText();

        String req = "select u.email from user u join tickets t on u.id_user=t.id_user join events e on t.IDEvent=e.IDEvent where e.IDEvent=?";

        PreparedStatement eventStatement = conx.prepareStatement(req);
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
//            for (String email : emails) {
//                ps.evoyerEmailToParticipantWhenEventpostponed(email, e1);
//            }

        } catch (SQLException e1) {
            // handle the exception
        }

        es.modifier(e1);
        System.out.println(e);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("modification avec succees");
        alert.showAndWait();

    }

    @FXML
    private void search1(ActionEvent event) throws SQLException, ParseException {

        String nomevent = searchid.getText();
        System.out.println(nomevent);

        e1 = es.getOneByName(nomevent);

        txtlieu.setText(e1.getLieuEvent());
        txtorg.setText(e1.getOrganisateurEvent());
        txtdesc.setText(e1.getDescriptionEvent());
        intnbr.setText(Integer.toString(e1.getNombreLimEvent()));
        prix.setText(Integer.toString(e1.getPrixEvent()));

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(e1.getDateDabEvent());
            java.util.Date date2 = format.parse(e1.getDateFinEvent());
            System.out.println(date);
            System.out.println(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int yeardeb = calendar.get(Calendar.YEAR);
        int monthdeb = calendar.get(Calendar.MONTH);
        int daydeb = calendar.get(Calendar.DATE);

        LocalDate date = LocalDate.of(yeardeb, monthdeb, daydeb);
        LocalDate date2 = LocalDate.of(yeardeb, monthdeb, daydeb);
        datdeb.setValue(date);
        datefin.setValue(date);

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
    private void creation(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/creation.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void affichage(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/affichage.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void modification(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/modification.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void tickets(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/event/affichageTickets.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void refreshTable(MouseEvent event) {
    }

    @FXML
    private void Close(MouseEvent event) {
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

///////////////////////////////////////////////////////
//             e1.setNomEvent(txtnom.getText());
//             e1.setNombreLimEvent(Integer.parseInt(NombreLim));
//             e1.setLieuEvent(txtlieu.getText());
//             e1.setOrganisateurEvent(Organisateur);
//             e1.setDescriptionEvent(Description);
             
                //es.modifier(e);
