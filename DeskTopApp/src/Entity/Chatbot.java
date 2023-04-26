/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author leila
 */
public class Chatbot {
     public String processInput(String input) {
       if(null == input)return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
       else switch (input) {
            case "Salut":
                return "Bonjour, comment puis-je vous aider ?";
            case "Pouvez vous m'expliquer le concept de cette plateforme":
                return "DEPANINI , est une plateforme qui permet au freelancers    \n  de postuler des  offres presentiel ou en ligne !  " ;
            case "Comment je peux creer une offre":
                return "Tout d'abord acceder au interface Ajouter offre  , Remplir soignesement  le formulaire  \n  ,  et au finale cliquer sur Ajouter  \n une notifaction sera Afficher contenant les informations de l'offre enregistrer " ;
           
            case "Merci":
                return "A tout moment , je suis là pour vous aidez !";
              
            default:
                return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        }
    }
}

