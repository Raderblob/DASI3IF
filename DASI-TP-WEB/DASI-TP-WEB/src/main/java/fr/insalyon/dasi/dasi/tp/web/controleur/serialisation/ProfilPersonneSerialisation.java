/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.tp.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.personne.Personne;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rader
 */
public class ProfilPersonneSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Personne personne = (Personne)request.getAttribute("personne");
        JsonObject container = new JsonObject();

        Boolean connexion = (personne != null);
        container.addProperty("connexion", connexion);

        if (personne != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", personne.getId());
            jsonClient.addProperty("nom", personne.getNom());
            jsonClient.addProperty("prenom", personne.getPrenom());
            jsonClient.addProperty("mail", personne.getMail());
            jsonClient.addProperty("type", personne.getClass().toString());
            container.add("personne", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
