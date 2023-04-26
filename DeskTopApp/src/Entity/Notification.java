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
public class Notification {

    private int id_notification;
    private int id_feedback;
    private Date date_notification;
    private String contenu_notification;
    private Date marked_as_read;

    private String name_user;
    private Double nb_stars;

    public Notification(int id_notification, String name_user, Double nb_stars) {
        this.id_notification = id_notification;
        this.name_user = name_user;
        this.nb_stars = nb_stars;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public Double getNb_stars() {
        return nb_stars;
    }

    public void setNb_stars(Double nb_stars) {
        this.nb_stars = nb_stars;
    }
    
    
    
    public Notification(int id_feedback, Date date_notification, String contenu_notification) {
        this.id_feedback = id_feedback;
        this.date_notification = date_notification;
        this.contenu_notification = contenu_notification;
    }

    public Notification(int id_notification, int id_feedback, Date date_notification, String contenu_notification, Date marked_as_read) {
        this.id_notification = id_notification;
        this.id_feedback = id_feedback;
        this.date_notification = date_notification;
        this.contenu_notification = contenu_notification;
        this.marked_as_read = marked_as_read;
    }

    public Notification(int id_notification, int id_feedback, Date date_notification, String contenu_notification) {
       this.id_notification = id_notification;
        this.id_feedback = id_feedback;
        this.date_notification = date_notification;
        this.contenu_notification = contenu_notification;
    }

    
    
    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public Date getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(Date date_notification) {
        this.date_notification = date_notification;
    }

    public String getContenu_notification() {
        return contenu_notification;
    }

    public void setContenu_notification(String contenu_notification) {
        this.contenu_notification = contenu_notification;
    }

    public Date getMarked_as_read() {
        return marked_as_read;
    }

    public void setMarked_as_read(Date marked_as_read) {
        this.marked_as_read = marked_as_read;
    }

    @Override
    public String toString() {
        return "Notification{" + "id_notification=" + id_notification + ", id_feedback=" + id_feedback + ", date_notification=" + date_notification + ", contenu_notification=" + contenu_notification + ", marked_as_read=" + marked_as_read + '}';
    }

    
}
