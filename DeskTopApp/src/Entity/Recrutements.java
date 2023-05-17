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
public class Recrutements {
    private int reference ;
    private int idClient ;
    private float salaire ;
    private String date ;
    private String nom ;
    private String description ; 
    
        public Recrutements(int reference,int idClient, float salaire, String description, String date, String nom) {
        this.reference = reference;
        this.idClient = idClient;
        this.salaire = salaire;
        this.description = description;
        this.date = date;
        this.nom = nom;

    }
        public Recrutements(int idClient, float salaire, String description, String date,String nom) {
        this.idClient = idClient;
        this.salaire = salaire;
        this.description = description;
        this.date = date;
        this.nom = nom;
    }

    public Recrutements(String nom, float salaire, String date, String description) {
        this.salaire = salaire;
        this.description = description;
        this.date = date;
        this.nom = nom;    }

    public Recrutements(String nom, float salaire, String description) {
 this.salaire = salaire;
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

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
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
        return "Demande{" + "reference=" + reference + ", idClient=" + idClient + ", salaire=" + salaire + ", date=" + date + ", description=" + description + '}';
    }




    
    
  
    
}
