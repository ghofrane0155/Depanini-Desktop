/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author leila
 */
public class Offres {
    
    private int id_offre;
    private int id_categorie;
    private int id_user;
    private double prix_offre ;
    private String description_offre;
    private String location_offre;
    private String nom_offre;
    private String image_offre;
    private String type_offre;
    

    public Offres() {
    }

    public Offres(int id_offre, int id_categorie ,int id_user, double prix_offre, String description_offre, String location_offre, String nom_offre,String image_offre, String type_offre) {
        this.id_offre = id_offre;
        this.id_categorie=id_categorie;
        this.id_user = id_user;
        this.prix_offre = prix_offre;
        this.description_offre = description_offre;
        this.location_offre = location_offre;
        this.nom_offre = nom_offre;
        this.type_offre = type_offre;
        this.image_offre = image_offre;
        
    }

    public Offres(int id_user, double prix_offre, String description_offre, String location_offre, String nom_offre,String image_offre, String type_offre) {
        this.id_user = id_user;
       
        this.prix_offre = prix_offre;
        this.description_offre = description_offre;
        this.location_offre = location_offre;
        this.nom_offre = nom_offre;
        this.type_offre = type_offre;
        this.image_offre = image_offre;
      
    }

    public int getId_offre() {
        return id_offre;
    }

    public int getId_user() {
        return id_user;
    }
        public int getId_categorie() {
        return id_categorie;
    }
    
    public double getPrix_offre() {
        return prix_offre;
    }

    public String getDescription_offre() {
        return description_offre;
    }

    public String getLocation_offre() {
        return location_offre;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public String getType_offre() {
        return type_offre;
    }

    public String getImage_offre() {
        return image_offre;
    }

  

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }


    public void setPrix_offre(double prix_offre) {
        this.prix_offre = prix_offre;
    }

    public void setDescription_offre(String description_offre) {
        this.description_offre = description_offre;
    }

    public void setLocation_offre(String location_offre) {
        this.location_offre = location_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public void setType_offre(String type_offre) {
        this.type_offre = type_offre;
    }

    public void setImage_offre(String image_offre) {
        this.image_offre = image_offre;
    }

   
    
    @Override
    public String toString() {
        return "Offres{" + "id_offre=" + id_offre + " ,id_categorie=" + id_categorie + ", id_user=" + id_user + ", prix_offre=" + prix_offre + ", description_offre=" + description_offre + ", location_offre=" + location_offre + ", nom_offre=" + nom_offre + ", type_offre=" + type_offre + ", image_offre=" + image_offre +  '}';
    }
    


}
