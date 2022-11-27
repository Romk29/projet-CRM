package fr.m2i.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import fr.m2i.model.Adresse;
import fr.m2i.model.Client;

public class AdresseAdapter implements JsonSerializer<Adresse> {

	@Override
	public JsonElement serialize(Adresse adresse, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", adresse.getId());
		json.addProperty("rue", adresse.getRue());
		json.addProperty("ville", adresse.getVille());
		json.addProperty("pays", adresse.getPays());
		json.addProperty("code_Postal", adresse.getCodePostal());
		
		JsonArray clientsJson = new JsonArray();
		JsonObject tmp;
		for(Client l : adresse.getClients()) {
			tmp = new JsonObject();
			tmp.addProperty("id", l.getId());
			tmp.addProperty("nom", l.getNom());
			tmp.addProperty("prenom", l.getPrenom());
			clientsJson.add(tmp);
		}
		
		json.add("clients", clientsJson);
		
		return json;
	}

}
