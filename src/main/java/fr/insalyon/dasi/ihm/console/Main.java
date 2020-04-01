package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.Gender;
import fr.insalyon.dasi.metier.modele.medium.Astrologue;
import fr.insalyon.dasi.metier.modele.medium.Cartomancien;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

       // initialiserClients();            // Question 3
        testerInscriptionTous();       // Question 4 & 5
        testerRechercheTous();         // Question 6
        //testerListeClients();            // Question 7
        //testerAuthentificationClient();  // Question 8
        //saisirInscriptionClient();       // Question 9
        //saisirRechercheClient();
        
        
        JpaUtil.destroy();
    }
    

    public static void afficherPersonne(Personne personne) {
        System.out.println("-> " + personne);
    }
    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }


    public static void testerInscriptionTous() {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client claude = new Client(new Date(),"69100","Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012","01254605");
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(claude);

        Client hedy = new Client(new Date(),"69100","Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906","123456789");
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(hedy);

        Client hedwig = new Client(new Date(),"69100","Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever","987654321");
        Long idHedwig = service.inscrireClient(hedwig);
        if (idHedwig != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherPersonne(hedwig);
        
        
        Employee e1 = new Employee(Gender.MALE, 0, true,"Employee1", "Employee1Name", "namelessBob.fotiadu@insa-lyon.fr", "e1234567489","852917382645");
        Long ide1 = service.inscrireEmployee(e1);
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
    }

    public static void testerRechercheTous() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        String mail;
        String name;
        Client client;
        Employee employee;
        Medium medium;

        mail = "frederic.fotiadu@insa-lyon.fr";
        System.out.println("** Recherche du Client #" + mail);
        client = service.rechercherClientParMail(mail);
        if (client != null) {
            afficherPersonne(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }
        
        mail = "falseEmail";
        System.out.println("** Recherche du Client #" + mail);
        client = service.rechercherClientParMail(mail);
        if (client != null) {
            afficherPersonne(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }
        
        mail = "namelessBob.fotiadu@insa-lyon.fr";
        System.out.println("** Recherche du Employee #" + mail);
        employee = service.rechercherEmployeeParMail(mail);
        if (employee != null) {
            afficherPersonne(employee);
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
