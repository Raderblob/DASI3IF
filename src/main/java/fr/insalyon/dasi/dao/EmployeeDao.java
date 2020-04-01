/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rader
 */
public class EmployeeDao {
    
    public void creer(Employee employee) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employee);
    }
    
    
    
    // modifier / supprimer  ... 
}
