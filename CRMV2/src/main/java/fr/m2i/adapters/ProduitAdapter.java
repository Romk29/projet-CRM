package fr.m2i.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import fr.m2i.model.Produit;
import fr.m2i.model.Panier;

class ProduitAdapter implements JsonSerializer<Produit> {

	@Override
	public JsonElement serialize(Produit produit, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", produit.getId());
		json.addProperty("nom", produit.getNom());
		json.addProperty("description", produit.getDescription());
		json.addProperty("prix", produit.getPrix());
		
		
		JsonArray paniersJson = new JsonArray();
		JsonObject tmp;
		for(Panier p : produit.getPaniers()) {
			tmp = new JsonObject();
			tmp.addProperty("id", p.getId());
			
			
			paniersJson.add(tmp);
		}
		
		json.add("paniers", paniersJson);
		
		return json;
	}

}