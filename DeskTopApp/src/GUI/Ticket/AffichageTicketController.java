/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Ticket;

import com.jfoenix.controls.JFXTextField;
import Entity.Event;
import Entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.EventService;
import services.TicketService;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class AffichageTicketController implements Initializable {

    @FXML
    private Button btn_creer2;

    /**
     * ******************************************************
     */
    public Ticket t;
    public List<Ticket> Tickets;
    private TicketService ts = new TicketService();
    /**
     * ************************************
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button refresh;
    private JFXTextField idev;
    private Label txtprixtot;

    EventService es = new EventService();
    TicketService tss = new TicketService();
    @FXML
    private TableColumn<Ticket, Integer> colidEv;
    @FXML
    private TableColumn<Ticket, String> colLogin;
    @FXML
    private TableColumn<Ticket, Integer> colQuantite;
    @FXML
    private TableColumn<Ticket, Integer> colPrix;
    @FXML
    private TableView<Ticket> ticketstable;
    @FXML
    private Button btn_aff;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_dash;
    @FXML
    private Button btn_dash1;
    @FXML
    private JFXTextField search;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }

    @FXML
    private void getAdd(MouseEvent event) {
    }

    private void ChangeScene1(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/Ticket/creationTicket.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void refreshTable() {
        ObservableList<Ticket> TicketList = FXCollections.observableArrayList();
//        
        colidEv.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("IDEvent"));
        colLogin.setCellValueFactory(new PropertyValueFactory<Ticket, String>("login"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("prixtotal"));

        try {
            Tickets = ts.getAll();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        TicketList.addAll(Tickets);
        ticketstable.setItems(TicketList);

        /**
         * ******************************************filtered search **********************************************
         */
        FilteredList<Ticket> filter = new FilteredList<>(TicketList, e -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((E) -> {

                if (newValue.isEmpty() || newValue == null) {
                    refreshTable();
                    return true;
                }
                String keySearch = newValue.toLowerCase();

                if (E.getLogin().toLowerCase().indexOf(keySearch) > -1) {

                    return true;
                } else {

                    return false;
                }

            });
        });
        SortedList<Ticket> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(ticketstable.comparatorProperty());
        ticketstable.setItems(sortData);

    }

    private void ChangeScene4(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/affichage.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    private void prixtotale(ActionEvent event) throws SQLException {

        int res = tss.prixTot(String.valueOf(idev.getText()));
        String s = "";

        s = String.valueOf("le prix totale de cet evenement est " + res + "dt");

        txtprixtot.setText(s);
    }

    @FXML
    private void dash(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/welcome.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void tickets(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/Ticket/affichageTicket.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void creation(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/creation.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void eventlist(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/affichage.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
    }

    @FXML
    private void modification(ActionEvent event) throws IOException {
        Parent creerParent = FXMLLoader.load(getClass().getResource("/GUI/event/modification.fxml"));
        Scene creerScene = new Scene(creerParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creerScene);
        window.show();
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
