/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 * @param <produits>
 */
    public interface Iproduits <produits> {
    
   public void ajouterP(produits p) throws SQLException;
    public List<produits> afficherproduits() throws SQLException;
    }

 
