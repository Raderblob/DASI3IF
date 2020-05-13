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
public class PredictionsSerialisation extends Serialisation{


    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> pInfo = (List<String>)request.getAttribute("result");
        if(pInfo==null){
            pInfo = new ArrayList<String>();
        }
        
        JsonObject container = new JsonObject();
        
        container.addProperty("infoNumber", pInfo.size());
        JsonArray listContainer = new JsonArray();
        for(String c : pInfo){
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("info", c);
            
            listContainer.add(jsonClient);
        }
        container.add("AllInfo", listContainer);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
