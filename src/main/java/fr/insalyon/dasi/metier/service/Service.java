package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.PersonneDao;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class Service {


    protected MediumDao mediumDao = new MediumDao();
    protected PersonneDao personneDao = new PersonneDao();

    public Long inscrirePersonne(Personne personne) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            personneDao.creer(personne);
            JpaUtil.validerTransaction();
            resultat = personne.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrirePersonne(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    

    
     public Long inscrireMedium(Medium medium) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDao.creer(medium);
            JpaUtil.validerTransaction();
            resultat = medium.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireMedium(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    

    public Personne rechercherPersonneParMail(String mail){
        Personne resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            resultat = personneDao.chercherParMail(mail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherPersonneParMail(String mail)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Medium rechercherMediumParNom(String nom){
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            resultat = mediumDao.chercherParName(nom);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMediumParNom(String nom)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Medium> GetListOfMediums(String type){
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            resultat = mediumDao.GetListOfMediums(type);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service GetListOfMediums(String type)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    
    }
    
      public Personne authenticatePersonne(String personneMail, String personneMdp){
         Personne result = null;
         JpaUtil.creerContextePersistance();
         
         try {
            result = personneDao.chercherParMail(personneMail);
            if(result == null || result.getMotDePasse() != personneMdp){
                result = null;
            }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authenticatePersonne(String personneMail, String personneMdp)", ex);
            result = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         return result;
         
     }
      
     public List<Consultation> getClientConsultations(String personneMail){
         Personne personne = null;
         List<Consultation> result = null;
         JpaUtil.creerContextePersistance();
         
         try {
            personne = personneDao.chercherParMail(personneMail);
            if(personne instanceof Client){
                result = ((Client)personne).getMyConsultationHistory();
            }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authenticatePersonne(String personneMail, String personneMdp)", ex);
            result = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         return result;
     }
     
     public Consultation addClientConsultation(String clientEmail, Medium medium){
         Client client  = null;
         Consultation consultation = null;
         JpaUtil.creerContextePersistance();
         
          try {
            Personne personne = personneDao.chercherParMail(clientEmail);
            if(personne instanceof Client){
                client  = (Client)personne;
                consultation = new Consultation(client,medium);
                JpaUtil.ouvrirTransaction();
                personneDao.addClientConsultation(client, consultation);
                JpaUtil.validerTransaction();
            }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service addClientConsultation(String clientEmail, Consultation consultation)", ex);
            client = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         return consultation;
     }

}
