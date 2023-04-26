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
 * @author Mega-pc
 * @param <T>
 */
public interface InterfaceCRUD <T> {
    
    public void ajouter(T t) throws SQLException;
    public void modifier(T t) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<T> getAll() throws SQLException;
    public T getOneById(int id) throws SQLException;
    public T getOneByName(String Name) throws SQLException;
     public int prixTot( String nom) throws SQLException;
     public int prixTot( int p) throws SQLException;
     
   

}
