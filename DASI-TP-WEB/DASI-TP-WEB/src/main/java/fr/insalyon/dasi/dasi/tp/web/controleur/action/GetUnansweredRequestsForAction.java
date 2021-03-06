/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class GetUnansweredRequestsForAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String eEmail = session.getAttribute("login").toString();
        Service service = new Service();
        List<Consultation> result = new ArrayList<Consultation>();
        Consultation res = service.getUnconfirmedEmployeeConsultations(eEmail);
        if(res!=null){
            result.add(res);
        }

        request.setAttribute("consultations", result);
        
    }
    
}
