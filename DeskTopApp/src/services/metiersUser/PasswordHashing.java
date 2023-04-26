package services.metiersUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SplittableRandom;

/**
 *
 * @author Ghof
 */
public class PasswordHashing {
      public static String hashPassword(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes=md.digest();
            StringBuilder sb=new StringBuilder();

            for(byte b :bytes){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex.getMessage());} 
    return "";       
    }
/*******************************************************************/
    public static String generateOTP(int longueur) {
        SplittableRandom str=new SplittableRandom();
        StringBuilder sb=new StringBuilder();
        
        for(int i=0;i<longueur;i++)
            sb.append(str.nextInt(0, 10));//any random digit from zero to 9
        
         return sb.toString();
    }
}
