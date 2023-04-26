/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Notification;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mega-pc
 */
public class NotificationService {

    Connection conx = MyDB.getInstance().getConx();

    public void createNotification(Notification notif) {
        try {
            String req = "INSERT INTO `notifications`(`id_feedback`, `date_notification`, `contenu_notification`) VALUES (?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, notif.getId_feedback());
            ps.setDate(2, new java.sql.Date(notif.getDate_notification().getTime()));
            ps.setString(3, notif.getContenu_notification());
            ps.executeUpdate();
            System.out.println("Notification Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    // mettre a jour le champ mark as read
    public void modifier(int id_notification) throws SQLException {
        try {
            String req = "UPDATE  notifications SET `marked_as_read`=?  WHERE `id_notification`=?";
            PreparedStatement ps = conx.prepareStatement(req);
            // pour que le statue marked as read contient la date de l'update
            LocalDate localDate = LocalDate.now();
            java.util.Date date = java.sql.Date.valueOf(localDate);
            ps.setDate(1, (Date) date);
            ps.setInt(2, id_notification);
            ps.executeUpdate();
            System.out.println("notifications Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // afficher with current user 
    public List<Notification> afficherNortificationCurrentUser(int CurrentUserID) throws SQLException {
        List<Notification> result = new ArrayList<>();
        try {
            String req = "SELECT n.* FROM users u INNER JOIN feedbacks f ON u.id_user = f.id_client INNER JOIN notifications n ON f.id_feedback = n.id_feedback WHERE u.id_user = " + CurrentUserID + ";";
            PreparedStatement ps = conx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {
                int id_notification = rs.getInt(1);
                int id_feedback = rs.getInt(2);
                Date date = rs.getDate(3);
                String contenu_notification = rs.getString(4);
                Date marked_as_read = rs.getDate(5);
                Notification n = new Notification(id_notification, id_feedback, date, contenu_notification, marked_as_read);
                result.add(n);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    // affiher les etoile avec les nom des utilisateur qui on fait un feedback sur le current user
     public List<Notification> afficherNameNemberStars(int CurrentUserID) throws SQLException {
        List<Notification> result = new ArrayList<>();
        try {
            String req = "SELECT n.id_notification,u.nom_user,f.stars FROM users u INNER JOIN feedbacks f ON u.id_user = f.id_freelancer INNER JOIN notifications n ON f.id_feedback = n.id_feedback WHERE f.id_client = " + CurrentUserID + ";";
            PreparedStatement ps = conx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {
                int id_notification = rs.getInt(1);
                String nom_user = rs.getString(2);
                 Double stars = rs.getDouble(3);
                Notification n = new Notification(id_notification, nom_user,stars);
                result.add(n);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
}
