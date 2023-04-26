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
public class Commandes {
    
    
    private  int id_produit;
    private  int id_user;
    private  int id_commande;
    
    long millis = System.currentTimeMillis();
    java.sql.Date date_commande = new java.sql.Date(millis);

    public Commandes( int id_produit,int id_user, int id_commande, java.sql.Date date_commande) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.date_commande = date_commande;
    }

    public Commandes( int id_produit,int id_user, java.sql.Date date_commande ) {
        this.id_user = id_user;
        this.id_produit = id_produit;
         this.date_commande = date_commande;
    }

    public Commandes( int id_produit ,int id_user) {
        this.id_user = id_user;
        this.id_produit = id_produit;
    }
    
    
    

    /*  public Commandes(int aInt, int aInt0, int aInt1) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public Commandes(int id_user, int id_produit, int id_commande) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.id_commande = id_commande;
    }

    
   
    

    public int getId_user() {
        return id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public long getMillis() {
        return millis;
    }
    
    public java.sql.Date getDate_commande() {
    return date_commande;
    }

    @Override
    public String toString() {
        return "\n---------\nCommande{" + " id_produit=" + id_produit 
                + ", id_user=" + id_user 
                +  ", id_commande=" + id_commande 
                + ", date_commande=" + date_commande + "}";
    }

  
}
