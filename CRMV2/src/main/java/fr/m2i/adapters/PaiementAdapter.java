package fr.m2i.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.m2i.model.Paiement;



public class PaiementAdapter implements JsonSerializer<Paiement> {
	
	@Override
	public JsonElement serialize(Paiement paiement, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();

		json.addProperty("id", paiement.getId());
		json.addProperty("noCarte", paiement.getNoCarte());
		json.addProperty("codeConfidentiel", paiement.getCodeConfidentiel());
		json.addProperty("banque", paiement.getBanque());

		JsonObject client = null;
		if(paiement.getClient() != null) {
			client = new JsonObject();
			client.addProperty("id", paiement.getClient().getId());
			client.addProperty("nom", paiement.getClient().getNom());
			client.addProperty("prenom", paiement.getClient().getPrenom());
		}
		json.add("client", client);


		return json;
	}


}
