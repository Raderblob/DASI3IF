/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rader
 */
public class PersonneDao {
    public void creer(Personne personne) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(personne);
    }
     public Personne chercherParMail(String personneMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Personne> query = em.createQuery("SELECT c FROM Personne c WHERE c.mail = :mail", Personne.class);
        query.setParameter("mail", personneMail); // correspond au paramètre ":mail" dans la requête
        List<Personne> people = query.getResultList();
        Personne result = null;
        if (!people.isEmpty()) {
            result = people.get(0); // premier de la liste
        }
        return result;
    }
     
   public Client addClientConsultation(Client client,Consultation consultation){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        
        client.getMyConsultationHistory().add(consultation);
        
        return client;
   }
     
   
}
