/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Produits {

    private int id_produits;
    private int id_user;
    public String nom_produits, categorie_produits;
    public Double prix_produits;
    public String description;
    // public Date date_creation;  déclarer bel new java.sql.Date
    public String image_produits;
    public int barecode;

    long millis = System.currentTimeMillis();
    java.sql.Date date_creation = new java.sql.Date(millis);

    public Produits( String nom_produits, String categorie_produits, Double prix_produits, String description,int id_produits) {
       
        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.id_produits = id_produits;
    }

    public Produits(int id_user, String nom_produits, String categorie_produits, Double prix_produits, String description, String image_produits, int barecode) {
        this.id_user = id_user;
        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.image_produits = image_produits;
        this.barecode = barecode;
    }
    
    
    

    public Produits(/*int id_produits,*/String nom_produits) {
        //  this.id_produits = id_produits;

        this.nom_produits = nom_produits;
    }

    public Produits(/*int id_produits,*/String nom_produits, String categorie_produits) {
        // this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
    }

    public Produits(/*int id_produits,*/String nom_produits, String categorie_produits, Double prix_produits) {
        // this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
    }

    public Produits(/*int id_produits,*/String nom_produits, String categorie_produits, Double prix_produits, String description) {
        //this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
    }

    public Produits(/*int id_produits,*/String nom_produits, String categorie_produits, Double prix_produits, String description, java.sql.Date date_creation) {
        //  this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.date_creation = date_creation;

    }

    public Produits(/*int id_produits,*/String nom_produits, String categorie_produits, Double prix_produits, String description, java.sql.Date date_creation, String image_produits) {
        // this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.date_creation = date_creation;
        this.image_produits = image_produits;

    }

  

    public Produits( String nom_produits, String categorie_produits, Double prix_produits, String description, java.sql.Date date_creation,int id_produits) {
        this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.date_creation = date_creation;
        //this.image_produits = image_produits;

    }
public Produits(int id_produits, String nom_produits, String categorie_produits, Double prix_produits, String description, java.sql.Date date_creation) {
        this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.date_creation = date_creation;
        //this.image_produits = image_produits;
    }

  
    public Produits(int id_produits, String nom_produits, String categorie_produits, Double prix_produits, String description, java.sql.Date date_creation, String image_produits) {
        this.id_produits = id_produits;

        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.date_creation = date_creation;
        this.image_produits = image_produits;
    }



    public Produits() {

    }

    public Produits(String nom, String categorie, double prix, String desc, String pic) {
      

        this.nom_produits = nom;
        this.categorie_produits = categorie;
        this.prix_produits = prix;
        this.description = desc; 
        this.image_produits = pic;
    }

    public Produits(int id_produits, String nom_produits, String categorie_produits, Double prix_produits, String description,java.sql.Date date_creation, String image_produits, int barecode) {
        this.id_produits = id_produits;
        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.date_creation = date_creation;
        this.barecode = barecode;
        this.image_produits = image_produits;
        
    }

    public Produits(int id_user,String nom, String categorie, double prix, String desc, String pic, int parseInt) {
     
        this.id_user = id_user;
        this.nom_produits = nom;
        this.categorie_produits = categorie;
        this.prix_produits = prix;
        this.description = desc; 
        this.image_produits = pic;
        this.barecode = parseInt;
    }

    public Produits(int id_produits, String nom_produits, String categorie_produits, Double prix_produits, String description, java.sql.Date date_creation, int barecode) {
        this.id_produits = id_produits;
        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
         this.date_creation = date_creation;
        this.barecode = barecode;
    }

    public Produits(int id_produits, int id_user, String nom_produits, String categorie_produits, Double prix_produits, String description, String image_produits, int barecode) {
        this.id_produits = id_produits;
        this.id_user = id_user;
        this.nom_produits = nom_produits;
        this.categorie_produits = categorie_produits;
        this.prix_produits = prix_produits;
        this.description = description;
        this.image_produits = image_produits;
        this.barecode = barecode;
    }

    
    
    

    

    
    

    

    public int getId_produits() {
        return id_produits;
    }

    public void setId_produits(int id_produits) {
        this.id_produits = id_produits;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    

    public String getNom_produits() {
        return nom_produits;
    }

    public void setNom_produits(String nom_produits) {
        this.nom_produits = nom_produits;
    }

    public String getCategorie_produits() {
        return categorie_produits;
    }

    public void setCategorie_produits(String categorie_produits) {
        this.categorie_produits = categorie_produits;
    }

    public String getImage() {
        return image_produits;
    }

    public void setImage(String path) {
        this.image_produits = path;
    }

    public String getImage_produits() {
        return image_produits;
    }
    
    

    public Double getPrix_produits() {
        return prix_produits;
    }

    public void setPrix_produits(Double prix_produits) {
        this.prix_produits = prix_produits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public int getBarecode() {
        return barecode;
    }

    public void setBarecode(int barecode) {
        this.barecode = barecode;
    }
    
//
//    public String getproduits(Produits Produits){
//     return nom_produits, Double.toString(prix_produits), description;
//        
//    }
    /**
     *
     * @param date_creation
     */
    public void setDate_creation(Date date_creation) {
        this.date_creation = (java.sql.Date) date_creation;
    }

    @Override
    public String toString() {
        return "\n---------\nproduits: " + "id_produits=" + id_produits
                + "id_user=" + id_user
                + ", nom_produits=" + nom_produits
                + "\n categorie=" + categorie_produits
                + ", prix=" + prix_produits
                + "\n description=" + description
                + ", date_creation=" + date_creation 
                
                + ", code à barre=" + barecode
                + "\n Image path=" + image_produits + "}";
    }

    
}