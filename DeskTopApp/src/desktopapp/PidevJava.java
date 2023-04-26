/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import Entity.Demandes;
import Entity.Contrats;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ContratsService;
import services.DemandesService;
/**
 *
 * @author yasmine
 */
public class PidevJava {
        public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
//        Demandes d = new Demandes(10,3,100,"dev","14/02/2025");        
//        Demandes d1 = new Demandes(13,333,2,"dev","14/02/2029");
//        
//        
//       DemandesService sd = new DemandesService();
//     Contrats d1 = new Contrats(16,"yasmine","env","14/02/2029");
        ContratsService sd = new ContratsService();
        System.out.println(sd.Recherche("yasmine"));
//        
//        try {
//            sd.ajouterD(d1);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        
//          try {
//            System.out.println(sd.afficherdemande());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
       
         /*  try {
            sd.supprimerD(13);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
//            try {
//            sd.modifierD(c);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
            
            //sd.supprimerD(10);
        
    }    
}
