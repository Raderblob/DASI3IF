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
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.personne.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rader
 */
public class ConsultationListSerialisation  extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH-mm-ss");
        
        List<Consultation> consultations = (List<Consultation>)request.getAttribute("consultations");
        if(consultations==null){
            consultations = new ArrayList<Consultation>();
        }
        
        JsonObject container = new JsonObject();
        
        container.addProperty("ConsultationsNumber", consultations.size());
        JsonArray listContainer = new JsonArray();
        for(Consultation c : consultations){
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", c.getId());
            jsonClient.addProperty("caller", c.getCaller().getMail());
            jsonClient.addProperty("nom", c.getMedium().getName());
            jsonClient.addProperty("state", c.getState().toString());
            if(c.getAcceptor()!=null){
                jsonClient.addProperty("acceptor", c.getAcceptor().getMail());
            }else{
                jsonClient.addProperty("acceptor", "none");
            }
            if(c.getDate()!=null){
                jsonClient.addProperty("date", dateFormat.format(c.getDate()));
            }else{
                jsonClient.addProperty("date", "Not specified");
            }
            if(c.getDate()!=null){
                jsonClient.addProperty("endHour", hourFormat.format(c.getDate()));
            }else{
                jsonClient.addProperty("endHour", "Not specified");
            }
            if(c.getStartDate()!=null){
                jsonClient.addProperty("startHour", hourFormat.format(c.getStartDate()));
            }else{
                jsonClient.addProperty("startHour", "Not specified");
            }
            jsonClient.addProperty("comment", c.getComment());
            jsonClient.addProperty("length", c.getConsultationLength());
            
            listContainer.add(jsonClient);
        }
        container.add("consultations", listContainer);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
