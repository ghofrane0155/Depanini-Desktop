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
public class Services {
    
    private int id_service;
    private String nom_service;
    private String prix_service;
    private String description_service;

    public Services() {
    }
    

 
    public Services(int id_service, String nom_service, String prix_service, String description_service) {
        this.id_service = id_service;
        this.nom_service = nom_service;
        this.prix_service = prix_service;
        this.description_service = description_service;
    }

    public Services(String nom_service, String prix_service, String description_service) {
        this.nom_service = nom_service;
        this.prix_service = prix_service;
        this.description_service = description_service;
    }
    
    
    
    public int getId_service() {
        return id_service;
    }

    public String getNom_service() {
        return nom_service;
    }

    public String getPrix_service() {
        return prix_service;
    }

 
    public String getDescription_service() {
        return description_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public void setPrix_service(String prix_service) {
        this.prix_service = prix_service;
    }

    public void setDescription_service(String description_service) {
        this.description_service = description_service;
    }

    

    @Override
    public String toString() {
        return "Services{" + "id_service=" + id_service + ", nom_service=" + nom_service+ ", prix_service=" + prix_service + ", description_service=" + description_service+  "\n}";
    }


}
