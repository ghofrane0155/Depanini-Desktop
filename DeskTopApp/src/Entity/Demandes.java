/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author yasmine
 */
public class Demandes {
    private int reference ;
    private int idClient ;
    private float budget ;
    private String date ;
    private String nom ;
    private String description ; //description / cahier des charges (importer pdf)
    
        public Demandes(int reference,int idClient, float budget, String description, String date, String nom) {
        this.reference = reference;
        this.idClient = idClient;
        this.budget = budget;
        this.description = description;
        this.date = date;
        this.nom = nom;

    }
        public Demandes(int idClient, float budget, String description, String date,String nom) {
        this.idClient = idClient;
        this.budget = budget;
        this.description = description;
        this.date = date;
        this.nom = nom;
    }

    public Demandes(String nom, float budget, String date, String description) {
        this.budget = budget;
        this.description = description;
        this.date = date;
        this.nom = nom;    }

    public Demandes(String nom, float budget, String description) {
 this.budget = budget;
        this.description = description;
        this.nom = nom;    }
    
            public int getRef() {
        return reference;
    }

    public void setRef(int reference) {
        this.reference = reference;
    }

    public int getID() {
        return idClient;
    }

    public void setID(int idClient) {
        this.idClient = idClient;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
    
        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Demande{" + "reference=" + reference + ", idClient=" + idClient + ", budget=" + budget + ", date=" + date + ", description=" + description + '}';
    }




    
    
  
    
}
