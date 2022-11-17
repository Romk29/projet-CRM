package fr.m2i.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.dao.AdresseDao;
import fr.m2i.dao.ClientDao;
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.model.Adresse;
import fr.m2i.model.Client;


@WebServlet("/ModifierClient")
public class ModifierClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDao clientDao;
	private AdresseDao adresseDao;
       
    
    public ModifierClient() {
        super();
        clientDao = DaoFactory.getInstance().getClientDao();
        adresseDao = DaoFactory.getInstance().getAdresseDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("client", clientDao.trouver(id));
			request.setAttribute("adresses", adresseDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierClient.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
Map<String, String> erreurs = new HashMap<String, String>();
		
		String nom = request.getParameter("nomClient");
		String prenom = request.getParameter("prenomClient");
		int id = Integer.parseInt(request.getParameter("id"));
		
		Adresse adresse = null;
		try {
			int idAdresse = Integer.parseInt(request.getParameter("adresseClient"));
			adresse = adresseDao.trouver(idAdresse);
		} catch (DaoException | NumberFormatException e) {
			erreurs.put("adresseClient", "Erreur l'adresse n'existe pas.");
		}
				
		
		String nomSociete = request.getParameter("nomSocieteClient");
		String mail = request.getParameter("mailClient");
		String telephone = request.getParameter("telephoneClient");
		
		int etat = 0;
		int genre = 0;
		
		if (nom.length() == 0) {
			erreurs.put("nomClient", "Merci d'entrer un nom.");
		}
		
		if (prenom.length() == 0) {
			erreurs.put("prenomClient", "Merci d'entrer un prenom.");
		}
		
		if(mail != null) {
			if(mail.length() > 60) {
				erreurs.put("emailClient", "Un email doit avoir maximum 60 caractères.");
			}
			if(!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				erreurs.put("emailClient", "Merci d'entrer une adresse email valide.");
			}
		}
		
		if(telephone != null) {
			if(telephone.length() > 10) {
				erreurs.put("telephoneClient", "Un numéro de téléphone doit avoir maximum 10 caractères.");
			}
			if(!telephone.matches("^\\d+$")) {
				erreurs.put("telephoneClient", "Un numéro de téléphone doit contenir uniquement des chiffres.");
			}
		} else {
			erreurs.put("telephoneClient", "Merci d'entrer un numéro de téléphone.");
		}
		
		try {
			etat = Integer.parseInt(request.getParameter("etat"));
		} catch (NumberFormatException e) {
			erreurs.put("etatClient", "Merci d'entrer 1 pour un 'propre' ou 2 pour 'sale' .");
		}
		
		try {
			genre = Integer.parseInt(request.getParameter("genre"));
		} catch (NumberFormatException e) {
			erreurs.put("genreClient", "Merci d'entrer 1 pour 'homme', 2 pour 'femme' ou 3 pour 'autre'.");
		}
		
		
		
		Client client = null;
		try {
			client = clientDao.trouver(id);
		} catch (DaoException e1) {
			client = new Client();
			erreurs.put("client", "Erreur le client n'existe pas.");
		}
		
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setNomSociete(nomSociete);
		client.setMail(mail);
		client.setTelephone(telephone);
		client.setEtat(etat);
		client.setGenre(genre);
			
		if (erreurs.isEmpty()) {
		
		try {
			clientDao.miseAJour(client);
			
			request.getSession().setAttribute("confirmMessage", "Le client a bien été modifié !");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/ListeClients");
		
		} else {
			
			try {
				request.setAttribute("adresseClient", adresseDao.lister());
			} catch (DaoException e) {
				e.printStackTrace();
			}
			request.setAttribute("client", client);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterClient.jsp").forward(request, response);
		}
	}

}
