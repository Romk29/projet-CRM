package utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import fr.m2i.adapters.PaiementAdapter;
import fr.m2i.adapters.ClientAdapter;
import fr.m2i.adapters.AdressetAdapter;
import fr.m2i.adapters.ProduittAdapter;
import fr.m2i.adapters.PanierAdapter;
import fr.m2i.model.Adresse;
import fr.m2i.model.Client;
import fr.m2i.model.Paiement;
import fr.m2i.model.Panier;
import fr.m2i.model.Produit;



public class Utils {

	public static Gson getSuperJson() {
		GsonBuilder gsonBuilder = new GsonBuilder()
				.registerTypeAdapter(Client.class, new ClientAdapter())
				.registerTypeAdapter(Adresse.class, new AdresseAdapter())
				.registerTypeAdapter(Paiement.class, new PaiementAdapter())
				.registerTypeAdapter(Produit.class, new ProduitAdapter())
				.registerTypeAdapter(Panier.class, new PanierAdapter())
				.serializeNulls();

		return gsonBuilder.create();
	}


	public static JsonObject getJsonFromBuffer(HttpServletRequest request) throws IOException, JsonSyntaxException {
		//Récupération du body de la requête sous forme de String
		StringBuffer buffer = new StringBuffer();
		String line = null, body = "";
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		body = buffer.toString();

		//Récupération d'un objet JAVA représentant un JSON
		JsonObject data = JsonParser.parseString(body).getAsJsonObject();

		return data;
	}

	public static String getStringParameter(JsonObject data, String nameField, boolean isNullable, int minLength, int maxLength) throws ServiceException {
		String parameter = null;

		if(data.get(nameField) != null && !data.get(nameField).isJsonNull()) {
			parameter = data.get(nameField).getAsString().trim();

			if(parameter.length() < minLength) {
				throw new ServiceException("Le champ "+nameField+ " doit contenir au moins "+minLength+" caractères.");
			}

			if(parameter.length() > maxLength) {
				throw new ServiceException("Le champ "+nameField+ " doit contenir au maximum "+maxLength+" caractères.");
			}
		}


		if(!isNullable && parameter == null) {
			throw new ServiceException("Le champ "+nameField+ " est obligatoire.");
		}

		return parameter;
	}

	public static String getStringParameter(JsonObject data, String nameField, boolean isNullable, int minLength, int maxLength, String regexFormat) throws ServiceException {
		String parameter = getStringParameter(data, nameField, isNullable, minLength, maxLength);

		if(parameter != null) {
			if(!parameter.matches(regexFormat)) {
				throw new ServiceException("Le champ "+nameField+ " n'a pas un format valide.");
			}
		}

		return parameter;
	}

}
