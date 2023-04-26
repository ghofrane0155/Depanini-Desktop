/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Categories;
import Entity.Services;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leila
 */
public class ServiceServices implements ICRUD<Services>{
    Connection connexion;
    Statement stm;

    public ServiceServices() {
        connexion = MyDB.getInstance().getConx();
    }
    
    @Override
    public void ajouter(Services t) throws SQLException {
        String requete = "insert into services (id_service,nom_service,prix_service,descrption_service) values ("
            + t.getId_service()+ ",'" + t.getNom_service()+ "','" + t.getPrix_service()+ "','" + t.getDescription_service()+"')";
        
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);
    }
/************************************************************************************/
    @Override
    public void supprimer(int id) throws SQLException {
        String requete = "DELETE FROM services where id_service="+id;
        
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);    }
/************************************************************************************/
    @Override
    public void modifier(Services t) throws SQLException {
         String requete = "UPDATE services set nom_service='"+t.getNom_service()+"',prix_service='"+t.getPrix_service()+"',descrption_service='"+t.getDescription_service()+"' WHERE id_service="+t.getId_service();
                     
        stm = (Statement) connexion.createStatement();  
        stm.executeUpdate(requete);
    }
///************************************************************************************/
    @Override
    public List<Services> afficher() throws SQLException {
        List<Services> listes = new ArrayList<>();
        
        String req = "select * from services";
        stm = (Statement) connexion.createStatement();
        
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Services o = new Services( rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4));
            listes.add(o);
        }
        return listes;
    }

    @Override
    public Services afficherById(int id) throws SQLException {
        Services s = null ;
        
        String req = "select * from Services where id_service="+id;
        stm = (Statement) connexion.createStatement();
        
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            s = new Services(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return s; 
    }

    
    public List<Services> Recherche(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


    
}
