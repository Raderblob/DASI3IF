/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Gender;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.util.Date;
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
     
    public Personne chercherParMotDePasse(String personneMotDePasse) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Personne> query = em.createQuery("SELECT c FROM Personne c WHERE c.motDePasse = :motDePasse", Personne.class);
        query.setParameter("motDePasse", personneMotDePasse); // correspond au paramètre ":motDePasse" dans la requête
        List<Personne> people = query.getResultList();
        Personne result = null;
        if (!people.isEmpty()) {
            result = people.get(0); // premier de la liste
        }
        return result;
    }
    
    public Personne chercherParMotDePasseEtMail(String mail, String motDePasse) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Personne> query = em.createQuery("SELECT c FROM Personne c WHERE c.motDePasse = :motDePasse AND c.mail = :mail", Personne.class);
        query.setParameter("motDePasse", motDePasse); // correspond au paramètre ":motDePasse" dans la requête
        query.setParameter("mail", motDePasse); // correspond au paramètre ":mail" dans la requête
        List<Personne> people = query.getResultList();
        System.out.println(mail+motDePasse);
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
   
   public List<Employee> GetAvailableEmployees(Gender gender){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employee> query = em.createQuery("SELECT c FROM Employee c WHERE c.myGender = :gender AND c.available = :isAvailable", Employee.class);
        query.setParameter("gender", gender);
        query.setParameter("isAvailable", true);
        List<Employee> employees = query.getResultList();
        return employees;
   }
   
   public Employee setAvailable(Employee employee,boolean available){
       EntityManager em = JpaUtil.obtenirContextePersistance();
        
       employee.setAvailable(available);
        
       return employee;
   }
   
   public Employee addEmployeeConsultation(Employee employee,Consultation consultation){
       EntityManager em = JpaUtil.obtenirContextePersistance();
       employee.setCurrentConsultation(consultation);
       employee.setAvailable(false);
       consultation.setAccepted(true);
       consultation.setStartDate(new Date());
       return employee;
   }
   
}
