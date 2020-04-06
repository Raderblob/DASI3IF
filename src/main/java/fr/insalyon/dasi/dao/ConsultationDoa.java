/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rader
 */
public class ConsultationDoa {
    
    public void createConsultation(Consultation consultation){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }
    
    public Consultation finishConsultation(Consultation consultation,String review){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        consultation.setDate(new Date());
        consultation.setConsultationLength((int)(consultation.getDate().getTime() - consultation.getStartDate().getTime()));
        consultation.setComment(review);
        return consultation;
    }
    
    public List<Consultation> getMediumConsultations(String mediumName){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.medium.name = :name", Consultation.class);
        query.setParameter("name", mediumName);
        List<Consultation> consultations = query.getResultList();
        
        return consultations;
    }
    
    public List<Consultation> getEmployeePastConsultations(String employeeEmail){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.acceptor.mail = :mail", Consultation.class);
        query.setParameter("mail", employeeEmail);
        List<Consultation> consultations = query.getResultList();
        
        return consultations;
    }
    
    
}


