package fr.m2i.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.m2i.model.Panier;
import fr.m2i.model.Produit;



public class PanierAdapter implements JsonSerializer<Panier> {

	@Override
	public JsonElement serialize(Panier panier, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", panier.getId());
		
		JsonObject client = null;
		if(panier.getClient() != null) {		
			client = new JsonObject();
			client.addProperty("id", panier.getClient().getId());
			client.addProperty("nom", panier.getClient().getNom());
			client.addProperty("prenom", panier.getClient().getPrenom());
		}
		json.add("client", client);
		
		JsonArray produitsJson = new JsonArray();
		JsonObject tmp;
		for(Produit p : panier.getProduits()) {
			tmp = new JsonObject();
			tmp.addProperty("id", p.getId());
			tmp.addProperty("nom", p.getNom());
			tmp.addProperty("prix", p.getPrix());
			produitsJson.add(tmp);
		}
		
		json.add("produits", produitsJson);
		
		return json;
	}

}
