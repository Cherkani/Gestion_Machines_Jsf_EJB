/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author cherk
 */

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import Entities.Login;

@Stateless
public class LoginFacade {

    @PersistenceContext(unitName = "ControleIsicPU")
    private EntityManager entityManager;

    public List<Login> findByUsernameAndPassword(String username, String password) {
        Query query = entityManager.createQuery("SELECT l FROM Login l WHERE l.username = :username AND l.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList();
    }
}