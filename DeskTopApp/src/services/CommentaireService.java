/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Commentaire;
import Entity.Statut;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mega-pc
 */
public class CommentaireService implements ICRUD<Commentaire>{
  Connection conx = MyDB.getInstance().getConx();
    @Override
    public void ajouter(Commentaire t) throws SQLException {
        try {
            String req = "INSERT INTO commentaires (`id_reclamation`, `editeur`,`date`,`commentaire`)VALUES(?,?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            
           
            ps.setInt(1, t.getId_reclamation());
            ps.setString(2, t.getEditeur());
            ps.setDate(3, new java.sql.Date(t.getDate().getTime()));
            ps.setString(4, t.getCommentaire());          
            ps.executeUpdate();
            System.out.println("Commentaire Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
         try {
            String req = "DELETE FROM commentaires WHERE `id_commentaire`=?";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Commentaire t) throws SQLException {
       try {
            String req = "UPDATE  commentaires SET `id_reclamation`=?,`editeur`=?,`date`=?,`commentaire`=? WHERE `id_commentaire`=?";
            PreparedStatement ps = conx.prepareStatement(req);          
            ps.setInt(1, t.getId_reclamation());
            ps.setString(2, t.getEditeur());
            ps.setDate(3, new java.sql.Date(t.getDate().getTime()));
            ps.setString(4, t.getCommentaire());              
            ps.setInt(5, t.getId_commentaire());
          
            ps.executeUpdate();
            System.out.println("Commentaire Updated  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public List<Commentaire> afficher() throws SQLException {
        List<Commentaire> result = new ArrayList<>();
        try {
            String req = "SELECT * FROM commentaires";
            PreparedStatement ps = conx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {
                
                int id_commentaire = rs.getInt(1);
                int id_reclamation = rs.getInt(2);
                String editeur = rs.getString(3);              
                Date date= rs.getDate(4);
                String commentaire = rs.getString(5);    
                
               Commentaire c = new Commentaire(id_commentaire,id_reclamation, editeur, date, commentaire);
                result.add(c);               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public Commentaire afficherById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
