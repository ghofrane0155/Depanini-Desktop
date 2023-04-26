/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Mega-pc
 */
public enum Type {
    ACCOUNT_INFORMATION("Information sur votre compte"),
    ORDER_INFORMATION("Information sur vos commandes"),
    WEBSITE_FEEDBACK("Suggestions et remarques sur le site"),
    REPORT_ISSUE("Signaler un dysfonctionnement"),
    OTHER("Autre");
    
    private String label;
    
    Type(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
}
