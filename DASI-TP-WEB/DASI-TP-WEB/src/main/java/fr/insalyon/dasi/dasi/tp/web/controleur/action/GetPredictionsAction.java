/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.personne.Employee;
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
        Service service = new Service();
        HttpSession session = request.getSession();
        
        Personne userLogin = service.rechercherPersonneParMail(session.getAttribute("login").toString());
        if(userLogin instanceof Employee){
        
            String login = request.getParameter("login");
            String amour = request.getParameter("amour");
            String travail = request.getParameter("travail");
            String sante = request.getParameter("sante");

            List<String> result = service.genererPredictionsRechercheMail(login,Integer.parseInt(amour),Integer.parseInt(sante),Integer.parseInt(travail));
            request.setAttribute("result", result);
        }
        
    }
    
}
