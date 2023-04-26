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
public class Contrats {
    int IDContrat;
    int cin;
    String nom_offre;
    String date;
    String description;
    double price;

    public Contrats(int cin) {
        this.cin = cin;
    }

    public Contrats(String nom_offre,String date,  String description,Double price,int cin) {
        this.cin = cin;
                this.price = price;
        this.nom_offre = nom_offre;
        this.date = date;
        this.description = description;
    }
       public Contrats(int cin,String date,String nom_offre,  String description) {
        this.cin = cin;
        this.nom_offre = nom_offre;
        this.date = date;
        this.description = description;
    }

    public Contrats(int IDContrat, int cin, String date, String nom_offre, String description) {
        this.IDContrat=IDContrat;
         this.cin = cin;
        this.nom_offre = nom_offre;
        this.date = date;
        this.description = description;    }

    public Contrats(int IDContrat, int cin) {
         this.IDContrat=IDContrat;
         this.cin = cin;
    }

    public int getIDContrat() {
        return IDContrat;
    }

    public void setIDContrat(int IDContrat) {
        this.IDContrat = IDContrat;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Contrats(int IDContrat, int cin, String nom_offre, String date, String description, double price) {
        this.IDContrat = IDContrat;
        this.cin = cin;
        this.nom_offre = nom_offre;
        this.date = date;
        this.description = description;
        this.price = price;
    }

    public Contrats(int cin, String nom_offre, String date, String description, double price) {
        this.cin = cin;
        this.nom_offre = nom_offre;
        this.date = date;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Contrats{" + "IDContrat=" + IDContrat + ", cin=" + cin + ", nom_offre=" + nom_offre + ", date=" + date + ", description=" + description + ", price=" + price + '}';
    }



    

}
