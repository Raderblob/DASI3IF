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
import fr.insalyon.dasi.metier.modele.medium.Medium;
import fr.insalyon.dasi.metier.modele.personne.Client;
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
public class ClientListSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Client> clients = (List<Client>)request.getAttribute("clients");
        if(clients==null){
            clients = new ArrayList<Client>();
        }
        
        JsonObject container = new JsonObject();
        
        container.addProperty("ClientsNumber", clients.size());
        
        JsonArray listContainer = new JsonArray();
        for(Client cl : clients){
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", cl.getId());
            jsonClient.addProperty("mail", cl.getMail());
            jsonClient.addProperty("nom", cl.getNom());
            jsonClient.addProperty("prenom", cl.getPrenom());
            jsonClient.addProperty("numConsulations", cl.getMyConsultationHistory().size());
            listContainer.add(jsonClient);
        }
        container.add("clients", listContainer);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
