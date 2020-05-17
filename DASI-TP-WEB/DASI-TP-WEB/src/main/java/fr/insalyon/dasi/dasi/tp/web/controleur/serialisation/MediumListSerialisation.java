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
import fr.insalyon.dasi.metier.modele.medium.Astrologue;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.medium.Spirite;
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
public class MediumListSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        if(mediums==null){
            mediums = new ArrayList<Medium>();
        }
        
        JsonObject container = new JsonObject();
        
        container.addProperty("mediumsNumber", mediums.size());
        
        JsonArray listContainer = new JsonArray();
        for(Medium med : mediums){
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", med.getId());
            jsonClient.addProperty("nom", med.getName());
            jsonClient.addProperty("presentation", med.getPresentation());
            if(med instanceof Spirite ){
                jsonClient.addProperty("support", ((Spirite) (med)).getSupport());
                jsonClient.addProperty("type", "spirite");
            }else if(med instanceof Astrologue){
                jsonClient.addProperty("formation", ((Astrologue) (med)).getFormation());
                jsonClient.addProperty("promotion", ((Astrologue) (med)).getPromotion());
                 jsonClient.addProperty("type", "astrologue");
            }else{
                jsonClient.addProperty("type", "cartomancien");
            }
            
            listContainer.add(jsonClient);
        }
        container.add("mediums", listContainer);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
