/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.personne.Client;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rader
 */
public class EmployeeListSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Employee> employees = (List<Employee>)request.getAttribute("employees");
        if(employees==null){
            employees = new ArrayList<Employee>();
        }
        
        JsonObject container = new JsonObject();
        
        container.addProperty("EmployeesNumber", employees.size());
        
        JsonArray listContainer = new JsonArray();
        for(Employee e : employees){
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", e.getId());
            jsonClient.addProperty("mail", e.getMail());
            jsonClient.addProperty("nom", e.getNom());
            jsonClient.addProperty("prenom", e.getPrenom());
            jsonClient.addProperty("NumConsultations", e.getNumConsultations());
            listContainer.add(jsonClient);
        }
        container.add("employees", listContainer);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
