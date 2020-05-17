/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class SendRequestAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String mediumName = request.getParameter("mediumName");
        String clientName = session.getAttribute("login").toString();
        Service service = new Service();
        Medium medium = service.rechercherMediumParNom(mediumName);
        Consultation consultation = service.addClientConsultation(clientName, medium);
        request.setAttribute("consultation", consultation);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        
    }
    
}
