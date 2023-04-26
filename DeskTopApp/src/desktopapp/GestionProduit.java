/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import Entity.Commandes;
import Entity.Produits;
import java.sql.SQLException;
import services.CommandeService;
//import java.util.Date;
import services.ProduitsService;
import Utils.MyDB;

/**
 *
 * @author MSI
 */
public class GestionProduit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    //Date  date = new Date(1220227200L);
        MyDB db1 = MyDB.getInstance();
    Produits p;
        p = new Produits("site","tech",3.2,"new product");
        Produits p1;
        p1 = new Produits("siteeeee","pres",2.1,"produit");
      
        
       
  
        ProduitsService ps = new ProduitsService() ;
            
       Commandes c = new Commandes(1004,2);
        
       Commandes c1 = new Commandes(43,24);
      
        CommandeService cs = new CommandeService();
        
        
       // ps.ajouterP(p1);
       // System.out.println("produit ajouté avec succes from main");
        try {
            System.out.println(ps.afficherproduits());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
//         try {
//            cs.ajouter(c);
//            System.out.println("commande ajouté avec succes");
//        } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
//        }
//        try {
//            System.out.println(cs.afficher());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
/*
    try {
        ps.modifierproduit(p1);
        System.out.println("modifiee avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
     try {
            ps.supprimer(143);
            System.out.println( "produit supprimé avec succes");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
     
    
    */
    }
    };

