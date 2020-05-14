/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class GetConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("consultationId"));
        Service service = new Service();
        List<Consultation> result = new ArrayList<Consultation>();
        Consultation res = service.getConsultation(id);
        if(res!=null){
            result.add(res);
        }

        request.setAttribute("consultations", result);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (result!=null) {
            session.setAttribute("size",result.size() );
        }
        else {
            session.setAttribute("size",0);
        }
    }
    
}
