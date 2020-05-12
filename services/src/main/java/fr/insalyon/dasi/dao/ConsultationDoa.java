/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.ConsultationState;
import fr.insalyon.dasi.metier.modele.Gender;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
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
        consultation.setState(ConsultationState.CONFIRMED);
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
    
    public Consultation setAcceptor(Long consultationId,Long employeeId){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Consultation consultation = em.find(Consultation.class, consultationId);
        Employee employee = em.find(Employee.class, employeeId);
        consultation.setAcceptor(employee);
        
        return consultation;
    }
    
    
      public List<Consultation> getUnacceptedConsultations(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.state = :unassigned", Consultation.class);
        query.setParameter("unassigned", ConsultationState.NOTASSIGNED);
        List<Consultation> consultations = query.getResultList();
        return consultations;
   }
      
    public Consultation accept(Long consultationId){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Consultation consultation = em.find(Consultation.class, consultationId);;
        consultation.setState(ConsultationState.ACCEPTED);
        consultation.setStartDate(new Date());
        return consultation;
    }
    
    
   public List<Consultation> getConsultationList() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c ", Consultation.class);
        List<Consultation> cons = query.getResultList();
        return cons;
    }
}


