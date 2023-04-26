/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.metierEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utils.MyDB;

/**
 *
 * @author noure
 */
public class Calcul {
    
    Connection conx=MyDB.getInstance().getConx();
    Statement stm;
    public Calcul(){
        conx = MyDB.getInstance().getConx();
    }

    int EventsD;
 public int countEvents() throws SQLException{
      String req = "SELECT count(IDEvent) as n FROM `events` ";
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
       
            if (rst.next()) {
                EventsD = rst.getInt("n");
                }
            
        rst.close();
        stm.close();
//        conx.close();
        
        return(EventsD);   
     }
 
    private int soldTicket;

    public int countTicket() throws SQLException {
        
        String req = "SELECT count(IDTicket) as n FROM tickets ";
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
       
            if (rst.next()) {
                soldTicket = rst.getInt("n");
                }
        rst.close();
        stm.close();
        //conx.close();
        
        return(soldTicket);
    }
    private float total_income;
    
    public int total() throws SQLException{
        String req ="SELECT sum(prixtotal) as n from tickets";
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
            if (rst.next()) {
                total_income = rst.getInt("n");
                }
            rst.close();
            stm.close();
           // conx.close();
            
        return (int) total_income;
        
    }
    
}
