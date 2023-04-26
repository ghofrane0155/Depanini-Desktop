/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Offre;

import Utils.MyDB;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leila
 */
public class FXMLStatsController implements Initializable {
     Connection connexion ;
      Statement stm;
       private Stage stage;
      private Scene scene;
      private Parent root;

    @FXML
    private PieChart pieChart;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            afficherPie();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
     public FXMLStatsController() {
        connexion = MyDB.getInstance().getConx();
    }   
      public void afficherPie() throws SQLException{
        int infOffersCount=0 ;
        int babySittingOffersCount=0 ;
        int demenagementOffersCount=0 ;
        int coutureOffersCount=0 ;
        int beauteOffersCount=0 ;
        int traductionOffersCount=0 ;
         String req = "SELECT COUNT(*) AS count FROM offres WHERE type_cat='Informatique' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           infOffersCount = rst.getInt(1);
        }
         PieChart.Data qtr1= new PieChart.Data("Offre Informatique", +infOffersCount) ;
         String req1 = "SELECT COUNT(*) AS count FROM offres WHERE type_cat='Baby_sitting' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst1 = stm.executeQuery(req1);

        while (rst1.next()) {
         babySittingOffersCount = rst1.getInt(1);
        }
         PieChart.Data qtr2= new PieChart.Data("Offre Baby_Sitting", +babySittingOffersCount) ;
          String req2 = "SELECT COUNT(*) AS count FROM offres WHERE type_cat='Demenagement' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst2 = stm.executeQuery(req2);

        while (rst2.next()) {
         demenagementOffersCount = rst2.getInt(1);
        }
         PieChart.Data qtr3= new PieChart.Data("Offre demenagement", +demenagementOffersCount) ; 
          String req3 = "SELECT COUNT(*) AS count FROM offres WHERE type_cat='Couture' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst3 = stm.executeQuery(req3);

        while (rst3.next()) {
         coutureOffersCount = rst3.getInt(1);
        }
         PieChart.Data qtr4= new PieChart.Data("Offre Couture ", +coutureOffersCount) ; 
         
           String req4 = "SELECT COUNT(*) AS count FROM offres WHERE type_cat='Beaute' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst4 = stm.executeQuery(req4);

        while (rst4.next()) {
         beauteOffersCount = rst4.getInt(1);
        }
         PieChart.Data qtr5= new PieChart.Data("Offre Beaute ", +beauteOffersCount) ; 
             String req5 = "SELECT COUNT(*) AS count FROM offres WHERE type_cat='Traduction' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst5 = stm.executeQuery(req5);

        while (rst5.next()) {
         beauteOffersCount = rst5.getInt(1);
        }
         PieChart.Data qtr6= new PieChart.Data("Offre Traduction ", +traductionOffersCount) ; 
         
         pieChart.getData().addAll(qtr1,qtr2,qtr3,qtr4,qtr5,qtr6) ;
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/GUI/Offre/FXMLAfficher.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
