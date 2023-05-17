/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;



/**
 *
 * @author leila
 */
public class CategoriesServices implements ICRUD <Categories>{
    Connection connexion;
    Statement stm;

    public CategoriesServices() {
        connexion = MyDB.getInstance().getConx();
    }
    
    @Override
    public void ajouter(Categories t) throws SQLException {
        String requete = "insert into categories (id_categorie,nom_categorie,image_categorie) values "
                + "(" + t.getId_categorie()+ ",'" + t.getNom_categorie()+ "','" + t.getImage_categorie()+"')";
        
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);
    }
/************************************************************************************/
    @Override
    public void supprimer(int id) throws SQLException {
        String requete = "DELETE FROM categories where id_categorie="+id;
        
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);    }
/************************************************************************************/
    @Override
    public void modifier(Categories t) throws SQLException {
         String requete = "UPDATE categories set id_categorie="+t.getId_categorie()+",nom_categorie='"+t.getNom_categorie()+"',image_categorie='"+t.getImage_categorie()+"' WHERE id_categorie="+t.getId_categorie();
              
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);
    }
/************************************************************************************/
    @Override
    public List<Categories> afficher() throws SQLException {
        List<Categories> listes = new ArrayList<>();
        
        String req = "select * from categories";
        stm = (Statement) connexion.createStatement();
        
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categories o = new Categories( rst.getInt(1),rst.getString(2),rst.getString(3));
            listes.add(o);
        }
        return listes;
    }

    @Override
    public Categories afficherById(int id) throws SQLException {
        Categories cat = null ;
        
        String req = "select * from categories where id_categorie="+id;
        stm = (Statement) connexion.createStatement();
        
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            cat = new Categories(rst.getInt(1),rst.getString(2),rst.getString(3));
        }
        return cat; 
    }

    
    public List<Categories> Recherche(String nom) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}