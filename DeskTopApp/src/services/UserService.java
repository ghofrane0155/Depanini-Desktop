package services;

import Entity.user;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entity.Reclamation;
import Utils.MyDB;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
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
 * @author Ghof
 */
public class UserService implements ICRUD<user>{
   static Connection connexion;
   public Statement stm;
   public static PreparedStatement ps;

    public UserService() {
        connexion = MyDB.getInstance().getConx();
    }
    
    
     @Override
    public void ajouter(user u) throws SQLException {
        try {
        String requete = "insert into users (id_user,nom_user,prenom_user,login,password,date_nais_user,email,adresse,tel,sexe,roles,photo_user) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
         ps = (PreparedStatement) connexion.prepareStatement(requete);
            ps.setInt(1, u.getId_user());
            ps.setString(2, u.getNom_user());
            ps.setString(3, u.getPrenom_user());
            ps.setString(4, u.getLogin());
            ps.setString(5,u.getPassword());
           
            ps.setDate(6,new java.sql.Date(u.getDate_nais_user().getTime()));
            ps.setString(7, u.getEmail());
            ps.setString(8, u.getAdresse());
            ps.setString(9, u.getTel());
            ps.setString(10,u.getSexe());
            ps.setString(11, u.getRoles());

            ps.setString(12, u.getPhoto_user());
             

            ps.executeUpdate();
        System.out.println("user Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        stm = (Statement) connexion.createStatement();  
//        stm.executeUpdate(requete);
     }
/*****************************************************************************/    
    @Override
    public void supprimer(int id) throws SQLException {
        String requete = "DELETE FROM users where id_user="+id;
        
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);    }
/*****************************************************************************/
    @Override
    public void modifier(user t) throws SQLException {
        String requete = "UPDATE users set nom_user=?,prenom_user=?,login=?,password=?,date_nais_user=?,email=?,adresse=?,tel=?,sexe=?,roles=?,photo_user=? WHERE id_user=?";

            ps = (PreparedStatement) connexion.prepareStatement(requete);
            ps.setString(1, t.getNom_user());
            ps.setString(2, t.getPrenom_user());
            ps.setString(3, t.getLogin());
            ps.setString(4,t.getPassword());
           
            ps.setDate(5,new java.sql.Date(t.getDate_nais_user().getTime()));
            ps.setString(6, t.getEmail());
            ps.setString(7, t.getAdresse());
            ps.setString(8, t.getTel());
            ps.setString(9,t.getSexe());
            ps.setString(11, t.getRoles());
            ps.setString(12, t.getPhoto_user());
            ps.setInt(13,t.getId_user());
           
            ps.executeUpdate();
        
//        stm = (Statement) connexion.createStatement();  
//        stm.executeUpdate(requete);   
    }
/************************************************************************/
/***************************************************************************/
    public void modifier_password(int id,String pass) throws SQLException {
        String requete = "UPDATE users set password=? WHERE id_user=?";
        try{
            ps = (PreparedStatement) connexion.prepareStatement(requete);

            ps.setString(1,pass);      
            ps.setInt(2,id);
           
            ps.executeUpdate();  
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
/*****************************************************************************/
    @Override
    public List<user> afficher() throws SQLException {
        List<user> users = new ArrayList<>();
        
        String req = "select * from users";
        stm =connexion.createStatement();
        
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            user u = new user(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8),rst.getString(9),

                    rst.getString(10),rst.getString(11),rst.getString(12)) ;
            users.add(u);
        }
        return users;       }
/************************************************************************/
    @Override
    public user afficherById(int id) throws SQLException {
        user u = null ;
        
        String req = "select * from users where id_user="+id;
        stm = connexion.createStatement();
        
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            u = new user(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),
                  rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8),rst.getString(9),

                  rst.getString(10),rst.getString(11),rst.getString(12)) ;
        }
        return u; 
    }
 /********************************************************************************/
    public user getUserByLogin(String log) {
        user u=null;
        try{
        String req="select * from users where login='"+log+"'";
        ps=connexion.prepareStatement(req);
        ResultSet rst=ps.executeQuery();
        if(rst.first()){
            u=new user(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),
                rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8),rst.getString(9),

                rst.getString(10),rst.getString(11),rst.getString(12)) ;
              }
        
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return u;
        }
        return u;
    }
/****************************************************************************************/
        public user getUserByEmail(String mail) {
        user u=null;
        try{
        String req="select * from users where email='"+mail+"'";
        ps=connexion.prepareStatement(req);
        ResultSet rst=ps.executeQuery();
        if(rst.first()){
            u=new user(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),
                rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8),rst.getString(9),

                rst.getString(10),rst.getString(11),rst.getString(12)) ;
              }
        
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return u;
        }
        return u;
    }
/*************************************************************************************/
    public static int existe(String attribut, String valeur){
        int nb=0;
        try{
            String req="select * from users where "+attribut+"='"+valeur+"'";
            ps=connexion.prepareStatement(req);
            ResultSet rst=ps.executeQuery();
              
            while (rst.next())
                nb++;
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return nb;
    }
/***********************************************************************************************/
        public static user search_user(String attribut, String valeur){
        user u=null;
        try{
            String req="select * from users where "+attribut+"='"+valeur+"'";
            ps=connexion.prepareStatement(req);
            ResultSet rst=ps.executeQuery();
              
            if(rst.first()){
            u=new user(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),
              rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8),rst.getString(9),
              rst.getString(10),rst.getString(11),rst.getString(12)) ;
              }
        
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return u;
        }
        return u;
    }
/***********************************************************************************************/
    public void sendEmail(String email, String code,String login) throws IOException {
        //  la session pour l'envoie d'un email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("depanini2023@gmail.com", "gpjhpzyjvtqycvlv");
            }
        });
        try {
            // Création de l'objet Message
            Message message = new MimeMessage(session);
            // from 
            message.setFrom(new InternetAddress("depanini2023@gmail.com"));
            // Recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            // Objet de l'email
            message.setSubject("Réinitialisation du mot de passe de votre compte Depanini");
        //////////////////////////////////////////////////////    
            message.setSentDate(new Date(System.currentTimeMillis()));
            String htmlBody = new String(Files.readAllBytes(Paths.get("src/mail.html"))).replace("123456",code);
            String htmlBody2 = new String(htmlBody).replace("XXYY",login);
          
            message.setContent(htmlBody2, "text/html");
                     
            //  Envoyer le message
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }
}