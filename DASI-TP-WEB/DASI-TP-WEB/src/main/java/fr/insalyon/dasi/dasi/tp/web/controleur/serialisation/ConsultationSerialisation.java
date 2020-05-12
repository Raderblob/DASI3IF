/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rader
 */
public class ConsultationSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consultation consultation = (Consultation)request.getAttribute("consultation");
        JsonObject container = new JsonObject();

        Boolean connexion = (consultation != null);
        container.addProperty("Done", connexion);

        if (consultation != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", consultation.getId());
            jsonClient.addProperty("medium", consultation.getMedium().getName());
            jsonClient.addProperty("caller", consultation.getCaller().getNom());
            if(consultation.getAcceptor() != null){
                jsonClient.addProperty("acceptor", consultation.getAcceptor().getNom());
            }else{
                jsonClient.addProperty("acceptor","none");
            }
            container.add("consultation", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
