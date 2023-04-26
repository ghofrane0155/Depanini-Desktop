/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Commandes;
import java.sql.Connection;
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
public class CommandeService implements ICommande<Commandes> {
    
  
  
      Connection connexion = MyDB.getInstance().getConx();
    

    /**
     *
     * @param c
     * @throws SQLException 
     */
    @Override
    public void ajouter(Commandes c) throws SQLException {
        try {
            
        
           Statement stm = connexion.createStatement(); //auto increment  
        stm.execute("alter table commandes AUTO_INCREMENT = 1");
//        req = "INSERT INTO `commandes` VALUES "
//                + "( '" + c.getId_produit()+ "', '"+ c.getId_user()+ "',"
//                + "') ";
        String req = "INSERT INTO `commandes` (`id_produit`, `id_user`, `date_commande`) VALUES (?, ?, ?) ";

        PreparedStatement pr;
        pr = connexion.prepareStatement(req,Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys teeb3a l auto increment
        
        pr.setInt(1, c.getId_produit());
        pr.setInt(2, c.getId_user());
        pr.setDate(3, (java.sql.Date) c.getDate_commande()); 
        System.out.println(pr);
        pr.executeUpdate();
        System.out.println("ajout avec succes");
        
        } catch (Exception e) {
        }
        
    }


    @Override
    public List<Commandes> afficher() throws SQLException {
        List<Commandes> commande = new ArrayList<>();
        String req = "select * from commandes";
       Statement stm = connexion.createStatement();
        ResultSet res = stm.executeQuery(req);
        
        while(res.next()) {
            Commandes c;
            c = new Commandes(res.getInt("id_commande"),
                    res.getInt("id_produit"),
                    res.getInt("id_user")
            );
            commande.add(c);
        }
        
        return commande;
    }

    /**
     *
     * @param c
     * @throws SQLException
     */
    public void modifier(Commandes c) throws SQLException {
       try{
         String req = "UPDATE  produit SET `id_produit`=?, `id_user`=?, WHERE `id_commande`= ? " ;
            
        PreparedStatement pr = connexion.prepareStatement(req);
       
         pr.setInt(1, c.getId_produit());
         pr.setInt(2, c.getId_user());
         pr.setInt(3, c.getId_commande());
           System.out.println(pr);
        
        pr.executeUpdate();
        System.out.println("Product Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     } 
   
    
    
     public void supprimer(int id_produit) throws SQLException {
            try {
            String req = "DELETE FROM commandes WHERE `id_produit`=?";
            PreparedStatement pr = connexion.prepareStatement(req);
            pr.setInt(1, id_produit);
            pr.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

    
    
    
    
    







