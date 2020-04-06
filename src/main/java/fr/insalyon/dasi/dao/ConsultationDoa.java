/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.Date;
import javax.persistence.EntityManager;

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
}
