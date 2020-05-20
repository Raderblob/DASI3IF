/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class ForgottenPasswordAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
       
        String email = request.getParameter("email");
        Service service = new Service();
        if(service.rechercherPersonneParMail(email)!=null && email != null){
            Personne personne = service.envoyerMessageReinitialistaionPassword(email);
            request.setAttribute("personne", personne);
        }else{
            request.setAttribute("personne", null);
        }
        
    }
    
}

