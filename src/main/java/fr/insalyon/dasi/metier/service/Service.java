package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ConsultationDoa;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.PersonneDao;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.consultationSorting.SortByDate;
import fr.insalyon.dasi.metier.modele.consultationSorting.SortByEmployee;
import fr.insalyon.dasi.metier.modele.consultationSorting.SortByMedium;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AstroTest;
import util.Message;

/**
 *
 * @author DASI Team
 */
public class Service {


    protected MediumDao mediumDao = new MediumDao();
    protected PersonneDao personneDao = new PersonneDao();
    protected ConsultationDoa consultationDao = new ConsultationDoa();

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
    
    
    
    
    public void envoyerMessageConfirmation(Consultation consultation) {
        String telephoneDestinataire=null;
        String texte=null;
        Message message=new Message();
        JpaUtil.creerContextePersistance();
        try {
            if(consultation.getAccepted()==true)
            {
                
                SimpleDateFormat ftd = new SimpleDateFormat ("yyyy/MM/dd");
                SimpleDateFormat fth = new SimpleDateFormat ("hh:mm");                
                telephoneDestinataire=consultation.getCaller().getTelephoneNumber();
                texte="Bonjour "+consultation.getCaller().getPrenom()+". J’ai bien reçu votre demande de consultation du "+ftd.format(consultation.getStartDate())+" à "+fth.format(consultation.getStartDate())+". Vous pouvez dès à présent me contacter au "+consultation.getAcceptor().getTelephoneNumber()+". A tout de suite ! Médiumiquement vôtre, "+consultation.getMedium().getName()+"";     
                message.envoyerNotification(telephoneDestinataire,texte);
            }else{
                telephoneDestinataire=consultation.getAcceptor().getTelephoneNumber();
                texte="Attention, il faut confirmer le rdv sur le site avant d'envoyer un message au client";     
                message.envoyerNotification(telephoneDestinataire,texte);
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrirePersonne(client)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return ;
    }
    
    public void EnvoyerMailInscription(Personne personne)
    {
        Message message=new Message();
        Long resultat = null;
        String mailExpediteur="contact@predict.if";
        String mailDestinataire=personne.getMail();
        String object=null;
        String corps=null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = personne.getId();
            if (resultat != null) {
                object="Bienvenue chez PREDICT’IF";
                corps="Bonjour "+personne.getPrenom()+", nous vous confirmons votre inscription au service PREDICT’IF.Rendez-vous  vite  sur  notre  site  pour  consulter  votre profil  astrologique  et  profiter  des  dons incroyables de nos mediums";
                message.envoyerMail(mailExpediteur,mailDestinataire,object,corps);
            } else {
                object="Echec de l’inscription chez PREDICT’I";
                corps="Bonjour "+personne.getPrenom()+", votre inscription au service PREDICT’IF a malencontreusement échoué... Merci de recommencer ultérieurement.";
                message.envoyerMail(mailExpediteur,mailDestinataire,object,corps);
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrirePersonne(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }  
        
        return ;
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
    
    public Personne rechercherPersonneParMotDePasse(String motDePasse){
        Personne resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            resultat = personneDao.chercherParMotDePasse(motDePasse);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherPersonneParMotDePasse(String motDePasse)", ex);
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
    
    
    
    public List<String> genererPredictions(String couleur,String animal,int amour, int sante, int travail){
        List<String> resultat = null;
        AstroTest astroTest=new AstroTest();
        JpaUtil.creerContextePersistance();
        try {
            // on génère la prédiction
            resultat = astroTest.getPredictions(couleur,animal,amour,sante,travail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherPersonneParMail(String mail)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<String> genererPredictionsRechercheMail(String mail,int amour, int sante, int travail){
        List<String> resultat = null;
        AstroTest astroTest=new AstroTest();
        JpaUtil.creerContextePersistance();
        Personne subject= null;
        /*if(subject!=null){
            System.out.println(subject.getNom());
        }*/
        try {
            // Recherche du client
            subject = personneDao.chercherParMail(mail);
            //si la recherche aboutit et que la personne est un client alors on crée un prédiction
            if(subject!=null &&subject instanceof Client)
            {
                Client test=(Client)subject;
                resultat = astroTest.getPredictions(test.getCouleurPorteBonheur(),test.getAnimalTotem(),amour,sante,travail);
            }
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
            Collections.sort(resultat);
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
      
     public List<Consultation> getClientConsultations(String personneMail,SortType sortType){
         List<Consultation> result = null;
         JpaUtil.creerContextePersistance();
         
         try {
            Personne personne = personneDao.chercherParMail(personneMail);
            if(personne instanceof Client){
                result = ((Client)personne).getMyConsultationHistory();
                switch(sortType) {
                    case EMPLOYEE:
                        result.sort(new SortByEmployee());
                        break;
                    case DATE:
                        result.sort(new SortByDate());
                        break;
                    case MEDIUM:
                        result.sort(new SortByMedium());
                        break;
                }
            }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authenticatePersonne(String personneMail, String personneMdp)", ex);
            result = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         return result;
     }
     
     public Consultation addClientConsultation(String clientEmail, Medium medium){//Assigns as well
         Client client ;
         Consultation consultation = null;
         JpaUtil.creerContextePersistance();
         
          try {
            Personne personne = personneDao.chercherParMail(clientEmail);
            if(personne instanceof Client){
                client  = (Client)personne;
                consultation = new Consultation(client,medium);
                List<Employee> possibleEmployees = personneDao.GetAvailableEmployees(medium.getMyGender());
                if(!possibleEmployees.isEmpty()){
                    consultation.setAcceptor(possibleEmployees.get(0));
                    JpaUtil.ouvrirTransaction();
                    consultationDao.createConsultation(consultation);
                    JpaUtil.validerTransaction();
                    JpaUtil.ouvrirTransaction();
                    personneDao.addClientConsultation(client.getId(), consultation.getId());
                    personneDao.addEmployeeConsultation(possibleEmployees.get(0).getId(), consultation.getId());
                    mediumDao.addConsultation(medium.getId(), consultation.getId());
                    JpaUtil.validerTransaction();
                }else{
                    System.out.println("No available employee");
                    JpaUtil.ouvrirTransaction();
                    consultationDao.createConsultation(consultation);
                    JpaUtil.validerTransaction();
                    JpaUtil.ouvrirTransaction();
                    personneDao.addClientConsultation(client.getId(), consultation.getId());
                    mediumDao.addConsultation(medium.getId(), consultation.getId());
                    JpaUtil.validerTransaction();
                }
            }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service addClientConsultation(String clientEmail, Consultation consultation)", ex);
            JpaUtil.annulerTransaction();
            consultation = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         return consultation;
     }
     
     public List<Consultation> assignConsultations(){ //not finished // DO NOT CALL
         List<Consultation> acceptedConsultations = new ArrayList();
         JpaUtil.creerContextePersistance();
         
         try {
             List<Consultation> unacceptedConsultations = consultationDao.getUnacceptedConsultations();
             for(Consultation consultation:unacceptedConsultations){
                List<Employee> possibleEmployees = personneDao.GetAvailableEmployees(consultation.getMedium().getMyGender());
                if(!possibleEmployees.isEmpty()){
                    JpaUtil.ouvrirTransaction();
                    consultationDao.setAcceptor(consultation.getId(), possibleEmployees.get(0).getId());
                    personneDao.addEmployeeConsultation(possibleEmployees.get(0).getId(), consultation.getId());
                    JpaUtil.validerTransaction();
                    acceptedConsultations.add(consultation);
                }
             }
             

         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service addClientConsultation(String clientEmail, Consultation consultation)", ex);
            JpaUtil.annulerTransaction();
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         return acceptedConsultations;
     }
     
     public Employee confirmConsultation(String employeeEmail, String review){
         Employee employee = null;
         JpaUtil.creerContextePersistance();
         try {
            Personne personne;
            personne = personneDao.chercherParMail(employeeEmail);
            if(personne instanceof Employee){
                employee = (Employee)personne;
                if(!employee.getAvailable()){
                    JpaUtil.ouvrirTransaction();
                    consultationDao.finishConsultation(employee.getConsultations().get(employee.getConsultations().size()-1), review);
                    personneDao.setAvailable(employee.getId(), true);
                    JpaUtil.validerTransaction();
                }else{
                    employee = null;
                }
            }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service confirmConsultation(String employeeEmail, String review)", ex);
            JpaUtil.annulerTransaction();
            employee = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
          
          return employee;
     }
     
     public Personne setPassword(String pEmail, String newPassword){
         Personne personne = null;
         JpaUtil.creerContextePersistance();
         try {
           personne = rechercherPersonneParMail(pEmail);
           if(personne != null){
               JpaUtil.ouvrirTransaction();
               personneDao.setPassword(personne.getId(), newPassword);
               JpaUtil.validerTransaction();
           }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service setPassword(String pEmail, String newPassword)", ex);
            JpaUtil.annulerTransaction();
            personne = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
          
          return personne;
     }
     
     public List<Consultation> getMediumConsultations(String mediumName){
         List<Consultation> consultations = null;
         
         JpaUtil.creerContextePersistance();
         try {
           consultations = consultationDao.getMediumConsultations(mediumName);
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service getMediumConsultations(String mediumName)", ex);
            consultations = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         
         return consultations;
     }
     
    public List<Consultation> getPastEmployeeConsultations(String employeeName){
         List<Consultation> consultations = null;
         
         JpaUtil.creerContextePersistance();
         try {
           consultations = consultationDao.getEmployeePastConsultations(employeeName);
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service getPastEmployeeConsultations(String employeeName)", ex);
            consultations = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         
         return consultations;
     }
    
    public Consultation getUnconfirmedEmployeeConsultations(String employeeName){
        Consultation consultation = null;
         
         JpaUtil.creerContextePersistance();
         try {
           List<Consultation> consultations = consultationDao.getEmployeePastConsultations(employeeName);
           if(!consultations.isEmpty()){
               consultation = consultations.get(consultations.size()-1);
               if(consultation.getAcceptor().getAvailable()){
                   consultation = null;
               }
           }
         }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service getPastEmployeeConsultations(String employeeName)", ex);
            consultation = null;
         }finally {
            JpaUtil.fermerContextePersistance();
         }
         
         
         return consultation;
    }
    
    

}
