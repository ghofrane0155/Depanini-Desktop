package services.metiersUser;

import java.time.LocalDate;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import services.UserService;

/**
 *
 * @author Ghof
 */
    
public class Verif {

    public static boolean validateNom(String nom){
        if(!Pattern.matches("([a-zA-Z ])+",nom)){
             showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez votre Nom ! ");
                return false;
        }
        return true;
    }
    
     public static boolean validatePrenom(String prenom){
        if(!Pattern.matches("([a-zA-Z ])+",prenom)){
             showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez votre Prénom ! ");
                return false;
        }
        return true;
    }
     
    /**********login start with an alphabet + alphabets, numbers or an underscore (length=6-30)*****************************************************/            
     public static boolean validateLogin(String log){
        if (!Pattern.matches("^[A-Za-z][A-Za-z0-9_]{5,29}$", log)) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "login doit commencer par majus et peut contenir que des (lettres,chiffres,'_') .\nExemple : Depanini_2023");
                return false;
            }
         return true;
      } 
     
    /***************telephone 8 chiffres***************************************************************************************/ 
     public static boolean validateTel(String tel){
        if (!Pattern.matches("\\d*", tel) || tel.length() != 8) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Le numéro de telephone doit contenir 8 chiffres ou plus.\nExemple : 96451906");
                return false;
            }
        return true;
    }
     
    /*******************mail (randomString)@(randomString2).(2-3 characters) ********************************************************/
     public static boolean validateMail(String mail){
        if (!Pattern.matches("^[a-z]+[a-z0-9.]+@[a-z]+\\.[a-z]{2,3}", mail)) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Email non valide !");
                return false;
            }
        return true;
     }
       
    /************************DATE***************************************************************************************/      
      public static boolean validateDate(LocalDate ldate){
        if (ldate.toString().length() != 10 || !Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", ldate.toString()) ) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner votre date de naissance.\nExemple : 2001-06-23");
                return false;
            }
        if (ldate.getYear() > 2007) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vous êtes trop jeune !");
                return false;
            }
        return true;
      }
        
    /*************password minimum 8*********************************************************************/  
      public static boolean validatePass(String pass){
        if (pass.length()<8) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Mot de Passe doit contenir au moins 8 caractères,");
                return false;
            }
        return true;
      }
    /**********confirmer password*********************************************************/
       public static boolean confirmPass(String pass,String pass2){
        if (!pass.equals(pass2)) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier Confirm New password", "Les mots de passe ne correspondent pas !");
                return false;
            }
        return true;
      }

///**************************************************************************************************/
 public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
}
