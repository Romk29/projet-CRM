package fr.m2i.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
@WebServlet("/AjouterAdresse")
public class AjouterAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdresseDao adresseDao;

	public AjouterAdresse() {
		super();
		adresseDao = DaoFactory.getInstance().getAdresseDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAdresse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> erreurs = new HashMap<String, String>();
		String rue = request.getParameter("rueAdresse");
		String ville = request.getParameter("villeAdresse");
		String pays = request.getParameter("paysAdresse");
		String codePostal = request.getParameter("codePostalAdresse");

		// Ajout des contrôles
		if (rue.length() == 0) {

			erreurs.put("rueAdresse", "Merci d'entrer la rue.");
		}

		if (ville.length() == 0) {

			erreurs.put("villeAdresse", "Merci d'entrer la ville.");
		}

		if (pays.length() == 0) {

			erreurs.put("paysAdresse", "Merci d'entrer un pays.");
		}
		
		Pattern patternAdress = Pattern.compile("[0-9]{5}");

		if (!patternAdress.matcher(codePostal).matches()) {

			erreurs.put("codePostalAdresse", "le code postale n'est pas valide il lui faut 5 chiffre");
		}

		Adresse adresse = new Adresse();
		adresse.setRue(rue);
		adresse.setVille(ville);
		adresse.setPays(pays);
		adresse.setCodePostal(codePostal);

		if (erreurs.isEmpty()) {
			try {
				adresseDao.creer(adresse);
				request.getSession().setAttribute("confirmMessage", "L'adresse a bien été ajoutée !");

				response.sendRedirect(request.getContextPath() + "/ListeAdresses");
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("adresse", adresse);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAdresse.jsp").forward(request, response);
		}

	}
}
