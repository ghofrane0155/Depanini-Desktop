/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author noure
 */
public class Event {
    
    private int IDEvent;
    private String NomEvent;
    private int NombreLimEvent;
    private String LieuEvent , OrganisateurEvent ;
    private String DateDabEvent ,DateFinEvent;//a modifier date 
    private String DescriptionEvent;
    private String ImageEvent;
    private int prixEvent;

    public Event(int IDEvent, String NomEvent, int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent, String ImageEvent, int prixEvent) {
        this.IDEvent = IDEvent;
        this.NomEvent = NomEvent;
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.ImageEvent = ImageEvent;
        this.prixEvent = prixEvent;
    }

    public int getPrixEvent() {
        return prixEvent;
    }
    
    

    public Event() {
    }

    public Event(String NomEvent, int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent, String ImageEvent, int prixEvent) {
        this.NomEvent = NomEvent;
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.ImageEvent = ImageEvent;
        this.prixEvent = prixEvent;
    }

    public Event(int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent, String ImageEvent, int prixEvent) {
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.ImageEvent = ImageEvent;
        this.prixEvent = prixEvent;
    }

    public Event(int IDEvent, String NomEvent, String ImageEvent) {
        this.IDEvent = IDEvent;
        this.NomEvent = NomEvent;
        this.ImageEvent = ImageEvent;
    }

    public Event(int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent, int prixEvent) {
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.prixEvent = prixEvent;
    }
    
    

    public Event(int IDEvent, String NomEvent, int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent) {
        this.IDEvent = IDEvent;
        this.NomEvent = NomEvent;
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

    
    
    public Event(int IDEvent, String NomEvent, int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent, String ImageEvent) {
        this.IDEvent = IDEvent;
        this.NomEvent = NomEvent;
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.ImageEvent = ImageEvent;
    }

    public Event(String NomEvent, int NombreLimEvent, String LieuEvent, String OrganisateurEvent, String DateDabEvent, String DateFinEvent, String DescriptionEvent) {
        this.NomEvent = NomEvent;
        this.NombreLimEvent = NombreLimEvent;
        this.LieuEvent = LieuEvent;
        this.OrganisateurEvent = OrganisateurEvent;
        this.DateDabEvent = DateDabEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

   

  

    public int getIDEvent() {
        return IDEvent;
    }

    public String getNomEvent() {
        return NomEvent;
    }

    public int getNombreLimEvent() {
        return NombreLimEvent;
    }

    public String getLieuEvent() {
        return LieuEvent;
    }

    public String getOrganisateurEvent() {
        return OrganisateurEvent;
    }

    public String getDateDabEvent() {
        return DateDabEvent;
    }

    public String getDateFinEvent() {
        return DateFinEvent;
    }

    public String getDescriptionEvent() {
        return DescriptionEvent;
    }

    public String getImageEvent() {
        return ImageEvent;
    }

    public void setIDEvent(int IDEvent) {
        this.IDEvent = IDEvent;
    }

    public void setNomEvent(String NomEvent) {
        this.NomEvent = NomEvent;
    }

    public void setNombreLimEvent(int NombreLimEvent) {
        this.NombreLimEvent = NombreLimEvent;
    }

    public void setLieuEvent(String LieuEvent) {
        this.LieuEvent = LieuEvent;
    }

    public void setOrganisateurEvent(String OrganisateurEvent) {
        this.OrganisateurEvent = OrganisateurEvent;
    }

    public void setDateDabEvent(String DateDabEvent) {
        this.DateDabEvent = DateDabEvent;
    }

    public void setDateFinEvent(String DateFinEvent) {
        this.DateFinEvent = DateFinEvent;
    }

    public void setDescriptionEvent(String DescriptionEvent) {
        this.DescriptionEvent = DescriptionEvent;
    }

    public void setImageEvent(String ImageEvent) {
        this.ImageEvent = ImageEvent;
    }

    @Override
    public String toString() {
        return "Event{" + "IDEvent=" + IDEvent + ", NomEvent=" + NomEvent + ", NombreLimEvent=" + NombreLimEvent + ", LieuEvent=" + LieuEvent + ", OrganisateurEvent=" + OrganisateurEvent + ", DateDabEvent=" + DateDabEvent + ", DateFinEvent=" + DateFinEvent + ", DescriptionEvent=" + DescriptionEvent + ", ImageEvent=" + ImageEvent + '}';
    }
     
    
   

   
}