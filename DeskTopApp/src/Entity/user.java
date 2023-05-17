package Entity;

import java.util.Date;
import Entity.Enum.*;

/**
 *
 * @author Ghof
 */
public class user {
    private int id_user;
    private String nom_user;
    private String prenom_user;
    private String login;
    private String password;
    private Date date_nais_user;
    private String email;
    private String adresse;
    private String tel;
    private String sexe;
    private String roles;
    private String photo_user;
   // private Session session;
    
    public static user Current_User;

    public user() {
    }

    public user(int id_user, String nom_user, String prenom_user, String login, String password, Date date_nais_user, String email, String adresse, String tel, String sexe,String roles, String photo_user) {
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.login = login;
        this.password = password;
        this.date_nais_user = date_nais_user;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.sexe = sexe;
        this.roles = roles;
        this.photo_user = photo_user;
    }

    public user(String nom_user, String prenom_user, String login, String password, Date date_nais_user, String email, String adresse, String tel, String sexe,String roles, String photo_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.login = login;
        this.password = password;
        this.date_nais_user = date_nais_user;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.sexe = sexe;
        this.roles = roles;
        this.photo_user = photo_user;
    }

    public user(String nom_user, String prenom_user, String login, String password, Date date_nais_user, String email, String adresse, String tel, String sexe,String roles) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.login = login;
        this.password = password;
        this.date_nais_user = date_nais_user;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.sexe = sexe;
        this.roles = roles;
    }
    
/****************************************************/
    public int getId_user() {
        return id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getSexe() {
        return sexe;
    }

    public Date getDate_nais_user() {
        return date_nais_user;
    }

    public String getRoles() {
        return roles;
    }

    public String getPhoto_user() {
        return photo_user;
    }

    public static user getCurrent_User() {
        return Current_User;
    }
    
/*******************************************************/
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDate_nais_user(Date date_nais_user) {
        this.date_nais_user = date_nais_user;
    }


    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPhoto_user(String photo_user) {
        this.photo_user = photo_user;
    }

    public static void setCurrent_User(user Current_User) {
        user.Current_User = Current_User;
    }
    
/*************************************************************/
    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", login=" + login + ", password=" + password + ", date_nais_user=" + date_nais_user + ", email=" + email + ", adresse=" + adresse + ", tel=" + tel + ", sexe=" + sexe + ", roles=" + roles + ", photo_user=" + photo_user + '}';
    }
/************************************************************/ 
           
}