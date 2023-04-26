/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author noure
 */
public class Ticket {
   private int IDTicket , IDEvent , id_user ,PrixTicket, prixtotal;
   private String login;

    public Ticket() {
    }

    public Ticket(int IDTicket, int IDEvent, int id_user, int PrixTicket, int prixtotal, String login) {
        this.IDTicket = IDTicket;
        this.IDEvent = IDEvent;
        this.id_user = id_user;
        this.PrixTicket = PrixTicket;
        this.prixtotal = prixtotal;
        this.login = login;
    }

    public Ticket(int PrixTicket, int prixtotal, String login) {
        this.PrixTicket = PrixTicket;
        this.prixtotal = prixtotal;
        this.login = login;
    }

    public Ticket(int IDEvent, int PrixTicket, int prixtotal, String login) {
        this.IDEvent = IDEvent;
        this.PrixTicket = PrixTicket;
        this.prixtotal = prixtotal;
        this.login = login;
    }

   
    public Ticket(int IDEvent, int id_user, int PrixTicket, int prixtotal, String login) {
        this.IDEvent = IDEvent;
        this.id_user = id_user;
        this.PrixTicket = PrixTicket;
        this.prixtotal = prixtotal;
        this.login = login;
    }

    public int getIDTicket() {
        return IDTicket;
    }

    public int getIDEvent() {
        return IDEvent;
    }

    public int getId_user() {
        return id_user;
    }

    public int getPrixTicket() {
        return PrixTicket;
    }

    public int getPrixtotal() {
        return prixtotal;
    }

    public String getLogin() {
        return login;
    }

    public void setIDTicket(int IDTicket) {
        this.IDTicket = IDTicket;
    }

    public void setIDEvent(int IDEvent) {
        this.IDEvent = IDEvent;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setPrixTicket(int PrixTicket) {
        this.PrixTicket = PrixTicket;
    }

    public void setPrixtotal(int prixtotal) {
        this.prixtotal = prixtotal;
    }

    public void setLogin(String login) {
        this.login = login;
    }

   
    @Override
    public String toString() {
        return "Ticket{" + "IDTicket=" + IDTicket + ", IDEvent=" + IDEvent + ", id_user=" + id_user + ", PrixTicket=" + PrixTicket + '}';
    }
   
   
    
    
}
