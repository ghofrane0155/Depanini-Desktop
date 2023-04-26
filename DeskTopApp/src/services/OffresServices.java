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
import Utils.*;
import Entity.Enum.TypeC;
import Entity.Enum.TypeO;
import java.sql.PreparedStatement;

/**
 *
 * @author leila
 */
public class OffresServices implements ICRUD<Offres> {

    Connection connexion;
    Statement stm;
    PreparedStatement ps;

    public OffresServices() {
        connexion = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Offres t) throws SQLException {
        String requete = "insert into offres (id_offre,id_user,prix_offre,description_offre,localisation_offre,nom_offre,image_offre,type_offre,type_cat) values (?,?,?,?,?,?,?,?,?)";

        ps = connexion.prepareStatement(requete);
        ps.setInt(1, t.getId_offre());
        ps.setInt(2, t.getId_user());
        ps.setDouble(3, t.getPrix_offre());
        ps.setString(4, t.getDescription_offre());
        ps.setString(5, t.getLocation_offre());
        ps.setString(6, t.getNom_offre());
        ps.setString(7, t.getImage_offre());
        ps.setString(8, t.getType_offre().toString());
        ps.setString(9, t.getType_cat().toString());

        ps.executeUpdate();
        System.out.println("Offres added!");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String requete = "DELETE FROM offres where id_offre=" + id;

        stm = (Statement) connexion.createStatement();
        stm.executeUpdate(requete);
    }

    @Override
    public void modifier(Offres t) throws SQLException {
        String requete = "UPDATE offres set id_user=?,prix_offre=?,description_offre=?,localisation_offre=?,nom_offre=?,image_offre=?,type_offre=?,type_cat=? WHERE id_offre=" + t.getId_offre();

        ps = connexion.prepareStatement(requete);
        ps.setInt(1, t.getId_user());
        ps.setDouble(2, t.getPrix_offre());
        ps.setString(3, t.getDescription_offre());
        ps.setString(4, t.getLocation_offre());
        ps.setString(5, t.getNom_offre());
        ps.setString(6, t.getImage_offre());
        ps.setString(7, t.getType_offre().toString());
        ps.setString(8, t.getType_cat().toString());

        ps.executeUpdate();
        System.out.println("Offres updated!");
    }

    @Override
    public List<Offres> afficher() throws SQLException {
        List<Offres> offres = new ArrayList<>();

        String req = "select * from offres";
        stm = (Statement) connexion.createStatement();

        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            Offres o = new Offres(rst.getInt(1), rst.getInt(2), rst.getDouble(3), rst.getString(4), rst.getString(5),
                    rst.getString(6), rst.getString(7), TypeO.valueOf(rst.getString(8)),
                    TypeC.valueOf(rst.getString(9)));

            offres.add(o);
        }
        return offres;
    }

    /**
     * *************************************************************************************
     */
    @Override
    public Offres afficherById(int id) throws SQLException {
        Offres o = null;

        String req = "select * from offres where id_offre=" + id;
        stm = (Statement) connexion.createStatement();

        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            o = new Offres(rst.getInt(1), rst.getInt(2), rst.getDouble(3), rst.getString(4), rst.getString(5),
                    rst.getString(6), rst.getString(7), TypeO.valueOf(rst.getString(8)),
                    TypeC.valueOf(rst.getString(9)));
        }
        return o;
    }

    /**
     *
     * @param nom
     * @return
     * @throws SQLException
     */
    public List<Offres> Recherche(String nom) throws SQLException {
        List<Offres> contrats = new ArrayList<>();
        String req = "SELECT * FROM offres WHERE nom_offre LIKE ?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, "%" + nom + "%");
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            Offres o = new Offres(rst.getInt(1), rst.getInt(2), rst.getDouble(3), rst.getString(4), rst.getString(5),
                    rst.getString(6), rst.getString(7), TypeO.valueOf(rst.getString(8)),
                    TypeC.valueOf(rst.getString(9)));

            contrats.add(o);
        }
        return contrats;
    }
  
}
