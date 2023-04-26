/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;

/**
 *
 * @author noure
 */
public class TicketService implements InterfaceCRUD<Ticket> {

    Connection conx;
    Statement stm;

    public TicketService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Ticket t) throws SQLException {
        String req;
        req = "INSERT INTO `Tickets`(`id_user`, `IDEvent`, `quantite`,prixtotal,login) VALUES ("
                 + t.getId_user() + "," + t.getIDEvent() + "," + t.getPrixTicket() +"," + t.getPrixtotal() + ",'" + t.getLogin() + "') ";
        stm = conx.createStatement();

        stm.executeUpdate(req);
        System.out.println(req); 
   }

    @Override
    public void modifier(Ticket t) throws SQLException {
        String req = "update Tickets set `IDTicket`=?, `id_user`=?, `IDEvent`=?, `quantite`=? WHERE `IDTicket`=?";
        PreparedStatement ps = conx.prepareStatement(req);

        ps.setInt(1, t.getIDTicket());
        ps.setInt(2, t.getId_user());
        ps.setInt(3, t.getIDEvent());
        ps.setInt(4, t.getPrixTicket());
        ps.setInt(5, t.getIDTicket());
        System.out.println(ps);

        ps.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `Tickets` where IDTicket=" + id;
        stm = (Statement) conx.createStatement();
        stm.executeUpdate(req);

        System.out.println(req);

    }
    
 

    @Override
    public List<Ticket> getAll() throws SQLException {
        List<Ticket> Tickets = new ArrayList<>();
        String req = "SELECT * FROM `Tickets`";
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Ticket t;
            t = new Ticket(

                    rst.getInt("IDTicket"),
                    rst.getInt("id_user"),
                    rst.getInt("IDEvent"),
                    rst.getInt("quantite"),
                    rst.getInt("prixtotal"),
                    rst.getString("login")
            );
            Tickets.add(t);
        }
        return Tickets;

    }

    
    
    public List<Ticket> getAll(String log) throws SQLException {
        List<Ticket> Tickets = new ArrayList<>();
        String req = "SELECT * FROM `Tickets` where login='"+ log+"'";
        System.out.println(req);
        stm = conx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Ticket t;
            t = new Ticket(
                    rst.getInt("IDTicket"),
                    rst.getInt("id_user"),
                    rst.getInt("IDEvent"),
                    rst.getInt("quantite"),
                    rst.getInt("prixtotal"),
                    rst.getString("login")
            );
            Tickets.add(t);
            System.out.println(Tickets);
        } 
         return Tickets;
    }

    @Override
    public Ticket getOneById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ticket getOneByName(String Name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int prixTot(String p) throws SQLException {

        String sql = "SELECT SUM(quantite) as q FROM Tickets WHERE IDEvent=(SELECT IDEvent FROM events WHERE NomEvent=?)";
        PreparedStatement stmt = conx.prepareStatement(sql);
        stmt.setString(1, p);
        ResultSet rs = stmt.executeQuery();
        int sum=0;
        if (rs.next()) {
            sum = rs.getInt("q");
            }
        rs.close();
        stmt.close();
        conx.close();
        return(sum);
    }

    @Override
    public int prixTot(int p) throws SQLException {
        String sql = "SELECT SUM(quantite) as q FROM Tickets WHERE IDEvent=(?)";
        PreparedStatement stmt = conx.prepareStatement(sql);
        stmt.setInt(1, p);
        ResultSet rs = stmt.executeQuery();
        int sum=0;
        if (rs.next()) {
            sum = rs.getInt("q");
            }
        rs.close();
        stmt.close();
        conx.close();
        return(sum);
    }
    
    
    

}
