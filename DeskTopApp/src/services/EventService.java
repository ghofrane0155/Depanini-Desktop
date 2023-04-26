/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author noure
 */
public class EventService implements InterfaceCRUD<Event> {

    Connection conx;
    Statement stm;

    public EventService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Event e) throws SQLException {

        String req;
        req = "INSERT INTO `events`(`NomEvent`, `NombreLimEvent`, `LieuEvent`, `DateDebutEvent`, `DateFinEvent`, `OrganisateurEvent`, `DescriptionEvent`, `ImageEvent`, `prixEvent`) VALUES ( '"
           + e.getNomEvent() + "', '" + e.getNombreLimEvent() + "', '" + e.getLieuEvent() + "', '" + e.getDateDabEvent() + "', '" + e.getDateFinEvent() + "', '" + e.getOrganisateurEvent() + "', '" + e.getDescriptionEvent() + "', '" + e.getImageEvent() + "', '" + e.getPrixEvent() +"') ";
 
conx = MyDB.getInstance().getConx();
        stm = conx.createStatement();
        stm.executeUpdate(req);
        

    }

    @Override
    public void modifier(Event e) throws SQLException {
//         String req1 ="UPDATE events SET (`NomEvent`, `NombreLimEvent`, `LieuEvent`, `DateDebutEvent`, `DateFinEvent`, `OrganisateurEvent`, `DescriptionEvent`, `ImageEvent`, `prixEvent`) VALUES ( '"
//                 + e.getNomEvent() + "', '" + e.getNombreLimEvent() + "', '" + e.getLieuEvent() + "', '" + e.getDateDabEvent() + "', '" + e.getDateFinEvent() + "', '" + e.getOrganisateurEvent() + "', '" + e.getDescriptionEvent() + "', '" + e.getImageEvent() + "', '" + e.getPrixEvent() +"') ";
//         
//         conx = MyDB.getInstance().getConx();
//        stm = conx.createStatement();
//        stm.executeUpdate(req1);
       String req = "UPDATE events SET `NomEvent`=?,`NombreLimEvent`=?,`LieuEvent`=?,`DateDebutEvent`=?,`DateFinEvent`=?,`OrganisateurEvent`=?,`DescriptionEvent`=?,`ImageEvent`=? WHERE `IDEvent`=?";
        PreparedStatement ps = conx.prepareStatement(req);
System.out.println(e.getNomEvent());
        System.out.println(ps);
        ps.setString(1,e.getNomEvent());
        ps.setInt(2,e.getNombreLimEvent());
        ps.setString(3,e.getLieuEvent());
        ps.setString(4,e.getDateDabEvent());
        ps.setString(5,e.getDateFinEvent());
        ps.setString(6,e.getOrganisateurEvent());
        ps.setString(7,e.getDescriptionEvent());
        ps.setString(8,e.getImageEvent());
        ps.setInt(9,e.getIDEvent());
         System.out.println("----------------"+ps);
        
     
        ps.executeUpdate();
        
   }

    @Override
    public void delete (int id) throws SQLException {

      
            String req = "DELETE FROM `events` WHERE `IDEvent`=?";
            PreparedStatement ps = conx.prepareStatement(req);
            System.out.println(ps);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("evenement supprimee !");
    }

    @Override
    public List<Event> getAll() throws SQLException {
        List<Event> Events = new ArrayList<>();
        String req = "SELECT * FROM `events`";
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Event e;
            e = new Event(
                    rst.getInt("IDEvent"),
                    rst.getString("NomEvent"),
                    rst.getInt("NombreLimEvent"),
                    rst.getString("LieuEvent"),
                    rst.getString("OrganisateurEvent"),
                    rst.getString("DateDebutEvent"),
                    rst.getString("dateFinEvent"),
                    rst.getString("DescriptionEvent"),
                    rst.getString("ImageEvent"),
                    rst.getInt("prixEvent")
                    
            );
            Events.add(e);
        }
        return Events;
    }

    @Override
    public Event getOneById(int id) throws SQLException {
        Event e =new Event();
        String req = "SELECT `IDEvent`, `NomEvent`, `NombreLimEvent`, `LieuEvent`, `DateDebutEvent`, `DateFinEvent`, `OrganisateurEvent`, `DescriptionEvent`,'ImageEvent','prixEvent' FROM `events` WHERE IDEvent = "+id;
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
            while (rst.next()) {
                   
                    e = new Event(
                            rst.getInt("IDEvent"),
                            rst.getString("NomEvent"),
                            rst.getInt("NombreLimEvent"),
                            rst.getString("LieuEvent"),
                            rst.getString("OrganisateurEvent"),
                            rst.getString("dateDebutEvent"),
                            rst.getString("DateFinEvent"),
                            rst.getString("DescriptionEvent"),
                            rst.getString("ImageEvent"),
                            rst.getInt("prixEvent")
                            
                    );
                    
                }
                return e;
    }

    @Override
    public Event getOneByName(String Name) throws SQLException {
        
        
        String req = "SELECT * FROM `events` WHERE NomEvent = '"+Name+"'";
        stm = conx.createStatement();
        System.out.println(req);
        ResultSet rst = stm.executeQuery(req);
        
        
            while (rst.next()) {
                    Event e;
                    e = new Event(
                            rst.getInt("IDEvent"),
                            rst.getString("NomEvent"),
                            rst.getInt("NombreLimEvent"),
                            rst.getString("LieuEvent"),
                            rst.getString("OrganisateurEvent"),
                            rst.getString("dateDebutEvent"),
                   
                            
                            rst.getString("DateFinEvent"),
                            rst.getString("DescriptionEvent"),
                            rst.getString("ImageEvent"));
                            rst.getInt("prixEvent");
                            
                           System.out.println(e.toString()); 
                   return  e;
                    
                            }
        
                    return null;
    }
    /////////////////////notif
    public Event getevent(String Name) throws SQLException {
         
         Event e =new Event();
        String req="select * from events where IDEvent IN (select IDEvent from tickets WHERE login='"+Name+"')";
        
         
        
        
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
            while (rst.next()) {
                   
                    e = new Event(
                            rst.getInt("IDEvent"),
                            rst.getString("NomEvent"),
                            rst.getInt("NombreLimEvent"),
                            rst.getString("LieuEvent"),
                            rst.getString("OrganisateurEvent"),
                            rst.getString("dateDebutEvent"),
                            rst.getString("DateFinEvent"),
                            rst.getString("DescriptionEvent"),
                            rst.getString("ImageEvent"),
                            rst.getInt("prixEvent")
                            
                    );
                    
                }
                return e;
    
       
    }

    public int getnbrl(String p) throws SQLException {
        String req="select NombreLimEvent as nbrL from events where NomEvent="+p;
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        int nbr = 0;
            if (rst.next()) {
                nbr = rst.getInt("nbrL");
                }
        rst.close();
        stm.close();
        //conx.close();
        
        return(nbr);
    } 

    public int prixTot(int p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int prixTot(String nom) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
       // conx.close();
        
        return(EventsD);   
     }

    

   

    

}
