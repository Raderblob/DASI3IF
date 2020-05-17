/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class GetUserAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userLogin = session.getAttribute("login").toString();
        
        String requestedUser = request.getParameter("login");
        Service service = new Service();
        
        
        Personne usr = service.rechercherPersonneParMail(userLogin);
        if(requestedUser==null){
            request.setAttribute("personne", usr);
        }else if(usr instanceof Employee){
            Personne req = service.rechercherPersonneParMail(requestedUser);
            if(req instanceof Client){
                request.setAttribute("personne", req);
            }
        }
        
        
       
        
        

        
    


      
    }
    
}
