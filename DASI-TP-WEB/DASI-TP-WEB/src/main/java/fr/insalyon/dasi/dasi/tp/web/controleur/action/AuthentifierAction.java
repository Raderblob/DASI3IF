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
public class AuthentifierAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Service service = new Service();
        Personne personne = service.authenticatePersonne(login, password);
        request.setAttribute("personne", personne);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
    }
    
}
