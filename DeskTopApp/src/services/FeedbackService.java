/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Feedback;
import Entity.Notification;
import Entity.user;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static services.UserService.connexion;

/**
 *
 * @author Mega-pc
 */
public class FeedbackService implements ICRUD<Feedback> {

    NotificationService notifs;
    Connection conx = MyDB.getInstance().getConx();

    @Override
    public void ajouter(Feedback f) throws SQLException {
        try {
            String req = "INSERT INTO feedbacks (`id_freelancer`,`id_client` ,`commentaire`, `date`, `stars`)VALUES (?,?,?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, f.getId_freelancer());
            ps.setInt(2, f.getId_client());
            ps.setString(3, f.getCommentaire());
            ps.setDate(4, new java.sql.Date(f.getDate().getTime()));
            ps.setDouble(5, f.getStars());
            ps.executeUpdate();
            System.out.println("Feedback Added !");
            String req1 = "Select id_feedback From feedbacks ORDER BY id_feedback DESC LIMIT 1;";
            PreparedStatement ps1 = conx.prepareStatement(req1);
            ResultSet res_query = ps1.executeQuery(req1);
            while (res_query.next()) {
                int id_feedback = res_query.getInt(1);              
                notifs = new NotificationService();
                String contenu_notification = "Feedback";
                LocalDate localDate = LocalDate.now();
                Date date = java.sql.Date.valueOf(localDate);
                Notification not = new Notification(id_feedback, date, contenu_notification);
                notifs.createNotification(not);              
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Feedback f) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Feedback> afficher() throws SQLException {
        List<Feedback> res = new ArrayList<>();
        String req = "Select * From feedbacks";
        PreparedStatement ps = conx.prepareStatement(req);

        ResultSet res_query = ps.executeQuery(req);
        while (res_query.next()) {
            int id_feedback = res_query.getInt(1);
            int id_freelancer = res_query.getInt(2);

            int id_client = res_query.getInt(3);
            String comm = res_query.getString(4);
            Date date = res_query.getDate(5);
            Double stars = res_query.getDouble(6);
            Feedback f = new Feedback(id_feedback, id_freelancer, id_client, comm, date, stars);
            res.add(f);
        }
        return res;
    }
// metier affichage TOP 10 USER

    public ObservableList<String> afficherNameAndStars() throws SQLException {
        //  List<Feedback> res = new ArrayList<>();
        ObservableList<String> listItems = FXCollections.observableArrayList();
        String req = "SELECT u.email, SUM(f.stars) as total_stars FROM users u JOIN feedbacks f ON u.id_user = f.id_freelancer GROUP BY u.id_user ORDER BY total_stars DESC LIMIT 5;";
        PreparedStatement ps = conx.prepareStatement(req);

        ResultSet res_query = ps.executeQuery(req);
        while (res_query.next()) {
            String nom = res_query.getString(1);
            Double str = res_query.getDouble(2);
            listItems.add(nom + "   Total :" + str);
        }
        return listItems;
    }

    public Double calculeStars(int user_id) throws SQLException {
        Double total_stars = 0.0;

        String reqq = "SELECT SUM(stars) FROM feedbacks WHERE id_freelancer =" + user_id + ";";
        System.out.println(reqq);
        PreparedStatement ps = conx.prepareStatement(reqq);
        ResultSet rsss = ps.executeQuery(reqq);
        while (rsss.next()) {
            total_stars = rsss.getDouble(1);
        }
        return total_stars;
    }

    public user selectUser(String email) throws SQLException {
        user u1 = null;

        String req = "select * from users where email= '" + email + "';";
        PreparedStatement ps = conx.prepareStatement(req);

        //ensemble de resultat
        ResultSet rst = ps.executeQuery(req);

        while (rst.next()) {
            user u = new user(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
                    rst.getString(5), rst.getDate(6), rst.getString(7), rst.getString(8), rst.getString(9),
                    Entity.Enum.Sexe.valueOf(rst.getString(10)), Entity.Enum.TypeR.valueOf(rst.getString(11)), rst.getString(12));
            return u;
        }

        return u1;

    }

    @Override
    public Feedback afficherById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
