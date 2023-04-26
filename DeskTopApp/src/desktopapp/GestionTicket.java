/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import Entity.Ticket;
import java.sql.SQLException;
import services.TicketService;
import Utils.MyDB;

/**
 *
 * @author noure
 */
public class GestionTicket {


    public static void main(String[] args) throws SQLException {
        MyDB db1 = MyDB.getInstance();
        TicketService ts = new TicketService();

        Ticket t1 = new Ticket(3, 5, 1, 1, 20, "nour123");
        //Ticket t2= new Ticket(1, 1, 50);
        // System.out.println(ts.countTicket());

//         try{
//                ts.ajouter(t1);
//                System.out.println("ajout avec succes");
//            }
//                catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
        /*try{
                ts.modifier(t1);
                System.out.println("modifié avec succes");
            }
                catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
         
          try {
               ts.delete(t1.getIDTicket());
               System.out.println("supprimé avec succes"); 
            } 
                catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }*/
        try {
            System.out.println(ts.getAll("nour123"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

}
