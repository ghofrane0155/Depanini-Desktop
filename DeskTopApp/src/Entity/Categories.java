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
public class Categories {
    
    private int id_categorie;
    private String nom_categorie;
    private String image_categorie;

    public Categories() {
    }

    public Categories(int id_categorie, String nom_categorie, String image_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.image_categorie = image_categorie;
    }

    public Categories(String nom_categorie, String image_categorie) {
        this.nom_categorie = nom_categorie;
        this.image_categorie = image_categorie;
    }

    public Categories(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*************************************************/
    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public String getImage_categorie() {
        return image_categorie;
    }
/*************************************************/
    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setImage_categorie(String image_categorie) {
        this.image_categorie = image_categorie;
    }
/*************************************************/
    @Override
    public String toString() {
        return "Categories{" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + ", image_categorie=" + image_categorie +"\n}";
    }


}