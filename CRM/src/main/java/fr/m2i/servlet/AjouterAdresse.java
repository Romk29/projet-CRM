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
import fr.m2i.dao.DaoException;
import fr.m2i.dao.DaoFactory;
import fr.m2i.model.Adresse;

/**
 * Servlet implementation class AjouterAdresse
 */
@WebServlet("/ajouterAdresse")
public class AjouterAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdresseDao adresseDao;
	
    public AjouterAdresse() {
        super();
        adresseDao = DaoFactory.getInstance().getAdresseDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAdresse.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Map<String, String> erreurs = new HashMap<String, String>();
		
		String rue = request.getParameter("rueAdresse");
		String ville = request.getParameter("villeAdresse");
		String pays = request.getParameter("paysAdresse");
		int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		
		/*
		//Ajout des contrôles
		if(nom != null) {
			if(nom.length() < 2 || nom.length() > 20) {
				erreurs.put("nomAdresse", "Un nom d'adresse doit contenir entre 2 et 20 caractères.");
			}
		} else {
			erreurs.put("nomAdresse", "Merci d'entrer un nom d'adresse.");
		}
		
		if(prenom != null) {
			if(prenom.length() > 20) {
				erreurs.put("prenomAdresse", "Un prénom d'adresse doit avoir maximum 20 caractères.");
			}
		}
		
		if(telephone != null) {
			if(telephone.length() > 10) {
				erreurs.put("telephoneAdresse", "Un numéro de téléphone doit avoir maximum 10 caractères.");
			}
			if(!telephone.matches("^\\d+$")) {
				erreurs.put("telephoneAdresse", "Un numéro de téléphone doit contenir uniquement des chiffres.");
			}
		} else {
			erreurs.put("telephoneAdresse", "Merci d'entrer un numéro de téléphone.");
		}
		
		if(email != null) {
			if(email.length() > 60) {
				erreurs.put("emailAdresse", "Un email doit avoir maximum 60 caractères.");
			}
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				erreurs.put("emailAdresse", "Merci d'entrer une adresse email valide.");
			}
		}
		*/
		Adresse adresse = new Adresse();
		adresse.setRue(rue);
		adresse.setVille(ville);
		adresse.setPays(pays);
		adresse.setCodePostal(codePostal);
		
		//if(erreurs.isEmpty()) {
			try {
				adresseDao.creer(adresse);
				request.getSession().setAttribute("confirmMessage", "L'adresse a bien été ajouté !");
				
				response.sendRedirect( request.getContextPath() + "/listeAdresses" );
			} catch (DaoException e) {
				e.printStackTrace();
			}
		/* } else {
			request.setAttribute("adresse", adresse);
			request.setAttribute("erreurs", erreurs);
*/
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAdresse.jsp").forward(request, response);
		}		
	

}
