/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Entity.Recrutements;
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
public class RecrutementService implements ICRUD<Recrutements> {
    
    Connection connexion;
    Statement stm;

    public RecrutementService() {
        connexion = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Recrutements d) throws SQLException {            
        String req = "INSERT INTO `recrutements` (`idClient`, `salaire` , `description` ,`date` ,`nom`) VALUES ( '"
                + d.getID() + "', '" + d.getSalaire() + "', '" + d.getDescription() + "','" + d.getDate() + "','" + d.getNom()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        System.out.println("Demande ajoutee !");
    }

    @Override
    public List<Recrutements> afficher() throws SQLException {
        List<Recrutements> demandes = new ArrayList<>();
        String req = "select * from recrutements";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Recrutements d = new Recrutements(
                    rst.getInt("reference"),
                    rst.getInt("idClient"),
                    rst.getFloat("salaire"),
                    rst.getString("description"),
                    rst.getString("date"),
                    rst.getString("nom"));
            demandes.add(d);
        }
        return demandes;
    }   

    @Override
    public void supprimer(int ref) throws SQLException {
            String req = "DELETE FROM recrutements WHERE `reference`=?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, ref);
            ps.executeUpdate();
            System.out.println("Demande supprimee !");
    }

    @Override
    public void modifier(Recrutements d) throws SQLException {
        
          String req = "UPDATE recrutements SET  `nom`=?,`salaire`=?,  `description`=? WHERE `nom`= ?";
     PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, d.getNom());
            ps.setFloat(2, d.getSalaire());
            ps.setString(3, d.getDescription());
            ps.setString(4, d.getNom());
            
            ps.executeUpdate();
            System.out.println("Demande Updated  !");
      
      
      
    }

    public List<Recrutements> Recherche(String searchString) throws SQLException {
    List<Recrutements> demandes = new ArrayList<>();
    String req = "SELECT * FROM recrutements WHERE nom LIKE ? OR date LIKE ? OR description LIKE ? OR salaire LIKE ?";
    PreparedStatement ps = connexion.prepareStatement(req);
    ps.setString(1, "%" + searchString + "%");
    ps.setString(2, "%" + searchString + "%");
    ps.setString(3, "%" + searchString + "%");
    ps.setString(4, "%" + searchString + "%");
    ResultSet rst = ps.executeQuery();

    while (rst.next()) {
        Recrutements c = new Recrutements(rst.getString("nom"),
                rst.getFloat("salaire"),
                rst.getString("date"),
                rst.getString("description"));
        demandes.add(c);
    }
    return demandes;
    }

    @Override
    public Recrutements afficherById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


