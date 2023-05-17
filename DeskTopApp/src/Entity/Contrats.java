/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author yasmine
 */
public class Contrats {
    int id_contrat;
    int reference;
    String etat;
    String date;
    String conditions;

    public Contrats(int reference) {
        this.reference = reference;
    }

    public Contrats(String etat,String date,  String conditions,Double price,int reference) {
        this.reference = reference;
        this.etat = etat;
        this.date = date;
        this.conditions = conditions;
    }
       public Contrats(int reference,String date,String etat,  String conditions) {
        this.reference = reference;
        this.etat = etat;
        this.date = date;
        this.conditions = conditions;
    }

    public Contrats(int id_contrat, int reference, String date, String etat, String conditions) {
        this.id_contrat=id_contrat;
         this.reference = reference;
        this.etat = etat;
        this.date = date;
        this.conditions = conditions;    }

    public Contrats(int id_contrat, int reference) {
         this.id_contrat=id_contrat;
         this.reference = reference;
    }

    public int getIDContrat() {
        return id_contrat;
    }

    public void setIDContrat(int id_contrat) {
        this.id_contrat = id_contrat;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }


    public Contrats(int id_contrat, int reference, String etat, String date, String conditions, double price) {
        this.id_contrat = id_contrat;
        this.reference = reference;
        this.etat = etat;
        this.date = date;
        this.conditions = conditions;
    }

    public Contrats(int reference, String etat, String date, String conditions, double price) {
        this.reference = reference;
        this.etat = etat;
        this.date = date;
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "Contrats{" + "id_contrat=" + id_contrat + ", reference=" + reference + ", etat=" + etat + ", date=" + date + ", conditions=" + conditions +  '}';
    }



    

}
