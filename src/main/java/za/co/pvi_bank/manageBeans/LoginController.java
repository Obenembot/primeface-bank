/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.pvi_bank.manageBeans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import za.co.pvi_bank.entities.login_details.LoginDetails;

/**
 *
 * @author embot
 */
@ManagedBean(name = "login")
public class LoginController {

    private String username;
    private int password;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    List<LoginDetails> login = null;
    EntityManagerFactory emf = null;
    EntityManager em = null;

    public String login() {
        emf = Persistence.createEntityManagerFactory("pviBank");
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            login = em.createQuery("SELECT a FROM LoginDetails a").getResultList();
            int a = 0;
            for (a = 0; a < login.size(); a++) {
                if (password == login.get(a).getPassword()
                        && login.get(a).getUserName().equals(username)
                        && login.get(a).getEmployee().getEmployeePosition().equalsIgnoreCase("teller")) {

                    return "menu.xhtml";

                } else if (password == login.get(a).getPassword()
                        && login.get(a).getUserName().equals(username)
                        && login.get(a).getEmployee().getEmployeePosition().equalsIgnoreCase("manager")) {

                    return "employee.xhtml";

                }
            }
            if (login.size() == a) {
                message = "LoginFailed";
                return "index.xhtml";
            }

        } catch (Exception e) {

        }
        return null;
    }
}
