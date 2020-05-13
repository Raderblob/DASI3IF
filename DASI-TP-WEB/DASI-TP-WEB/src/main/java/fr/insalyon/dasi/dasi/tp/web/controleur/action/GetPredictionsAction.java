/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class GetPredictionsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String amour = request.getParameter("amour");
        String travail = request.getParameter("travail");
        String sante = request.getParameter("sante");
        Service service = new Service();
        List<String> result = service.genererPredictionsRechercheMail(login,Integer.parseInt(amour),Integer.parseInt(sante),Integer.parseInt(travail));
        request.setAttribute("result", result);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
    }
    
}
