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
public class Feedback {
    
    private String nom_user;
    private int id_feedback;
    private int id_freelancer;
    private int id_client;
    private String commentaire ;
    private Date date;
    private Double stars;

    public Feedback(String nom_user, Double stars) {
        this.nom_user = nom_user;
        this.stars = stars;
    }
    
    public Feedback(int id_feedback, int id_freelancer, int id_client, String commentaire, Date date, Double stars) {
        this.id_feedback = id_feedback;
        this.id_freelancer = id_freelancer;
        this.id_client = id_client;
        this.commentaire = commentaire;
        this.date = date;
        this.stars = stars;
    }
    public Feedback(int id_freelancer, int id_client, String commentaire, Date date, Double stars) {
        this.id_freelancer = id_freelancer;
        this.id_client = id_client;
        this.commentaire = commentaire;
        this.date = date;
        this.stars = stars;
    }

    
    @Override
    public String toString() {
        return "Feedback{" + "nom_user=" + nom_user + ", stars=" + stars + '}';
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }
    
}
