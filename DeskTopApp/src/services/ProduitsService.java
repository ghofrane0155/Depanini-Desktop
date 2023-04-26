/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Produits;
import java.sql.Connection;
//import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;


/**
 *
 * @author MSI
 */
public class ProduitsService implements Iproduits<Produits> {
//    Connection connexion;
//    Statement stm;

  
      Connection connexion = MyDB.getInstance().getConx();
    

    /**
     *
     * @param p 
     */
    
    
    
    @Override
   public void ajouterP(Produits p) {
        
        try {
            Statement stmt = connexion.createStatement(); //auto increment
        
//        stmt.execute("alter table produits AUTO_INCREMENT = 1"); //auto increment
        String req = "INSERT INTO `produits` (`nom_produit`, `categorie_produit`, `prix_produit`, `description`, `date_creation`, `image_produit`, `barecode`) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement pr;
        pr = connexion.prepareStatement(req,Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys teeb3a l auto increment
        // pr.setInt(1, p.getId_produits());
        pr.setString(1, p.getNom_produits());
        pr.setString(2, p.getCategorie_produits());
        pr.setDouble(3, p.getPrix_produits());
        pr.setString(4, p.getDescription()); //normalement text
        pr.setDate(5, (java.sql.Date) p.getDate_creation());     
        pr.setString(6, p.getImage());
        pr.setInt(7, p.getBarecode());
       // pr.addBatch();
        System.out.println(pr);
        pr.executeUpdate();
        System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 

    /**
     *
     * @return
     * @throws SQLException
     */
      @Override
    public List<Produits> afficherproduits() throws SQLException {
        List<Produits> prods = new ArrayList<>();
        String req = "select * from produits";
      Statement  stm = connexion.createStatement();
        ResultSet res = stm.executeQuery(req);
        
        
        while(res.next()) {
            Produits p;
            p = new Produits(res.getInt("id_produit"),
                    res.getString("nom_produit"),
                    res.getString("categorie_produit"), 
                    res.getDouble("prix_produit"), 
                    res.getString("description"),                       
                    res.getDate("date_creation"),
                    res.getString("image_produit"),
                    res.getInt("barecode")
                     
                    
            );        
                    
           
            prods.add(p);
        }
        
        return prods;
    }


    /**
     *
     * @param p
     * @throws SQLException
     */
    public void modifierproduit(Produits p) throws SQLException {
       try{
           
//           "UPDATE  Produits SET `nom_produit`=?, `categorie_produit`=?, `prix_produit`=?,`description`=?, `date_creation`=?, `image_produit`=? WHERE `id_produit`= ? "
         String req = "UPDATE  produits SET `nom_produit`=?, `categorie_produit`=?, `prix_produit`=?,`description`=?, `date_creation`=? WHERE `id_produit`= ? " ;
            
        PreparedStatement pr = connexion.prepareStatement(req);
       
        pr.setString(1, p.getNom_produits());
        pr.setString(2, p.getCategorie_produits());
        pr.setDouble(3, p.getPrix_produits());
        pr.setString(4, p.getDescription()); //normalement text
        pr.setDate(5, new    java.sql.Date(p.getDate_creation().getTime()));
//        pr.setString(6, p.getImage());
         pr.setInt(6, p.getId_produits());
           System.out.println(pr);
        
        pr.executeUpdate();
        System.out.println("Product Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     } 
   
    
    
     public void supprimerproduit(int id_produits) throws SQLException {
            try {
            String req = "DELETE FROM produits WHERE `id_produit`=?";
            PreparedStatement pr = connexion.prepareStatement(req);
            pr.setInt(1, id_produits);
            pr.executeUpdate();
            System.out.println("Product deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
   /*  public List<produits> chercher(String nom) throws SQLException {
         
        List<produits> Produits = new ArrayList<>();
        String req = "select * from Produits WHERE `nom_produits`=? ";
        stm = connexion.createStatement();
        ResultSet res = stm.executeQuery(req);
         while(res.next()) {
            Produits p;
            p = new Produits(res.getInt("id_produits"),
                 //  res.getInt("id_user"),
                    res.getString("nom_produits"),
                    res.getString("categorie_produits"), 
                    res.getDouble("prix_produits"), 
                    res.getString("description"),                       
                    res.getDate("date_creation"),
                    res.getString("image_produits") 
                    
            );
            Produits.add(p);
        }
        
        return Produits;
    } */

    /**
     *
     * @return
     * @throws SQLException
     */


}