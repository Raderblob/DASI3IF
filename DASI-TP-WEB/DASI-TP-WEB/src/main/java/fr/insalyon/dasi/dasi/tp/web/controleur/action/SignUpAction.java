/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class SignUpAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        String name = request.getParameter("name");
        String firstName = request.getParameter("firstName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phoneNumber = request.getParameter("phoneNumber");
        String postCode = request.getParameter("postCode");
        
        Service service = new Service();
        Client client = new Client(dateOfBirth, postCode,name, firstName, email, password, phoneNumber);
        Long id  = service.inscrirePersonne(client);
        service.EnvoyerMailInscription(client);
        
        request.setAttribute("connexion", id);
        

    }
    
}
