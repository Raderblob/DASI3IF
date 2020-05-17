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
public class GetCompanyStatsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String usrLogin = session.getAttribute("login").toString();
        
        
        Service service = new Service();
        Personne personne = service.rechercherPersonneParMail(usrLogin);
        if(personne instanceof Employee){
            List<String> result = service.getStats();
            request.setAttribute("result", result);
        }
        
    }
    
}
