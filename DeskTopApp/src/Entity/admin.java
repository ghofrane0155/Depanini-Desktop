/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import Entity.Enum.*;

/**
 *
 * @author Ghof
 */
public class admin {

    private int id_admin;
    private String login_admin;
    private String mdp_admin;
    private String nom_admin;
    private String prenom_admin;
    private Date date_nais_admin;
    private String photo_admin;
    private Sexe sexe_admin;

    public admin() {
    }

    public admin(int id_admin, String login_admin, String mdp_admin, String nom_admin, String prenom_admin, Date date_nais_admin, String photo_admin, Sexe sexe_admin) {

        this.id_admin = id_admin;
        this.login_admin = login_admin;
        this.mdp_admin = mdp_admin;
        this.nom_admin = nom_admin;
        this.prenom_admin = prenom_admin;
        this.date_nais_admin = date_nais_admin;
        this.photo_admin = photo_admin;
        this.sexe_admin = sexe_admin;

    }

    public admin(String login_admin, String mdp_admin, String nom_admin, String prenom_admin, Date date_nais_admin, String photo_admin, Sexe sexe_admin) {
        this.login_admin = login_admin;
        this.mdp_admin = mdp_admin;
        this.nom_admin = nom_admin;
        this.prenom_admin = prenom_admin;
        this.date_nais_admin = date_nais_admin;
        this.photo_admin = photo_admin;
        this.sexe_admin = sexe_admin;
    }

    /**
     * ********************************************************
     */
    public int getId_admin() {
        return id_admin;
    }

    public String getLogin_admin() {
        return login_admin;
    }

    public String getMdp_admin() {
        return mdp_admin;
    }

    public String getNom_admin() {
        return nom_admin;
    }

    public String getPrenom_admin() {
        return prenom_admin;
    }

    public Date getDate_nais_admin() {
        return date_nais_admin;
    }

    public String getPhoto_admin() {
        return photo_admin;
    }

    public Sexe getSexe_admin() {
        return sexe_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setLogin_admin(String login_admin) {
        this.login_admin = login_admin;
    }

    public void setMdp_admin(String mdp_admin) {
        this.mdp_admin = mdp_admin;
    }

    public void setNom_admin(String nom_admin) {
        this.nom_admin = nom_admin;
    }

    public void setPrenom_admin(String prenom_admin) {
        this.prenom_admin = prenom_admin;
    }

    public void setDate_nais_admin(Date date_nais_admin) {
        this.date_nais_admin = date_nais_admin;
    }

    public void setPhoto_admin(String photo_admin) {
        this.photo_admin = photo_admin;
    }

    public void setSexe_admin(Sexe sexe_admin) {
        this.sexe_admin = sexe_admin;
    }

    /**
     * ********************************************************
     */
    @Override

    public String toString() {
        return "admin{" + "id_admin=" + id_admin + ", login_admin=" + login_admin + ", mdp_admin=" + mdp_admin + ", nom_admin=" + nom_admin + ", prenom_admin=" + prenom_admin + ", date_nais_admin=" + date_nais_admin + ", photo_admin=" + photo_admin + ", sexe_admin=" + sexe_admin + '}';
        
    }
}