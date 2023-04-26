/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import Entity.Event;
import java.sql.SQLException;
import services.EventService;
import Utils.MyDB;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import services.metierEvent.qrcode;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.Format;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author noure
 */
public class GestionEvent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, WriterException {  
        
        MyDB db1 = MyDB.getInstance();
        EventService se = new EventService();
        Event E1 =new Event(111,"youyou", 30, "tunis", "youssef", "2022-12-1", "2023-12-5", " developpment", "image",40); 
        
        Event E2 =new Event(219,"nour", 100, "LieuEvent", "youssef", "2023-12-1", "2023-12-5", " developpment", "Work anniversary-pana.png",50);
        
        System.out.println(se.countEvents());
        
        se.getevent("Nour123");
        
//        try{
//                se.ajouter(E2);
//                System.out.println("ajout avec succes");
//            }
//                catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//       try{
//                se.modifier(E2);
//                System.out.println("modifié avec succes");
//            }
//                catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//       String outputQR="C:\\Users\\noure\\OneDrive\\Bureau\\desktopapp2\\src\\images\\qr.png";
//         qrcode.generateQRcode("hey",1250,1250,outputQR);
//        System.out.println("heyy");
          
        /* try {
               se.delete(E2.getIDEvent());
               System.out.println("supprimé avec succes"); 
            } 
                catch (SQLException ex) {
                System.out.println(ex.getMessage());
                }*/
        
        /*try{
                se.ajouter(E1);
                System.out.println("ajout avec succes");
            }
                catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }*/
                
//        try  {
//                System.out.println(se.getAll());
//            } 
//                catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//        }
            try  {
                System.out.println();
                            System.out.println(se.getOneByName(E2.getNomEvent()));
                        } 
                            catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                    }


                }
}

