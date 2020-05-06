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
public class ChangePasswordAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        Service service = new Service();
        
        if(service.authenticatePersonne(login, password)!=null && newPassword != null){
            Personne personne = service.setPassword(login, newPassword);
            request.setAttribute("personne", personne);
        }else{
            request.setAttribute("personne", null);
        }
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
    }
    
}
