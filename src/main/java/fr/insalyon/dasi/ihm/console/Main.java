package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.ConsultationState;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.Gender;
import fr.insalyon.dasi.metier.modele.medium.Astrologue;
import fr.insalyon.dasi.metier.modele.medium.Cartomancien;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.metier.service.SortType;
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


       doTestCases();
       //doUnitTests();

        JpaUtil.destroy();
    }


    public static void afficherPersonne(Personne personne) {
        System.out.println("-> " + personne);
    }
    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }


    public static void doTestCases(){
        createEmployees();//creation of employees
        createMediums();//creation of mediums
        
        clientCreateAccount();//two clients create accounts

        clientsConnect();// Both clients connect to the website
        
        loadHomePage();//Load the client information
        
        searchMediums();//Get list of mediums and make a selection
        
        bothClientsAskForSameConsult();//Clients ask for consultations Only one is assigned because not enough employees


        employeeAcceptsConsultation();//employee acccepts consultation
        
        //consultation in progress via phone
        
        //end consultation via phone
        confirmConsultation();//employee writes comment for consultation

        clientChangesPassword();//Client decides to change password
        
        loadHomePage();//Load the client information
        
        employeeViewContents();//Employee searches the different lists of clients/mediums and enployees
        
        employeeViewsHisPastConsultations(); //Employee views his past consultations
        
        employeeAcceptsConsultation();//employee acccepts consultation
        
        //consultation in progress via phone
        
        employeeNeedHelp();//Employee asks for help using the generator
        
        employeeViewClientProfile(); // Employee view CLient profile
        
        employeeViewClientConsultations();//Employee view client consultations using different sorting methods // Tested more in the unittests
        
        //end consultation via phone
        confirmConsultation();//employee writes comment for consultation
        
        employeeGetsEmplStats(); // Employee chekcs out company stats
    }




    public static void doUnitTests(){
         testerInscriptionTous();
        testerRechercheTous();

        testerAuthenticatePersonne();
        testerAddClientConsultation();
        testerconfirmConsultation();
        testerGetMediumConsultations();

        testerGenerationPrediction();
        testerAssignConsultation();
        testerGetPastEmployeeConsultations();
        //testeEnvoiMailInscription();
        testerGetUnconfirmedEmployeeConsultations();
        testerGetListOfMediums();
        testerGetClientConsultations();
       // testeEnvoiMessageDemandeConsultation();
       //testChangePassword();
    }
    
    public static void employeeGetsEmplStats(){
        System.out.println();
        System.out.println("**** employeeGetsEmplStats() ****");
        System.out.println();

        Service service = new Service();
        
        List<String> res = service.getStats();
        
        System.out.println(res);
    }
    
    public static void employeeViewClientConsultations(){
        System.out.println();
        System.out.println("**** employeeViewClientConsultations() ****");
        System.out.println();

        Service service = new Service();
        String clientMail = "blaise.pascal@insa-lyon.fr";
        List<Consultation> consultations;
        
        System.out.println("Get Consultations by DATE");
        consultations = service.getClientConsultations(clientMail, SortType.DATE);
        System.out.println(consultations);
        
        System.out.println("Get Consultations by EMPLOYEE");
        consultations = service.getClientConsultations(clientMail, SortType.EMPLOYEE);
        System.out.println(consultations);
        
         System.out.println("Get Consultations by MEDIUM");
        consultations = service.getClientConsultations(clientMail, SortType.MEDIUM);
        System.out.println(consultations);
    }
    
    public static void employeeViewClientProfile(){
        System.out.println();
        System.out.println("**** employeeViewClientProfile() ****");
        System.out.println();

        Service service = new Service();
        
        Client result;
        Personne personne;
        String userMail = "blaise.pascal@insa-lyon.fr";
        
        personne = service.rechercherPersonneParMail(userMail);
        if(personne !=null && personne instanceof Client){
            result = (Client)personne;
            System.out.print(personne);
            System.out.print(result);
        }
    }
    
    public static void employeeNeedHelp(){
        System.out.println();
        System.out.println("**** employeeNeedHelp() ****");
        System.out.println();

        Service service = new Service();
        
        String clientMail = "blaise.pascal@insa-lyon.fr";
        List<String> result;
        
        result = service.genererPredictionsRechercheMail(clientMail, 1, 2, 3);
       if(result !=null){
           System.out.println(result);
       }
        
    }
    
    public static void employeeViewsHisPastConsultations(){
        System.out.println();
        System.out.println("**** employeeViewsHisPastConsultations() ****");
        System.out.println();

        Service service = new Service();
        
        String mail = "namelessBob3.fotiadu@insa-lyon.fr";
        
        System.out.println("Viewing past consulations for " + mail);
        List<Consultation> consultations = service.getPastEmployeeConsultations(mail);
        if(consultations != null){
            System.out.println(consultations);
        }
    }
    
    public static void searchMediums(){
        System.out.println();
        System.out.println("**** SearchMediums() ****");
        System.out.println();

        Service service = new Service();
        
        String type;
        List<Medium> result;
        
        
        type = "Medium";
        System.out.println("**Searching for " + type);
        result = service.GetListOfMediums(type);
        if(result !=null){
            System.out.println(result);
        }
        
        String mediumId ="HilbertShadow";
        System.out.println("** Select Medium #" + mediumId);
        Medium medium = service.rechercherMediumParNom(mediumId);
        if(medium != null){
            System.out.println(medium);
        }
        
    }
    
    public static void loadHomePage(){
        System.out.println();
        System.out.println("**** loadHomePage() ****");
        System.out.println();

        Service service = new Service();
        
        String userMail;
        Personne personne;
        Client client;
        
        userMail = "ada.lovelace@insa-lyon.fr";
        System.out.println("Loading page for " + userMail);
        personne = service.rechercherPersonneParMail(userMail);
        if(personne !=null && personne instanceof Client){
            client = (Client)personne;
            System.out.println(client);
        }
        
        userMail = "blaise.pascal@insa-lyon.fr";
        System.out.println("Loading page for " + userMail);
        personne = service.rechercherPersonneParMail(userMail);
        if(personne !=null && personne instanceof Client){
            client = (Client)personne;
            System.out.println(client);
        }
    }
    
    public static void createEmployees(){
        System.out.println();
        System.out.println("**** createEmployees() ****");
        System.out.println();

        Service service = new Service();

        Employee e1 = new Employee(Gender.MALE, 0, true,"Employee1", "Employee1Name", "namelessBob.fotiadu@insa-lyon.fr", "e1234567489","852917382645");
        Long ide1 = service.inscrirePersonne(e1);
        if (ide1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(e1);

        Employee e2 = new Employee(Gender.MALE, 0, true,"Employee2", "Employee1Name", "namelessBob2.fotiadu@insa-lyon.fr", "e12345674892","8529173826452");
        Long ide12 = service.inscrirePersonne(e2);
        if (ide12 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(e2);

        Employee e3 = new Employee(Gender.FEMALE, 0, true,"Employee3", "Employee3Name", "namelessBob3.fotiadu@insa-lyon.fr", "e123456748923","85291738264523");
        Long ide13 = service.inscrirePersonne(e3);
        if (ide13 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(e3);
    }

    public static void createMediums(){
        System.out.println();
        System.out.println("**** createMediums() ****");
        System.out.println();

        Service service = new Service();

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

    public static void clientCreateAccount(){
        System.out.println();
        System.out.println("**** clientCreateAccount() ****");
        System.out.println();

        Service service = new Service();

        Client lovelace = new Client("31/12/1998","69100","Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012","01254605");
        Long idLovelace = service.inscrirePersonne(lovelace);
        if (idLovelace != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        service.EnvoyerMailInscription(lovelace);
        afficherPersonne(lovelace);




        Client pascal = new Client("11/01/1988","69100","Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906","123456789");
        Long idPascal = service.inscrirePersonne(pascal);
        if (idPascal != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        service.EnvoyerMailInscription(pascal);
        afficherPersonne(pascal);
    }

    public static void clientsConnect(){
        System.out.println();
        System.out.println("**** clientsConnect() ****");
        System.out.println();

        Service service = new Service();

        String userMail;
        String password;
        Personne result;


        userMail = "ada.lovelace@insa-lyon.fr";
        password = "Ada1012";
        System.out.println("** Authenticate with #" + userMail + " Password: " + password);
        result = service.authenticatePersonne(userMail,password);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }

        userMail = "blaise.pascal@insa-lyon.fr";
        password = "Blaise1906";
        System.out.println("** Authenticate with #" + userMail + " Password: " + password);
        result = service.authenticatePersonne(userMail,password);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }
    }

    public static void bothClientsAskForSameConsult(){
        System.out.println();
        System.out.println("**** bothClientsAskForSameConsult() ****");
        System.out.println();

        Service service = new Service();

        String clientEmail;
        Medium medium;
        String mediumId;
        Consultation consultation;

        mediumId ="HilbertShadow";
        clientEmail = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Adding a Consultation #" + clientEmail + " " + mediumId);
        medium = service.rechercherMediumParNom(mediumId);
        if (medium != null) {
            consultation = service.addClientConsultation(clientEmail, medium);
            if(consultation != null){
                  System.out.println(consultation);
                  if(consultation.getAcceptor()!=null){
                    service.envoyerMessageDemande(consultation);
                  }
            }else{
                System.out.println("Cannot add consultation");
            }
        }else{
            System.out.println("Invalid Medium");
        }

        mediumId ="HilbertShadow";
        clientEmail = "blaise.pascal@insa-lyon.fr";
        System.out.println("** Adding a Consultation #" + clientEmail + " " + mediumId);
        medium = service.rechercherMediumParNom(mediumId);
        if (medium != null) {
            consultation = service.addClientConsultation(clientEmail, medium);
            if(consultation != null){
                  System.out.println(consultation);
                  if(consultation.getAcceptor()!=null){
                    service.envoyerMessageDemande(consultation);
                  }
            }else{
                System.out.println("Cannot add consultation");
            }
        }else{
            System.out.println("Invalid Medium");
        }
    }

    public static void confirmConsultation(){
        System.out.println();
        System.out.println("**** confirmConsultation() ****");
        System.out.println();

        Service service = new Service();

        Consultation consultation;
        String employeeEmail;

        employeeEmail = "namelessBob3.fotiadu@insa-lyon.fr";
        System.out.println("** Get Unconfirmed for " + employeeEmail);
        consultation = service.getUnconfirmedEmployeeConsultations(employeeEmail);
        if(consultation != null){
            System.out.println(consultation);
            Employee employee = service.confirmConsultation(employeeEmail,"test");
            if(employee !=null){
                System.out.println(employee.getConsultations().get(employee.getConsultations().size()-1));
            }else{
                System.out.println("Could not confirm");
            }
        }else{
            System.out.println("No ConsultationInProgress");
        }
        System.out.println("Assigning new consultations");
        List<Consultation> consultations = service.assignConsultations();
        System.out.println(consultations);
    }
    
    public static void employeeViewContents(){
        System.out.println();
        System.out.println("**** employeeViewContents() ****");
        System.out.println();

        Service service = new Service();
        List<Medium> mediums;
        List<Client> clients;
        List<Employee> employees;
        
        mediums = service.GetListOfMediums("Medium");
        clients = service.getListClients();
        employees = service.getListEmployees();
        System.out.println("Mediums " + mediums);
        System.out.println("Clients " + clients);
        System.out.println("employees" + employees);
    }
    
    public static void employeeAcceptsConsultation(){
        System.out.println();
        System.out.println("**** employeeAcceptsConsultation() ****");
        System.out.println();

        Service service = new Service();

        Consultation consultation;
        String employeeEmail;

        employeeEmail = "namelessBob3.fotiadu@insa-lyon.fr";
        System.out.println("** Get Unconfirmed for " + employeeEmail);
        consultation = service.getUnconfirmedEmployeeConsultations(employeeEmail);
        if(consultation != null){
            System.out.println(consultation);
            Employee employee = service.acceptConsultation(employeeEmail);
            if(employee !=null){
                consultation = employee.getConsultations().get(employee.getConsultations().size()-1);
                System.out.println(consultation);
                service.envoyerMessageConfirmation(consultation);
            }else{
                System.out.println("Could not confirm");
            }
        }else{
            System.out.println("No ConsultationInProgress");
        }
    }

    public static void clientChangesPassword(){
        System.out.println();
        System.out.println("**** clientChangesPassword() ****");
        System.out.println();

        Service service = new Service();

        String userMail;
        String newPassword;
        Personne result;

        userMail = "ada.lovelace@insa-lyon.fr";
        newPassword = "Ada1012NewPassword";
        System.out.println("** change password with #" + userMail + " Password: " + newPassword);
        result = service.setPassword(userMail,newPassword);
        if (result != null) {
            afficherPersonne(result);
        } else {
            System.out.println("=> Credentials false");
        }
    }


    public static void testeEnvoiMessageDemandeConsultation()
    {
        System.out.println();
        System.out.println("**** testeEnvoiMessageDemandeConsultation() ****");
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
        consultation = service.addClientConsultation(clientEmail, medium);


        service.envoyerMessageDemande(consultation);

    }


    public static void testeEnvoiMessageConfirmationConsultation()
    {
        System.out.println();
        System.out.println("**** testeEnvoiMessageConfirmationConsultation() ****");
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
        consultation = service.addClientConsultation(clientEmail, medium);

        String emplEmail;
        Employee employee;

        emplEmail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** confirmConsultation #" + emplEmail);
        employee = service.confirmConsultation(emplEmail, "It was ok");
        service.envoyerMessageConfirmation(consultation);

        mediumId ="SIDMAG";
        clientEmail = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Adding a Consultation #" + clientEmail + " " + mediumId);
        medium = service.rechercherMediumParNom(mediumId);
        consultation = service.addClientConsultation(clientEmail, medium);
        consultation.setState(ConsultationState.NOTASSIGNED);//CHECK THIS--------------------------------------------------------------------------------------------------------
        emplEmail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** confirmConsultation #" + emplEmail);
        employee = service.confirmConsultation(emplEmail, "It was ok");
        service.envoyerMessageConfirmation(consultation);

        service.envoyerMessageConfirmation(consultation);

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

        Employee e2 = new Employee(Gender.MALE, 0, true,"Employee2", "Employee1Name", "namelessBob2.fotiadu@insa-lyon.fr", "e12345674892","8529173826452");
        Long ide12 = service.inscrirePersonne(e2);
        if (ide12 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(e2);

        Employee e3 = new Employee(Gender.FEMALE, 0, true,"Employee3", "Employee3Name", "namelessBob3.fotiadu@insa-lyon.fr", "e123456748923","85291738264523");
        Long ide13 = service.inscrirePersonne(e3);
        if (ide13 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(e3);

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
        System.out.println("** Recherche de Consultations sortTypeNone " + email);
        testList = service.getClientConsultations(email,SortType.NONE);
        if(testList != null){
            for (Consultation x:testList){
                System.out.println(x);
            }
        }

        email = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Recherche de Consultations sortTypeMedium " + email);
        testList = service.getClientConsultations(email,SortType.MEDIUM);
        if(testList != null){
            for (Consultation x:testList){
                System.out.println(x);
            }
        }

        email = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Recherche de Consultations sortTypeEmployee " + email);
        testList = service.getClientConsultations(email,SortType.EMPLOYEE);
        if(testList != null){
            for (Consultation x:testList){
                System.out.println(x);
            }
        }

        email = "ada.lovelace@insa-lyon.fr";
        System.out.println("** Recherche de Consultations sortTypeDate " + email);
        testList = service.getClientConsultations(email,SortType.DATE);
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

        mediumId ="HilbertShadow";
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
            System.out.println("No Employee by that name");
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

    public static void testerGetUnconfirmedEmployeeConsultations(){
        System.out.println();
        System.out.println("**** testerGetUnconfirmedEmployeeConsultations() ****");
        System.out.println();

        Service service = new Service();

        Consultation consultation;
        String employeeEmail;

        employeeEmail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** testerGetUnconfirmedEmployeeConsultations() #" + employeeEmail);
        consultation = service.getUnconfirmedEmployeeConsultations(employeeEmail);
        if(consultation != null){
            System.out.println(consultation);
        }else{
            System.out.println("No ConsultationInProgress");
        }
    }

}
