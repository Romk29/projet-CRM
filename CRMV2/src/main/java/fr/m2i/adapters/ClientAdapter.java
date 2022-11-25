package fr.m2i.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.m2i.model.Adresse;
import fr.m2i.model.Client;
import fr.m2i.model.Paiement;
import fr.m2i.model.Panier;

public class ClientAdapter implements JsonSerializer<Client> {

	@Override
	public JsonElement serialize(Client client, Type typeOfSrc, JsonSerializationContext context) {
		
		JsonObject json = new JsonObject();
		json.addProperty("id", client.getId());
		json.addProperty("nom", client.getNom());
		json.addProperty("prenom", client.getPrenom());
		json.addProperty("mail", client.getMail());
		json.addProperty("nomSociete", client.getNomSociete());
		json.addProperty("telephone", client.getTelephone());
		json.addProperty("etat", client.getEtat());
		json.addProperty("genre", client.getGenre());
		json.addProperty("adresse", client.getAdresse().getId());
		
		JsonArray paniersJson = new JsonArray();
		JsonObject tmp;
		for(Panier p : client.getPaniers()) {
			tmp = new JsonObject();
			tmp.addProperty("id", p.getId());
			paniersJson.add(tmp);
		}		
		json.add("paniers", paniersJson);
		
		JsonArray paiementsJson = new JsonArray();
		JsonObject tmp2;
		for(Paiement pt : client.getPaiements()) {
			tmp2 = new JsonObject();
			tmp2.addProperty("id", pt.getId());
			paiementsJson.add(tmp2);
		}		
		json.add("paiements", paiementsJson);
		
		return json;
	}

}
