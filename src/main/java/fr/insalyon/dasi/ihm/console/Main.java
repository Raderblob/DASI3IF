package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.Gender;
import fr.insalyon.dasi.metier.modele.medium.Astrologue;
import fr.insalyon.dasi.metier.modele.medium.Cartomancien;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.AstroTest;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();


        testerInscriptionTous();       
        testerRechercheTous();        
        testerGetListOfMediums();
        testerAuthenticatePersonne();
        testerAddClientConsultation();
        testerGetClientConsultations();
        testerconfirmConsultation();
        testerGetMediumConsultations();
        
        testerGenerationPrediction();
        testerAssignConsultation();
        testerGetPastEmployeeConsultations();
        testeEnvoiMailInscription();
        JpaUtil.destroy();
    }
    

    public static void afficherPersonne(Personne personne) {
        System.out.println("-> " + personne);
    }
    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }

    public static void testeEnvoiMailInscription()
    {
        System.out.println();
        System.out.println("**** testeEnvoiMailInscription() ****");
        System.out.println();
        
        Service service = new Service();
        Client claude = new Client("31/10/1998","69105","Lovelace0", "Ada0", "ada0.lovelace0@insa-lyon.fr", "Ada10120","012546050");
        Long idClaude = service.inscrirePersonne(claude);
        
        service.EnvoyerMailInscription(claude);
        
        Client claude2 = new Client("31/11/1998","69102","Lovelace2", "Ada2", "ada.lovelace2@insa-lyon.fr", "Ada10122","012546052");
        
        service.EnvoyerMailInscription(claude2);
        
        
        
    }
    
    
    public static void testerGenerationPrediction()
    {
        System.out.println();
        System.out.println("**** testerGenerationPrediction() ****");
        System.out.println();
        
        int amour=1;
        int sante=1;
        int travail=1;
        Service service=new Service();
        
        
       //List<String> predictions=service.genererPredictions("Caramel","Cheval",amour,sante,travail);
       List<String> predictions=service.genererPredictionsRechercheMail("frederic.fotiadu@insa-lyon.fr",amour,sante,travail);
       if(predictions!=null)
       {
           System.out.println("ok");
           System.out.println(predictions.get(0));
       }else{
           System.out.println("pas ok");
       }
       
       
    }

    public static void testerInscriptionTous() {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client claude = new Client("31/12/1998","69100","Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012","01254605");
        Long idClaude = service.inscrirePersonne(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(claude);

        Client hedy = new Client("11/01/1988","69100","Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906","123456789");
        Long idHedy = service.inscrirePersonne(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(hedy);

        Client hedwig = new Client("15/02/1798","69100","Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever","987654321");
        Long idHedwig = service.inscrirePersonne(hedwig);
        if (idHedwig != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(hedwig);
        
        
        Employee e1 = new Employee(Gender.MALE, 0, true,"Employee1", "Employee1Name", "namelessBob.fotiadu@insa-lyon.fr", "e1234567489","852917382645");
        Long ide1 = service.inscrirePersonne(e1);
        if (ide1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(e1);
        
       Medium m1 = new Astrologue("I was magic school", 42, "HilbertShadow", "Pick me",Gender.FEMALE);
       Long idm1 = service.inscrireMedium(m1);
       if (idm1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
       }
       afficherMedium(m1);
        
       Medium m2 = new Cartomancien( "SIDMAG", "SIGMAGISUS",Gender.MALE);
       Long idm2 = service.inscrireMedium(m2);
       if (idm2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
       }
       afficherMedium(m2);
       
       Medium m3 = new Cartomancien( "Alphy", "AlphyMag",Gender.MALE);
       Long idm3 = service.inscrireMedium(m3);
       if (idm2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
       }
       afficherMedium(m3);
    }

    public static void testerRechercheTous() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        String mail;
        String name;
        Personne personne;
        Medium medium;

        mail = "frederic.fotiadu@insa-lyon.fr";
        System.out.println("** Recherche du Client #" + mail);
        personne = service.rechercherPersonneParMail(mail);
        if (personne != null && personne instanceof Client) {
            afficherPersonne(personne);
        } else {
            System.out.println("=> Client non-trouvé");
        }
        
        mail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** Recherche du Client #" + mail);
        personne = service.rechercherPersonneParMail(mail);
        if (personne != null && personne instanceof Client) {
            afficherPersonne(personne);
        } else {
            System.out.println("=> Client non-trouvé");
        }
        
        mail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** Recherche du Employee #" + mail);
        personne = service.rechercherPersonneParMail(mail);
        if (personne != null && personne instanceof Employee) {
            afficherPersonne(personne);
        } else {
            System.out.println("=> employee non-trouvé");
        }
        
        name = "SIDMAG";
        System.out.println("** Recherche du Medium #" + name);
        medium = service.rechercherMediumParNom(name);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium non-trouvé");
        }
    }
    
    public static void testerGetListOfMediums(){
        System.out.println();
        System.out.println("**** testerGetListOfMediums() ****");
        System.out.println();
        
        Service service = new Service();
        
        String parameters;
        
        List<Medium> testList;
        
        parameters = "Medium";
        System.out.println("** Recherche du Medium #" + parameters);
        testList = service.GetListOfMediums(parameters);
        for (Medium x:testList){
            afficherMedium(x);
        }
        
        
        parameters = "Cartomancien";
        System.out.println("** Recherche du Medium #" + parameters);
        testList = service.GetListOfMediums(parameters);
        for (Medium x:testList){
            afficherMedium(x);
        }
        
        parameters = "Astrologue";
        System.out.println("** Recherche du Medium #" + parameters);
        testList = service.GetListOfMediums(parameters);
        for (Medium x:testList){
            afficherMedium(x);
        }
        
        parameters = "Spirite";
        System.out.println("** Recherche du Medium #" + parameters);
        testList = service.GetListOfMediums(parameters);
        for (Medium x:testList){
            afficherMedium(x);
        }
    }
    
    public static void testerAuthenticatePersonne(){
        System.out.println();
        System.out.println("**** testerAuthenticatePersonne() ****");
        System.out.println();
        
        Service service = new Service();
        String userMail;
        String password;
        Personne result;
        
        
        userMail = "frederic.fotiadu@insa-lyon.fr";
        password = "wrong";
        System.out.println("** Authenticate with #" + userMail + " Password: " + password);
        result = service.authenticatePersonne(userMail,password);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }
        
        userMail = "frederic.fotiadu@insa-lyon.fr";
        password = "INSA-Forever";
        System.out.println("** Authenticate with #" + userMail + " Password: " + password);
        result = service.authenticatePersonne(userMail,password);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }
        
        
        userMail = "ada.lovelace@insa-lyon.fr";
        password = "Ada1012";
        System.out.println("** Authenticate with #" + userMail + " Password: " + password);
        result = service.authenticatePersonne(userMail,password);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }
        
        userMail = "ada.lovelace@insa-lyon.fr123";
        password = "Ada1012";
        System.out.println("** Authenticate with #" + userMail + " Password: " + password);
        result = service.authenticatePersonne(userMail,password);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }
        
    }
    
    public static void testerGetClientConsultations(){
        System.out.println();
        System.out.println("**** testerGetClientConsultations() ****");
        System.out.println();
        
        Service service = new Service();
        
        String email;
        List<Consultation> testList;
        
        email = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Recherche de Consultations #" + email);
        testList = service.getClientConsultations(email);
        if(testList != null){
            for (Consultation x:testList){
                System.out.println(x);
            }
        }
    }
    
    public static void testerAddClientConsultation(){
        System.out.println();
        System.out.println("**** testerAddClientConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        
        String clientEmail;
        Medium medium;
        String mediumId;
        Consultation consultation;
        
        
        mediumId ="SIDMAG";
        clientEmail = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Adding a Consultation #" + clientEmail + " " + mediumId);
        medium = service.rechercherMediumParNom(mediumId);
        if (medium != null) {
            consultation = service.addClientConsultation(clientEmail, medium);
            if(consultation != null){
                  System.out.println(consultation);
            }else{
                System.out.println("Cannot add consultation");
            }
        }else{
            System.out.println("Invalid Medium");
        }
        
        mediumId ="Alphy";
        clientEmail = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Adding a Consultation #" + clientEmail + " " + mediumId);
        medium = service.rechercherMediumParNom(mediumId);
        if (medium != null) {
            consultation = service.addClientConsultation(clientEmail, medium);
            if(consultation != null){
                  System.out.println(consultation);
            }else{
                System.out.println("Cannot add consultation");
            }
        }else{
            System.out.println("Invalid Medium");
        }
        
    }
    
    public static void testerconfirmConsultation(){
        System.out.println();
        System.out.println("**** testerconfirmConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        
        String emplEmail;
        Employee employee;
        
        emplEmail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** confirmConsultation #" + emplEmail);
        employee = service.confirmConsultation(emplEmail, "It was ok");
        if(employee !=null){
            System.out.println(employee.getConsultations().get(employee.getConsultations().size()-1));
        }else{
            System.out.println("Could not confirm");
        }
        
        emplEmail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** confirmConsultation #" + emplEmail);
        employee = service.confirmConsultation(emplEmail, "It was ok");
        if(employee !=null){
            System.out.println(employee.getConsultations().get(employee.getConsultations().size()-1));
        }else{
            System.out.println("Could not confirm");
        }
        
        
        emplEmail = "Not possible email";
        System.out.println("** confirmConsultation #" + emplEmail);
        employee = service.confirmConsultation(emplEmail, "It was ok");
        if(employee !=null){
            System.out.println(employee.getConsultations().get(employee.getConsultations().size()-1));
        }else{
            System.out.println("Could not confirm");
        }
    }
    
    public static void testerGetMediumConsultations(){
        System.out.println();
        System.out.println("**** testerGetMediumConsultations() ****");
        System.out.println();
        
        Service service = new Service();
        List<Consultation> consultations;
        String mediumName;
        
        
        mediumName = "Alphy";
        System.out.println("** GetMediumConsultations() #" + mediumName);
        consultations = service.getMediumConsultations(mediumName);
        if(consultations != null){
            for(Consultation x:consultations){
                System.out.println(x);
            }
        }else{
            System.out.println("No Medium by that name");
        }
        
        mediumName = "SIDMAG";
        System.out.println("** GetMediumConsultations() #" + mediumName);
        consultations = service.getMediumConsultations(mediumName);
        if(consultations != null){
            for(Consultation x:consultations){
                System.out.println(x);
            }
        }else{
            System.out.println("No Medium by that name");
        }
        
        mediumName = "noExist";
        System.out.println("** GetMediumConsultations() #" + mediumName);
        consultations = service.getMediumConsultations(mediumName);
        if(consultations != null){
            for(Consultation x:consultations){
                System.out.println(x);
            }
        }else{
            System.out.println("No Medium by that name");
        }
        
    }
    
    public static void testerGetPastEmployeeConsultations(){
        System.out.println();
        System.out.println("**** testerGetPastEmployeeConsultations() ****");
        System.out.println();
        
        Service service = new Service();
        List<Consultation> consultations;
        String employeeEmail;
        
        employeeEmail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** testerGetPastEmployeeConsultations() #" + employeeEmail);
        consultations = service.getPastEmployeeConsultations(employeeEmail);
        if(consultations != null){
            for(Consultation x:consultations){
                System.out.println(x);
            }
        }else{
            System.out.println("No Medium by that name");
        }
        
    }
    
    
    public static void testerAssignConsultation(){
        System.out.println();
        System.out.println("**** testerAssignConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        
         List<Consultation> acceptedConsultations = service.assignConsultations();
         
         System.out.println(acceptedConsultations);
    }
/*
    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada2020";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    public static void saisirInscriptionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");

        Client client = new Client(nom, prenom, mail, motDePasse);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.authentifierClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
    
    */
}
