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
public class participant {
    private String login ,email,tel,role;
    private int id_user,id_ticket,qtt,prixtot;

    public participant(String login, String email, String tel, String role, int id_user, int id_ticket, int qtt, int prixtot) {
        this.login = login;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.id_user = id_user;
        this.id_ticket = id_ticket;
        this.qtt = qtt;
        this.prixtot = prixtot;
    }

    public participant(String login, String email, String tel, String role, int id_user, int id_ticket, int qtt) {
        this.login = login;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.id_user = id_user;
        this.id_ticket = id_ticket;
        this.qtt = qtt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    

    public void setPrixtot(int prixtot) {
        this.prixtot = prixtot;
    }

    public int getPrixtot() {
        return prixtot;
    }

    public participant(String login, String email, String tel, int id_user, int id_ticket, int qtt, int prixtot) {
        this.login = login;
        this.email = email;
        this.tel = tel;
        this.id_user = id_user;
        this.id_ticket = id_ticket;
        this.qtt = qtt;
        this.prixtot = prixtot;
    }

    public participant() {
    }

    public participant(String login, String email, String tel, int id_user, int id_ticket, int qtt) {
        this.login = login;
        this.email = email;
        this.tel = tel;
        this.id_user = id_user;
        this.id_ticket = id_ticket;
        this.qtt = qtt;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public int getQtt() {
        return qtt;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }
    
    
    
    
}
