/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.personne.Employee;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class ConfirmConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        String eEmail = request.getParameter("employeeEmail");
        String review = request.getParameter("review");
        Service service = new Service();
        Employee employee = service.confirmConsultation(eEmail,review);
        if(employee!=null){
            service.assignConsultations();
        }
        
        request.setAttribute("personne", employee);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
    }
    
}
