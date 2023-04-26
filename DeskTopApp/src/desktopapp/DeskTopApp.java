/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import Entity.Event;
import Entity.Notification;
import Entity.Reclamation;
import Entity.Type;
import Utils.MyDB;
import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import javax.mail.MessagingException;
import services.FeedbackService;
import services.NotificationService;
import services.ParticipantService;
import services.ReclamationService;


/**
 *
 * @author Mega-pc
 */
public class DeskTopApp {

    /**
     * @param args the command line arguments
     */    public static void main(String[] args) throws SQLException, IOException, MessagingException {
        // cette fontion pour tester la connexion de la base
        MyDB db1 = MyDB.getInstance();
        
        FeedbackService fs = new FeedbackService();
      

    }
}
