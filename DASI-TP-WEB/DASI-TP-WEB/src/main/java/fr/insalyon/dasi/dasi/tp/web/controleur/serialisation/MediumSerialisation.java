/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.medium.Astrologue;
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.medium.Spirite;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rader
 */
public class MediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Medium medium = (Medium)request.getAttribute("medium");
        JsonObject container = new JsonObject();

        Boolean connexion = (medium != null);
        container.addProperty("found", connexion);

        if (medium != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", medium.getId());
            jsonClient.addProperty("nom", medium.getName());
            jsonClient.addProperty("presentation", medium.getPresentation());
            if(medium instanceof Spirite ){
                jsonClient.addProperty("support", ((Spirite) (medium)).getSupport());
            }else if(medium instanceof Astrologue){
                jsonClient.addProperty("formation", ((Astrologue) (medium)).getFormation());
                jsonClient.addProperty("promotion", ((Astrologue) (medium)).getPromotion());
            }
            
            container.add("medium", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
