/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Reclamation;
import Entity.Statut;
import Entity.Type;
import Utils.MyDB;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Mega-pc
 */
public class ReclamationService implements ICRUD<Reclamation> {

    Connection conx = MyDB.getInstance().getConx();
    String email_user = "";

    public void evoyerEmailToAdmin(String email, Reclamation t) throws IOException {
        // *************************Partie evoyer par mail API ****************************
        //  la session pour l'envoie d'un email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yousseffarhat818@gmail.com", "cerbtzsaklmkfbde");
            }
        });
        try {
            // Création de l'objet Message
            Message message = new MimeMessage(session);
            // from 
            message.setFrom(new InternetAddress(email));
            // Recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yousseffarhat818@gmail.com"));
            // Objet de l'email
            message.setSubject(t.getType().getLabel());
            Multipart emailContent = new MimeMultipart();

            // creer la parite message de l'email
            MimeBodyPart emailTextContent = new MimeBodyPart();
            emailTextContent.setText(t.getDescription());
            // creer la parite addPDF de l'email
            MimeBodyPart emailPdfFile = new MimeBodyPart();
            emailPdfFile.attachFile("C:/Users/noure/Downloads/" + t.getPiece_jointe());
            //ajouter les deux parties
            emailContent.addBodyPart(emailTextContent);
            emailContent.addBodyPart(emailPdfFile);

            message.setContent(emailContent);

            //  Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void evoyerEmailToUser(String email, Reclamation t) throws IOException {
        // *************************Partie evoyer par mail API ****************************
        //  la session pour l'envoie d'un email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yousseffarhat818@gmail.com", "cerbtzsaklmkfbde");
            }
        });
        try {
            // Création de l'objet Message
            Message message = new MimeMessage(session);
            // from 
            message.setFrom(new InternetAddress("yousseffarhat818@gmail.com"));
            // Recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            // Objet de l'email
            message.setSubject("Reclamation " + t.getStatut().getLabel());
            Multipart emailContent = new MimeMultipart();

            // creer la parite message de l'email
            MimeBodyPart emailTextContent = new MimeBodyPart();
            if (t.getStatut().getLabel() == "En_attente") {
            String htmlBody = new String(Files.readAllBytes(Paths.get("src/reclamation.html")));                     
            message.setContent(htmlBody, "text/html");
            } else {
            String htmlBody = new String(Files.readAllBytes(Paths.get("src/reclamation_solved.html")));                     
            message.setContent(htmlBody, "text/html");
            }

            //  Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye_vers_user");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }

    public void ajouterSelonEmailUser(String email, Reclamation t) throws SQLException, IOException {
        try {
            //recuperation id_user appartir de l'email
            int id_u = 0;
            String req2 = "Select `id_user` from `users` where email=" + "'" + email + "'";

            Statement stm = conx.createStatement();
            ResultSet rs = stm.executeQuery(req2);
            while (rs.next()) {
                id_u = rs.getInt(1);
            }
            String req = "INSERT INTO reclamations (`id_user`,`type` ,`description`, `date_reclamation`, `piece_jointe`)VALUES (?,?,?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, id_u);
            ps.setString(2, t.getType().getLabel());
            ps.setString(3, t.getDescription());
            ps.setDate(4, new java.sql.Date(t.getDate_reclamation().getTime()));
            ps.setString(5, t.getPiece_jointe());
            System.out.println(ps);
            ps.executeUpdate();
            System.out.println("Reclamation Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        evoyerEmailToAdmin(email, t);
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
        try {
            String req = "INSERT INTO reclamations (`id_user`,`type` ,`description`, `date_reclamation`, `piece_jointe`)VALUES (?,?,?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, t.getId_user());
            ps.setString(2, t.getType().getLabel());
            ps.setString(3, t.getDescription());
            ps.setDate(4, new java.sql.Date(t.getDate_reclamation().getTime()));
            ps.setString(5, t.getPiece_jointe());
            ps.executeUpdate();
            System.out.println("Reclamation Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation t) throws SQLException {
        try {
            String req = "UPDATE  reclamations SET `date_reclamation`=?,`id_user`=?,`description`=?,`piece_jointe`=?,`statut`=?,`date_resolution`=? WHERE `id_reclamation`= ?";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setDate(1, new java.sql.Date(t.getDate_reclamation().getTime()));
            ps.setInt(2, t.getId_user());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getPiece_jointe());
            ps.setString(5, t.getStatut().getLabel());
            ps.setDate(6, new java.sql.Date(t.getDate_resolution().getTime()));
            ps.setInt(7, t.getId_reclamation());
            ps.executeUpdate();
            System.out.println("Reclamation Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierpersonalise(Reclamation t) {
        try {
            
            //recuperation id_user appartir de l'email

            String req2 = "Select `email` from `users` where id_user="+t.getId_user();
           
            Statement stm;
            try {
                stm = conx.createStatement();
                ResultSet rs = stm.executeQuery(req2);
               
                while (rs.next()) {
                    email_user = rs.getString(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            String req = "UPDATE  reclamations SET `statut`=?,`date_resolution`=? WHERE `id_reclamation`= ?";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, t.getStatut().getLabel());
            ps.setDate(2, new java.sql.Date(t.getDate_resolution().getTime()));
            ps.setInt(3, t.getId_reclamation());
            ps.executeUpdate();           
            System.out.println("Reclamation Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            
            evoyerEmailToUser(email_user, t);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        try {
            String req = "DELETE FROM reclamations WHERE `id_reclamation`=?";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reclamation Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() throws SQLException {
        List<Reclamation> result = new ArrayList<>();
        try {
           // Connection conx = MyDB.getInstance().getConx();
            String req = "SELECT * FROM reclamations";
            PreparedStatement ps = conx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);

            while (rs.next()) {
                int id_reclamation = rs.getInt(1);
                int id_user = rs.getInt(2);
                int id_admin = rs.getInt(3);
                Type t = null;
                switch (rs.getString(4)) {
                    case "Information sur votre compte":
                        t = Type.ACCOUNT_INFORMATION;
                        break;
                    case "Information sur vos commandes":
                        t = Type.ORDER_INFORMATION;
                        break;
                    case "Suggestions et remarques sur le site":
                        t = Type.WEBSITE_FEEDBACK;
                        break;
                    case "Signaler un dysfonctionnement":
                        t = Type.REPORT_ISSUE;
                        break;
                    case "Autre":
                        t = Type.OTHER;
                        break;
                }
                String description = rs.getString(5);
                Date date_reclamation = rs.getDate(6);
                String piece_jointe = rs.getString(7);
                Statut statut = Statut.valueOf(rs.getString(8));
                Date date_resolution = rs.getDate(9);
                Reclamation c = new Reclamation(id_reclamation, id_user, id_admin, t, description, date_reclamation, piece_jointe, statut, date_resolution);
                result.add(c);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
     
    public List<Reclamation> afficherAcevJointure() throws SQLException {
        List<Reclamation> result = new ArrayList<>();
        try {
            String req = "SELECT u.nom_user,r.type,r.statut,r.date_reclamation,r.id_user,r.id_reclamation,r.description,r.piece_jointe FROM reclamations r JOIN users u ON r.id_user = u.id_user";
            
            PreparedStatement ps = conx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);

            while (rs.next()) {
                String nom_user = rs.getString(1);              
                Type t = null;
                switch (rs.getString(2)) {
                    case "Information sur votre compte":
                        t = Type.ACCOUNT_INFORMATION;
                        break;
                    case "Information sur vos commandes":
                        t = Type.ORDER_INFORMATION;
                        break;
                    case "Suggestions et remarques sur le site":
                        t = Type.WEBSITE_FEEDBACK;
                        break;
                    case "Signaler un dysfonctionnement":
                        t = Type.REPORT_ISSUE;
                        break;
                    case "Autre":
                        t = Type.OTHER;
                        break;
                }     
                Statut statut = Statut.valueOf(rs.getString(3));
                Date date_reclamation = rs.getDate(4);
                int id_uesr = rs.getInt(5);
                int id_reclamation =rs.getInt(6);
                String description = rs.getString(7);
                String piece_jointe = rs.getString(8);
                
                Reclamation c = new Reclamation(nom_user,id_uesr,t, statut, date_reclamation,id_reclamation,description,piece_jointe);
                result.add(c);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public Reclamation afficherById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
