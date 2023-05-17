/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.admin;
import Entity.user;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static services.UserService.connexion;
import static services.UserService.ps;

/**
 *
 * @author Ghof
 */
public class AdminService{
    Connection conx;
    Statement stm;
    PreparedStatement ps;

    public AdminService() {
        conx=MyDB.getInstance().getConx();
    }
/*******************************************************************/
    public admin getAdminByLogin(String log) {
        admin a=null;
        try{
        String req="select * from admins where login_admin='"+log+"'";
        ps=conx.prepareStatement(req);
        ResultSet rst=ps.executeQuery();
        if(rst.first()){
            a=new admin(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),

                rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8)) ;

        }
        
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return a;
        }
        return a;
    }
/************************************************************************/

    
}