/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.metiersUser;

import Entity.Enum;
import java.util.Date;


/**
 *
 * @author Ghof
 */
public final class SessionUser {
    private static SessionUser instance;
    
    private static int id_user;
    private static String nom_user;
    private static String prenom_user;
    private static String login;
    private static String mdp;
    private static Date date_nais_user;
    private static String email;
    private static String adresse;
    private static String tel;
    private static Entity.Enum.Sexe sexe;
    private static Entity.Enum.TypeR role;
    private static String photo_user;

    private SessionUser(int id_user, String nom_user, String prenom_user, String login, String mdp, Date date_nais_user, String email, String adresse, String tel, Enum.Sexe sexe, Enum.TypeR role, String photo_user) {
        SessionUser.id_user = id_user;
        SessionUser.nom_user = nom_user;
        SessionUser.prenom_user = prenom_user;
        SessionUser.login = login;
        SessionUser.mdp = mdp;
        SessionUser.date_nais_user = date_nais_user;
        SessionUser.email = email;
        SessionUser.adresse = adresse;
        SessionUser.tel = tel;
        SessionUser.sexe = sexe;
        SessionUser.role = role;
        SessionUser.photo_user = photo_user;
    }
/********************************************************************************/
    public static SessionUser getInstace(int id_user, String nom_user, String prenom_user, String login, String mdp, Date date_nais_user, String email, String adresse, String tel, Enum.Sexe sexe, Enum.TypeR role, String photo_user) {
        if(instance == null) {
            instance = new SessionUser(id_user,nom_user,prenom_user,login,mdp,date_nais_user,email,adresse,tel,sexe,role,photo_user);
        }
        return instance;
    } 
/*******************************************************************************/
    public static SessionUser getInstance() {
        return instance;
    }

    public static int getId_user() {
        return id_user;
    }

    public static String getNom_user() {
        return nom_user;
    }

    public static String getPrenom_user() {
        return prenom_user;
    }

    public static String getLogin() {
        return login;
    }

    public static String getMdp() {
        return mdp;
    }

    public static Date getDate_nais_user() {
        return date_nais_user;
    }

    public static String getEmail() {
        return email;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static String getTel() {
        return tel;
    }

    public static Enum.Sexe getSexe() {
        return sexe;
    }

    public static Enum.TypeR getRole() {
        return role;
    }

    public static String getPhoto_user() {
        return photo_user;
    }

/**************************************************************/
    public static void setInstance(SessionUser instance) {
        SessionUser.instance = instance;
    }

    public static void setId_user(int id_user) {
        SessionUser.id_user = id_user;
    }

    public static void setNom_user(String nom_user) {
        SessionUser.nom_user = nom_user;
    }

    public static void setPrenom_user(String prenom_user) {
        SessionUser.prenom_user = prenom_user;
    }

    public static void setLogin(String login) {
        SessionUser.login = login;
    }

    public static void setMdp(String mdp) {
        SessionUser.mdp = mdp;
    }

    public static void setDate_nais_user(Date date_nais_user) {
        SessionUser.date_nais_user = date_nais_user;
    }

    public static void setEmail(String email) {
        SessionUser.email = email;
    }

    public static void setAdresse(String adresse) {
        SessionUser.adresse = adresse;
    }

    public static void setTel(String tel) {
        SessionUser.tel = tel;
    }

    public static void setSexe(Enum.Sexe sexe) {
        SessionUser.sexe = sexe;
    }

    public static void setRole(Enum.TypeR role) {
        SessionUser.role = role;
    }

    public static void setPhoto_user(String photo_user) {
        SessionUser.photo_user = photo_user;
    }


/**************************************************************/
        @Override
    public String toString() {
         return "UserSession{" +
                "login='" + login + '\'' +
                "email='" + email + '\'' +
               
                "id_user = '" + id_user + '\'' +
           
                ", privileges=" + role +
                
            '}';
    }
/**************************************************************/
    public static void cleanUserSession() {
      SessionUser.setInstance(null);
    }
  /**************************************************************/
  
}
