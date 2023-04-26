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
public enum Statut {

    Ouvert("Ouvert"),
    Resolu("Resolu"),
    En_attente("En_attente");
    
    private String label ;

    private Statut(String label) {
        this.label = label;
    }
    
     public String getLabel() {
        return label;
    }
}
