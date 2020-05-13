/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dasi.tp.web.controleur.action.*;
import fr.insalyon.dasi.dasi.tp.web.controleur.serialisation.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rader
 */
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {
    @Override
    public void init() throws ServletException {
      super.init();
      JpaUtil.init();
    }
    @Override
    public void destroy() {
      JpaUtil.destroy();
      super.destroy();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        Action action = null;
        Serialisation serialisation = null;
        
        String todo = request.getParameter("todo");
        
        if (todo != null) {
            switch (todo) {
                case "consulter_liste_medium":
                    action = new GetMediumsAction();
                    serialisation = new MediumListSerialisation();
                    break;
                case "connecter":
                    action = new AuthentifierAction();
                    serialisation = new ProfilPersonneSerialisation();
                    break;
                case "createAccount":
                    action = new SignUpAction();
                    serialisation = new ProfilPersonneSerialisation();
                    break;
                case "changePassword":
                    action = new ChangePasswordAction();
                    serialisation = new ProfilPersonneSerialisation();
                    break;
                case "getMedium":
                    action = new GetMediumAction();
                    serialisation = new MediumSerialisation();
                    break;
                case "requestConsultation":
                    action = new SendRequestAction();
                    serialisation = new ConsultationSerialisation();
                    break;
                case "getFilteredMediums":
                    action = new GetFilteredMediums();
                    serialisation = new MediumListSerialisation();
                    break;
                case "getClientList":
                    action = new GetClientsAction();
                    serialisation = new ClientListSerialisation();
                    break;
                case "getEmployeeList":
                    action = new GetEmployeesAction();
                    serialisation = new EmployeeListSerialisation();
                    break;
                case "getUnansweredRequests":
                    action = new GetUnansweredRequestsForAction();
                    serialisation = new ConsultationListSerialisation();
                    break;
                case "getClientConsultations":
                    action = new GetClientConsultationsAction();
                    serialisation = new ConsultationListSerialisation();
                    break;
                case "getClient":
                    action = new GetUserAction();
                    serialisation = new ClientSerialisation();
                    break;
                case "getEmployee":
                    action = new GetUserAction();
                    serialisation = new EmployeeSerialisation();
                    break;
                case "getPredictions":
                    action = new GetPredictionsAction();
                    serialisation = new PredictionsSerialisation();
                    break;
                case "acceptConsultation":
                    action = new AcceptConsultationAction();
                    serialisation = new EmployeeSerialisation();
                    break;
                case "confirmConsultation":
                    action = new ConfirmConsultationAction();
                    serialisation = new EmployeeSerialisation();
                    break;
                default:
                    action = new GetMediumsAction();
                    serialisation = new MediumListSerialisation();
                    break;
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
