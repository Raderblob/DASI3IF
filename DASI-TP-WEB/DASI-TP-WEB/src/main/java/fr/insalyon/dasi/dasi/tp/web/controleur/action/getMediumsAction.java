/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Rader
 */
public class GetMediumsAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {


        Service service = new Service();
        List<Medium> result = service.GetListOfMediums("Medium");

        request.setAttribute("mediums", result);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (result.isEmpty()) {
            session.setAttribute("size",result.size() );
        }
        else {
            session.setAttribute("size",result.size());
        }
    }
    
}
