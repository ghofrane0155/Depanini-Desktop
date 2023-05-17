/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.PreparedStatement;
import Entity.Contrats;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Utils.MyDB;
import java.util.ArrayList;

/**
 *
 * @author yasmine
 */
public class ContratsService implements ICRUD<Contrats>{
    Connection connexion;
    Statement stm;
    
        public ContratsService() {
        connexion = MyDB.getInstance().getConx();
    }
    
    @Override
    public void ajouter(Contrats d) throws SQLException {
        String req = "INSERT INTO `contrat` ( `reference` , `etat` ,`date`,`conditions` ) VALUES ( '"
                 + d.getReference()+ "', '" + d.getEtat()+ "','" + d.getDate()+ "', '" + d.getConditions()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        System.out.println("Contrat ajoutee !");
    }

    @Override
    public List<Contrats> afficher() throws SQLException {
        List<Contrats> contrats = new ArrayList<>();
        String req = "select * from contrat";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Contrats c = new Contrats(rst.getInt("id_contart"),
                    rst.getInt("reference"),
                    rst.getString("etat"),
                    rst.getString("date"),
                    rst.getString("conditions"));
            contrats.add(c);
        }
      
        return contrats;
    }

    @Override
    public void supprimer(int IDC) throws SQLException {
            String req = "DELETE FROM contrat WHERE `id_contart`=?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, IDC);
            ps.executeUpdate();
            System.out.println("Contrat supprimee !");
    }

    @Override
    public void modifier(Contrats c) throws SQLException {
     String req = "UPDATE contrat SET  `conditions`=? WHERE `id_contart`= ?";
    
     PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, c.getConditions());
            ps.setInt(2, c.getIDContrat());
            System.out.println(c.getIDContrat());
            ps.executeUpdate();
            System.out.println("Contrat Updated  !");

      
    }
public List<Contrats> Recherche(String searchString) throws SQLException {
    List<Contrats> contrats = new ArrayList<>();
    String req = "SELECT * FROM contrat WHERE etat LIKE ? OR date LIKE ? OR conditions LIKE ? OR reference LIKE ?";
    PreparedStatement ps = connexion.prepareStatement(req);
    ps.setString(1, "%" + searchString + "%");
    ps.setString(2, "%" + searchString + "%");
    ps.setString(3, "%" + searchString + "%");
        ps.setString(4, "%" + searchString + "%");
    ps.setString(5, "%" + searchString + "%");

    ResultSet rst = ps.executeQuery();

    while (rst.next()) {
        Contrats c = new Contrats(rst.getInt("id_contart"),rst.getInt("reference"),
                rst.getString("date"),
                rst.getString("etat"),
                rst.getString("conditions"));
        contrats.add(c);
    }
    return contrats;
}

       
   public int existe(String attribut, String valeur) throws Exception{
        int nb=0;
        try{
            String req="select * from contrat where "+attribut+"='"+valeur+"'";
          PreparedStatement  ps=connexion.prepareStatement(req);
            ResultSet rst=ps.executeQuery();
              
            while (rst.next())
                nb++;
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return nb;
    }

    @Override
    public Contrats afficherById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

 

    


