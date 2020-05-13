/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rader
 */
public class EmployeeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee employee = (Employee)request.getAttribute("personne");
        JsonObject container = new JsonObject();

        Boolean connexion = (employee != null);
        container.addProperty("connexion", connexion);

        if (employee != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", employee.getId());
            jsonClient.addProperty("nom", employee.getNom());
            jsonClient.addProperty("prenom", employee.getPrenom());
            jsonClient.addProperty("mail", employee.getMail());
            jsonClient.addProperty("Available", employee.getAvailable());
            jsonClient.addProperty("NumConsultations", employee.getNumConsultations());
            jsonClient.addProperty("Gender", employee.getMyGender().name());
            container.add("personne", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
