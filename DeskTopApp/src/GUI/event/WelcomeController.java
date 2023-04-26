/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.event;

import Entity.admin;
import Entity.user;
import com.sun.javafx.scene.SceneHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.metierEvent.Calcul;
import services.EventService;
import services.TicketService;
import Utils.MyDB;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.AdminService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class WelcomeController implements Initializable {

    @FXML
    private Button btn_creer2;
    @FXML
    private Button btn_aff;
    @FXML
    private Button btn_modif;
    @FXML
    private Label dashboard_totalsold;
    @FXML
    private Label dashboard_totalrev;
    @FXML
    private Label dashboard_events;
    @FXML
    private Button btn_dash;
    @FXML
    private Button btn_dash1;

//     Connection conx=MyDB.getInstance().getConx();
    Statement stm;
    TicketService tss = new TicketService();
    EventService es = new EventService();
    Calcul c = new Calcul();
    @FXML
    private AnchorPane chartPane;
    @FXML
    private Button back;
/*********************************************************/   
    public user u;
    public admin a;
    public List<user> users;
    private UserService us=new UserService();
    private AdminService as=new AdminService();
    @FXML
    private ImageView tfimg;
    @FXML
    private Text tfnom;
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
        
        
        
        try {
            displayTotalEvents();
        } catch (SQLException ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ////////////////line chart 
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("event");
        yAxis.setLabel("revenu");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Event Prices");
      
        lineChart.getXAxis().setLabel("Events");
        lineChart.getYAxis().setLabel("Tickets");
        
        
        try {
            Connection conx = MyDB.getInstance().getConx();
            Statement statement = conx.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT IDEvent, SUM(prixtotal) AS totalprice FROM tickets GROUP BY IDEvent");
             // Populate the line chart with data series
            XYChart.Series<Number, Number> Series = new XYChart.Series<>();
            while (resultSet.next()) {
                Series.getData().add(new XYChart.Data<>(resultSet.getInt("IDEvent"), resultSet.getInt("totalprice")));
            }
                lineChart.getData().add(Series);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        chartPane.getChildren().add(lineChart);        

    }

    @FXML
    private void getAdd(MouseEvent event) {
    }



    @FXML
    private void tickets(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/Ticket/affichageTicket.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

   
    /////////////////////////////les metiers Calcul

    public void displayTotalEvents() throws SQLException {

        dashboard_events.setText(String.valueOf(c.countEvents()));
        dashboard_totalrev.setText(String.valueOf(c.total()));
        dashboard_totalsold.setText(String.valueOf(c.countTicket()));
    }

    ////////////////////////////////////////////////////////////
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
    private void events(ActionEvent event) throws IOException {
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
    private void back(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/Admin/TableView.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

}
