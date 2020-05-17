/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.metier.service.SortType;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class GetClientConsultationsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        String sortType = request.getParameter("sortType");
        
        HttpSession session = request.getSession();
        Personne personne = service.rechercherPersonneParMail(session.getAttribute("login").toString());
        
        String eEmail;
        if(personne instanceof Employee)
        {
            eEmail = request.getParameter("clientEmail");
        }else{
            eEmail = personne.getMail();
        }
        
        

        
        List<Consultation> result = service.getClientConsultations(eEmail,SortType.valueOf(sortType));
        if(result ==null){
            result = new ArrayList<Consultation>();
        }
        request.setAttribute("consultations", result);
        
        

    }
    
}
