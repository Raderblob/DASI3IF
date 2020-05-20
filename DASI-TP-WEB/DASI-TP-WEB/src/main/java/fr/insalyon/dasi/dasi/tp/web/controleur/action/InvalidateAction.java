/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
public class InvalidateAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
         HttpSession session = request.getSession();
         session.invalidate();
    }
    
}
