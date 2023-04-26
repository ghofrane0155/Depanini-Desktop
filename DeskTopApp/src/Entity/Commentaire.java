/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *

 * @author Mega-pca
 */
public class Commentaire {
    private int id_commentaire ;
    private int id_reclamation;
    private String editeur;
    private Date date;
    private String commentaire;

    public Commentaire(int id_commentaire, int id_reclamation, String editeur, Date date, String commentaire) {
        this.id_commentaire = id_commentaire;
        this.id_reclamation = id_reclamation;
        this.editeur = editeur;
        this.date = date;
        this.commentaire = commentaire;
    }

    public Commentaire(int id_reclamation, String editeur, Date date, String commentaire) {
        this.id_reclamation = id_reclamation;
        this.editeur = editeur;
        this.date = date;
        this.commentaire = commentaire;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {

        return "\nCommentaire{" + "id_commentaire=" + id_commentaire + ", id_reclamation=" + id_reclamation + ", editeur=" + editeur + ", date=" + date + ", commentaire=" + commentaire + "}\n";

    }


    
}
