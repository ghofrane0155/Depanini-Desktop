/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Mega-pc
 */
public class Reclamation {

    private String nomUtilisateur;
    private int id_reclamation;   
    private int id_user;
    private int id_admin;
    private String type;
    private String description;
    private Date date_reclamation;
    private String piece_jointe;
    private Statut statut;    
    private Date date_resolution;

    public Reclamation(String nomUtilisateur,int id_user ,String type,Statut statut,Date date_reclamation,int id_reclamation,String description,String piece_jointe ) {
        this.nomUtilisateur = nomUtilisateur;
        this.id_user = id_user;
        this.type = type;
        this.date_reclamation = date_reclamation;
        this.statut = statut;
        this.id_reclamation = id_reclamation;
        this.description = description;
        this.piece_jointe = piece_jointe;
    }
  
    public Reclamation(String type, String description, Date date_reclamation, String piece_jointe) {
        this.type = type;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.piece_jointe = piece_jointe;
    }

    public Reclamation(int id_reclamation, int id_user, int id_admin, String type, String description, Date date_reclamation, String piece_jointe, Statut statut, Date date_resolution) {
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.id_admin = id_admin;
        this.type = type;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.piece_jointe = piece_jointe;
        this.statut = statut;
        this.date_resolution = date_resolution;
    }

    
    public Reclamation(int id_user, int id_admin, String type, String description, Date date_reclamation, String piece_jointe, Statut statut, Date date_resolution) {
        this.id_user = id_user;
        this.id_admin = id_admin;
        this.type = type;
        this.description = description;
        this.date_reclamation = date_reclamation;

        this.piece_jointe = piece_jointe;
        this.statut = statut;
        this.date_resolution = date_resolution;
    }

    public Reclamation(int id_reclamation,int id_user ,Statut statut, Date date_resolution) {
        this.id_user = id_user;
       this.id_reclamation = id_reclamation;
       this.statut = statut;
        this.date_resolution = date_resolution;
    }
    
        public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getPiece_jointe() {
        return piece_jointe;
    }

    public void setPiece_jointe(String piece_jointe) {
        this.piece_jointe = piece_jointe;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }


    public Date getDate_resolution() {
        return date_resolution;
    }

    public void setDate_resolution(Date date_resolution) {
        this.date_resolution = date_resolution;
    }

    public Reclamation(int id_user, String type, String description, Date date_reclamation, String piece_jointe) {
        this.id_user = id_user;
        this.type = type;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.piece_jointe = piece_jointe;
    }

    @Override
    public String toString() {
        return "\nReclamation{" + "id_reclamation=" + id_reclamation + ", id_user=" + id_user + ", id_admin=" + id_admin + ", type=" + type + ", description=" + description + ", date_reclamation=" + date_reclamation + ", piece_jointe=" + piece_jointe + ", statut=" + statut + ", date_resolution=" + date_resolution + "\n}";
    }


}