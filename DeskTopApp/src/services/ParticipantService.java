/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Event;
import Entity.participant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import Utils.MyDB;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;

/**
 *
 * @author noure
 */
public class ParticipantService {
    Connection conx;
    Statement stm;

    
     
    public ParticipantService(){
        conx=MyDB.getInstance().getConx();   
    }
         
    
    
    
    public List<participant> getAll() throws SQLException{
    List<participant> participants = new ArrayList<>();
    String req;
    req = "SELECT user.id_user, tickets.IDTicket, user.login, user.email, user.tel, user.role, events.prixEvent, tickets.quantite " +
          "FROM user " +
          "INNER JOIN tickets ON user.id_user = tickets.id_user " +
          "INNER JOIN events ON events.IDEvent = tickets.IDEvent";
    stm = conx.createStatement();
    ResultSet rst = stm.executeQuery(req);
    while (rst.next()) {
        participant p = new participant(
            rst.getString("login"), 
            rst.getString("email"), 
            rst.getString("tel"), 
            rst.getString("role"), 
            rst.getInt("IDTicket"), 
            rst.getInt("id_user"),
            rst.getInt("quantite"),
            rst.getInt("prixEvent")
        );
        participants.add(p);
    }
        System.out.println(participants);
    return participants;
}
    public void evoyerEmailToParticipantWhenEventDeleted(String email, Event event) throws AddressException, MessagingException, IOException{
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
            message.setSubject("Abrogation de l'événement ! ") ;
            Multipart emailContent = new MimeMultipart();

            // creer la parite message de l'email
            MimeBodyPart emailTextContent = new MimeBodyPart();
            
            String htmlBody = new String(Files.readAllBytes(Paths.get("src/event_deleted.html"))).replace("jjmmyyyy",event.getDateDabEvent());          
            message.setContent(htmlBody, "text/html");

            // Envoie l'email
                    Transport.send(message); 
    }

         catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
    public void evoyerEmailToParticipantWhenEventpostponed(String email, Event event) throws AddressException, MessagingException, IOException{
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
            message.setSubject("Abrogation de l'événement ! ") ;
            Multipart emailContent = new MimeMultipart();

            // creer la parite message de l'email
            MimeBodyPart emailTextContent = new MimeBodyPart();
            

            String htmlBody = new String(Files.readAllBytes(Paths.get("src/event_updated.html"))).replace("XXXX",event.getNomEvent());
            String htmlBody2 = new String(htmlBody).replace("jjmmyyyy",event.getDateDabEvent());
            String htmlBody3 = new String(htmlBody2).replace("@lieu",event.getLieuEvent());
           
            message.setContent(htmlBody3, "text/html");
                    // Envoie l'email
                    Transport.send(message); 
    }

         catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
//rst.getString("login"),rst.getString("email"), rst.getString("LieuEvent"), 0, 0, 0
//FROM table1
//
//INNER JOIN table2 ON table1.common_column = table2.common_column
//INNER JOIN table3 ON table2.common_column = table3.common_column
//WHERE table1.condition = 'value';