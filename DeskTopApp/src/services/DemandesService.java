/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Entity.Demandes;
import Entity.Offres;
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
 * @author yasmine
 */
public class DemandesService implements ICRUD<Demandes> {
    
    Connection connexion;
    Statement stm;

    public DemandesService() {
        connexion = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Demandes d) throws SQLException {            
        String req = "INSERT INTO `demandes` (`idClient`, `budget` , `description` ,`date` ,`nom`) VALUES ( '"
                + d.getID() + "', '" + d.getBudget() + "', '" + d.getDescription() + "','" + d.getDate() + "','" + d.getNom()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        System.out.println("Demande ajoutee !");
    }

    @Override
    public List<Demandes> afficher() throws SQLException {
        List<Demandes> demandes = new ArrayList<>();
        String req = "select * from demandes";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Demandes d = new Demandes(
                    rst.getInt("reference"),
                    rst.getInt("idClient"),
                    rst.getFloat("budget"),
                    rst.getString("description"),
                    rst.getString("date"),
                    rst.getString("nom"));
            demandes.add(d);
        }
        return demandes;
    }   

    @Override
    public void supprimer(int ref) throws SQLException {
            String req = "DELETE FROM demandes WHERE `reference`=?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, ref);
            ps.executeUpdate();
            System.out.println("Demande supprimee !");
    }

    @Override
    public void modifier(Demandes d) throws SQLException {
        
          String req = "UPDATE demandes SET  `nom`=?,`budget`=?,  `description`=? WHERE `nom`= ?";
     PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, d.getNom());
            ps.setFloat(2, d.getBudget());
            ps.setString(3, d.getDescription());
            ps.setString(4, d.getNom());
            
            ps.executeUpdate();
            System.out.println("Demande Updated  !");
      
      
      
    }

    public List<Demandes> Recherche(String searchString) throws SQLException {
    List<Demandes> demandes = new ArrayList<>();
    String req = "SELECT * FROM demandes WHERE nom LIKE ? OR date LIKE ? OR description LIKE ? OR budget LIKE ?";
    PreparedStatement ps = connexion.prepareStatement(req);
    ps.setString(1, "%" + searchString + "%");
    ps.setString(2, "%" + searchString + "%");
    ps.setString(3, "%" + searchString + "%");
    ps.setString(4, "%" + searchString + "%");
    ResultSet rst = ps.executeQuery();

    while (rst.next()) {
        Demandes c = new Demandes(rst.getString("nom"),
                rst.getFloat("budget"),
                rst.getString("date"),
                rst.getString("description"));
        demandes.add(c);
    }
    return demandes;
    }

    @Override
    public Demandes afficherById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}


